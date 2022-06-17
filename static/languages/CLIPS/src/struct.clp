(deftemplate car
   (slot make
     (type SYMBOL)
     (allowed-symbols
      truck compact)
     (default compact))
   (multislot name
      (type SYMBOL)
      (default ?DERIVE)))

(deffacts cars
   (car (make truck)
        (name Tundra))
   (car (make compact)
        (name Accord))
   (car (make compact)
        (name Passat))
)

(defrule cheapcar
   (car (make compact)
         (name ?name))
   =>
   (printout t ?name crlf))
