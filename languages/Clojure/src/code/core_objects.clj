;*******************************************************************************
;
;      filename:  core_objects.clj
;
;   description:  Clojure Demo File
;
;        author:  McKernan, Thomas A.
;       Copyright (c) 2019 Saverio Perugini, University of Dayton
;******************************************************************************/
(in-ns 'clojure-demo.core)

; Clojure Objects
(println "Objects")

; Use Interfaces to define methods
(defprotocol Person
  (getOlder [this])
  (getIntro [this])
  )

; Implement the interface with "Classes"
(defrecord Student [name age] Person
  (getOlder [this]
    (Student. (:name this) (inc (:age this))))
  (getIntro [this]
    (format
      "Hi, My name is %s and I am %d"
      (:name this)
      (:age this)
      ))
  )

; Create an object

(def tom (Student. "Tom" 21))

; Call Object methods
(println (.getIntro tom))
(def tom (.getOlder tom))
(println (.getIntro tom))
