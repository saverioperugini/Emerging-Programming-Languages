#lang racket
;;;;****************************************************************************
;;;;
;;;;      filename:  pluso_adder.rkt
;;;;
;;;;   description:  Adder in miniKanren addapeted from Scheme example
;;;;
;;;;        author:  William Byrd
;;;;
;;;;        source: Relational Programming In miniKanren: Techniques, Applications, and Implementations, 2009
;;;;        
;;;;
;;;;****************************************************************************
(require Racket-miniKanren/miniKanren/mk)

(define ando
  (lambda (x y o)
    (conde
     ((== x 0)
      (== y 0)
      (== o 0))
     ((== x 0)
      (== y 1)
      (== o 0))
     ((== x 1)
      (== y 0)
      (== o 0))
     ((== x 1)
      (== y 1)
      (== o 1)))))

(define xoro
  (lambda (x y o)
    (conde
     ((== x 1)
      (== y 0)
      (== o 1))
     ((== x 0)
      (== y 1)
      (== o 1))
     ((== x 0)
      (== y 0)
      (== o 0))
     ((== x 1)
      (== y 1)
      (== o 0)))))

(define poso
  (lambda (n)
    (fresh (a d)
           (== `(,a . ,d) n))))

(define gr_oneo
  (lambda (n)
    (fresh (a b d)
           (== `(,a ,b . ,d) n))))

(define haddo
  (lambda (x y out carry)
    (fresh (r c)
           (ando x y c)
           (xoro x y r)
           (== out r)
           (== carry c))))

(define faddo
  (lambda (c x y out cout)
    (fresh (o1 c1 c2)
           (haddo x y o1 c1)
           (haddo o1 c out c2)
           (xoro c1 c2 cout))))

(define addero
  (lambda (c x y out)
    (conde
     ((== c 0)
      (== x '())
      (== y out))
     ((== c 0)
      (== y '())
      (poso x)
      (== x out))
     ((== c 1)
      (== x '())
      (addero 0 '(1) y out))
     ((== c 1)
      (== y '())
      (poso x)
      (addero 0 '(1) x out))
     ((== x '(1))
      (== y '(1))
      (fresh (o1 c1)
             (== `(,o1 ,c1) out)
             (faddo c 1 1 o1 c1)))
     ((== x '(1))
      (gr_oneo y)
      (genaddo c x y out))
     ((== y '(1))
      (gr_oneo x)
      (gr_oneo out)
      (addero c '(1) x out))
     ((gr_oneo x)
      (gr_oneo y)
      (genaddo c x y out)))))

(define genaddo
  (lambda (ca x y out)
    (fresh (a b c d e f g)
           (== `(,a . ,d) x)
           (== `(,b . ,e) y)
           (== `(,c . ,f) out)
           (poso e)
           (poso f)
           (faddo ca a b c g)
           (addero g d e f))))

(define pluso
  (lambda (x y out)
    (addero 0 x y out)))
(define minuso
  (lambda (x y out)
    (addero 0 y out x)))
