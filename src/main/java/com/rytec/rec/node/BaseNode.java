package com.rytec.rec.node;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by danny on 17-1-21.
 * Node 的基础类
 */
public abstract class BaseNode {

    /**
     * 从数据库的配置生成Node的配置对象
     *
     * @param inStr //json 配置字符串
     * @return //
     */
    static public NodeConfig parseConfig(String inStr) {
        NodeConfig nodeConfig;
        try {
            nodeConfig = new ObjectMapper().readValue(inStr, NodeConfig.class);
        } catch (IOException e) {
            nodeConfig = new NodeConfig();

        }

        // 设定初始值
        if (nodeConfig.pA == null) {
            nodeConfig.pA = new Float(1);
        }
        if (nodeConfig.pB == null) {
            nodeConfig.pB = new Float(0);
        }

        return nodeConfig;
    }

    // 预解码

}