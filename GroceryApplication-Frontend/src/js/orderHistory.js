import {AXIOS} from "./axiosinst";

export default {
  name: "OrderHistory",

  //Run when component is created
  mounted() {
    AXIOS.get(`/groceryOrder/`)
      .then(res => this.saveOrders(res))
      .catch(err => console.log(err))
  },

  data() {
    return {
      tableEntries: [],
      price: "#4.99",
    };
  },

  methods: {

    saveOrders(res) {
      this.tableEntries = res.data;
      console.log(this.tableEntries);
    }
  }

}
