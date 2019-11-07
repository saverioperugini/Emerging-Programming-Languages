<template>
	<div>
		<div style="overflow-x: scroll">
			<table>
				<thead>
				<tr>
					<th style="vertical-align: bottom">Concept/Language</th>
					<th v-for="lang in Object.keys(languages)" :key="lang" class="rotate">
						<div>
							<span>{{ lang }}</span>
						</div>
					</th>
				</tr>
				<tr>
					<th>Language Paradigm</th>
					<th v-for="lang in Object.keys(languages)" :key="lang">{{ languages[lang]["Paradigm"] }}</th>
				</tr>
				</thead>
				<tbody>
				<tr v-for="concept in concepts" :key="concept">
					<td>{{ concept }}</td>
					<td v-for="lang in Object.keys(languages)" :key="lang">
						<label>
							<input type="checkbox" :class="checkboxValue(languages[lang][concept])" onclick="
						switch (this.classList[0]) {
							case 'checked':
								this.checked = true;
								break;
							case 'indeterminate':
								this.indeterminate = true;
								break;
							default:
								this.checked = false;
					}"/>
						</label>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<p>
			<strong>Legend:</strong><br/>
			<label><input type="checkbox" class="indeterminate" onclick="this.indeterminate = true"/></label> = pseudo-support (i.e., meaning either partial support, can be built from within the language, or realized through a (third-party) library);<br/>
			fun = functional; impr = imperative; meta = <em>meta</em>language; mult = multi-paradigm; and oo = object-oriented
		</p>
	</div>
</template>

<script>
	export default {
		name: "ConceptsTable",
		data: () => ({
			concepts: ["First-Class Functions", "λ Expressions", "Higher-Order Functions", "First-Class Closures", "Currying", "Continuation-Passing Style", "First-Class Continuations", "Pattern Matching", "Strong Typing", "Type Systems", "Lazy Evaluation", "List/Generator Comprehension", "Message Passing", "Actor Model of Concurrency", "Coroutines", "Channels", "Communicating Sequential Processes", "Homoiconic", "Concurrency", "Callbacks", "Type Inference", "Dynamic Dispatch", "Dynamic Typing", "Heterogeneous Lists"],
			languages: {
				"Racket": {
					"Paradigm": "meta",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 2,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 1,
					"Pattern Matching": 1,
					"Strong Typing": 0,
					"Type Systems": 0,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 2,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 1,
					"Concurrency": 2,
					"Callbacks": 1,
					"Type Inference": 0,
					"Dynamic Dispatch": 1,
					"Dynamic Typing": 1,
					"Heterogeneous Lists": 1
				},
				"ML": {
					"Paradigm": "fun",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 1,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 1,
					"Pattern Matching": 1,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 2,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 2,
					"Callbacks": 1,
					"Type Inference": 1,
					"Dynamic Dispatch": 1,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"Haskell": {
					"Paradigm": "meta",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 1,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 1,
					"Pattern Matching": 1,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 1,
					"List/Generator Comprehension": 1,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 1,
					"Dynamic Dispatch": 1,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"Ruby": {
					"Paradigm": "mult",
					"First-Class Functions": 2,
					"λ Expressions": 1,
					"Higher-Order Functions": 2,
					"First-Class Closures": 1,
					"Currying": 1,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 1,
					"Pattern Matching": 2,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 1,
					"Message Passing": 1,
					"Actor Model of Concurrency": 2,
					"Coroutines": 2,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 2,
					"Type Inference": 0,
					"Dynamic Dispatch": 2,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"Python": {
					"Paradigm": "mult",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 2,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 2,
					"Pattern Matching": 2,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 1,
					"Message Passing": 2,
					"Actor Model of Concurrency": 2,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 0,
					"Dynamic Dispatch": 2,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"JavaScript": {
					"Paradigm": "mult",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 2,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 2,
					"Pattern Matching": 2,
					"Strong Typing": 0,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 2,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 0,
					"Callbacks": 1,
					"Type Inference": 1,
					"Dynamic Dispatch": 2,
					"Dynamic Typing": 1,
					"Heterogeneous Lists": 1
				},
				"Elm": {
					"Paradigm": "fun",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 1,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 1,
					"Pattern Matching": 1,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 0,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 2,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 2,
					"Callbacks": 1,
					"Type Inference": 1,
					"Dynamic Dispatch": 1,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"Go": {
					"Paradigm": "impr",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 2,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 0,
					"Pattern Matching": 0,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 0,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 0,
					"Channels": 1,
					"Communicating Sequential Processes": 1,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 1,
					"Dynamic Dispatch": 0,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"Elixir": {
					"Paradigm": "mult",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 2,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 2,
					"Pattern Matching": 1,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 1,
					"Message Passing": 1,
					"Actor Model of Concurrency": 1,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 0,
					"Dynamic Dispatch": 2,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"Lua": {
					"Paradigm": "meta",
					"First-Class Functions": 1,
					"λ Expressions": 2,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 2,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 2,
					"Pattern Matching": 2,
					"Strong Typing": 2,
					"Type Systems": 2,
					"Lazy Evaluation": 2,
					"List/Generator Comprehension": 0,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 1,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 0,
					"Dynamic Dispatch": 2,
					"Dynamic Typing": 1,
					"Heterogeneous Lists": 1
				},
				"Java": {
					"Paradigm": "oo",
					"First-Class Functions": 2,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 2,
					"Continuation-Passing Style": 2,
					"First-Class Continuations": 2,
					"Pattern Matching": 2,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 2,
					"Message Passing": 1,
					"Actor Model of Concurrency": 0,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 2,
					"Type Inference": 0,
					"Dynamic Dispatch": 2,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"IO": {
					"Paradigm": "oo",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 2,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 1,
					"Pattern Matching": 2,
					"Strong Typing": 0,
					"Type Systems": 1,
					"Lazy Evaluation": 1,
					"List/Generator Comprehension": 2,
					"Message Passing": 1,
					"Actor Model of Concurrency": 1,
					"Coroutines": 1,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 2,
					"Dynamic Dispatch": 2,
					"Dynamic Typing": 1,
					"Heterogeneous Lists": 1
				},
				"Julia": {
					"Paradigm": "mult",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 0,
					"Continuation-Passing Style": 0,
					"First-Class Continuations": 2,
					"Pattern Matching": 2,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 1,
					"Message Passing": 2,
					"Actor Model of Concurrency": 2,
					"Coroutines": 1,
					"Channels": 1,
					"Communicating Sequential Processes": 2,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 1,
					"Dynamic Dispatch": 2,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"Factor": {
					"Paradigm": "mult",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 1,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 1,
					"Pattern Matching": 0,
					"Strong Typing": 0,
					"Type Systems": 1,
					"Lazy Evaluation": 1,
					"List/Generator Comprehension": 1,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 1,
					"Concurrency": 0,
					"Callbacks": 1,
					"Type Inference": 0,
					"Dynamic Dispatch": 0,
					"Dynamic Typing": 1,
					"Heterogeneous Lists": 1
				},
				"PROLOG": {
					"Paradigm": "logic",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 1,
					"Continuation-Passing Style": 0,
					"First-Class Continuations": 0,
					"Pattern Matching": 1,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 1,
					"List/Generator Comprehension": 1,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 1,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 1,
					"Dynamic Dispatch": 1,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"CLIPS": {
					"Paradigm": "logic",
					"First-Class Functions": 0,
					"λ Expressions": 0,
					"Higher-Order Functions": 0,
					"First-Class Closures": 0,
					"Currying": 0,
					"Continuation-Passing Style": 0,
					"First-Class Continuations": 0,
					"Pattern Matching": 1,
					"Strong Typing": 0,
					"Type Systems": 1,
					"Lazy Evaluation": 0,
					"List/Generator Comprehension": 0,
					"Message Passing": 0,
					"Actor Model of Concurrency": 0,
					"Coroutines": 0,
					"Channels": 0,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 1,
					"Concurrency": 0,
					"Callbacks": 0,
					"Type Inference": 1,
					"Dynamic Dispatch": 1,
					"Dynamic Typing": 1,
					"Heterogeneous Lists": 1
				},
				"Kotlin": {
					"Paradigm": "mult",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 1,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 2,
					"Pattern Matching": 0,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 1,
					"List/Generator Comprehension": 1,
					"Message Passing": 1,
					"Actor Model of Concurrency": 1,
					"Coroutines": 1,
					"Channels": 1,
					"Communicating Sequential Processes": 1,
					"Homoiconic": 0,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 1,
					"Dynamic Dispatch": 0,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
				"F#": {
					"Paradigm": "fun",
					"First-Class Functions": 1,
					"λ Expressions": 1,
					"Higher-Order Functions": 1,
					"First-Class Closures": 1,
					"Currying": 1,
					"Continuation-Passing Style": 1,
					"First-Class Continuations": 1,
					"Pattern Matching": 1,
					"Strong Typing": 1,
					"Type Systems": 1,
					"Lazy Evaluation": 2,
					"List/Generator Comprehension": 1,
					"Message Passing": 1,
					"Actor Model of Concurrency": 1,
					"Coroutines": 2,
					"Channels": 2,
					"Communicating Sequential Processes": 0,
					"Homoiconic": 2,
					"Concurrency": 1,
					"Callbacks": 1,
					"Type Inference": 1,
					"Dynamic Dispatch": 1,
					"Dynamic Typing": 0,
					"Heterogeneous Lists": 0
				},
			}
		}),
		methods: {
			checkboxValue: function (value) {
				switch (value) {
					case 0:
						return "unchecked";
					case 1:
						return "checked";
					case 2:
						return "indeterminate";
				}
			},
			checkboxOnClick: function (self) {
				switch (self.classList[0]) {
					case "checked":
						self.checked = true;
						break;
					case "indeterminate":
						self.indeterminate = true;
						break;
					default:
						self.checked = false;
				}
			}
		},
		mounted() {
			let blanks = document.getElementsByClassName("unchecked");
			let checks = document.getElementsByClassName("checked");
			let lines = document.getElementsByClassName("indeterminate");
			
			for (let i = 0; i < checks.length; i++) {
				checks[i].checked = true
			}
			
			for (let i = 0; i < lines.length; i++) {
				lines[i].indeterminate = true
			}
			
			for (let i = 0; i < blanks.length; i++) {
				// blanks[i].disabled = true
			}
		}
	}
</script>

<style scoped>
	td {
		border-right: 1px solid #ccc;;
	}
	
	tbody tr:nth-child(2n) {
		background: #ddd;
	}
	
	td:nth-child(n + 2), th:nth-child(n + 2) {
		text-align: center;
		width: 3.5rem !important;
		/*overflow-x: hidden;*/
	}
	
	th.rotate {
		vertical-align: middle;
		height: 140px;
		white-space: nowrap;
	}
	
	th.rotate > div {
		transform:
				translate(25px, 51px)
				rotate(315deg);
		width: 30px;
	}
	th.rotate > div > span {
		border-bottom: 1px solid #ccc;
		padding: 5px 10px;
	}
</style>