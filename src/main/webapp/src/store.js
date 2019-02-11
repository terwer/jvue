import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

// 假定我们有一个可以返回 Promise 的通用 API（请忽略此 API 具体实现细节）
import api from "./api";

export function createStore() {
  return new Vuex.Store({
    state: {
      items: {}
    },
    actions: {
      fetchItem({ commit }) {
        // `store.dispatch()` 会返回 Promise，
        // 以便我们能够知道数据在何时更新
        console.log("store fetchItem from api");
        return api.getPostList().then(item => {
          const id = "getPostList";
          const data = item.data;
          commit("setItem", { id, data });
          console.log("after set");
          // console.log(item.data);
        });
      }
    },
    mutations: {
      setItem(state, { id, data }) {
        Vue.set(state.items, id, data);
        console.log("item =>", data);
        console.log("store setItem success");
      }
    }
  });
}
