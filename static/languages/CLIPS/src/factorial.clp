(defrule factorial
   (fact_run ?x)
   =>
   (assert (fact ?x 1)))

(defrule fact_helper
   ?t <- (fact ?x ?y)
   (test (> ?x 0))
   =>
   (retract ?t)
   (assert (fact (- ?x 1) (* ?x ?y))))

(assert (fact_run 5))   
