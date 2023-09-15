import { createRouter, createWebHashHistory } from 'vue-router'
import BillView from '../views/BillView'
import AboutView from '../views/AboutView'
import LoginView from '../views/LoginView'
import RegistView from '../views/RegistView'

const routes = [
    { path: '/', component: BillView },
    { path: '/bill', component: BillView },
    { path: '/about', component: AboutView },
    { path: '/login', component: LoginView },
    { path: '/regist', component: RegistView }
]

const router = createRouter({
    history: createWebHashHistory(),
    routes
})

export default router