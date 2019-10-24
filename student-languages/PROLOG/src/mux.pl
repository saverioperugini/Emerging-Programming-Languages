mux(A,B,C,D,0,0,O) :- O = A.
mux(A,B,C,D,0,1,O) :- O = B.
mux(A,B,C,D,1,0,O) :- O = C.
mux(A,B,C,D,1,1,O) :- O = D.

NAND(1,1,0).
NAND(1,0,1).
NAND(0,1,0).
NAND(0,0,1).

mux2(A,B,C,D,0,0,O) :- O = A.
mux2(A,B,C,D,0,1,O) :- O = B.
mux2(A,B,C,D,1,0,O) :- O = C.
mux2(A,B,C,D,1,1,O) :- O = D.