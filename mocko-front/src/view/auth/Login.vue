<template>
	<div class="login-container">
		<div class="login-box">

			<!-- avatar -->
			<div class="avatar-box">
				<img src="/favicon.svg" alt="Logo"/>
			</div>

			<!-- title -->
			<h2 class="title-info">MOCKO</h2>

			<!--表单信息-->
			<el-form status-icon
			         :model="form"
			         ref="formRef"
			         :rules="loginFormRules" class="login-form">

				<el-form-item label="" prop="username">
					<el-input :prefix-icon="User"
					          placeholder="用户名"
					          v-model="form.username"
					/>
				</el-form-item>

				<el-form-item label="" prop="password">
					<el-input type="password"
					          :prefix-icon="Lock"
					          placeholder="密码"
					          v-model="form.password"
					          autocomplete="off"
					          show-password
					/>
				</el-form-item>

				<el-form-item class="login-btn">
					<el-button type="primary" @click="submitLogin()">登录</el-button>
					<el-button type="info">重置</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>


<script setup>
import {ref} from 'vue'
import {useRouter} from 'vue-router'
import {Lock, User} from "@element-plus/icons-vue"
import {useAuthStore} from '@/store/auth'


const router = useRouter()
const authStore = useAuthStore()


const form = ref({
	username: '',
	password: ''
});

const formRef = ref(null)

function submitLogin() {

	formRef.value.validate(
		(valid) => {
			if (valid) {
				const formData = {...form.value}
				authStore.loginHandle(formData)
					.then(() => {
						router.push({
							name: 'Home'
						})
					})
			}
		}
	)

}


const loginFormRules = {
	username: [{
		required: true, trigger: 'blur', message: '用户名不可为空'
	}],
	password: [{
		required: true, trigger: 'blur', message: '密码不可为空'
	}]
}

</script>

<style lang="less" scoped>
.login-box {
	// 宽450像素
	width: 390px;
	// 高300像素
	height: 310px;
	// 背景颜色
	background-color: #fff;
	// 圆角边框3像素
	border-radius: 3px;
	// 绝对定位
	position: absolute;
	// 距离左则50%
	left: 50%;
	// 上面距离50%
	top: 50%;
	// 进行位移，并且在横轴上位移-50%，纵轴也位移-50%
	transform: translate(-50%, -50%);

	.avatar-box {
		// 盒子高度130像素
		height: 90px;
		// 宽度130像素
		width: 90px;
		// 边框线1像素 实线
		border: 1px solid #eee;
		// 圆角
		border-radius: 50%;
		// 内padding
		padding: 10px;
		// 添加阴影 向外扩散10像素 颜色ddd
		box-shadow: 0 0 10px #ddd;
		// 绝对定位
		position: absolute;
		// 距离左则
		left: 50%;
		// 位移
		transform: translate(-50%, -50%);
		// 背景
		background-color: #fff;

		img {
			width: 100%;
			height: 100%;
			border-radius: 50%;
			background-color: #eee;
		}
	}


	.title-info {
		margin: 0;
		padding: 75px 0 0 0;
		text-align: center;
		font-family: 'Monospaced', 'serif';
		letter-spacing: 0.3em;
	}

}

.login-form {
	position: absolute;
	bottom: 0;
	width: 100%;
	padding: 0 20px;
	box-sizing: border-box;
	font-size: 600px
}

.login-btn {
	// 设置弹性布局
	display: flex;
	// 横轴上尾部对齐
	justify-content: flex-end;
}
</style>


