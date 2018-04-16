% Trabalho 3 - Programação Lógica - Prolog

% 1. Defina um predicado zeroInit(L) que é verdadeiro se L for uma lista que inicia com o número 0 (zero).
zeroInit(L) :- 
	L = [H|_], 
	H =:= 0.

% 2. Defina um predicado has5(L) que é verdadeiro se L for uma lista de 5 elementos. Resolva este exercício sem usar um predicado auxiliar.
has5(L) :- 
	L = [_,_,_,_,_].

% 3. Defina um predicado hasN(L,N) que é verdadeiro se L for uma lista de N elementos.
hasN(L,N) :- 
	length(L,A), 
	A=:=N.

% 4. Defina um predicado potN0(N,L), de forma que L seja uma lista de potências de 2, com expoentes de N a 0.
potN0(0,[1]).
potN0(N,L) :- 
	N>0, 
	N1 is N-1, 
	potN0(N1,L1), 
	M is 2^N, 
	L = [M|L1].

% 5. Defina um predicado zipmult(L1,L2,L3), de forma que cada elemento da lista L3 seja o produto dos elementos de L1 e L2 na mesma posição do elemento de L3. 
zipmult([],[],[]).
zipmult(L1,L2,L3) :- 
	[A|B] = L1, 
	[C|D] = L2, 
	zipmult(B,D,Y), 
	X is A*C, 
	L3 = [X|Y]. 

% 6. Defina um predicado potencias(N,L), de forma que L seja uma lista com as N primeiras potências de 2, sendo a primeira 2^0 e assim por diante.
potencias(0,[1]).
potencias(N,L) :- 
	reverse(L,L1), 
	potN0(N-1,L1).

% 7. Defina um predicado positivos(L1,L2), de forma que L2 seja uma lista só com os elementos positivos de L1.
positivos([],[]).
positivos(L1,L2) :- 
	L1 = [H|T], 
	H>0,
	positivos(T,T1),
	L2 = [H|T1].

positivos(L1,L2) :-
	L1 = [_|T], 
	positivos(T,T1),
	L2 = T1.	

% 8. Considere que L1 e L2 sejam permutações de uma lista de elementos distintos, sem repetições. Sabendo disso, defina um predicado mesmaPosicao(A,L1,L2) para verificar se um elemento A está na mesma posição nas listas L1 e L2. 
