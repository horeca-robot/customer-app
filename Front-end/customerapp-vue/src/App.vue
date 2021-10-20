<template>
  <div id="app">
    <div class="content">
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
      <router-view/>
    </div>
    <b-card-footer class="footer">
      <b-button class="questionButton justify-content-end" v-on:click="GetHelp">
        <b-icon icon="question-circle" variant="dark"/>
      </b-button>
      <b-button class="shoppingCartButton justify-content-end" v-on:click="GoToCart">
        <b-icon icon="cart4" variant="dark"/>
      </b-button>
    </b-card-footer>
  </div>
  
</template>
<script>
export default {
  methods: {
    GoToCart(){
          this.$router.push("/cart")
      },
    GetHelp(){
      //change method.
    }
  }
}
</script>
<style lang="scss">
html, body{
  height: 100%;
  background-color: #CBE1D9 !important;
}
#app {
  text-align: center;
  height: 100%;
}
@font-face {
    font-family: "Baskerville";
    src: url(../public/baskvill.ttf);
  }
.content{
  min-height: 100%;
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
.footer{
 background-color: #82C4B5 !important;
 display: flex;
 justify-content: space-evenly !important;
}
.backButton{
    background-color: #BDAD89 !important;
    border: 2px solid #E0DCCC !important;
}
.shoppingCartButton{
    background-color: #BDAD89 !important;
    border: 2px solid #E0DCCC !important;
}
.questionButton{
   background-color: #BDAD89 !important;
    border: 2px solid #E0DCCC !important;
}
.Header .container{
    background-color: #82C4B5 !important;
}
</style>
