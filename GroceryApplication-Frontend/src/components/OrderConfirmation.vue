<template>
  <div class="container">
    <h1>Thank you for your order!</h1>
    <div class="spacer"></div>
    <p>You will receive a confirmation email shortly at: <b>{{ customerEmail }}</b></p>
    <p>Your order number is: <b>{{ orderNumber }}</b></p>
    <button @click="viewOrders">View all your orders</button>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        customerEmail: 'example@mail.com',
        orderNumber: '123456'
      };
    },
    methods: {
      viewOrders() {
        this.$router.push({ name: 'OrderHistory' })
      }
    }
  }
</script>

<style scoped src="../css/order.css"></style>
