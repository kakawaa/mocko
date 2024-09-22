<template>
	<el-container class="home_container">

		<sidebar :collapsed="isCollapsed"
		         :menu-items="homeMenuItem"/>

		<el-container direction="vertical">
			<head-bar :collapsed="isCollapsed" @menu="changeMenuStatus"/>
			<el-main>
				<router-view></router-view>
			</el-main>
		</el-container>

	</el-container>
</template>

<script setup>
import {ref} from 'vue'

import Sidebar from '@/component/layout/HomeSide.vue'
import HeadBar from '@/component/layout/HeadBar.vue'

import homeMenuItem from '@/view/home/menu'


let isCollapsed = ref(false)

function changeMenuStatus(status) {
	isCollapsed.value = status
}
</script>

<style lang="less" scoped>

.home_container {
	height: 100%;
	background-color: #FFFFFF;
}

.el-main {
	display: block;
	padding: 0;
}
</style>