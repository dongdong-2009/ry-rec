/**
 * Created by danny on 16-12-19.
 *
 * Channel 和 Node 的管理
 */

Ext.define('app.view.admin.frame.ChannelNode', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.admin.frame.channelnode',
    layout: 'border',
    icon: 'icon/toolbar/control.png',
    title: '通道和节点',
    itemId: 'gridChannelNode',
    items: [
        /**
         * 主表设置
         */
        {
            xtype: 'admin.panel.channel',
            region: 'center',
            margins: '0 0 0 0',
        },

        /**
         * 子表设置
         */
        {
            xtype: 'admin.panel.node',
            region: 'south',
            height: 500,
            margins: '0 0 5 0',
            split: true,
        }
    ],


    /**
     * 初始化
     */
    initComponent: function () {

        var me = this;

        me.callParent(arguments);

        //更新成功后
        this.down('#adminNodeGridForChannel').store.on('update', function (store, record, operation, eOpts) {
            var nodeGrid = Ext.ComponentQuery.query('#admin_panel_NodeForDevice')[0];
            nodeGrid.store.load();
        })

        // 当数据改变成功后的回调
        Ext.direct.Manager.on('event', function (event, provider, eOpts) {
            // 当 Device 删除后，应该刷新 Node
            if (event.action == 'extDevice' && event.method == 'delete') {
            }

        });

        this.on('show', function (from, eOpts) {
            var node = Ext.ComponentQuery.query('#admin_panel_NodeForDevice')[0];
            var gis = Ext.ComponentQuery.query('#admin_panel_gis')[0];
            node.show();
            gis.hide();
        })
    }
});