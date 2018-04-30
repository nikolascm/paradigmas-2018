/*   
     Nikolas Machado Corrêa - Paradigmas de Programação
     Trabalho 4: Resolvendo problemas da OBI em Prolog
     
     Problema: Uma banda formada por alunos e alunas da escola está gravando um CD com exatamente
     sete músicas distintas – S, T, V, W, X, Y e Z. Cada música ocupa exatamente uma das sete 
     faixas contidas no CD. Algumas das músicas são sucessos antigos de rock; outras são 
     composições da própria banda. As seguintes restrições devem ser obedecidas: 
     - S ocupa a quarta faixa do CD. 
     - Tanto W como Y precedem S no CD (ou seja, W e Y estão numa faixa que é tocada antes de S no CD). 
     - T precede W no CD (ou seja, T está numa faixa que é tocada antes de W). 
     - Um sucesso de rock ocupa a sexta faixa do CD. 
     - Cada sucesso de rock é imediatamente precedido no CD por uma composição da banda (ou seja, no CD cada sucesso de rock toca imediatamente após uma composição da banda). 
     - Z é um sucesso de rock
*/
regra1(L) :-
     L = [_,_,_,s,_,_,_].    

regra2(L) :-
     nth0(W,L,w),
     nth0(Y,L,y),
     W<3,
     Y<3.

regra3(L) :-
     nth0(T,L,t),
     nth0(W,L,w),
     T < W.

regra4(L) :-
     nth0(6,L,Id).

regra5(L,A) :-
     nth0(1,L,A);
     nth0(5,L,A).

regra6(L) :-
     regra5(L,z).

% Regras Combinadas
cd_independente(L) :-
   L = [_,_,_,_,_,_,_],
   Musicas = [s,t,v,w,x,y,z],
   permutation(Musicas, L),
   regra1(L),
   regra2(L),
   regra3(L),
   regra4(L),
   regra5(L,A),
   regra6(L).

/*
Questão 11. Qual das seguintes alternativas poderia ser a
ordem das músicas no CD, da primeira para a sétima faixa?

(A) T, W, V, S, Y, X, Z
(B) V, Y, T, S, W, Z, X
(C) X, Y, W, S, T, Z, S
(D) Y, T, W, S, X, Z, V
(E) Z, T, X, W, V, Y, S
*/

/*
 ?- cd_independente([t,w,v,s,y,x,z]).
 ?- cd_independente([v,y,t,s,w,z,x]).
 ?- cd_independente([x,y,w,s,t,z,s]).
 ?- cd_independente([y,t,w,s,x,z,v]). R: true
 ?- cd_independente([z,t,x,w,v,y,s]).
 */