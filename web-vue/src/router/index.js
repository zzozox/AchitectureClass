import { createRouter, createWebHistory } from 'vue-router'

const index = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'login',
            component: () => import('../views/login.vue'),
        },{
            path: '/business',
            name: 'business',
            component: () => import('../views/business/showShops.vue'),
        },{
            path: '/showItems',
            name: 'showItems',
            component: () => import('../views/business/showItems.vue'),
        },{
            path: '/newShop',
            name: 'newShop',
            component: () => import('../views/business/newShop.vue'),
        },{
            path: '/newItem',
            name: 'newItem',
            component: () => import('../views/business/newItem.vue'),
        },{
            path: '/shopItems',
            name: 'shopItems',
            component: () => import('../views/business/shopItems.vue'),
        },{
            path: '/showOrders',
            name: 'showOrders',
            component: () => import('../views/business/showOrders.vue'),
        },{
            path: '/newShopItem',
            name: 'newShopItem',
            component: () => import('../views/business/newShopItem.vue'),
        },{
            path: '/customer',
            name: 'customer',
            component: () => import('../views/customer/index.vue'),
        },{
            path: '/chooseItem',
            name: 'chooseItem',
            component: () => import('../views/customer/chooseItem.vue'),
        },{
            path: '/payOrder',
            name: 'payOrder',
            component: () => import('../views/customer/payOrder.vue'),
        }

    ]
})

/**
 * 路由守卫
 */
// index.beforeEach((to, from, next) => {
//     const isUnauthorized = unauthorized()
//     if(to.name.startsWith('welcome') && !isUnauthorized) {
//         next('/index')
//     } else if(to.fullPath.startsWith('/index') && isUnauthorized) {
//         next('/login')
//     } else {
//         next()
//     }
// })

export default index
