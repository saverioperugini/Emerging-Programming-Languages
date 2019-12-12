;*******************************************************************************
;
;      filename:  core_closures.clj
;
;   description:  Clojure Demo File
;
;        author:  McKernan, Thomas A.
;       Copyright (c) 2019 Saverio Perugini, University of Dayton
;******************************************************************************/
(in-ns 'clojure-demo.core)

(println "Closures")

(defn counter
  []
  (let [a (atom 0)]
    (fn [] (do
             (swap! a inc)
             @a)
      )
    ))

(def ct1 (counter))
(def ct2 (counter))

