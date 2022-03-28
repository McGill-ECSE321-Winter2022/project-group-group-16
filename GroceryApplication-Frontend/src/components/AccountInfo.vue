<template>
  <form>
    <h1>Account Information</h1>
    
    <div class="row">
      <div class="col">
        <label>role :</label>
        <p> Customer </p>
      </div>
      <div class="col">
        <label>username :</label>
        <p> myusername123 </p>
      </div>
    </div>

    <label>email :</label>
    <p> username@store.com </p>

    <div class="row">
      <div class="col">
        <label>First Name :</label>
        <p> User </p>
      </div>
      <div class="col">
        <label>Last Name :</label>
        <p> Name </p>
      </div>
    </div>

    <label>Date of birth :</label>
    <p> 2001-01-01 </p>

    <div class="button" align="center">
      <button onclick="location.href='http://127.0.0.1:8087/?#/editingaccinfo'" type="button" > Edit </button>
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

<style scoped src="../css/form.css"></style>