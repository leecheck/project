const getters = { //实时监听state值的变化(最新状态)
  sidebar: state => state.app.sidebar,
  device: state => state.app.device,
  menu: state => state.app.menu,
  token: state => state.user.token,
  user: state => state.user.user,
  type:state => state.type.type,
  keyword:state => state.search.keyword,
}
export default getters
