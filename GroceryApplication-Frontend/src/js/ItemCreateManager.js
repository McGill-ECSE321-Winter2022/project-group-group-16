import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default{
    name: 'ItemCreateManager',

    created: function () {
        AXIOS.get('/getAllProduct')
            .then(response => {
                this.products = response.data
            })
            .catch(e => {
                this.errorProduct = e;
            })
    },

    
    data(){
        return{
            image:"",
            applicationId:"",
            categoryId:"",
            name:"",
            description:"",
            price:"",
            weight:"",
            volume:"",
            availability:"",
            isRefundable:"",
            avaQuantity:"",
            errorProduct:"",
        }
    },

    products: async function () {
        try {
          //await this.createPayment();
          console.log("Create product");
          console.log(this.errorUser);
        } catch (e) {
          console.log(e.response)
          if (e.response) {
            this.errorUser = e.response.data.message
          }
        }
    },

    methods: {
        createProduct: async function (){
            const res = await AXIOS.post(`/product/?image=${this.image}&name=${this.name}&pric=${this.price}&description=${this.description}&weight=${this.weight}&volume=${this.volume}&availability=${this.availability}&isRefundable=${this.isRefundable}&avaQuantity=${this.avaQuantity}`)
        }
    },

}
