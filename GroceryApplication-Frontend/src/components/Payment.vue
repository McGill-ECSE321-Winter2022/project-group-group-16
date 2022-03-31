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
        <label>Gift Card Number (if applicable):</label>
        <input type="text" required v-model="giftCardNumber" />
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
        <input type="text" required v-model="billingAddresss" />
        <label>Card Number:</label>
        <input type="text" required v-model="lastName" />
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
      <button class="submit" type="submit" @click="signup">Confirm payment</button>
    </div>
  </form>
</template>

<script>
</script>

<style scoped>
.row {
  background: #e5e9f0;
  overflow: hidden;
  padding: 10px;
  border-radius: 10px;
}
.col {
  float: left;
  width: auto;
}
.parent {
  width: auto;
}
.double {
  display: inline-block;
  vertical-align: center;
  width: auto;
}
.hor-spacer {
  width: 0px;
  display: inline-block;
  vertical-align: center;
}
.ver-spacer {
  height: 50px;
}
form {
  width: 500px;
  margin: 5px auto;
  background: #fff;
  text-align: left;
  padding: 40px;
  border-radius: 10px;
}
label {
  color: rgb(158, 158, 158);
  display:inline-block;
  margin: 25px 0 0px;
  text-transform: uppercase;
}
input {
  display: block;
  padding: 10px 6px;
  width: 100%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #ddd;
}
</style>