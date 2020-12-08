var api = {};

api.login = "/login/do";

api.sysuser_add = "/admin/sysUser/add";
api.sysuser_edit = "/admin/sysUser/edit";
api.sysuser_del = "/admin/sysUser/del";
api.sysuser_edit_pass = "/admin/sysUser/edit/pass";
api.sysuser_query = "/admin/sysUser/query";
api.sysUser_role_update = "/admin/sysUser/role/update";
api.sysuser_usernameDump="/admin/sysUser/usernameDump";
api.sysuser_orgid = "/admin/sysUser/orgid";

api.sysrole_query = "/admin/sysRole/query";
api.sysrole_edit = "/admin/sysRole/edit";
api.sysrole_del = "/admin/sysRole/del";
api.sysrole_add = "/admin/sysRole/add";
api.sysrole_query_uid = "/admin/sysRole/query/uid";
api.sysrole_menu_update = "/admin/sysRole/menu/update";
api.sysrole_roleCodeDump="/admin/sysRole/roleCodeDump";
api.sysrole_roleNameDump="/admin/sysRole/roleNameDump";


api.sysOrg_tree = "/admin/sysOrg/tree";
api.sysOrg_query = "/admin/sysOrg/query";
api.sysOrg_edit = "/admin/sysOrg/edit";
api.sysOrg_add = "/admin/sysOrg/add";
api.sysOrg_del = "/admin/sysOrg/del";
api.sysOrg_organCodeDump="/admin/sysOrg/organCodeDump";


api.sysDict_tree = "/admin/sysDict/tree";
api.sysDict_query = "/admin/sysDict/query";
api.sysDict_edit = "/admin/sysDict/edit";
api.sysDict_add = "/admin/sysDict/add";
api.sysDict_del = "/admin/sysDict/del";
api.sysDict_dictCodeDump = "/admin/sysDict/dictCodeDump";
api.sysDict_dictItem = "/admin/sysDict/getDict/api";

api.sysMaplayer_query = "/admin/sysMaplayer/query";
api.sysMaplayer_edit = "/admin/sysMaplayer/edit";
api.sysMaplayer_add = "/admin/sysMaplayer/add";
api.sysMaplayer_del = "/admin/sysMaplayer/del";

api.getLayerGroup = "/admin/sysMaplayer/get/layerGroup";
api.getMapConfig = "/admin/sysMaplayer/get/mapConfig";
api.getLayerType = "/admin/sysMaplayer/get/layerType";

//台站
api.tbstatinfo_query = "/work/tbStationinfo/query";
api.tbstatinfo_edit = "/work/tbStationinfo/edit";
api.tbstatinfo_add = "/work/tbStationinfo/add";
api.tbstatinfo_del = "/work/tbStationinfo/del";
api.tbstatinfo_edit_org = "/work/tbStationinfo/edit/org";
api.tbstatinfo_refresh = "/work/tbStationinfo/refresh";

//系统日志
api.tbsystemlog_query = "/work/tbSystemLog/query";
api.tbsystemlog_del = "/work/tbSystemLog/del";

//短信管理
api.sysSms_del = "/admin/sysSms/del";
api.sysSms_query = "/admin/sysSms/query";
api.sysSms_add = "/admin/sysSms/add";
api.sysSms_bind = "/admin/sysSms/bind/user";

//操作日志
api.syslog_query = "/admin/sysLog/query";
api.syslog_del = "/admin/sysLog/del";
api.syslog_logType = "/admin/sysLog/logType";
//任务
api.tbtask_query="/work/tbTaskModel/query_param";
api.tbtask_queryname="/work/tbTaskModel/queryStat";//台站
api.tbtask_add="/work/tbTaskModel/add";
api.tbtask_edit="/work/tbTaskModel/edit";
api.tbtask_del="/work/tbTaskModel/del";
api.tbtask_del_task="/work/tbTaskModel/del_task";
api.tbtask_log="/work/tbTaskModel/log";
api.tbtask_taskModels="/work/tbTaskModel/models";

api.task_replay_id = "/index/taskReplay";
//任务数据
api.task_data_trans="/work/tbSpectrumdata/query_task_data";
//运行日志
api.sysrunlog_query="/admin/sysRunLog/query";
api.sysrunlog_del="/admin/sysRunLog/del";
//异常信息
api.tbabnormal_query="/work/tbAbnormal/query";
api.tbabnormal_query_name="/work/tbAbnormal/queryStat";//台站
api.tbabnormal_del="/work/tbAbnormal/del";
//菜单
api.sysmenu_tree="/admin/sysMenu/tree";
api.sysmenu_query="/admin/sysMenu/query";
api.sysmenu_add="/admin/sysMenu/add";
api.sysmenu_edit="/admin/sysMenu/edit";
api.sysmenu_del="/admin/sysMenu/del";
api.sysmenu_childNameDump="/admin/sysMenu/childNameDump";
api.sysmenu_query_roleid="/admin/sysMenu/query/RoleId";
//区域
api.sysarea_tree="/admin/sysArea/tree";
api.sysarea_query="/admin/sysArea/query";
api.sysarea_add="/admin/sysArea/add";
api.sysarea_edit="/admin/sysArea/edit";
api.sysarea_del="/admin/sysArea/del";
api.sysarea_areaCodeDump="/admin/sysArea/areaCodeDump";
//文件
api.sysfile_add="/admin/sysFile/add";
api.sysfile_query="/admin/sysFile/query";
api.sysfile_del="/admin/sysFile/del";
api.sysfile_edit="/admin/sysFile/edit";
api.sysfile_download="/admin/sysFile/download";
//数据库备份
api.sysStore_query = "/admin/sysStore/query";
api.sysStore_add = "/admin/sysStore/add";
api.sysStore_connect = "/admin/sysStore/connect";
api.sysStore_del = "/admin/sysStore/del";
api.sysStore_backup = "/admin/sysStore/backup";
api.sysStore_edit = "/admin/sysStore/edit";
api.sysStore_cornEdit = "/admin/sysStore/corn/edit";

//电磁态势
api.eleSituation_upload = "/work/eleSituation/upload";
api.eleSituation_query = "/work/eleSituation/query";

//统计中心
api.statistic_data = "/work/statistic/data";
