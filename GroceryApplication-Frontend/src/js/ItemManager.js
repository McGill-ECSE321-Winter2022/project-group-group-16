import { AXIOS } from "./axiosinst";
//ITEMMANAGER
export default {
  name: "ItemManager",

  /**
   * declaration of the page's data
   */
  data() {
    return {
        barcode: "",
        image: "",
        applicationId: "",
        categoryId: "",
        name: "",
        description: "",
        price: "",
        weight: "",
        volume: "",
        availability: "",
        isRefundable: "",
        avaQuantity: "",

    };
  },
  methods: {
    itemManagerDelete: async function () {
        try {
          await this.deleteProduct
  
        } catch (e) {
          console.log(e.response)
          if (e.response) {
            this.errorUser = e.response.data.message
          }
        }
  
        // if (this.errorUser === "") {
        //   console.log(this.paymentCode, " payed!")
        //
        //   let data = {
        //     order_id_param: this.orderId,
        //     email_param: this.email
        //   }
        //   await this.$router.push({name: 'OrderConfirmation', params: {data}})
        // } else {
        //   alert("Payment failed!")
        //   console.log(this.errorUser);
        //   this.errorUser = ""
        // }
      },

    itemManagerUpdate: async function () {
      try {
        await this.updateProduct

      } catch (e) {
        console.log(e.response)
        if (e.response) {
          this.errorUser = e.response.data.message
        }
      }

      // if (this.errorUser === "") {
      //   console.log(this.paymentCode, " payed!")
      //
      //   let data = {
      //     order_id_param: this.orderId,
      //     email_param: this.email
      //   }
      //   await this.$router.push({name: 'OrderConfirmation', params: {data}})
      // } else {
      //   alert("Payment failed!")
      //   console.log(this.errorUser);
      //   this.errorUser = ""
      // }
    },

    updateProduct: async function () {
        //update Product
        const res = await AXIOS.put(
        `/product/barcode=${this.barcode}&
        image=${this.image}&applcationId=${this.applicationId}&
        categoryId=${this.categoryId}&
        name=${this.name}&
        description=${this.description}&
        price=${this.price}&
        weight=${this.weight}&
        volume=${this.volume}&
        availability=${this.availability}&
        isRefundable=${this.isRefundable}&
        avaQuantity=${this.avaQuantity}`)
  
      },
      deleteroduct: async function () {
        //delete Product
        const res = await AXIOS.delete(`/product/barcode=${this.barcode}`)
        
      },
    },
  };