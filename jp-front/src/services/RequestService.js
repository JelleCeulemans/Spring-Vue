
import axios from 'axios';

export default {
  init(baseURL) {
    axios.defaults.baseURL = baseURL;
    axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
    axios.defaults.headers.common['Content-Type'] = 'application/json';
  },
  removeHeaders() {
    axios.defaults.headers.common = {};
  },
};
