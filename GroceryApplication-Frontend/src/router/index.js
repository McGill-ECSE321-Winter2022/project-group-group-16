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

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/store',
      name: 'Store',
      component: Store
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
      path: '/404',
      name: 'NotFound',
      component: Four0Four

    }, {
      path: '*',
      redirect: '/404'
    }
  ]
})