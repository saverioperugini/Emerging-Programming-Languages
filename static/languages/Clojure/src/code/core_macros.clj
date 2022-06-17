;*******************************************************************************
;
;      filename:  core_macros.clj
;
;   description:  Clojure Demo File
;
;        author:  McKernan, Thomas A.
;       Copyright (c) 2019 Saverio Perugini, University of Dayton
;******************************************************************************/
(in-ns 'clojure-demo.core)

(println "Macros")

(defmacro inc3
  [x]
  (list + 3 x)
  )
