package com.rytec.rec.service;

import com.rytec.rec.app.RecBase;
import org.openmuc.openiec61850.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 61850 的Java实现
 */
@Service
public class Iec61850Adapter extends RecBase implements ServerEventListener {

    List<ServerSap> serverSaps = null;
    ServerSap serverSap = null;
    ServerModel serversServerModel;


    private void setBdaValue(BasicDataAttribute bda, String valueString) {
        if (bda instanceof BdaFloat32) {
            float value = Float.parseFloat(valueString);
            ((BdaFloat32) bda).setFloat(value);
        } else if (bda instanceof BdaFloat64) {
            double value = Float.parseFloat(valueString);
            ((BdaFloat64) bda).setDouble(value);
        } else if (bda instanceof BdaInt8) {
            byte value = Byte.parseByte(valueString);
            ((BdaInt8) bda).setValue(value);
        } else if (bda instanceof BdaInt8U) {
            short value = Short.parseShort(valueString);
            ((BdaInt8U) bda).setValue(value);
        } else if (bda instanceof BdaInt16) {
            short value = Short.parseShort(valueString);
            ((BdaInt16) bda).setValue(value);
        } else if (bda instanceof BdaInt16U) {
            int value = Integer.parseInt(valueString);
            ((BdaInt16U) bda).setValue(value);
        } else if (bda instanceof BdaInt32) {
            int value = Integer.parseInt(valueString);
            ((BdaInt32) bda).setValue(value);
        } else if (bda instanceof BdaInt32U) {
            long value = Long.parseLong(valueString);
            ((BdaInt32U) bda).setValue(value);
        } else if (bda instanceof BdaInt64) {
            long value = Long.parseLong(valueString);
            ((BdaInt64) bda).setValue(value);
        } else {
            throw new IllegalArgumentException();
        }
    }


    @PostConstruct
    public void startService() throws SclParseException, IOException {
        serverSaps = ServerSap.getSapsFromSclFile("/home/danny/projects/ry-rec/doc/icd/RYTEC-SCL.icd");
        serverSap = serverSaps.get(0);
        serverSap.setPort(9999);
        serverSap.startListening(this);
        serversServerModel = serverSap.getModelCopy();
        System.out.println(serversServerModel);

        BasicDataAttribute bda = (BasicDataAttribute) serversServerModel.findModelNode("RYTEC-RECSENSORS/MMXU3.value.instMag.f", Fc.MX);
        BasicDataAttribute bda1 = (BasicDataAttribute) serversServerModel.findModelNode("RYTEC-RECSENSORS/CALH1.value.stVal", Fc.ST);


        ((BdaBoolean)bda1).setValue(true);
        try {
            setBdaValue(bda, "11.234");
        } catch (Exception e) {
            System.out.println(
                    "The console server does not support writing this type of basic data attribute.");
            return;
        }

        List<BasicDataAttribute> bdas = new ArrayList<>();
        bdas.add(bda);
        bdas.add(bda1);
        serverSap.setValues(bdas);

        System.out.println(serversServerModel);
    }

    @PreDestroy
    public void stopService() {
        serverSap.stop();
    }

    @Override
    public List<ServiceError> write(List<BasicDataAttribute> bdas) {
        return null;
    }

    @Override
    public void serverStoppedListening(ServerSap serverSAP) {

    }
}
