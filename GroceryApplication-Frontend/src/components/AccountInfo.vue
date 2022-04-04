<template>
  <form>
    <h1>Account Information</h1>
    
    <div class="row">
      <div class="col">
        <label>role :</label>
        <p> customer </p>
      </div>
      <div class="col">
        <label>username :</label>
        <p> {{username}} </p>
        <!-- <p> username </p> -->
      </div>
    </div>

    <label>email :</label>
    <p> {{email}} </p>
    <!-- <p> email </p> -->

    <label>password :</label>
    <p> {{password}} </p>

    <div class="row">
      <div class="col">
        <label>First Name :</label>
        <p> {{firstName}} </p>
        <!-- <p> firstName </p> -->
      </div>
      <div class="col">
        <label>Last Name :</label>
        <p> {{lastName}} </p>
        <!-- <p> lastName </p> -->
      </div>
    </div>

    <label>Date of birth :</label>
    <p> {{date}} </p>
    <!-- <p> date </p> -->

    <div class="button" align="center">
      <a href="#/editingaccinfo">
      <button class="submit" type="button" @click="account"> Edit </button>
      </a>
    </div>
  </form>
</template>

<script src="../js/account.js"></script>

<style scoped src="../css/form.css"></style>