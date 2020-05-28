<template>
  <div class="container mt-5">
    <h1>Favorites</h1>
    <table class="table table-hover">
      <thead>
      <tr>
        <th scope="col">Product</th>
        <th scope="col">Brand</th>
        <th scope="col">Brewery</th>
        <th scope="col">Percentage</th>
        <th scope="col">Price</th>
        <th scope="col">Unlike</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="favorite in favorites">
        <td>{{favorite.product.name}}</td>
        <td>{{favorite.product.brand}}</td>
        <td>{{favorite.product.brewery}}</td>
        <td>{{favorite.product.percentage}}%</td>
        <td>€{{favorite.product.price}}</td>
        <td>
          <button @click="removeFavorite(favorite.favoriteID)" class="btn btn-danger">
            <font-awesome-icon icon="heart-broken"/>
          </button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  import FavoritesAPI from '../services/API/FavoritesAPI';
  import ProductsAPI from '../services/API/ProductsAPI';
  import { mapState } from 'vuex';

  export default {
    data() {
      return {
        favorites: []
      };
    },
    methods: {
      loadFavorites() {
        //FIXME loadFavotitesByUserID(this.user.id)
        FavoritesAPI.getAllFavorites()
          .then(res => {
            console.log(res);
            const data = res.data;
            for (let key in data) {
              ProductsAPI.getProduct(data[key].productID)
                .then(res => {
                  data[key].product = res.data;
                  this.favorites.push(data[key]);
                })
                .catch(error => console.log(error));
            }
          })
          .catch(error => console.log(error));
      },
      removeFavorite(id) {
        FavoritesAPI.deleteFavorite(id)
          .then(res => {
            this.favorites = [];
            this.loadFavorites();
          })
          .catch(error => console.log(error));
      }
    },
    mounted() {
      this.loadFavorites();
    },
    computed: {
      ...mapState({
        user: state => state.user.loggedInUser,
      }),
    },
  };
</script>

