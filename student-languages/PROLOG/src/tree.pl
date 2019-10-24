%example of a graph in prolog

branch(a,b).
branch(a,c).
branch(b,d).
branch(c,e).

path(X,Y) :- branch(X,Z),path(Z,Y).
path(X,Y) :- branch(X,Y).
