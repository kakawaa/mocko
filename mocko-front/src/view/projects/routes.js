import ProjectListPage from './ProjectList.vue'
import ProjectPage from './Project.vue'
import MethodPage from './Method.vue'

export default [
	{
		name: 'ProjectList',
		path: '/project-list',
		component: ProjectListPage,
		meta: {
			title: '应用',
		},
	},
	{
		name: 'Project',
		path: '/project',
		component: ProjectPage,
		meta: {
			title: '方法',
		},
		children: [{
			name: 'Method',
			path: '/method',
			component: MethodPage,
		},]
	},
]