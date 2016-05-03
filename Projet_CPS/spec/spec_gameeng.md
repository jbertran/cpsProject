% Spécification du service GameEngine

Service: gameEng

Types:
	
* int
* bool
* Lemming
* Level
	
Observators:

* sizeColony: [gameEng] → int
* colony: [gameEng] → Set<Lemming>
* getLemm: [gameEng] *int → Lemming
	* pre getLemm(G, i) require 0 ≤ i < sizeColony(G)
* obstacle: [gameEng] * int * int → bool
	* pre obstacle(G, i, j) require 0 ≤ i < Level::width(level(G)) ^ 0 ≤ j < Level::height(level(G))
* spawned: [gameEng] → int
* spawnSpeed: [gameEng] → int
* level: [gameEng] → Level
* tours: [gameEng] → int
* score: [gameEng] → int
* getNextLemNo: [gameEng] → int
* gameOver: [gameEng] → bool
* nbSauves: [gameEng] → int
* nbVivants: [gameEng] → int
	
Constructors:

* init: int * int → [gameEng]
	* pre init(sc, ss) require sc > 0 ^ ss > 0

Operators:
	
* addLemming: [gameEng] * Lemming → [gameEng]
* killLemming: [gameEng] * int → [gameEng]
	* pre killLemming(G, i) require 0 ≤ i < sizeColony(G)
* saveLemming: [gameEng] * int → [gameEng]
	* pre saveLemming(G, i) require 0 ≤ i < sizeColony(G)
* loadLevel: Level * int * int → [gameEng]
	* pre loadLevel(L, sc, ss) require sc > 0 ^ ss > 0
* step: [gameEng] → [gameEng]
* annihilate: [gameEng] → [gameEng]

Observations:

* [invariants]
	* gameOver() min= |colony(G)| == 0
	* score() min= nbSauves(G) / tours(G)
	* 0 ≤ spawned(G) < sizeColony(G)
	* 0 ≤ nbSauves(G) < sizeColony(G)
	* obstacle(G,x,y)) minimisation = Level::nature((level(G),x,y)!=EMPTY;
* [init]
	* sizeColony(init(G,sc,ss))=sc
	* spawnSpeed(init(G,sc,ss))=ss
	* spawned(init(G,sc,ss))=0
	* tours(init(G,sc,ss))=1
	* nbSauves(init(G,sc,ss))=0
* [addLeming]
	* sizeColony(addLeming(G,L,numero))=sizeColony(G)
	* spawnSpeed(addLeming(G,L,numero)=spawnSpeed(G)
	* spawned(addLeming(G,L,numero))=spawned(G)
	* tours(addLeming(G,L,numero)=tours(G)
	* nbSauves(addLeming(G,L,sc,ss))=0
* [killLeming]
	* sizeColony(killLeming(G,L,numero))=sizeColony(G)
	* spawnSpeed(killLeming(G,L,numero)=spawnSpeed(G)
	* spawned(killLeming(G,L,numero))=spawned(G)
	* tours(killLeming(G,L,numero)=tours(G)
	* nbSauves(killLeming(G,L,sc,ss))=nbSauves(G)
* [step]
	* sizeColony(step(G))=sizeColony(G)
	* spawnSpeed(step(G))=spawnSpeed(G)
	* tours(step(G))=tours(G)+1
* [loadLevel]
	* sizeColony(loadLevel(G,L,sc,ss))=sc
	* spawnSpeed(loadLevel(G,L,sc,ss))=ss
	* spawned(loadLevel(G,L,sc,ss))=0
	* tours(loadLevel(G,L,sc,ss))=0
	* nbSauves(loadLevel(G,L,sc,ss))=0
* [annihilate]
	* ∀ i 0 ≤ i < sizeColony(G):
		* isBomber(getLemming(annihilate(G)), i) = true
	* spawned(G) = sizeCOlony(G)
