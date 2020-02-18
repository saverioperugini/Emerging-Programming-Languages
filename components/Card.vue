<template>
	<div class="column">
		<div class="card">
			<header class="card-header">
				<p class="card-header-title is-size-5 has-text-grey">
					{{ title }}
				</p>
				<a class="has-text-grey" style="padding: 1rem" target="_blank" :href="'https://github.com/saverioperugini/Emerging-Programming-Languages/tree/gh-pages/languages/' + title">
					Source Code
				</a>
			</header>
			<div class="card-content">
				<div class="content has-text-centered" style="height: 15vw; line-height: 15vw">
					<img v-if="icon" :src="icon" :alt="title + ' logo'">
					<span v-else style="font-size: 10rem; font-weight: bold;" class="has-text-black">{{ title[0] }}</span>
				</div>
			</div>
			<footer class="card-footer">
				<div class="card-footer-item" style="justify-content: space-around">
					<slot/>
				</div>
			</footer>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			title: {
				type:     String,
				required: true
			},
			icon:  {
				type:     String,
				required: true
			}
		}
	}
</script>

<style>
	img {
		max-width: 15vw;
		max-height: 15vw;
		vertical-align: middle !important;
	}
</style>
