import { AXIOS } from "./axiosinst";
//ITEMCREATEMANAGER
export default {
    name: "",

    /**
     * declaration of the page's data
     */
    data() {
        return {
            imageCategory: "",
            nameCategory: "",
            descriptionCategory: "",
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

        addProduct: async function () {
            try {
                await this.createCategory;
                await this.createProduct;
            } catch (e) {
                console.log(e.response)
                if (e.response) {
                    this.errorUser = e.response.data.message
                }
            }

            // if (this.errorUser === "") {
            //     console.log(this.email, " Product Created!")
            //     localStorage.setItem("email", this.email)
            //     localStorage.setItem("usertype", "customer")
            //     this.$router.push('login')
            // } else {
            //     alert("Signup failed")
            //     this.errorUser = ""
            // }
        },

        createCategory: async function () {
            const res = await AXIOS.post(`/category/?image=${this.imageCategory}&applicationId=0&name=${this.nameCategory}&lastName=${this.descriptionCategory}`)
        },

        createProduct: async function () {
            // create product
            const res = await AXIOS.put(
                `image=${this.image}&applcationId=${this.applicationId}&
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
    },
};
