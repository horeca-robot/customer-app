<template>
  <b-modal
    class="confirmtable"
    centered
    hide-header
    hide-footer
    ref="confirmtable"
    id="confirmtable"
  >
    <div v-if="!loading && !messageSend">
      <p>Waar heeft u hulp bij nodig?</p>
      <b-select @change="ResetError()" v-model="selected" :options="options">
      </b-select>
      <br />
      <div v-if="selected != null">
        <br />
        <label>geef een beschrijving van je probleem</label>
        <b-form-textarea
          @click="ResetError()"
          v-model="description"
          class="help-input"
        ></b-form-textarea>
        <br/>
      </div>

      <b-button class="button-style" @click="Confirm()">Bevestigen</b-button>
      <br />
      <br />
      <b-alert show v-if="error.active" variant="danger">{{
        error.message
      }}</b-alert>

    </div>
    <b-container v-if="loading" class="loading">
      <b-row class="text-center">
        <b-col>
          <b-spinner class="spinner"></b-spinner>
          <br/>
          <br/>
          <p>Bericht aan het versturen</p>
        </b-col>
      </b-row>
    </b-container>
    <b-container v-if="!loading && messageSend">
        <p>{{responseMessage}}</p>
        <b-button @click="Closing()" class="button-style">Sluiten</b-button>
    </b-container>
  </b-modal>
</template>

<script>
var socket;
export default {
  data() {
    return {
      selected: null,
      options: [
        { value: null, text: "Selecteren" },
        { value: "Probleem met de robot", text: "Probleem met de robot" },
        { value: "Probleem met het eten", text: "Probleem met het eten" },
        { value: "Probleem met betalen", text: "Probleem met betalen" },
        { value: "Anders", text: "Anders" },
      ],
      error: {
        active: false,
        message: "",
      },
      description: "",
      loading: false,
      messageSend: false,
      responseMessage: "",
    };
  },
  methods: {
    Show() {
      this.$refs["confirmtable"].show();
    },
    Confirm() {
      if (this.selected == null) {
        this.error.active = true;
        this.error.message = "Selecteer een reden a.u.b.";
        return;
      }
      if (this.description.length > 450) {
        this.error.active = true;
        this.error.message = "Uw beschrijving is te lang";
        return;
      }
      if (this.selected === "Anders" && this.description.length <= 0) {
        this.error.active = true;
        this.error.message =
          "Als u 'Anders' selecteerd moet u een beschrijving meegeven";
        return;
      }
      var HelpMessage = {
        type: "help",
        payload: {
          tableId: "123",
          reason: this.selected,
          description: this.description,
        },
      };
      socket.send(JSON.stringify(HelpMessage));
      this.loading = true;
    },
    Closing(){
        setTimeout(() => this.messageSend = false, 300);
        this.$refs["confirmtable"].hide()
        
    },
    ResetError() {
      this.error.active = null;
      this.error.message = "";
    },
    ConnectWebSocket() {
      try {
        socket = new WebSocket("wss://ws.guyliangilsing.me/customer");

        socket.onopen = () => {
          console.log("[open] Connection established");
        };

        socket.onmessage = (message) =>{
          console.log(message.data);
          var object = JSON.parse(message.data);
          if(object.isSuccessful){
            console.log("is gelukt!");
            this.messageSend = true;
            this.loading = false;
            this.responseMessage = object.message;
          }
          else if(object.isSuccessful === undefined){
            return;
          }
          else{
            this.messageSend = true;
            this.loading = false;
            this.responseMessage = object.message;
          }
        }

        socket.onclose = (event) => {
          console.log("websocket connection closed event" + event + event.code);
        };
        socket.onerror = (error) => {
          console.log("WS ERROR: ", error);
          setTimeout(() => this.ConnectWebSocket(), 5000);
        };
      } catch (e) {
        console.log(e);
      }
    },
  },
  mounted() {
    this.ConnectWebSocket();
  },
};
</script>

<style scoped>
#confirmtable .modal-dialog .modal-content {
  border: 0;
  border-radius: 0;
  background-color: var(--secondary-color-light);
  color: var(--text-color-secondary-light);
}
.helpinput {
  min-height: 450px;
}
.spinner{
    color: var(--primary-color);
}
</style>
