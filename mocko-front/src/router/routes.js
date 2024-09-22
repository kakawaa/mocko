import authRoutes from '@/view/auth/routes'
import dashboardRoutes from '@/view/dashboard/routes'
import projectsRoutes from '@/view/projects/routes'
import usersRoutes from '@/view/users/routes'
import methodRoutes from '@/view/method/routes'

import NotFoundPage from '@/view/error/NotFound.vue'
import HomePage from "@/view/home/Home.vue";
import LoginPage from "@/view/auth/Login.vue"


const routes = [
	{
		name: 'Home',
		path: '/',
		component: HomePage,
		meta: {
			title: '首页',
		},
		children: [
			...dashboardRoutes,
			...projectsRoutes,
			...usersRoutes,
			...methodRoutes
		],
	}, {
		name: 'Login',
		path: '/login',
		component: LoginPage
	},
	...authRoutes,
	{
		path: "/:pathMatch(.*)*", component: NotFoundPage, name: 'notFound'
	}
]

export default routes