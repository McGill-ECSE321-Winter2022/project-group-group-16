import Vue from 'vue'
import Router from 'vue-router'
import Signup from '@/components/Signup'
import Login from '@/components/Login'
import Four0Four from '@/components/404'
import Store from '@/components/Store'
import HomeScreenCustomer from '@/components/HomeScreenCustomer'
import ItemCustomer from '@/components/ItemCustomer'
import Employee from '@/components/Employee'
import EmployeeProfile from '@/components/EmployeeProfile'
import ItemManager from '@/components/ItemManager'
import ItemCreateManager from '@/components/ItemCreateManager'
import HomeScreenManager from '@/components/HomeScreenManager'
import Shift from '@/components/Shift'
import HomeScreenEmployee from '@/components/HomeScreenEmployee'
import ShiftEmployee from '@/components/ShiftEmployee'

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
      path: '/homescreenCustomer',
      name: 'HomeScreenCustomer',
      component: HomeScreenCustomer
    },
    {
      path: '/homescreenManager',
      name: 'HomeScreenManager',
      component: HomeScreenManager
    },
    {
      path: '/homescreenEmployee',
      name: 'HomeScreenEmployee',
      component: HomeScreenEmployee
    },
    {
      path: '/itemCustomer',
      name: 'ItemCustomer',
      component: ItemCustomer
    },
    {
      path: '/itemManager',
      name: 'ItemManager',
      component: ItemManager
    },
    {
      path: '/itemCreateManager',
      name: 'ItemCreateManager',
      component: ItemCreateManager
    },
    {
      path: '/employee',
      name: 'Employee',
      component: Employee
    },
    {
      path: '/employeeProfile',
      name: 'EmployeeProfile',
      component: EmployeeProfile
    },
    {
      path: '/shift',
      name: 'Shift',
      component: Shift
    },
    {
      path: '/shiftEmployee',
      name: 'ShiftEmployee',
      component: ShiftEmployee
    },
    {
      path: '/404',
      name: 'NotFound',
      component: Four0Four

    },
    {
      path: '/order-history',
      name: 'OrderHistory',
      component: OrderHistory
    },
    {
      path: '/order-confirmation',
      name: 'OrderConfirmation',
      component: OrderConfirmation,
      meta: {auth: true}
    },
    {
      path: '/payment',
      name: 'Payment',
      component: Payment
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})
