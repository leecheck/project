<#macro simple title="" tab="" import="" theme="" css="" js="">
    <@c.html title="${title!}" import="${import}" css="${css}" js="${js}" theme="default">
        <#nested />
    </@c.html>
</#macro>

<#macro manage title="" tab="" import="" theme="" css="" js="">
    <@c.html title="${title!}" import="${import}" css="${css}" js="${js}" theme="default">
        <div class="manage_page absoluteCotainer" style="">
            <div class="reletiveContainer">
                <nav>
                    <div class="nav-left">
                        <img src="<@c.rootPath/>/images/title.png">
                    </div>
                    <div class="nav-right">
                        <p>欢迎您,<span></span></p>
                    </div>
                </nav>
                <div class="manage_left absoluteArea" style="top: 70px;left:0;width: 200px;bottom: 0">
                    <div class="reletiveContainer">
                        <div class="manage_menu absoluteArea"
                             style="left:0;top: 0;right: 0;bottom: 0;background-color: #0b2e7c;overflow-y: auto">
                            <div class="meun-title">账号管理</div>
                            <div <#if tab == "sysUser">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysUser/list">
                                    <i size="20" class="ivu-icon ivu-icon-ios-contact"></i>用户管理</a></div>
                            <div <#if tab == "sysRole">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysRole/list">
                                    <i size="20" class="ivu-icon ivu-icon-ios-people"></i>角色管理</a></div>
                            <div <#if tab == "sysMenu">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> ><a href="<@c.rootPath/>/admin/sysMenu/list">
                                <i size="20" class="ivu-icon ivu-icon-md-menu"></i>菜单管理</a></div>
                            <div <#if tab == "sysOrg">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysOrg/list">
                                    <i size="20" class="ivu-icon ivu-icon-ios-briefcase"></i>单位管理</a></div>
                            <div <#if tab == "sysArea">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysArea/list">
                                    <i size="20" class="ivu-icon ivu-icon-md-globe"></i>区域管理</a></div>
                            <div <#if tab == "sysLog">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysLog/list">
                                    <i size="20" class="ivu-icon ivu-icon-ios-hand"></i>操作日志</a></div>
                            <div <#if tab == "sysRunLog">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysRunLog/list">
                                    <i size="20" class="ivu-icon ivu-icon-md-book"></i>运行日志</a></div>
                            <div <#if tab == "sysFile">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysFile/list">
                                    <i size="20" class="ivu-icon ivu-icon-ios-folder"></i>文件管理</a></div>
                            <div class="meun-title">系统配置</div>
                            <div <#if tab == "sysSms">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysSms/list">
                                    <i size="20" class="ivu-icon ivu-icon-ios-cube"></i>短信管理</a></div>
                            <div <#if tab == "sysSQL">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysSql/list">
                                    <i size="20" class="ivu-icon ivu-icon-ios-cube"></i>数据库监控</a></div>
                            <div <#if tab == "sysStoreBackup">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysStore/list/">
                                    <i size="20" class="ivu-icon ivu-icon-ios-recording"></i>数据库备份</a></div>
                            <div <#if tab == "sysDict">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysDict/list">
                                    <i size="20" class="ivu-icon ivu-icon-md-bookmarks"></i>字典管理</a></div>
                            <div <#if tab == "sysMaplayer">class="manage-meun-item manage-meun-item-active" <#else> class="manage-meun-item"</#if> >
                                <a href="<@c.rootPath/>/admin/sysMaplayer/list">
                                    <i size="20" class="ivu-icon ivu-icon-md-map"></i>图层管理</a></div>
                            <div class="meun-title">更多操作</div>
                            <div class="manage-meun-item"><a href="<@c.rootPath/>/index/workPage">返回首页</a></div>
                            <div class="manage-meun-item"><a href="<@c.rootPath/>/logout">退出登录</a></div>
                        </div>
                    </div>
                </div>
                <div class="manage_right absoluteArea" style="top: 70px;left:200px;right: 0;bottom: 0;padding: 0">
                    <#nested />
                </div>
            </div>
        </div>
    </@c.html>
</#macro>


