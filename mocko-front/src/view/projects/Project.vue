<template>
	<el-container class="project-container">
		<el-aside class="tree-container">
			<tree-side :tree-data="treeData" :app-id="appId" />
		</el-aside>

		<el-main>
			<router-view :key="$route.fullPath"></router-view>
		</el-main>
	</el-container>
</template>

<script setup>
import { ref } from "vue";

import TreeSide from '@/component/layout/TreeSide.vue'
import { getClassTree } from "@/api/type.js";
import { useRoute } from 'vue-router';

const route = useRoute();

let treeData = ref(null)
let appId = ref('')

getClassTree(route.query.appId).then(response => {
	treeData.value = response.data
	appId = route.query.appId
})

</script>


<style lang="less" scoped>
.project-container {
	height: 100%;
}

.tree-container {
	background-color: #F5F5F5;

	.el-tree {
		background: transparent;
	}

	.is-current {
		background-color: #000;
	}
}
</style>