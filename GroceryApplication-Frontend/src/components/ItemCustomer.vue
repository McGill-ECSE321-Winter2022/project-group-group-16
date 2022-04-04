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
            <a href="#products" class="navbar__links" id="products-page">Cart</a>
          </li>
          <li class="navbar__item">
            <a href="#about" class="navbar__links" id="about-page">Profile</a>
          </li>
        </ul>
      </div>
    </nav>
</header>
<div class="main" id="about">
      <div class="main__container">
        <div class="main__image__container">
          <img src="https://img.freepik.com/free-psd/milk-box-mockup-template_1051-2941.jpg">
        </div>
        <div class="main__content">
          <h1>Milk</h1>
          <p>4.99$</p>
          <p>Dairy Products</p>
          <p>This 2% milk is 100% organic from the amazing cows of DannyFarmy</p>
          <p>Weight: 1kg</p>
          <p>Volume: 1L</p>
          <p>Available online and in store</p>
          <p>Remaining quantity: 18</p>
          <p>Not refundable</p>
          <div class="products__button"><button>Add to Cart</button></div>
        </div>
      </div>
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

<style scoped src="../css/itemCustomer.css"></style>