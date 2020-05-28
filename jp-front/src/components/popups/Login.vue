<style lang="scss">
  .login {
    padding:50px 30px;

    input {
      max-width: 400px;
    }
  }
</style>

<template>
  <div class="login">
    <h2 class="mb-4"><span class="sr-only">Login, </span>Welkom terug</h2>

    <form @submit.prevent="login">
      <div v-if="invalidCredentials" class="alert alert-danger" role="alert">
        Het wachtwoord is incorrect, of je account werd niet gevonden.
      </div>

      <div class="form-group">
        <label for="login-emailInput">E-mailadres</label>
        <input autocomplete="username" v-model="credentials.username" type="email"
          class="form-control" id="login-emailInput" placeholder="Wat is je e-mailadres?"
          :disabled="processing || user">
      </div>

      <div class="form-group">
        <label for="login-passwordInput">Wachtwoord</label>
        <input autocomplete="current-password" v-model="credentials.password"
          type="password" class="form-control" id="login-passwordInput" placeholder="Je wachtwoord"
          :disabled="processing || user">
      </div>

      <button type="submit" class="btn btn-primary" :disabled="processing || user">
        Login
        <span v-if="processing || user" class="spinner-border spinner-border-sm"
          role="status" aria-hidden="true"></span>
      </button>
    </form>

    <p class="mt-4 mb-0 small">
      Nieuw hier?
      <a @click.prevent="$parent.$emit('register')" href="JavaScript:Void(0);">Registreren</a>
    </p>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data() {
    return {
      processing: false,
      invalidCredentials: false,
      credentials: {
        username: 'r0699180@student.thomasmore.be',
        password: 'password',
      },
    };
  },

  computed: {
    ...mapState({
      user: state => state.user.loggedInUser,
    }),
  },

  methods: {
    login() {
      this.invalidCredentials = false;
      this.processing = true;

      this.$store.dispatch('user/login', this.credentials)
        .then(() => { // User was logged in.
          this.$parent.$emit('close');
        })
        .catch((err) => { // Invalid credentials.
        console.log(err)
          this.invalidCredentials = true;
        })
        .finally(() => {
          this.processing = false;
        });
    },
  },
};
</script>
