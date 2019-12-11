! Copyright (C) 2017 Zackery Arnold.
! See http://factorcode.org/license.txt for BSD license.
! This file is supplemental to "An Introduction to Factor".
! See https://github.com/saverioperugini/Emerging-Languages-Spring-2017/blob/master/Factor/Factor.pdf
USING: accessors combinators io kernel math math.parser sequences strings ;
IN: library

TUPLE: novel title author genre publisher year ID ;
TUPLE: textbook title author subject publisher year ID ;
TUPLE: article title author discipline journal volume publisher year ID ;

MIXIN: library

INSTANCE: novel library
INSTANCE: textbook library
INSTANCE: article library

: <novel> ( title author genre publisher year ID -- novel ) novel boa ;
: <textbook> ( title author subject publisher year ID -- novel ) textbook boa ;
: <article> ( title author discipline journal volume publisher year ID -- novel ) article boa ;

GENERIC: book-print ( book -- )

M: novel book-print "Class: Novel" print {
   [ "Title: " swap title>> append print ]
   [ "Author: " swap author>> append print ]
   [ "Genre: " swap genre>> append print ]
   [ "Publisher: " swap publisher>> append print ]
   [ "Year: " swap year>> number>string append print ] 
   [ "ID: " swap ID>> number>string append print ]
} cleave ;

M: textbook book-print "Class: Textbook" print {
   [ "Title: " swap title>> append print ]
   [ "Author: " swap author>> append print ]
   [ "Subject: " swap subject>> append print ]
   [ "Publisher: " swap publisher>> append print ]
   [ "Year: " swap year>> number>string append print ] 
   [ "ID: " swap ID>> number>string append print ]
} cleave ;

M: article book-print "Class: Article" print {
   [ "Title: " swap title>> append print ]
   [ "Author: " swap author>> append print ]
   [ "Discipline: " swap discipline>> append print ]
   [ "Journal: " swap journal>> append print ]
   [ "Volume: " swap volume>> number>string append print ]
   [ "Publisher: " swap publisher>> append print ]
   [ "Year: " swap year>> number>string append print ] 
   [ "ID: " swap ID>> number>string append print ]
} cleave ;

GENERIC: get-ID ( book -- ID )

M: library get-ID ID>> ;