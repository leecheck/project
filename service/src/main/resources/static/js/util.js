(function (name, definition) {
    var hasDefine = typeof define == "function",
        hasModuleExports = typeof module != "undefined" && module.exports;

    //当存在define、module函数时候，包装为模块
    if (hasDefine) {
        define(name, definition);
    } else if (hasModuleExports) {
        module.exports = definition();
    } else {
        this[name] = definition();
    }
})("u", function () {
    var root = window,
        _u = {}; //仿照jquery生成一个命名空间_$
    root._u = _u; //把命名空间挂载到window下，以便外部访问

    _u.rootPath = "/sow";

    _u.wsPath = "ws://localhost:2333/emv/StationWs";
    //_u.wsPath = "ws://183.245.4.249:2333/emv/StationWs";

    _u.notice = function (instence, info) {
        instence.$Notice.info({
            title: "提示",
            desc: info,
            duration: 3
        });
    };

    _u.success = function (instence, info) {
        instence.$Notice.success({
            title: "成功",
            desc: info,
            duration: 3
        });
    };

    _u.warning = function (instence, info) {
        instence.$Notice.warning({
            title: "提醒",
            desc: info,
            duration: 3
        });
    };

    _u.error = function (instence, info) {
        instence.$Notice.error({
            title: "错误",
            desc: info,
            duration: 5
        });
    };
    _u.holdMsg = function (instence, info) {
        instence.$Notice.info({
            title: "提示",
            desc: info,
            duration: 0
        });
    };

    _u.validate_isJSON = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持默认值[]"));
        }
        try {
            var j = JSON.parse(value);
            return callback();
        } catch (e) {
            return callback(new Error("输入值转换JSON失败"));
        }
    };
    //经度
    _u.validate_Longitude = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持不为空"));
        }
        try {
            var reg = new RegExp(/^[\-\+]?(0?\d{1,2}\.\d{1,5}|1[0-7]?\d{1}\.\d{1,5}|180\.0{1,5})$/);//0-180
            var j = value;
            if (!reg.test(j)) {
                return callback("不是数字");
            }
            return callback();
        } catch (e) {
            return callback(new Error("输入错误"));
        }
    };
    //纬度
    _u.validate_Latitude = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持不为空"));
        }
        try {
            var reg = new RegExp(/^[\-\+]?([0-8]?\d{1}\.\d{1,5}|90\.0{1,5})$/);//0-90
            if (!reg.test(value)) {
                return callback("不是数字");
            }
            return callback();
        } catch (e) {
            return callback(new Error("输入错误"));
        }
    };
    //ip
    _u.validate_Ip = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持不为空"));
        }
        try {
            var reg = new RegExp(/^$1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9]/);//1-255
            if (!reg.test(value)) {
                return callback("不是正确ip");
            }
            return callback();
        } catch (e) {
            return callback(new Error("输入错误"));
        }
    };
    //密码
    _u.validate_Pwd = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持不为空"));
        }
        try {
            var reg = new RegExp(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/);//数字字母组合，长度6
            if (!reg.test(value)) {
                return callback("不是正确密码");
            }
            return callback();
        } catch (e) {
            return callback(new Error("输入错误"));
        }
    };
    //邮箱
    _u.validate_email = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持不为空"));
        }
        try {
            var reg = new RegExp(/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}/);
            var j = value;
            if (!reg.test(j)) {
                return callback(new Error("不是正确邮箱"));
            }
            return callback();
        } catch (e) {
            return callback(new Error("输入错误"));
        }
    };
    //手机号
    _u.validate_phone = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持不为空"));
        }
        try {
            var reg = /^0?1[3|4|5|6|7|8][0-9]\d{8}$/;
            var j = value;
            if (!reg.test(j)) {
                return callback(new Error("不是11位数字"));
            }
            return callback();
        } catch (e) {
            return callback(new Error("输入错误"));
        }
    };
    //电话号
    _u.validate_telphone = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持不为空"));
        }
        try {
            var mobile = /^1[0-9]{10}$/, phone = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
            if (mobile.test(value) == false && phone.test(value) == false) {
                return callback(new Error("电话格式不正确"));
            }
            return callback();
        } catch (e) {
            return callback(new Error("输入错误"));
        }
    };
    //邮编
    _u.validate_postcode = function (rule, value, callback) {
        if (!value || value === "") {
            return callback();
        }
        try {
            var reg = /^[1-9]\d{5}$/;
            if (reg.test(value) == false) {
                return callback(new Error("邮编格式不正确"));
            }
            return callback();
        } catch (e) {
            return callback(new Error("输入错误"));
        }
    };
    //身份证
    _u.validate_idcard = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持不为空"));
        }
        try {
            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            var j = value;
            if (!reg.test(j)) {
                return callback(new Error("身份证输入不合法"));
            }
            return callback();
        } catch (e) {
            return callback(new Error("输入错误"));
        }
    };
    //字符串
    _u.validate_char = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error());
        }
        try {
            var reg = new RegExp(/^[a-zA-Z]+$/);
            var j = value;
            if (!reg.test(j)) {
                return callback(new Error());
            }
            return callback();
        } catch (e) {
            return callback(new Error());
        }
    };

    //输入字符不能为空
    _u.validate_notNull = function (rule, value, callback) {
        if (value && value.trim() == '') {
            return callback(new Error());
        } else {
            return callback();
        }
    };

    //英文 数字 下划线
    _u.validate_char_Num = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error());
        }
        try {
            var reg = new RegExp(/^[0-9a-zA-Z_]{1,}$/);
            var j = value;
            if (!reg.test(j)) {
                return callback(new Error());
            }
            return callback();
        } catch (e) {
            return callback(new Error());
        }
    };
    //数字
    _u.validate_int = function (rule, value, callback) {
        if (!value || value === "") {
            return callback(new Error("如不需填写，请保持不为空"));
        }
        try {
            var reg = new RegExp(/^\+?[1-9][0-9]*$/);
            var j = value;
            if (!reg.test(j)) {
                return callback(new Error("请输入数字"));
            }
            return callback();
        } catch (e) {
            return callback(new Error());
        }
    };
    //经度
    _u.validate_lon = function (rule, value, callback) {
        if (!value || value === "") {
            return callback();
        }
        try {
            if((value.toString()).indexOf('.')<0){
                value=value.toFixed(1);
            }
            var reg =/^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
            var j = value;
            if (!reg.test(j)||parseFloat(j)>180) {
                return callback(new Error("请输入正确格式的经度"));
            }
            return callback();
        } catch (e) {
            return callback(new Error());
        }
    };
    //纬度
    _u.validate_lat = function (rule, value, callback) {
        if (!value || value === "") {
            return callback();
        }
        try {
            if((value.toString()).indexOf('.')<0){
                value=value.toFixed(1);
            }
            var reg =/^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
            var j = value;
            if (!reg.test(j)||parseFloat(j)>90) {
                return callback(new Error("请输入正确格式的纬度"));
            }
            return callback();
        } catch (e) {
            return callback(new Error());
        }
    };
    //传真
    _u.validate_fax = function (rule, value, callback) {
        if (!value || value === "") {
            return callback();
        }
        try {
            var reg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
            var j = value;
            if (!reg.test(j)) {
                return callback(new Error("请输入正确格式的传真"));
            }
            return callback();
        } catch (e) {
            return callback(new Error());
        }
    };
    //日期
    _u.validate_isDate = function (rule, value, callback) {
        if (!value || value === "") {
            return callback();
        }
        try {
            var reg = /^(\d{1,4})(-)(\d{1,2})\2(\d{1,2})$/;
            var j = value;
            if (!reg.test(j)) {
                return callback(new Error("请输入正确格式的日期"));
            }
            return callback();
        } catch (e) {
            return callback(new Error());
        }
    };
    _u.getLayerGroup = function (vueApp, callback) {
        var url = _u.rootPath + api.getLayerGroup;
        var data = {};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data);
            } else {
                _u.error(vueApp, "图层组获取失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "图层组获取失败" + JSON.stringify(_ex));
        });
    };

    _u.getLogType = function (vueApp, callback) {
        var url = _u.rootPath + api.syslog_logType;
        var data = {};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data);
            } else {
                _u.error(vueApp, "日志类型获取失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "日志类型获取失败" + JSON.stringify(_ex));
        });
    };

    _u.getUsers = function (vueApp, callback) {
        var url = _u.rootPath + api.sysuser_query;
        var data = {param: "{}"};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data);
            } else {
                _u.error(vueApp, "获取用户列表失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "获取用户列表失败" + JSON.stringify(_ex));
        });
    };

    _u.getMapConfig = function (vueApp, callback) {
        var url = _u.rootPath + api.getMapConfig;
        var data = {};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data);
            } else {
                _u.error(vueApp, "图层组获取失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "图层组获取失败" + JSON.stringify(_ex));
        });
    };

    _u.getOrgTree = function (vueApp, callback) {
        var url = _u.rootPath + api.sysOrg_tree;
        var data = {id: 1};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data);
            } else {
                _u.error(vueApp, "单位数据获取失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "单位数据获取失败" + JSON.stringify(_ex));
        });
    };

    _u.getAreaTree = function (vueApp, callback) {
        var url = _u.rootPath + api.sysarea_tree;
        var data = {id: 1};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data);
            } else {
                _u.error(vueApp, "区域数据获取失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "区域数据获取失败" + JSON.stringify(_ex));
        });
    };

    _u.getRoles = function (vueApp, callback) {
        var url = _u.rootPath + api.sysrole_query;
        var data = {param: JSON.stringify({})};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data.records);
            } else {
                _u.error(vueApp, "角色数据获取失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "角色数据获取失败" + JSON.stringify(_ex));
        });
    };

    _u.getMenus = function (vueApp, callback) {
        var url = _u.rootPath + api.sysmenu_query;
        var data = {param: JSON.stringify({})};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data.records);
            } else {
                _u.error(vueApp, "菜单数据获取失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "菜单数据获取失败" + JSON.stringify(_ex));
        });
    };

    _u.getOrgs = function (vueApp, callback) {
        var url = _u.rootPath + api.sysOrg_query;
        var data = {param: JSON.stringify({})};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data.records);
            } else {
                _u.error(vueApp, "单位数据获取失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "单位数据获取失败" + JSON.stringify(_ex));
        });
    };

    _u.getTaskModels = function (vueApp, callback) {
        var url = _u.rootPath + api.tbtask_taskModels;
        var data = {};
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data);
            } else {
                _u.error(vueApp, "任务模板获取失败" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "任务模板获取失败" + JSON.stringify(_ex));
        });
    };

    _u.layerType = [
        {
            value: "esri_dynamic",
            label: "ArcGIS动态服务"
        },
        {
            value: "esri_tile",
            label: "ArcGIS切片服务"
        },
    ];

    _u.getRandomData = function (val) {
        var temp = [];
        for (var i = 0; i < val; i++) {
            var item = Math.random() * 100 - Math.random() * 50 - 100;
            //temp.push(Math.abs(item).toFixed(0));
            temp.push(item.toFixed(1));
        }
        return temp;
    };
    _u.getXGap = function (sta, end, val) {
        var temp = [];
        sta = (sta / 1000000);
        end = (end / 1000000);
        var gap = (end - sta) / val;
        for (var i = 0; i < val; i++) {
            var value = 0;
            if (i === 0) {
                value = sta;
            } else if (i === (val - 1)) {
                value = end;
            } else {
                value = sta + i * gap;
            }
            temp.push(value.toFixed(2));
        }
        return temp;
    };
    _u.getXGapPre = function (sta, end, val) {
        var temp = [];
        sta = (sta / 1000000);
        end = (end / 1000000);
        var gap = (end - sta) / val;
        for (var i = 0; i < val; i++) {
            var value = 0;
            if (i === 0) {
                value = sta;
            } else if (i === (val - 1)) {
                value = end;
            } else {
                value = sta + i * gap;
            }
            temp.push(value.toFixed(4));
        }
        return temp;
    };
    _u.getPbData = function (val) {
        var temp = [];
        for (var i = 0; i < val; i++) {
            var value = Math.random() * 600;
            temp.push(value.toFixed(0));
        }
        return temp;
    };

    _u.getRadio = function (index) {
        if (index < 0) {
            return "未知频段";
        } else {
            var item = _u.fmRadio[index];
            if (typeof item === 'undefined') {
                return "未知频段";
            }
            return item;
        }
    };
    _u.fmRadio = [
        "中国国际广播电台",
        "浙江电台 新闻综合频道",
        "嘉兴电台 城乡生活",
        "昆山人民广播电台",
        "吴江人民广播电台 交通音乐",
        "无锡广播电台 新闻",
        "上海东方广播电台",
        "连云港人民广播电台 经济广播",
        "上海东广新闻台",
        "苏州广电总台新闻综合 城市之声",
        "中央人民广播电台 财富之声",
        "杭州人民广播电台 交通经济广播",
        "嘉兴电台 交通经济",
        "无锡人民广播电台 江南之声",
        "南通人民广播电台 音乐交通",
        "浙江电台 交通之声",
        "上海人民广播电台",
        "宁波人民广播电台 音乐台",
        "上海文广 体育频率",
        "苏州人民广播电台 音乐电台",
        "浙江电台 经济台",
        "苏州人民广播电台 生活调频广播网",
        "浙江电台 音乐调频",
        "吴江人民广播电台 新闻综合",
        "上海人民广播电台 戏剧曲艺",
        "浙江电台 新闻综合频道",
        "上海人民广播电台 第一财经频道",
        "无锡都市广播",
        "中国之声",
        "浙江电台 文艺",
        "常州文艺台",
        "常熟人民广播电台 交通音乐",
        "江苏人民广播电台 经济交通广播",
        "上海 动感101",
        "余杭人民广播电台",
        "苏州广播电视总台 故事广播",
        "湖州交通经济",
        "上海 LOVE RADIO",
        "无锡人民广播电台 经济台",
        "浙江电台 旅游之声",
        "杭州 汽车休闲广播",
        "苏州广播电视总台 交通经济",
        "上海交通台",
        "韩语电台",
        "上海青浦人民广播电台",
        "无锡人民广播电台 交通台",
        "中央人民广播电台 音乐之声"
    ];

    _u.getSignal = function (index) {
        if (index < 0) {
            return "未知频段";
        } else {
            var item = _u.signals[index];
            if (typeof item === 'undefined') {
                return "未知频段";
            }
            return item;
        }
    };

    _u.signals = [
        "广播",
        "航空无线电导航",
        "航空移动",
        "航空无线电导航",
        "卫星标准频率和时间信号",
        "气象辅助",
        "无线电定位，航空无线电导航",
        "专用双频通信，农村无线接入",
        "数字电视，微波接力",
        "数字集群通信上行",
        "无线数据通信",
        "中国电信CDMA上行",
        "RFID",
        "微波中继",
        "数字集群通信下行",
        "无线数据通信",
        "中国电信CDMA下行",
        "铁路E-GSM",
        "中国移动GSM上行",
        "中国联通GSM上行",
        "ISM",
        "立体声广播",
        "RFID",
        "铁路E-GSM下行",
        "中国移动GSM下行",
        "中国联通GSM下行",
        "航空导航",
        "科研、定位、导航",
        "空间科学、定位、导航",
        "航空导航，无线电定位",
        "无线电定位",
        "卫星地球勘探",
        "点对多点微波系统",
        "海事卫星通信",
        "航空卫星导航",
        "海事卫星通信",
        "气象卫星通信，无绳电话",
        "中国移动GSM上行",
        "中国联通GSM1800",
        "中国联通FFD上行",
        "中国电信FFD上行",
        "FFD扩展频段",
        "民航专用频段",
        "中国移动GSM下行",
        "中国移动GSM下行",
        "中国联通FDD下行",
        "中国电信FDD下行",
        "FDD保护频段",
        "移动TD-SCDMA/TD-LTE",
        "使用TDD频段",
        "中国电信FDD上行",
        "中国联通WCDMA FDD-LTE上行",
        "FDD上行",
        "卫星通信",
        "TD-SCDMA/TD-LTE",
        "固定台，移动台，卫星通信",
        "中国电信FDD下行",
        "中国联通WCDMA/FDD下行",
        "FDD下行",
        "卫星通信",
        "固定台，移动台，卫星通信",
        "中国联通TDD",
        "中国移动TDD",
        "中国电信TDD",
        "无线电定位，TDD补充频段",
        "ISM频段",
        "卫星广播，卫星移动",
        "卫星广播，卫星移动，TD-LTE",
        "TDD频段",
        "中国联通TDD",
        "中国移动TDD",
        "中国电信TDD",
        "TD-LTE",
        "固定台，移动台，广播",
        "航空无线电导航",
        "无线电导航，定位",
        "TDD",
        "点对点扩频通信，宽带无线接入，蓝牙"
    ];


    _u.proximateXdata = function (data, xdata) {
        var differ = 0;
        var maxDiffer = -1;
        var length = xdata.length;
        for (var i = 0; i < length; i++) {
            differ = Math.abs(xdata[i] - data);
            if (differ >= maxDiffer && maxDiffer !== -1) {
                if (i - 2 > 0) {
                    return i - 1;
                }
                return i;
            }
            maxDiffer = differ;
        }
        return data[length - 1];
    };

    _u.closest = function (arr, num) {
        var left = 0;
        var right = arr.length - 1;
        while (left <= right) {
            var middle = Math.floor((right + left) / 2);
            if (right - left <= 1) {
                break;
            }
            var val = arr[middle];
            if (val === num) {
                return middle;
            } else if (val > num) {
                right = middle;
            } else {
                left = middle;
            }
        }
        var leftValue = arr[left];
        var rightValue = arr[right];
        return rightValue - num > num - leftValue ? left : right;
    };


    _u.buildRequest = function (reqType) {
        return {
            reqType: reqType,
            nodeName: "",
            taskId: "",
            reqMess: ""
        };
    };

    /*请求类型*/
    _u.REQTYPE_LISTNODES = "LISTNODES";
    _u.REQTYPE_REFRESHNODES = "REFRESHNODES";
    _u.REQTYPE_REFRESHNODE = "REFRESHNODE";

    _u.REQTYPE_NODEINFO = "NODEINFO";
    _u.REQTYPE_CHECKTIME = "CHECKTIME";
    _u.REQTYPE_LISTTASKS = "LISTTASKS";
    _u.REQTYPE_TASK = "TASK";
    _u.REQTYPE_TASKDATATEST = "TASKDATATEST";
    _u.REQTYPE_HEARTBEAT = "HEARTBEAT";
    _u.REQTYPE_TASKDATATEST = "TASKDATATEST";

    _u.REQTYPE_DroneDetectList = "DroneDetectList";//无人机监测列表
    _u.REQTYPE_DetectedDrone = "DetectedDrone";//无人机监测记录

    _u.REQTYPE_SUBTASK = "SUBTASK";
    _u.REQTYPE_UNSUBTASK = "UNSUBTASK";

    _u.REQTYPE_RECTASK = "RECTASK";
    _u.REQTYPE_UNRECTASK = "UNRECTASK";

    _u.REQTYPE_CHANGETASK_EnviromentLearning = "EnviromentLearning";//修改任务  EnviromentLearning
    _u.REQTYPE_CHANGETASK_HistoryThreshold = "HistoryThreshold";//修改任务  HistoryThreshold
    _u.REQTYPE_OPERATETASK_CloseHistoryThreshold = "CloseHistoryThreshold";//操作任务 CloseHistoryThreshold

    _u.REQTYPE_CHANGETASK_StartRecord = "StartRecord";//修改任务  节点记录
    _u.REQTYPE_OPERATETASK_StopRecord = "StopRecord";//操作任务 停止节点记录

    _u.REQTYPE_CHANGETASK_ChangeRange = "ChangeRange";//修改任务  ChangeRange

    _u.REQTYPE_OPERATETASK_endTask = "endTask";//操作任务 endTask

    /*返回类型*/
    _u.RESTYPE_CHECKTIME = "CHECKTIME";
    _u.RESTYPE_INFO = "INFO";//返回普通消息
    _u.RESTYPE_NODEINFO = "NODEINFO";
    _u.RESTYPE_TASKINFO = "TASKINFO";
    _u.RESTYPE_HEARTBEAT = "HEARTBEAT";
    _u.RESTYPE_REBOOT = "REBOOT";
    _u.RESTYPE_TASK = "TASK";
    _u.RESTYPE_TASKACTIVITY = "TASKACTIVITY"; //任务活动信息

    _u.REQTYPE_AUDIO = "AUDIO"; //是否开启音频
    _u.REQTYPE_DroneSpectrum = "DroneSpectrum";//是否开启无人机频谱
    _u.REQTYPE_DroneIdentify = "DroneIdentify";//开启无人机识别
    _u.REQTYPE_DroneNodeRecord = "DroneNodeRecord";//无人机节点记录
    _u.REQTYPE_DroneThreshold = "DroneThreshold";//无人机门限
    _u.REQTYPE_DroneJammer = "DroneJammer";//无人机反制

    _u.RESTYPE_RECTASK = "RECTASK";
    _u.RESTYPE_UNRECTASK = "UNRECTASK";

    _u.REQTYPE_ChangeChannel = "ChangeChannel";//更改调制解调idx

    _u.REQTYPE_RebootNode = "RebootNode";

    _u.RESTYPE_TASKDATA = "TASKDATA";
    _u.RESTYPE_CHANGETASK_EnviromentLearning = "EnviromentLearning";//修改任务  EnviromentLearning
    _u.RESTYPE_CHANGETASK_HistoryThreshold = "HistoryThreshold";//修改任务  HistoryThreshold
    _u.RESTYPE_CHANGETASK_StartRecord = "StartRecord";//修改任务  StartRecord
    _u.RESTYPE_CHANGETASK_ChangeRange = "ChangeRange";//修改任务  ChangeRange
    _u.RESTYPE_OPERATETASK_endTask = "endTask";//操作任务 endTask
    _u.RESTYPE_OPERATETASK_StopRecord = "StopRecord";//操作任务 StopRecord
    _u.RESTYPE_OPERATETASK_CloseHistoryThreshold = "CloseHistoryThreshold";//操作任务 CloseHistoryThreshold
    //心跳检测
    _u.reqHeatBeat = {
        reqType: _u.REQTYPE_HEARTBEAT,
        nodeId: "",
        taskId: "",
        reqMess: ""
    };
    _u.TASKTYPE_SPECTRUM = "SPECTRUM";
    _u.TASKTYPE_AVIATION = "AVIATION";
    _u.TASKTYPE_AUDIO = "AUDIO";
    _u.TASKTYPE_TDOA = "TDOA";
    _u.taskTypeDict = {
        "SPECTRUM": "频谱扫描",
        "AVIATION": "无人机监控",
        "AUDIO": "调制解调",
        "TDOA": "TDOA",
    };
    _u.taskStatusDict = {
        1: "任务录入数据库",
        3: "任务结束",
        2: "台站响应，任务开启",
        4: "任务加入请求队列",
        5: "主动取消 任务结束"
    };
    _u.logTypeDict = {
        "task_alert": "任务报警",
        "task_exception": "任务异常",
        "detected_Drone": "发现无人机",
        "Interaction": "任务交互异常"
    };
    _u.dictLog = function (list) {
        list.forEach(function (log) {
            log.logTypeName = _u.logTypeDict[log.log_type];
        });
        return list;
    };
    _u.dictTaskStatus = function (status) {
        if (!_u.taskStatusDict.hasOwnProperty(status)) {
            return "未知状态";
        } else {
            return _u.taskStatusDict[status];
        }
    };
    _u.dictTaskType = function (type) {
        if (!_u.taskTypeDict.hasOwnProperty(type)) {
            return "未知类型";
        } else {
            return _u.taskTypeDict[type];
        }
    };
    /**
     * ws 推送的task 进行必要的完善
     * @param item
     */
    _u.taskFormat = function (item) {
        item.taskTypeName = _u.dictTaskType(item.taskType);
        item.taskStatusName = _u.dictTaskStatus(item.taskStatus);
        if (item.hasOwnProperty("station") && item.station.hasOwnProperty("stationName")) {
            item.nodeName = item.station.stationName;
        }
        if (item.taskType !== 'SPECTRUM') {
            try {
                item.channel = JSON.parse(item.channel)
            } catch (e) {
                console.log("channel error")
                item.channel = [];
            }
        }
        if (item.taskType === 'TDOA') {
            try {
                item.channel = {
                    nodes: JSON.parse(item.channel.nodes),
                    ts: JSON.parse(item.channel.ts),
                }
            } catch (e) {
                console.log("channel error")
                item.channel = {
                    nodes: [],
                    ts: [],
                };
            }
        }
        if (item.hasOwnProperty("createUser") && item.createUser.hasOwnProperty("displayName")) {
            item.userName = item.createUser.displayName;
        }
    }
    /*任列表务格式*/
    _u.formatTbTask = function (page) {
        var list = page.records;
        list.forEach(function (item) {
            item.taskTypeName = _u.dictTaskType(item.taskType);
            item.taskStatusName = _u.dictTaskStatus(item.taskStatus);
            if (item.hasOwnProperty("station") && item.station.hasOwnProperty("stationName")) {
                item.nodeName = item.station.stationName;
            }
            if (item.taskType !== 'SPECTRUM') {
                item.channel = item.channel ? item.channel : "[]";
                try {
                    item.channel = JSON.parse(item.channel)
                } catch (e) {
                    console.log("channel error")
                    item.channel = [];
                }
            }
            if (item.taskType === 'TDOA') {
                try {
                    item.channel = {
                        nodes: JSON.parse(item.channel.nodes ? item.channel.nodes : "[]"),
                        ts: JSON.parse(item.channel.ts ? item.channel.ts : "[]")
                    }
                } catch (e) {
                    console.log("channel error")
                    item.channel = {
                        nodes: [],
                        ts: [],
                    };
                }
            }
            if (item.hasOwnProperty("createUser") && item.createUser.hasOwnProperty("displayName")) {
                item.userName = item.createUser.displayName;
            }
        });
        return page;
    };
    _u.station2Node = function (station) {
        return {
            nodeId: station.id,
            nodeName: station.stationName,
            position: {
                latitude: station.latitude,
                longitude: station.longitude
            }
        }
    };

    _u.getValueConfig = function (vueApp, code, callback) {
        var url = _u.rootPath + api.sysDict_dictItem;
        var data = new FormData();
        data.append("dictcode", code)
        _$.post(vueApp, data, url, function (_r, _x) {
            if (_r.success) {
                callback(_r.data);
            } else {
                _u.error(vueApp, "曲线图取值范围配置获取失败，采用默认值" + _r.message);
            }
        }, function (_ex) {
            _u.error(vueApp, "曲线图取值范围配置获取失败，采用默认值" + JSON.stringify(_ex));
        });
    };
});


Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

String.prototype.compare = function (str) {
    if (this.toLowerCase() == str.toLowerCase()) {
        return true;
    } else {
        return false;
    }
};
