{----------------------------------------------------------------------------------
/
/      filename:  IdrisHelperExample.idr
/
/   description:  Program to demonstrate the power of the strong typing Idris uses
/					to help build functions for us.
/
/        author:  Henry, Jonathon P.
/      Copyright (c) 2019 Jonathon P. Henry, University of Dayton
----------------------------------------------------------------------------------}


data Vect : Nat -> Type -> Type where
	Nil : Vect Z a
	(::) : a -> Vect k a -> Vect (S k) a

{-
 %name acts as a hint for the program, and declares that any names generated
 for the type Vect should be generated in the given order.
-}
%name Vect xs, ys, zs


append : Vect n a -> Vect m a -> Vect (n+m) a
append [] ys = ys
append (x :: xs) ys = x :: append xs ys

zipWith : (a -> b -> c) -> Vect n a -> Vect n b -> Vect n c
zipWith f [] [] = []
zipWith f (x :: xs) (y :: ys) = f x y :: zipWith f xs ys

empty_vectors : Vect m (Vect 0 a)
empty_vectors {m = Z} = []
empty_vectors {m = (S k)} = [] :: empty_vectors

transpose_rhs_2 : (x : Vect m a) -> (xs_transpose : Vect m (Vect k a)) -> Vect m (Vect (S k) a)
transpose_rhs_2 [] xs_transpose = []
transpose_rhs_2 (x :: xs) (y :: ys) = (x :: y) :: transpose_rhs_2 xs ys

transpose : Vect n (Vect m a) -> Vect m (Vect n a)
transpose [] = empty_vectors
transpose (x :: xs) = let xs_transpose = transpose xs in
																						transpose_rhs_2 x xs_transpose
