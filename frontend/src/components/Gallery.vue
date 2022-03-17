<script setup lang="ts">
import { ref } from 'vue'
import { api } from '@/http-api';
import { ImageType } from '@/image';
import router from "@/router";
import ImageDisplay from './ImageDisplay.vue';
import { Component } from '@angular/core';

const imageList = ref<ImageType[]>([]);
var selectedId = ref(-1);

api.getImageList()
  .then((data) => {
    imageList.value = data;
  })
  .catch(e => {
    console.log(e.message);
  });

  function windowframe64(base64URL){
    var win = window.open();
    win.document.write('<iframe src="' + base64URL  + '" frameborder="0" style="border:0; top:0px; left:0px; bottom:0px; right:0px; width:100%; height:100%;" allowfullscreen></iframe>');
}

  function select(e){
  var modal = document.getElementById("myModal");
  var modalImg = document.getElementsByClassName("modal-content")
  var captionText = document.getElementById("caption");
  const elem = e.target;
  modal.style.display = "block";
  modalImg.src = elem.dataset.biggerSrc || elem.src;
  captionText.innerHTML = elem.alt;
  window.location.href = modalImg.src;
  windowframe64(modalImg.src);
  //router.push({ name: 'image', params: { id: selectedId.value } })

  }


</script>




<template>
<h3>Gallery</h3>
  <div>
  <div id="myModal" class="modal">
    <ImageDisplay class="modal-content" v-for="image in imageList" :id="image.id" @click="select($event)" />
  </div></div>
   <div id="caption"></div>
</template>







<style>


img {
    display: inline-flex;
    width:  200px;
    height: 200px;
    object-fit: cover;
    border-radius: 10px;
    cursor: pointer;
    transition: 0.3s;
    margin: 30px 20px;
}
img:hover
{
   opacity: 0.7;

}
</style>
