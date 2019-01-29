import { calendar } from "./Calendar";

const lunar = calendar.solar2lunar();

/**
 * 根据日期获取星期几
 * @returns {string} 星期
 */
var getWeekByDay = function() {
  var day = new Date();
  var today = [
    "星期日",
    "星期一",
    "星期二",
    "星期三",
    "星期四",
    "星期五",
    "星期六"
  ]; //创建星期数组
  // console.log(today[day.getDay()]);
  return today[day.getDay()]; //返一个星期中的某一天，其中0为星期日
};

/**
 * 获取星期
 * @returns {string}
 */
var getClientTime = function() {
  //获取客户端时间
  var now = new Date();
  var currDatetime = "";
  // currDatetime += now.getFullYear() + "年";
  // currDatetime +=
  //   now.getMonth() + 1 > 9 ? now.getMonth() + 1 : "0" + (now.getMonth() + 1);
  // currDatetime += "月";
  // currDatetime += now.getDate() > 9 ? now.getDate() : "0" + now.getDate();
  // currDatetime += "日 ";
  currDatetime += now.getHours() > 9 ? now.getHours() : "0" + now.getHours();
  currDatetime += ":";
  currDatetime +=
    now.getMinutes() > 9 ? now.getMinutes() : "0" + now.getMinutes();
  currDatetime += ":";
  currDatetime +=
    now.getSeconds() > 9 ? now.getSeconds() : "0" + now.getSeconds();
  return currDatetime;
};

var getPopTime = function() {
  var popTime =
    "阳历：" +
    lunar.cYear +
    "年" +
    lunar.cMonth +
    "月" +
    lunar.cDay +
    "日（" +
    lunar.astro +
    "）";
  return popTime;
};

var getTradTime = function() {
  var tradTime = "农历：" + lunar.lYear + "年" + lunar.IMonthCn + lunar.IDayCn;
  return tradTime;
};

var getShengXiao = function() {
  var shengxiao =
    lunar.gzYear +
    "年" +
    lunar.gzMonth +
    "月" +
    lunar.gzDay +
    "日（" +
    lunar.Animal +
    "年）";
  return shengxiao;
};

export { getClientTime, getPopTime, getTradTime, getShengXiao, getWeekByDay };
