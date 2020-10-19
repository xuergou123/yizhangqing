<template>
  <div>
    <van-nav-bar
      title="预算设置"
      right-text="确定"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
    />
    <van-cell is-link @click="showPopup">展示弹出层</van-cell>
    <van-cell
      title="预算周期"
      is-link
      :value="budgetPeroid"
      style="text-align: initial;"
      @click="showAction=true"
    />
    <van-action-sheet
      v-model="showAction"
      :actions="actions"
      @select="onSelect"
      description="选择周期"
    />
    <van-cell-group>
      <van-field
        v-model="budgetValue"
        label="每周期预算金额"
        placeholder="3000.00"
        type="number"
        label-width="8.2em"
        input-align="right"
      />
    </van-cell-group>
  </div>
</template>

<script>
export default {
  data() {
    return {
      actions: [
        { name: "每月" },
        { name: "每季度" },
        { name: "每年" },
        { name: "永久" },
      ],
      showAction: false,
      budgetPeroid: "每月",
      userid: "11111",
      budgetValue: 3000,
    };
  },
  methods: {
    onClickLeft() {
      this.$router.push("/detail");
    },
    onClickRight() {
      var budgetConfig = {
        budgetPeriod: this.budgetPeroid,
        budgetValue: this.budgetValue,
      };
      this.$api.configBudget(this.userid, budgetConfig);
    },
    onSelect(item) {
      // 默认情况下点击选项时不会自动收起
      // 可以通过 close-on-click-action 属性开启自动收起
      this.showAction = false;
      this.budgetPeroid = item.name;
      console.log(item);
    }
  },
};
</script>