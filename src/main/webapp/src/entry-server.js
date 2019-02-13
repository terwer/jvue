import { createApp } from "./app";

// This exported function will be called by `bundleRenderer`.
// return a Promise that resolves to the app instance.
export default context => {
  return new Promise((resolve, reject) => {
    console.log("entry server received context=>", context);
    const { app } = createApp(context);
    resolve(app);
  });
};
