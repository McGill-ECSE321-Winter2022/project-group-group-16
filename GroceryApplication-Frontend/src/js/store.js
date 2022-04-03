import { AXIOS } from "./axiosinst";

export default {
    name: "Store",

    /**
     * declaration of the page's data
     */
    data() {
        return {
            name: "",
            weekDayOpening: "",
            weekDayClosing: "",
            weekEndOpening: "",
            weekEndClosing: "",
            streetNumber: "",
            streetName: "",
            city: "",
            province: "",
            country: "",
            postalCode: "",
            error: "",
        };
    },
    methods: {
        store: async function () {
            try {
                await this.createStore();
                await this.createAddress();
            } catch (e) {
                console.log(e.response)
                if (e.response) {
                    this.error = e.response.data.message
                }
            }

            if (this.errorUser === "") {
                console.log(this.name, " store created!")
                localStorage.setItem("name", this.name)
            } else {
                alert("Store creation failed")
                this.error = ""
            }
        },

        createStore: async function(){
            //create store
            const res = await AXIOS.post(`/store/?name=${this.name}&weekDayOpening=${this.weekDayOpening}&weekDayClosing=${this.weekDayClosing}&weekEndOpening=${this.weekEndOpening}&weekEndClosing8=${this.weekEndClosing}`)
        },

        createAddress: async function () {
            // create address
            const res = await AXIOS.post(`/address/?streetNumber=${this.streetNumber}&streetName=${this.streetName}&province=${this.province}&city=${this.city}&country=${this.country}&postalCode=${this.postalCode}`)
            this.addressId = res.data.id
        },
    },
};