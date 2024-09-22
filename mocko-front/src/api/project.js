import request from '@/router/request.js'


/**
 * 获取应用信息
 * 
 * @returns 应用集合
 */
export function getAppList() {
	return request.get('/admin/app/list');
}
