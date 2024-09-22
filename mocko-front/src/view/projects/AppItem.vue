<template>
	<div class="app-box" @click="goToApp(props.item.appId)">
		<div class="app-icon">
			{{ props.item.icon }}
		</div>

		<div class="app-title">
			{{ props.item.appName }}
		</div>
	</div>
</template>

<script setup>
import {useRouter} from 'vue-router';

const router = useRouter();

const props = defineProps({
	item: {
		type: Object,
	}
})

const goToApp = (appId) => {
	router.push({name: 'MethodList', query: {'appId': appId}, replace: true})
}
</script>

<style scoped lang="less">
.app-box {
	display: grid;
	width: 150px;
	height: 150px;
	justify-content: center;

	.app-icon {
		display: flex;
		margin: 20px 30px 0 30px;
		padding: 0;
		height: 90px;
		width: 90px;
		border: 3px solid #42b983;
		border-radius: 50%;
		background-color: #FFFFFF;
		align-items: center;
		justify-content: center;
		font-size: 36px;
		font-weight: bold;
		color: #42b983;
	}

	.app-title {
		display: block;
		margin: 0 20px 6px 20px;
		padding: 0;
		width: 110px;
		height: 20px;
		overflow: hidden;
		text-align: center;
		font-family: 'Monospaced', 'serif';
		color: #1a1a1a;
	}
}


.app-box:hover {
	background-color: #F0FFF0;
	cursor: pointer;

	.app-title {
		color: #888888;
	}
}
</style>