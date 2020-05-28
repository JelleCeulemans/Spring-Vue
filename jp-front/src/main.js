import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store/index';
import './registerServiceWorker';
import RequestService from './services/RequestService';
import AuthService from '@/services/AuthService';
import UsersAPI from '@/services/API/UsersAPI';

const ENDPOINT_EDGE = 'http://localhost:8762/edge/';


import { library } from '@fortawesome/fontawesome-svg-core'
import { faTrash, faEdit, faShoppingCart, faHeart, faHeartBroken } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

Vue.component('font-awesome-icon', FontAwesomeIcon);
library.add(faTrash, faEdit, faShoppingCart, faHeart, faHeartBroken);

RequestService.init(ENDPOINT_EDGE);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App),

  beforeMount() {
    if (AuthService.isLoggedIn()) {
      AuthService.setAuthorizationHeader();

      AuthService.renew()
        .then((response) => {
          UsersAPI.getWhereUsername(response.data.username)
            .then((user) => {
              this.$store.dispatch('user/setLoggedInUser', user);
            });
        })
        .catch(() => { AuthService.logout(); });
    }
  },

}).$mount('#app');
