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
  <form>
    <div class="row">
      <div class="col">
        <h5>Product Information</h5>
        <label>Name:</label>
        <input type="text" v-model="name" />
        <label>Image Source:</label>
        <input type="text" v-model="image" />
        <label>Price:</label>
        <input type="number" step="0.01" v-model="price" />
        <label>Category:</label>
        <input type="number" step="1" v-model="categoryId" />
        <label>Description:</label>
        <input type="text" v-model="description" />
        <div class="parent">
          <div class="double">
            <label>Weight:</label>
            <input type="number" step="0.01" v-model="weight" />
          </div>
          <div class="hor-spacer"></div>
          <div class="double">
            <label>Volume:</label>
            <input type="number" step="0.01" v-model="volume" />
          </div>
        </div>
        <label>Available:</label>
        <input type="text" v-model="availability" />
        <label>Remaining Quantity:</label>
        <input type="number" step="1" v-model="avaQuantity" />
        <label>Refundable:</label>
        <input type="text" v-model="isRefundable" />
        <div class="ver-spacer"></div>
      </div>
    </div>
    <div class="button" align="center">
      <button class="submit" type="submit" @click="addProduct()">Add product</button>
    </div>
  </form>
</body>
</template>

<script src="../js/ItemCreateManager.js"></script>

<style scoped src="../css/itemCreateManager.css"></style>