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
            <a href="#products" class="navbar__links" id="products-page" @click="employee">Employees</a>
          </li>
          <li class="navbar__item">
            <a href="#about" class="navbar__links" id="about-page" @click="accountInfo">Profile</a>
          </li>
        </ul>
      </div>
    </nav>
</header>
<div class="container">
    <h4 class="label">Products</h4>
    <div class="spacer"> </div>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Availability</th>
        <th scope="col">Refundable</th>
        <th scope="col">Quantity left</th>
        <th scope="col">Price</th>
        <th scope="col">Edit</th>
      </tr>
      </thead>
      <tbody slot="body" slot-scope="{ displayData }">
        <v-tr v-for="product in displayData" :key="product.id" :row="product">
          <td>{{ product.name }}</td>
          <td>{{ product.availability }}</td>
          <td>{{ product.refundable }}</td>
          <td>{{ product.avaQuantity }}</td>
          <td>{{ product.price }}</td>
          <td><button @click="itemManager">Edit</button></td>
        </v-tr>
      </tbody>
    </table>
    <div class="add__button" id="add_button" @click="itemCreateManager"><button>Add Item</button></div>
  </div>
</body>
</template>

<script src="../js/homeScreenCustomer.js">
</script>

<style scoped src="../css/homeScreenCustomer.css"></style>
<style scoped src="../css/form.css"></style>
