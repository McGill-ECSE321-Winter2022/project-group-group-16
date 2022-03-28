<template>
  <form>
    <h1>Editing Account Information</h1>
    
    <div class="row">
      <div class="col">
        <label>role :</label>
        <input type="text" required />
      </div>
      <div class="col">
        <label>username :</label>
        <input type="text" required />
      </div>
    </div>

    <label>email :</label>
    <input type="email" v-model="email" />

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
            <button class="submit"  type="submit"> Delete </button>
        </div>
      </div>
      <div class="col">
        <div class="button" align="center">
            <button onclick="location.href='http://127.0.0.1:8087/?#/accountinfo'" type="button"> Save </button>
        </div>
      </div>
    </div>

    
  </form>
</template>

<script>
export default {
  data() {
    return {
      formValues: {},
    };
  },
};
</script>

<style scoped src="../css/store.css"></style>