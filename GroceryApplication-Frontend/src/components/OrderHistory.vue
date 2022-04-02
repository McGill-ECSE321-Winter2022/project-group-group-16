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
        <th scope="col">Refund</th>
        <th scope="col">View</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <th scope="row">#{{ order1.orderNumber }}</th>
        <td>{{ order1.orderStatus }}</td>
        <td>{{ order1.orderTotal }}</td>
        <td><button>Refund</button></td>
        <td><button>View</button></td>
      </tr>
      <tr>
        <th scope="row">#{{ order2.orderNumber }}</th>
        <td>{{ order2.orderStatus }}</td>
        <td>{{ order2.orderTotal }}</td>
        <td><button>Refund</button></td>
        <td><button>View</button></td>
      </tr>
      <tr>
        <th scope="row">#{{ order3.orderNumber }}</th>
        <td>{{ order3.orderStatus }}</td>
        <td>{{ order3.orderTotal }}</td>
        <td><button>Refund</button></td>
        <td><button>View</button></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        order1: {
          orderNumber:'5434',
          orderStatus: 'Delivered',
          orderTotal: '$19.99'
        },
        order2: {
          orderNumber:'7234',
          orderStatus: 'Cancelled',
          orderTotal: '$34.10'
        },
        order3: {
          orderNumber:'1234',
          orderStatus: 'Shipped',
          orderTotal: '$9.89'
        }
      };
    }
  }
</script>

<style scoped src="../css/order.css"></style>
