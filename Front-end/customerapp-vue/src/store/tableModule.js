import localStorageHelper from "../helpers/localStorageHelper";

export default {
  namespaced: true,
  state: () => ({
    tableId: localStorageHelper.load("tableId"),
  }),
  mutations: {
    loadState(state, loadedState) {
      this.state.tableModule = { ...state, ...loadedState };
    },
    setTableId(state, tableId) {
      state.tableId = tableId;
    },
  },
  actions: {
    setTable({ state, commit }, tableId) {
      commit("setTableId", tableId);
      localStorageHelper.update("table", state);
    },
    loadState({ commit }) {
      commit("loadState", localStorageHelper.load("table"));
    },
  },
  getters: {
    tableId: (state) => {
      return state.tableId;
    },
  },
};
