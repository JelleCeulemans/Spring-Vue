<style lang="scss">
   @import 'assets/styles/main';
</style>

<template>
  <div id="app">
    <main-navigation @handleLoginRequest="showLoginPopup = true"/>
    <router-view/>
    <cart/>

    <popup v-show="!user" :open="showLoginPopup"
      @close="showLoginPopup = false"
      @register="showLoginPopup = false; showRegisterPopup = true">

      <login/>
    </popup>

    <popup v-show="!user" :open="showRegisterPopup"
      @close="showRegisterPopup = false">

      <register/>
    </popup>
  </div>
</template>

<script>
import { mapState } from 'vuex';
import MainNavigation from '@/components/MainNavigation.vue';
import Cart from '@/components/Cart.vue';
import Popup from '@/components/Popup.vue';
import Login from '@/components/popups/Login.vue';
import Register from '@/components/popups/Register.vue';


export default {

  components: {
    MainNavigation,
    Cart,
    Popup,
    Login,
    Register,
  },

  data() {
    return {
      showLoginPopup: false,
      showRegisterPopup: false,
      isAuthenticated: false
    };
  },

  computed: {
    ...mapState({
      user: state => state.user.loggedInUser,
    }),
  },

  //FIXME
  //Not necessary
  mounted() {
    let jqueryScript1 = document.createElement('script');
    jqueryScript1.setAttribute('src', 'https://code.jquery.com/jquery-3.3.1.slim.min.js');
    let jqueryScript2 = document.createElement('script');
    jqueryScript2.setAttribute('src', 'https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js');
    let jqueryScript3 = document.createElement('script');
    jqueryScript3.setAttribute('src', 'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js');
    document.head.append(jqueryScript1);
    document.head.append(jqueryScript2);
    document.head.append(jqueryScript3);
  }
};
</script>
