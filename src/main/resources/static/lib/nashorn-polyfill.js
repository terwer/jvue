/**
 * poli-fill the following
 * self,global,process,console,Object.assign
 * setTimeout,clearTimeout,setImmediate,clearImmediate,setInterval,clearInterval
 * XMLHttpRequest
 */
var self = this;
// 模拟global
var global = this;

// 模拟window
var window = {
    navigator: {
        userAgent: "Chrome"
    }
};
global.window = window;

// 模拟process
var process = {
    env: {
        VUE_ENV: "server",
        NODE_ENV: "production"
    },
    nextTick: function (fn) {
        global.setTimeout(fn, 0);
    }
};
global.process = process;

// 模拟console
var console = {};
console.debug = print;
console.warn = print;
console.log = print;
console.error = print;
console.trace = print;
console.assert = print;
global.console = console;

// 模拟Object.assign
Object.assign = function (t) {
    for (var s, i = 1, n = arguments.length; i < n; i++) {
        s = arguments[i];
        for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
            t[p] = s[p];
    }
    return t;
};

/*
 Source is originated from https://github.com/sabren/java-XmlHttpRequest
 Articles about Nashorn:
 - https://blog.codecentric.de/en/2014/06/project-nashorn-javascript-jvm-polyglott/
 How it work:
  in https://github.com/morungos/java-xmlhttprequest, it uses Timer to run setTimeout and setInterval task,
  but they are run in a separate thread of the Timer creates that is different with the main JavaScript thread.
  This implementation uses ScheduledExecutorService instead of Timer so the threads for task scheduling can be
  reused instead of each JavasScript thread create a Timer thread when using Timer.
  And most important thing is this adds global.nashornEventLoop and scheduled tasks only add function callback
  object in eventLoop (ArrayQueue), and it is main JavaScript thread to run these function callback by calling
  `global.nashornEventLoop.process();` at the end of JavaScript Application. It is just like browser or NodeJS
  that event loop is called when the main stack is cleared.
  When runs on server with Promise, remember to call `nashornEventLoop.process()` when waiting for Promise by
  Thread.sleep(), and call `nashornEventLoop.reset()` if server thread (e.g. Servlet thread) decides to be
  timeout so that eventLoop will be clean for next request.
 */
(function nashornEventLoopMain(context) {
    'use strict';

    var Thread = Java.type('java.lang.Thread');
    var Phaser = Java.type('java.util.concurrent.Phaser');
    var ArrayDeque = Java.type('java.util.ArrayDeque');
    var HashMap = Java.type('java.util.HashMap');
    var TimeUnit = Java.type("java.util.concurrent.TimeUnit");
    var Runnable = Java.type('java.lang.Runnable');

    var globalTimerId;
    var timerMap;
    var eventLoop;
    var phaser = new Phaser();

    // __NASHORN_POLYFILL_TIMER__ type is ScheduledExecutorService
    var scheduler = context.__NASHORN_POLYFILL_TIMER__;

    resetEventLoop();

    // console.log('main javasript thread ' + Thread.currentThread().getName());

    function resetEventLoop() {
        globalTimerId = 1;
        if (timerMap) {
            timerMap.forEach(function (key, value) {
                value.cancel(true);
            });
        }
        timerMap = new HashMap();
        eventLoop = new ArrayDeque();
    }

    function waitForMessages() {
        phaser.register();
        var wait = !(eventLoop.size() === 0);
        phaser.arriveAndDeregister();

        return wait;
    }

    function processNextMessages() {
        var remaining = 1;
        while (remaining) {
            // console.log('eventLoop size ' + eventLoop.size() + 'in thread ' + Thread.currentThread().getName());
            phaser.register();
            var message = eventLoop.removeFirst();
            remaining = eventLoop.size();
            phaser.arriveAndDeregister();

            var fn = message.fn;
            var args = message.args;

            try {
                // console.log('processNextMessages in thread ' + Thread.currentThread().getName());
                fn.apply(context, args);
            } catch (e) {
                console.trace(e);
                console.trace(fn);
                console.trace(args);
            }
        }
    }

    context.nashornEventLoop = {
        process: function () {
            console.log('nashornEventLoop.process is called in thread ' + Thread.currentThread().getName());
            while (waitForMessages()) {
                processNextMessages();
            }
        },
        reset: resetEventLoop
    };

    function createRunnable(fn, timerId, args, repeated) {
        var Runner = Java.extend(Runnable, {
            run: function () {
                try {
                    var phase = phaser.register();
                    eventLoop.addLast({
                        fn: fn,
                        args: args
                    });
                    console.log('TimerTask add one event, and eventLoop size is:' + eventLoop.size() + ' in thread ' + Thread.currentThread().getName());
                } catch (e) {
                    console.trace(e);
                } finally {
                    if (!repeated) timerMap.remove(timerId);
                    phaser.arriveAndDeregister();
                }
            }
        });
        return new Runner();
    }

    var setTimeout = function (fn, millis /* [, args...] */) {
        var args = [].slice.call(arguments, 2, arguments.length);

        var timerId = globalTimerId++;
        var runnable = createRunnable(fn, timerId, args, false);

        var task = scheduler.schedule(runnable, millis, TimeUnit.MILLISECONDS);
        timerMap.put(timerId, task);

        return timerId;
    };

    var setImmediate = function (fn /* [, args...] */) {
        var args = [].slice.call(arguments, 1, arguments.length);
        return setTimeout(fn, 0, args);
    };

    var clearImmediate = function (timerId) {
        clearTimeout(timerId);
    };

    var clearTimeout = function (timerId) {
        var task = timerMap.get(timerId);
        if (task) {
            task.cancel(true);
            timerMap.remove(timerId);
        }
    };

    var setInterval = function (fn, delay /* [, args...] */) {
        var args = [].slice.call(arguments, 2, arguments.length);

        var timerId = globalTimerId++;
        var runnable = createRunnable(fn, timerId, args, true);
        var task = scheduler.scheduleWithFixedDelay(runnable, delay, delay, TimeUnit.MILLISECONDS);
        timerMap.put(timerId, task);

        return timerId;
    };

    var clearInterval = function (timerId) {
        clearTimeout(timerId);
    };

    var XMLHttpRequest = function () {
        var method, url, async, user, password, headers = {};

        var that = this;

        this.onreadystatechange = function () {
        };

        this.readyState = 0;
        this.response = null;
        this.responseText = null;
        this.responseType = '';
        this.status = null;
        this.statusText = null;
        this.timeout = 0; // no timeout by default
        this.ontimeout = function () {
        };
        this.withCredentials = false;
        this.requestBuilder = null;

        this.abort = function () {

        };

        this.getAllResponseHeaders = function () {

        };

        this.getResponseHeader = function (key) {

        };

        this.setRequestHeader = function (key, value) {
            headers[key] = value;
        };

        this.open = function (_method, _url, _async, _user, _password) {
            this.readyState = 1;

            method = _method;
            url = _url;

            async = _async === false ? false : true;

            user = _user || '';
            password = _password || '';

            this.requestBuilder = RequestBuilder.create(_method);
            this.requestBuilder.setUri(_url);

            setTimeout(this.onreadystatechange, 0);
        };

        this.send = function (data) {
            phaser.register();
            var that = this;

            var clientBuilder = HttpAsyncClientBuilder.create();
            var httpclient = clientBuilder.build();
            httpclient.start();

            var callback = new FutureCallback({
                completed: function (response) {

                    that.readyState = 4;

                    var body = org.apache.http.util.EntityUtils.toString(response.getEntity(), 'UTF-8');
                    that.responseText = that.response = body;

                    if (that.responseType === 'json') {
                        try {
                            that.response = JSON.parse(that.response);
                        } catch (e) {

                            // Store the error
                            finalException = e;

                            context.shutdown();
                        }
                    }

                    if (finalException) {
                        return;
                    }

                    var statusLine = response.getStatusLine();
                    that.status = statusLine.getStatusCode();
                    that.statusText = statusLine.getReasonPhrase();

                    context.setTimeout(that.onreadystatechange, 0);

                    phaser.arriveAndDeregister();
                },
                cancelled: function () {
                    System.err.println("Cancelled");
                },
                failed: function (e) {

                    that.readyState = 4;
                    that.status = 0;
                    that.statusText = e.getMessage();
                    context.setTimeout(that.onreadystatechange, 0);

                    phaser.arriveAndDeregister();
                }
            });

            httpclient.execute(this.requestBuilder.build(), null, callback);
        };
    };

    context.setTimeout = setTimeout;
    context.clearTimeout = clearTimeout;
    context.setImmediate = setImmediate;
    context.clearImmediate = clearImmediate;
    context.setInterval = setInterval;
    context.clearInterval = clearInterval;
    context.XMLHttpRequest = XMLHttpRequest;

})(typeof global !== "undefined" && global || typeof self !== "undefined" && self || this);