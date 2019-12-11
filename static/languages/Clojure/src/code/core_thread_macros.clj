;*******************************************************************************
;
;      filename:  core_thread_macros.clj
;
;   description:  Clojure Demo File
;
;        author:  McKernan, Thomas A.
;       Copyright (c) 2019 Saverio Perugini, University of Dayton
;******************************************************************************/
(in-ns 'clojure-demo.core)

; Clojure Macros
(println "Thread Macros")

; Thread first macro

; These two macros are equivalent
(def threadFirst (->
                   (range 10)
                   (concat '(10))
                   (rest)
                   ))

(def threadFirstAs (as->
                     (range 10) input
                     (concat input '(10))
                     (rest input)
                     ))

; These two macros are equivalent
(def threadLast (->>
                  (range 10)
                  (concat '(-1))
                  (map inc)
                  ))

(def threadLastAs (as->
                    (range 10) input
                    (concat '(-1) input)
                    (map inc input)
                    ))

; Some macro. Exits on nil. This will not error unlike ->
(def threadSome (some->
                  {:one 1, :two 2}
                  (:three)
                  (inc)
                  ))

; Cond macro. only applies the function if conditional is true
(def threadCond (cond->
                  (range 10)
                  (= 5 5) (concat '(10))
                  (= 5 4) (concat '(100))
                  ))
