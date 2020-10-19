<template>
  <div class="main">
    <van-nav-bar title="钱包" right-text="按钮" @click-left="onClickLeft" @click-right="onClickRight">
      <template #left>
        <van-icon name="add-o" size="23" />
      </template>
      <template #right>
        <van-icon name="records" size="23" />
      </template>
    </van-nav-bar>

    <van-cell-group>
      <van-cell is-link>
        <div>净资产</div>
        <div class="redText">0.00</div>
        <div class="assetdetail">
          <div style="float:left">
            负债：
            <span class="spanText">0.00</span>
          </div>
          <div style="float:right">
            资产：
            <span class="spanText">0.00</span>
          </div>
        </div>
      </van-cell>
    </van-cell-group>

    <van-cell-group style="display:none">
      <van-cell is-link>
        <van-image round width="4rem" height="4rem" src="https://img.yzcdn.cn/vant/cat.jpeg" />
        <div style="float: right;
    margin-right: 68%;">去登陆</div>
      </van-cell>
    </van-cell-group>

    <van-cell-group class="panel" style="display:none">
      <van-grid :column-num="3">
        <van-grid-item icon="gift-o" text="松果中心" />
        <van-grid-item icon="balance-list-o" text="我的月报" />
        <van-grid-item icon="award-o" text="赚松果" />
        <van-grid-item icon="gold-coin-o" text="我的零钱" />
        <van-grid-item icon="cash-back-record" text="赚点钱" />
        <van-grid-item icon="medal-o" text="我的勋章" />
      </van-grid>
    </van-cell-group>
    <div style="font-size: 12px;margin: 10px;color: grey;">我的账户</div>

    <van-collapse v-model="activeName"  accordion>
      <van-collapse-item title="现金账户" name="1" style="margin-bottom: 6px;">
        <van-cell icon="location-o" title="现金" :value=" zero" @click="goTo('crash')"/>
      </van-collapse-item>
      <van-collapse-item title="储蓄卡账户" name="2" style="margin-bottom: 6px;">
        <van-cell icon="location-o" title="储蓄卡" :value=" zero" />
      </van-collapse-item>
      <van-collapse-item title="信用卡账户" name="3" style="margin-bottom: 6px;">
        <van-cell icon="location-o" title="信用卡" :value=" zero" />
      </van-collapse-item>
      <van-collapse-item title="网络账户" name="4" style="margin-bottom: 6px;">
        <van-cell icon="location-o" title="支付宝" :value=" zero" />
        <van-cell icon="location-o" title="微信" :value=" zero" />
      </van-collapse-item>
      <van-collapse-item title="我的借款" name="5" style="margin-bottom: 6px;">
        <van-cell icon="location-o" title="借入" :value=" zero" />
        <van-cell icon="location-o" title="借出" :value=" zero" />
        <van-cell icon="location-o" title="报销" :value=" zero" />
      </van-collapse-item>
    </van-collapse>
  </div>
</template>

<script>

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
      activeName: "1",
      zero: "￥ 0.00",
    };
  },
  components: {

  },
  mounted() {
    this.clickShowData()
  },
  methods: {
    clickShowData() {
      this.$api
        .eggDemo() //$api在main.js里已经引入到vue的原型上，不需要在引入就可以通过this.$api直接使用
        .then((res) => {
          console.log(res)
          alert(res)
        })
        .catch((err) => {
          console.log(err)
        });
    },
    goTo(url){
      this.$router.push(url)
    }
  },
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

.redText {
  font-size: 40px;
  color: #f1627b;
  height: 6vh;
  line-height: 64px;
  padding: 1px;
}

.assertDetail {
  height: 4vh;
  padding-top: 1vh;
}

.spanText {
  color: rgb(241, 98, 123);
  font-size: 17px;
  font-weight: 700;
}
</style>
