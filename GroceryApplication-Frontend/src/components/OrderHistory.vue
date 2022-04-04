<template>
  <div class="container">
    <h4 class="label">Your order history: </h4>
    <div class="spacer"> </div>

    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th scope="col">Order number</th>
        <th scope="col">Status</th>
        <th scope="col">Price</th>
        <th scope="col">View</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="tableEntry in tableEntries">
        <th scope="row">#{{ tableEntry.orderId }}</th>
        <td>{{ tableEntry.orderStatus}}</td>
        <td>{{ price }}</td>
        <td><button>View</button></td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script src="../js/orderHistory.js"></script>

<style scoped src="../css/order.css"></style>
