<template>
  <form>
    <h1>Welcome</h1>
    <label>Email :</label>
    <input type="text" required />

    <label>Password :</label>
    <input type="text" required />
    <div class="button" align="center">
      <button class="submit" type="submit">Login</button>
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
