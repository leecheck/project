
const types = ['ADCP','CTD','海洋气象','生物化学'];
const type = {
  state: {
    type: '',
  },

  mutations: {
    CHANGE_TYPE: (state,type) => {
      state.type = type;
    }
  },

  actions: {
    changeType({
      commit
    },type) {
      commit('CHANGE_TYPE',type)
    },
  }
}

export default type
