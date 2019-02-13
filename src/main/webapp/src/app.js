import Vue from "vue";
import App from "./App.vue";

/**
 * Expose a factory function that creates a fresh set of
 * app instances on each call (which is called for each SSR request)
 * @param ssrContext
 * @returns {{app: Vue | CombinedVueInstance<V, object, object, object, Record<never, any>>}}
 */
export function createApp(ssrContext) {
  console.log("ssrContext=>", ssrContext);

  // create the app instance.
  const app = new Vue({
    render: h => h(App)
  });

  // expose the app
  // note we are not mounting the app here, since bootstrapping will be
  // different depending on whether we are in a browser or on the server.
  return { app };
  // return { app, router, store }
}
