! Copyright (C) 2017 Your name.
! See http://factorcode.org/license.txt for BSD license.
USING: kernel locals math math.functions ;
IN: my-quadratic

:: quad ( a b c -- x1 )
   b neg 
   b b *
   4 a c * * - sqrt
   +
   2 a * / ;

:: quad2 ( a b c -- x1 x2 )
   b neg 
   b b *
   4 a c * * - sqrt
   [ + ] [ - ] 2bi
   [ 2 a * / ] bi@ ;
