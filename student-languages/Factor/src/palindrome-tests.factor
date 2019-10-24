! Copyright (C) 2017 Your name.
! See http://factorcode.org/license.txt for BSD license.
USING: tools.test palindrome ;
IN: palindrome.tests
[ f ] [ "hello" palindrome? ] unit-test
[ t ] [ "racecar" palindrome? ] unit-test
[ t ] [ "A man, a plan, a canal: Panama." palindrome? ] unit-test