<template>
	<section class="section">
		<div class="columns is-mobile" v-for="project in finals" :key="project">
			<div class="card">
				<header class="card-header">
					<p class="card-header-title is-size-5 has-text-grey">
						{{ fullNames[project.final] }}<br/>
						{{ titles[project.final] }}
					</p>
				</header>
				<div class="card-content">
					{{ abstracts[project.final] }}
				</div>
				<footer>
					<b-button v-if="project.language in presentations" class="button" type="is-primary" style="margin-bottom: 5px" @click="project.presentation=true">YouTube</b-button>
					<b-button v-if="project.slides.path !== ''" class="button" type="is-primary" style="margin-bottom: 5px" @click="project.slides.modal=true">PowerPoint</b-button>
					<b-button v-if="project.paper.path !== ''" class="button" type="is-primary" style="margin-bottom: 5px" @click="project.paper.modal=true">Paper </b-button>
					
					<b-modal :active.sync="project.presentation" height="90vh" width="95vw">
						<div class="card" style="width: 95vw; overflow-y: hidden; padding: 0; background: black">
							<div class="card-content" style="padding: 0">
								<div class="content">
									<iframe :src="presentations[project.username]" style="height: 90vh; width: 95vw" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
								</div>
							</div>
						</div>
					</b-modal>
					
					<b-modal :active.sync="project.slides.modal" height="95vh" width="75vw">
						<div class="card" style="width: 75vw; overflow-y: hidden; padding: 0">
							<div class="card-content" style="padding: 0">
								<div class="content">
									<iframe :src="project.slides.path" style="height: 90vh; width: 75vw"></iframe>
								</div>
							</div>
						</div>
					</b-modal>
					
					<b-modal :active.sync="project.paper.modal" height="95vh" width="55vw">
						<div class="card" style="width: 55vw; overflow-y: hidden; padding: 0">
							<div class="card-content" style="padding: 0">
								<div class="content">
									<iframe :src="project.paper.path" style="height: 90vh; width: 55vw"></iframe>
								</div>
							</div>
						</div>
					</b-modal>
				</footer>
			</div>
		</div>
	</section>
</template>

<script>
	import Card from "~/components/Card"
	
	export default {
		name: "HomePage",
		components: {
			Card
		},
		created() {
			this.$axios.$get("http://localhost:3000/languages/finals")
				.then(res => {
					console.log(res);
					this.finals = res;
					
					for (let i = 0; i < res.length; i++) {
						let name = res[i].final;
						let content = res[i].abstract;
						
						let el = document.createElement('html');
						el.innerHTML = content;
						this.fullNames[name] = el.getElementsByTagName("h3")[1].innerText;
						this.titles[name] = el.getElementsByTagName("h3")[0].innerText;
						this.abstracts[name] = el.getElementsByTagName("blockquote")[0].innerText
					}
				})
		},
		data: () => ({
			finals: [],
			fullNames: {
			
			},
			titles: {
			
			},
			abstracts: {
			
			},
			presentations: {
				"Julia": "https://www.youtube.com/embed/ZPvwzmJF4Jc",
				"Elm": "https://www.youtube.com/embed/XxFOFKXM-5s",
				"Lua": "https://www.youtube.com/embed/eOtMCUV3vL0",
				"Io": "https://www.youtube.com/embed/x2KtYbNzhSg",
				"Factor": "https://www.youtube.com/embed/FjW4-5tGidk",
				"Prolog": "https://www.youtube.com/embed/n0WfrbltxdU",
				"CLIPS": "https://www.youtube.com/embed/XX8Fxze6Np8",
			}
		}),
		methods: {
		}
	}
</script>

<style lang="scss">
	// Import Bulma's core
	@import "~bulma/sass/utilities/_all";
	
	// Set your colors
	$primary: #2EAE77;
	
	// Setup $colors to use as bulma classes (e.g. 'is-twitter')
	$colors: (
			"white": ($white, $black),
			"black": ($black, $white),
			"light": ($light, $light-invert),
			"dark": ($dark, $dark-invert),
			"primary": ($primary, $primary-invert),
			"info": ($info, $info-invert),
			"success": ($success, $success-invert),
			"warning": ($warning, $warning-invert),
			"danger": ($danger, $danger-invert),
	);
	
	// Links
	$link: $primary;
	$link-invert: $primary-invert;
	$link-focus-border: $primary;
	
	// Import Bulma and Buefy styles
	@import "~bulma";
	@import "~buefy/src/scss/buefy";
</style>
