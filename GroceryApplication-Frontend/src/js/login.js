import { AXIOS } from "./axiosinst";
export default {
    name: "Login",

    /**
     * declaration of the page's data
     */
    data() {
        return {
            email: "",
            password: "",
            errorUser: "",
        };
    },
    methods: {

        /**
         * login method
         */
        login: async function () {
            try {
                localStorage.setItem("usertype", "")
                localStorage.setItem("email", "")


                /**
                 * attempt to login using get request to the backend
                 */
                const res = await AXIOS.get(`/getGroceryUserbyEmail/${this.email}`)

                if (this.password == res.data.password) {
                    try {
                        const _ = await AXIOS.get(`/getCustomerByEmail/${this.email}`)
                        localStorage.setItem("usertype", "customer")
                        localStorage.setItem("email", this.email)
                    } catch (ignore) {
                        try {
                            const _ = await AXIOS.get(`/employee/email/${this.email}`)
                            localStorage.setItem("usertype", "employee")
                            localStorage.setItem("email", this.email)
                        }
                        catch (ignore) {
                        }
                    }
                } else {
                    alert("incorrect username or password")
                }

            } catch (e) {
                console.log(e.response.data, this.errorUser)
                this.errorUser = e.response.data.message;
                alert("incorrect username or password")

            };

            console.log(localStorage.getItem("email"), " logged in!")
            console.log(localStorage.getItem("usertype"))

        },

    },
};