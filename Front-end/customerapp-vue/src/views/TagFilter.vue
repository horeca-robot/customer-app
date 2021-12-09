<template>
  <div class="background">
    <div class="Header">
      <b-container>
        <b-row class="SearchandStore">
          <search-bar />
          <menu-button :col="2" />
          <cart-button :col="2" />
        </b-row>
      </b-container>
    </div>
    <b-container style="background-color: #e0dccc">
      <b-row>
        <b-col class="text-left">
          <h1 class="header-style">Filter</h1>
        </b-col>
      </b-row>
      <b-form-checkbox-group
        id="checkbox-group-2"
        v-model="selectedTags"
        name="flavour-2"
      >
        <b-row class="text-start">
          <b-col v-for="tag in tags" :key="tag.id" cols="4">
            <b-form-checkbox :value="tag.id"
              ><span class="mx-1">{{ tag.name }}</span></b-form-checkbox
            >
          </b-col>
        </b-row>
      </b-form-checkbox-group>
    </b-container>
  </div>
</template>

<script>
import SearchBar from "../custom-tags/searchbar.vue";
import CartButton from "../custom-tags/cartbutton.vue";
import MenuButton from "../custom-tags/menubutton.vue";

export default {
  name: "TagFilter",
  components: {
    SearchBar,
    CartButton,
    MenuButton,
  },
  data() {
    return {
      tags: [],
    };
  },
  computed: {
    selectedTags: {
      get() {
        return this.$store.state.tagFilter.selectedFilters;
      },
      set(selectedFilters) {
        this.$store.dispatch("tagFilter/setSelectedFilters", selectedFilters);
      },
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
.background {
  background-color: RGBA(203, 225, 217, 0.5);
}
.header-style {
  font-family: Baskerville Old Face;
}
</style>
