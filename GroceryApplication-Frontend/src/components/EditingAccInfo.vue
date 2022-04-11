<template>
  <form>
    <h1>Editing Account Information</h1>

    <label>email : {{email}}</label>

    <div class="row">
      <div class="col">
        <label>username :</label>
        <input type="text" required v-model="username"/>
      </div>
    </div>


<!--    <input type="email" v-model="email" />-->

    <label> password :</label>
    <input type="password" required v-model="password" />

    <div class="row">
      <div class="col">
        <label>First Name :</label>
        <input type="text" required v-model="firstName" />
      </div>
      <div class="col">
        <label>Last Name :</label>
        <input type="text" required v-model="lastName" />
      </div>
    </div>

    <label>Date of birth :</label>
    <input type="date" required v-model="date" />

    <div class="row">
      <div class="col">
        <div class="button" align="center">
          <a href="#/login">
            <button class="submit" type="submit" @click="deleteAccount"> Delete </button>
            </a>
        </div>
      </div>
      <div class="col">
        <div class="button" align="center">
          <a href="#/accountinfo">
            <button class="submit" type="button" @click="editAccount"> Save </button>
          </a>
        </div>
      </div>
    </div>


  </form>
</template>

<script src="../js/editaccount.js"></script>

<style scoped src="../css/form.css"></style>
