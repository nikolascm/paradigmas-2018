/*   
    Nikolas Machado Corrêa - Paradigmas de Programação
    Trabalho 4: Resolvendo problemas da OBI em Prolog

    OBI2013(Fase 1, Nível 2, Modalidade Iniciação)
    Problema: Acampamento

    Oito amigos (A, B,C, D, E, F, G e H) vão acampar durante o feriado. Eles
    vão utilizar uma grande barraca, que permite acomodar duas fileiras de 
    camas, cada uma com quatro camas, conforme a figura ao lado. Uma das 
    fileiras é chamada de fileira da direita, e outra é chamada de
    fileira da esquerda. Duas camas são vizinhas de lado se estão
    na mesma fileira e têm números consecutivos.

    Cada cama de uma fileira tem uma cama vizinha de frente, da outra fileira: as camas 1 e 5 são vizinhas
    de frente, as camas 2 e 6 são vizinhas de frente, as camas 3 e 7 são vizinhas de frente, as camas 4 e
    8 são vizinhas de frente. Cada amigo vai dormir em uma cama, e as seguintes condições devem ser
    obedecidas:
    • C e F não podem ser vizinhos de lado.
    • G e H devem ser vizinhos de lado.
    • F deve dormir na cama 6.
    • Se E e H forem vizinhos de frente, então A deve dormir na cama 3.
    • Se B dormir na fileira da direita, C deve dormir na fileira da esquerda.
*/

regra1(L) :-
	nth0(C,L,c),
	nth0(F,L,f),
	Dif is abs(C-F),
	Dif>1.

regra2(L) :-
	nextto(g,h,L);
	nextto(h,g,L).

regra3(L) :-
	L = [_,_,_,_,_,f,_,_].

regra4(L) :-
	nth0(E,L,e),
	nth0(H,L,h),
	Dif is abs(E-H),
	Dif =:= 4,
	L = [_,_,a,_,_,_,_,_].

regra5(L) :-
	nth0(B,L,b),
	nth0(C,L,c),
	B < 4,
	C > 3.

% Regras Combinadas
fileira_direita(L) :-
	L = [_,_,_,_,_,_,_,_],
	Camas = [a,b,c,d,e,f,g,h],
	permutation(Camas, L),
	regra1(L),
	regra2(L),
	regra3(L),
	regra4(L),
	regra5(L).

/*
Questão 11. Em nenhuma ordem particular, qual 
das alternativas abaixo é uma lista de amigos 
que podem dormir na fileira da direita?
(A) A, B, D, E
(B) A, C, G, H
(C) B, C, G, H
(D) B, D, E, H
(E) D, F, G, H
*/

/*
fileira_direita([a,b,d,e,_,_,_,_]).
fileira_direita([a,c,g,h,_,_,_,_]). Alternativa correta. 
fileira_direita([b,c,g,h,_,_,_,_]). 
fileira_direita([b,d,e,h,_,_,_,_]). 
fileira_direita([d,f,g,h,_,_,_,_]). 
*/