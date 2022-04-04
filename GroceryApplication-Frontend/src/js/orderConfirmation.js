export default {
  name: "OrderConfirmation",

  data() {
    return {
      customerEmail: '',
      orderNumber: ''
    };
  },

  mounted() {
    let data = this.$route.params.data;
    this.customerEmail = data['email_param'];
    this.orderNumber = data['order_id_param'];
  },

  methods: {
    viewOrders() {
      this.$router.push({ name: 'OrderHistory' })
    }
  }
}

