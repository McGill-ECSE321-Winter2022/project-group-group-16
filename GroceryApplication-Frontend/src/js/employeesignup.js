import { AXIOS } from "./axiosinst";

export default {
    name: "SignupEmployee",

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
            pay: "",
            errorUser: "",
        };
    },
    methods: {
        signupEmployee: async function () {
            console.log("here")

            // create grocery user
            try {
                await this.createUser();
                await this.createEmployee();
            } catch (e) {
                console.log(e.response)
                if (e.response) {
                    this.errorUser = e.response.data.message
                }
            }

            if (this.errorUser === "") {
                console.log(this.email, " logged in!")
                localStorage.setItem("email", this.email)
                localStorage.setItem("usertype", "employee")
                this.$router.push('login')
            } else {
                alert("Signup failed")
                this.errorUser = ""
            }
        },

        createUser: async function () {
            const res = await AXIOS.post(`/gorceryUser/?username=${this.username}&password=${this.password}&firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&date=${this.date}`)
        },

        createEmployee: async function () {

            // create an employee
            // use today's date for hiredDate and ACTIVE as status 
            const res = await AXIOS.post(`/employee/?hiredDate=${new Date().toISOString().split('T')[0]}&employeeStatus=ACTIVE&hourlyPay=${this.pay}&email=${this.email}&groceryStoreApplicationId=0`)

        },
    },
};