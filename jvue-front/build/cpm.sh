#!/usr/bin/env bash
# dos2unix ./build/cpm.sh && ./build/cpm.sh
# node_modules/ncp/bin/ncp [source] [dest] [--limit=concurrency limit] [--filter=filter] --stopOnErr

# clean & mkdir
rm -rf dist/node_modules
mkdir -p dist/node_modules

# core-js
node_modules/ncp/bin/ncp node_modules/core-js dist/node_modules/core-js

# vue-server-renderer
node_modules/ncp/bin/ncp node_modules/he dist/node_modules/he
node_modules/ncp/bin/ncp node_modules/lodash._reinterpolate dist/node_modules/lodash._reinterpolate
node_modules/ncp/bin/ncp node_modules/lodash.template dist/node_modules/lodash.template
node_modules/ncp/bin/ncp node_modules/lodash.templatesettings dist/node_modules/lodash.templatesettings
node_modules/ncp/bin/ncp node_modules/resolve dist/node_modules/resolve
node_modules/ncp/bin/ncp node_modules/serialize-javascript dist/node_modules/serialize-javascript
node_modules/ncp/bin/ncp node_modules/vue-server-renderer dist/node_modules/vue-server-renderer
rm -rf dist/node_modules/vue-server-renderer/node_modules

# vue
node_modules/ncp/bin/ncp node_modules/vue dist/node_modules/vue

# vue-router
node_modules/ncp/bin/ncp node_modules/vue-router dist/node_modules/vue-router

# axios
node_modules/ncp/bin/ncp node_modules/axios dist/node_modules/axios
node_modules/ncp/bin/ncp node_modules/is-buffer dist/node_modules/is-buffer
node_modules/ncp/bin/ncp node_modules/follow-redirects dist/node_modules/follow-redirects

# bootstrap-vue
node_modules/ncp/bin/ncp node_modules/bootstrap-vue dist/node_modules/bootstrap-vue
rm -rf dist/node_modules/bootstrap-vue/node_modules
rm -rf dist/node_modules/bootstrap-vue/src

# circular-json
node_modules/ncp/bin/ncp node_modules/circular-json dist/node_modules/circular-json

# source-map
node_modules/ncp/bin/ncp node_modules/source-map dist/node_modules/source-map

# url-search-params-polyfill
node_modules/ncp/bin/ncp node_modules/url-search-params-polyfill dist/node_modules/url-search-params-polyfill

# vue-hljs
node_modules/ncp/bin/ncp node_modules/vue-hljs dist/node_modules/vue-hljs
node_modules/ncp/bin/ncp node_modules/highlight.js dist/node_modules/highlight.js

# element-ui
node_modules/ncp/bin/ncp node_modules/element-ui dist/node_modules/element-ui
node_modules/ncp/bin/ncp node_modules/deepmerge dist/node_modules/deepmerge
node_modules/ncp/bin/ncp node_modules/resize-observer-polyfill dist/node_modules/resize-observer-polyfill
node_modules/ncp/bin/ncp node_modules/throttle-debounce dist/node_modules/throttle-debounce
node_modules/ncp/bin/ncp node_modules/normalize-wheel dist/node_modules/normalize-wheel
node_modules/ncp/bin/ncp node_modules/async-validator dist/node_modules/async-validator
node_modules/ncp/bin/ncp node_modules/babel-runtime dist/node_modules/babel-runtime
node_modules/ncp/bin/ncp node_modules/babel-helper-vue-jsx-merge-props dist/node_modules/babel-helper-vue-jsx-merge-props