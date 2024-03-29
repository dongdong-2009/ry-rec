package com.rytec.rec.db;

import com.rytec.rec.app.ManageableInterface;
import com.rytec.rec.db.mapper.*;
import com.rytec.rec.db.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danny on 16-12-28.
 * <p>
 * 数据库的所有配置信息都在这里集中读取和初始化
 */

@Service
@Order(200)
public class DbConfig implements ManageableInterface {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<ChannelNode> channelNodeList;
    private List<DeviceNode> deviceNodeList;
    private List<Device> deviceList;
    private List<RuleAction> ruleActionList;
    private List<Channel> channelList;
    private List<Config> configList;
    private List<DeviceGis> deviceGisList;
    private List<GisLayer> gisLayerList;

    @Autowired
    ChannelNodeMapper channelNodeMapper;

    @Autowired
    DeviceNodeMapper deviceNodeMapper;

    @Autowired
    DeviceMapper deviceMapper;

    @Autowired
    RuleActionMapper ruleActionMapper;

    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    ConfigMapper configMapper;

    @Autowired
    DeviceGisMapper deviceGisMapper;

    @Autowired
    GisLayerMapper gisLayerMapper;


    /**
     * 初始化 ChannelNode 列表
     */
    private void initChannelNode() {

        byte a = 0;

        ChannelNodeExample channelNodeExample = new ChannelNodeExample();

        channelNodeExample.createCriteria().andNidGreaterThan(0).andEnableGreaterThan(a);

        channelNodeList = channelNodeMapper.selectByExample(channelNodeExample);

    }

    /**
     * 初始化 DeviceNode 列表
     */
    private void initDeviceNode() {

        DeviceNodeExample deviceNodeExample = new DeviceNodeExample();

        deviceNodeExample.createCriteria().andNidGreaterThan(0);

        deviceNodeList = deviceNodeMapper.selectByExample(deviceNodeExample);
    }

    /**
     * 初始化Device 列表
     */
    private void initDevice() {
        deviceList = deviceMapper.selectByExample(null);
    }


    /**
     * 初始化 RuleAction 列表
     */
    private void initRuleAction() {
        ruleActionList = ruleActionMapper.selectByExample(null);
    }

    /**
     * 初始化 Channel 列表
     */
    private void initChannel() {
        channelList = channelMapper.selectByExample(null);
    }

    private void initConfig() {
        configList = configMapper.selectByExample(null);
    }

    private void initDeviceGis() {
        deviceGisList = deviceGisMapper.selectByExample(null);
    }

    private void initGisLayer(){
        gisLayerList = gisLayerMapper.selectByExample(null);
    }
    /**
     * @return ChannelNode 的列表
     */
    public List<ChannelNode> getChannelNodeList() {
        if (channelNodeList == null) {
            initChannelNode();
        }
        return channelNodeList;
    }

    /**
     * DeviceNode 的列表
     *
     * @return
     */
    public List<DeviceNode> getDeviceNodeList() {
        if (deviceNodeList == null) {
            initDeviceNode();
        }
        return deviceNodeList;
    }

    /**
     * Device 列表
     *
     * @return
     */
    public List<Device> getDeviceList() {
        if (deviceList == null) {
            initDevice();
        }
        return deviceList;
    }

    /**
     * RuleAction 列表
     */

    public List<RuleAction> getRuleActionList() {
        if (ruleActionList == null) {
            initRuleAction();
        }

        return ruleActionList;
    }

    public List<Channel> getChannelList() {
        if (channelList == null) {
            initChannel();
        }

        return channelList;
    }

    public List<Config> getConfigList() {
        if (configList == null) {
            initConfig();
        }

        return configList;
    }

    public List<DeviceGis> getDeviceGisList() {
        if (deviceGisList == null) {
            initDeviceGis();
        }

        return deviceGisList;
    }

    public List<GisLayer> getGisLayerList(){
        if (gisLayerList == null){
            initGisLayer();
        }
        return gisLayerList;
    }

    /**
     * 实现管理接口
     */
    public void stop() {
        channelNodeList = null;
        deviceNodeList = null;
        deviceList = null;
        ruleActionList = null;
        channelList = null;
        configList = null;
        deviceGisList = null;
        gisLayerList = null;
    }

    public void start() {

    }

}
