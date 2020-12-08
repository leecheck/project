//前端请求的接口等
var api = {};
var ip='120.224.102.163';//后端请求的ip 120.224.102.163
var port=':2337';//后端请求的端口 2400
var base_rest = 'http://'+ip+port+'/odp/rest';
window.api = api;
api.cameraServer = "http://localhost:8082/camera/"
api.ip=ip;//后端请求的ip
api.demo = "http://118.190.202.102:6210/wsp/rest/manage/login";
api.collectdemo = "http://192.168.1.130:2400/trsyb/rest/log/query";
api.ip = "http://192.168.1.130:2400/trsyb"
api.login = base_rest + "/sys/login";
api.authDemo = base_rest + "/sysdict/getDict"; 


// 浮标管理
api.buoy_page = base_rest + '/tBuoy/page';
api.buoy_add = base_rest + '/tBuoy/add';
api.buoy_edit = base_rest + '/tBuoy/edit';
api.buoy_del = base_rest + '/tBuoy/del';

// 监测点管理
api.monitoringPoint_page = base_rest + '/tPoint/page';
api.monitoringPoint_add = base_rest + '/tPoint/add';
api.monitoringPoint_edit = base_rest + '/tPoint/edit';
api.monitoringPoint_del = base_rest + '/tPoint/del';

// 试验船管理
api.vessel_page = base_rest + '/tVessel/page';
api.vessel_add = base_rest + '/tVessel/add';
api.vessel_edit = base_rest + '/tVessel/edit';
api.vessel_del = base_rest + '/tVessel/del';

//数据导入
api.excel = base_rest + '/util/excel';

//统计分析
api.buoy_list = base_rest + "/ssfbData/analysis-list";     // 浮标数据(不分页)
api.station_list = base_rest + "/sshyzXssj/analysis-list"; // 海洋站数据(不分页)

api.mapConf = base_rest + "mapconf"; //todo

api.oneMap = base_rest + '/tBuoy/oneMap';
api.intervalBuoys = base_rest + '/tBuoy/intervalBuoys';

api.checkTask = base_rest + '/util/checkTask';