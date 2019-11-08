<template>
	<section class="section">
		<div class="columns is-mobile" v-for="chunk in chunkLangs">
         <div class="column is-one-quarter" v-for="(lang, index) in chunk">
            <card :title="lang.language" icon="github-circle">
               <b-button class="button" type="is-primary" @click="lang.on=true">View Presentation</b-button>
               
					<b-modal :active.sync="lang.on" :width="980">
						<div class="card" v-for="file in lang.files">
							<div class="card-content">
								<div class="media">
									<div class="media-content">
										<p class="title is-4">{{file.name}}</p>
									</div>
								</div>

								<div class="content">
									<iframe :src="file.path"></iframe>
								</div>
							</div>
						</div>
					</b-modal>
            </card>
         </div>
      </div>
		<ConceptsTable/>
	</section>
</template>

<script>
	import Card from "~/components/Card"
	import ConceptsTable from "../components/ConceptsTable";
	import _ from 'lodash';  

	export default {
      name: "HomePage",
      components: {
         Card,
         ConceptsTable
      },
      created() {
         
      },
      computed: {
         chunkLangs() {
            return _.chunk(this.languages, 4)
         }
      },
      mounted() {
         this.$axios.$get('http://localhost:3000/languages')
         .then(res => { 
				console.log(res); 
				this.languages = res;
			})
      },
      data: () => ({
         languages: [],
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