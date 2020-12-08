(function(name,definition){
    var hasDefine = typeof define == "function",
        hasModuleExports = typeof module != "undefined" && module.exports;

    //当存在define、module函数时候，包装为模块
    if(hasDefine){
        define(name,definition);
    }else if(hasModuleExports){
        module.exports = definition();
    }else{
        this[name] = definition();
    }
})("lib",function() {
    var root = window,
        _$ = {}; //仿照jquery生成一个命名空间_$
    root._$ = _$; //把命名空间挂载到window下，以便外部访问

    /*私有函数 类型判断*/
    var getType = function(para) {
        return Object.prototype.toString.call(para).slice(8, -1);
    }
    /*对象浅拷贝*/
    var shallowCopy = function(old, newer) {
        for (i in old) {
            newer[i] = old[i];
        }
    }
    /*对象深拷贝*/
    var deepCopy = function(newer) {
        var obj = {};
        for (i in newer) {
            obj[i] = typeof newer[i] == 'object' ? deepCopy(newer[i]) : newer[i];
        }
        return obj;
    };

    _$.clone_str = function (obj) {
        var o;
        switch(typeof obj){
            case 'undefined': break;
            case 'string'   : o = obj + '';break;
            case 'number'   : o = obj - 0;break;
            case 'boolean'  : o = obj;break;
            case 'object'   :
                if(obj === null){
                    o = "";
                }else{
                    if(obj instanceof Array){
                        o = [];
                        for(var i = 0, len = obj.length; i < len; i++){
                            o.push(_$.clone_str(obj[i]));
                        }
                    }else{
                        o = {};
                        for(var k in obj){
                            o[k] = _$.clone_str(obj[k]);
                        }
                    }
                }
                break;
            default:
                o = obj;break;
        }
        return o;
    };

    _$.clone = function (obj) {
        var o;
        switch(typeof obj){
            case 'undefined': break;
            case 'string'   : o = obj + '';break;
            case 'number'   : o = obj - 0;break;
            case 'boolean'  : o = obj;break;
            case 'object'   :
                if(obj === null){
                    o = null;
                }else{
                    if(obj instanceof Array){
                        o = [];
                        for(var i = 0, len = obj.length; i < len; i++){
                            o.push(_$.clone(obj[i]));
                        }
                    }else{
                        o = {};
                        for(var k in obj){
                            o[k] = _$.clone(obj[k]);
                        }
                    }
                }
                break;
            default:
                o = obj;break;
        }
        return o;
    };

    var util = {};
    _$.util = util;
    /*传入的字符串是否重复*/
    util.isDuplicate=function(){
        var array = Array.prototype.slice.call(arguments);  //arguments为数组
        array.sort(compare);
        var result;
        var compare = function(value1,value2){           //按从小到大的顺序排
            return value1-value2;
        }
        for(var i = 0;i< array.length-1;i++){
            if(array[i] == array[i+1]){
                result = true;
                break;
            }
            else{
                result = false;
            }
        }
        return result;
    }

    /*产生随机数的数组
    *@param Max:最大范围的数
    *@param len: 数组长度
    */
    util.getRandom = function(Max,len){
        var temp = [];

        while(temp.length<len){
            //设置标志检测产生的随机数是否相等
            var flag = true;
            //向上取整1-Max
            var r = Math.ceil(Math.random()*Max);
            //第一次产生的随机数不进入循环
            for(var i = 0;i < temp.length;i++){
                if(temp[i] == r){
                    flag = false;
                }else{
                    flag = true;
                }
            }

            if(flag){
                temp[temp.length]  = r;
            }
        }
        return temp;
    }

    /*数组方法*/
    var arr = {};
    _$.array = arr;

    /*数组中是否含有某值*/
    arr.contains = function(arr, content) {
        for (i in arr) {
            if (arr[i] == content) {
                return true;
            }
        }
        return false;
    }
    /*数组去重*/
    arr.unique = function(arr) {
        var newArr = [],
            judgeObj = {}; //hash表
        for (var i = 0, len = arr.length; i < len; i++) {
            var item = arr[i];
            if (judgeObj[item] == undefined) { //如果hash表中没有当前项
                newArr.push(item);
                judgeObj[item] = true;
            }
        }
        return newArr;
    }
    /*山形数组去重*/
    arr.uniqueMountain = (function(){
        var p,
            q,
            len=arr.length,
            judgeObj = {};
        p=0;
        q=len-1;
        return function print(arr){
            if(p>len || q<0) return;
            if(arr[p]<arr[q]){
                if(!judgeObj[arr[p]]){
                    console.log(arr[p]);
                    judgeObj[arr[p]] = true;
                }
                p++;
                arguments.callee(arr);
            }else if(arr[p]>arr[q]){
                if(!judgeObj[arr[q]]){
                    console.log(arr[q]);
                    judgeObj[arr[q]] = true;
                }
                q--;
                arguments.callee(arr);

            }else{
                if(!judgeObj[arr[q]] && !judgeObj[arr[p]]){
                    console.log(arr[p]);
                    judgeObj[arr[p]] = true;
                    judgeObj[arr[q]] = true;
                }
                p++;
                q--;
                arguments.callee(arr);
            }
        }
    })();
    /*删除指定元素*/
    arr.remove = function(arr, content) {
        for (var i = 0, len = arr.length; i < len; i++) {
            if (arr[i] == content) {
                arr.splice(i, 1);
            }
        }
        return arr;
    }
    /*优化的数组冒泡排序*/
    arr.bubblSort = function(arr) {
        var swap = function(index1, index2) {
            var aux = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = aux;
        }
        for (var i = 0, len = arr.length; i < len; i++) {
            for (var j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
        return arr;
    }
    /*增对数组中的每个元素执行fn函数*/
    arr.each = function(arr, fn) {
        for (var i = 0, len = arr.length; i < len; i++) {
            fn.call(arr, i, arr[i]);
        }
    }

    /*css方法*/
    var css = {};
    _$.css = css;

    /*获取元素css样式*/
    css.getCss = function(ele, styleName) {
        var styles = window.getComputedStyle ? window.getComputedStyle(ele, null) : ele.currentStyle;
        return styles[styleName];
    }
    /*设置元素css样式*/
    css.setCss = function(ele, styleNames) {
        if (getType(ele) == 'Array') {
            for (var j = 0, len = ele.length; j < len; j++) {
                if (getType(styleNames) == 'Object') {
                    for (i in styleNames) {
                        ele[j]['style'][i] = styleNames[i];
                    }
                }
            }
        } else if (getType(styleNames) == 'Object') {
            for (i in styleNames) {
                ele['style'][i] = styleNames[i];
            }
        }

    }

    /*事件方法*/
    var eventUtil = {};
    _$.eventUtil = eventUtil;

    /*添加监听事件*/
    eventUtil.addHandler = function(ele, type, handler) {
        if (ele.addEventListener) {
            ele.addEventListener(type, handler, false);
        } else if (ele.attachEvent) {
            ele.attachEvent('on' + type, handler);
        } else {
            ele["on" + type] = handler;
        }
    }
    eventUtil.removeHandler = function(ele,type,handle) {
        if(elem.removeEventListener){
            elem.removeEventListener(type,handler,false);
        }else if(elem.detachEvent){
            elem.detachEvent("on"+type,handler);
        }else{
            elem["on"+type]=null;
        }
    }
    /*获得事件对象*/
    eventUtil.getEvent = function(event) {
        return event ? event : window.event;
    }
    /*获取目标*/
    eventUtil.getTarget = function(event) {
        return event.target || event.srcElement;
    }
    /*阻止事件冒泡*/
    eventUtil.stopPropagation = function(event) {
        if (event.stopPropagation) {
            event.stopPropagation();
        } else {
            event.cancelable = true;
        }
    }
    /*阻止默认行为*/
    eventUtil.preventDefault = function(event) {
        if (event.preventDefault) {
            event.preventDefault();
        } else {
            event.returnValue = false;
        }
    }

    /*dom操作*/
    var dom = {};
    _$.dom = dom;

    /*判断给定值是否是DOM元素*/
    dom.isElementDom = function(obj){
        return !!(obj && obj.nodeType == 1);
    }
    /*判断元素是否有该类名*/
    dom.hasClass = function(ele, className) {
        if (!ele.className) {
            return false;
        } else {
            var arr = ele.className.split(" ");
            for (var i = 0, len = arr.length; i < len; i++) {
                if (arr[i] == className) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
    /*为dom增加一个class类名*/
    dom.addClass = function(ele, newClassName) {
        if (!ele.className) {
            ele.className = newClassName;
        } else {
            if (!dom.hasClass(ele, newClassName)) {
                var oldName = ele.className;
                var newName = [oldName, newClassName].join(' ');
                ele.className = newName;
            }
        }
    }
    /*为dom删除一个class类名*/
    dom.removeClass = function(ele, oldClassName) {
        if (oldClassName == '*') {
            ele.className = '';
        }
        var nameArry = ele.className.split(' ');
        for (var i = 0, len = nameArry.length; i < len; i++) {
            if (nameArry[i] == oldClassName) {
                nameArry.splice(i, 1);
            };
        }
        var names = nameArry.join(' ');
        ele.className = names;
    }
    /*通过类名获取元素*/
    dom.getElementsByClass = function(className, parent) {
        if (typeof className != "string") return false;
        var result = [],
            eles;
        parent ? eles = parent.getElementsByTagName("*") : eles = document.getElementsByTagName("*");
        for (var i = 0, len = eles.length; i < len; i++) {
            var arr = eles[i].className.split(" ");
            for (var j = 0, leng = arr.length; j < leng; j++) {
                if (arr[j] == className) {
                    result.push(eles[i]);
                }
            }
        }
        return result;
    }
    /*增加属性值或者获取属性值*/
    dom.attr = function(ele, name, value) {
        if (value) {
            ele.setAttribute(name, value);
        } else {
            var attrName = ele.getAttribute(name);
            return attrName;
        }
    }
    /*设置style样式*/
    dom.setStyle = function(ele, obj) {
        if (getType(obj) != "Object") return false;
        for (var item in obj) {
            ele["style"][item] = obj[item];
        }
    }
    /*绑定事件*/
    dom.on = function(ele, type, handler) {
        eventUtil.addHandler(ele, type, handler);
    }
    /*获取dom位置(元素各边距离页面上和左边的距离)*/
    dom.getPosition = function(ele) {
        var box = ele.getBoundingClientRect();
        return {
            top: box.top, // 元素上边距离页面上边的距离
            left: box.left, // 元素左边距离页面左边的距离
            right: box.right, // 元素右边距离页面左边的距离
            bottom: box.bottom
        }
    }
    /*ajax操作*/
    _$.ajax = function(options) {
        /*data:formdata或者{}对象*/
        var createXHR = function() {
            if (typeof XMLHttpRequest != 'undefined') {
                return new XMLHttpRequest;
            } else if (typeof ActiveXObject != 'undefined') {
                if (typeof arguments.callee.activeXString != 'string') {
                    var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"],
                        i, len;
                    for (var i = 0, len = versions.length; i < len; i++) {
                        try {
                            new ActiveXObect(versions[i]);
                            arguments.callee.activeXString = versions[i];
                            break;
                        } catch (ex) {
                            //跳过
                        }
                    }
                }
                return new ActiveXObect(arguments.callee.activeXString);
            } else {
                throw new Error("No XHRobject available.");
            }
        }
        var xhr = createXHR();
        var options = options || {};
        var type = options.type || 'GET';
        var responseType = options.responseType || '';//默认返回字符，支持"arraybuffer"
        var data = options.data || {};
        var dataType = options.dataType || "";
        var url = options.url;
        var success = options.success || function(data) {};
        var fail = options.fail || function(data) {}
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                var response;
                if (responseType === "arraybuffer") {
                    var response = xhr.response;
                }else {
                    response = xhr.responseText;
                    if (dataType.toUpperCase() === 'JSON'){
                        try{
                            response = JSON.parse(response);
                        }catch(err){
                            if (response.indexOf("SYSTEMLOGINPAGEMARKER") !== -1){
                                response = {
                                    success:false,
                                    message:"登录超时,需重新登录",
                                    data:response
                                }
                            } else {
                                response = {
                                    success:false,
                                    message:"请求返回错误，请通知开发人员",
                                    data:response
                                }
                            }
                        }
                    }
                }
                if ((xhr.status >= 200 && xhr.status < 300) || xhr.status == 304) {
                    success(response, xhr);
                } else {
                    fail(response, xhr);
                }
            }
        };
        var str = '?';
        if (type.toUpperCase() === 'GET') {
            for (var i in data) {
                var item = data[i];
                str += encodeURIComponent(i) + '=' + encodeURIComponent(JSON.stringify(item)) + '&';
            }
            str = str.substring(0, str.length - 1);
            xhr.open("GET", url + str, true);
            xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
            if (responseType === "arraybuffer") {
                xhr.responseType = "arraybuffer";
            }
            xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
            xhr.send(null);
        } else if (type.toUpperCase() === 'POST') {
            var paramdata = new FormData();
            if (data instanceof FormData){
                paramdata = data;
            } else {
                for (var index in data) {
                    if (data.hasOwnProperty(index)) {
                        paramdata.append(index,encodeURI(data[index]));
                    }
                }
            }
            xhr.open("POST", url, true);
            xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
            xhr.send(paramdata);
        }
    };

    /**
     * 封装post
     * @param pvue 加入进度条的vue实例，若不需要传false
     * @param pdata formdata或者{}对象
     * @param purl url
     * @param psuccess 成功回调
     * @param pfail 失败回调
     */
    _$.post = function(pvue,pdata,purl,psuccess,pfail) {
        var createXHR = function() {
            if (typeof XMLHttpRequest != 'undefined') {
                return new XMLHttpRequest;
            } else if (typeof ActiveXObject != 'undefined') {
                if (typeof arguments.callee.activeXString != 'string') {
                    var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"],
                        i, len;
                    for (var i = 0, len = versions.length; i < len; i++) {
                        try {
                            new ActiveXObect(versions[i]);
                            arguments.callee.activeXString = versions[i];
                            break;
                        } catch (ex) {
                            //跳过
                        }
                    }
                }
                return new ActiveXObect(arguments.callee.activeXString);
            } else {
                throw new Error("No XHRobject available.");
            }
        }
        var xhr = createXHR();
        var data = pdata || {};
        var dataType = "JSON";
        var url = purl;
        var success = psuccess || function(data) {};
        var fail = pfail || function(data) {}
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                var response = xhr.responseText;
                if (dataType.toUpperCase() === 'JSON'){
                    try{
                        response = JSON.parse(response);
                    }catch(err){
                        if (response.indexOf("SYSTEMLOGINPAGEMARKER") !== -1){
                            response = {
                                success:false,
                                message:"登录超时,需重新登录",
                                data:response
                            }
                        } else {
                            response = {
                                success:false,
                                message:"请求返回错误，请通知开发人员",
                                data:response
                            }
                        }
                    }
                }
                if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
                    if (pvue){
                        pvue.$Loading.finish();
                    }
                    success(response, xhr);
                } else {
                    if (pvue){
                        pvue.$Loading.error();
                    }
                    fail(response, xhr);
                }
            }
        };
        var str = '?';
        var paramdata = new FormData();
        if (data instanceof FormData){
            paramdata = data;
        } else {
            for (var index in data) {
                if (data.hasOwnProperty(index)) {
                    paramdata.append(index,encodeURI(data[index]));
                }
            }
        }
        xhr.open("POST", url, true);
        xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        xhr.send(paramdata);
        if (pvue){
            pvue.$Loading.start();
        }
    };

    /**
     * 封装get
     * @param pvue 加入进度条的vue实例，若不需要传false
     * @param pdata formdata或者{}对象
     * @param purl url
     * @param psuccess 成功回调
     * @param pfail 失败回调
     */
    _$.get = function(pvue,pdata,purl,psuccess,pfail) {
        var createXHR = function() {
            if (typeof XMLHttpRequest != 'undefined') {
                return new XMLHttpRequest;
            } else if (typeof ActiveXObject != 'undefined') {
                if (typeof arguments.callee.activeXString != 'string') {
                    var versions = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"],
                        i, len;
                    for (var i = 0, len = versions.length; i < len; i++) {
                        try {
                            new ActiveXObect(versions[i]);
                            arguments.callee.activeXString = versions[i];
                            break;
                        } catch (ex) {
                            //跳过
                        }
                    }
                }
                return new ActiveXObect(arguments.callee.activeXString);
            } else {
                throw new Error("No XHRobject available.");
            }
        }
        var xhr = createXHR();
        var data = pdata || {};
        var dataType = "JSON";
        var url = purl;
        var success = psuccess || function(data) {};
        var fail = pfail || function(data) {}
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                var response = xhr.responseText;
                if (dataType.toUpperCase() === 'JSON'){
                    try{
                        response = JSON.parse(response);
                    }catch(err){
                        if (response.indexOf("SYSTEMLOGINPAGEMARKER") !== -1){
                            response = {
                                success:false,
                                message:"登录超时,需重新登录",
                                data:response
                            }
                        } else {
                            response = {
                                success:false,
                                message:"请求返回错误，请通知开发人员",
                                data:response
                            }
                        }
                    }
                }
                if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
                    if (pvue){
                        pvue.$Loading.finish();
                    }
                    success(response, xhr);
                } else {
                    if (pvue){
                        pvue.$Loading.error();
                    }
                    fail(response, xhr);
                }
            }
        };
        var str = '?';
        var paramdata = new FormData();
        if (data instanceof FormData){
            paramdata = data;
        } else {
            for (var index in data) {
                if (data.hasOwnProperty(index)) {
                    paramdata.append(index,encodeURI(data[index]));
                }
            }
        }
        xhr.open("GET", url, true);
        xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        xhr.send(paramdata);
        if (pvue){
            pvue.$Loading.start();
        }
    };

    /*url操作*/
    var url = {};
    _$.url = url;

    url.getUrlString = function(){
        var queryStr = location.search > 1 ? location.search.substring(1):'';
        var args = {};
        var items = queryStr.split('&');
        var item,key,value,i,len =items.length;
        for(i = 0;i<len;i++){
            item = items[i].split('=');
            key = decodeURIComponent(item[0]);
            value = decodeURIComponent(item[1]);
            args[key] = value;
        }
        return args;
    }

    var base64 = {};
    _$.base64 = base64;
    base64._keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    base64.decode = function (str) {
        return decodeURIComponent(atob(str).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
    }
});
