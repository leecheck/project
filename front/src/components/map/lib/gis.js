//度转度°分′秒″
function ToDegrees(val) {
    if (typeof(val) == "undefined" || val == "") {
        return "";
    }
    var i = val.indexOf('.');
    var strDu = i < 0 ? val : val.substring(0, i); //获取度
    var strFen = 0;
    var strMiao = 0;
    if (i > 0) {
        var strFen = "0" + val.substring(i);
        strFen = strFen * 60 + "";
        i = strFen.indexOf('.');
        if (i > 0) {
            strMiao = "0" + strFen.substring(i);
            strFen = strFen.substring(0, i); //获取分
            strMiao = strMiao * 60 + "";
            i = strMiao.indexOf('.');
            strMiao = strMiao.substring(0, i + 4); //取到小数点后面三位
            strMiao = parseFloat(strMiao).toFixed(2); //精确小数点后面两位
        }
    }
    return strDu + "'" + strFen + "'" + strMiao;
}

//度°分′秒″转度
function ToDigital(strDu, strFen, strMiao, len) {
    len = (len > 6 || typeof(len) == "undefined") ? 6 : len; //精确到小数点后最多六位   
    strDu = (typeof(strDu) == "undefined" || strDu == "") ? 0 : parseFloat(strDu);
    strFen = (typeof(strFen) == "undefined" || strFen == "") ? 0 : parseFloat(strFen) / 60;
    strMiao = (typeof(strMiao) == "undefined" || strMiao == "") ? 0 : parseFloat(strMiao) / 3600;
    var digital = strDu + strFen + strMiao;
    if (digital == 0) {
        return "";
    } else {
        return digital.toFixed(len);
    }
}

/**
 文件说明：将gps的坐标转换成度形式的坐标方便计算 gpsToDu.js
 功能说明：
        将str1 和 str2 形式的坐标转换成 str3形式  【 度分秒格式和度分格式 转换成 度格式 】
             var str1="W 39°55′44″";//d-m-s  或者  str1="39°55′44″ W";
             var str2="N 39°55.7333′";//d-m   或者  str2="39°55.7333′ E";
             to:
             var str3="39.9288888885"; // d
 */
/**
 *  函数使用说明：
 *      1、直接调用函数  gpsToDu(gpsStr);
 *  返回说明： 返回结果数组
 *      1 gpsDu[0] 表示方向 ，小写字母 s w e n
 *      2 gpsDu[1] 表示计算的结果 ， 数字-19.8222222
 * */
/**
 *  优点： 自动处理空格与其他无关字符，只处理表示方向、小数点、数字三种字符
 *  缺点： 小数点必须为半角的小数点 .  不能是全角小数点
 * 注：1、将gps数据转换成小数点格式即度格式: W 39°55′44″->39.9288888885 或 39°55.7333 ' N ->39.9288888885
注： 2、其中： W S N E 分别表示经纬度方向 返回结果中 W S 将返回负值
 * */

function gpsToDu(gpsStr) {
    gpsStr = gpsStr.toLowerCase();
    gpsStr = gpsStr.replace(/\s+/g, "");
    var tempStrArray = new Array();
    var flag = 1;
    var lastFlag = 0;
    var strLength = gpsStr.length;
    var gpsDu = new Array();
    var gpsDir;
    var tempcount = 0;
    var tempString = "";
    var tempPointFlag = 0;
    if (gpsStr[0] == 'w' || gpsStr[0] == 's') {
        flag = -1;
        lastFlag = 0;
        gpsDir = gpsStr[0];
    } else if (gpsStr[strLength - 1] == 'w' || gpsStr[strLength - 1] == 's') {
        flag = -1;
        lastFlag = 1;
        gpsDir = gpsStr[strLength - 1];
    }
    for (var i = 0; i <= strLength; i++) {
        if (gpsStr[i] >= '0' && gpsStr[i] <= '9') {
            tempString += gpsStr[i];
            continue;
        } else if (gpsStr[i] == '.') {
            tempStrArray[tempcount] = tempString;
            tempString = "";
            tempcount++;
            tempStrArray[tempcount] = '.';
            tempPointFlag = 1;
            tempcount++;
        } else if (tempString.length > 0) {
            tempStrArray[tempcount] = tempString;
            tempString = "";
            tempcount++;
        }
    }
    if (tempPointFlag == 0) {
        var num1 = parseInt(tempStrArray[0] || 0, 10);
        var num2 = parseInt(tempStrArray[1] || 0, 10);
        var num3 = parseInt(tempStrArray[2] || 0, 10);
        console.log(num1 + '  ' + num2 / 60 + ' ' + num3 / (60 * 60));
        gpsDu[1] = num1 + num2 / 60 + num3 / (60 * 60);
        gpsDu[1] = gpsDu[1] * flag;
        gpsDu[0] = gpsDir;
    } else if (tempPointFlag == 1) {
        var num1 = parseInt(tempStrArray[0], 10);
        var num2 = parseFloat(tempStrArray[1] + '.' + tempStrArray[3], 10);
        gpsDu[1] = num1 + num2 / 60;
        gpsDu[1] = gpsDu[1] * flag;
        gpsDu[0] = gpsDir;
    }
    return gpsDu;
}

/**
 * 注：1、将小数点格式即度格式转换成gps数据中的DMS格式: 39.9288888885->W 39°55′44″
注： 2、其中： W S N E 分别表示经纬度方向
 * @param {*} duStr 
 * @param {*} duDir 
 */
function duToGpsDMS(duStr, duDir) {
    duStr = duStr.toLowerCase();
    duStr = duStr.replace(/\s+/g, "");
    duDir = duDir.toUpperCase();
    var strLength = duStr.length;
    var tempString = "";
    var tempStrArray = new Array();
    var tempCount = 0;
    var tempPointFlag = 0;
    var gpsDMS;
    for (var i = 0; i <= strLength; i++) {
        if (duStr[i] >= '0' && duStr[i] <= '9') {
            tempString += duStr[i];
            continue;
        } else if (duStr[i] == '.') {
            tempStrArray[tempCount] = tempString;
            tempString = "";
            tempCount++;
            tempStrArray[tempCount] = '.';
            tempPointFlag = 1;
            tempCount++;
        } else if (tempString.length > 0) {
            tempStrArray[tempCount] = tempString;
            tempString = "";
            tempCount++;
        }
    }
    if (tempPointFlag == 1) {
        var num1 = tempStrArray[0];
        var num2 = parseFloat('0' + tempStrArray[1] + tempStrArray[2], 10) * 60;
        var num3 = parseInt(parseFloat((num2 - parseInt(num2, 10)) * 60, 10), 10);
        num2 = parseInt(num2, 10);
        console.log(tempStrArray);
        console.log(num1 + "   " + num2 + "   " + num3 + " ");
        gpsDMS = duDir + " " + num1 + "°" + num2 + "′" + num3 + "″";
        //gpsDMS=+num1+"°"+num2+"′"+num3+"″"+" "+duDir;
        // console.log(gpsDMS);
    }
    return gpsDMS;
}

/**
 * 注：1、将小数点格式即度格式转换成gps数据中的DM格式: 39.9288888885->W 39°55.7333′
注： 2、其中： W S N E 分别表示经纬度方向
 * @param {*} duStr 
 * @param {*} duDir 
 */
function duToGpsDM(duStr, duDir) {
    duStr = duStr.toLowerCase();
    duStr = duStr.replace(/\s+/g, "");
    duDir = duDir.toUpperCase();
    var strLength = duStr.length;
    var tempString = "";
    var tempStrArray = new Array();
    var tempCount = 0;
    var tempPointFlag = 0;
    var gpsDM;
    for (var i = 0; i <= strLength; i++) {
        if (duStr[i] >= '0' && duStr[i] <= '9') {
            tempString += duStr[i];
            continue;
        } else if (duStr[i] == '.') {
            tempStrArray[tempCount] = tempString;
            tempString = "";
            tempCount++;
            tempStrArray[tempCount] = '.';
            tempPointFlag = 1;
            tempCount++;
        } else if (tempString.length > 0) {
            tempStrArray[tempCount] = tempString;
            tempString = "";
            tempCount++;
        }
    }
    if (tempPointFlag == 1) {
        var num1 = tempStrArray[0];
        var num2 = parseFloat('0' + tempStrArray[1] + tempStrArray[2], 10) * 60;
        gpsDM = duDir + " " + num1 + "°" + num2 + "′";
        //gpsDM=+num1+"°"+num2+"′"+" "+duDir;
        // console.log(gpsDM);
    }
    return gpsDM;
}

export { ToDegrees, ToDigital, gpsToDu }