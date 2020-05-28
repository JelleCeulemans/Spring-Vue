import axios from 'axios';

export default {
  getAllFavorites() {
    return axios.get('/favorites/all');
  },
  loadFavotitesByUserID(id) {
    return axios.get('/favorites/user/' + id);
  },
  examplePut(data) {
    return axios.put('', data);
  },
  saveFavorite(data) {
    return axios.post('/favorites/favorite', data);
  },
  deleteFavorite(id) {
    return axios.delete('/favorites/favorite/' + id);
  }
};
