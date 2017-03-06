/**
 * Created by danny on 17-1-24.
 *
 * GisView 的Plugin，用于一个Panel, layout: 'fit' 必须
 *
 * 参考：
 * http://openlayers.org/workshop/en/index.html
 * http://viglino.github.io/ol3-ext/
 * https://github.com/Viglino/ol3-ext
 * https://openlayersbook.github.io/
 * http://www.acuriousanimal.com/thebookofopenlayers3/
 * http://www.acuriousanimal.com/thebookofopenlayers3/chapter06_02_markers_overlays.html
 * https://github.com/anzhihun/OpenLayers3Primer
 *
 *
 *
 * 配置：
 * layerStore：字符串， GisLayer对应 app.store.GisLayer
 * editable: boolean 是否允许编辑
 * deviceGrid: 字符串，devicegrid的ID
 */

Ext.define('app.lib.GisViewPlugin', {
    alias: 'plugin.gis.view',                   //使用 ptypt:'gis.view' 方式建立插件
    extend: 'Ext.AbstractPlugin',

    mixins: {
        observable: 'Ext.util.Observable'       //具备事件的处理能力
    },

    /*******************************************************
     * Map 属性                                             *
     *******************************************************/

    extent: [-180, -90, 180, 90],

    projection: new ol.proj.Projection({
        code: 'EPSG:4326',
        units: 'm',
        extent: [-180.0000, -90.0000, 180.0000, 90.0000]
    }),

    /*******************************************************
     * 基本的Style                                          *
     *******************************************************/
    style: {
        /**
         *
         * @param icon               图标的代码
         * @param iconState               图标模式
         * @returns {string}        返回图标的路径
         */
        getIconPath: function (icon, iconState) {
            return "icon/device_icon/" + icon + "-" + iconState + ".gif";
        },

        // 区域填充的样式
        fill: new ol.style.Fill({
            color: 'rgba(255, 255, 255, 0.2)'
        }),

        // 线条填充样式
        stroke: new ol.style.Stroke({
            color: '#ffcc33',
            width: 2
        }),

        // 点填充样式
        point: new ol.style.Circle({
            radius: 7,
            fill: new ol.style.Fill({
                color: '#ffcc33'
            })
        }),

        // 样式的缓存
        cache: {}
    },

    // 是否在编辑
    editing: false,

    /**
     * 初始化函数
     * client 对应父控件，一般是一个panel
     * @param client
     */
    init: function (client) {

        client.layout = 'fit';          // 强制使用fit
        this.callParent(arguments);

        var me = this;

        client.gis = me;
        ry.gis = me;


        /*******************************************************
         * 样式的集合Style                                          *
         *******************************************************/

        // 缺省的样式集
        me.style.default = new ol.style.Style({
            fill: me.style.fill,
            stroke: me.style.stroke,
            image: me.style.point
        });

        // 选中的样式
        me.style.selected = new ol.style.Style({
            fill: me.style.fill,
            stroke: me.style.stroke,
            image: new ol.style.Icon({
                src: '/icon/mark/light.gif'
            })
        });

        /**
         * 动态的图标在Overlay中显示，这里的样式只是在绘图的时候表示设备的静态状态
         * @param feature
         * @param resolution
         * @returns {*}
         */

        me.style.styleFun = function (feature, resolution) {

            // 缓冲样式的 key，用于 gis.style.chace 的索引
            var styleKey = feature.getProperties().icon + '-' + 11;

            // 是否有缓冲
            var cachedStyle = me.style.cache[styleKey];

            if (!cachedStyle) {
                // 没有缓冲，新建
                var cachedStyle = new ol.style.Style({
                    fill: me.style.fill,
                    stroke: me.style.stroke,
                });

                // 建立点的样式
                var icon = new ol.style.Icon({
                    src: me.style.getIconPath(feature.getProperties().icon, 11)
                });

                // 点样式加入到样式
                cachedStyle.setImage(icon);

                me.style.cache[styleKey] = cachedStyle;
            }

            return cachedStyle;
        };

        /**
         * 改变一组 Feature 的样式
         * @param features   features 集合
         * @param type       样式的风格，整形
         */

        me.style.changeStyle = function (features, type) {

            // 缓冲样式的 key，用于 gis.style.chace 的索引

            for (var i in features) {
                var feature = features[i];
                var styleKey = feature.getProperties().icon + '-' + type;
                var cachedStyle = me.style.cache[styleKey];


                if (!cachedStyle) {
                    // 没有缓冲，新建
                    var cachedStyle = new ol.style.Style({
                        fill: me.style.fill,
                        stroke: me.style.stroke,
                    });

                    // 建立点的样式
                    var icon = new ol.style.Icon({
                        src: me.style.getIconPath(feature.getProperties().icon, type)
                    });

                    // 点样式加入到样式
                    cachedStyle.setImage(icon);

                    me.style.cache[styleKey] = cachedStyle;
                }

                feature.setStyle(cachedStyle);
            }
        };

        /**
         * 改变当前层中 Device 的 Style
         * @param device  device 的ID
         * @param icon   状态：整形
         */

        me.setDeviceStyle = function (device, icon) {
            var features = me.getFeaturesByDeviceOfLayer(gis.getActiveVectorLayer(), device);
            me.style.changeStyle(features, icon);
        };


        /*******************************************************
         * Map 对象                                             *
         *******************************************************/
        me.map = new ol.Map({
            renderer: 'canvas',
            view: new ol.View({
                projection: me.projection,
                center: ol.extent.getCenter(me.extent),
                extent: me.extent,
                zoom: 4,
                maxZoom: 4
            })
        });

        // map.owner 可以访问到map插件
        me.map.owner = me;

        // 添加图层切换
        me.map.addControl(new ol.control.LayerSwitcherImage());

        /**
         * 重新设置mapView
         */
        me.maxExtent = function () {
            //me.map.getView().fit(me.extent, me.map.getSize());   //-- v3.0
            me.map.getView().fit(me.extent);   //-- v4.0
        };

        /**
         * 缩放到Features对应的区域
         * @param features
         */

        me.zoomToFeatures = function (features) {
            var a = [];
            for (var i in features) {
                a.push(features[i].getGeometry());
            }
            var gc = new ol.geom.GeometryCollection(a);
            me.map.getView().fit(gc.getExtent(), me.map.getSize());
        };

        /*******************************************************
         * 图层管理                                             *
         *******************************************************/

        me.layers = new Ext.util.HashMap();

        // 为 layer 加载Feature
        me.onLayerFeaturesLoad = function (response) {
            var me = this;
            var reader = new ol.format.GeoJSON();
            var features = reader.readFeatures(response.responseText);
            // 得到 Vector layer
            var layer = me.layers.get(response.request.options.params.layer).getLayers().getArray()[1];
            layer.getSource().addFeatures(features);
        };

        /**
         * 删除所有的图层
         */
        me.clearLayers = function () {
            me.map.getLayers().clear();
            me.layers.clear();
        };

        /**
         * 删除一个图层
         * @param layerId
         */
        me.delLayer = function (layerId) {
            var layer = me.layers.get(layerId);
            me.map.removeLayer(layer);
            me.layers.removeAtKey(layerId);
        };

        /**
         * 得到当前激活的图层组的图层组
         * @returns {*} 当前激活的图层对象
         */
        me.getActiveLayerGroup = function () {
            var r = null;
            me.layers.each(function (key, value, length) {
                if (value.getVisible()) {
                    r = value;
                }
            }, this);
            return r;
        };

        /**
         * 得到当前激活图层的设备图层 Vecto 图层
         * @returns {*}
         */
        me.getActiveVectorLayer = function () {
            var layer = me.getActiveLayerGroup();
            if (layer) {
                return layer.getLayers().getArray()[1];
            } else {
                return null;
            }
        };

        /**
         * 添加图层组
         * 1、建立一个图层组：底图和设备图层
         * 2、图层得到设备信息时，更新设备状态
         * 3、图层激活的时候，更新设备状态
         * @param layerId
         * @param layerName
         * @param layerFile
         */
        me.addLayer = function (layerId, layerName, layerFile) {

            // 请求layer的Features
            Ext.Ajax.request({
                url: 'srv/gis/features',
                params: {
                    layer: layerId
                },
                success: me.onLayerFeaturesLoad,
                scope: me
            });

            // 图层：位图
            var imageLayer = new ol.layer.Image({
                source: new ol.source.ImageStatic({
                    url: '/upload/gis/layer/' + layerFile,
                    projection: me.projection,
                    imageExtent: me.extent
                })
            });

            // 图层：矢量图
            var vectorLayer = new ol.layer.Vector({
                source: new ol.source.Vector({}),
                style: me.style.styleFun
            });

            // 在 click 事件中，只能有layer传过去，这里给layer添加一个属性
            vectorLayer.owner = me;

            vectorLayer.getSource().getExtent();

            var newLayer = new ol.layer.Group({
                baseLayer: true,
                name: layerName,
                preview: '/upload/gis/layer/_' + layerFile,
                visible: false,
                layers: [
                    imageLayer,
                    vectorLayer
                ]
            });

            /**
             * 当图层被激活的时候，更新设备状态
             */
            newLayer.on('change:visible', function (event) {
                // 状态改变的时候触发，多次触发

                //event.target;       //layer group
                //event.oldValue;     // 以前的状态 true false
                // 当前激活的层
                if (!event.oldValue) {
                    // 获取设备的状态
                    gisDevice.getDevicesState(function (data, event, rst) {

                        this.overlay.devicesState = data;
                        this.overlay.updateDevice(this);
                        this.maxExtent();

                        // 是否需要有高亮的Device
                        if (me.deviceNeedToBeHighlight) {
                            me.highlightDevice(me.deviceNeedToBeHighlight);
                        }

                    }, this)
                }
            }, me);

            me.layers.add(layerId, newLayer);

            newLayer.id = layerId;
            me.map.addLayer(newLayer);
        };


        /**
         * 通过LayerId来查询layer
         * @param layerId
         */
        me.activeLayerById = function (layerId) {

            me.layers.each(function (key, value, length) {
                value.setVisible(false);
            }, me);

            me.layers.get(layerId).setVisible(true);
        };


        /*******************************************************
         * 使用 overlay 来对设备的状态进行改变
         * Feature 管理
         *
         *******************************************************/

        me.overlay = {

            // 存放当前层的Overlay
            featureStateOverlays: new Ext.util.HashMap(),

            // 所有设备的状态
            devicesState: null,


            /**
             * 通过Feature生成一个overlay
             * @param feature
             * @param iconState
             * @param me
             * @returns {ol.Overlay}
             *
             * todo: 为了局部更新，需要把生成的Overlay加入到一个hash中进行管理
             */
            createFeatureOverlay: function (feature, iconState, me) {
                var elem = document.createElement('div');
                elem.style.width = '16px';
                elem.style.height = '16px';
                elem.style.backgroundImage = "url(" + me.style.getIconPath(feature.getProperties().icon, iconState) + ")";

                var overlay = new ol.Overlay({
                    id: feature.getId(),
                    element: elem,
                    stopEvent: false,
                    position: feature.getGeometry().getCoordinates(),
                    positioning: 'center-center',
                });
                return overlay;
            },

            /**
             * 更新当前层的Device状态
             * @param me
             *
             * map.overlays_ 是 map 自己的一个对象
             */
            updateDevice: function (me) {
                me.map.overlays_.clear();
                me.overlay.featureStateOverlays.clear();
                var features = me.getActiveVectorLayer().getSource().getFeatures();
                for (var i in features) {
                    var feature = features[i];
                    var icon = this.devicesState[feature.getProperties().deviceId].runtime.iconState;
                    var overlay = me.overlay.createFeatureOverlay(feature, icon, me);
                    me.overlay.featureStateOverlays.add(feature.getId(), overlay);
                    me.map.addOverlay(overlay);
                }

            }
        };

        /**
         * 更新一个设备的状态，包括图标
         * @param state
         */
        me.updateDeviceState = function (state) {
            if (!me.overlay.devicesState) {
                return;
            }

            // 更新数据和图标
            me.overlay.devicesState[state.device.id] = state;
            me.deviceSetIcon(state.device.id, state.runtime.iconState);

            // 更新控制面板
            var panel = ry.devices['device_' + state.device.type].controlPanel;
            if (panel != null) {
                panel.updateState(state);
            }
        };
        /**
         * 设置一个Device的Icon，只在当前层有效
         * @param deviceId
         * @param iconState
         */
        me.deviceSetIcon = function (deviceId, iconState) {

            // 找到device对应的features
            var features = me.getFeaturesByDeviceOnActiveLayer(deviceId);

            if (!features) {
                return;
            }

            for (var i = 0; i < features.length; i++) {
                var feature = features[i];
                var overlay = me.overlay.featureStateOverlays.get(feature.getId());
                overlay.getElement().style.backgroundImage = "url(" + me.style.getIconPath(feature.getProperties().icon, iconState) + ")";
            }

        };

        /*******************************************************
         * Features 操作接口                                     *
         *******************************************************/

        /**
         * 得到一个Layer中对应的Device的Feature列表
         * @param id
         * @param layer
         */
        me.getFeaturesByDeviceOfLayer = function (layer, id) {
            if (!layer) {
                return;
            }
            var out = [];
            var features = layer.getSource().getFeatures();
            for (var index in features) {
                if (features[index].getProperties().deviceId == id) {
                    out.push(features[index]);
                }
            }
            return out;
        };

        /**
         * 得到当前激活图层上Device的Feature列表
         * @param id
         * @returns {Array}
         */
        me.getFeaturesByDeviceOnActiveLayer = function (id) {
            return me.getFeaturesByDeviceOfLayer(me.getActiveVectorLayer(), id);
        };


        /**
         * 高亮Feature，通过Overlayer
         * @param features          features的 数组
         * @param zoom              是否放大到该区域
         */

        me.highlightOverlays = [];

        // 需要被高亮的device，为0是没有
        me.deviceNeedToBeHighlight = 0;

        me.highlightFeatures = function (features, zoom) {
            // 首先清理以前的高亮
            for (var i in me.highlightOverlays) {
                me.map.removeOverlay(me.highlightOverlays[i]);
            }
            me.highlightOverlays.length = 0;

            // 然后进行高亮
            for (var i in features) {
                var position = features[i].getGeometry().getCoordinates();
                var elem = document.createElement('div');

                var overlay = new ol.Overlay({
                    element: elem,
                    position: position,
                    positioning: 'center-center'
                });

                me.highlightOverlays.push(overlay);
                me.map.addOverlay(overlay);
                elem.setAttribute('class', 'circle');
            }

            if (zoom) {
                me.zoomToFeatures(features);
            }
        };

        /**
         * 高亮 Device
         * @param device   DeviceID
         * @param layer    layerID
         */

        me.highlightDevice = function (device) {
            // 首先在当前层寻找Feature
            var features = me.getFeaturesByDeviceOnActiveLayer(device);

            if (!features) {
                return;
            }
            if (features.length) {
                // 当前层中没有
                me.highlightFeatures(features, true);
                me.deviceNeedToBeHighlight = 0;
                return;
            }

            // 到其他的层中寻找
            var vectorLayerId = 0;          // 找到的层
            var keySet = me.layers.getKeys();

            for (var i in keySet) {
                var index = keySet[i];
                var layerGroup = me.layers.get(index);
                if (layerGroup.getVisible()) {
                    continue;
                }

                var vectorLayer = layerGroup.getLayers().getArray()[1];
                features = me.getFeaturesByDeviceOfLayer(vectorLayer, device);
                if (features.length) {
                    vectorLayerId = index;
                    break;
                }
            }

            if (vectorLayerId) {
                me.deviceNeedToBeHighlight = device;
                me.activeLayerById(vectorLayerId);
            }
        };

        /**
         * 删除一个Id的Feature
         */
        me.deleteFeature = function (featureId) {
            me.layers.each(function (key, value, length) {
                // 得到Vector layer
                var layer = value.getLayers().getArray()[1];
                // 得到 Features 数组
                var featureSource = layer.getSource();
                // 得到 Feature
                var feature = featureSource.getFeatureById(featureId);

                if (feature) {
                    featureSource.removeFeature(feature);
                }
            }, me);


        };


        /*******************************************************
         * Map 编辑工具                                         *
         *******************************************************/
        me.interaction = {};
        if (me.editable) {
            me.interaction = {

                // 判断是否可以编辑的函数
                drawConditionFun: function (event) {
                    var deviceGrid = Ext.getCmp('admin.device.grid');
                    if (!deviceGrid.getSelectionModel().selected.length) {
                        // 没有选中的Device，不能添加
                        return false;
                    } else {
                        return !me.editing;
                    }
                },

                // 作图完成后的事件
                onDrawEnd: function (event) {
                    var deviceGrid = Ext.getCmp('admin.device.grid');
                    var deviceId = deviceGrid.getSelectionModel().selected.get(0).get('id');
                    var icon = deviceGrid.getSelectionModel().selected.get(0).get('icon');
                    var layer = me.getActiveLayerGroup().id;

                    // 设置Feature 的属性
                    event.feature.setProperties(
                        {
                            'deviceId': deviceId,
                            'icon': icon,
                            'layer': layer
                        });

                    var layerSource = me.getActiveVectorLayer().getSource();
                    layerSource.addFeature(event.feature);

                    //一个新的对象被添加
                    var parser = new ol.format.GeoJSON();
                    var geoJson = parser.writeFeature(event.feature);

                    //保存当前作图的状态
                    me.editing = true;
                    me.lastFeature = event.feature;

                    // 保存，回调
                    extGis.saveFeature(geoJson, function (result, e) {
                        this.editing = false;
                        this.lastFeature.setId(result.id);
                    }, me);
                },

                // 编辑完成后的事件
                onModifyEnd: function (event) {
                    var feature = event.target.dragSegments_[0][0].feature;

                    var parser = new ol.format.GeoJSON();
                    var geoJson = parser.writeFeature(feature);

                    //保存当前作图的状态
                    //this.editing = true;
                    //this.lastFeature = feature;

                    // 保存，回调
                    extGis.saveFeature(geoJson, function (result, e) {
                        //this.editing = false;
                    }, me);
                }
            };

            // 画点工具
            me.interaction.drawPoint = new ol.interaction.Draw({
                type: "Point",
                condition: me.interaction.drawConditionFun
            });
            me.interaction.drawPoint.on('drawend', me.interaction.onDrawEnd, me);

            // 画线工具
            me.interaction.drawLine = new ol.interaction.Draw({
                type: "LineString",
                condition: me.interaction.drawConditionFun
            });
            me.interaction.drawLine.on('drawend', me.interaction.onDrawEnd, me);

            // 画面工具
            me.interaction.drawPolygon = new ol.interaction.Draw({
                type: "Polygon",
                condition: me.interaction.drawConditionFun
            });
            me.interaction.drawPolygon.on('drawend', me.interaction.onDrawEnd, me);

            // 修改工具
            me.interaction.modify = new ol.interaction.Modify({
                features: new ol.Collection(),
                //condition: me.interaction.drawConditionFun
            });
            me.interaction.modify.on('modifyend', me.interaction.onModifyEnd, me);

            // **********************编辑工具条***********************
            me.editBar = new ol.control.Bar();
            me.map.addControl(me.editBar);
            me.editBar.barItems = new ol.control.Bar({
                toggleOne: true, group: true
            });
            me.editBar.addControl(me.editBar.barItems);


            // 添加画点
            me.editBar.barItems.addControl(new ol.control.Toggle(
                {
                    html: "<img src= 'icon/24/Point.png' />",
                    className: "point",
                    title: '点',
                    interaction: me.interaction.drawPoint,
                    onToggle: function (active) {
                        me.setEditing();
                    }
                })
            );

            // 添加画线
            me.editBar.barItems.addControl(new ol.control.Toggle(
                {
                    html: "<img src= 'icon/24/Line.png' />",
                    className: "line",
                    title: '线',
                    interaction: me.interaction.drawLine,
                    onToggle: function (active) {
                        me.setEditing();
                    }
                })
            );

            // 添加画面
            me.editBar.barItems.addControl(new ol.control.Toggle(
                {
                    html: "<img src= 'icon/24/Polygon.png' />",
                    className: "polygon",
                    title: '面',
                    interaction: me.interaction.drawPolygon,
                    onToggle: function (active) {
                        me.setEditing();
                    }
                })
            );

            // 添加修改
            me.editBar.barItems.addControl(new ol.control.Toggle(
                {
                    html: "<img src= 'icon/24/Move.png' />",
                    className: "modify",
                    title: '移动',
                    interaction: me.interaction.modify,
                    onToggle: function (active) {
                        me.setEditing();
                        // 把当前激活图层的features添加道modify控件当中
                        me.interaction.modify.features_.clear();
                        var features = me.getActiveVectorLayer().getSource().getFeatures();
                        for (i in features) {
                            var feature = features[i];
                            me.interaction.modify.addFeature_(feature);
                        }
                    }
                })
            );

            // 判断是否Edit工具使能
            me.setEditing = function () {

                var active = false;
                for (i in this.editBar.barItems.controls_) {
                    var control = this.editBar.barItems.controls_[i];
                    if (control.getActive()) {
                        active = true;
                        break;
                    }
                }

                if (active) {
                    this.map.overlays_.clear();
                    this.interaction.hoverSelect.setActive(false);
                } else {
                    this.overlay.updateDevice(this);
                    this.interaction.hoverSelect.setActive(true);
                }
            };
        }


        // ***************************非编辑工具***************************

        // Hover 控件
        me.interaction.hoverSelect = new ol.interaction.Select({
            condition: ol.events.condition.pointerMove,
            multi: false,
        });

        //弹出的Overlay
        me.interaction.popup = new ol.Overlay.Popup({
            popupClass: "default", //"tooltips", "warning" "black" "default", "tips", "shadow",
            positioning: 'auto',
            autoPan: true,
            autoPanAnimation: {duration: 250}
        });

        me.interaction.hoverSelect.on('select', function (event) {
            if (event.selected.length) {

                // 首先清理以前加入的Overlay
                this.map.removeOverlay(this.interaction.popup);

                var fProperties = event.selected[0].getProperties();

                if (!ry.deviceControlPanel.hasPanel(fProperties.type)) {
                    return;
                }

                // 添加Overlay
                this.map.addOverlay(this.interaction.popup);

                this.interaction.popup.show(fProperties.geometry.getCoordinates());

                if (!ry.deviceControlPanel.rendered) {
                    ry.deviceControlPanel.render(this.interaction.popup.content);
                    ry.deviceControlPanel.getEl().setStyle('z-index', '80000');
                }

                ry.deviceControlPanel.showPanel(fProperties.type, this.overlay.devicesState[fProperties.deviceId]);

            } else {
                // 没有选中不能hide，否则不能使用面板了
            }
        }, me);

        me.map.addInteraction(me.interaction.hoverSelect);


        // Click 选择工具
        me.interaction.clickSelect = new ol.interaction.Select({
            condition: ol.events.condition.click,
            multi: false,
        });

        //
        me.interaction.clickSelect.on('select', function (event) {
            me.interaction.popup.hide();
        }, me);

        me.map.addInteraction(me.interaction.clickSelect);

        /*******************************************************
         * Client 的事件                                        *
         *******************************************************/
        client.on('resize', function (client) {
            this.map.updateSize();
        }, me);

        client.on('afterLayout', function (client) {
            this.map.setTarget(client.body.dom);
            // 窗口改变大小后，需要重新缩放图层
            this.maxExtent();
        }, me);

        // 当Layer Store 刷新时触发
        me.onLayerFresh = function (store, opt) {

            //首删除所有的图层
            this.clearLayers();

            //添加相应的图层
            for (var i = 0; i < store.data.getCount(); i++) {
                var item = store.data.getAt(i).data;
                me.addLayer(item.id, item.name, item.file);
            }

            if (this.layers.getValues().length) {
                //把第一个层作为显示层
                this.layers.getValues()[0].setVisible(true);
            }
        };

        me.store = Ext.StoreMgr.get(me.layerStore);
        me.store.on('refresh', me.onLayerFresh, me);

        client.refresh = function () {
            me.store.load();
        }
    }
});



