REM %cd%\build\cpm.cmd
REM ncp [source] [dest] [--limit=concurrency limit] [--filter=filter] --stopOnErr

REM clean && mkdir
del "dist/node_modules" /q
mkdir "dist/node_modules"

REM core-js
node node_modules/ncp/bin/ncp node_modules/core-js dist/node_modules/core-js

REM vue-server-renderer
node node_modules/ncp/bin/ncp node_modules/he dist/node_modules/he
node node_modules/ncp/bin/ncp node_modules/lodash._reinterpolate dist/node_modules/lodash._reinterpolate
node node_modules/ncp/bin/ncp node_modules/lodash.template dist/node_modules/lodash.template
node node_modules/ncp/bin/ncp node_modules/lodash.templatesettings dist/node_modules/lodash.templatesettings
node node_modules/ncp/bin/ncp node_modules/resolve dist/node_modules/resolve
node node_modules/ncp/bin/ncp node_modules/serialize-javascript dist/node_modules/serialize-javascript
node node_modules/ncp/bin/ncp node_modules/vue-server-renderer dist/node_modules/vue-server-renderer
del dist/node_modules/vue-server-renderer/node_modules /q

REM vue
node node_modules/ncp/bin/ncp node_modules/vue dist/node_modules/vue

REM vue-router
node node_modules/ncp/bin/ncp node_modules/vue-router dist/node_modules/vue-router

REM axios
node node_modules/ncp/bin/ncp node_modules/axios dist/node_modules/axios
node node_modules/ncp/bin/ncp node_modules/is-buffer dist/node_modules/is-buffer
node node_modules/ncp/bin/ncp node_modules/follow-redirects dist/node_modules/follow-redirects

REM bootstrap-vue
node node_modules/ncp/bin/ncp node_modules/bootstrap-vue dist/node_modules/bootstrap-vue
del "dist/node_modules/bootstrap-vue/node_modules" /q
del "dist/node_modules/bootstrap-vue/src" /q

REM circular-json
node node_modules/ncp/bin/ncp node_modules/circular-json dist/node_modules/circular-json

REM source-map
node node_modules/ncp/bin/ncp node_modules/source-map dist/node_modules/source-map

REM url-search-params-polyfill
node node_modules/ncp/bin/ncp node_modules/url-search-params-polyfill dist/node_modules/url-search-params-polyfill

REM vue-hljs
node node_modules/ncp/bin/ncp node_modules/vue-hljs dist/node_modules/vue-hljs
node node_modules/ncp/bin/ncp node_modules/highlight.js dist/node_modules/highlight.js

REM element-ui
node node_modules/ncp/bin/ncp node_modules/element-ui dist/node_modules/element-ui
node node_modules/ncp/bin/ncp node_modules/deepmerge dist/node_modules/deepmerge
node node_modules/ncp/bin/ncp node_modules/resize-observer-polyfill dist/node_modules/resize-observer-polyfill
node node_modules/ncp/bin/ncp node_modules/throttle-debounce dist/node_modules/throttle-debounce
node node_modules/ncp/bin/ncp node_modules/normalize-wheel dist/node_modules/normalize-wheel
node node_modules/ncp/bin/ncp node_modules/async-validator dist/node_modules/async-validator
node node_modules/ncp/bin/ncp node_modules/babel-runtime dist/node_modules/babel-runtime
node node_modules/ncp/bin/ncp node_modules/babel-helper-vue-jsx-merge-props dist/node_modules/babel-helper-vue-jsx-merge-props