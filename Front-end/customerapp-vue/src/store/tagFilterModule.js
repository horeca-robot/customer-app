import localStorageHelper from "../helpers/localStorageHelper";

export default {
  namespaced: true,
  state: () => ({
    selectedFilters: localStorageHelper.load("tagFilter") ? localStorageHelper.load("tagFilter").selectedFilters : [],
  }),
  mutations: {
    setSelectedFilters(state, selectedFilters) {
      state.selectedFilters = selectedFilters;
    },
  },
  actions: {
    setSelectedFilters({ state, commit }, selectedFilters) {
      commit("setSelectedFilters", selectedFilters);
      localStorageHelper.update("tagFilter", state);
    },
  },
  getters: {
    selectedFilters: (state) => {
      return state.selectedFilters;
    },
  },
};
