import storage from '@/store/storage'

const app = {
  state: {
    menu: '',
    sidebar: {
      opened: !+storage.get('sidebarStatus')
    },
    device: 'desktop'
  },
  mutations: {
    setMenu(state, menu) {
      state.menu = menu
    },
    TOGGLE_SIDEBAR: state => {
      if (state.sidebar.opened) {
        storage.set('sidebarStatus', 1)
      } else {
        storage.set('sidebarStatus', 0)
      }
      state.sidebar.opened = !state.sidebar.opened
    },
    CLOSE_SIDEBAR: (state) => {
      storage.set('sidebarStatus', 1)
      state.sidebar.opened = false
    },
    TOGGLE_DEVICE: (state, device) => {
      state.device = device
    }
  },
  actions: {
    setMenu(context, menu) {
      context.commit('setMenu', menu);
    },
    ToggleSideBar: ({ commit }) => {
      commit('TOGGLE_SIDEBAR')
    },
    CloseSideBar({ commit } ) {
      commit('CLOSE_SIDEBAR')
    },
    ToggleDevice({ commit }, device) {
      commit('TOGGLE_DEVICE', device)
    }
  }
}

export default app
