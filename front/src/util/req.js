//方法请求的配置文件
import iView from 'iview'
import storage from '@/store/storage'
import {_notify} from "@/util/util"

const tokenKey = window.globle.tokenKey;

/*ajax操作*/
let _$ = {};
_$.BHToken = '';
_$.setBHToken = function(token){
  _$.BHToken = token;
}
_$.ajax = function (options) {
  /*data:formdata或者{}对象*/
  var createXHR = function () {
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
  var addToken = options.addToken || false;
  var responseType = options.responseType || ''; //默认返回字符，支持"arraybuffer"
  var data = options.data || {};
  var dataType = options.dataType || "";
  var url = options.url;
  var success = options.success || function (data) {};
  var fail = options.fail || function (data) {}
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      var response;
      if (responseType === "arraybuffer") {
        var response = xhr.response;
      } else {
        response = xhr.responseText;
        if (dataType.toUpperCase() === 'JSON') {
          try {
            response = JSON.parse(response);
          } catch (err) {
            if (response.indexOf("SYSTEMLOGINPAGEMARKER") !== -1) {
              response = {
                success: false,
                message: "登录超时,需重新登录",
                data: response
              }
            } else {
              response = {
                success: false,
                message: "请求返回错误，请通知开发人员",
                data: response
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
    if (data instanceof FormData) {
      for (let [key, value] of data.entries()) {
        str += encodeURIComponent(key) + '=' + encodeURIComponent(JSON.stringify(value)) + '&';
      }
    } else {
      for (var i in data) {
        var item = data[i];
        str += encodeURIComponent(i) + '=' + encodeURIComponent(JSON.stringify(item)) + '&';
      }
    }
    str = str.substring(0, str.length - 1);
    xhr.open("GET", url + str, true);
    if (responseType === "arraybuffer") {
      xhr.responseType = "arraybuffer";
    }
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhr.send(null);
  } else if (type.toUpperCase() === 'POST') {
    var paramdata = new FormData();
    if (data instanceof FormData) {
      paramdata = data;
    } else {
      for (var index in data) {
        if (data.hasOwnProperty(index)) {
          paramdata.append(index, encodeURI(data[index]));
        }
      }
    }
    xhr.open("POST", url, true);
    if(addToken){
      xhr.setRequestHeader(tokenKey, storage.getToken());
    }
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
    xhr.send(paramdata);
  }
};

/**
 * 封装post
 * @param pdata formdata或者{}对象
 * @param purl url
 * @param psuccess 成功回调
 * @param pfail 失败回调
 */
_$.post = function (pdata, purl, psuccess, pfail) {
  var createXHR = function () {
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
  var success = psuccess || function (data) {};
  var fail = pfail || function (data) {}
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      var response = xhr.responseText;
      if (dataType.toUpperCase() === 'JSON') {
        try {
          response = JSON.parse(response);
        } catch (err) {
          if (response.indexOf("SYSTEMLOGINPAGEMARKER") !== -1 || response.indexOf("未知页面") !== -1) {
            response = {
              success: false,
              code:401,
              message: "未授权的页面",
              sysMessage:"未授权的页面",
              data: response
            }
          } else {
            response = {
              success: false,
              code:0,
              message: "请求返回错误，请通知开发人员",
              sysMessage:"请求返回错误，请通知开发人员",
              data: response
            }
          }
        }
      }
      if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
        iView.LoadingBar.finish()
        if(response.code == 401){
          _notify.error("登录信息过期");
          return
        }
        if(!response.success){
          fail(response, xhr);
          return
        }
        success(response, xhr);
      } else {
        iView.LoadingBar.finish()
        fail(response, xhr);
      }
    }
  };
  var str = '?';
  var paramdata = new FormData();
  if (data instanceof FormData) {
    paramdata = data;
  } else {
    for (var index in data) {
      if (data.hasOwnProperty(index)) {
        paramdata.append(index, encodeURI(data[index]));
      }
    }
  }
  xhr.open("POST", url, true);
  xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
  xhr.setRequestHeader(tokenKey, storage.getToken());
  let a = storage.getToken();
  xhr.send(paramdata);
  iView.LoadingBar.start();
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

/**
 * 封装post 不添加Authorization
 * @param pdata formdata或者{}对象
 * @param purl url
 * @param psuccess 成功回调
 * @param pfail 失败回调
 */
_$.postFree = function (pdata, purl, psuccess, pfail) {
  var createXHR = function () {
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
  var success = psuccess || function (data) {};
  var fail = pfail || function (data) {}
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      var response = xhr.responseText;
      if (dataType.toUpperCase() === 'JSON') {
        try {
          response = JSON.parse(response);
        } catch (err) {
          if (response.indexOf("SYSTEMLOGINPAGEMARKER") !== -1) {
            response = {
              success: false,
              message: "登录超时,需重新登录",
              data: response
            }
          } else {
            response = {
              success: false,
              message: "请求返回错误，请通知开发人员",
              data: response
            }
          }
        }
      }
      if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
        iView.LoadingBar.finish()
        success(response, xhr);
      } else {
        iView.LoadingBar.finish()
        fail(response, xhr);
      }
    }
  };
  var str = '?';
  var paramdata = new FormData();
  if (data instanceof FormData) {
    paramdata = data;
  } else {
    for (var index in data) {
      if (data.hasOwnProperty(index)) {
        paramdata.append(index, encodeURI(data[index]));
      }
    }
  }
  xhr.open("POST", url, true);
  xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
  xhr.send(paramdata);
  iView.LoadingBar.start();
};

/**
 * 封装get
 * @param pdata formdata或者{}对象
 * @param purl url
 * @param psuccess 成功回调
 * @param pfail 失败回调
 */
_$.get = function (pdata, purl, psuccess, pfail) {
  var createXHR = function () {
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
  var success = psuccess || function (data) {};
  var fail = pfail || function (data) {}
  xhr.onreadystatechange = function () {
    if (xhr.readyState === 4) {
      var response = xhr.responseText;
      if (dataType.toUpperCase() === 'JSON') {
        try {
          response = JSON.parse(response);
        } catch (err) {
          if (response.indexOf("SYSTEMLOGINPAGEMARKER") !== -1) {
            response = {
              success: false,
              message: "登录超时,需重新登录",
              data: response
            }
          } else {
            response = {
              success: false,
              message: "请求返回错误，请通知开发人员",
              data: response
            }
          }
        }
      }
      if ((xhr.status >= 200 && xhr.status < 300) || xhr.status === 304) {
        iView.LoadingBar.finish()
        success(response, xhr);
      } else {
        iView.LoadingBar.finish()
        fail(response, xhr);
      }
    }
  };
  var str = '?';
  if (data instanceof FormData) {
    for (let [key, value] of data.entries()) {
      str += encodeURIComponent(key) + '=' + encodeURIComponent(JSON.stringify(value)) + '&';
    }
  } else {
    for (var i in data) {
      var item = data[i];
      str += encodeURIComponent(i) + '=' + encodeURIComponent(JSON.stringify(item)) + '&';
    }
  }
  str = str.substring(0, str.length - 1);
  xhr.open("GET", url + str, true);
  xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
  xhr.setRequestHeader(tokenKey, storage.getToken());
  xhr.send(null);
  iView.LoadingBar.start();
};

export default _$
