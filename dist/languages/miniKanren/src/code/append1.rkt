#lang racket
;;;;****************************************************************************
;;;;
;;;;      filename:  append1.rkt
;;;;
;;;;   description:  Append two numbers in miniKanren
;;;;
;;;;        author:  Benjamin Amato
;;;; Copyright (c) 2019 Benjamin Amato, University of Dayton
;;;;
;;;;****************************************************************************

(require Racket-miniKanren/miniKanren/mk)
(define append
  (lambda (l s)
    (cond
      ((null? l) s)
      (else (cons (car l)(append (cdr l) s))))))

(define appendo
  (lambda (l s out)
    (conde
     ((== l '()) (== out s))
     ((fresh (a d res)
             (== `(,a . ,d) l)
             (== `(,a . ,res) out)
             (appendo d s res))))))

(run 1 (q) (appendo '(1 2 3) '(4 5) q))

(run 2 (q) (appendo '(1 2 3) '(4 5) q))

(run 1 (q) (appendo '(1 2 3) q '(1 2 3 4 5)))
(run 1 (q) (appendo  q '(4 5) '(1 2 3 4 5)))
(run 2 (q) (appendo  q '(4 5) '(1 2 3 4 5)))
