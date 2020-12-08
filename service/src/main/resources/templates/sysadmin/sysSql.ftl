<@l.manage tab="sysSQL" title="数据库监控" import="base,vue,iview,layout" js="" >
<div id="App" class="reletiveContainer list-panel-container" v-cloak>
    <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
        <div class="reletiveContainer" id="druidFrame">

        </div>
    </div>
</div>

<style type="text/css">

</style>

<script type="text/javascript">
    var layerApp = {};
    var func = {};
    var appData = {

    };

    window.onload = function () {
        App = new Vue({
            el: '#App',
            data: appData,
            mounted: function () {
                this.$nextTick(function () {
                    document.getElementById("druidFrame").innerHTML = '<object type="text/html" data="<@c.rootPath/>/druid" width="100%" height="100%"></object>';
                })
            },
            methods: {

            }
        });
    };
</script>
</@l.manage>
