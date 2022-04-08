<script setup lang="ts">
import { ref } from 'vue'
import { api } from '@/http-api';
import { ImageType } from '@/image';
import router from "@/router";
import ImageDisplay from './ImageDisplay.vue';

const imageList = ref<ImageType[]>([]);
document.title = 'Gallerie'

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
  var modalImg = document.getElementsByClassName("modal-content")
  const elem = e.target;
  modalImg.src = elem.dataset.biggerSrc || elem.src;
  window.location.href = modalImg.src;
  windowframe64(modalImg.src);
  //router.push({ name: 'image', params: { id: selectedId.value } })
  }
</script>




<template>
<h3>Gallery</h3>
<div id="myModal" class="modal" v-for="image in imageList">
    <ImageDisplay class="modal-content" :id="image.id" @click="select($event)" />
  </div>

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
    outline: 1px solid rgba(255,255,255,.5);

}
img:active{
    margin:0px 20px;
    display:inline-block;
    text-decoration:none;
    color:black;
}
img:hover
{
   opacity: 0.7;
   -ms-transform: scale(1.3); /* IE 9 */
   -webkit-transform: scale(1.3); /* Safari 3-8 */
   transform: scale(1.3); 

}


</style>
