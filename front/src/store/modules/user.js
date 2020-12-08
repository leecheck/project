import storage from '@/store/storage'
const user = {
  state: {
    user: {},
  },

  mutations: {
    BIND_LOGIN(state, loginfo) {
      state.user = loginfo.user;
      storage.setUser(loginfo.user);
      storage.setToken(loginfo.token);
      storage.setDict(loginfo.dicts);
      storage.setMenus(loginfo.menus);
    },
    BIND_LOGOUT: (state) => {
      state.user = undefined;
      storage.removeUser();
    }
  },

  actions: {
    bindLogin({
      commit
    },loginfo) {
      commit('BIND_LOGIN',loginfo)
    },
    bindLogout({
      commit
    }) {
      commit('BIND_LOGOUT')
    }
  }
}

export default user
