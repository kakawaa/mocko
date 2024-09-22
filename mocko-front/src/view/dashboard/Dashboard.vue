<template>
	<el-container class="home_container">
		<!-- 头部区域 -->
		<el-header>
			<div>
				<img src="/assassin.png" alt=""/>
				<span>Mocko Server</span>
			</div>
			<el-button type="info" @click="logout">退出</el-button>
		</el-header>
		<!-- 页面主体区域 -->
		<el-container>
			<!-- 侧边栏 -->
			<el-aside :width="isCollapse ? '64px':'200px'">
				<div class="toggle-button">|||</div>
				<!-- 侧边栏菜单区域 -->
				<el-menu active-text-color="#409Eff"
				         background-color="#545c64"
				         text-color="#fff" unique-opened
				         :collapse="isCollapse" :collapse-transition="false"
				         :router="true">
					<!-- 一级菜单 -->
					<el-sub-menu :index="item.id+''" v-for="item in menuList" :key="item.id">
						<!-- 一级菜单模板区域 -->
						<template #title>
							<el-icon :class="iconsObj[item.id]"></el-icon>
							<span>{{ item.authName }}</span>
						</template>
						<!-- 二级菜单 -->
						<el-menu-item :index="'/'+subItem.path" v-for="subItem in item.children" :key="subItem.id">
							<template #title>
								<el-icon>
									<iconMenu/>
								</el-icon>
								<span>{{ subItem.authName }}</span>
							</template>
						</el-menu-item>
					</el-sub-menu>
				</el-menu>
			</el-aside>
			<!-- 右侧内容主体区域 -->
			<el-main>
				<!-- 路由占位符 -->
				<router-view></router-view>
			</el-main>
		</el-container>
	</el-container>
</template>


<script setup>
import {ref} from 'vue'

// 左侧菜单数据对象
let menuList = ref([])

let iconsObj = ref({
	125: 'iconfont icon-users',
	103: 'iconfont icon-tijikongjian',
	101: 'iconfont icon-shangpin',
	102: 'iconfont icon-danju',
	145: 'iconfont icon-baobiao'
})


let isCollapse = false;


</script>


<style lang="less" scoped>
.home_container {
	height: 100%;
}

.el-header {
	background-color: #363d40;
	// 给头部设置一下弹性布局
	display: flex;
	// 让它贴标左右对齐
	justify-content: space-between;
	// 清空图片左侧padding
	padding-left: 0;
	// 按钮居中
	align-items: center;
	// 文本颜色
	color: #fff;
	// 设置文本字体大小
	font-size: 20px;
	// 嵌套
	> div {
		// 弹性布局
		display: flex;
		// 纵向上居中对齐
		align-items: center;
		// 给文本和图片添加间距，使用类选择器
		span {
			margin-left: 15px;
		}
	}
}

.el-aside {
	background-color: #313743;

	.el-menu {
		border-right: none;
	}
}

.el-main {
	background-color: #e9edf1;
}

.iconfont {
	margin-right: 10px;
}

.toggle-button {
	// 添加背景颜色
	background-color: #4A5064;
	// 设置文本大小
	font-size: 10px;
	// 设置文本行高
	line-height: 24px;
	// 设置文本颜色
	color: #fff;
	// 设置文本居中
	text-align: center;
	// 设置文本间距
	letter-spacing: 0.2em;
	// 设置鼠标悬浮变小手效果
	cursor: pointer;
}
</style>