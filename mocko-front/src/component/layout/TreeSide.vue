<template>
	<el-tree style="max-width: 300px" 
		:data="props.treeData" 
		:props="treeConfig" 
		:highlight-current="true"
		:default-expand-all="true" 
		@node-click="handleNodeClick">

		<template #default="{ node, data }">
			<span>
				<el-icon>
					<Folder v-if="!data.classNode && !data.methodNode" />
					<Files v-if="data.classNode" />
					<DocumentAdd v-if="data.methodNode" />
				</el-icon> {{ node.label }}
			</span>
		</template>

	</el-tree>
</template>

<script setup>

import { useRouter, useRoute } from "vue-router";
import { getMethods } from "@/api/type.js";

const route = useRoute();

const router = useRouter();

const props = defineProps({
	treeData: Object,
	appId: String,
})

const treeConfig = {
	children: 'children',
	label: 'value',
}

const Tree = {
	value: String,

	classNode: Boolean,

	methodNode: Boolean,

	children: Array,
}

const handleNodeClick = (data, node) => {
	addMethods(data, node)
	loadMethodPage(data)
}


function loadMethodPage(data) {
	if (!data.methodNode) {
		return
	}

	router.push({
		name: 'Method',
		query: {
			'appId': route.query.appId,
			'methodId': data.methodId,
		}
	})
}


function addMethods(data, node) {
	if (!data.classNode || (data.children && data.children.length)) {
		return
	}

	getMethods(data.classId).then(response => {
		let methods = response.data
		if (!data.children) {
			data.children = response.data
		} else {
			data.children.push(...methods)
		}
		node.expand()
	})
}

</script>

<style lang="less" scoped></style>