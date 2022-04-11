import { AXIOS } from "./axiosinst";

export default {
    name: "Account",

    /**
     * declaration of the page's data
     */
    data() {
        return {
            email: "",
            username: "",
            password: "",
            firstName: "",
            lastName: "",
            date: "",
            errorUser: "",
            userData: []
        };
    },

    mounted() {
        this.email = localStorage.getItem("email");
        let wtv = this.account();
    },

    methods: {

        account: async function () {
            try {
                let res = await AXIOS.get('/getGroceryUserbyEmail/' + this.email);
                this.userData = res.data;
            } catch (e) {
                console.log(e.response)
                if (e.response) {
                    this.errorUser = e.response.data.message
                }
            }

            if (this.errorUser === "") {
                console.log(this.userData);
                this.username = this.userData.username;
                this.password = this.userData.password;
                this.firstName = this.userData.firstName;
                this.lastName = this.userData.lastName;
                this.date = this.userData.dateOfBirth;

            } else {
                alert("Unable to load user!")
                this.errorUser = ""
            }
        },
    },
};
