#lang racket
;;;;****************************************************************************
;;;;
;;;;      filename:  addo.rkt
;;;;
;;;;   description:  Simple Binary Expression in miniKanren
;;;;
;;;;        author:  Benjamin Amato
;;;; Copyright (c) 2019 Benjamin Amato, University of Dayton
;;;;
;;;;****************************************************************************
(require Racket-miniKanren/miniKanren/mk)
(define binExp
  (lambda (x y z)
    (conde
     ((== x 1)
      (== y 1)
      (== z 1))
     ((== x 0)
      (== y 0))
     ((== x 0)
      (== x 0)
      (== z 0)))))