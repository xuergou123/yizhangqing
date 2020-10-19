<template>
  <van-tabbar v-model="active"  :route= "route">
  <van-tabbar-item name="home" icon="home-o" to="/wallet">钱包</van-tabbar-item>
  <van-tabbar-item name="category" icon="search" to="/chart">图表</van-tabbar-item>
  <van-tabbar-item name="shop-cart" icon="friends-o" to="/detail">明细</van-tabbar-item>
  <van-tabbar-item name="setting" icon="setting-o" to="/mine">我的</van-tabbar-item>
</van-tabbar>
</template>

<script>
import { Tabbar, TabbarItem  } from 'vant';

export default {
  name: 'App',
  components: {
    [Tabbar.name]: Tabbar,
    [TabbarItem.name]: TabbarItem,
  },
  data() {
  return {
    active: 'home',
    route:true
  }
},
}
</script>

<style>

</style>
