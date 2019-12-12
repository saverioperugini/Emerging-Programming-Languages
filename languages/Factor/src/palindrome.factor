! Copyright (C) 2017 Your name.
! See http://factorcode.org/license.txt for BSD license.
USING: kernel sequences unicode.categories unicode.case ;
IN: palindrome
: normalize ( string -- string ) [ Letter? ] filter >lower ;
: palindrome? ( string -- ? ) normalize dup reverse = ;
