export default {
  name: "OrderHistory",

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
