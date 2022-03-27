<template>
  <form>
    <h1>Welcome</h1>
    <label>Store Name :</label>
    <input type="text" required />

    <label>opening hour :</label>
    <input type="time" required />
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

<style scoped src="../css/store.css"></style>
