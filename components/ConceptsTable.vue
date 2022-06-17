<template>
	<div>
		<div style="overflow-x: scroll">
			<table style="border-bottom: 1px solid #CCCCCC">
				<thead>
				<tr>
					<th style="vertical-align: bottom; text-align: center" colspan="2">Language</th>
					<th v-for="lang in languages" :key="lang" class="rotate">
						<div>
							<span>{{ lang }}</span>
						</div>
					</th>
				</tr>
				<tr>
					<th style="border: 1px solid #CCCCCC; text-align: center" colspan="2">Paradigm</th>
					<th v-for="paradigm in Object.keys(paradigmMap)" :key="paradigm" :colspan="paradigmMap[paradigm].length" style="border: 1px solid #CCCCCC">{{ paradigm }}</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td :rowspan="concepts.length + 1" style="padding-top: 18%; border-left: 1px solid #CCCCCC;">
						<span class="vert-cell"><strong>Concepts</strong></span>
					</td>
				</tr>
				<tr v-for="concept in concepts" :key="concept">
					<td class="concept-cell" style="min-width: fit-content">{{ concept }}</td>
					<td v-for="lang in languages" :key="lang" style="vertical-align: middle">
						<label>
							<input type="checkbox" :class="checkboxValue(conceptsMap[lang][concept])" onclick="
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
		name:     "ConceptsTable",
		computed: {
			languages: function () {
				return Object.values(this.paradigmMap).flat()
			}
		},
		data:     () => ({
			concepts:    ["First-Class Functions", "λ Expressions", "Higher-Order Functions", "First-Class Closures", "Currying", "Continuation-Passing Style", "First-Class Continuations", "Pattern Matching", "Strong Typing", "Type Systems", "Lazy Evaluation", "List/Generator Comprehension", "Message Passing", "Actor Model of Concurrency", "Coroutines", "Channels", "Communicating Sequential Processes", "Homoiconic", "Concurrency", "Callbacks", "Type Inference", "Dynamic Dispatch", "Dynamic Typing", "Heterogeneous Lists"],
			paradigmMap: {
				"meta":  [
					"Haskell",
					"Lua",
					"Racket"
				],
				"fun":   [
					"Clojure",
					"Elm",
					"F#",
					"Idris",
					"ML",
					"R"
				],
				"logic": [
					"CLIPS",
					"miniKanren",
					"PROLOG"
				],
				"mult":  [
					"Elixir",
					"Factor",
					"JavaScript",
					"Julia",
					"Kotlin",
					"Python",
					"Ruby",
					"Swift"
				],
				"oo":    [
					"Io",
					"Java"
				],
				"impr":  [
					"Go"
				]
			},
			conceptsMap: {
				"CLIPS":      {
					"First-Class Functions":              0,
					"λ Expressions":                      0,
					"Higher-Order Functions":             0,
					"First-Class Closures":               0,
					"Currying":                           0,
					"Continuation-Passing Style":         0,
					"First-Class Continuations":          0,
					"Pattern Matching":                   1,
					"Strong Typing":                      0,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       0,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         1,
					"Concurrency":                        0,
					"Callbacks":                          0,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   1,
					"Dynamic Typing":                     1,
					"Heterogeneous Lists":                1
				},
				"Clojure":    {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         0,
					"First-Class Continuations":          0,
					"Pattern Matching":                   0,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    2,
					"List/Generator Comprehension":       1,
					"Message Passing":                    2,
					"Actor Model of Concurrency":         2,
					"Coroutines":                         2,
					"Channels":                           2,
					"Communicating Sequential Processes": 2,
					"Homoiconic":                         1,
					"Concurrency":                        1,
					"Callbacks":                          2,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   1,
					"Dynamic Typing":                     1,
					"Heterogeneous Lists":                1
				},
				"Elixir":     {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          2,
					"Pattern Matching":                   1,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       1,
					"Message Passing":                    1,
					"Actor Model of Concurrency":         1,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   2,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"Elm":        {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           1,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          1,
					"Pattern Matching":                   1,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       0,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         2,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        2,
					"Callbacks":                          1,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   1,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"F#":         {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           1,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          1,
					"Pattern Matching":                   1,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    2,
					"List/Generator Comprehension":       1,
					"Message Passing":                    1,
					"Actor Model of Concurrency":         1,
					"Coroutines":                         2,
					"Channels":                           2,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         2,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   1,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"Factor":     {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           1,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          1,
					"Pattern Matching":                   0,
					"Strong Typing":                      0,
					"Type Systems":                       1,
					"Lazy Evaluation":                    1,
					"List/Generator Comprehension":       1,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         1,
					"Concurrency":                        0,
					"Callbacks":                          1,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   0,
					"Dynamic Typing":                     1,
					"Heterogeneous Lists":                1
				},
				"Go":         {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          0,
					"Pattern Matching":                   0,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       0,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           1,
					"Communicating Sequential Processes": 1,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   0,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"Haskell":    {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           1,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          1,
					"Pattern Matching":                   1,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    1,
					"List/Generator Comprehension":       1,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   1,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"Idris":      {
					"First-Class Functions":              0,
					"λ Expressions":                      0,
					"Higher-Order Functions":             0,
					"First-Class Closures":               0,
					"Currying":                           0,
					"Continuation-Passing Style":         0,
					"First-Class Continuations":          0,
					"Pattern Matching":                   0,
					"Strong Typing":                      0,
					"Type Systems":                       0,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       0,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        0,
					"Callbacks":                          0,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   0,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"Io":         {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          1,
					"Pattern Matching":                   2,
					"Strong Typing":                      0,
					"Type Systems":                       1,
					"Lazy Evaluation":                    1,
					"List/Generator Comprehension":       2,
					"Message Passing":                    1,
					"Actor Model of Concurrency":         1,
					"Coroutines":                         1,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     2,
					"Dynamic Dispatch":                   2,
					"Dynamic Typing":                     1,
					"Heterogeneous Lists":                1
				},
				"Java":       {
					"First-Class Functions":              2,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         2,
					"First-Class Continuations":          2,
					"Pattern Matching":                   2,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       2,
					"Message Passing":                    1,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          2,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   2,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"JavaScript": {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          2,
					"Pattern Matching":                   2,
					"Strong Typing":                      0,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       2,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        0,
					"Callbacks":                          1,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   2,
					"Dynamic Typing":                     1,
					"Heterogeneous Lists":                1
				},
				"Julia":      {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           0,
					"Continuation-Passing Style":         0,
					"First-Class Continuations":          2,
					"Pattern Matching":                   2,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       1,
					"Message Passing":                    2,
					"Actor Model of Concurrency":         2,
					"Coroutines":                         1,
					"Channels":                           1,
					"Communicating Sequential Processes": 2,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   2,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"Kotlin":     {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           1,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          2,
					"Pattern Matching":                   0,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    1,
					"List/Generator Comprehension":       1,
					"Message Passing":                    1,
					"Actor Model of Concurrency":         1,
					"Coroutines":                         1,
					"Channels":                           1,
					"Communicating Sequential Processes": 1,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   0,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"Lua":        {
					"First-Class Functions":              1,
					"λ Expressions":                      2,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          2,
					"Pattern Matching":                   2,
					"Strong Typing":                      2,
					"Type Systems":                       2,
					"Lazy Evaluation":                    2,
					"List/Generator Comprehension":       0,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         1,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   2,
					"Dynamic Typing":                     1,
					"Heterogeneous Lists":                1
				},
				"miniKanren": {
					"First-Class Functions":              0,
					"λ Expressions":                      0,
					"Higher-Order Functions":             0,
					"First-Class Closures":               0,
					"Currying":                           0,
					"Continuation-Passing Style":         0,
					"First-Class Continuations":          0,
					"Pattern Matching":                   2,
					"Strong Typing":                      0,
					"Type Systems":                       0,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       0,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         1,
					"Concurrency":                        0,
					"Callbacks":                          0,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   0,
					"Dynamic Typing":                     1,
					"Heterogeneous Lists":                1
				},
				"ML":         {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           1,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          1,
					"Pattern Matching":                   1,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       2,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        2,
					"Callbacks":                          1,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   1,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"PROLOG":     {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           1,
					"Continuation-Passing Style":         0,
					"First-Class Continuations":          0,
					"Pattern Matching":                   1,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    1,
					"List/Generator Comprehension":       1,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         1,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   1,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"Python":     {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          2,
					"Pattern Matching":                   2,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       1,
					"Message Passing":                    2,
					"Actor Model of Concurrency":         2,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          1,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   2,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"R":          {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          1,
					"Pattern Matching":                   0,
					"Strong Typing":                      0,
					"Type Systems":                       1,
					"Lazy Evaluation":                    1,
					"List/Generator Comprehension":       1,
					"Message Passing":                    2,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           2,
					"Communicating Sequential Processes": 2,
					"Homoiconic":                         1,
					"Concurrency":                        2,
					"Callbacks":                          0,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   0,
					"Dynamic Typing":                     1,
					"Heterogeneous Lists":                1
				},
				"Racket":     {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           2,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          1,
					"Pattern Matching":                   1,
					"Strong Typing":                      0,
					"Type Systems":                       0,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       2,
					"Message Passing":                    0,
					"Actor Model of Concurrency":         0,
					"Coroutines":                         0,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         1,
					"Concurrency":                        2,
					"Callbacks":                          1,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   1,
					"Dynamic Typing":                     1,
					"Heterogeneous Lists":                1
				},
				"Ruby":       {
					"First-Class Functions":              2,
					"λ Expressions":                      1,
					"Higher-Order Functions":             2,
					"First-Class Closures":               1,
					"Currying":                           1,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          1,
					"Pattern Matching":                   2,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       1,
					"Message Passing":                    1,
					"Actor Model of Concurrency":         2,
					"Coroutines":                         2,
					"Channels":                           0,
					"Communicating Sequential Processes": 0,
					"Homoiconic":                         0,
					"Concurrency":                        1,
					"Callbacks":                          2,
					"Type Inference":                     0,
					"Dynamic Dispatch":                   2,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				},
				"Swift":      {
					"First-Class Functions":              1,
					"λ Expressions":                      1,
					"Higher-Order Functions":             1,
					"First-Class Closures":               1,
					"Currying":                           0,
					"Continuation-Passing Style":         1,
					"First-Class Continuations":          0,
					"Pattern Matching":                   0,
					"Strong Typing":                      1,
					"Type Systems":                       1,
					"Lazy Evaluation":                    0,
					"List/Generator Comprehension":       1,
					"Message Passing":                    2,
					"Actor Model of Concurrency":         2,
					"Coroutines":                         2,
					"Channels":                           2,
					"Communicating Sequential Processes": 2,
					"Homoiconic":                         0,
					"Concurrency":                        2,
					"Callbacks":                          2,
					"Type Inference":                     1,
					"Dynamic Dispatch":                   1,
					"Dynamic Typing":                     0,
					"Heterogeneous Lists":                0
				}
			}
		}),
		methods:  {
			checkboxValue:   function (value) {
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
		border-right: 1px solid #CCCCCC;;
	}
	
	thead tr:nth-child(2) th:nth-child(2n) {
	 background: #b4ffe6
	}
	
	tbody tr:nth-child(2n + 1), thead tr:nth-child(2) th:nth-child(2n - 1) {
		background: #B4FFC8;
	}
	
	
	td:nth-child(n + 2), th:nth-child(n + 2) {
		text-align: center;
		min-width: 45px !important;
		/*overflow-x: hidden;*/
	}
	
	th.rotate {
		vertical-align: middle;
		height: 145px;
		white-space: nowrap;
	}
	
	th.rotate > div {
		transform: translate(25px, 51px) rotate(315deg);
		width: 30px;
	}
	
	th.rotate > div > span {
		border-bottom: 1px solid #CCCCCC;
		padding: 5px 10px;
	}
	
	.vert-cell {
		writing-mode: vertical-rl !important;
		text-orientation: mixed;
		transform: rotate(180deg);
	}
	
	.concept-cell {
		white-space: nowrap;
		padding-left: .25rem;
		padding-right: .25rem;
	}
</style>