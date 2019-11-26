<template>
	<section class="section">
		<div class="columns is-mobile" v-for="chunk in chunkLangs" :key="chunk">
			<Card :title="lang.language" v-bind:icon="logos[lang.language]" class="column is-one-third" v-for="lang in chunk" :key="lang">
<!--				<div style="justify-content: space-around; padding: 0">-->
<!--					<b-button v-if="lang.presentation != null" class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.presentation.modal=true">Presentation</b-button>-->
					<b-button v-if="lang.reference.path !== ''" class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.reference.modal=true">Reference </b-button>
					<b-button v-if="lang.synopsis.path !== ''" class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.synopsis.modal=true">Synopsis</b-button>
					<b-button v-if="lang.notes.path !== ''" class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.notes.modal=true">Notes</b-button>
<!--				</div>-->
				<!--<b-modal :active.sync="lang.presentation.modal" :width="980" :height="640">
					<div class="card" style="height: 640px; overflow-y: hidden; padding: 0">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="lang.files[0].path" width="980" style="height: 640px"></iframe>
							</div>
						</div>
					</div>
				</b-modal>-->
				
				<b-modal :active.sync="lang.reference.modal" :width="980" :height="640">
					<div class="card" style="height: 640px; overflow-y: hidden; padding: 0">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="lang.reference.path" width="980" style="height: 640px"></iframe>
							</div>
						</div>
					</div>
				</b-modal>
				
				<b-modal :active.sync="lang.synopsis.modal" :width="980" :height="640">
					<div class="card" style="height: 640px; overflow-y: hidden; padding: 0">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="lang.synopsis.path" width="980" style="height: 640px"></iframe>
							</div>
						</div>
					</div>
				</b-modal>
				
				<b-modal :active.sync="lang.notes.modal" :width="980" :height="640">
					<div class="card" style="height: 640px; overflow-y: hidden; padding: 0">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="lang.notes.path" width="980" style="height: 640px"></iframe>
							</div>
						</div>
					</div>
				</b-modal>
			</Card>
		</div>
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
				let x = _.chunk(this.languages, 3);
				console.log(x);
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
			languages: [],
			logos: {
				"CLIPS":      "http://clipsrules.net/clipslogo.png",
				"Clojure":    "https://clojure.org/images/clojure-logo-120b.png",
				"Elixir":     "https://elixir-lang.org/images/logo/logo.png",
				"Elm":        "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Elm_logo.svg/1024px-Elm_logo.svg.png",
				"F#":         "https://fsharp.org/img/logo/fsharp256.png",
				"Factor":     "https://upload.wikimedia.org/wikipedia/en/f/ff/NewFactorLogo.png",
				"Go":         "https://golang.org/lib/godoc/images/go-logo-blue.svg",
				"Haskell":    "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Haskell-Logo.svg/1280px-Haskell-Logo.svg.png",
				"Idris":      "https://pbs.twimg.com/profile_images/838385415132413952/6UQFD8wV_400x400.jpg",
				"Io":         "https://upload.wikimedia.org/wikipedia/commons/thumb/2/26/Io-logo.svg/1024px-Io-logo.svg.png",
				"Java":       "https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/800px-Java_programming_language_logo.svg.png",
				"JavaScript": "https://i.stack.imgur.com/Mmww2.png",
				"Julia":      "https://julialang.org/v2/img/logo.svg",
				"Kotlin":     "https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Kotlin-logo.svg/1024px-Kotlin-logo.svg.png",
				"Lua":        "https://www.lua.org/images/logo.gif",
				"Python":     "https://upload.wikimedia.org/wikipedia/commons/c/c3/Python-logo-notext.svg",
				"R":          "https://upload.wikimedia.org/wikipedia/commons/1/1b/R_logo.svg",
				"Racket":     "https://racket-lang.org/img/racket-logo.svg",
				"Ruby":       "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Ruby_logo.svg/1024px-Ruby_logo.svg.png",
				"Swift":      "https://developer.apple.com/assets/elements/icons/swift/swift-64x64.png"
			}
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