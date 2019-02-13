// the environment that will be considered when building the skin, either `production` or `development`
const nodeEnv = process.env.NODE_ENV || "development";

console.log("nodeEnv=>", nodeEnv);

module.exports = {
  nodeEnv: nodeEnv,
  isProduction: nodeEnv === "production"
};
