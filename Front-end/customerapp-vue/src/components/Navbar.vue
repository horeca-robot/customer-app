<template>
  <b-navbar class="navbar" toggleable type="light" variant="light">
    <b-card-img :src="img" class="image"></b-card-img>
    <b-navbar-brand>{{ route.name }}</b-navbar-brand>
    <div
    v-b-toggle.sidebar-1
      class="hamburger"
      @click="hamburgerOpen = !hamburgerOpen"
      :class="hamburgerOpen ? 'hamburger--is-open' : ''"
    >
      <div class="hamburger__item hamburger__item--first"></div>
      <div class="hamburger__item hamburger__item--middle"></div>
      <div class="hamburger__item hamburger__item--last"></div>
    </div>
    <Navigation/>
  </b-navbar>
</template>

<script>
import Image from "../assets/logo.png";
import Navigation from "./Navigation.vue";
export default {
  props: ["route"],
  components: {
    Navigation,
  },
  data() {
    return {
      img: Image,
      hamburgerOpen: false,
    };
  },
  methods: {
    //TODO:Need to add page specific name filter.
  },
};
</script>

<style lang="scss" scoped>
.image {
  height: 30px;
  width: auto;
  margin-left: 5px;
}
.navbar {
  display: flex;
  justify-content: space-between;
}
.bg-blue-bright {
  background-color: black;
}

.hamburger {
  height: 30px;
  width: 30px;
  display: flex;
  margin-right: 5px;
  flex-direction: column;
  justify-content: space-between;

  &:hover {
    cursor: pointer;
  }

  &__item {
    height: 6px;
    width: 100%;
    background: black;
    transition: transform 300ms cubic-bezier(0.445, 0.05, 0.55, 0.95),
      opacity 300ms linear;

    &--first {
      .hamburger--is-open & {
        transform: translate(0, 11px) rotate(45deg);
      }
    }

    &--middle {
      .hamburger--is-open & {
        opacity: 0;
      }
    }

    &--last {
      .hamburger--is-open & {
        transform: translate(0, -13px) rotate(-45deg);
      }
    }
  }
}
</style>
