<template>
  <form>
    <h1>Sign Up an Employee</h1>

    <p><br />Personal Information</p>
    <label>First Name :</label>
    <input type="text" required v-model="firstName" />

    <label>Last Name :</label>
    <input type="text" required v-model="lastName" />

    <label>Date of birth :</label>
    <input type="date" required v-model="date" />

    <label>Username :</label>
    <input type="text" required v-model="username" />

    <label>Email :</label>
    <input type="email" required v-model="email" />

    <label>Password :</label>
    <input type="password" required v-model="password" />

    <label>Pay :</label>
    <input type="number" step="0.01" v-model="pay" />

    <div class="button" align="center">
      <button class="submit" type="submit" @click="signupEmployee">Sign up</button>
    </div>
  </form>
</template>

<script src="../js/employeesignup.js"></script>

<style scoped src="../css/signup.css"></style>
