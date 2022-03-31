<template>
  <div class="container">
    <h4 class="label">CART </h4>
    <div class="spacer"> </div>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th scope="col">ITEM</th>
        <th scope="col">DESCRIPTION</th>
        <th scope="col">PRICE</th>
        <th scope="col">QUANTITY</th>
        <th scope="col">REMOVE</th>

      </tr>
      </thead>
      <tbody>
      <tr>
        <th scope="row">{{ item1.itemName }}</th>
        <td>{{ item1.itemDesc }}</td>
        <td>{{ item1.itemPrice }}</td>
        <td>{{ item1.itemQuantity }}</td>
        <td><button>REMOVE</button></td>

      </tr>
      <tr>
        <th scope="row">{{ item2.itemName  }}</th>
        <td>{{ item2.itemDesc }}</td>
        <td>{{ item2.itemPrice }}</td>
        <td>{{ item2.itemQuantity }}</td>
        <td><button>REMOVE</button></td>

      </tr>
      <tr>
        <th scope="row">{{ item3.itemName  }}</th>
        <td>{{ item3.itemDesc }}</td>
        <td>{{ item3.itemPrice }}</td>
        <td>{{ item3.itemQuantity }}</td>
        <td><button>REMOVE</button></td>

      </tr>

       <tr>
        <th scope="row">{{ item4.itemName  }}</th>
        <td>{{ item4.itemDesc }}</td>
        <td>{{ item4.itemPrice }}</td>
        <td>{{ item4.itemQuantity }}</td>
        <td><button>REMOVE</button></td>

      </tr>

              
    





      </tbody>
    </table>

    <div class="button" align="right">
    <button class="submit" type="submit" @click="viewPayment">CHECKOUT</button>
    </div>

  </div>

</template>

<script>
  export default {
    data() {
      return {
        item1: {
          itemName:'Milk',
          itemDesc: 'White stuff to put in your mouth non susp',
          itemQuantity : 1,
          itemPrice: '$' + (Math.round(4.99 *100)/100).toFixed(2)
        },
        item2: {
          itemName:'Carrots',
          itemDesc: 'It takes some finesee, Ive seen you finesse',
          itemQuantity : 1,
          itemPrice: '$' +(Math.round(2.99 *100)/100).toFixed(2)
        },
        item3: {
          itemName:'Bread',
          itemDesc: 'Go get bread young buck',
          itemQuantity : 1,
          itemPrice: '$' +(Math.round(5.99 *100)/100).toFixed(2)
        },

         item4: {
          itemName:'Sausage',
          itemDesc: 'My favorite movies starts with the word sausage',
          itemQuantity : 1,
          itemPrice: '$' +(Math.round(6.99 *100)/100).toFixed(2)
        }
      };
    },

    methods: {
      viewPayment() {
        this.$router.push({ name: 'Payment' })
      }
    }




  }
</script>

<style scoped src="../css/form.css"></style>



