#lang racket
;;;;****************************************************************************
;;;;
;;;;      filename:  addo.rkt
;;;;
;;;;   description:  Determines a number is not in a list in miniKanren
;;;;
;;;;        author:  Benjamin Amato
;;;; Copyright (c) 2019 Benjamin Amato, University of Dayton
;;;;
;;;;****************************************************************************
(require Racket-miniKanren/miniKanren/mk)
(define absento
  (lambda (elem l)
    (fresh(a d)
         (conde
          ((== l '()))
          ((== `(,a . ,d) l)
           (=/= elem a)
           (absento elem d))))))

(run 1 (q) (== 2 2))
(run 1 (q) (absento 1 '(1 2 3))(== q 1))
(run 1 (q) (absento 1 '())(== q 1))
(run 1 (q) (absento 2 '(1 2 3))(== q 1))
(run 1 (q) (absento 4 '(1 2 3))(== q 1))
