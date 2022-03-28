import Vue from 'vue'
import Router from 'vue-router'
import Signup from '@/components/Signup'
import Login from '@/components/Login'
import Four0Four from '@/components/404'
import Store from '@/components/Store'
import EmployeeSignup from '@/components/EmployeeSignup'
import AccountInfo from '@/components/AccountInfo'
import EditingAccInfo from '@/components/EditingAccInfo'
import navbar from '@/components/navbar'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/store',
      name: 'Store',
      component: Store
    },
    {
      path: '/navbar',
      name: 'navbar',
      component: navbar
    },
    {
      path: '/accountinfo',
      name: 'AccountInfo',
      component: AccountInfo
    },
    {
      path: '/editingaccinfo',
      name: 'EditingAccInfo',
      component: EditingAccInfo
    },
    {
      path: '/employeesignup',
      name: 'EmployeeSignup',
      component: EmployeeSignup
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
      redirect: '/404'
    }
  ]
})