// the environment that will be considered when building the skin, either `production` or `development`
const nodeEnv = process.env.NODE_ENV || "development";
const isProduction = nodeEnv === "production";
const pkg = require("./package.json");

console.log("nodeEnv=>", nodeEnv);

// This is page seo
const seo = {
  title: isProduction ? "{{ title }}" : [pkg.name, " v", pkg.version].join(""),
  meta: {
    keywords: isProduction ? "{{ meta.keywords }}" : pkg.keywords.join(),
    description: isProduction ? "{{meta.description}}" : pkg.description
  }
};

module.exports = {
  nodeEnv: nodeEnv,
  isProduction: isProduction,
  seo: seo
};
