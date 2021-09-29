<template>
  <div class="about">
    <b-card style="width:500px; height 300px;">
      <b-card-header>Bootstrap werk!</b-card-header>
      <br />
      <br />
      <b-button v-b-modal.modal-1>Show product</b-button>
    </b-card>

    <b-modal id="modal-1" title="BootstrapVue">
      <h1>Ingredienten</h1>
      <ul>
        <li>Pinda</li>
        <li>Kaas</li>
      </ul>
    </b-modal>
  </div>
</template>
