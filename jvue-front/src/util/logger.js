/**
 * logger
 *
 * @author Terwer
 * @version 1.0
 * 19-2-27 下午11:22
 **/
global.loggerName = "default";
const isDebugEnabled = process.env.VUE_APP_DEBUG === "true";
const isInfoEnabled = process.env.VUE_APP_INFO === "true";
const isErrorEnabled = process.env.VUE_APP_ERROR === "true";
console.log(
  "isDebugEnabled,isInfoEnabled,isErrorEnabled=>",
  `${isDebugEnabled},${isInfoEnabled},${isErrorEnabled}`
);

const debug = log => {
  if (isDebugEnabled) {
    console.warn("[", global.loggerName, "]", log);
  }
};

const info = log => {
  if (isInfoEnabled) {
    console.log("[", global.loggerName, "]", log);
  }
};

const error = log => {
  if (isErrorEnabled) {
    console.error("[", global.loggerName, "]", log);
  }
};

let logger = {
  debug,
  info,
  error
};

export const getLogger = name => {
  global.loggerName = name;
  return logger;
};
