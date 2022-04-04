<template>
  <tr>
    <th scope="row">#{{ order.orderNumber }}</th>
    <td>{{ order.orderStatus }}</td>
    <td>{{ order.orderTotal }}</td>
    <td><button>Refund</button></td>
    <td><button>View</button></td>
  </tr>
</template>

<script>
export default {
  name: "OrderHistoryTableElement",

  data() {
    return {
      order: {
        orderNumber:'',
        orderStatus: '',
        orderTotal: ''

      }
    };
  },
}
</script>

<style scoped src="../css/order.css"></style>
