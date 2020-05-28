<template>
  <div class="container mt-5 mb-5">
    <h1>Products overview</h1>
    <div class="row">
      <div class="col-lg-4 col-md-6 col-sm-12 d-flex align-items-stretch" v-for="product in products">
        <div class="card mt-5" style="width: 18rem;">
          <img :src="getImgUrl(product.brand)" class="card-img-top" :alt="product.brand">
          <hr>
          <div class="card-body d-flex flex-column">
            <h3 class="card-title">{{product.name}}</h3>
            <div class="card-text"><b>Brand: </b>{{product.brand}}</div>
            <div class="card-text"><b>Brewery: </b>{{product.brewery}}</div>
            <div class="card-text"><b>Percentage: </b>{{product.percentage}} %</div>
            <div class="card-text"><b>Price: </b>€{{product.price}}</div>
            <div class="divider"></div>

          </div>
          <div class="d-flex justify-content-between card-footer" >
            <button @click="makeOrder(product.productID)" class="btn btn-primary"><font-awesome-icon icon="shopping-cart"/></button>
            <button @click="deleteProduct(product.productID)" class="btn btn-danger"><font-awesome-icon icon="trash"/></button>
            <button @click="editProduct(product.id)" class="btn btn-success"><font-awesome-icon icon="edit"/></button>
            <button @click="saveAsFavorite(product.productID)" class="btn btn-info"><font-awesome-icon icon="heart"/></button>
          </div>
        </div>
      </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" tabindex="-1" role="dialog" id="editModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Edit product</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
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
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" @click="changeProduct">Save Changes</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ProductsAPI from '../../services/API/ProductsAPI';
import FavoritesAPI from '../../services/API/FavoritesAPI';
import { mapState } from 'vuex';
export default {
  name: 'products',
  data() {
    return {
      products: [],
      product: {}
    }
  },
  methods: {
    loadProducts() {
      ProductsAPI.getProducts()
        .then(res => {
          const data = res.data;
          const products = [];
          for(let key in data) {
            const product = data[key];
            product.id = key;
            products.push(product);
          }
          this.products = products;
        })
        .catch(error => {
          console.log(error);
        });
    },
    getImgUrl(brand) {
      try {
        return require('../../assets/images/'+ brand.toLowerCase().replace(/\s/g, '') +'.jpg');
      } catch (ex) {
        return require('../../assets/images/beer.jpg');
      }
    },
    deleteProduct(id) {
      ProductsAPI.deleteProduct(id)
        .then(res => this.loadProducts())
        .catch(error => console.log(error));
    },
    editProduct(id) {
      const product = {
        ...this.products[id],
      };
      delete product.id;
      this.product = product;
      $('#editModal').modal('show');
    },
    changeProduct() {
      this.product.shortBrand = this.product.brand.toLowerCase().replace(/\s/g, '');
      this.product.shortBrewery = this.product.brewery.toLowerCase().replace(/\s/g, '');
      ProductsAPI.changeProduct(this.product)
        .then(res => {
          $('#editModal').modal('hide');
          this.loadProducts();

        })
        .catch(error => console.log(error));
    },
    saveAsFavorite(id) {
      const favorite = {
        favoriteID: '',
        //FIXME
        userID: this.user.id,
        productID: id
      };
      FavoritesAPI.saveFavorite(favorite)
        .then(res => {
          this.$router.push('favorites');
        })
        .catch(error => console.log(error));
    },
    makeOrder(id){
      this.$router.push({ name: 'order', params: { productID: id } });
    }
  },
  mounted() {
    this.loadProducts();
  },
  computed: {
    ...mapState({
      user: state => state.user.loggedInUser,
    }),
  },
};
</script>

<style scoped>
  .card-img-top {
    object-fit: contain;
    height: 250px;
  }

  #product-footer {
    position: relative;
    margin-bottom: 10px;
  }
</style>
