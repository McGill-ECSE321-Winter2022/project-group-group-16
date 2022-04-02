<template>
<body>
<header id="header">
  <nav class="navbar">
      <div class="navbar__container">
        <img src="https://chailly-food.ch/wp-content/uploads/2020/06/market-5043895_1280.png" alt="logo" id="navbarlogo">
        <div class="navbar__toggle" id="mobile-menu">
          <span class="bar"></span> <span class="bar"></span>
          <span class="bar"></span>
        </div>
        <ul class="navbar__menu">
          <li class="navbar__item">
            <a href="#products" class="navbar__links" id="products-page">Home</a>
          </li>
          <li class="navbar__item">
            <a href="#about" class="navbar__links" id="about-page">Profile</a>
          </li>
        </ul>
      </div>
    </nav>
</header>
<div>
    <h1>Employee Profile</h1>
    <p>Name:</p>
    <p>Hired date:</p>
    <p>Pay:</p>
    <form action="/action_page.php">
    <label for="status">Modify status</label>
    <select name="status" id="status">
    <option value="volvo">Active</option>
    <option value="saab">Quit</option>
    <option value="opel">Banned</option>
  </select>
  <br><br>
  <input type="submit" value="Submit">
</form>
</div>
</body>
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

<style scoped src="../css/employeeProfile.css"></style>