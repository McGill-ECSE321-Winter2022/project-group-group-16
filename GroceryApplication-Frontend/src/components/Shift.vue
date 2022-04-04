<template>
<body>
<header id="header">
  <nav class="navbar">
      <div class="navbar__container">
        <img src="https://chailly-food.ch/wp-content/uploads/2020/06/market-5043895_1280.png" alt="logo" id="navbarlogo">
        <div class="navbar__toggle" id="mobile-menu">
          <span class="bar"></span> <span class="bar"></span>
          <span class="bar"></span>
        </div>
        <ul class="navbar__menu">
          <li class="navbar__item">
            <a href="#products" class="navbar__links" id="products-page" @click="homeScreenManager">Home</a>
          </li>
          <li class="navbar__item">
            <a href="#about" class="navbar__links" id="about-page" @click="accountInfo">Profile</a>
          </li>
        </ul>
      </div>
    </nav>
</header>
  <div class="container">
    <h4 class="label">Shift schedule</h4>
    <div class="spacer"></div>
    <table class="table table-striped table-hover">
      <thead>
      <tr>
        <th scope="col">Day</th>
        <th scope="col">Shift Type</th>
        <th scope="col">Remove</th>
      </tr>
      </thead>
      <tbody >
        <v-tr v-for="product in displayData" :key="product.id" :row="product">
          <td>{{ product.name }}</td>
          <td>{{ product.availability }}</td>
          <td><button>Remove</button></td>
        </v-tr>
      </tbody>
    </table>
  </div>
  <form>
    <div class="row">
      <div class="col">
        <h5>Add shift</h5>
        <label>Day:</label>
        <input type="text" required v-model="shiftDay" />
        <label>Shift Type:</label>
        <input type="text" required v-model="shiftType" />
      </div>
    </div>
    <div class="button" align="center">
      <button class="submit" type="submit" @click="payment">ADD</button>
    </div>
  </form>
</body>
</template>

<script>
  export default {
    data() {
      return {
        item1: {
          itemDesc: 'Sunday',
          itemPrice: 'Closing'
        },
        item2: {
          itemDesc: 'Monday',
          itemPrice: 'Opening'
        },
      };
    },
    methods: {
      viewShift() {
        this.$router.push({ name: 'Employee' })
      }
    }
  }
</script>

<style scoped src="../css/form.css"></style>
<style scoped src="../css/homeScreenManager.css"></style>