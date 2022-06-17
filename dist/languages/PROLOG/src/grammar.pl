sentence(S) :- append(NP,VP,S), 
               noun_phrase(NP), 
               verb_phrase(VP).

noun_phrase(NP) :- append(ART,NP2,NP), 
                   det(ART), 
                   noun_phrase_adj(NP2).
                   
noun_phrase(NP) :- noun_phrase_adj(NP).

noun_phrase_adj(NP) :- append(ADJ,NPADJ,NP), 
                       adjective(ADJ), 
                       noun_phrase_adj(NPADJ).
                       
noun_phrase_adj(NP) :- noun(NP).

verb_phrase(VP) :- append(V,NP,VP), 
                   verb(V), 
                   noun_phrase(NP).
                   
verb_phrase(VP) :- verb(VP).

det([the]).
adjective([quick]).
adjective([slow]).
noun([sword]).
noun([dragoon]).
verb([cuts]).
