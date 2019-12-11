#lang racket
;;;;****************************************************************************
;;;;
;;;;      filename:  addo.rkt
;;;;
;;;;   description:  Remeoves an element from a list in miniKanren
;;;;
;;;;        author:  Benjamin Amato
;;;; Copyright (c) 2019 Benjamin Amato, University of Dayton
;;;;
;;;;****************************************************************************
(require Racket-miniKanren/miniKanren/mk)
(define rember
  (lambda (x l)
    (cond
      ((null? l) '())
      ((eqv? (car l) x) (cdr l))
      (else (cons (car l) (rember x (cdr l)))))))
(rember 'b '(a b c))

(define rembero
  (lambda (x l out)
    (conde
      ((== '() l)(== '() out))
      ((fresh (d)
              (== `(,x . ,d) l)
              (== d out)))
      ((fresh (a d res)
             (== `(,a . ,d) l)
             (=/= a x)
             (== `(,a . ,res) out)
             (rembero x d res))))))