const tokenKey = window.global.tokenKey;
const userKey = 'user';
const dictKey = 'dicts';
const menusKey = 'menus';
const expire = 1000 * 60 * 60 * 24; //过期时间

/**
 * 浏览器Local Storage操作类
 * 主要用来存储对象
 */
class Storage {
  constructor() {
    this.storage = window.localStorage
    this.prefix = "tr_";//存储前缀名
  }

  /**
   * 新增
   * @param key
   * @param value 对象将被转成json字符串存储
   * @param fn
   */
  insertObject(key, value, fn) {
    try {
      value = JSON.stringify(value)
    } catch (e) {

    }
    this.storage.setItem(this.prefix + key, value)
    fn && fn()
  }

  set(key, value, fn) {
    this.storage.setItem(this.prefix + key, value)
    fn && fn()
  }

  /**
   * 查询
   * @param key
   * @param fn
   */
  selectObject(key, fn) {
    if (!key) {
      throw new Error('can not found the key')
    }
    if (typeof key === 'object') {
      throw new Error(' the key can not be an object')
    }
    var value = this.storage.getItem(this.prefix + key)
    if (value !== null) {
      try {
        value = JSON.parse(value)
      } catch (e) {

      }
    }
    return value
  }

  get(key, fn) {
    if (!key) {
      throw new Error('can not found the key')
    }
    if (typeof key === 'object') {
      throw new Error(' the key can not be an object')
    }
    return this.storage.getItem(this.prefix + key);
  }

  /**
   * 删
   * @param key
   */
  delete(key) {
    this.storage.removeItem(this.prefix + key)
  }

  /**
   * 改
   * @param key
   * @param value
   * @param fn
   */
  update(key, value, fn) {
    this.insert(key, value, fn)
  }

  /**
   * 清除所有
   */
  clear() {
    this.storage.clear();
  }



  /* token相关数据 */
  getToken() {
    let cur = new Date().getTime();
    let tokenStr = this.storage.getItem(this.prefix + tokenKey);
    if (tokenStr) {
      let tokenObj = JSON.parse(tokenStr);
      if (tokenObj.time && ((cur - expire) < tokenObj.time)) {
        return tokenObj.token;
      }
    }
    return false;
  }

  setToken(token) {
    this.insertObject(tokenKey, {
      token: token,
      time: new Date().getTime()
    });
  }

  delToken() {
    this.delete(tokenKey);
  }

  /* 用户相关 */
  getUser() {
    return this.selectObject(this.prefix + userKey) || "";
  }

  setUser(user) {
    this.insertObject(this.prefix + userKey, user);
  }

  removeUser() {
    this.delete(this.prefix + userKey);
  }

  /* 字典相关的数据处理 */
  getDict(pCode) {
    var dicts = this.selectObject(this.prefix + dictKey) || {};
    return dicts[pCode];
  }

  getSelectDict(pCode) {
    var dicts = this.selectObject(this.prefix + dictKey) || {};
    var dict = dicts[pCode];
    var selectData = [];
    if (dict) {
      for (let key in dict) {
        selectData.push({
          value: key, label: dict[key]
        });
      };
    }

    return selectData;
  }


  setDict(dicts) {
    this.insertObject(this.prefix + dictKey, dicts);
  }

  removeDict() {
    this.delete(this.prefix + dictKey);
  }

  setMenus(menus){
    this.insertObject(this.prefix + menusKey, menus);
  }

  getMenus(){
    return this.selectObject(this.prefix + menusKey) || [];
  }

  removeMenus(){
    this.delete(this.prefix + menusKey);
  }
}

export default new Storage()
