<template>
  <b-container>
    <b-row>
      <b-col cols="12" class="mt-3">
        <b-alert show variant="info" class="alert-link"
          >Controlleren van tafel...</b-alert
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "TableValidator",
  beforeCreate() {
    if (!this.$route?.params?.tableId) {
      this.$router.push({ name: "table-picker" });
    } else {
      this.$store.dispatch("tableModule/setTable", this.$route.params.tableId);
      this.$router.push({ name: "menu" });
    }
  },
};
</script>

<style scoped></style>
