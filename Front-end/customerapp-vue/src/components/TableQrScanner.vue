<template>
  <div>
    <qrcode-stream
      v-if="scanning"
      :camera="camera"
      @init="onInit"
      @decode="onDecode"
      v-bind:class="{ 'loading-hide': loading }"
    >
      <div v-if="validationFailure" class="validation-failure">
        QR code kan niet herkend worden
      </div>

      <div v-if="validationPending" class="validation-pending">
        Aanschuiven aan een tafel
      </div>
    </qrcode-stream>
    <b-card-img v-if="!scanning || loading" :src="img"></b-card-img>
    <b-button
      class="w-100 mt-3 scan-button-bg"
      :disabled="!loading"
      @click="startScanning"
      >Scan QR</b-button
    >
  </div>
</template>

<script>
import Image from "../assets/qr-code-horeca.png";

export default {
  name: "TableQrScanner",
  data() {
    return {
      img: Image,
      scanning: false,
      loading: true,
      isValid: undefined,
      camera: "auto",
      result: null,
    };
  },
  computed: {
    validationPending() {
      return this.isValid === undefined && this.camera === "off";
    },
    validationFailure() {
      return this.isValid === false;
    },
  },
  methods: {
    async onDecode(content) {
      this.result = content;
      this.turnCameraOff();

      const match = this.result.match(/tablevalidator\/([\s\S]*)$/);

      await this.timeout(0.1);
      if (!match || !match[0].includes("tablevalidator")) {
        this.isValid = false;
        await this.timeout(2000);
      } else {
        this.$store.dispatch("tableModule/setTable", match[1]);
        this.$router.push({ name: "Menu" });
      }
      this.turnCameraOn();
    },
    startScanning() {
      this.scanning = true;
    },
    onInit(promise) {
      promise
        .catch(console.error)
        .then(() => {
          this.isValid = undefined;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    turnCameraOn() {
      this.camera = "auto";
    },

    turnCameraOff() {
      this.camera = "off";
    },
    timeout(ms) {
      return new Promise((resolve) => {
        window.setTimeout(resolve, ms);
      });
    },
  },
};
</script>

<style scoped>
.loading-hide {
  height: 0 !important;
}
.validation-failure,
.validation-pending {
  position: absolute;
  width: 100%;
  height: 100%;

  background-color: rgba(255, 255, 255, 0.8);
  text-align: center;
  font-weight: bold;
  font-size: 1.4rem;
  padding: 10px;

  display: flex;
  flex-flow: column nowrap;
  justify-content: center;
}
.validation-failure {
  color: red;
}
.scan-button-bg {
  background: var(--secondary-color);
  color: var(--text-color-secondary);
}
.scan-button-bg:hover {
  background: var(--secondary-color-light);
  color: var(--text-color-secondary);
}
</style>
