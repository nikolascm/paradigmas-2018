/*
	Nikolas Machado Corrêa - Paradigmas de Programação
    Extra extra01b: Genealogia e linhagem da série The Vampire Diaries 
*/

% Personagens Masculinos
male(mikael).
male(elijah).
male(finn).
male(kol).
male(damon).
male(stefan).
male(niklaus).
male(tyler).
male(george).
male(richard).
male(mason).
male(niklaus).
male(tyler).
male(henrik).
male(john).

% Personagens Femininos
female(rebekah).
female(jenna).
female(mary).
female(rose).
female(katherine).
female(caroline).
female(isobel).
female(vicki).
female(elena).
female(abby).
female(ayana).
female(esther).
female(emily).
female(sheila).
female(bonnie).
female(carol).

% Classificação
vampire(mikael).
vampire(elijah).
vampire(finn).
vampire(kol).
vampire(rebekah).
vampire(jenna).
vampire(mary).
vampire(rose).
vampire(katherine).
vampire(caroline).
vampire(damon).
vampire(stefan).
vampire(isobel).
vampire(vicki).
vampire(elena).
vampire(abby).
vampire(niklaus).
vampire(tyler).

werewolf(george).
werewolf(richard).
werewolf(mason).
werewolf(niklaus).
werewolf(tyler).

witch(ayanna).
witch(esther).
witch(emily).
witch(sheila).
witch(bonnie).

hybrid(X) :- 
	vampire(X),
	werewolf(X).

human(X) :- 
	(male(X);
	female(X)),
	not(vampire(X)),
	not(werewolf(X)),
	not(witch(X)).

% Pais e filhos
% Família Mikaelson
parent(esther,elijah).
parent(esther,finn).
parent(esther,kol).
parent(esther,rebekah).
parent(esther,henrik).
parent(esther,klaus).
parent(mikael,elijah).
parent(mikael,finn).
parent(mikael,kol).
parent(mikael,rebekah).
parent(mikael,henrik).

% Família Lockwood
parent(george,mason).
parent(george,richard).
parent(richard, tyler).
parent(carol, tyler).

% Família Bennett
parent(ayanna,emily).
parent(emily,sheila).
parent(sheila,abby).
parent(abby,bonnie).

% Família Gilbert
parent(isobel,elena).
parent(john,elena).

% Transformações
turned(niklaus,tyler).
turned(niklaus,jenna).
turned(niklaus,mary).
turned(mary,rose).
turned(rose,katherine).
turned(katherine,caroline).
turned(katherine,damon).
turned(katherine,stefan).
turned(damon,isobel).
turned(damon,vicki).
turned(damon,elena).
turned(damon,abby).

% Relações:
father(X,Y) :- 
	man(X), 
	parent(X,Y).
mother(X,Y) :- 
	female(X), 
	parent(X,Y).
son(X,Y) :- 
	man(X), 
	parent(Y,X).
daughter(X,Y) :- 
	female(X), 
	parent(Y,X).
sibling(X,Y) :- 
	parent(Z,X), 
	parent(Z,Y),
	X \= Y.
brother(X,Y) :-
	man(X), 
	sibling(X,Y). 
sister(X,Y) :-
	female(X), 
	sibling(X,Y).
uncle(X,Y) :-
	man(X),
	sibling(X,P),
	parent(P,Y).
aunt(X,Y) :- 
	female(X),
	sibling(X,P),
	parent(P,Y).
grandfather(X,Y) :-
	man(X),
	father(X,Z),
	parent(Z,Y).
grandmother(X,Y) :-
	female(X),
	mother(X,Z),
	parent(Z,Y).

% Ancestral (recursivo)
ancestral(X,Y) :- parent(X,Y).
ancestral(X,Y) :- parent(X,Z), ancestral(Z,Y).

% Ancestralidade por transformações
bloodline(X,Y) :- turned(X,Y).
bloodline(X,Y) :- turned(X,Z), bloodline(Z,Y).
