import Vue from 'vue'
import Router from 'vue-router'
import Signup from '@/components/Signup'
import Login from '@/components/Login'
import Four0Four from '@/components/404'
import Store from '@/components/Store'
import Cart from '@/components/Cart'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/store',
      name: 'Store',
      component: Store
    },

    {
      path: '/cart',
      name: 'Cart',
      component: Cart
    },




    {
      path: '/signup',
      name: 'Signup',
      component: Signup
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/404',
      name: 'NotFound',
      component: Four0Four

    }, {
      path: '*',
      redirect: '/cart'
    }
  ]
})