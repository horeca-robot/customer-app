<template>
  <b-card tag="article">
    <qrcode-stream v-if="scanning" @decode="onDecode"></qrcode-stream>
    <b-card-img
      v-if="!scanning"
      :src="require('../assets/qr-code-horeca.webp')"
    ></b-card-img>
    <b-button
      href="#"
      variant="primary"
      class="w-100 mt-3"
      @click="startScanning"
      >Scan QR</b-button
    >
  </b-card>
</template>

<script>
export default {
  name: "TableQrScanner",
  data() {
    return {
      scanning: false,
    };
  },
  methods: {
    onDecode(result) {
      console.log(result);
      window.location = result;
    },
    startScanning() {
      this.scanning = true;
    },
  },
};
</script>

<style scoped></style>
