<template>
	<div class="column">
		<div class="card">
			<header class="card-header">
				<p class="card-header-title is-size-5 has-text-grey">
					{{ title }}
				</p>
				<a class="has-text-grey" target="_blank" :href="'https://github.com/saverioperugini/Emerging-Languages-Spring-2017/tree/master/static/' + title">
					<b-icon icon="code-not-equal-variant" type="is-large"></b-icon>
				</a>
			</header>
			<div class="card-content">
				<div class="content has-text-centered" style="height: 15vw; text-align: center">
					<img v-if="icon" :src="icon" :alt="title + ' logo'">
					<b-icon
							v-else
							icon="code-braces"
							size="is-large"
							type="is-black"></b-icon>
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
