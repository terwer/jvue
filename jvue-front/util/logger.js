/**
 * logger.js
 *
 *@author Terwer
 *@version 1.0
 *2019/3/19 17:07
 **/
const isDebugEnabled = process.env.NODE_ENV !== "production";
const isInfoEnabled = true;
const isErrorEnabled = true;

let loggerName = "default";

console.log(
  "isDebugEnabled,isInfoEnabled,isErrorEnabled=>",
  `${isDebugEnabled},${isInfoEnabled},${isErrorEnabled}`
);

const debug = log => {
  if (isDebugEnabled) {
    console.warn("[", loggerName, "]", log);
  }
};

const info = log => {
  if (isInfoEnabled) {
    console.log("[", loggerName, "]", log);
  }
};

const error = log => {
  if (isErrorEnabled) {
    console.error("[", loggerName, "]", log);
  }
};

const logger = {
  debug,
  info,
  error
};

export const getLogger = name => {
  if (name) {
    loggerName = name;
  }
  return logger;
};
