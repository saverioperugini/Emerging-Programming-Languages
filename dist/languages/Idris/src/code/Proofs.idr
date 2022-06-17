{----------------------------------------------------------------------------------
/
/      filename:  Proofs.idr
/
/   description:  Program to demonstrate the proofing system that Idris has to offer.
/
/        author:  Henry, Jonathon P.
/      Copyright (c) 2019 Jonathon P. Henry, University of Dayton
----------------------------------------------------------------------------------}



--Proofs are built into Idris!
module Main


plusAssoc : (a, b, c : Nat) -> a `plus` (b `plus` c) = (a `plus` b) `plus` c
plusAssoc Z b c = Refl
plusAssoc (S k) b c = rewrite plusAssoc k b c in
                                      ?plusAssoc_rhs_2


plusZero : (x : Nat) -> x = plus x Z
plusZero Z = Refl
plusZero (S k) = rewrite plusZero k in
      Refl


plusNat : (x : Nat) -> x = plus x (S y)


plusCommut : (x : Nat) -> (y : Nat) -> plus x y = plus y x
plusCommut Z y =  proof {
  intro
  rewrite (plusZero y)
  trivial
}

plusCommut (S k) y = ?plusCommut_rhs_2
