<template>
  <div class="container mt-5">
    <h1>Order page</h1>
    <form>
      <div class="form-group">
        <label>Select beer</label>
        <select class="custom-select" @change="changedSelect($event)">
          <option>Select a beer.</option>
          <option :selected="isSelected(product.productID)" v-for="product in products" :value="product.id">{{product.name}}  | €{{product.price}}</option>
        </select>
      </div>
      <div class="form-group">
        <label for="amount">Amount</label>
        <input type="number" class="form-control" id="amount" v-model="order.amount">
      </div>
      <table class="table table-dark">
        <thead>
        <tr>
          <th scope="col">Beer</th>
          <th scope="col">price</th>
          <th scope="col">Amount</th>
          <th scope="col">Total</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <th scope="row">{{order.productName}}</th>
          <td>€ {{order.price}}</td>
          <td>{{order.amount}}</td>
          <td>€ {{order.price * order.amount}}</td>
        </tr>
        </tbody>
      </table>
      <button @click.prevent="createOrder" type="submit" class="btn btn-primary">Submit</button>
    </form>
  </div>
</template>

<script>
  import ProductsAPI from '../../services/API/ProductsAPI';
  import OrdersAPI from '../../services/API/OrdersAPI';
  import { mapState } from 'vuex';
  export default {
    data() {
      return {
        products: [],
        order: {
          productName: '',
          price: 0,
          amount: 0
        },
        productID: ''
      };
    },
    computed: {

    },
    methods: {
      isSelected(id) {
        return id === this.productID;
      },
      changedSelect(event) {
        const selectedProduct = this.products[event.target.value];
        this.order.productName = selectedProduct.name;
        this.order.price = selectedProduct.price;
      },
      createOrder() {
        //FIXME
        // this.order.userID = this.user.id;
        OrdersAPI.placeOrder(this.order)
          .then(res => this.$router.push("orders"))
          .catch(error => console.log(error));
      }
    },
    mounted() {
      ProductsAPI.getProducts()
        .then(result => {
          const data = result.data;
          const products = [];
          for(let key in data) {
            const product = data[key];
            product.id = key;
            products.push(product);
          }
          this.products = products;
          this.productID = this.$route.params.productID;
          if (this.productID) {
            const product = this.products.filter(p => p.productID === this.productID);
            this.order.productName = product[0].name;
            this.order.price = product[0].price;
          }
        })
        .catch(error => {
          console.log(error);
        })
    },
    computed: {
      ...mapState({
        user: state => state.user.loggedInUser,
      }),
    },
  };
</script>

<style scoped>

</style>
