<template>
	<section class="section">
		<div class="columns is-mobile">
			<div class="column is-one-quarter">
				<card title="CLIPS" icon="github-circle">
					<b-button class="button is-link" type="is-primary">View Presentation</b-button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="Elixir" icon="github-circle">
				<b-button class="button is-link" type="is-primary">View Presentation</b-button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="Factor" icon="github-circle">
					<b-button class="button is-link" type="is-primary">View Presentation</b-button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="IO" icon="github-circle">
					<b-button class="button is-link" type="is-primary">View Presentation</b-button>
				</card>
			</div>
		</div>
		
		<div class="columns is-mobile">
			<div class="column is-one-quarter">
				<card title="Julia" icon="github-circle">
					<b-button class="button is-link" type="is-primary">View Presentation</b-button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="Lua" icon="github-circle">
					<b-button class="button is-link" type="is-primary">View Presentation</b-button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="PROLOG" icon="github-circle">
					<b-button class="button is-link" type="is-primary">View Presentation</b-button>
				</card>
			</div>
		</div>
		<ConceptsTable/>
	</section>
</template>

<script>
	import Card from "~/components/Card"
	import ConceptsTable from "../components/ConceptsTable";
	
	export default {
		name: "HomePage",
		
		components: {
			Card,
			ConceptsTable
		},
		created() {
		
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
	$primary: #ef4542;
	
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