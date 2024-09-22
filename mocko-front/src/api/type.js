import request from '@/router/request.js'


/**
 * 类结构树
 *
 * @param  appId 应用ID
 * @returns  类结构树
 */
export function getClassTree(appId) {
	return request.post('/admin/classes/tree', {
		'appId': appId
	});
}


/**
 * 获取类下的方法
 *
 * @param classId 类ID
 */
export function getMethods(classId) {
	return request.post('/admin/classes/methods', {
		'classId': classId
	})
}


/**
 * 分页获取方法信息
 *
 * @param methodReq 方法列表请求
 */
export function findMethodsInPage(methodReq) {
	return request.post('/admin/method/page-list', {
		'appId': methodReq.appId,
		'keyword': methodReq.keyword,
		'pageNo': methodReq.pageNo,
		'pageSize': methodReq.pageSize
	})
}