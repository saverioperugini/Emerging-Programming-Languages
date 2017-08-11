(reset)
(defglobal ?*input* = "" )
;(assert
; (state input))

(defrule state-input
   ?currstate <- (initial-fact)
    =>
   (retract ?currstate)
   (printout t "Input String:")
   (bind ?*input* (read))
   (assert (state 1)) )

(defrule state1
   ?currstate <- (state 1)   
   (test (not (eq ?*input* ""))) 
    =>
    (retract ?currstate)
    (if ( eq (sub-string 1 1 ?*input*) "a")
      then (assert (state 2))
      else (assert (state 1)))
   (bind ?*input* (sub-string 2 (str-length ?*input*) ?*input*)))

(defrule state1_accept
   (state 1)
   (test (eq ?*input* ""))
    =>
   (assert (state accept))) 

(defrule state2
   ?currstate <- (state 2)   
   (test (not (eq ?*input* ""))) 
    =>
   (retract ?currstate)   
   (if ( eq (sub-string 1 1 ?*input*) "a")
      then (assert (state 3))
      else (assert (state 2)))
   (bind ?*input* (sub-string 2 (str-length ?*input*) ?*input* )))

(defrule state2_accept
   (state 2)
   (test (eq ?*input* ""))
    =>
   (assert (state reject)))

(defrule state3
   ?currstate <- (state 3)   
   (test (not (eq ?*input* ""))) 
    =>
   (retract ?currstate)
   (if ( eq (sub-string 1 1 ?*input*) "a")
      then (assert (state 1))
      else (assert (state 3)))
   (bind ?*input* (sub-string 2  (str-length ?*input*) ?*input* )))

(defrule state3_accept
   (state 3)
   (test (eq ?*input* ""))
    =>
   (assert (state reject))) 

(defrule reject
   (state reject)
   =>
   (printout t "Rejected" crlf))

(defrule accept
   (state accept)
   =>
   (printout t "Accepted" crlf))
