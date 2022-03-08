<script setup lang="ts">
import { api } from "@/http-api";
import { ImageType } from "@/image";
import { defineProps, ref } from "vue";

const props = defineProps<{ id: number }>();
const imageList = ref<ImageType[]>([]);
getImageList();

function getImageList() {
  api.getImageList().then((data) => {
    imageList.value = data;
  }).catch(e => {
    console.log(e.message);
  });
}

function getNameFromId(id: number) {
    for (const image of imageList.value) {
        if (image.id === id) return image.name
    }
    return 'null';
}

async function downloadImage() {
    const a = document.createElement('a');
    a.href = "/images/"+props.id;
    a.download = getNameFromId(props.id);
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
}

</script>

<template>
	<button @click="downloadImage">Download</button>
</template>

<style></style>
