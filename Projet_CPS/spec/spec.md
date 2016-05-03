# Level

* __Service__: Level
* __Types__: int, bool, enum Nature{DIRT, METAL, EMPTY}
* __Observators__:
	* height: [Level] → int
	* width: [Level] → int
	* editing: [Level] → bool
	* nature: [Level] * int * int → Nature
* __Constructors__:
	* init: → [Level]
* __Operators__:
	* setNature: [Level] *int * int * Nature → [Level]
		* pre setNature(L, x, y, n) require 0 ≤ x ≤ width(L) ∧ 0 ≤ y ≤ height(L) ∧ editing(L)
	* goPlay: [Level] → [Level]
	* remove: [Level] * int * int → [Level]
		* pre remove(L, x, y) require 0 ≤ x ≤ width(L) ∧ 0 ≤ y ≤ height(L) ∧ nature(L, x, y) = DIRT ∧ not(editing(L))
	* build: [Level] * int * int → [Level]
		* pre build(L, x, y) require 0 ≤ x ≤ width(L) ∧ 0 ≤ y ≤ height(L) ∧ nature(L, x, y) = EMPTY ∧ not(editing(L))
* __Observations__:
	* [invariants]
		* height(L) > 0; width(L) > 0
	* [init]
		* width(init(h, w)) = w;
		* height(init(h, w)) = h;
		* editing(init(h, w)) = true;
		* entree_x(init(h,w))= null;
		* entree_y(init(h,w))= null;
		* sortie_x(init(h,w))= null;
		* sortie_y(init(h,w))= null;
		* nature(L,x,y)= DIRT;
	* [setNature]
		* width(setNature(L,x,y))=width(L)
		* height(setNature(L,x,y))=height(L)
		* editing(setNature(L,x,y))=true;
		* entree_x(setNature(L,x,y))= null;
		* entree_y(setNature(L,x,y))= null;
		* sortie_x(setNature(L,x,y))= null;
		* sortie_y(setNature(L,x,y))= null;
		* nature(setNature(L, x, y, n), x, y) = n;
		* nature(setNature(L, x, y, n), i, j) = nature(L, i, j) forall (i, j) != (x, y)
	 * [goPlay]
		 * width(goPlay(L))=width(L)
		 * height(goPlay(L))=height(L)
		 * editing(goPlay(L)) = false;
		 * entree_x(goPlay(L, , xe, ye, xs, ys)) == xe;
		 * entree_y(goPlay(L, , xe, ye, xs, ys)) == ye;
		 * sortie_x(goPlay(L, , xe, ye, xs, ys)) == xs;
		 * sortie_y(goPlay(L, , xe, ye, xs, ys)) == ys;
		 * for i in (0..height(L)), nature(goPlay(L, xe, ye, xs, ys), i, 0) == METAL ^ nature(goPlay(L, xe, ye, xs, ys), i, width(L)-1) == METAL
		 * for j in (0..width(L)), nature(goPlay(L, xe, ye, xs, ys), 0, j) == METAL ^ nature(goPlay(L, xe, ye, xs, ys), height(L)-1, j) == METAL
	* [remove]
		* width(remove(L,x,y))=width(L)
		* height(remove(L,x,y))=height(L)
		* editing(remove(L,x,y)) = false;
		* entree_x(remove(L,x,y)) = entree_x(L)
		* entree_y(remove(L,x,y)) = entree_y(L)
		* sortie_x(remove(L,x,y)) = sortie_x(L)
		* sortie_y(remove(L,x,y)) = sortie_y(L)
		* nature(remove(L, x, y), x, y) == EMPTY
		* nature(remove(L, x, y), i, j) == nature(L, i, j) forall (i, j) != (x, y)
	* [build]
		* width(build(L,x,y))=width(L)
		* height(build(L,x,y))=height(L)
		* editing(build(L,x,y)) = false;
		* entree_x(build(L,x,y)) = entree_x(L)
		* entree_y(build(L,x,y)) = entree_y(L)
		* sortie_x(build(L,x,y)) = sortie_x(L)
		* sortie_y(build(L,x,y)) = sortie_y(L)
		* nature(build(L, x, y), x, y) == DIRT
		* nature(build(L, x, y), i, j) == nature(L, i, j) forall (i, j) != (x, y)

# GameEng

* __Service__: GameEng
* __Types__: int, bool, enum Nature {DIRT, METAL, EMPTY}, Lemming, Set<Lemming>
* __Observators__:
	* colony : [GameEng] → Set<Lemming>
	* nextLemNo : [GameEng] → int
	* peekNextLemNo : [GameEng] → int
	* getLemm : [GameEng] * int → Lemming
	* sizeColony : [GameEng] → int
	* spawned : [GameEng] → int
	* spawnSpeed : [GameEng] → int
	* levelInit : [GameEng] → Level
	* level : [GameEng] → Level
	* tours : [GameEng] → int
	* nbSauves : [GameEng] → int
	* score : [GameEng] → score
		* pre score(G) require gameOver(G)
	* gameOver : [GameEng] → bool
	* obstacle : [GameEng] * int * int → bool
		* pre obstacle(G, x, y) require 0 ≤ x < Level::height(level(G)) ∧ 0 ≤ y < Level::height(level(G))
* __Constructors__: 
	* init : Level, int, int → [GameEng]
		* pre init(lvl, sc, ss) require sc > 0 ∧ ss > 0
* __Operators__:
	* addLemming : [GameEng] * Lemming → [GameEng]
		* pre addLemming(G, l) require spawned(G) < sizeColony(G)
	* killLemming: [GameEng] * int → [GameEng]
		* pre killLemming(G, ln) require 0 ≤ ln < sizeColony(G)
	* saveLemming: [GameEng] * int → [GameEng]
		* pre saveLemming(G, ln) require 0 ≤ ln < sizeColony(G)
	* step: [GameEng] → [GameEng]
	* loadLevel: [GameEng] * Level * int * int → [GameEng]
		* pre loadLevel(G, lvl, sc, ss) require sc > 0 ∧ ss > 0
* __Observations__:
	* [invariants]
		* 	gameOver() min= |colony()| == 0
		* 	score() min= nbSauves() / tours()
		* 	0 ≤ spawned() < sizeColony()
		* 	0 ≤ nbSauves() < sizeColony()
		*  obstacle(G,x,y)) min = Level::nature(x,y)!=EMPTY;
	* [init]
	 	*  sizeColony(init(L,sc,ss))=sc
	 	*  spawnSpeed(init(L,sc,ss))=ss
	 	*  spawned(init(L,sc,ss))=0
	 	*  tours(init(L,sc,ss))=0
	 	*  nbSauves(init(L,sc,ss))=0
		*  level(init(L,sc,ss)) = L
		*  levelInit(init(L,sc,ss)) = L
	* [addLeming]
	 	* 	sizeColony(addLeming(G,L,numero))=sizeColony(G)
	 	*  spawnSpeed(addLeming(G,L,numero)=spawnSpeed(G)
	 	*  spawned(addLeming(G,L,numero))=spawned(G)
	 	*  tours(addLeming(G,L,numero)=tours(G)
	 	*  nbSauves(addLeming(G,L,sc,ss))=0
	 * [killLeming]
	 	* 	sizeColony(killLeming(G,L,numero))=sizeColony(G)
	 	*  spawnSpeed(killLeming(G,L,numero)=spawnSpeed(G)
	 	*  spawned(killLeming(G,L,numero))=spawned(G)
	 	*  tours(killLeming(G,L,numero)=tours(G)
	 	*  nbSauves(killLeming(G,L,sc,ss))=nbSauves(G)
	 * [step]
	 	* 	sizeColony(step(G))=sizeColony(G)
	 	*  spawnSpeed(step(G))=spawnSpeed(G)
	 	*  tours(step(G))=tours(G)+1
	 * [loadLevel]
	 	* 	sizeColony(loadLevel(G,L,sc,ss))=sc
	 	*  spawnSpeed(loadLevel(G,L,sc,ss))=ss
	 	*  spawned(loadLevel(G,L,sc,ss))=0
	 	*  tours(loadLevel(G,L,sc,ss))=0
	 	*  nbSauves(loadLevel(G,L,sc,ss))=0

# Lemming

* __Service__: Lemming
* __Types__: int, bool, enum Status{WALK, FALL, BUILD, FLOAT, BOMB, STOP, BASH},enum Direction{DROITIER,GAUCHER}
* __Observators__:
	*      getX: [Lemming] → int
  	*      getY: [Lemming] → int
  	*	   getNumber: [Lemming] →int
  	*      getDir(): [Lemming] → Direction
  	*      getStatus(): [Lemming] → Status
  	*      timeFalling(): [Lemming] → int
  	*      gameEngine(): [Lemming] → [gameEngine];
* __Constructors__:
  	*      init: [gameEngine] → [Lemming]
* __Operators__:
  	*      changeDir: [Lemming] → [Lemming]
  	*      setStatus: [Lemming] 	* Status → [Lemming]
  	*      step: [Lemming] → [Lemming]
* __Observations__:
	* [invariants]
		* getX(L) >= 0
		* getY(L) >= 0
		* 0 <= getNumber(L) < GameEng::sizeColony(gameEngine(L))
	* [init]
	  	*  getX(init(G))=gameEngine::entree_X()
	  	*  getY(init(G))=gameEngine::entree_Y()
	  	*  getDir(init(G))=DROITIER;
	  	*  getStatus(init(G))=TOMBEUR;
	  	*  timeFalling(init(G))=0;
		*  GameEng::nextLemNo(gameEngine(init(G))) = GameEng::nextLemNo(G) + 1
		*  getNumber(init(G)) = GameEng::nextLemNo(G)
	* [changeDir]
	  	* 	getX(changeDir(Le))=getX(Le);
	  	*  getY(changeDir(Le))=getY(Le);
	  	*   if(getDir(Le)==DROITIER)then getDir(changeDir(Le))=GAUCHER else getDir(changeDir(Le))=DROITIER;
	  	*  getStatus(changeDir(Le))=getStatus(Le);
	  	*  timeFalling(changeDir(Le))=timeFalling(Le);
	* [setStatus]
	  	* 	getX(setStatus(Le,s))=getX(Le);
	  	*  getY(setStatus(Le,s))=getY(Le);
	  	*  getDir(setStatus(Le,s))= getDir(Le);
	  	*  getStatus(setStatus(Le,s))=s;
	  	*  timeFalling(setStatus(Le,s))=timeFalling(Le);
	* [step]
		* getStatus(L) = Marcheur ⇒ 
			* Level::Nature(GameEng::level(gameEngine(L)), getX(L), getY(L)+1) = EMPTY ⇒
				* getStatus(step(L)) = Tombeur
				* getX(L) = getX(step(L))
				* getY(L) = getY(step(L))
			* Level::Nature(GameEng::level(gameEngine(L)), getX(L), getY(L)+1) != EMPTY ⇒
				* getDir(L) = Droite ⇒
					* Level::Nature(GameEng::level(gameEngine(L)), getX(L)+1, getY(L)) != EMPTY v Level::Nature(GameEng::level(gameEngine(L)), getX(L)+1, getY(L)-1) != EMPTY ⇒ getDir(step(L)) = changeDir(L)
					* Level::Nature(GameEng::level(gameEngine(L)), getX(L)+1, getY(L)) = EMPTY ∧ Level::Nature(GameEng::level(gameEngine(L)), getX(L)+1, getY(L)-1) = EMPTY ⇒ getX(step(L) = getX(L)+1
				* idem pour lemming gaucher
		* getStatus(L) = Tombeur ⇒
			* Level::Nature(GameEng::level(gameEngine(L)), getX(L), getY(L)+1) != EMPTY ⇒
				* timeFalling(L) < 8 ⇒ 
					* getStatus(step(L)) = Marcheur
					* timeFalling(step(L)) = 0
				* timeFalling(L) ≥ 8 ⇒ gameEngine(step(L)) = GameEng::killLemming(getNumber(L))
			* Level::Nature(GameEng::level(gameEngine(L)), getX(L), getY(L)+1) = EMPTY ⇒
				* getX(step(L)) = getX(L)
				* getY(step(L)) = getY(L) + 1
				
# Joueur

* __Service__: Joueur
* __Types__: int, bool, enum Status{WALK, FALL, BUILD, FLOAT, BOMB, STOP, BASH}
* __Observators__: 
	* nbTokens: [Joueur] * Status-> Joueur
	* gameEngine: [Joueur] -> GameEng
* __Constructors__:
	* init: GameEng -> [Joueur]
* __Operators__:
	* spendToken: [Joueur] *int * Status -> [Joueur]
		* pre spendToken(lemm, s) require nbTokens(s)>0
	* reset: [Level] -> [Level]
* __Observations__:
	* [invariants]
		* \forall s in Status: nbTokens(J, s) >= 0
		* gameEngine(reset(J)) min= GameEng::loadlevel(GameEng::levelInit(gameEngine(J)))
	* [init]
		* nbTokens(init(G), s) = 10 \forall s in Status
		* gameEngine(init(G)) = G
	* [spendToken]
		* nbTokens(spendToken(J, s), s) = nbTokens(J, s) - 1
		* nbTokens(spendToken(J, s), st) = nbTokens(J, st) \forall st != s in Status
	* [reset]
		* nbTokens(init(G), s) = 10 \forall s in Status

```sh
$ pandoc spec.md -s -o spec.html
```
