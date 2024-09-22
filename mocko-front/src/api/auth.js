import request from '@/router/request.js'


/**
 * 发送登录请求
 */
export function doLogin(data) {

	return request.post(
		'/login',
		{
			...data,
		}, {
			withCredentials: true
		})

}


/**
 * 发送退出请求
 */
export function doLogout() {
	return request.post('/logout')
}


/**
 * 设置token
 */
export function setHttpToken(token) {
	request.defaults.headers.common.Authorization = `Bearer ${token}`
}


/**
 * 设置token
 */
export function removeHttpToken() {
	request.defaults.headers.common.Authorization = ``
}
