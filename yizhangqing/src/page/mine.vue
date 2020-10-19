<template>
  <div class="main">
    <van-nav-bar title="我的" right-text="按钮" @click-left="onClickLeft" @click-right="onClickRight">
      <template #right>
        <van-icon name="setting-o" />
      </template>
    </van-nav-bar>
    <van-cell-group>
      <van-cell is-link>
        <van-image round width="4rem" height="4rem" src="https://img.yzcdn.cn/vant/cat.jpeg" />
        <div style="float: right;
    margin-right: 68%;">去登陆</div>
      </van-cell>
    </van-cell-group>

    <van-cell-group class="panel">
      <van-grid :column-num="3">
        <van-grid-item   icon="gift-o" text="松果中心" />
        <van-grid-item   icon="balance-list-o" text="我的月报" />
        <van-grid-item   icon="award-o" text="赚松果" />
        <van-grid-item   icon="gold-coin-o" text="我的零钱" />
        <van-grid-item   icon="cash-back-record" text="赚点钱" />
        <van-grid-item   icon="medal-o" text="我的勋章" />
      </van-grid>
    </van-cell-group>
    <van-cell-group class="panel">
      <van-cell icon="location-o" title="手势密码" is-link url="/vant/mobile.html" />
      <van-cell icon="location-o" title="修改密码" is-link to="index" />
      <van-cell icon="location-o" title="密保问题设置" is-link to="index" />
    </van-cell-group>

    <van-cell-group class="panel">
      <van-cell icon="location-o" title="手机绑定" is-link url="/vant/mobile.html" />
      <van-cell icon="location-o" title="邮箱绑定" is-link to="index" />
      <van-cell icon="location-o" title="三方账号绑定" is-link to="index" />
      <van-cell icon="location-o" title="账单导出" is-link to="index" />
    </van-cell-group>

    <van-cell-group class="panel">
      <van-cell icon="location-o" title="账单发送" is-link url="/vant/mobile.html" />
      <van-cell icon="location-o" title="邀请好友" is-link to="index" />
    </van-cell-group>
  </div>
</template>

<script>
import {
  Cell,
  CellGroup,
  Image as VanImage,
  NavBar,
  Icon,
  Grid,
  GridItem,
} from "vant";
export default {
  name: "App",
  data() {
    return {
      date: "826",
      year: "2020",
      month: "8",
      rate: 190,
      currentRate: 67,
      gradientColor: {
        "0%": "#3fecff",
        "100%": "#6149f6",
      },
      show: false,
    };
  },
  components: {
    //   "van-cell":Cell,
    "van-cell": Cell,
    "van-cell-group": CellGroup,
    "van-image": VanImage,
    "van-nav-bar": NavBar,
    "van-icon": Icon,
    "van-grid-item": GridItem,
    "van-grid": Grid,
  },
  mounted() {},
  methods: {},
};
</script>

<style>
.main {
  height: 100%;
  padding: 0;
  margin: 0;
  background-color: #9697991f;
}

.panel {
  border-radius: 12px;
  margin: 0.7vh;
  padding: 0.4vh;
}
</style>
