<template>
  <form>


    <div class="row">
      <div class="col">
        <h5>Payment information</h5>
        <label>Name On Card:</label>
        <input type="text" required v-model="nameOnCard" />
        <label>Card Number:</label>
        <input type="text" required v-model="cardNumber" />
        <div class="parent">
          <div class="double">
            <label>Expiry Date:</label>
            <input type="date" required v-model="expiryDate" />
          </div>
          <div class="hor-spacer"></div>
          <div class="double">
            <label>Security Code:</label>
            <input type="text" required v-model="securityCode" />
          </div>
        </div>
        <div class="ver-spacer"></div>

        <h5>Shipping information</h5>
        <div>
          <div class="double">
            <label>First Name:</label>
            <input type="text" required v-model="firstName" />
          </div>
          <div class="hor-spacer"></div>
          <div class="double">
            <label>Last Name:</label>
            <input type="text" required v-model="lastName" />
          </div>
        </div>
        <label>Shipping Address:</label>
        <input type="text" required v-model="shippingAddress" />
        <div>
          <div class="double">
            <label>City:</label>
            <input type="Text" required v-model="city" />
          </div>
          <div class="hor-spacer"></div>
          <div class="double">
            <label>Postal Code:</label>
            <input type="text" required v-model="postalCode" />
          </div>
        </div>
        <label>Email: </label>
        <input type="email" required v-model="email" />
        <div class="ver-spacer"></div>
      </div>
    </div>
    <div class="button" align="center">
      <button class="submit" type="submit" @click="payment">Confirm payment</button>
    </div>
  </form>
</template>

<script src="../js/payment.js"> </script>

<style scoped src="../css/payment.css"> </style>
