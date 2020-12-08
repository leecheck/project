<!-- root path -->
<#macro rootPath>${springMacroRequestContext.getContextPath()}</#macro>

<#global user = env.getCurrentUser()/>

<#global userInfo = env.getUserInfo()/>

<#global isAdmin = env.isAdmin()/>

<!-- add script -->
<#macro script url>
<script src="<@rootPath/>/${url}" type="text/javascript"></script>
</#macro>

<!-- add script CDN-->
<#macro scriptCDN url>
<script src="${url}" type="text/javascript"></script>
</#macro>

<!-- add style -->
<#macro style url>
<link href="<@rootPath/>/${url}" type="text/css" media="screen" rel="stylesheet"/>
</#macro>

<!-- add style CDN -->
<#macro styleCDN url>
<link href="${url}" type="text/css" media="screen" rel="stylesheet"/>
</#macro>

<!-- add media style -->
<#macro mstyle name media>
<link href="<@rootPath/>/${name}" type="text/css" media="${media}" rel="stylesheet"/>
</#macro>


<!-- base html -->
<#macro html title="" import="" css="" js="" theme="">
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width">
    <title>${title}</title>
    <link rel="shortcut icon" href="<@rootPath/>/images/ico.ico">
    <#if import??>
        <#list import?split(",") as lib>
            <#if lib?trim != "">
                <#switch lib>
                    <#case "base">
                        <@script url="js/api.js"></@script>
                        <@script url="js/lib.js"></@script>
                        <@script url="js/util.js"></@script>
                        <@script url="js/pre.js"></@script>
                        <@script url="js/store/store.js"></@script>
                        <#break />
                    <#case "vue">
                        <#--<@script url="js/vue/vue.min.js"></@script>--> <#--生产环境-->
                        <@script url="js/vue/vue-dev.js"></@script>
                        <#break />
                    <#case "iview">
                        <@script url="js/iview/iview.min.js"></@script>
                        <@style url="js/iview/iview.css"></@style>
                        <#break />
                    <#case "layout">
                        <@style url="css/layout.css"></@style>
                        <#break />
                    <#case "leaflet">
                        <@script url="js/leaflet/leaflet-src.js"></@script>
                        <@style url="js/leaflet/leaflet.css"></@style>
                        <@script url="js/leaflet/ChinaProvider.js"></@script>
                        <@script url="js/leaflet/esri-leaflet.js"></@script>
                        <#break />
                     <#case "echart">
                        <@script url="js/echart/echarts.js"></@script>
                        <#break />
                </#switch>
            </#if>
        </#list>
    </#if>
    <#if css??>
        <#list css?split(",") as csslib>
            <#if csslib?trim != "">
                <@style url = csslib ></@style>
            </#if>
        </#list>
    </#if>
    <#if js??>
        <#list js?split(",") as jslib>
            <#if jslib?trim != "">
                <@script url=jslib></@script>
            </#if>
        </#list>
    </#if>
</head>
<body style="height: 100%">
    <#nested />
</body>
</html>
</#macro>



