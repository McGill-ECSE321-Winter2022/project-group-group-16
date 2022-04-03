<template>
  <div class="Center">
    <form>
      <h1>Welcome</h1>
      <label>Email :</label>
      <input type="text" required v-model="email" />

      <label>Password :</label>
      <input type="password" required v-model="password" />
      <div class="button" align="center">
        <button class="submit" type="submit" @click="login">Login</button>
      </div>
    </form>
    <div class="button" align="center">
      <a href="#/signup">
        <button>Don't have an account? Signup</button>
      </a>
    </div>
  </div>
</template>

<script src="../js/login.js"></script>

<style scoped src="../css/form.css"></style>
