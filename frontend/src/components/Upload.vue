<script setup lang="ts">
import { ref } from "vue";
import { api } from "@/http-api";
document.title = "Upload";

const target = ref<HTMLInputElement>();

function submitFile() {
	if (
		target.value !== null &&
		target.value !== undefined &&
		target.value.files !== null
	) {
		for (const file of target.value.files) {
			if (file === undefined) return;
			let formData = new FormData();
			formData.append("file", file);
			api.createImage(formData)
				.then(() => {
					if (target.value !== undefined) target.value.value = "";
				})
				.catch((e) => {
					console.log(e.message);
				});
		}
	}
}

function handleFileUpload(event: Event) {
	target.value = event.target as HTMLInputElement;
}
</script>

<template>
	<div>
		<h3>Upload an image</h3>
		<div>
			<input
				type="file"
				id="file"
				ref="file"
				multiple
				@change="handleFileUpload"
			/>
		</div>
		<div>
			<button @click="submitFile">Submit</button>
		</div>
	</div>
</template>

<style scoped></style>
