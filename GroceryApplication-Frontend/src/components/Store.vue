<template>
  <form>
    <h1>Store information</h1>
    <label>Store Name :</label>
    <input type="text" required />

    <h5> <br /> Hours </h5>
    <div class="row">
      <div class="col">
        <p> <br /> Weekdays </p>
      </div>
      <div class="col">
        <label>opening hours :</label>
        <input type="time" required />
      </div>
      <div class="col">
        <label>closing hours :</label>
        <input type="time" required />
      </div>
    </div>

    <div class="row">
      <div class="col">
        <p> <br /> Weekends </p>
      </div>
      <div class="col">
        <label>opening hours :</label>
        <input type="time" required />
      </div>
      <div class="col">
        <label>closing hours :</label>
        <input type="time" required />
      </div>
    </div>

    <h5> <br /> Address </h5>
    <div class="row">
      <div class="col">
        <label>Street Number :</label>
        <input type="number" required />
      </div>
      <div class="col">
        <label>Street Name :</label>
        <input type="text" required />
      </div>
    </div>
    <div class="row">
      <div class="col">
        <label>City :</label>
        <input type="text" required v-model="city" />
      </div>
      <div class="col">
       <label>Province :</label>
        <input type="text" required v-model="province" />
      </div>
    </div>
    <div class="row">
      <div class="col">
       <label>Country :</label>
        <input type="text" required v-model="country" />
      </div>
      <div class="col">
       <label>Postal Code :</label>
        <input type="text" required v-model="postalCode" />
      </div>
    </div>

    <div class="button" align="center">
      <button class="submit" type="submit"> Save </button>
    </div>
  </form>
</template>

<script src="../js/store.js"></script>

<style scoped src="../css/form.css"></style>
