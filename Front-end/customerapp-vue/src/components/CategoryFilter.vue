<template>
  <b-collapse id="collapse-filter" v-model="visible" class="collapse">
    <div>
      <b-form-tags input-id="tags-basic" v-model="value"></b-form-tags>
    </div>
  </b-collapse>
</template>

<script>
export default {
  props: ["visible", "categories"],
  name: "CategoryFilter",
  data() {
    return {
      value: [],
    };
  },
};
</script>

<style scoped>
.collapse {
  overflow: auto;
}
</style>