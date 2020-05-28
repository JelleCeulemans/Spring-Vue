<template>
  <div class="container mt-5">
    <h1>Create product</h1>
    <form>
      <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" v-model="product.name">
      </div>
      <div class="form-group">
        <label for="brand">Brand</label>
        <input type="text" class="form-control" id="brand" v-model="product.brand">
      </div>
      <div class="form-group">
        <label for="brewery">Brewery</label>
        <input type="text" class="form-control" id="brewery" v-model="product.brewery">
      </div>
      <div class="form-group">
        <label for="percentage">Percentage</label>
        <input type="number" class="form-control" id="percentage" v-model="product.percentage">
      </div>
      <div class="form-group">
        <label for="price">Price</label>
        <input type="number" class="form-control" id="price" v-model="product.price">
      </div>
      <button type="submit" @click.prevent="addProduct" class="btn btn-primary">Submit</button>
    </form>
  </div>
</template>

<script>
import ProductsAPI from '../../services/API/ProductsAPI';
export default {
  name: 'product',
  data() {
    return {
      product: {
        name: '',
        brand: '',
        brewery: '',
        percentage: 0,
        price: 0.0
      }
    }
  },
  methods: {
    addProduct() {
      const product = {
        ...this.product,
        shortBrand: this.product.brand.toLowerCase().replace(/\s/g, ''),
        shortBrewery: this.product.brewery.toLowerCase().replace(/\s/g, '')
      };
      ProductsAPI.productPost(product)
        .then(res => this.$router.push("products"))
        .catch(error => console.log(error));
    }
  }
};
</script>
