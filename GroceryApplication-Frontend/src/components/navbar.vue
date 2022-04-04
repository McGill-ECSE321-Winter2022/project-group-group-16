<template>
<header id="header">
  <nav class="navbar">
      <div class="navbarcontainer">
        <img src="https://chailly-food.ch/wp-content/uploads/2020/06/market-5043895_1280.png" alt="logo" id="navbarlogo">
        <div class="navbartoggle" id="mobile-menu">
          <span class="bar"></span> <span class="bar"></span>
          <span class="bar"></span>
        </div>
        <ul class="navbarmenu">
          <li class="navbaritem">
            <a href="#home" class="navbar__links" id="home-page">Grocely</a>
          </li>
          <li class="navbaritem">
            <a href="#products" class="navbar__links" id="products-page">Cart</a>
          </li>
          <li class="navbaritem">
            <a href="#about" class="navbar__links" id="about-page">Profile page</a>
          </li>
        </ul>
      </div>
    </nav>
</header>
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

<style scoped src="../css/navbar.css"></style>