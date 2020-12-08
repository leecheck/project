// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import store from './store/index'
import * as util from "@/util/util"
import 'vue2-animate/dist/vue2-animate.css';
import '../static/iconfont/iconfont.css'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App';
import router from './router';
import VueRouter from 'vue-router';//引入路由模块
import storage from '@/store/storage'
import * as config from "@/config/config";
import './filters/filter'
import {notify} from "./util/ui"

import "../static/iconfont/iconfont.js"

import VueBus from './system/VueBus';

Vue.use(VueRouter);//加载路由模块
Vue.config.productionTip = false
Vue.prototype.$storage = storage;
Vue.prototype.$config = config;
Vue.prototype.$notify = notify;

import {
  Cascader,
  Radio,
  RadioGroup,
  RadioButton,
  Container,
  Header,
  Aside,
  Main,
  Footer,
  Row,
  Col,
  Pagination,
  Dialog,
  Button,
  Input,
  Table,
  TableColumn,
  Form,
  FormItem,
  Dropdown,
  Breadcrumb,
  BreadcrumbItem,
  Autocomplete,
  DropdownMenu,
  DropdownItem,
  Menu,
  Submenu,
  MenuItem,
  MenuItemGroup,
  MessageBox,
  Message,
  Loading,
  Notification,
  DatePicker,
  TimeSelect,
  TimePicker,
  Tabs,
  Select,
  Option,
  OptionGroup,
  TabPane,
  Tooltip,
  Progress,
  Carousel,
  CarouselItem,
  Upload,
  Popover,
  Tag,
  Tree,
  Image
} from 'element-ui'
Vue.use(Cascader)
Vue.use(Radio)
Vue.use(RadioGroup)
Vue.use(RadioButton)
Vue.use(Tag)
Vue.use(Popover)
Vue.use(Container)
Vue.use(Header)
Vue.use(Aside)
Vue.use(Main)
Vue.use(Footer)
Vue.use(Row)
Vue.use(Col)
Vue.use(Pagination)
Vue.use(Dialog)
Vue.use(Button)
Vue.use(Form)
Vue.use(Input)
Vue.use(Table)
Vue.use(TableColumn)
Vue.use(FormItem)
Vue.use(Dropdown)
Vue.use(Autocomplete)
Vue.use(DropdownMenu)
Vue.use(DropdownItem)
Vue.use(Breadcrumb)
Vue.use(BreadcrumbItem)
Vue.use(Menu)
Vue.use(Submenu)
Vue.use(MenuItem)
Vue.use(MenuItemGroup)
Vue.use(Loading.directive)
Vue.use(DatePicker)
Vue.use(TimeSelect)
Vue.use(TimePicker)
Vue.use(Tabs)
Vue.use(TabPane)
Vue.use(Tooltip)
Vue.use(Progress)
Vue.use(Carousel)
Vue.use(CarouselItem)
Vue.use(Upload)
Vue.use(Select)
Vue.use(Option)
Vue.use(OptionGroup)
Vue.use(Popover)
Vue.use(Tag)
Vue.use(Tree)
Vue.use(Image)
Vue.prototype.$loading = Loading.service
Vue.prototype.$msgbox = MessageBox
Vue.prototype.$alert = MessageBox.alert
Vue.prototype.$confirm = MessageBox.confirm
Vue.prototype.$prompt = MessageBox.prompt
Vue.prototype.$notify = Notification
Vue.prototype.$message = Message

Vue.use(VueBus);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {
    App
  },
  template: '<App/>'
})

