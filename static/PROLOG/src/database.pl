%Domain: All living beings
%A(X) - X is male.

%example of fact declarations
male(boromir).
male(faramir).
male(legolas).
male(gimli).
male(aragorn).
male(gandalf). 
male(frodo).
male(sam).
male(merry).
male(pippin).


%M(X,Y) - X is the mother of Y.
mother(finduilas,boromir).
mother(finduilas,faramir).

%F(X,Y) - X is the father of Y.
father(denethor,boromir).
father(denethor,faramir).

%example rule declaration
%Y is the son of X if X is a mother or father of Y and Y is male
son(X,Y) :- mother(X,Y), male(Y).
son(X,Y) :- father(X,Y), male(Y).

friends([gimli, aragorn, legolas, boromir, gandalf, frodo, sam, merry, pippin]).

hasring(frodo).

%E is a member of L if there exists some way to append a list containing E to 
%another arbitrary list and get L
member(E,L) :- append(_,[E|_],L).
