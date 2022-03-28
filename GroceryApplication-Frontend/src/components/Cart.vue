
<template>
  <form>
    <div><h1>CART</h1></div>
    
     <div>
    <b-table
      id="table-transition-example"
      :items="items"
      :fields="fields"
      striped
      small
      primary-key="a"
      :tbody-transition-props="transProps"

    


    ></b-table>
  </div>

    <div align="right">
    <p> Total Price: 96.80$ </p>
    </div>





    <div class="button" align="right">
      <button class="submit" type="submit">CHECKOUT</button>
    </div>


  </form>
  



</template>



<script>
  export default {
    data() {
      return {
        sortBy: 'price',
        sortDesc: false,
        fields: [
          { key: 'item_name', sortable: true },
          { key: 'description', sortable: false },
          { key: 'price', sortable: true },
          { key: 'quantity', sortable: true },
          { key: 'remove', sortable: false }
          
        ],
        items: [
          { quantity: 5, price: (Math.round(41 *100)/100).toFixed(2) + "$", item_name: 'Milk', description: 'White and drinkableTEST'},
          { quantity: 1, price: (Math.round(30.50 *100)/100).toFixed(2) + "$", item_name: 'Carrot', description: 'Good for the eyes' },
          { quantity: 1, price: (Math.round(15.30 *100)/100).toFixed(2) + "$", item_name: 'Bread', description: 'Buy if craving sandwich'},
          { quantity: 2, price: (Math.round(10 *100)/100).toFixed(2) + "$", item_name: 'Sausage', description: 'Love Sausage parties' },
          
         
        ]
      }
    }
  }
</script>
