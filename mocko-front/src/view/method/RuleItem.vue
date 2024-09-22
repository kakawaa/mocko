<template>

	<el-drawer title="编辑方法规则"
	           v-model="methodRuleDrawer"
	           :with-header=true size="40%">

		<el-container>
			<!--表单信息-->
			<el-form status-icon
			         label-position="right"
			         label-width="auto"
			         label-suffix=":"
			         :model="ruleForm" ref="ruleFormRef" :rules="ruleFormRules" class="rule-form">

				<el-form-item prop="methodId">
					<el-input type="hidden" v-model="ruleForm.methodId"/>
				</el-form-item>

				<el-form-item prop="ruleId" v-if="ruleForm.ruleId">
					<el-input type="hidden" v-model="ruleForm.ruleId"/>
				</el-form-item>

				<el-form-item label="规则名称" prop="ruleTitle">
					<el-input v-model="ruleForm.ruleTitle"/>
				</el-form-item>

				<el-form-item label="规则表达式" prop="ruleExp">
					<el-input v-model="ruleForm.ruleExp"/>
				</el-form-item>

				<el-form-item label="返回值" prop="response">
					<el-input type="textarea" v-model="ruleForm.response" :autosize="{ minRows: 4,}"/>
				</el-form-item>

				<el-form-item label="是否开启" prop="switchFlag">
					<el-switch v-model="ruleForm.switchFlag"/>
				</el-form-item>

				<el-form-item label="上次调用时间" prop="lastRequestTime" v-if="ruleForm.ruleId">
					<el-input readonly v-model="ruleForm.lastRequestTime"/>
				</el-form-item>

				<el-form-item label="备注" prop="remark">
					<el-input type="textarea" v-model="ruleForm.remark" :autosize="{ minRows: 4,}"/>
				</el-form-item>

				<el-form-item>
					<el-button type="primary" @click="submitMethodRule">保存</el-button>
				</el-form-item>
			</el-form>
		</el-container>

	</el-drawer>

</template>

<script setup>

// do not use same name with ref
import {ref} from "vue";
import {addMethodRule, getMethodRule, modifyMethodRule} from "@/api/method.js";
import {ElMessage, ElMessageBox} from "element-plus";


const methodRuleDrawer = ref(false)

const ruleForm = ref({
	ruleId: '',
	methodId: '',
	ruleTitle: '',
	ruleExp: '',
	response: '',
	switchFlag: '',
	lastRequestTime: '',
	remark: '',
})


const ruleFormRef = ref()

const ruleFormRules = {
	ruleTitle: [
		{required: true, message: '请输入规则名称', trigger: 'blur'},
	],
	response: [
		{required: true, message: '请输入返回值', trigger: 'blur'},
	],
};


const emit = defineEmits(['afterRuleModify'])


/**
 * 打开方法规则编辑抽屉
 */
const openMethodRuleDrawer = (ruleId, methodId) => {
	methodRuleDrawer.value = true

	if (ruleFormRef.value) {
		ruleFormRef.value.resetFields();
	}

	if (ruleId) {
		loadMethodRule(ruleId)
	} else {
		ruleForm.value = {
			methodId: methodId,
		}
	}
}


defineExpose({openMethodRuleDrawer})

/**
 * 加载方法规则到表单
 */
function loadMethodRule(ruleId) {

	getMethodRule(ruleId).then(
		response => {
			ruleForm.value = response.data
		}
	)
}


/**
 * 提交方法规则
 */
function submitMethodRule() {
	const formData = {...ruleForm.value}
	let maintainMethod = addMethodRule
	if (formData.ruleId) {
		maintainMethod = modifyMethodRule
	}
	maintainMethodRule(maintainMethod, formData)
}


/**
 * 方法规则维护
 *
 * @param maintainMethod 维护方法
 * @param formData 表单数据
 */
const maintainMethodRule = (maintainMethod, formData) => {

	ruleFormRef.value.validate((valid) => {
		if (!valid) {
			return
		}
		maintainMethod(formData).then(response => {
			if (response.data) {
				ElMessage.success({
					message: '保存成功',
					duration: 1500,
				})

				emit('afterRuleModify', formData)
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

</script>

<style scoped lang="less">
.rule-form {
	width: 100%;
}
</style>