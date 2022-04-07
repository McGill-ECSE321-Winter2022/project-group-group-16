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
            <a href="#/homescreenEmployee" class="navbar__links" id="products-page" @click="homeScreenEmployee">Home</a>
          </li>
        </ul>
      </div>
    </nav>
</header>
<div class="container">
    <h4 class="label">Employee Profile</h4>
    <div class="spacer"> </div>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Hired Date</th>
        <th scope="col">Salary</th>
        <th scope="col">Status</th>
      </tr>
      </thead>
      <tbody >
        <v-tr v-for="product in displayData" :key="product.id" :row="product">
          <td>{{ product.name }}</td>
          <td>{{ product.availability }}</td>
          <td>{{ product.refundable }}</td>
          <td>{{ product.avaQuantity }}</td>
        </v-tr>
      </tbody>
    </table>
  </div>
<div class="container">
    <h4 class="label">Shift schedule</h4>
    <div class="spacer"></div>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th scope="col">Day</th>
        <th scope="col">Shift Type</th>
      </tr>
      </thead>
      <tbody >
        <v-tr v-for="product in displayData" :key="product.id" :row="product">
          <td>{{ product.name }}</td>
          <td>{{ product.availability }}</td>
        </v-tr>
      </tbody>
    </table>
  </div>
</body>
</template>

<style scoped src="../css/shift.css"></style>
<style scoped src="../css/form.css"></style>