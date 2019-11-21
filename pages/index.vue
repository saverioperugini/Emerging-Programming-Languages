<template>
	<section class="section">
		<div class="columns is-mobile" v-for="chunk in chunkLangs" :key="chunk">
			<card :title="lang.language" icon="github-circle" class="column is-one-quarter" v-for="lang in chunk" :key="lang">
				<div style="display: flex; flex-direction: column; justify-content: center; padding: 0">
					<b-button class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.on=true">Presentation</b-button>
					<b-button class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.on=true">Cheat Sheet</b-button>
					<b-button class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.on=true">Notes</b-button>
				</div>
				<b-modal :active.sync="lang.on" :width="980" :height="640">
					<div class="card" style="height: 640px; overflow-y: hidden; padding: 0">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="lang.files[0].path" width="980" style="height: 640px"></iframe>
							</div>
						</div>
					</div>
				</b-modal>
			
			</card>
		
		</div>
		
		<!--		</div>-->
		<ConceptsTable/>
	</section>
</template>

<script>
	import Card from "~/components/Card"
	import ConceptsTable from "../components/ConceptsTable";
	import _ from "lodash";
	
	export default {
		name: "HomePage",
		components: {
			Card,
			ConceptsTable
		},
		created() {
		},
		computed: {
			chunkLangs() {
				let x = _.chunk(this.languages, 4);
				console.log(x)
				return x
			}
		},
		mounted() {
			this.$axios.$get("http://localhost:3000/languages")
				.then(res => {
					console.log(res);
					this.languages = res;
				})
		},
		data: () => ({
			languages: []
		})
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