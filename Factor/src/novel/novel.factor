! Copyright (C) 2017 Zackery Arnold.
! See http://factorcode.org/license.txt for BSD license.
! This file is supplemental to "An Introduction to Factor".
! See https://github.com/saverioperugini/Emerging-Languages-Spring-2017/blob/master/Factor/Factor.pdf
USING: accessors combinators io kernel math math.parser sequences strings ;
IN: novel

TUPLE: novel title author genre publisher year ID ;

: <novel> ( title author genre publisher year ID -- novel ) novel boa ;

: book-print ( book -- ) "Class: Novel" print {
   [ "Title: " swap title>> append print ]
   [ "Author: " swap author>> append print ]
   [ "Genre: " swap genre>> append print ]
   [ "Publisher: " swap publisher>> append print ]
   [ "Year: " swap year>> number>string append print ] 
   [ "ID: " swap ID>> number>string append print ]
} cleave ;
