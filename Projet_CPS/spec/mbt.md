#CPS - Projet Lemmings

## Model-based testing

1. **init -- Level, GameEngine, Service**
	1. Test *positif*
		* *Conditions initiales*: aucune
		* *Opération*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
		* *Oracle*: **pas** d'exception levée
	2. Test *négatif*
		* *Conditions initiales*: aucune
		* *Opération*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, -1, 4)
			* J = Joueur::init(G)
		* *Oracle*: Exception levée
	3. Test en termes *d'automates*
		* *Conditions initiales*: aucune
		* *Opération*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
			* GameEngine::step(GameEngine::step(GameEngine::step(GameEngine::step(G))))
		* *Oracle*:
			* height(L)==15
			* width(L)==10
			* spawnSpeed(G)==4
			* sizeColony(G)==8
2. **setNature -- Level**
	1. Test *positif*
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
		* *Opération*:
			* L = Level::setNature(L, 8, 8, Nature.DIRT);
		* *Oracle*: **pas** d'exception levée
	2. Test *négatif*
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
		* *Opération*:
			* L01 = Level::setNature(L, 12, 13, Nature.DIRT);
		* *Oracle*: Exception levée
	3. Test en termes *d'automates*
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
		* *Opération*:
			* L01 =	Level::setNature(L, 5, 5, Nature.DIRT);
		* *Oracle*: nature(5, 5)==Nature.DIRT
3. **goPlay -- Level**
	1. Test *positif*
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
		* *Opération*:
			* L01 =	getLevel().goPlay(2,2,4,8);
		* *Oracle*: **pas** d'exception levée
	2. Test *négatif*
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
		* *Opération*:
			* L01 =	getLevel().goPlay(2,2,4,9)
		* *Oracle*: Exception levée
	3. Test en termes *d'automates*
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
		* *Opération*:
			* L01 =	getLevel().goPlay(2, 2, 4, 8);
		* *Oracle*:
			* editing(L01)==false
			* entree_x(L01)==2
			* entree_y(L01)==2
			* sortie_x(L01)==4
			* sortie_y(L01)==8
4. **Step -- GameEngine**
	1. Test *positif*:
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
		* *Opérations*:
			* step(G)
		* *Oracle*: **pas** d'exception levée
	2. Test *multiple*:
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
		* *Opérations*:
			* G01 = step(step(step(step(step(step(step(step(G))))))))
		* *Oracle*: **pas** d'exception levée
5. **SpendToken -- Joueur**
	1. Test *positif*:
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
			* L01 for i = 0 to 20 setNature(i, 4, METAL)
			* Level::goPlay(L01, 2, 2, 18, 3)
			* G01 = GameEngine::step(GameEngine::step(GameEngine::step(G)))
		* *Opérations*:
			* spendToken(J, 0, STOP)
		* *Oracle*: pas d'exception levée
	2. Test *négatif*:
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
			* L01 for i = 0 to 20 setNature(i, 4, METAL)
			* Level::goPlay(L01, 2, 2, 18, 3)
			* G01 = GameEngine::step(GameEngine::step(GameEngine::step(G)))
		* *Opérations*:
			* spendToken(J, 5, STOP)
		* *Oracle*: exception levée
	3. Test en terms *d'automates*:
		* *Conditions initiales*:
			* L = Level::init(15, 10)
			* G = GameEngine::init(L, 8, 4)
			* J = Joueur::init(G)
			* L01 for i = 0 to 20 setNature(i, 4, METAL)
			* Level::goPlay(L01, 2, 2, 18, 3)
			* G01 = GameEngine::step(GameEngine::step(GameEngine::step(G)))
		* *Opérations*:
			* J01 = spendToken(J, 5, STOP)
			* G02 = GameEngine::step(gameEngine(J01))
		* *Oracle*: 
			* Lemming::getX(GameEngine::getLemm(G02, 0)) = Lemming::getX(GameEngine::getLemm(G01, 0))
			* Lemming::getY(GameEngine::getLemm(G02, 0)) = Lemming::getY(GameEngine::getLemm(G01, 0))
