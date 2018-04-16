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

% 4. Defina um predicado potN0(N,L), de forma que L seja uma lista de potências de 2, com expoentes de N a 0.
potN0(0,[1]).
potN0(N,L) :- N>0, N1 is N-1, potN0(N1,L1), M is 2^N, insereInicio(M,L1,L).

% Insere um elemento no início de uma lista
insereInicio(H, T, [H|T]):- !.

% 5. Defina um predicado zipmult(L1,L2,L3), de forma que cada elemento da lista L3 seja o produto dos elementos de L1 e L2 na mesma posição do elemento de L3. 
zipmult([],[],[]).
zipmult(L1,L2,L3) :- [A|B] = L1, [C|D] = L2, zipmult(B,D,Y), X is A*C, insereInicio(X,Y,L3). 

% 6. Defina um predicado potencias(N,L), de forma que L seja uma lista com as N primeiras potências de 2, sendo a primeira 2^0 e assim por diante.
potencias(0,[1]).
potencias(N,L) :- inverte(L,L1), potN0(N-1,L1).

% Inverte uma Lista
inverte([], []).
inverte([H|T], L) :- inverte(T, X), append(X, [H], L).