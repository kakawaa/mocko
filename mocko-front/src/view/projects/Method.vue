<template>
	<el-container class="method-container">
		<!--表单信息-->
		<el-form status-icon
		         :model="form"
		         ref="formRef"
		         :rules="methodFormRules" label-with="0" class="method-form">

			<el-form-item label="">
				<el-input placeholder="方法名"
				          readonly
				          v-model="form.methodName"/>
			</el-form-item>


			<el-form-item label="" prop="methodAlias">
				<el-input placeholder="方法别名"
				          v-model="form.methodAlias"/>
			</el-form-item>

			<el-form-item label="">
				<el-input placeholder="返回值类型"
				          readonly
				          v-model="form.responseType"/>
			</el-form-item>

			<el-form-item label="" prop="response">
				<el-input v-model="form.response"
				          type="textarea"/>
			</el-form-item>

			<el-form-item>
				<el-button type="primary" @click="onSubmit">提交</el-button>
			</el-form-item>
		</el-form>
	</el-container>
</template>

<script setup>
import {ref} from 'vue'

import {useRoute} from 'vue-router';
import {getMethod, modifyMethod} from "@/api/method.js";
import {ElMessage, ElMessageBox} from "element-plus";

const route = useRoute();

// do not use same name with ref
const form = ref({
	methodName: '',
	methodAlias: '',
	responseType: '',
	response: '',
})


getMethod(route.query.methodId).then(
	response => {
		form.value = response.data
	}
)


const formRef = ref(null)

function onSubmit() {

	formRef.value.validate(
		(valid) => {
			if (valid) {
				const formData = {...form.value}
				doSubmit(formData)
			}
		}
	)
}


function doSubmit(formData) {
	modifyMethod(formData).then(
		response => {
			if (response.data) {
				ElMessageBox({
					title: '提示',
					message: '保存成功.',
					type: 'success',
				})
			} else {
				ElMessageBox({
					title: '提示',
					message: response.msg,
				})
			}
		}
	)
}


const methodFormRules = {
	response: [{
	}],
}
</script>


<style scoped lang="less">

.method-container {

	width: 100%;

	.method-form {
		width: 100%;
	}
}
</style>