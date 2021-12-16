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
        <b-icon icon="cart4"/>
      </b-button>
    </b-card-footer>
    <HelpModal ref="helpmodal"/>
  </div>
</template>
<script>
import Navbar from "./components/Navbar.vue";
import HelpModal from "./components/HelpModal.vue";
import APIService from "./services/axios.service";

export default {
  components: {
    Navbar,
    HelpModal
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
        document.documentElement.style.setProperty('--primary-color-light', this.pSBC(0.4, response.data.primaryColor, false, true));
        document.documentElement.style.setProperty('--secondary-color', response.data.secondaryColor);
        document.documentElement.style.setProperty('--secondary-color-light', this.pSBC(0.4, response.data.secondaryColor,false,true));

        document.documentElement.style.setProperty('--text-color-secondary', this.getTextColor('--secondary-color'));
        document.documentElement.style.setProperty('--text-color-primary', this.getTextColor('--primary-color'));

        document.documentElement.style.setProperty('--text-color-secondary-light', this.getTextColor('--secondary-color-light'));
        document.documentElement.style.setProperty('--text-color-primary-light', this.getTextColor('--primary-color-light'));
      })
  },
  mounted(){
      this.$root.$on("help", () => {
        this.GetHelp();
      })
  },
  methods: {
    GoToCart() {
      this.$router.push({name:'Cart'});
    },
    GetHelp() {
      this.$refs.helpmodal.Show();
    },
    //https://stackoverflow.com/questions/5560248/programmatically-lighten-or-darken-a-hex-color-or-rgb-and-blend-colors
    pSBC(p,c0,c1,l){
    let r,g,b,P,f,t,h,i=parseInt,m=Math.round,a=typeof(c1)=="string";
    if(typeof(p)!="number"||p<-1||p>1||typeof(c0)!="string"||(c0[0]!='r'&&c0[0]!='#')||(c1&&!a))return null;
    if(!this.pSBCr)this.pSBCr=(d)=>{
        let n=d.length,x={};
        if(n>9){
            [r,g,b,a]=d=d.split(","),n=d.length;
            if(n<3||n>4)return null;
            x.r=i(r[3]=="a"?r.slice(5):r.slice(4)),x.g=i(g),x.b=i(b),x.a=a?parseFloat(a):-1
        }else{
            if(n==8||n==6||n<4)return null;
            if(n<6)d="#"+d[1]+d[1]+d[2]+d[2]+d[3]+d[3]+(n>4?d[4]+d[4]:"");
            d=i(d.slice(1),16);
            if(n==9||n==5)x.r=d>>24&255,x.g=d>>16&255,x.b=d>>8&255,x.a=m((d&255)/0.255)/1000;
            else x.r=d>>16,x.g=d>>8&255,x.b=d&255,x.a=-1
        }return x};
    h=c0.length>9,h=a?c1.length>9?true:c1=="c"?!h:false:h,f=this.pSBCr(c0),P=p<0,t=c1&&c1!="c"?this.pSBCr(c1):P?{r:0,g:0,b:0,a:-1}:{r:255,g:255,b:255,a:-1},p=P?p*-1:p,P=1-p;
    if(!f||!t)return null;
    if(l)r=m(P*f.r+p*t.r),g=m(P*f.g+p*t.g),b=m(P*f.b+p*t.b);
    else r=m((P*f.r**2+p*t.r**2)**0.5),g=m((P*f.g**2+p*t.g**2)**0.5),b=m((P*f.b**2+p*t.b**2)**0.5);
    a=f.a,t=t.a,f=a>=0||t>=0,a=f?a<0?t:t<0?a:a*P+t*p:0;
    if(h)return"rgb"+(f?"a(":"(")+r+","+g+","+b+(f?","+m(a*1000)/1000:"")+")";
    else return"#"+(4294967296+r*16777216+g*65536+b*256+(f?m(a*255):0)).toString(16).slice(1,f?undefined:-2)
},
    getTextColor(color) {
        const style = getComputedStyle(document.body);
        const backgroundColor = style.getPropertyValue(color);

        let rgbBackgroundColor;
        if(backgroundColor.startsWith('#')){
            rgbBackgroundColor = this.hexToRgb(backgroundColor)
        }
        else{
            rgbBackgroundColor = backgroundColor.match(/\d+/g);
        }

        let textColor;
        //https://stackoverflow.com/questions/3942878/how-to-decide-font-color-in-white-or-black-depending-on-background-color
        if ((rgbBackgroundColor[0] * 0.299 + rgbBackgroundColor[1] * 0.587 + rgbBackgroundColor[2] * 0.114) > 150) {
            textColor = 'rgba(0, 0, 0, 1)';
        } else {
            textColor = 'rgba(255, 255, 255, 1)';
        }
        return textColor;
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
  // color: var(--text-color-primary) !important;
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
  color: var(--text-color-secondary-light) !important;
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
