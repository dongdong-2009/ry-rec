package com.rytec.rec.web.app;

import com.rytec.rec.channel.ChannelInterface;
import com.rytec.rec.channel.ChannelManager;
import com.rytec.rec.device.AbstractOperator;
import com.rytec.rec.device.DeviceManager;
import com.rytec.rec.node.NodeInterface;
import com.rytec.rec.node.NodeManager;
import com.rytec.rec.util.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by danny on 17-1-31.
 * <p>
 * 输出JavaScript的常量数组
 */

@Controller
public class Config implements ConstantDeviceFunction, ConstantDeviceState, ConstantErrorCode {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DeviceManager deviceManager;

    @Autowired
    ChannelManager channelManager;

    @Autowired
    NodeManager nodeManager;

    @RequestMapping(value = "/config")
    public String genJsConfig(Model model) {

        // 把WEB端所需要的常量输出
        Class<?>[] constSets = this.getClass().getInterfaces();

        HashMap<String, Object> rst = new HashMap<>();

        for (Class<?> constant : constSets) {
            Description annotation = constant.getAnnotation(Description.class);
            HashMap<Integer, String> jsConst = new HashMap<>();
            try {
                for (Field field : constant.getDeclaredFields()) {

                    String name = field.getName();
                    int value = field.getInt(null);
                    jsConst.put(value, field.getAnnotation(JSExport.class).value());
                }
            } catch (IllegalAccessException e) {

            }
            rst.put(annotation.value(), jsConst);
        }

        // 把 Channel、Node、Device 的类型进行常量的输出

        // 得到Channel的类型常量
        HashMap<Integer, ChannelInterface> cis = channelManager.getAllChannelInterface();

        HashMap<Integer, String> ccm = new HashMap<>();
        for (Integer i : cis.keySet()) {
            ChannelInterface ci = cis.get(i);
            ccm.put(i, ci.getClass().getAnnotation(Description.class).value());
        }
        rst.put("CHANNEL_TYPE", ccm);

        // Node 常量
        Map<Integer, NodeInterface> nis = nodeManager.getAllNodeInterface();
        HashMap<Integer, String> cnm = new HashMap<>();
        for (Integer i : nis.keySet()) {
            NodeInterface ni = nis.get(i);
            cnm.put(i, ni.getClass().getAnnotation(Description.class).value());
        }
        rst.put("NODE_TYPE", cnm);

        // Device
        Map<Integer, AbstractOperator> dos = deviceManager.getAllDeviceOperator();
        HashMap<Integer, String> cdo = new HashMap<>();
        for (Integer i : dos.keySet()) {
            AbstractOperator doi = dos.get(i);
            cdo.put(i, doi.getClass().getAnnotation(Description.class).value());
        }
        rst.put("DEVICE_TYPE", cdo);

        model.addAttribute("const", rst);
        return "const";
    }
}
