export default [
	{
		name: 'Home',
		icon: 'Platform',
		uri: '/home',

		children: [{
			name: '应用',
			icon: 'menu',
			uri: '/project-list',
		}, {
			name: '用户',
			icon: 'user',
			uri: '/users',
		}, {
			name: '设置',
			icon: 'setting',
			uri: '#',
		}],
	},
]