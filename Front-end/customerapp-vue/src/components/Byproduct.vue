<template>
  <div class="byproduct">
    <div>{{ byproduct.name }}</div>
    <div>
      <span>&euro;2,34</span>
      <b-icon icon="dash" font-scale="1" v-on:click="RemoveFromAmount"></b-icon>
      <b-input
        class="numberSelected"
        type="number"
        v-model.number="amount"
        placeholder="0"
        min="0"
        >amount</b-input
      >
      <b-icon icon="plus" font-scale="1" v-on:click="AddToAmount"></b-icon>
    </div>
  </div>
</template>

<script>
export default {
  props: ["byproduct"],
  data() {
    return {
      amount: 0,
    };
  },
  methods: {
    AddToAmount() {
      this.amount++;
    },
    RemoveFromAmount() {
      if (this.amount != 0) {
        this.amount--;
      }
    },
  },
};
</script>

<style scoped>
.b-icon.bi {
  vertical-align: middle !important;
}
.form-control {
  display: inline !important;
  margin-bottom: 10px;
}
.numberSelected {
  width: 40px;
  max-height: 30px;
  text-align: center;
}
.byproduct {
  content: "";
  margin: 0 auto;
  width: 80%;
  padding-top: 10px;
  border-bottom: 1px solid black;
  display: flex;
  justify-content: space-between;
}
</style>
