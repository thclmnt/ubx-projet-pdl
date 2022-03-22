<script setup lang="ts">
import { api } from "@/http-api";
import { defineProps, ref } from "vue";

const props = defineProps<{ id: number }>();
const selectedAlgorithm = ref("");

function applyAlgorithm() {
	const id = props.id;
    if (selectedAlgorithm.value) {
        let params = "algorithm="+selectedAlgorithm.value;
        const div = document.getElementById(selectedAlgorithm.value);
        if (selectedAlgorithm.value === "blur") {
            const selectValue = div?.querySelector('select')?.value;
            const inputValue = div?.querySelector('input')?.value;
            params+= "&type="+selectValue+"&value="+inputValue;
        }
        else if (selectedAlgorithm.value === "color") {
            const inputValue = div?.querySelector('input')?.value;
            params+= "&value="+inputValue;
        }
        else if (selectedAlgorithm.value === "histogramequalization") {
            const selectValue = div?.querySelector('select')?.value;
            params+= "&canal="+selectValue;
        }
        else if (selectedAlgorithm.value === "luminosity") {
            const inputValue = div?.querySelector('input')?.value;
            params+= "&value="+inputValue;
        }
        else if (selectedAlgorithm.value === "outline") {
        }
        console.log(params)
        api.getImageFilter(id,params).then(() => {
            
        })
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
	</select>

	<div id="blur" v-if="selectedAlgorithm === 'blur'">
		<p>Type</p>
		<select>
			<option value="moyen">Moyen</option>
			<option value="gaussien">Gaussien</option>
		</select>
		<p>Valeur :</p>
		<input type="range" min="0" max="100" value="50" oninput="this.nextElementSibling.value = this.value"/>
		<output>50</output>
	</div>
	<div id="color" v-if="selectedAlgorithm === 'color'">
		<p>Valeur :</p>
		<input type="range" min="0" max="100" value="50" oninput="this.nextElementSibling.value = this.value"/>
		<output>50</output>
	</div>
	<div id="histogramequalization" v-if="selectedAlgorithm === 'histogramequalization'">
		<p>Canal</p>
		<select>
			<option value="S">S (Saturation)</option>
			<option value="V">V (Value)</option>
		</select>
	</div>
	<div id="luminosity" v-if="selectedAlgorithm === 'luminosity'">
		<p>Valeur :</p>
		<input type="range" min="0" max="100" value="50" oninput="this.nextElementSibling.value = this.value"/>
		<output>50</output>
	</div>
	<div id="outline" v-if="selectedAlgorithm === 'outline'"></div>
	<button @click="applyAlgorithm">Apply Algorithm</button>
</template>

<style></style>