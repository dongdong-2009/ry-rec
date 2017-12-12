package com.rytec.rec.channel.ModbusTcpServer.handler;

import com.rytec.rec.channel.ModbusTcpServer.ModbusChannelSession;
import com.rytec.rec.channel.ModbusTcpServer.ModbusCommon;
import com.rytec.rec.channel.ModbusTcpServer.ModbusMessage;
import com.rytec.rec.util.CRC16;
import com.rytec.rec.util.ConstantFromWhere;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by danny on 16-12-12.
 * 可以考虑使用 ReplayingDecoder
 */
public class ModbusFrameDecoder extends ByteToMessageDecoder {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {

        // 得到Channel 对应的 Session
        ModbusChannelSession modbusChannelSession = ctx.channel().attr(ModbusCommon.MODBUS_STATE).get();

        // 当前发送的命令
        ModbusMessage lastOutMsg = modbusChannelSession.getLastOutMsg();

        // 当前读取缓冲的状态
        int inBufferLen = in.readableBytes();
        int readIndex = in.readerIndex();
        int writeIndex = in.writerIndex();

        if (lastOutMsg == null) {
            byte[] remain = new byte[inBufferLen];
            in.getBytes(readIndex, remain, 0, inBufferLen);
            logger.debug("超时遗留数据：" + CRC16.bytesToHexString(remain));
            return;
        }

        // 没有足够的长度，返回
        if (inBufferLen < lastOutMsg.responseLen) {
            return;
        }

        /*
         * 这里应该是读取最后一段的内容，
         * 因为重头读取可能读取到上一次没有处理完的脏数据
         * 当成功收到回应后，清楚当前的缓冲
         * -----------------------
         * |  遗留数据 |  有效数据 |
         * -----------------------
         * read                 write
         * readableBytes() - len ： 需要跳过的字节
         */

        // todo: data 和 payload 可以放在 session 中增加效率
        /*
         * 只收取尾部期望的长度
         */
        byte[] data = new byte[lastOutMsg.responseLen];
        in.getBytes(writeIndex - lastOutMsg.responseLen, data, 0, lastOutMsg.responseLen);

        // 检查发送和接收的头部是相等的，地址和功能码是否一致
        lastOutMsg.payload.resetReaderIndex();
        if (((lastOutMsg.payload).getByte(0) == data[0]) & ((lastOutMsg.payload).getByte(1) == data[1])) {
            // 地址和功能码一致，可以继续
        } else {
            logger.debug("解码失败！！！！！！！！！！！！！！！！");
            modbusChannelSession.goodHelth(lastOutMsg, false);
            return;
        }


        // 检查包的CRC
        int error = CRC16.check(data);

        // 没有错误
        if (error == 0) {

            in.skipBytes(inBufferLen);
            // 成功组帧
            ByteBuf payload = Unpooled.buffer(lastOutMsg.responseLen);
            payload.setBytes(0, data);

            ModbusMessage msg = new ModbusMessage(ConstantFromWhere.FROM_RPS);
            msg.payload = payload;

            msg.nodeId = lastOutMsg.nodeId;
            msg.type = lastOutMsg.type;

            modbusChannelSession.goodHelth(lastOutMsg, true);

            // 清除当前发送的命令
            modbusChannelSession.clearLastOutMsg();

            out.add(msg);
        } else {
            // 解码错误，打印当前的内容和解码的内容
            modbusChannelSession.goodHelth(lastOutMsg, false);
            byte[] remain = new byte[inBufferLen];
            in.getBytes(readIndex, remain, 0, inBufferLen);
            logger.debug("遗留数据：" + CRC16.bytesToHexString(remain));
            logger.debug("解码数据：" + CRC16.bytesToHexString(data));
        }
    }
}
