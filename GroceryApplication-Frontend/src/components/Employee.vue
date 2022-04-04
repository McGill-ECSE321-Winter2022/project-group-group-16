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
<div class="employees" id="employees">
  <h1>List of employees</h1>
  <div class="main__content">
    <li><a href="#employee">Noah Yeat</a></li>
    <li><a href="#employee">Bruce Wayne</a></li>
    <li><a href="#employee">Kanye West</a></li>
    <li><a href="#employee">Chris Rock</a></li>
  </div>
  <button>Add employee</button>
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

<style scoped src="../css/employee.css"></style>