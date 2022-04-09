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
	font-family: Arial, Helvetica, sans-serif;
	color: #2c3e50;
	border-radius: 25px;
	height: fit-content;
	display: inline-flex;
	background-color: rgba(0, 0, 0, 0.4);
	-webkit-backdrop-filter: blur(10px);
	backdrop-filter: blur(10px);
	align-items: center;
	padding: 0 10px;
	margin: 50px 0 0 0;
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
	list-style: none;
	color: white;
	font-family: sans-serif;
	font-weight: bold;
	padding: 12px 16px;
	margin: 0 8px;
	position: relative;
	cursor: pointer;
	white-space: nowrap;
}
li:hover {
	filter: opacity(0.6);
	background: linear-gradient(to bottom, #e8edec, #d2d1d3);
	box-shadow: 0px 3px 20px 0px black;
	transform: scale(1.2);
}
li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}
.error {
	color: red;
	font-weight: bold;
	list-style-type: none;
	margin-top: 1em;
}
body {
	background-color: moccasin;
	height: 100vh;
	margin: 0;
	display: flex;
	justify-content: center;
}
</style>
