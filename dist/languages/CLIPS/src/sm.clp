(reset)
(defglobal ?*input* = "" )

(defrule state-input
   ?currstate <- (initial-fact)
    =>
   (retract ?currstate)
   (printout t "Input String:")
   (bind ?*input* (read))
   (assert (state 1)) )

(defrule statemachine
   ?currstate <- (state ?x)   
   (test (neq ?*input* "")) 
    =>
    (retract ?currstate)
    (if ( eq (sub-string 1 1 ?*input*) "a")
      then (if ( eq ?x 3 ) 
              then (assert (state 1))
              else (assert (state ( + ?x 1 ) )))
      else (assert (state ?x)))
   (bind ?*input* (sub-string 2 (str-length ?*input*) ?*input*)))

(defrule state_final
   (state ?x)
   (test (eq ?*input* ""))
    =>
   (if ( eq ?x 1 )
      then (assert (accept))
      else (assert (reject)))) 

(defrule reject
   (reject)
   =>
   (printout t "Rejected" crlf))

(defrule accept
   (accept)
   =>
   (printout t "Accepted" crlf))
