#lang racket
;;;;****************************************************************************
;;;;
;;;;      filename:  memebero.rkt
;;;;
;;;;   description:  Determines if element is mebero of list in miniKanren
;;;;
;;;;        author:  Benjamin Amato
;;;; Copyright (c) 2019 Benjamin Amato, University of Dayton
;;;;
;;;;****************************************************************************
(require Racket-miniKanren/miniKanren/mk)
(define membero
  (lambda (e l)
    (conde
     ((fresh (h t)
             (== `(,h ,t) l)
             (== h e)))
     ((fresh (h t)
             (== `(,h ,t) l)
             (membero e t))))))