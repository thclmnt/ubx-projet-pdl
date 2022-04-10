<script setup lang="ts">
import { api } from "@/http-api";
import { defineProps, ref } from "vue";

const props = defineProps<{ id: number }>();
const editedImageData = ref(new Blob());
const selectedAlgorithm = ref("");
const blurSelected = ref("moyen");

function applyAlgorithm() {
	const id = props.id;
	if (selectedAlgorithm.value) {
		let params = "algorithm=" + selectedAlgorithm.value;
		const div = document.getElementById(selectedAlgorithm.value);
		if (selectedAlgorithm.value === "blur") {
			const selectValue = div?.querySelector("select")?.value;
			params += "&type=" + selectValue;
			if (blurSelected.value === "moyen") {
				const inputValue = div?.querySelector("input")?.value
				params += "&value=" + inputValue;
			}
		} else if (selectedAlgorithm.value === "color") {
			const inputValue = div?.querySelector("input")?.value;
			params += "&value=" + inputValue;
		} else if (selectedAlgorithm.value === "histogramequalization") {
			const selectValue = div?.querySelector("select")?.value;
			params += "&canal=" + selectValue;
		} else if (selectedAlgorithm.value === "luminosity") {
			const inputValue = div?.querySelector("input")?.value;
			params += "&value=" + inputValue;
		} else if (selectedAlgorithm.value === "outline") {
		} else if (selectedAlgorithm.value === "flip") {
		}
		api.getImageFilter(id, params)
			.then((data: Blob) => {
				const reader = new window.FileReader();
				reader.readAsDataURL(data);
				reader.onload = () => {
					const galleryElt = document.getElementById("display");
					if (galleryElt !== null) {
						const imgExist = document.getElementById("imgmodified");
						if (imgExist === null) {
							const imgElt = document.createElement("img");
							galleryElt.appendChild(imgElt);
							if (imgElt !== null && (reader.result as string)) {
								imgElt.setAttribute(
									"src",
									reader.result as string
								);
								imgElt.setAttribute("id", "imgmodified");
							}
						} else {
							imgExist.setAttribute(
								"src",
								reader.result as string
							);
						}
					}
				};
				editedImageData.value = data;
			})
			.catch((e) => {
				console.log(e.message);
			});
	}
}

function saveEditedImageToServer() {
	if (editedImageData.value.size != 0) {
		const formData = new FormData();
		formData.append(
			"file",
			editedImageData.value,
			String(props.id) + " (edited)"
		);
		api.createImage(formData)
			.then(() => {})
			.catch((e) => {
				console.log(e);
			});
	}
}
</script>

<template>
	<select v-model="selectedAlgorithm" @change="">
		<option disabled value="">Choose an Algorithm</option>
		<option value="blur">Blur</option>
		<option value="color">Color</option>
		<option value="histogramequalization">Histogram Equalization</option>
		<option value="luminosity">Luminosity</option>
		<option value="outline">Outline</option>
		<option value="flip">Flip</option>
	</select>

	<div id="blur" v-if="selectedAlgorithm === 'blur'">
		<p>Type</p>
		<select v-model="blurSelected" @change="">
			<option value="moyen">Mean</option>
			<option value="gaussien">Gaussian</option>
		</select>
		<div v-if="blurSelected === 'moyen'">
			<p>Value :</p>
			<input
				type="range"
				min="1"
				max="100"
				value="0"
				oninput="this.nextElementSibling.value = this.value"
			/>
			<output>0</output>
		</div>
	</div>
	<div id="color" v-if="selectedAlgorithm === 'color'">
		<p>Value :</p>
		<input
			type="range"
			min="0"
			max="360"
			value="0"
			oninput="this.nextElementSibling.value = this.value"
		/>
		<output>0</output>
	</div>
	<div
		id="histogramequalization"
		v-if="selectedAlgorithm === 'histogramequalization'"
	>
		<p>Channel</p>
		<select>
			<option value="S">S (Saturation)</option>
			<option value="V">V (Value)</option>
		</select>
	</div>
	<div id="luminosity" v-if="selectedAlgorithm === 'luminosity'">
		<p>Value :</p>
		<input
			type="range"
			min="-100"
			max="100"
			value="0"
			oninput="this.nextElementSibling.value = this.value"
		/>
		<output>0</output>
	</div>
	<div id="outline" v-if="selectedAlgorithm === 'outline'"></div>
	<div id="flip" v-if="selectedAlgorithm === 'flip'"></div>
	<br>
	<button @click="applyAlgorithm">Apply Algorithm</button>

	<br />
	<br />
	<figure id="display"></figure>
	<button v-if="editedImageData.size > 0" @click="saveEditedImageToServer">
		Save Image to Gallery
	</button>
</template>

<style></style>
