import axios from 'axios'
var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default{
    name: 'ItemManager',

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
            errorProduct: '',
            newProduct: '',
            image:'',
            applicationId:'',
            categoryId:'',
            name:'',
            description:'',
            price:'',
            weight:'',
            volume:'',
            availability:'',
            isRefundable:'',
            avaQuantity:'',
        }
    },

    methods: {
        createProduct: function (image,applicationId,categoryId,name,description,price,weight,volume,availability,isRefundable,avaQuantity){
            
        }
    },

}
