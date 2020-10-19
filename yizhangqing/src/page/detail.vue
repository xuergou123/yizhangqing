<template>
  <div class="wallet">
    <!-- <van-cell title="选择单个日期" :value="date" @click="show = true" /> -->
    <van-nav-bar title="我的账单" left-arrow>
      <template #left>
        <van-icon name="bars" size="18" />
        <p style="color:#1989fa">日常</p>
      </template>
      <template #title>
        <van-tag
          type="primary"
          plain
          color="grey"
          size="large"
          @click="show = true"
        >{{ year }}年,{{month}}月</van-tag>
      </template>
      <template #right>
        <van-icon name="search" size="18" />
      </template>
    </van-nav-bar>
    <div></div>
    <van-calendar v-model="show" @confirm="onConfirm" position="top" />

    <div @click="toBudgetPage" class="circleDiv">
      <van-circle
        v-model="currentRate"
        :rate="100"
        :stroke-width="60"
        size="150px"
        :color="gradientColor"
        layer-color="#ebedf0"
        text="预算剩余 3000"
      >
        <template #default>
          <van-image
            round
            width="90"
            height="90"
            fill="none"
            class="imageDiv"
            :src="require('../assets/image/lang.jpg')"
          />
        </template>
      </van-circle>
    </div>
    <van-grid class="incomepay">
      <van-grid-item text />
      <van-grid-item>收入: {{income}}</van-grid-item>

      <div class="yaxis"></div>
      <van-grid-item>支出: {{payment}}</van-grid-item>
    </van-grid>
    <div style="height: 3vh;margin: 1px 1.8vh 20px 1.8vh;margin-top:2px">
      <div class="datemoney">
        <div class="date">
          <div style="padding-top: 6%;">{{currentDate}}</div>
        </div>
        <div class="money">支出 {{money}}</div>
      </div>
      <div class="divider"></div>
      <div class="triangle"></div>
    </div>

    <van-cell-group>
      <van-cell
        v-for="(item,index) in billData"
        :title="item.COST_TYPE"
        :value="item.COST"
        :key="index"
        :icon="judgeIcon(item.COST_TYPE)"
      />
    </van-cell-group>

    <van-icon name="add" class="addButton" size="3rem" @click="goToCostType()" />
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
      actions: [{ name: "选项一" }, { name: "选项二" }, { name: "选项三" }],
      showAction: true,
      income: 0.0,
      payment: 5783.0,
      userid: "11111",
      page: 1,
      size: 10,
      currentDate: "",
      money: "5783.00",
      billData: "",
    };
  },
  mounted() {
    this.date = this.onConfirm(new Date());
    this.initRate();
    this.initData();
    this.currentDate = this.$dateFormat("mm月dd日 W", new Date());
  },
  methods: {
    judgeIcon(costType) {
      var icon = "";
      switch (costType) {
        case 1:
          icon = "";
          break;
        case 2:
          icon = "";
          break;
        case 3:
          icon = "";
          break;
        case 4:
          icon = "";
          break;
        case 5:
          icon = "";
          break;
        default:
          icon = "location-o"

      }
      return icon
    },
    initData() {
      this.$api
        .queryBillByDate(
          this.userid,
          this.$dateFormat("YYYY-mm-dd HH:MM:SS", new Date()),
          "日",
          this.page,
          this.size
        )
        .then((res) => {
          console.log(res);
          if (res.data.code != 0) {
            this.$toast.fail("获取内容失败");
          } else {
            this.billData = res.data.page.list;

            console.log(this.billData);
          }
        })
        .catch((err) => {
          this.$toast.fail(err);
        });
    },
    formatDate(date) {
      return `${date.getMonth() + 1}/${date.getDate()}`;
    },
    onConfirm(date) {
      this.show = false;
      this.year = date.getFullYear();
      this.month = date.getMonth() + 1;
      this.date = this.formatDate(date);
      console.log(this.date);
    },
    initRate() {
      this.currentRate = 60;
    },
    addRate() {
      this.currentRate += 10;
    },
    reduceRate() {
      this.currentRate -= 10;
    },
    goToCostType() {
      this.$router.push("/costType");
    },
    toBudgetPage() {
      this.$router.push("/budgetConfig");
    },
  },
};
</script>

<style >
.wallet {
  text-align: center;
}

.addButton {
  position: absolute;
  bottom: 6vh;
  left: 21.8vh;
  color: #565656;
}
.yaxis {
  height: 2vh;
  width: 1px;
  background-color: #381e1e;
  margin-top: 1vh;
}
.incomepay {
  font-size: 14px;
  height: 5vh;
}

.van-grid-item__content {
  padding-top: 1vh;
}

.circleDiv {
  width: 12vh;
  display: contents;
}

.imageDiv {
  display: contents;
  width: 150px;
  height: 216px;
  margin-top: 41px;
}

.datemoney {
  border-bottom: 1px red solid;
  height: 100%;
  display: flex;
  justify-content: space-between;
}
.date {
  border: 1px red solid;
  width: 25%;
  height: 100%;
  border-radius: 11px 11px 0 0;
  border-bottom: none;
  font-size: 12px;
  color: red;
}
.money {
  font-size: 13px;
  padding-top: 1%;
}

.divider {
}

.triangle {
  float: right;
  border-left: 8px solid transparent;
  border-right: 8px solid transparent;
  border-top: 10px solid red;
}
.van-cell__title {
  text-align: initial;
}

.van-cell__value {
  text-align: right;
}
</style>
