import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Orders from './views/orders/Orders.vue';
import Order from './views/orders/Order.vue';
import Favorites from './views/Favorites.vue';

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/products',
      name: 'products',
      component: () => import(/* webpackChunkName: "products" */ './views/products/Products.vue'),
    },
    {
      path: '/product',
      name: 'product',
      component: () => import(/* webpackChunkName: "product" */ './views/products/Product.vue'),
    },
    {
      path: '/order',
      name: 'order',
      component: Order,
    },
    {
      path: '/orders',
      name: 'orders',
      component: Orders,
    },
    {
      path: '/favorites',
      name: 'favorites',
      component: Favorites,
    },
  ],
});
