import Vue from 'vue'
import VueRouter from 'vue-router'
import main from '../App.vue'
import home from '../page/home.vue'
import wallet from '../page/wallet.vue'
import chart from '../page/chart.vue'
import welfare from '../page/welfare.vue'
import mine from '../page/mine.vue'
import detail from '../page/detail.vue'
import costType from '../page/costType.vue'
import budgetConfig from '../page/budgetConfig.vue'
import crash from '../page/crash.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'main',
        component: main
    },
    {
        path: '/wallet',
        name: 'wallet',
        component: wallet
    },
    {
        path: '/costType',
        name: 'costType',
        component: costType
    },
    {
        path: '/detail',
        name: 'detail',
        component: detail
    },
    {
        path: '/crash',
        name: 'crash',
        component: crash
    },
    {
        path: '/budgetConfig',
        name: 'budgetConfig',
        component: budgetConfig
    },
    {
        path: '/chart',
        name: 'chart',
        component: chart
    },
    {
        path: '/welfare',
        name: 'welfare',
        component: welfare
    },
    {
        path: '/mine',
        name: 'mine',
        component: mine
    },
    {
        path: '/home',
        name: 'home',
        component: home
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

const router = new VueRouter({
    routes
})

export default router
