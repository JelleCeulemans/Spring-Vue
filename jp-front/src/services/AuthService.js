import axios from 'axios';
import TokenService from './TokenService';
import UsersAPI from './API/UsersAPI';

export default {
  login(credentials) {
    return UsersAPI.getAuthorizationToken(credentials)
      .then((response) => {
        if (response.data.token) {
          TokenService.setToken(response.data.token);
          this.setAuthorizationHeader();
        }
      });
  },
  isLoggedIn() {
    return !!TokenService.getToken();
  },
  logout() {
    TokenService.removeToken();
    this.removeAuthorizationHeader();
  },
  renew() {
    if (!this.isLoggedIn()) throw new Error('Cannot renew authentication because user is not authenticated.');

    return UsersAPI.renewAuthorizationToken()
      .then((response) => {
        if (response.data.token) {
          TokenService.setToken(response.data.token);
          this.setAuthorizationHeader();
        }
        return response;
      });
  },
  setAuthorizationHeader() {
    axios.defaults.headers.common.Authorization = `Bearer ${TokenService.getToken()}`;
  },
  removeAuthorizationHeader() {
    axios.defaults.headers.common.Authorization = undefined;
  },
};
