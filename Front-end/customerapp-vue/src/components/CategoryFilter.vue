<template>
  <b-collapse v-model="visible">
    <b-card>
      <b-input-group>
        <b-form-select
          v-model="selectedTag"
          class="form-select"
          value-field="item"
          text-field="name"
          disabled-field="notEnabled"
        >
          <b-form-select-option
            v-for="tag in tags"
            :key="tag.id"
            :value="tag"
            >{{ tag.name }}</b-form-select-option
          >
        </b-form-select>
        <b-button @click="addTagFilter()" class="add-button">
          <b-icon-plus-circle/>
          </b-button>
      </b-input-group>
      <b-list-group>
        <b-badge
          v-for="item in this.$store.state.tagFilter.selectedFilters"
          :key="item.id"
          class="mt-1"
        >
          <b-row align-h="between" align-v="center">
            <b-col class="text-start">{{ item.name }}</b-col>
            <b-col class="text-end">
              <b-icon-x-circle
                style="height: 1.5em; width: 1.5em"
                @click="removeTagFilter(item)"
              />
              <b-badge class="badge-remove-buttom"></b-badge>
            </b-col>
          </b-row>
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
      tags: [],
      selectedTag: null,

      value: [],
      selected: "",
    };
  },
  methods: {
    addTagFilter() {
      if (
        this.selectedTag === null ||
        this.$store.state.tagFilter.selectedFilters.includes(this.selectedTag)
      )
        return;

      this.$store.dispatch("tagFilter/setSelectedFilters", [
        ...this.$store.state.tagFilter.selectedFilters,
        this.selectedTag,
      ]);
    },
    removeTagFilter(tag) {
      if (tag == null) return;

      this.$store.dispatch(
        "tagFilter/setSelectedFilters",
        this.$store.state.tagFilter.selectedFilters.filter(
          (value) => value !== tag
        )
      );
    },
  },
  mounted() {
    this.$APIService.getAllTags().then((response) => {
      this.tags = response.data;
    });
  },
};
</script>

<style scoped>
.collapse {
  overflow: auto;
}
.card {
  background-color: var(--primary-color-light) !important;
}
.add-button {
  background-color: var(--secondary-color) !important;
  border: 2pt solid var(--secondary-color-light) !important;
  color: var(--text-color-secondary) !important;
}
.badge {
  background-color: var(--secondary-color) !important;
}
.badge-remove-buttom {
  background-color: var(--secondary-color-light) !important;
}
</style>
