<template>
  <div>
    <div class="header">
      <van-nav-bar
        title="现金"
        @click-left="onClickLeft"
        @click-right="onClickRight"
        left-arrow
        style="background-color: #64e7ed;"
      >
        <template #right>
          <van-icon name="search" size="18" />
        </template>
      </van-nav-bar>
      <div class="accoutBalance">
        <div style="font-size:12px">账户余额</div>
        <div style="font-size: 32px;line-height: 14px;padding: 0 2px 0px 3vh;">{{accoutBalance}}</div>
        <van-icon name="edit" size="20" @click="popShow=true" />
      </div>
      <div class="payandincome">
        <div style="font-size:12px">流入</div>
        <div style="font-size: 18px;line-height: 20px;padding: 0px 2px 0px 6vh;">{{accoutBalance}}</div>
      </div>
      <div class="payandincome">
        <div style="font-size:12px">流出</div>
        <div style="font-size: 18px;line-height: 20px;padding: 0px 2px 0px 6vh;">{{accoutBalance}}</div>
      </div>
    </div>

    <van-dialog v-model="popShow" title="请输入账户余额" show-cancel-button @confirm="configAccout">
      <van-field v-model="accoutBalance" placeholder type="number" class="inputForm" />
    </van-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      accoutBalance: 0,
      pay: 0,
      income: 0,
      popShow: false,
      accoutType:"1",
      accoutName:"现金",
      userid:"11111",
    };
  },
  methods: {
    onClickLeft() {
      this.$router.push("/wallet")
    },
    onClickRight() {},
    configAccout() {
      this.$api
        .configCost(this.userid,this.accoutType, this.accoutBalance, this.accoutName)
        .then((res) => {
          console.log(res);
          if (res.data.code != 0) {
            this.$toast.fail("获取内容失败");
          } else {
            if(res.data.page.updateResult!=1){
              this.$toast.fail("配置失败，请联系管理员");
            }
          }
        })
        .catch((err) => {
          this.$toast.fail(err);
        });
    },
  },
};
</script>

<style >
.header {
  background-color: #64e7ed;
  height: 22vh;
}
[class*="van-hairline"]::after {
  border: 0 solid #64e7ed;
}

.accoutBalance {
  display: flex;
  justify-content: initial;
  height: 5vh;
  padding: 1vh 0 2vh 2vh;
}

.payandincome {
  display: flex;
  justify-content: initial;
  height: 1vh;
  padding: 1vh 0 2vh 2vh;
}

.inputForm {
  border-bottom: 0.5px solid #d4d4d4;
  width: 72%;
  margin: 0 6vh 0 6vh;
}
</style>