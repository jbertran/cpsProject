% Spécification du service Joueur

Service: Joueur

Types: 
	
* int
* bool
* enum Status = {WALK, FALL, BUILD, CLIMB, FLOAT, BOMB, STOP, BASH, MINER}
	
Observators:

* nbTokens: [Joueur] * Status → [Joueur]
* gameEngine: [Joueur] → gameEngine

Constructors:
	
* init → Joueur

Operators:

* spendToken: [Joueur] * int * Status → [Joueur]
	* pre spendToken(J, l, s) require nbTokens(J, s) > 0
	
Observations:

* [Invariants]
* [Init]
	* ∀ s in Status, nbToken(init()) = 10
* [spendToken]
	* s = FLOAT ⇒ Lemming::isFloater(gameEngine(spendToken(J, l, s)), l) = true
	* s = CLIMB ⇒ Lemming::isClimber(gameEngine(spendToken(J, l, s)), l) = true
	* s = BOMB ⇒ Lemming::isBomber(gameEngine(spendToken(J, l, s)), l) = true
	* s != (FLOAT v CLIMB v BOMB) ⇒ Lemming::getStatus(gameEngine::getLemm(gameEngine(spendToken(J, l, s)), l)) = true

