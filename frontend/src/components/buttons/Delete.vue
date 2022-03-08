<script setup lang="ts">
import { defineProps } from "vue";
import { api } from "@/http-api";
import router from "@/router";

const props = defineProps<{ id: number }>();

function deleteFile() {
	const id = props.id;
	api.deleteImage(id)
		.then(() => {
      router.push({name: "home"});
		})
		.catch((e) => {
			console.log(e.message);
		});
}
</script>

<template>
	<button @click="deleteFile">Delete</button>
</template>

<style></style>
