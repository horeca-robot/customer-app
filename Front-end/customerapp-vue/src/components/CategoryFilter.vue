<template>
  <b-collapse id="collapse-filter" v-model="visible" class="collapse">
    <b-card>
      <b-input-group>
        <b-form-select
          v-model="selected"
          :options="subCategoryNames"
          class="form-select"
          value-field="item"
          text-field="name"
          disabled-field="notEnabled"
        ></b-form-select>
        <b-button @click="addCategory()" class="add-button"
          ><b-icon-plus-circle
        /></b-button>
      </b-input-group>
      <b-list-group>
        <b-badge class="text-body" v-for="item in value" :key="item">
          {{ item
          }}<b-button variant="link" @click="removeCategory(item)"
            ><b-icon-x-circle
          /></b-button>
        </b-badge>
      </b-list-group>
    </b-card>
  </b-collapse>
</template>

<script>
export default {
  props: ["visible", "categories"],
  name: "CategoryFilter",
  data() {
    return {
      value: [],
      selected: "",
    };
  },
  methods: {
    addCategory() {
      if (this.selected != "") {
        let item = this.value.find((i) => i === this.selected);
        if (!item) {
          this.value.push(this.selected);
        }
      }
    },
    removeCategory(category) {
      let item = this.value.find((i) => i === category);

      if (item) {
        this.value = this.value.filter((i) => i !== category);
      }
    },
  },
  computed: {
    subCategoryNames() {
      var array = this.categories;
      var subCategories = [];
      array.forEach((element) => {
        if (element.childCategories.length === 0) {
          subCategories.push(element.name);
        }
      });
      return subCategories;
    },
  },
};
</script>

<style scoped>
.collapse {
  overflow: auto;
}
.card {
  background-color: #cbe1d9 !important;
}
.add-button {
  background-color: #bdad89 !important;
  border: 2pt solid #e0dccc !important;
  color: black !important;
}
</style>