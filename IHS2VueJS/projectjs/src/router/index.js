import { createRouter, createWebHistory } from 'vue-router'
//import Home from '../views/Home.vue'
import Login from '@/components/Login.vue'
import Maison from '@/components/Maison.vue'
import Lechat from '@/components/Lechat.vue'

const routes = [
  {
    path: '/',
    name: 'Bienvenue',
    component: Login
  },
  {
    path: '/:leuser',
    name : 'Maison',
    component : Maison
  },
  {
    path: '/:leuser/chat',
    name : 'Lechat',
    component : Lechat
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
