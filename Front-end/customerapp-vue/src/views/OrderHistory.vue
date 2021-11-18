<template>
  <div class="container">
    <b-alert
      class="alert"
      :show="dismissCountDown"
      variant="success"
      @dismissed="dismissCountDown = 0"
      @dismiss-count-down="countDownChanged"
    >
      <p>Bestelling geplaatst!</p>
    </b-alert>
    <div></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dismissSecs: 3,
      dismissCountDown: 0,
      showDismissibleAlert: false,
    };
  },
  methods: {
    countDownChanged(dismissCountDown) {
      this.dismissCountDown = dismissCountDown;
    },
    showAlert() {
      this.dismissCountDown = this.dismissSecs;
    },
  },
};
</script>

<style scoped>
.alert {
  position: absolute;
  top: 0;
  width: 300px;
  margin-top: 20px;
  margin-left: auto;
  margin-right: auto;
  left: 0;
  right: 0;
}
</style>
