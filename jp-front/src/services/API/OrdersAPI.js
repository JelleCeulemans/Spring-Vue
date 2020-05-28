import axios from 'axios';

export default {
  getAllOrders() {
    return axios.get('/orders/all');
  },
  getOrderByUserId(id) {
    return axios.get('/orders/order/' + id);
  },
  changeOrder(data) {
    return axios.put('/orders/order', data);
  },
  placeOrder(data) {
    return axios.post('/orders/order', data);
  },
  deleteOrder(id) {
    return axios.delete('/orders/order/' + id);
  }
};
