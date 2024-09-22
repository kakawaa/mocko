<template>
	<div class="table-container">

		<div class="table-header">
			<el-form :model="keywordForm" label-width="auto" label-suffix=":" :inline="true"
			         @submit.native.prevent
			>
				<el-form-item label="关键字">
					<el-input v-model="keywordForm.keyword" @keyup.enter.native="queryMethods"/>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="queryMethods">查询</el-button>
				</el-form-item>
			</el-form>
		</div>

		<div class="table-body">
			<div class="table-button">
				<el-button type="primary">暂时留着</el-button>
			</div>

			<el-table :data="methodListData" border style="width: 100%">
				<el-table-column type="index" width="50"/>
				<el-table-column show-overflow-tooltip min-width=270 prop="typeName" label="类名"/>
				<el-table-column show-overflow-tooltip min-width=120 prop="methodName" label="方法名"/>
				<el-table-column show-overflow-tooltip min-width=180 prop="methodAlias" label="方法描述"/>
				<el-table-column show-overflow-tooltip min-width=270 prop="responseType" label="返回类型"/>
				<el-table-column show-overflow-tooltip width=180 align="center" prop="lastRequestTime"
				                 label="上次调用时间"/>
				<el-table-column label="操作" align="center" fixed="right" width=136>
					<template #default="scope">
						<el-button type="success" size="small" @click="handleEdit(scope.row)">
							编辑
						</el-button>
						<el-button type="danger" size="small" @click="handleDelete(scope.row)">
							删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>

		<div class="table-footer">
			<el-pagination :pager-count="5"
			               :page-size="pageData.pageSize"
			               :current-page="pageData.pageNo"
			               :total="pageData.total"
			               @size-change="handlePageChange"
			               @current-change="handlePageChange"
			               layout="prev, pager, next"/>
		</div>
	</div>

	<method-item @after-method-modify="loadMethodListData" ref="methodDrawerRef"/>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {findMethodsInPage} from '@/api/type'
import {useRoute} from 'vue-router';
import MethodItem from "@/view/method/MethodItem.vue";

const route = useRoute();


// 搜索数据
const keywordForm = ref({
	// 关键字
	keyword: ''
})


// 分页数据
const pageData = ref({
	// 当前页
	pageNo: 1,
	// 页面长度
	pageSize: 10,
	// 记录总数
	total: 0,
})


// 方法列表数据
const methodListData = ref([])


// 方法信息抽屉
const methodDrawerRef = ref()


// 加载方法数据
function loadMethodListData() {
	let keyword = keywordForm.value.keyword
	let appId = route.query.appId
	let pageNo = pageData.value.pageNo
	let pageSize = pageData.value.pageSize
	findMethodsInPage({
		'appId': appId,
		'keyword': keyword,
		'pageNo': pageNo,
		'pageSize': pageSize,
	}).then(response => {
		if (response && response.data && response.data.data) {
			methodListData.value = response.data.data
		}
	})
}


onMounted(() => {
	loadMethodListData()
})


const handlePageChange = async (val) => {
	console.log(val)
	loadMethodListData()
}


function handleEdit(record) {
	methodDrawerRef.value.openMethodDrawer(record.methodId)
}


function handleDelete(record) {
	console.log(record)
}


function queryMethods() {
	loadMethodListData()
}


</script>

<style lang="less" scoped>
.table-container {
	margin: 0;
	padding: 12px;
	background-color: #2b4b6b;
}

.table-header, .table-body, .table-footer {
	display: block;
	margin: 0;
	padding: 12px;
	background-color: #FFFFFF;
	border: #535bf2 1px solid;
}


.table-header, .table-body {
	margin-bottom: 9px;
}

.table-header {
	padding-top: 24px;
}


.table-button {
	padding-bottom: 9px;
}

</style>
