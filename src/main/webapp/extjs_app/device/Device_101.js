/**
 * Created by danny on 17-2-16.
 *
 * 开关控制
 */

// 配置面板
Ext.define('app.device.Device_101', {
    //没有配置面板

    // 开关控制面板
    controlPanel: Ext.create('Ext.panel.Panel', {
        id: 'device.controlpanel.101',
        bodyPadding: 5,
        autoDestroy: false,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },

        items: [{
            xtype: 'textfield',
            itemId: 'output',
            readOnly: true,
            fieldLabel: '状态',
            value: ""
        }, {
            xtype: 'textfield',
            itemId: 'remote',
            readOnly: true,
            fieldLabel: '工作模式',
            value: ""
        }, {
            xtype: 'textfield',
            itemId: 'feedback',
            readOnly: true,
            fieldLabel: '反馈状态',
            value: ""
        }, {
            xtype: 'button',
            itemId: 'button',
            text: 'Click me',
            handler: function () {
                if (this.ownerCt.runtime.state.output == 20) {
                    // 开启
                    action.operate(this.ownerCt.runtime.device, 101, null);
                } else {
                    // 关闭
                    action.operate(this.ownerCt.runtime.device, 100, null);
                }
            }
        }],

        // 更新状态
        updateState: function (state) {
            // 不是自己的数据
            if (state.device.type != 101) {
                return;
            }

            this.runtime = state.runtime;

            this.down("#button").setActive(true);
            // 开关状态
            if (this.runtime.state.output == 20) {
                this.down("#output").setValue("关闭");
                this.down("#button").setText("点击开启")
            }
            if (this.runtime.state.output == 21) {
                this.down("#output").setValue("开启");
                this.down("#button").setText("点击关闭")
            }
            if (this.runtime.state.output == null) {
                this.down("#output").setValue("失效！");
                this.down("#button").setActive(false)
            }

            // 远程、就地状态
            if (this.runtime.state.remote == 20) {
                this.down("#remote").setValue("远程控制")
            }
            if (this.runtime.state.remote == 21) {
                this.down("#remote").setValue("就地控制")
            }
            if (this.runtime.state.remote == null) {
                this.down("#remote").setValue("未配置/失效！")
            }

            // 反馈状态
            if (this.runtime.state.feedback == 20) {
                this.down("#feedback").setValue("关闭")
            }
            if (this.runtime.state.feedback == 21) {
                this.down("#feedback").setValue("开启")
            }
            if (this.runtime.state.feedback == null) {
                this.down("#feedback").setValue("未配置/失效！")
            }

        }
    })
});

ry.devices['device_101'] = Ext.create('app.device.Device_101', {});

ry.deviceEditor['act_101'] = Ext.create('Ext.form.field.ComboBox', {
    forceSelection: true,
    allowBlank: false,
    editable: false,
    autoSelect: true,
    triggerAction: 'all',
    store: ry.DEVICE_ACT_101
});