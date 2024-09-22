import request from '@/router/request.js'


/**
 * 获取方法信息
 * @param methodId 方法ID
 * @returns 方法信息
 */
export function getMethod(methodId) {
	return request.post('/admin/method/get', {
		'methodId': methodId
	})
}


/**
 * 修改方法信息
 *
 * @param  methodData 方法信息
 */
export function modifyMethod(methodData) {
	return request.post('/admin/method/modify', methodData);
}


/**
 * 获取方法规则信息
 * @param ruleId 方法ID
 * @returns 方法规则信息
 */
export function getMethodRule(ruleId) {
	return request.post('/admin/method/rule/get', {
		'id': ruleId
	})
}


/**
 * 更新方法规则
 *
 * @param  ruleData 方法规则
 */
export function modifyMethodRule(ruleData) {
	return request.post('/admin/method/rule/modify', ruleData);
}


/**
 * 更新方法规则
 *
 * @param  ruleData 方法规则
 */
export function addMethodRule(ruleData) {
	return request.post('/admin/method/rule/add', ruleData);
}


/**
 * 获取方法规则列表
 */
export function findRulesByMethodId(methodId) {
	return request.post('/admin/method/rule/list', {
		'methodId': methodId
	})
}


/**
 * 开启/关闭方法规则
 */
export function switchMethodRule(id, switchFlag) {
	return request.post('/admin/method/rule/switch', {
		'id': id,
		'switchFlag': switchFlag
	})
}
