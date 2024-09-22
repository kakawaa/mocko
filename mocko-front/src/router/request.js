import axios from 'axios';
import {ElMessage} from "element-plus";
import router from '@/router/index.js'
import config from "@/config/index.js";


//1. 创建axios对象
const axiosInst = axios.create({
	withCredentials: true,
	baseURL: `${import.meta.env.VITE_BASE_URL}`,
	timeout: `${import.meta.env.VITE_REQUEST_TIMEOUT}`,
	// 请求头
	headers: {
		"Content-Type": "application/json;charset=UTF-8",
	},
});


//2. 请求拦截器
axiosInst.interceptors.request.use(cfg => {
		let token = sessionStorage.getItem(config.Authorization)
		if (token) {
			cfg.headers.Authorization = `Bearer ${token}`
		}
		return cfg;
	},
	error => {
		Promise.reject(error);
	}
);


//3. 响应拦截器
axiosInst.interceptors.response.use(
	resp => {
		let result = resp.data

		//判断code码
		let code = result.code;
		if (code === 0) {
			return result;
		} else if (code === 100) {
			// 执行跳转到登录页
			sessionStorage.removeItem(config.Authorization)
			sessionStorage.removeItem(config.isLogin)
			router.push({name: config.loginRouteName})
		} else {
			let msg = (result.msg ? result.msg : resp.statusText)
			ElMessage.warning({
				message: msg,
				duration: 1500,
			})
			return result;
		}
	},
	error => {
		let resp = error.response
		if (!resp) {
			ElMessage.warning({
				message: '服务器开小差了',
				duration: 1500,
			})
		} else {
			let msg = (resp.data.msg ? resp.data.msg : resp.statusText)
			ElMessage.warning({
				message: msg,
				duration: 1500,
			})
		}

		return Promise.reject(error);
	}
);


export default axiosInst;