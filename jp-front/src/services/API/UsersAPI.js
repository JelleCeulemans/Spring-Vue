import axios from 'axios';

const ENDPOINT_AUTH = 'http://localhost:8762/auth/';

export default {
  getAuthorizationToken(data) {
    return axios({
      method: 'post',
      url: 'authenticate',
      baseURL: ENDPOINT_AUTH,
      data,
    });
  },
  renewAuthorizationToken() {
    return axios({
      method: 'get',
      url: 'reauthenticate',
      baseURL: ENDPOINT_AUTH,
    });
  },
  getWhereUsername(username) {
    return axios.get(`user/${username}`);
  },
};
