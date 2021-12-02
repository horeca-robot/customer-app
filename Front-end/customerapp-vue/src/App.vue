<template>
  <div id="app">
    <div class="content">
      <Navbar :route="this.$route" />
      <router-view />
    </div>
    <b-card-footer class="footer">
      <b-button class="questionButton justify-content-end" v-on:click="GetHelp">
        <b-icon  icon="question-circle" />
      </b-button>
      <b-button
        class="shoppingCartButton justify-content-end"
        v-on:click="GoToCart"
      >
        <b-icon icon="cart4" />
      </b-button>
    </b-card-footer>
  </div>
</template>
<script>
import Navbar from "./components/Navbar.vue";
import APIService from "./services/axios.service"
export default {
  components: {
    Navbar,
  },
  data(){
    return{
      restaurantInfo:{}
    }
  },
  created(){
      APIService.getRestaurantInfo().then(response =>{
        this.restaurantInfo = response.data;
        document.documentElement.style.setProperty('--primary-color', response.data.primaryColor);
        document.documentElement.style.setProperty('--primary-color-light', this.LightenDarkenColor(response.data.primaryColor, 40));
        document.documentElement.style.setProperty('--secondary-color', response.data.secondaryColor);
        document.documentElement.style.setProperty('--secondary-color-light', this.LightenDarkenColor(response.data.secondaryColor, 40));
        this.getTextColor();
      })
      
  },
  methods: {
    GoToCart() {
      this.$router.push({name:'Cart'});
    },
    GetHelp() {
      alert("Er komt zo snel mogelijk een medewerker aan.");
    },
    //https://stackoverflow.com/questions/5560248/programmatically-lighten-or-darken-a-hex-color-or-rgb-and-blend-colors
    LightenDarkenColor(col,amt) {
        var usePound = false;
        if ( col[0] == "#" ) {
            col = col.slice(1);
            usePound = true;
        }

        var num = parseInt(col,16);

        var r = (num >> 16) + amt;

        if ( r > 255 ) r = 255;
        else if  (r < 0) r = 0;

        var b = ((num >> 8) & 0x00FF) + amt;

        if ( b > 255 ) b = 255;
        else if  (b < 0) b = 0;

        var g = (num & 0x0000FF) + amt;

        if ( g > 255 ) g = 255;
        else if  ( g < 0 ) g = 0;

        return (usePound?"#":"") + (g | (b << 8) | (r << 16)).toString(16);
    },
    getTextColor() {
        const style = getComputedStyle(document.body);
        const secondary = style.getPropertyValue('--secondary-color');
        const primary = style.getPropertyValue('--primary-color');

        let rgbsec;
        if(secondary.startsWith('#')){
            rgbsec = this.hexToRgb(secondary)
        }
        else{
            rgbsec = secondary.match(/\d+/g);
        }

        let rgbpri;
        if(primary.startsWith('#')){
            rgbpri = this.hexToRgb(primary)
        }
        else{
            rgbpri = primary.match(/\d+/g);
        }

        let textColorPrimary;
        //https://stackoverflow.com/questions/3942878/how-to-decide-font-color-in-white-or-black-depending-on-background-color
        if ((rgbpri[0] * 0.299 + rgbpri[1] * 0.587 + rgbpri[2] * 0.114) > 186) {
            textColorPrimary = 'rgba(0, 0, 0, 1)';
        } else {
            textColorPrimary = 'rgba(255, 255, 255, 1)';
        }

        let textColorSecondary;
        //https://stackoverflow.com/questions/3942878/how-to-decide-font-color-in-white-or-black-depending-on-background-color
        if ((rgbsec[0] * 0.299 + rgbsec[1] * 0.587 + rgbsec[2] * 0.114) > 186) {
            textColorSecondary = 'rgba(0, 0, 0, 1)';
        } else {
            textColorSecondary = 'rgba(255, 255, 255, 1)';
        }

        document.documentElement.style.setProperty('--text-color-secondary', textColorSecondary);
        document.documentElement.style.setProperty('--text-color-primary', textColorPrimary);
        console.log( "TEXTCOLORPRIM", document.documentElement.style.getPropertyValue("--text-color-primary"));
        console.log("TEXTCOLORSEC", textColorSecondary);
    },

    //https://stackoverflow.com/questions/5623838/rgb-to-hex-and-hex-to-rgb
    hexToRgb(hex) {
        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? [
          parseInt(result[1], 16),
          parseInt(result[2], 16),
          parseInt(result[3], 16)
         ] : null;
      }
  }
};
</script>
<style lang="scss">
html,
body {
  height: 100%;
  background-color: var(--primary-color-light) !important;
}
#app {
  text-align: center;
  height: 100%;
}
@font-face {
  font-family: "Baskerville";
  src: url(../public/baskvill.ttf);
}
.content {
  min-height: 100%;
}
.bold {
  font-weight: bold;
}
.heading {
  font-family: "Baskerville";
  font-size: 20px;
}
.heading-2 {
  font-family: "Baskerville";
  font-size: 50px;
}
.text {
  font-family: Arial, Helvetica, sans-serif;
}
.footer {
  background-color: var(--primary-color) !important;
  color: var(--text-color-primary) !important;
  display: flex;
  justify-content: space-evenly !important;
}
.backButton {
  background-color: var(--secondary-color) !important;
  border: 2px solid var(--secondary-color-light) !important;
  color: var(--text-color-secondary) !important;
}
.shoppingCartButton {
  background-color: var(--secondary-color)  !important;
  border: 2px solid var(--secondary-color-light) !important;
  color: var(--text-color-secondary) !important;
}
.questionButton {
  background-color: var(--secondary-color)  !important;
  border: 2px solid var(--secondary-color-light) !important;
  color: var(--text-color-secondary) !important;
}
.Header .container {
  background-color: var(--primary-color) !important;
  color: var(--text-color-primary) !important;
  max-width: 100% !important;
}
.button-style {
  background-color: var(--secondary-color)  !important;
  border: 2pt solid var(--secondary-color-light) !important;
  color: var(--text-color-secondary) !important;
  height: 30px;
  width: 120px;
  padding: 1px !important;
  margin-top: 3px;
  margin-bottom: 3px;
}
.btn-secondary:active,
.btn-secondary:hover {
  background-color: var(--secondary-color-light);
  border: 2pt solid var(--secondary-color) ;
  color: var(--text-color-secondary) !important;
}
.Header .container {
    background-color: var(--primary-color) !important;
    color: var(--text-color-primary) !important;
    max-width: 100% !important;
}
.menuButton {
    background-color: var(--secondary-color)  !important;
    border: 2px solid var(--secondary-color-light) !important;
    color: var(--text-color-secondary) !important;
}
.SearchandStore {
  padding: 10px;
}
</style>
