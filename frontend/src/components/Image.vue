<script setup lang="ts">
import { defineProps } from "vue";
import ImageDisplay from "./ImageDisplay.vue";
import Delete from "./buttons/Delete.vue";
import Download from "./buttons/Download.vue";
import Algorithm from "./buttons/Algorithm.vue";

const props = defineProps<{ id: number }>();
</script>

<template>
	<div>
		<ImageDisplay :id="props.id" />
		<Delete :id="props.id" />
		<Download :id="props.id" />
		<br>
		<Algorithm :id="props.id" />
	</div>
</template>

<style></style>
