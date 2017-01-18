/**
 * Created by danny on 16-12-22.
 */

Ext.define('app.model.Option', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'id'},
        {name: 'cat', type: 'int'},
        {name: 'name'},
        {name: 'value'}
    ],

    validations: [{
        field: 'name',
        type: 'length',
        min: 2
    }]

});
