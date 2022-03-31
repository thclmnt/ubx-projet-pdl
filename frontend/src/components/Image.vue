<script setup lang="ts">
import { defineProps } from "vue";
import ImageDisplay from "./ImageDisplay.vue";
import Delete from "./buttons/Delete.vue";
import Download from "./buttons/Download.vue";
import Algorithm from "./buttons/Algorithm.vue";
document.title = 'Image'

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

<style>
img {
    display: inline-flex;
    width:  200px;
    height: 200px;
    object-fit: cover;
    border-radius: 10px;
    margin: 30px 20px;
}</style>
