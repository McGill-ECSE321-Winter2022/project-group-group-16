import { AXIOS } from "./axiosinst";

export default {
    name: "Signup",

    /**
     * declaration of the page's data
     */
    data() {
        return {
            username: "",
            password: "",
            firstName: "",
            lastName: "",
            email: "",
            date: "",
            streetNumber: "",
            streetName: "",
            city: "",
            province: "",
            country: "",
            postalCode: "",
            errorUser: "",
        };
    },
    methods: {

        signup: async function () {
            try {
                await this.createUser();
                await this.createAddress();
                await this.createCustomer();
            } catch (e) {
                console.log(e.response)
                if (e.response) {
                    this.errorUser = e.response.data.message
                }
            }

            if (this.errorUser === "") {
                console.log(this.email, " logged in!")
                localStorage.setItem("email", this.email)
                localStorage.setItem("usertype", "customer")
                this.$router.push('login')
            } else {
                alert("Signup failed")
                this.errorUser = ""
            }
        },

        createUser: async function () {
            const res = await AXIOS.post(`/gorceryUser/?username=${this.username}&password=${this.password}&firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&date=${this.date}`)
        },

        createAddress: async function () {

            // create address
            const res = await AXIOS.post(`/address/?streetNumber=${this.streetNumber}&streetName=${this.streetName}&province=${this.province}&city=${this.city}&country=${this.country}&postalCode=${this.postalCode}`)
            this.addressId = res.data.id
        },

        createCustomer: async function () {

            // create customer
            const res = await AXIOS.post(`/customer/?applicationId=0&addressId=${this.addressId}&userEmail=${this.email}`)

        },
    },
};