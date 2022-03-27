import Vue from 'vue'
import Router from 'vue-router'
import Signup from '@/components/Signup'
import Login from '@/components/Login'
import Four0Four from '@/components/404'


Vue.use(Router)

export default new Router({
  routes: [

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
      redirect: '/404'
    }
  ]
})