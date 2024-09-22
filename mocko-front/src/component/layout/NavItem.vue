<template>
	
	<!-- parent -->
	<el-sub-menu
		:index="props.item.uri"
		v-if="props.item.children">

		<template #title>
			<el-icon v-if="props.item.icon">
				<component :is="icon(props.item.icon)"/>
			</el-icon>
			<span>{{ props.item.name }}</span>
		</template>

		<template v-for="child in props.item.children">
			<nav-item v-if="child.children" :item="child" :key="child"/>
			<el-menu-item v-else :index="child.uri" :key="child.uri">
				<el-icon v-if="child.icon">
					<component :is="icon(child.icon)"/>
				</el-icon>
				{{ child.name }}
			</el-menu-item>
		</template>
	</el-sub-menu>

	<!-- children -->
	<el-menu-item
		:index="props.item.uri"
		v-else>
		<el-icon v-if="props.item.icon">
			<component :is="icon(props.item.icon)"/>
		</el-icon>
		<span>{{ props.item.name }}</span>
	</el-menu-item>
</template>

<script setup>
import {computed} from 'vue'

const props = defineProps({
	item: {
		type: Object,
		required: true
	}
})

const icon = computed(() => (name) => {
	return name
})
</script>

<style scoped lang="less">

</style>