! Copyright (C) 2017 Zackery Arnold.
! See http://factorcode.org/license.txt for BSD license.
USING: fry kernel math math.order sequences strings unicode.categories ;
IN: homework

<PRIVATE               
: wrap-uppers   ( c x -- y ) [ 65 - ] dip + 52 mod dup 26 < [ 65 + ] [ 26 mod 97 + ] if ;
: wrap-lowers   ( c x -- y ) [ 97 - ] dip + 52 mod dup 26 < [ 97 + ] [ 26 mod 65 + ] if ;

: wrap-uppers-n ( c x -- y ) [ 65 - ] dip + 52 mod dup 0 >= [ 65 + ] [ 27 mod 123 + ] if ;
: wrap-lowers-n ( c x -- y ) [ 97 - ] dip + 52 mod dup 0 >= [ 97 + ] [ 27 mod 91 + ] if ;

: wrapc   ( c x -- y ) over 90 > [ wrap-lowers ]   [ wrap-uppers ] if ;
: wrapc-n ( c x -- y ) over 90 > [ wrap-lowers-n ] [ wrap-uppers-n ] if ;
PRIVATE>

! Initially, just get a runtime configurable map function.
: caesar ( str n -- cipher ) [ [ Letter? ] filter ] dip '[ _ dup 0 < [ wrapc-n ] [ wrapc ] if ] map ; 
