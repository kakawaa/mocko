<template>
	<el-result icon="warning" title="404" subTitle="找不到页面">
		<template #extra>
			<el-button type="primary" size="small" @click="back">返回</el-button>
		</template>
	</el-result>
</template>


<script setup>
import {useRouter} from 'vue-router'

const router = useRouter()

const back = () => {
	router.back()
}
</script>


<style scoped lang="less">
.el-result{
	background-color: #FFFFFF;
}
</style>