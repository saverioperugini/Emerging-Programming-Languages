<template>
	<div>
		<nav class="navbar header has-shadow is-primary" role="navigation" aria-label="main navigation">
			<div class="navbar-brand">
				<a class="navbar-item" href="/">
					<strong>CPS 452: Emerging Programming Languages</strong>
				</a>
				
				<div class="navbar-burger">
					<span/>
					<span/>
					<span/>
				</div>
			</div>
		</nav>
		
		<section class="main-content columns">
			<aside class="column is-2 section" style="border-right: 1px solid #D7D7D7">
				<p class="menu-label is-hidden-touch">
					General
				</p>
				<ul class="menu-list">
					<li v-for="(item, key) of items" :key="key">
						<nuxt-link :to="item.to" exact-active-class="is-active">
							<b-icon :icon="item.icon"/>
							{{ item.title }}
						</nuxt-link>
					</li>
				</ul>
			</aside>
			
			<div class="container column is-10">
				<nuxt/>
			</div>
		</section>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				items: [
					{
						title: "Languages",
						icon: "home",
						to: {name: "index"}
					},
					{
						title: "Projects",
						icon: "code-tags",
						to: {name: "projects"}
					}
				]
			}
		}
	}
</script>
