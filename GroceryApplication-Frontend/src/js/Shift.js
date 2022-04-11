import { AXIOS } from "./axiosinst";

export default {
    name: 'Shift',

    data() {
        return {
            shiftId:"",
            shifts: [],
            input:'',




        };


    },

    methods: {
        addToShifts() {
          if (!this.input) {
            // input value is empty
            return;
          }
      
          // add item to reactive array items
          this.shifts.push(this.input);
      
          // clear the input
          this.input = '';
        }
      }





}