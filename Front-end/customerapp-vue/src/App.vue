<template>
  <div id="app">
    <div class="content">
      <Navbar :route="this.$route"/>
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
import Navbar from "./components/Navbar.vue";
export default {
  components: {
    Navbar,
  },
  methods: {
    GoToCart(){
          this.$router.push("/cart");
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
.button-style {
    background-color: #BDAD89 !important;
    border: 2pt solid #E0DCCC !important;
    height: 30px;
    width: 120px;
    color: black !important;
    padding: 1px !important;
    margin-top: 3px;
    margin-bottom: 3px;
  }
  .btn-secondary:active, .btn-secondary:hover {
    background-color: #E0DCCC;
    border: 2pt solid #BDAD89;
  }
</style>
