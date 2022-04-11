import { AXIOS } from "./axiosinst";

export default {
    name: "editProfile",

    /**
     * declaration of the page's data
     */
    data() {
        return {
            theTargetUser: {
                username: "",
                password: "",
                email: "",
                firstName: "",
                lastName: "",
                date: "",
            },
            theCurrentUser: {
                email: "",
            },

            newDate: "",
            newFirstName: "",
            newLastName: "",

            errorUser: "",
        };

    },

    /**
     * Initialization of the fields on page creation, using http requests to backend
     */
    created: async function() {
        var self = this;

        //get the target user
        AXIOS.get("/users/".concat(email))
            .then((response) => {
                this.theTargetUser = response.data;
            }).catch(function(e) {
                self.errorUser = e.response.data.message
            });

        //get the current user
        AXIOS.get("/users/".concat(localStorage.getItem('email')))
            .then((response) => {
                this.theCurrentUser = response.data;
            }).catch(function(e) {
                self.errorUser = e.response.data.message
            });
    },

    methods: {

        /**
         * update the email method
         * @param {*} newEmail 
         */
        updateEmail: function(newEmail) {
            var self = this;

            AXIOS.put("/gorceryUser/?" + this.theTargetUser.email, {
                params: {
                    newEmail: newEmail,
                },
            }).then((response) => {
                this.theTargetUser = response.data;

                //reinitialize the field
                this.newEmail = "";
                this.error = "";
            }).catch(function(err) {
                self.error = err.response.data.message;

            });
        },


        /**
         * update the first and last names method
         * @param {*} newFirstName 
         * @param {*} newLastName 
         */
        updateFirstLastName: function(newFirstName, newLastName) {
            var self = this;

            /**
             * http request to the backend to update the names
             */
            AXIOS.put("/gorceryUser/?" + this.theTargetUser.email, {
                params: {
                    newFirstName: newFirstName,
                    newLastName: newLastName
                },
            }).then((response) => {
                this.theTargetUser = response.data;

                //reinitialize the field
                this.newFirstName = "";
                this.newLastName = "";
                this.error = "";

            }).catch(function(err) {
                self.error = err.response.data.message;

            });
        },


        /**
         * update the password
         * @param {*} verificationPassword 
         * @param {*} newPassword 
         */
        updatePassword: function(verificationPassword, newPassword) {
            var self = this;

            /**
             * http request to update the password
             */
            AXIOS.put("/gorceryUser/?" + this.theTargetUser.email, {
                params: {
                    password: verificationPassword,
                    newPassword: newPassword
                },
            }).then((response) => {
                this.theTargetUser = response.data;

                //reinitialize the field
                this.verificationPassword = "";
                this.newPassword = "";
                this.error = "";
            }).catch(function(err) {
                self.error = err.response.data.message;

            });
        },


    },
}