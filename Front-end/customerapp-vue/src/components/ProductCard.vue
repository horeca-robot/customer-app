<template>
  <div>
    <b-card>
      <b-card-body>
        <img :src="product.img">
      </b-card-body>
      <b-card-footer> 
        <h3>{{product.category}}</h3>
        <p>{{product.name}}</p>
        <p>€ {{product.price.toFixed(2)}}</p>        
      </b-card-footer>
    </b-card>
  </div>
</template>

<script>
export default {
    props: ["product"]
}
</script>
