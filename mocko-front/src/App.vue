<template>
	<router-view/>
</template>


<script setup>
import {onMounted, watch} from 'vue'
import {useBreadcrumbStore} from "@/store/breadcrumb";
import {useRoute} from "vue-router";

const router = useRoute()
const breadcrumbStore = useBreadcrumbStore()

onMounted(
	() => {
		breadcrumbStore.set(router.matched)
	}
)

watch(router, () => {
	breadcrumbStore.set(router.matched)
})


</script>


<style scoped>

html, body {
	width: 100%;
	height: 100%;
}

body {
	margin: 0;
}

#app {
	height: 100%;
	width: 100%;
}

</style>
