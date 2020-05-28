<template>
  <div id="nav">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="#">Biershop</a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active"></li>

          <router-link class="nav-item" tag="li" to="/">
            <a class="nav-link" href="#">Home</a>
          </router-link>
          <router-link class="nav-item" tag="li" to="/Product">
            <a class="nav-link" href="#">Product</a>
          </router-link>
          <router-link class="nav-item" tag="li" to="/Products">
            <a class="nav-link" href="#">Products</a>
          </router-link>
          <router-link class="nav-item" tag="li" to="/Order">
            <a class="nav-link" href="#">Order</a>
          </router-link>
          <router-link class="nav-item" tag="li" to="/Orders">
            <a class="nav-link" href="#">Orders</a>
          </router-link>
          <router-link class="nav-item" tag="li" to="/Favorites">
            <a class="nav-link" href="#">Favorites</a>
          </router-link>
          <li v-if="user" class="nav-item">
            <a class="nav-link" href="#" @click.prevent="logout()">Logout</a>
          </li>
          <li v-else class="nav-item">
            <a class="nav-link" href="#" @click.prevent="$emit('handleLoginRequest')">Login</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>



<script>
import { mapState } from 'vuex';
export default {
  name: 'MainNavigation',

  computed: {
    ...mapState({
      user: state => state.user.loggedInUser,
    }),
  },
  methods: {
    logout() {
      this.$store.dispatch('user/logout');
    }
  },
};

</script>
