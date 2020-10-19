<template>
  <div class="costType">
    <div>
      <van-tabs v-model="active" class="tabs">
        <van-tab title="支出">
          <van-swipe class="my-swipe" indicator-color="#ff7600">
            <van-swipe-item v-for="(item) in costSwiperCount" :key="item">
              <van-cell-group class="panel">
                <van-grid :column-num="6">
                  <van-grid-item
                    :icon="costItem.ICONNAME"
                    :text="costItem.COST_NAME"
                    @click="addCost(costItem.COST_TYPE,costItem.COST_NAME)"
                    :class="{actives:costItem.COST_TYPE===limit}"
                    v-for="(costItem,costIndex) in costIconData.slice((item-1)*12,item*12)"
                    :key="costIndex"
                  />
                  <van-grid-item
                    v-if="(item*12+1)>costIconData.length"
                    icon="balance-list-o"
                    text="新增"
                  />
                </van-grid>
              </van-cell-group>
            </van-swipe-item>
          </van-swipe>
        </van-tab>
        <van-tab title="收入">
          <van-swipe class="my-swipe" indicator-color="#ff7600">
            <van-swipe-item v-for="(item) in incomeSwiperCount" :key="item">
              <van-cell-group class="panel">
                <van-grid :column-num="6">
                  <van-grid-item
                    :icon="incomeItem.ICONNAME"
                    :text="incomeItem.COST_NAME"
                    @click="addIncome(incomeItem.COST_TYPE,incomeItem.COST_NAME)"
                    :class="{actives:incomeItem.COST_TYPE===limit}"
                    v-for="(incomeItem,costIndex) in incomeIconData.slice((item-1)*12,item*12)"
                    :key="costIndex"
                  />
                  <van-grid-item
                    v-if="(item*12+1)>incomeIconData.length"
                    icon="balance-list-o"
                    text="新增"
                  />
                </van-grid>
              </van-cell-group>
            </van-swipe-item>
          </van-swipe>
        </van-tab>
        <van-tab title="转账">
          <div class="accountTransfer">
            <van-icon name="cluster" size="35px" style="position: absolute;top: 60%;left:46%"/>
            <div class="leftBox">
              <div class="leftBoxItem">
                <div class="boxItemClass" style="line-height: 54px">
                  <van-icon name="add" style="float: left;margin: 19px 8px 0 0;"/>
                  <span style="font-size: 12px;">转出</span>
                </div>
                <div class="boxItemClass">转出账户</div>
              </div>
              <div class="leftBoxItem">
                <div style="height:35%;font-size:12px;line-height: 27px;">CNY</div>
                <div style="height:65%;font-size:26px">0.00</div>
              </div>
            </div>
            <div class="rightBox">
              <div class="rightBoxItem" >
                <div class="boxItemClass" style="line-height: 54px">
                  <van-icon name="clear" style="float: left;margin: 19px 0px 0 139px;" />
                  <span style="font-size: 12px;">转入</span>
                </div>
                <div class="boxItemClass">转入账户</div>
              </div>
              <div class="rightBoxItem">
                <div style="height:35%;font-size:12px;line-height: 27px;">CNY</div>
                <div style="height:65%;font-size:26px">0.00</div>
              </div>
            </div>
          </div>
        </van-tab>
      </van-tabs>
    </div>
    <div>
      <van-cell-group>
        <van-field
          readonly
          v-model="value"
          left-icon="smile-o"
          :label="costType"
          placeholder="请输入数值"
          input-align="right"
          :class="'inputField'+(showKeyboard != true?' show':'')"
        />
        <van-number-keyboard
          v-model="value"
          :show="showKeyboard"
          theme="custom"
          extra-key="."
          hide-on-click-outside="true"
          close-button-text="完成"
          @blur="showKeyboard = false"
          @input="onInput"
          @delete="onDelete"
          @close="onClose"
        />
      </van-cell-group>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: "￥",
      limit: 0,
      showKeyboard: false,
      userid: "11111",
      accoutType: "微信",
      costIconData: [],
      costSwiperCount: 0,
      incomeIconData: [],
      incomeSwiperCount: 0,
    };
  },
  mounted() {
    this.initData();
  },
  methods: {
    initData() {
      this.$api
        .queryCostType(this.userid, "pay")
        .then((res) => {
          console.log(res);
          if (res.data.code != 0) {
            this.$toast.fail("获取内容失败");
          } else {
            this.costIconData = res.data.page.list;
            this.costSwiperCount = (this.costIconData.length % 12) + 1;
          }
        })
        .catch((err) => {
          this.$toast.fail(err);
        });
      this.$api
        .queryCostType(this.userid, "income")
        .then((res) => {
          console.log(res);
          if (res.data.code != 0) {
            this.$toast.fail("获取内容失败");
          } else {
            this.incomeIconData = res.data.page.list;
            this.incomeSwiperCount = (this.costIconData.length % 12) + 1;
          }
        })
        .catch((err) => {
          this.$toast.fail(err);
        });
    },
    addCost(index, type) {
      this.limit = index;
      this.showKeyboard = true;
      this.costType = type;
      console.log(type);
    },
    addIncome(index, type) {
      this.limit = index;
      this.showKeyboard = true;
      this.costType = type;
      console.log(type);
    },
    onInput() {},
    onDelete() {},
    onClose() {
      var costvlaue = this.value.replace("￥", "");
      this.$api
        .addCost(this.userid, this.costType, costvlaue, this.accoutType)
        .then((res) => {
          console.log(res);
          alert(res);
        })
        .catch((err) => {
          console.log(err);
        });
      this.value = "￥";
    },
  },
};
</script>

<style>
.tabs {
  /* padding-left: 5vh;
  padding-right: 5vh; */
}

.van-swipe__indicator {
  background-color: black;
}
.actives {
  color: #ff976a;
}

.inputField {
  position: fixed;
  bottom: 29vh;
}
.show {
  display: none;
}
.accountTransfer {
  margin: 5vh 1vh 2vh 1vh;
  height: 17vh;
  border-radius: 4px;
  box-shadow: 1px 0px 4px #5855557a;
}

.leftBox {
  float: left;
  height: 100%;
  width: 49%;
}

.rightBox {
  float: right;
  height: 100%;
  width: 49%;
}

.leftBoxItem {
  width: 100%;
  height: 50%;
  padding-left: 1.5vh;
  text-align: left;
}

.rightBoxItem {
  width: 94%;
  height: 50%;
  padding-right: 1.5vh;
  text-align: right;
}

.boxItemClass {
  width: 100%;
  height: 50%;
  line-height: 32px;
}

.lineHeight {
  line-height: 22px;
}
</style>