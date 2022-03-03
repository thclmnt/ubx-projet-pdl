<script setup lang="ts">
import { defineProps } from "vue";
import { api } from "@/http-api";
import ImageDisplay from "./ImageDisplay.vue";
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
	<div>
		<ImageDisplay :id="props.id" />
		<button @click="deleteFile">Delete</button>
	</div>
</template>

<style></style>
