import Vue from 'vue'
import App from './App.vue'
import 'vant/lib/index.css';
import router from './router'
import axios from 'axios'
import demo from './request/apis'
import QS from 'qs'

import {
    Tabbar, TabbarItem, Calendar, Cell, Tag,
    CellGroup,
    Image as VanImage,
    NavBar,
    Icon,
    Grid,
    GridItem,
    Collapse,
    CollapseItem, Circle, Button, Tab, Tabs, Swipe, SwipeItem, NumberKeyboard, Field, DatetimePicker, Popup, ActionSheet, Toast, Dialog
} from 'vant';

[Tabbar, TabbarItem, Calendar, Cell, Tag,
    CellGroup,
    VanImage,
    NavBar,
    Icon,
    Grid,
    GridItem,
    Collapse,
    CollapseItem, Tag, Circle, Button, Tab, Tabs, Swipe, SwipeItem, NumberKeyboard, Field, DatetimePicker, Popup, ActionSheet, Toast, Dialog].forEach(x => Vue.use(x))

// Vue.use(Tabbar).use(TabbarItem).use(Calendar).use(Cell);
Vue.config.productionTip = false

Vue.prototype.$axios = axios;
Vue.prototype.$api = demo;

Vue.use(QS);//,request


function dateFormat(fmt, date) {
    let ret
    let weekDay = ["周一", "周一", "周二", "周三", "周四", "周五", "周六", "周日"]
    const opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString(),         // 秒
        "W+": weekDay[date.getDay()].toString()              // 周
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    }
    for (let k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        }
    }
    return fmt
}

Vue.prototype.$dateFormat = dateFormat;

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')



