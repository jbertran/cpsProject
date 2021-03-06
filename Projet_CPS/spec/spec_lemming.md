Service: lemming

Types:
	
* int
* bool
* enum Status = {WALK, FALL, BUILD, CLIMB, FLOAT, BOMB, STOP, BASH, MINER}
* enum Nature = {DIRT, METAL, EMPTY}
* enum Direction = {DROITE, GAUCHE}
* GameEngine
	
Observators:

* getX: [Lemming] → int
* getY: [Lemming] → int
* getNumber: [Lemming] →int
* getDir: [Lemming] → Direction
* getStatus: [Lemming] → Status
* isMiningDown: [Lemming] → int
* isBomber: [Lemming] → bool
* isFloater: [Lemming] → bool
* isClimber: [Lemming] → bool
* tilesBuilt: [Lemming] → int
* timeWaiting: [Lemming] → int
* timeFalling: [Lemming] → int
* timeBashing: [Lemming] → int
* timeExploding: [Lemming] → int
* gameEngine: [Lemming] → [gameEngine];

Constructors:

* init: GameEngine → Lemming
	
Operators:
	
* changeDir: [Lemming] → [Lemming]
* setStatus: [Lemming] * Status → [Lemming]
* setBomber: [Lemming] → [Lemming]
* setClimber: [Lemming] → [Lemming]
* setFloater: [Lemming] * bool → [Lemming]
* step: [Lemming] → [Lemming]

Observations:

* [invariants]
	* getNumber(L) > 0
* [init]
	* getX(init(Le,G)) = gameEngine::entree_X()
	* getY(init(Le,G)) = gameEngine::entree_Y()
	* getDir(init(Le,G)) = DROIT
	* getStatus(init(Le,G)) = FALL
	* isMiningDown(init(Le,G)) = true
	* isBomber(init(Le,G)) = false
	* isFloater(init(Le,G)) = false
	* isClimber(init(Le,G)) = false
	* timeWaiting(init(Le,G)) = -1
	* tilesBuilt(init(Le,G)) = 0
* [changeDir]
	* getX(changeDir(Le)) = getX(Le)
	* getY(changeDir(Le)) = getY(Le)
	* if(getDir(Le)==DROITE)then getDir(changeDir(Le)) = GAUCHE else getDir(changeDir(Le)) = DROITE
	* getStatus(changeDir(Le)) = getStatus(Le)
	* timeFalling(changeDir(Le)) = timeFalling(Le)
	* timeWaiting(changeDir(Le)) = timeWaiting(Le)
	* tilesBuilt(changeDir(Le)) = tilesBuilt(Le)
* [setStatus]
	* getX(setStatus(Le,s))=getX(Le)
	* getY(setStatus(Le,s))=getY(Le)
	* getDir(setStatus(Le,s))= getDir(Le)
	* getStatus(setStatus(Le,s))=s
	* timeFalling(setStatus(Le,s))=timeFalling(Le)
	* timeWaiting(changeDir(Le)) = timeWaiting(Le)
	* tilesBuilt(changeDir(Le)) = tilesBuilt(Le)
* [setBomber]
	* isBomber(setBomber(Le)) = true
	* getX(setBomber(Le)) = getX(Le)
	* getY(setBomber(Le)) = getY(Le)
	* getDir(setBomber(Le)) = getDir(Le)
	* getStatus(setBomber(Le)) = getStatus(Le)
	* timeFalling(setBomber(Le)) = timeFalling(Le) 
	* timeWaiting(changeDir(Le)) = timeWaiting(Le)
	* tilesBuilt(changeDir(Le)) = tilesBuilt(Le)
* [setFloater]
	* isFloater(setFloater(Le, b)) = b
	* getX(setFloater(Le, b)) = getX(Le)
	* getY(setFloater(Le, b)) = getY(Le)
	* getDir(setFloater(Le, b)) = getDir(Le)
	* getStatus(setFloater(Le, b)) = getStatus(Le)
	* timeFalling(setFloater(Le, b)) = timeFalling(Le) 
* [setClimber]
	* isClimber(setClimber(Le)) = true
	* getX(setClimber(Le)) = getX(Le)
	* getY(setClimber(Le)) = getY(Le)
	* getDir(setClimber(Le)) = getDir(Le)
	* getStatus(setClimber(Le)) = getStatus(Le)
	* timeFalling(setClimber(Le)) = timeFalling(Le) 
	* timeWaiting(changeDir(Le)) = timeWaiting(Le)
	* tilesBuilt(changeDir(Le)) = tilesBuilt(Le)
* [step]
	* getStatus(L) == WALK ⇒
		* isClimber(L) ⇒
			* (getDir(L) == DROITE ^
			* GameEngine::obstacle(gameEngine(L), getX(L) + 1, getY(L)) ^ 
			* GameEngine::obstacle(gameEngine(L), getX(L) + 1, getY(L) - 1) ^
			* GameEngine::obstacle(gameEngine(L), getX(L), getY(L) - 2))
			* OR
			* (getDir(L) == GAUCHE ^
			* GameEngine::obstacle(gameEngine(L), getX(L) - 1, getY(L)) ^ 
			* GameEngine::obstacle(gameEngine(L), getX(L) - 1, getY(L) - 1) ^
			* GameEngine::obstacle(gameEngine(L), getX(L), getY(L) - 2)) ⇒
				* getX(step(L)) = getX(L)
				* getY(step(L)) = getY(L) - 1
		* !GameEngine::obstacle(gameEngine(L), getX(L), getY(L) + 1) AND !isClimber(L) ⇒
			* getStatus(step(L)) = FALL
			* getX(step(L)) = getX(L)
			* getY(step(L)) = getY(L)
		* getDir(L) == DROITE ⇒
			* !GameEngine::obstacle(gameEngine(L), getX(L) + 1, getY(L)) ^
			* !GameEngine::obstacle(gameEngine(L), getX(L) + 1, getY(L) - 1) ⇒
				* getX(step(L)) == getX(L) + 1 
				* getY(step(L)) == getY(L);
			* !GameEngine::obstacle(gameEngine(L), getX(L) + 1, getY(L) - 1) ^
			* !GameEngine::obstacle(gameEngine(L), getX(L) + 1, getY(L) - 2) ⇒
				* getX(step(L)) = getX(L) + 1
				* getY(step(L)) = getY(L) - 1
			* ELSE 
				* getDir(step(L)) = GAUCHE
				* getX(step(L)) = getX(L)
				* getY(step(L)) = getY(L)
		* getDir(L) == GAUCHE ⇒
			* !GameEngine::obstacle(gameEngine(L), getX(L) - 1, getY(L)) ^
			* !GameEngine::obstacle(gameEngine(L), getX(L) - 1, getY(L) - 1) ⇒
				* getX(step(L)) == getX(L) - 1 
				* getY(step(L)) == getY(L);
			* !GameEngine::obstacle(gameEngine(L), getX(L) - 1, getY(L) - 1) ^
			* !GameEngine::obstacle(gameEngine(L), getX(L) - 1, getY(L) - 2) ⇒
				* getX(step(L)) = getX(L) - 1
				* getY(step(L)) = getY(L) - 1
			* ELSE 
				* getDir(step(L)) = GAUCHE
				* getX(step(L)) = getX(L)
				* getY(step(L)) = getY(L)
	* getStatus(L) == FALL ⇒
		* isFloater(L) ⇒ 
			* GameEngine::obstacle(gameEngine(L), getX(L), getY(L) + 1) ⇒
				* getStatus(step(L)) = WALK
				* timeFalling(step(L)) = 0
				* isFloater(step(L)) = false 
				* getX(step(L)) == getX(L) 
				* getY(step(L)) == getY(L)
			* timeFalling(L) % 2 == 0 ⇒
				* getX(step(L)) == getX(L)
				* getY(step(L)) == getY(L) + 1
			* timeFalling(step(L)) = timeFalling(L) + 1
		* ELSE
			* GameEngine::obstacle(gameEngine(L), getX(L), getY(L) + 1) ⇒
				* timeFalling() < 8 ⇒
					* getStatus(step(L)) = WALK
					* timeFalling(step(L)) = 0 
					* getX(step(L)) == getX(L) 
					* getY(step(L)) == getY(L)
				* ELSE
					  * GameEngine::nbVivants(gameEngine(step(L))) = GameEngine::nbVivants(gameEngine(L)) - 1
			* ELSE 
					  *	getX(step(L)) == getX(L) getY() == getY(L) + 1
                  * timeFalling(step(L)) = timeFalling(L) + 1
	* getStatus(L) == BUILD ⇒	
			* tilesBuilt(L) ≥ 12 ⇒
				* tilesBuiltstep(L) = 0
				* timeWaitingstep(L) = -1
				* getStatusstep(L) = WALK
			* ELSE getDir(L) == DROITE ⇒
				* timeWaiting(L) == O ⇒
					* timeWaitingstep(L) = -1
					* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) + 1, getY(L)) = DIRT
					* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) + 2, getY(L) - 1) = DIRT
					* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) + 2, getY(L)) = DIRT
					* !GameEngine::obstacle(gameEngine(L), getX(L) + 2, getY(L) - 2) ^
					* !GameEngine::obstacle(gameEngine(L), getX(L) + 2, getY(L) - 3) ⇒
						* getX(step(L)) = getX(L) + 2
						* getY(step(L)) = getY(L) - 2
						* tilesBuild(step(L)) = tilesBuilt(L) + 3
					* timeWaiting > 0 ⇒
						* timeWaiting(step(L)) = timeWaiting(L) + 1
					* ELSE
						* !GameEngine::obstacle(gameEngine(L), getX(L) + 1, getY(L)) ^
					    * !GameEngine::obstacle(gameEngine(L), getX(L) + 2, getY(L)) ^
						* !GameEngine::obstacle(gameEngine(L), getX(L) + 2, getY(L) - 1) ⇒ 
							* timeWaiting(step(L)) = 3
						* ELSE
							* tilesBuilt(step(L)) = 0
							* timeWaiting(step(L)) = -1
							* getStatus(step(L)) = WALK
			* ELSE getDir(L) == GAUCHE ⇒
				* timeWaiting(L) == O ⇒
					* timeWaitingstep(L) = -1
						* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) - 1, getY(L)) = DIRT
						* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) - 2, getY(L) - 1) = DIRT
						* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) - 2, getY(L)) = DIRT
					* !GameEngine::obstacle(gameEngine(L), getX(L) - 2, getY(L) - 2) ^
					* !GameEngine::obstacle(gameEngine(L), getX(L) - 2, getY(L) - 3) ⇒
						* getX(step(L)) = getX(L) - 2
						* getY(step(L)) = getY(L) - 2
						* tilesBuild(step(L)) = tilesBuilt(L) + 3
					* timeWaiting > 0 ⇒
						* timeWaiting(step(L)) = timeWaiting(L) + 1
					* ELSE
						* !GameEngine::obstacle(gameEngine(L), getX(L) - 1, getY(L)) ^
						* !GameEngine::obstacle(gameEngine(L), getX(L) - 2, getY(L)) ^
						* !GameEngine::obstacle(gameEngine(L), getX(L) - 2, getY(L) - 1) ⇒ 
							* timeWaiting(step(L)) = 3
						* ELSE
							* tilesBuilt(step(L)) = 0
							* timeWaiting(step(L)) = -1
							* getStatus(step(L)) = WALK
	* getStatus(L) == STOP ⇒
		* getX(step(L)) = getX(L)
		* getY(step(L)) = getY(L)
	* getStatus(L) == MINE ⇒
		* !isMiningDown(L) ⇒
			* Level::nature(GameEngine::level(gameEngine(L)), getX(L), getY(L) + 1) != METAL ⇒
				* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L), getY(L) + 1) = EMPTY
			* Level::nature(GameEngine::level(gameEngine(L)), getX(L), getY(L) + 2) != METAL ⇒
				* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L), getY(L) + 2) = EMPTY
		* ELSE
			* getDir(L) == DROITE ⇒
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) + 1, getY(L)) == METAL v 
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) + 1, getY(L) - 1) = METAL ⇒
					* getStatus(step(L)) = WALK
				* ELSE
					* isMiningDown(step(L)) = !isMiningDown(L)
					* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) + 1, getY(L)) == EMPTY
					* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) + 1, getY(L) - 1) = EMPTY
					* getX(step(L)) = getX(L) + 1
			* getDir(L) == GAUCHE ⇒
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) - 1, getY(L)) == METAL v 
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) - 1, getY(L) - 1) = METAL ⇒
					* getStatus(step(L)) = WALK
				* ELSE
					* isMiningDown(step(L)) = !isMiningDown(L)
					* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) - 1, getY(L)) == EMPTY
					* Level::nature(GameEngine::level(gameEngine(step(L))), getX(L) - 1, getY(L) - 1) = EMPTY
					* getX(step(L)) = getX(L) - 1
	* getStatus(L) == BASH ⇒
		* timeBashing(step(L)) = timeBashing(L) + 1
		* timeBashing(L) < 19 ⇒
			* getDir(L) == DROITE ⇒
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) + 1, getY(L)) != METAL ^
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) + 1, getY(L) - 1) != METAL ^
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) + 1, getY(L) - 2) != METAL ⇒
					* Level::nature(GameEngine::level(gameEngine(L)), getX(L) + 1, getY(L)) = EMPTY
					* Level::nature(GameEngine::level(gameEngine(L)), getX(L) + 1, getY(L) - 1) = EMPTY
					* Level::nature(GameEngine::level(gameEngine(L)), getX(L) + 1, getY(L) - 2) = EMPTY
					* getX(step(L)) = getX(L) + 1
				* ELSE
					* getStatus(step(L)) = WALK
			* getDir(L) == GAUCHE ⇒
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) - 1, getY(L)) != METAL ^
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) - 1, getY(L) - 1) != METAL ^
				* Level::nature(GameEngine::level(gameEngine(L)), getX(L) - 1, getY(L) - 2) != METAL ⇒
					* Level::nature(GameEngine::level(gameEngine(L)), getX(L) - 1, getY(L)) = EMPTY
					* Level::nature(GameEngine::level(gameEngine(L)), getX(L) - 1, getY(L) - 1) = EMPTY
					* Level::nature(GameEngine::level(gameEngine(L)), getX(L) - 1, getY(L) - 2) = EMPTY
					* getX(step(L)) = getX(L) - 1
				* ELSE
					* getStatus(step(L)) = WALK						
		* ELSE GameEngine::obstacle(gameEngine(L), getX(L), getY(L) + 1) ⇒
			* getStatus(step(L)) = FALL
		* ELSE
			* timeBashing(L) = 0
			* getStatus(step(L)) = WALK
	* isBomber(L) ⇒
		* timeExploding(step(L)) = timeExploding(L) + 1
		* timeExploding(L) > 4 ⇒
			* ∀ i getX(L) - 2 ≤ i < getX(L) + 3
			* ∀ j getY(L) - 2 ≤ j < getY(L) + 2
				* 0 ≤ i < Level::width(GameEngine::level(gameEngine(L))) ^ 
				* 0 ≤ j < Level::height(GameEngine::level(gameEngine(L))) ⇒
					* Level::nature(GameEngine::level(gameEngine(L)), i, j) == DIRT ⇒
						* Level::nature(GameEngine::level(gameEngine(step(L))), i, j) = EMPTY
					* ELSE Level::nature(GameEngine::level(gameEngine(step(L))), i, j) = Level::nature(GameEngine::level(gameEngine(L)), i, j) 
