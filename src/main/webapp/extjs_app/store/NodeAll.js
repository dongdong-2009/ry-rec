/**
 * Created by danny on 16-12-20.
 */

Ext.define('app.store.NodeAll', {
    extend: 'Ext.data.Store',
    autoLoad: true,
    autoSync: true,
    model: 'app.model.Node',
    proxy: {
        type: 'direct',
        api: {
            read: extNode.list,
            create: extNode.create,
            update: extNode.update,
            destroy: extNode.delete
        }
    }
})