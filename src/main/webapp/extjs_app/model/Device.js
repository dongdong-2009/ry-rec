/**
 * Created by danny on 16-12-21.
 *
 * 设备列表
 */

Ext.define('app.model.Device', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'id'},
        {name: 'no'},
        {name: 'name'},
        {name: 'type'},
        {name: 'icon'},
        {
            name: 'iec61850',
            type: 'boolean',
            convert: function (v) {
                if (typeof v === 'boolean') {
                    return v ? 1 : 0;
                } else {
                    return parseInt(v, 10);
                }
            },
            defaultValue: 0
        },
        {name: 'opt'}
    ],

    validations: [{
        field: 'name',
        type: 'length',
        min: 2
    }]

});
