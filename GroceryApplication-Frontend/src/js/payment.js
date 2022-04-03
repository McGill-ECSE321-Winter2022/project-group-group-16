import { AXIOS } from "./axiosinst";

export default {
  name: "Payment",

  /**
   * declaration of the page's data
   */
  data() {
    return {
      orderId: "123",
      paymentAmount: "13.99",
      paymentType: "CREDIT",
      paymentCode: "CV3S33FS8O",
      nameOnCard: "",
      cardNumber: "",
      expiryDate: "",
      securityCode: "",
      giftCardNumber: "",
      firstName: "",
      lastName: "",
      shippingAddress: "",
      city: "",
      postalCode: "",
      email: "",

    };
  },
  methods: {

    payment: async function () {
      try {
        //await this.createPayment();
        console.log("Created payment")
      } catch (e) {
        console.log(e.response)
        if (e.response) {
          this.errorUser = e.response.data.message
        }
      }

      if (this.errorUser === "") {
        console.log(this.paymentCode, " payed!")

        let data = {
          order_id_param: this.orderId,
          email_param: this.email
        }
        await this.$router.push({name: 'OrderConfirmation', params: {data}})
      } else {
        //alert("Payment failed!")
        this.errorUser = ""
      }
    },

    createPayment: async function () {
      //create payment
      const res = await AXIOS.post(`/payment/?orderId=${this.orderId}&amount=${this.paymentAmount}&paymentType=${this.paymentType}&paymentCode=${this.paymentCode}`)

    },
  },
};
