<template>
  <form>
    <h1>Sign Up</h1>

    <div class="row">
      <div class="col">
        <p><br />Personal Information</p>
        <label>First Name :</label>
        <input type="text" required />

        <label>Last Name :</label>
        <input type="text" required />

        <label>Date of birth :</label>
        <input type="date" required />

        <label>Username :</label>
        <input type="text" required />

        <label>Email :</label>
        <input type="email" required />

        <label>Password :</label>
        <input type="password" required />
      </div>
      <div class="col">
        <p><br />Addresss Information</p>
        <label>Street Number :</label>
        <input type="number" required />

        <label>Street Name :</label>
        <input type="text" required />

        <label>Street Name :</label>
        <input type="text" required />

        <label>City :</label>
        <input type="text" required />

        <label>Province :</label>
        <input type="text" required />

        <label>Country :</label>
        <input type="text" required />

        <label>Postal Code :</label>
        <input type="text" required />
      </div>
    </div>
    <div class="button" align="center">
      <button class="submit" type="submit">Sign up</button>
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

<style scoped src="../css/signup.css"></style>
