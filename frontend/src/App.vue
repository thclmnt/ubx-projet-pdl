<script setup lang="ts"></script>

<template>
	<div id="app">
		<nav>
			<ul id="menu-bar">
				<li>
					<router-link to="/">Home</router-link>
				</li>
				<li>
					<router-link to="/gallery">Gallery</router-link>
				</li>
				<li>
					<router-link to="/upload">Upload</router-link>
				</li>
			</ul>
		</nav>

		<div>
			<h1>PDL - L3</h1>
			<router-view />
		</div>
	</div>
</template>

<style>
#app {
	text-align: center;
	align-items: center;
	font-family: Arial, Helvetica, sans-serif;
	height: fit-content;
	display: inline-flex;
	color: #293949;
	border-radius: 30px;
	background-color: rgba(0, 0, 0, 0.4);
	-webkit-backdrop-filter: blur(10px);
	padding: 0 10px;
	margin: 0px 0 0 0;
	backdrop-filter: blur(15px);
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}
li {
	float: left;
	font-family: sans-serif;
	font-weight: bold;
	position: relative;
	cursor: pointer;
	color: white;
	margin: 0 8px;
	padding: 10px 15px;
}
li:hover {
	filter: opacity(0.6);
	background: linear-gradient(to bottom, #d9dbdb, #cbcbcc);
	box-shadow: 0px 3px 20px 0px black;
	transform: scale(1.2);
}
li a {
	padding: 12px 14px;
	text-decoration: none;
	display: block;
	color: white;
	text-align: center;
}

body {
	display: flex;
	justify-content: center;
	background-color: rgba(202, 154, 83, 0.904);
	margin: 0;
}
</style>
