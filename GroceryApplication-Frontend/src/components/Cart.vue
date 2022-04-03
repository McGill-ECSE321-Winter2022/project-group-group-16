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
        <td><button @click="removeItemFromCart">REMOVE</button></td>

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
     <p> Total Price: $20.96 </p>
    </div>

  </div>

</template>

<script>
  export default {
    data() {
      return {
        rows:[],
        item1: {
          itemName:'Milk',
          itemDesc: 'Its composition is understood principally in terms of the fat, protein, lactose (carbohydrate), vitamins, minerals and water it contains. The major component of milk is water at 87.3%.',
          itemQuantity : 1,
          itemPrice: '$' + (Math.round(4.99 *100)/100).toFixed(2)
        },
        item2: {
          itemName:'Carrots',
          itemDesc: 'Raw carrots are 88% water, 9% carbohydrates, 0.9% protein, 2.8% dietary fiber, 1% ash and 0.2% fat.',
          itemQuantity : 1,
          itemPrice: '$' +(Math.round(2.99 *100)/100).toFixed(2)
        },
        item3: {
          itemName:'Bread',
          itemDesc: 'Bread, baked food product made of flour or meal that is moistened, kneaded, and sometimes fermented. ',
          itemQuantity : 1,
          itemPrice: '$' +(Math.round(5.99 *100)/100).toFixed(2)
        },
         item4: {
          itemName:'Sausage',
          itemDesc: 'The main ingredients of a sausage are meat, fat, binding agents and water.',
          itemQuantity : 1,
          itemPrice: '$' +(Math.round(6.99 *100)/100).toFixed(2)
        }
      };
    },
    props: ["cart"], 
    methods: {
      viewPayment() {
        this.$router.push({ name: 'Payment' })
      },
      methods: {
         removeItemFromCart (rows) {
           let index = this.rows.indexOf(rows)
           this.rows.splice(index,1)
         }
      }
    }
  }
</script>

<style scoped src="../css/form.css"></style>