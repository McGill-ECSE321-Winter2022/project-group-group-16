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
            <a href="#/homeScreenCustomer" class="navbar__links" id="products-page">Home</a>
          </li>
          <li class="navbar__item">
            <a href="#/accountInfo" class="navbar__links" id="about-page">Profile</a>
          </li>
        </ul>
      </div>
    </nav>
</header>
<div class="container">
    <h4 class="label">Cart</h4>
    <div class="spacer"> </div>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th scope="col">Name</th>
        <th scope="col">Quantity</th>
        <th scope="col">Price</th>

      </tr>
      </thead>
      <tbody slot="body" slot-scope="{ displayData }">
        <v-tr v-for="product in displayData" :key="product.id" :row="product">
          <td>{{ product.name }}</td>
          <td>{{ product.availability }}</td>
          <td>{{ product.refundable }}</td>
          <td>{{ product.avaQuantity }}</td>
          <td>{{ product.price }}</td>
          <td><button>Add to cart</button></td>
        </v-tr>
      </tbody>
    </table>
    <div class="button" align="right">
      
    <button class="submit" type="submit" @click="viewPayment">CHECKOUT</button>
     <p> Total Price: $20.96 </p>
    </div>

  </div>
</body>
</template>

<script>
export default {
    props: ["cart"], 
    methods: {
      viewPayment() {
        this.$router.push({ name: 'Payment' })
      },

    }

}
    
  
</script>







<style scoped src="../css/homeScreenCustomer.css"></style>
<style scoped src="../css/form.css"></style>