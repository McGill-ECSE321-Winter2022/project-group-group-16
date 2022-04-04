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
        };
    },
    props: {
        username: Text,
        firstName: Text,
        lastName: Text,
        date: Date,
        password: Text
      },

    mounted() {
        let data = this.$route.params.data;
        this.username = data['username_param'];
        this.password = data['password_param'];
        this.firstName = data['firstName_param'];
        this.lastName = data['lastName_param'];
        this.date = data['date_param'];
      },

    methods: {

        account: async function () {
            try {
                await this.getUser();
            } catch (e) {
                console.log(e.response)
                if (e.response) {
                    this.errorUser = e.response.data.message
                }
            }

            if (this.errorUser === "") {
                localStorage.getItem("email", this.email)
                // let data = this.$route.params.data;
                // this.firstName = data['firstName_param'];
                // this.lastName = data['lastName_param'];
                // this.date = data['date_param'];

                this.$router.push('editaccount')
            } else {
                alert("Edit failed")
                this.errorUser = ""
            }
        },

        getUser: async function () {
            var self = this
            obj.email = route.params.email;
            const res = await AXIOS.get(`/getGroceryUserbyEmail/?email=${this.email}`)
            .then((response) => {
                obj.user = response.data;
            })
            .catch(function (e) {
                if (e.response.data.message === "The user cannot be null") {
                    self.$router.push('/404')
                }

                console.log(e);
            });
        },    


        
    },
};