expression([H|T],V) :- expr(T,V,H).

expr([],Value,Accumulator) :- Value is Accumulator.
expr(Equation,Value,Accumulator) :- append(_, [Operator|Optail], Equation),
                                    append(_, [Operand|Rest], Optail),
                                    apply(Accumulator, Operand, Operator, Return),
                                    expr(Rest,Value,Return).
                                    
apply(LHS,RHS,*,V) :- V is LHS * RHS.
apply(LHS,RHS,-,V) :- V is LHS - RHS.
apply(LHS,RHS,+,V) :- V is LHS + RHS.
apply(LHS,RHS,/,V) :- V is LHS / RHS.
