;*******************************************************************************
;
;      filename:  core_control.clj
;
;   description:  Clojure Demo File
;
;        author:  McKernan, Thomas A.
;       Copyright (c) 2019 Saverio Perugini, University of Dayton
;******************************************************************************/
(in-ns 'clojure-demo.core)

; If Statement
(if (= 1 1) "True" "False")

; Cond block
(cond
  (even? 1) "Result 1"
  :else 1
  )
