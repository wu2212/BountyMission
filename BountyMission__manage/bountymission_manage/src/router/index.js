import { createRouter, createWebHistory } from 'vue-router'

import Login from '../views/LoginIndex.vue'
import Home from '../views/HomeIndex.vue'
const routes = [
  {
    path:'/',
    component:Login
  },
  {
    path:'/home',
    component:Home
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
