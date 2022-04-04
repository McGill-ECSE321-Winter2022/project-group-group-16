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
            <a href="#products" class="navbar__links" id="products-page" @click="homeScreenManager">Home</a>
          </li>
          <li class="navbaritem">
            <a href="#about" class="navbar__links" id="about-page" @click="accountInfo">Profile</a>
          </li>
        </ul>
      </div>
    </nav>
</header>
<div class="container">
    <h4 class="label">Employee</h4>
    <div class="spacer"> </div>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th scope="col">EmployeeId</th>
        <th scope="col">Hired Date</th>
        <th scope="col">Salary</th>
        <th scope="col">Status</th>
        <th scope="col">Modify</th>
      </tr>
      </thead>
      <tbody >
        <v-tr v-for="product in displayData" :key="product.id" :row="product">
          <td>{{ product.name }}</td>
          <td>{{ product.availability }}</td>
          <td>{{ product.refundable }}</td>
          <td>{{ product.avaQuantity }}</td>
          <td>{{ product.price }}</td>
          <td><button @click="shift">Modify</button></td>
        </v-tr>
      </tbody>
    </table>

       <div class="button" align="right">

    <button class="submit" type="submit" @click="employeeSignUp">Add employee</button>

    </div>

  </div>
</body>
</template>



<style scoped src="../css/homeScreenCustomer.css"></style>
<style scoped src="../css/form.css"></style>