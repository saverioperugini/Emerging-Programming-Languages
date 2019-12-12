; factorial with lazy sequences
(defn factorials
      ([] (factorials 1 1))
      ([p c]
       (lazy-seq
         (cons (* c p) (factorials (* p c) (+ c 1)))
         )
       )
      )

(defn getFact
      [n] (cond
            (= n 0) 1
            (= n 1) 1
            :else (nth (factorials) (- n 1))
            )
      )

; Increment and decrement closure

(defn counterIncDec
      [step init]
      (let [a (atom init)]
           (list
             (fn []
                 (do
                   (swap! a #(+ %1 step))
                   @a
                   )
                 )
             (fn []
                 (do
                   (swap! a #(- %1 step))
                   @a
                   )
                 ))))

(def ctr1 (counterIncDec 5 0))
(def incCtr1 (nth ctr1 0))
(def decCtr1 (nth ctr1 1))

