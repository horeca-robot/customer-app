export default class {
  static set(key, object) {
    localStorage.setItem(key, JSON.stringify(object));
  }

  static load(key) {
    return JSON.parse(localStorage.getItem(key));
  }

  static update(key, object) {
    const tempObject = this.load(key);
    if (tempObject) {
      this.set(key, { ...tempObject, ...object });
    } else {
      this.set(key, object);
    }
  }
}
