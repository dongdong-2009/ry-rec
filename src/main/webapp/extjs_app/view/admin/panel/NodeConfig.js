/**
 * Created by danny on 17-2-3.
 * Node 的参数配置
 * 生成一个json存放在Node表的opt字段
 */


Ext.define('app.view.admin.panel.NodeConfig', {
    extend: 'Ext.form.Panel',
    alias: 'widget.admin.panel.nodeconfig',
    itemId: 'admin_panel_nodeconfig',
    title: '节点参数配置',
    icon: 'icon/toolbar/node.png',
    hidden: true,


    bodyPadding: '5 5 0',
    //layout: 'form',
    //frame: true,
    fileUpload: true,
    items: [
        {
            xtype: 'numberfield',
            fieldLabel: '灵敏度：',
            name: 'sensitive',
            itemId: 'sensitive'
        },
        {
            xtype: 'numberfield',
            fieldLabel: '线性参数A',
            name: 'pA',
            itemId: 'pA'
        }, {
            xtype: 'numberfield',
            fieldLabel: '线性参数B',
            name: 'pB',
            itemId: 'pB'
        }],
    buttons: [{
        text: "更新",
        handler: function () {

            var form = this.up('form').getForm();

            debugger;
            var pa = this.ownerCt.ownerCt;
            var selection = pa.selection;
            var opt = {};

            selection.beginEdit();

            opt.sensitive = pa.down('#sensitive').getValue();
            opt.pA = pa.down('#pA').getValue();
            opt.pB = pa.down('#pB').getValue();

            selection.set('opt', JSON.stringify(opt))
            selection.endEdit();
            selection.commit();
        }
    }],

    /**
     * 根据
     * @param store
     * @param field
     */
    readConfig: function (selection, field) {
        this.show();
        var optStr = selection.get(field);
        this.selection = selection;
        //读取数据
        if (optStr.length) {
            var opt = JSON.parse(optStr);
            this.down('#sensitive').setValue(opt.sensitive);
            this.down('#pA').setValue(opt.pA);
            this.down('#pB').setValue(opt.pB);
        } else {
            this.down('#sensitive').setValue(null);
            this.down('#pA').setValue(null);
            this.down('#pB').setValue(null);
        }
    }

});