import AuthService from '@/services/AuthService';
import UsersAPI from '@/services/API/UsersAPI';

const state = {
  loggedInUser: null,
};

const getters = {};

const actions = {
  login({ commit }, credentials) {
    return AuthService.login(credentials)
      .then(() => {
        UsersAPI.getWhereUsername(credentials.username)
          .then((response) => {
            commit('SET_LOGGED_IN_USER', response.data);
          });
      });
  },

  setLoggedInUser({ commit }, user) {
    commit('SET_LOGGED_IN_USER', user);
  },

  logout({ commit }) {
    AuthService.logout();
    commit('UNSET_LOGGED_IN_USER');
  },
};

const mutations = {
  SET_LOGGED_IN_USER(_state, user) {
    _state.loggedInUser = user;
  },

  UNSET_LOGGED_IN_USER(_state) {
    _state.loggedInUser = null;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
