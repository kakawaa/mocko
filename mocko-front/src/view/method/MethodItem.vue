<template>
	<el-drawer title="编辑方法信息"
	           v-model="methodDrawer"
	           :with-header=true size="60%">

		<el-container class="method-container">
			<!--方法表单信息-->
			<el-form status-icon
			         label-position="right"
			         label-suffix=":"
			         ref="methodFormRef" :model="methodForm" :rules="methodFormRules" class="method-form">

				<el-form-item prop="methodId">
					<el-input type="hidden" v-model="methodForm.methodId"/>
				</el-form-item>

				<el-form-item label="类名" prop="typeName">
					<el-input readonly v-model="methodForm.typeName"/>
				</el-form-item>

				<el-form-item label="方法名" prop="methodName">
					<el-input readonly v-model="methodForm.methodName"/>
				</el-form-item>


				<el-form-item label="方法描述" prop="methodAlias">
					<el-input v-model="methodForm.methodAlias"/>
				</el-form-item>

				<el-form-item label="返回值类型" prop="responseType">
					<el-input readonly v-model="methodForm.responseType"/>
				</el-form-item>

				<el-form-item class="submit-btn">
					<el-button type="primary" @click="submitMethodMaintain">保存方法基础信息</el-button>
					<el-button type="success" @click="openMethodRuleAddDrawer">新增方法规则</el-button>
				</el-form-item>
			</el-form>
		</el-container>


		<!--方法规则列表-->
		<el-container class="table-container">
			<el-table :data="methodRuleListData" show-overflow-tooltip border style="width: 100%">
				<el-table-column type="index" width="50"/>
				<el-table-column min-width=120 prop="ruleTitle" label="名称"/>
				<el-table-column prop="expression" label="规则"/>
				<el-table-column min-width=60 label="开关">
					<template v-slot="{row}">
						<el-switch
							v-model="row.switchFlag"
							inline-prompt
							active-text="开启"
							inactive-text="关闭"
							active-value="1"
							inactive-value="0"
							@change="handleSwitchMethodRule(row)"
						/>
					</template>
				</el-table-column>
				<el-table-column min-width=50 prop="requestCount" label="次数"/>
				<el-table-column width=180 align="center" prop="lastRequestTime"
				                 label="上次调用时间"/>
				<el-table-column label="操作" align="center" fixed="right" width=136>
					<template #default="scope">
						<el-button type="success" size="small" @click="editMethodRule(scope.row)">
							编辑
						</el-button>
						<el-button type="danger" size="small" @click="deleteMethodRule(scope.row)">
							删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
		</el-container>


		<!--方法规则抽屉-->
		<rule-item @after-rule-modify="loadMethodRuleList" ref="methodRuleDrawerRef"/>
	</el-drawer>
</template>

<script setup>

// do not use same name with ref
import {ref} from "vue";
import {findRulesByMethodId, getMethod, modifyMethod, switchMethodRule} from "@/api/method.js";
import {ElMessage, ElMessageBox} from "element-plus";
import RuleItem from "@/view/method/RuleItem.vue";


const methodDrawer = ref(false)


// 打开方法抽屉
const openMethodDrawer = (methodId) => {
	methodDrawer.value = true
	loadMethodInfo(methodId)
	loadMethodRuleListData(methodId)
}


// 向外暴露方法抽屉打开方法
defineExpose({openMethodDrawer})


// 方法表单
const methodForm = ref({
	methodId: '',
	methodName: '',
	methodAlias: '',
	responseType: '',
	typeName: '',
})


// 方法表单引用
const methodFormRef = ref()


// 方法表单校验规则
const methodFormRules = {
	methodName: [
		{required: true, message: '请输入方法名', trigger: 'blur'},
	],
	methodAlias: [
		{required: true, message: '请输入方法描述', trigger: 'blur'},
	],
	responseType: [
		{required: true, message: '请输入响应值类型', trigger: 'blur'},
	],
};


// 定义方法抽屉的事件
const emit = defineEmits(['afterMethodModify'])


// 方法规则列表数据
const methodRuleListData = ref([])


// 方法规则信息抽屉
const methodRuleDrawerRef = ref()


/**
 * 加载方法数据到表单
 */
function loadMethodInfo(methodId) {
	if (methodFormRef.value) {
		methodFormRef.value.resetFields();
	}
	getMethod(methodId).then(
		response => {
			methodForm.value = response.data
		}
	)
}


/**
 * 提交方法维护信息
 */
function submitMethodMaintain() {

	methodFormRef.value.validate((valid) => {
		if (!valid) {
			return
		}
		const formData = {...methodForm.value}
		modifyMethod(formData).then(response => {
			if (response.data) {
				ElMessage.success({
					message: '保存成功',
					duration: 1500,
				})

				emit('afterMethodModify', formData)
			} else {
				ElMessageBox({
					title: '提示',
					message: response.msg,
				})
			}
		})
		// end modify request handle
	})
}


// 加载方法规则列表数据
function loadMethodRuleList(rule) {
	loadMethodRuleListData(rule.methodId)
}


/**
 * 加载方法规则列表数据
 */
function loadMethodRuleListData(methodId) {
	findRulesByMethodId(methodId).then(response => {
		methodRuleListData.value = response.data
	})
}


/**
 * 编辑方法规则
 */
function editMethodRule(row) {
	console.log(row)
	methodRuleDrawerRef.value.openMethodRuleDrawer(row.ruleId)
}


/**
 * 删除方法规则
 */
function deleteMethodRule(row) {
	console.log(row)
}


/**
 * 开启/关闭方法规则
 */
function handleSwitchMethodRule(row) {
	console.log(row)
	let switchFlag = (row.switchFlag === 0 ? 1 : 0)
	switchMethodRule(row.id, switchFlag)
}

function openMethodRuleAddDrawer() {
	let methodId = methodForm.value.methodId
	methodRuleDrawerRef.value.openMethodRuleDrawer(null, methodId)
}


</script>

<style scoped lang="less">
.method-form {
	width: 100%;
}

.submit-btn .el-form-item__content {
	text-align: right !important;
	display: flex;
	justify-content: flex-end;
}
</style>