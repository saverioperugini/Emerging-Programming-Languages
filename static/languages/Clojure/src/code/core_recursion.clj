;*******************************************************************************
;
;      filename:  core_recursion.clj
;
;   description:  Clojure Demo File
;
;        author:  McKernan, Thomas A.
;       Copyright (c) 2019 Saverio Perugini, University of Dayton
;******************************************************************************/
(in-ns 'clojure-demo.core)

; Clojure Recursion
(println "Recursion")

; Regular recursive function
(defn powRecursive
  "A Recursive pow function"
  [x y]
  (letfn [(powAcc
            [counter acc]
            (cond
              (= counter 0) acc
              :else (powAcc (- counter 1) (* acc x))))]
    (powAcc y 1)))

; Tail Recursive function
(defn powTailRecursive
  "A tail recursive pow function"
  [x y]
  (loop
    [counter y
     acc 1]
    (cond
      (= counter 0) acc
      :else (recur (- counter 1) (* acc x)))
    ))

; Power generator
(defn toPow
  "Creates a pow function for a particular power"
  [x]
  #(powTailRecursive %1 x))

(def square "A square function" (toPow 2))
