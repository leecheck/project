
const search = {
  state: {
    keyword: '',
  },

  mutations: {
    CHANGE_keyword: (state,keyword) => {
      state.keyword = keyword;
    }
  },

  actions: {
    changeKeyword({
      commit
    },keyword) {
      commit('CHANGE_keyword',keyword)
    },
  }
}

export default search
