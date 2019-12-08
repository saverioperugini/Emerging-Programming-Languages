;*******************************************************************************
;
;      filename:  core_data_structures.clj
;
;   description:  Clojure Demo File
;
;        author:  McKernan, Thomas A.
;       Copyright (c) 2019 Saverio Perugini, University of Dayton
;******************************************************************************/
(in-ns 'clojure-demo.core)

; Clojure Data Structures
(println "Data Structures")

; Lists
(list 1 2 3)
'(1 2 3)
(first '(1 2 3)) ; 1
(last '(1 2 3)) ; 3
(rest '(1 2 3)) ; (2 3)
(cons 1 '(2 3)) ; (1 2 3)

; Vectors
[1 2 3]
(rest [1 2 3]) ; (2 3)
(nth [1 2 3] 2) ; 3
([1 2 3] 2) ; 3
(concat [1 2 3] [4]) ; (1 2 3 4)

; Sets

#{1 2 3} ; #{1 3 2}
(sort #{1 3 2}) ; (1 2 3)
(sorted-set 2 3 1) ; #{1 2 3}


; Maps

(def myMap {:key "value", :a "b"})
(:key myMap) ; "value"
(sorted-map 1 :one, 3 :three, 2 :two)
; {1 :one, 2 :two, 3 :three}

