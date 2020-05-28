import axios from 'axios';

export default {
  getProducts(data) {
    return axios.get('/products/all');
  },
  getProduct(productID) {
    return axios.get('/products/product/' + productID);
  },
  changeProduct(data) {
    return axios.put('/products/product', data);
  },
  productPost(data) {
    return axios.post('/products/product', data);
  },
  deleteProduct(id) {
    return axios.delete('/products/product/' + id);
  },
};
