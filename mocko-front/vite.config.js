import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import {resolve} from "path"

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import Icons from 'unplugin-icons/vite'
import IconsResolver from 'unplugin-icons/resolver'
import ElementPlus from 'unplugin-element-plus/vite'


export default defineConfig({

	envDir: './env',
	envPrefix: ['VITE_', 'MOCKO_'],

	// plugins
	plugins: [
		vue(),
		AutoImport({
			resolvers: [
				ElementPlusResolver(),
				// 自动导入图标组件
				IconsResolver({})
			],
		}),

		Components({
			resolvers: [
				ElementPlusResolver(),
				// 自动导入图标组件
				IconsResolver({
					prefix: '',
					// @iconify-json/ep 是 Element Plus 的图标库
					enabledCollections: ['ep'],
				})
			],
		}),

		ElementPlus(),

		Icons({
			autoInstall: true,
		}),
	],

	// custom
	define: {
		'import.meta.env.CUSTOM': '11111',
	},


	resolve: {
		// 配置路径别名
		alias: {
			'@': resolve(__dirname, './src'),
		},
	},
})
