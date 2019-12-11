#lang racket
;;;;****************************************************************************
;;;;
;;;;      filename:  addo.rkt
;;;;
;;;;   description:  Attempt add adding two numbers in miniKanren
;;;;
;;;;        author:  Benjamin Amato
;;;; Copyright (c) 2019 Benjamin Amato, University of Dayton
;;;;
;;;;****************************************************************************

(require Racket-miniKanren/miniKanren/mk)

(define add
  (lambda (lst llst)
    (add-carry lst llst 0)))

(define add-carry
  (lambda (lst llst carry)
    (cond
      ((and (null? lst) (null? llst))
       (cond
         ((eqv? 1 carry) '(1))
         (else '())))
      ((null? lst)
       (cond
         ((and (eqv? 1 carry) (eqv? 1 (car llst))) (cons 0 (add-carry '() (cdr llst) 1)))
         ((or (eqv? 1 carry) (eqv? 1 (car llst))) (cons 1 (cdr llst)))
         (else (cons 0 (cdr llst)))))
      ((null? llst)
       (cond
         ((and (eqv? 1 carry) (eqv? 1 (car lst))) (cons 0 (add-carry '() (cdr lst) 1)))
         ((or (eqv? 1 carry) (eqv? 1 (car lst))) (cons 1 (cdr lst)))
         (else (cons 0 (cdr lst)))))
      (else
       (cond
         ((and (eqv? 1 carry) (eqv? 1 (car lst)) (eqv? 1 (car llst))) (cons 1 (add-carry (car lst) (cdr llst) 1)))
         ((eqv? 1 carry)
          (cond
            ((or (eqv? 1 (car lst)) (eqv? 1 (car llst))) (cons 0 (add-carry (cdr lst) (cdr llst) 1)))
            (else (cons 1 (add-carry (cdr lst) (cdr llst) 0)))))
         (else
          (cond
            ((and (eqv? 1 (car lst)) (eqv? 1 (car llst))) (cons 0 (add-carry (cdr lst) (cdr llst) 1)))
            (else (cons 1 (add-carry (cdr lst) (cdr llst) 0))))))))))

(define add-carryo
  (lambda (lst llst carry out)
    (conde
      ((==  lst '())
       (== llst '())
       (conde
         ((== carry 1)
          (== out '(1)))
         ((== carry 0)
          (== out '()))))
      ((== lst '())
       (fresh (a d)
              (== `(,a . ,d) llst)
              (conde
               ((== 1 carry)
                (conde
                 ((== 1 a)
                  (fresh (res)
                     (== `(,0 . ,res) out)
                     (add-carryo '() d 1 res)))
                 ((== 0 a)
                  (== `(,1 . ,d) out))))
               ((== 0 carry)
                (conde
                 ((== 1 a)
                  (== `(,1 . ,d) out))
                 ((== 0 a)
                  (== `(,0 . ,d) out)))))))
      ((== llst '())
       (fresh (a d)
              (== `(,a . ,d) lst)
              (conde
               ((== 1 carry)
                (conde
                 ((== 1 a)
                  (fresh (res)
                     (== `(,0 . ,res) out)
                     (add-carryo '() d 1 res)))
                 ((== 0 a)
                  (== `(,1 . ,d) out))))
               ((== 0 carry)
                (conde
                 ((== 1 a)
                  (== `(,1 . ,d) out))
                 ((== 0 a)
                  (== `(,0 . ,d) out)))))))
      ((fresh (a aa d dd)
             (== `(,a . ,d) lst)
             (== `(,aa . ,dd) llst)
             (conde
              ((== a 1)
               (conde
                ((== aa 1)
                 (conde
                  ((== carry 1)
                   (fresh (res)
                          (== `(,1 . ,res) out)
                          (add-carryo d dd 1 res)))
                  ((== carry 0)
                   (fresh (res)
                          (== `(,0 . ,res) out)
                          (add-carryo d dd 1 res)))))
                 ((== aa 0)
                  (conde
                  ((== carry 1)
                   (fresh (res)
                          (== `(,0 . ,res) out)
                          (add-carryo d dd 1 res)))
                  ((== carry 0)
                   (fresh (res)
                          (== `(,1 . ,res) out)
                          (add-carryo d dd 0 res)))))))
              ((== a 0)
               (conde
                ((== aa 1)
                 (conde
                  ((== carry 1)
                   (fresh (res)
                          (== `(,0 . ,res) out)
                          (add-carryo d dd 1 res)))
                  ((== carry 0)
                   (fresh (res)
                          (== `(,1 . ,res) out)
                          (add-carryo d dd 0 res)))))
                 ((== aa 0)
                  (conde
                  ((== carry 1)
                   (fresh (res)
                          (== `(,1 . ,res) out)
                          (add-carryo d dd 0 res)))
                  ((== carry 0)
                   (fresh (res)
                          (== `(,0 . ,res) out)
                          (add-carryo d dd 0 res)))))))))))))

      