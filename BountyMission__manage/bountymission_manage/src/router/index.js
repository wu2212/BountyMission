import { createRouter, createWebHistory } from 'vue-router'

import Login from '../views/LoginIndex.vue'
const routes = [

  
  {
    path:'/',
    component:Login
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
