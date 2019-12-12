;*******************************************************************************
;
;      filename:  core_concurrency.clj
;
;   description:  Clojure Demo File
;
;        author:  McKernan, Thomas A.
;       Copyright (c) 2019 Saverio Perugini, University of Dayton
;******************************************************************************/
(in-ns 'clojure-demo.core)

(println "Concurrency")

; Agents are asynchronous references
(def myAgent (agent 1))


; Manipulating agents by sending them a function
(send myAgent inc)

; Futures execute in a separate thread and return the last result

(defn myFutureFn
  []
  (future (Thread/sleep 5000) (println "Future complete!"))
  )

; Threads
(defn myThread
  []
  (thread
    (Thread/sleep 5000)
    (println "Im in a Thread")
    ))

; Channels

(def buff (chan))

(go
  (loop [a 1]
    (cond
      (= a 10) (>!! buff false)
      :else (do
              (>!! buff "ping")
              (println (<!! buff))
              (recur (inc a))
              )
      )
    )
  )

(go
  (loop []
    (let [value (<!! buff)]
      (if
        value
        (do
          (println value)
          (>!! buff "pong")
          (recur)
          )
        )
      )
    )
  )
