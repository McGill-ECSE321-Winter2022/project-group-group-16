import { AXIOS } from "./axiosinst";

export default {
    name: "EditAccount",

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
            errorUser: "",
            userData: []
        };
    },

    mounted() {
      this.email = localStorage.getItem("email");
      let wtv = this.displayUser();
    },

    methods: {

        editAccount: async function () {
            try {
                await this.modifyUser();
            } catch (e) {
                console.log(e.response)
                if (e.response) {
                    this.errorUser = e.response.data.message
                }
            }

            if (this.errorUser === "") {
                alert("Information updated!")
            } else {
                alert("Edit failed")
                this.errorUser = ""
            }
        },

        displayUser: async function () {
            let res = await AXIOS.get('/getGroceryUserbyEmail/' + this.email);
            this.userData = res.data;
            this.username = this.userData.username;
            this.password = this.userData.password;
            this.firstName = this.userData.firstName;
            this.lastName = this.userData.lastName;
            this.date = this.userData.dateOfBirth;
        },

        modifyUser: async function () {
            const res = await AXIOS.put(`/gorceryUser/` + this.email + `/?username=${this.username}&password=${this.password}&firstName=${this.firstName}&lastName=${this.lastName}&date=${this.date}`)
        },

        deleteAccount: async function (){
            alert("Account deleted!");
            this.$router.push({name: 'Login'});
            // const res = await AXIOS.delete(`/deleteGroceryUser/` + this.email)
        }
    },
};
