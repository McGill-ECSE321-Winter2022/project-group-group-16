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
<div class="products" id="products">
      <h1>Products</h1>
      <div class="products__wrapper">
        <div class="products__card"><img src="https://img.freepik.com/free-psd/milk-box-mockup-template_1051-2941.jpg"  width="200" height="200">
          <h2>Milk: 4.99$</h2>
          <div class="products__button"><button>Add to Cart</button></div>
        </div>
        <div class="products__card"><img src="https://www.jessicagavin.com/wp-content/uploads/2019/02/carrots-7-1200.jpg" width="200" height="200">
          <h2>Carrotts: 2.99$</h2>
          <div class="products__button"><button>Add to Cart</button></div>
        </div>
        <div class="products__card"><img src="https://bakingamoment.com/wp-content/uploads/2020/01/IMG_7173-white-bread-2-500x500.jpg" width="200" height="200">
          <h2>Bread: 5.99$ </h2>
          <div class="products__button"><button>Add to Cart</button></div>
        </div>
        <div class="products__card"><img src="https://www.daringgourmet.com/wp-content/uploads/2018/01/Breakfast-Sausages-5-square-lighter-2.jpg" width="200" height="200">
          <h2>Sausages: 6.99$</h2>
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

<style scoped src="../css/homeScreenCustomer.css"></style>