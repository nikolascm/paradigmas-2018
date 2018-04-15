% Trabalho 3 - Programação Lógica - Prolog

% "Aquecimento"
repete(0, _, []).
repete(N, C, L) :- 
	N > 0,
	L = [C | T],
	N1 is N - 1,
	repete(N1, C, T).

% ?- repete(1,a,L).
% R: L = [a].

% ?- repete(2,b,L).
% R: L = [b, b].

% ?- repete(-2,b,L).
% R: false

% ?- repete(2,a,[a,b,c]).
% R: false

% ?- repete(3,a,[a|T]).
% R: T = [a, a].

% 1. Defina um predicado zeroInit(L) que é verdadeiro se L for uma lista que inicia com o número 0 (zero).
zeroInit(L) :- L = [H|_], H =:= 0.

% 2. Defina um predicado has5(L) que é verdadeiro se L for uma lista de 5 elementos. Resolva este exercício sem usar um predicado auxiliar.
has5(L) :- L = [_,_,_,_,_].

% 3. Defina um predicado hasN(L,N) que é verdadeiro se L for uma lista de N elementos.
hasN(L,N) :- length(L,A), A=:=N.
