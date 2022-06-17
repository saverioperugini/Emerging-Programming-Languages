! Copyright (C) 2017 Your name.
! See http://factorcode.org/license.txt for BSD license.
USING: math math.ranges sequences ;
IN: my-factorial

: fact ( n -- n! ) [1,b] 1 [ * ] reduce ;