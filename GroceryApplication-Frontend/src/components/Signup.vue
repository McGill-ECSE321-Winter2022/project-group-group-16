<template>
  <form>
    <h1>Sign Up</h1>

    <div class="row">
      <div class="col">
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
      </div>
      <div class="col">
        <p><br />Addresss Information</p>
        <label>Street Number :</label>
        <input type="number" required v-model="streetNumber" />

        <label>Street Name :</label>
        <input type="text" required v-model="streetName" />

        <label>City :</label>
        <input type="text" required v-model="city" />

        <label>Province :</label>
        <input type="text" required v-model="province" />

        <label>Country :</label>
        <input type="text" required v-model="country" />

        <label>Postal Code :</label>
        <input type="text" required v-model="postalCode" />
      </div>
    </div>
    <div class="button" align="center">
      <button class="submit" type="submit" @click="signup">Sign up</button>
    </div>
  </form>
</template>

<script src="../js/signup.js"></script>

<style scoped src="../css/signup.css"></style>
