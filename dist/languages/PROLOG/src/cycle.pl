edge(a,b).
edge(b,a).
edge(a,c).
edge(c,d).
edge(d,a).

cycle(Start, Visited) :-
   cycle(Start, Start, [Start], Visited).

cycle(Orig, Start, Path, Visited) :-
   edge(Start,Orig),
   reverse([Orig|Path], Visited).

cycle(Orig, Start, Path, Visited) :-
   edge(Start, Next),
   \+ member(Next, Path),
   cycle(Orig, Next,
        [Next|Path], Visited).