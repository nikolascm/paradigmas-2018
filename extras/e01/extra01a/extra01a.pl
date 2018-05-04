/*
	Nikolas Machado Corrêa - Paradigmas de Programação
    Extra extra01a: Árvore genealógica da série Dark 
*/

% Personagens Masculinos
man(aleksander).
man(bartosz).
man(egon).
man(helge).
man(jonas).
man(mads).
man(magnus).
man(mikkel).
man(peter).
man(tronte).
man(ulrich).

% Personagens Femininos
woman(agnes).
woman(charlote).
woman(claudia).
woman(elizabeth).
woman(franziska).
woman(hannah).
woman(ines).
woman(jana).
woman(katharina).
woman(martha).
woman(michael).
woman(regina).

% Famílias
kahnwald(ines).
kahnwald(michael).
kahnwald(hannah).
kahnwald(jonas).

nielsen(agnes).
nielsen(tronte).
nielsen(jana).
nielsen(katharina).
nielsen(ulrich).
nielsen(mads).
nielsen(magnus).
nielsen(martha).
nielsen(mikkel).

tiedemann(egon).
tiedemann(claudia).
tiedemann(aleksander).
tiedemann(regina).
tiedemann(bartosz).

doppler(helge).
doppler(peter).
doppler(charlotte).
doppler(franziska).
doppler(elizabeth).

% Pais e filhos
% Família Kahnwald
parent(ines, michael).
parent(michael, jonas).
parent(hannah, jonas).
% Família Nielsen
parent(agnes, tronte).
parent(tronte, ulrich).
parent(tronte, mads).
parent(jana, ulrich).
parent(jana, mads).
parent(ulrich, magnus).
parent(ulrich, martha).
parent(ulrich, mikkel).
parent(katharina, magnus).
parent(katharina, martha).
parent(katharina, mikkel).
% Família Tiedemann
parent(egon, claudia).
parent(claudia, regina).
parent(regina, bartosz).
parent(aleksander, bartosz).
% Família Doppler
parent(helge, peter).
parent(peter, franziska).
parent(peter, elizabeth).
parent(charlotte, franziska).
parent(charlotte, elizabeth).

% Relações:

% Pai e Mãe
father(X,Y) :- 
	man(X), 
	parent(X,Y).
mother(X,Y) :- 
	woman(X), 
	parent(X,Y).
% father(tronte,ulrich).
% father(tronte,X).

% Filho e Filha
son(X,Y) :- 
	man(X), 
	parent(Y,X).
daughter(X,Y) :- 
	woman(X), 
	parent(Y,X).
% son(jonas,michael).
% daughter(elizabeth,X).

% Os irmãos, "irmão de" e "irmã de"
sibling(X,Y) :- 
	parent(Z,X), 
	parent(Z,Y),
	X \= Y.
brother(X,Y) :-
	man(X), 
	sibling(X,Y). 
sister(X,Y) :-
	woman(X), 
	sibling(X,Y).
% sibling(mikkel,magnus). -> não importa o gênero
% brother(martha,magnus). -> false
% sister(martha,X).

% Tio e tia
uncle(X,Y) :-
	man(X),
	sibling(X,P),
	parent(P,Y).
aunt(X,Y) :- 
	woman(X),
	sibling(X,P),
	parent(P,Y).
% uncle(mads,magnus).
% uncle(mads, X).

% Avô e Avó
grandfather(X,Y) :-
	man(X),
	father(X,Z),
	parent(Z,Y).
grandmother(X,Y) :-
	woman(X),
	mother(X,Z),
	parent(Z,Y).
% grandfather(helge,franziska).
% grandmother(agnes,X).

% Ancestral (recursivo)
ancestral(X,Y) :- parent(X,Y).
ancestral(X,Y) :- parent(X,Z), ancestral(Z,Y).
% ancestral(agnes,X).