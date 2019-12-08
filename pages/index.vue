<template>
	<section class="section">
		<div class="columns is-mobile" v-for="chunk in chunkLangs" :key="chunk">
			<Card :title="lang.language" v-bind:icon="logos[lang.language]" class="column is-one-third" v-for="lang in chunk" :key="lang">
				
					<b-button v-if="lang.language in presentations" class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.presentation=true">YouTube</b-button>
				<b-button v-if="lang.notes.path !== ''" class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.notes.modal=true">Notes</b-button>
				<b-button v-if="lang.slides.path !== ''" class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.slides.modal=true">PowerPoint</b-button>
				<b-button v-if="lang.synopsis.path !== ''" class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.synopsis.modal=true">Synopsis</b-button>
				<b-button v-if="lang.reference.path !== ''" class="button" type="is-primary" style="margin-bottom: 5px" @click="lang.reference.modal=true">Reference </b-button>
				
				<b-modal :active.sync="lang.presentation" height="90vh" width="95vw">
					<div class="card" style="width: 95vw; overflow-y: hidden; padding: 0; background: black">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="presentations[lang.language]" style="height: 90vh; width: 95vw" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
							</div>
						</div>
					</div>
				</b-modal>
				
				<b-modal :active.sync="lang.slides.modal" height="95vh" width="75vw">
					<div class="card" style="width: 75vw; overflow-y: hidden; padding: 0">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="lang.slides.path" style="height: 90vh; width: 75vw"></iframe>
							</div>
						</div>
					</div>
				</b-modal>
				
				<b-modal :active.sync="lang.reference.modal" height="95vh" width="55vw">
					<div class="card" style="width: 55vw; overflow-y: hidden; padding: 0">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="lang.reference.path" style="height: 90vh; width: 55vw"></iframe>
							</div>
						</div>
					</div>
				</b-modal>
				
				<b-modal :active.sync="lang.synopsis.modal" height="95vh" width="55vw">
					<div class="card" style="width: 55vw; overflow-y: hidden; padding: 0">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="lang.synopsis.path" style="height: 90vh; width: 55vw"></iframe>
							</div>
						</div>
					</div>
				</b-modal>
				
				<b-modal :active.sync="lang.notes.modal" height="95vh" width="80vw">
					<div class="card" style="width: 80vw; overflow-y: hidden; padding: 0">
						<div class="card-content" style="padding: 0">
							<div class="content">
								<iframe :src="lang.notes.path" style="height: 90vh; width: 80vw"></iframe>
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
			presentations: {
				"Julia": "https://www.youtube.com/embed/ZPvwzmJF4Jc",
				"Elm": "https://www.youtube.com/embed/XxFOFKXM-5s",
				"Lua": "https://www.youtube.com/embed/eOtMCUV3vL0",
				"Io": "https://www.youtube.com/embed/x2KtYbNzhSg",
				"Factor": "https://www.youtube.com/embed/FjW4-5tGidk",
				"Prolog": "https://www.youtube.com/embed/n0WfrbltxdU",
				"CLIPS": "https://www.youtube.com/embed/XX8Fxze6Np8",
			},
			logos: {
				"CLIPS":      "http://clipsrules.net/clipslogo.png",
				"Clojure":    "https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg",
				"Elixir":     "https://elixir-lang.org/images/logo/logo.png",
				"Elm":        "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Elm_logo.svg/1024px-Elm_logo.svg.png",
				"FSharp":      "https://fsharp.org/img/logo/fsharp256.png",
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
				"R":          "https://www.r-project.org/logo/Rlogo.svg",
				"Racket":     "https://racket-lang.org/img/racket-logo.svg",
				"Ruby":       "https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Ruby_logo.svg/1024px-Ruby_logo.svg.png",
				"Swift":      "https://upload.wikimedia.org/wikipedia/commons/2/20/Swift_logo_with_text.svg"
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