<template>
	<section class="section">
		<div class="columns is-mobile">
			<div class="column is-one-quarter">
				<card title="CLIPS" icon="github-circle">
					<button class="button is-link">View Presentation</button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="Elixir" icon="github-circle">
				<button class="button is-link">View Presentation</button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="Factor" icon="github-circle">
					<button class="button is-link">View Presentation</button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="IO" icon="github-circle">
					<button class="button is-link">View Presentation</button>
				</card>
			</div>
		</div>
		
		<div class="columns is-mobile">
			<div class="column is-one-quarter">
				<card title="Julia" icon="github-circle">
					<button class="button is-link">View Presentation</button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="Lua" icon="github-circle">
					<button class="button is-link">View Presentation</button>
				</card>
			</div>

			<div class="column is-one-quarter">
				<card title="PROLOG" icon="github-circle">
					<button class="button is-link">View Presentation</button>
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
