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

                /**
                 * attempt to login using get request to the backend
                 */
                const res = await AXIOS.get(`/getGroceryUserbyEmail/${this.email}`)

                if (this.password != res.data.password) {
                    alert("incorrect username or password")
                } else {
                    console.log(this.email, " logged in!")
                    localStorage.setItem("email", this.email)
                }

            } catch (e) {
                console.log(e.response.data, this.errorUser)
                this.errorUser = e.response.data.message;
                alert("incorrect username or password")
            };
        },

    },
};