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
        };
    },

    props : [],

    methods: {

        editaccount: async function () {
            try {
                await this.modifyUser();
            } catch (e) {
                console.log(e.response)
                if (e.response) {
                    this.errorUser = e.response.data.message
                }
            }

            if (this.errorUser === "") {
                localStorage.getItem("email", this.email)
                let data = {
                    username_param: this.username,
                    password_param: this.password,
                    firstName_param: this.firstName,
                    lastName_param: this.lastName,
                    date_param: this.date
                  }
                this.$router.push({name: 'account', params: {data}})
            } else {
                alert("Edit failed")
                this.errorUser = ""
            }
        },

        modifyUser: async function () {
            const res = await AXIOS.put(`/gorceryUser/` + this.email + `/?username=${this.username}&password=${this.password}&firstName=${this.firstName}&lastName=${this.lastName}&date=${this.date}`)
        },

        deleteaccount: async function (){
            const res = await AXIOS.delete(`/gorceryUser/` + this.email)
        }
    },
};