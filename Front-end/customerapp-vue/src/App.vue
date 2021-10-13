<template>
  <div id="app">
    <b-navbar toggleable type="light" variant="light">
      <b-navbar-brand style="margin-left: 30px" to="/"
        >CustomerApp</b-navbar-brand
      >

      <b-navbar-toggle target="navbar-toggle-collapse">
        <template #default="{ expanded }">
          <b-icon v-if="expanded" icon="chevron-bar-up"></b-icon>
          <b-icon v-else icon="chevron-bar-down"></b-icon>
        </template>
      </b-navbar-toggle>

      <b-collapse id="navbar-toggle-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <b-nav-item to="/">Home</b-nav-item>
          <b-nav-item to="/cart">Bestellijst</b-nav-item>
          <b-nav-item to="/orders">History</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <router-view />
  </div>
</template>

<style lang="scss">
#app {
  text-align: center;
}
@font-face {
    font-family: "Baskerville";
    src: url(../public/baskvill.ttf);
  }

  .bold {
    font-weight: bold;
  }

  .heading {
    font-family: "Baskerville";
    font-size: 20px;
  }

  .text {
    font-family: Arial, Helvetica, sans-serif;
  }
</style>
