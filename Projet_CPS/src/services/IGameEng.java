package services;

import implem.Level;

public interface IGameEng {
	
	// OBSERVATORS
	
	ILemming[] colony();
	ILemming getLemm(int ln);
	int sizeColony();
	int spawned();
	int spawnSpeed();
	ILevel level();
	ILevel levelInit();
	int tours();
	int nbSauves();
	
	/**
	 * PRE:
	 * 	score() require gameOver()
	 */
	int score();
	
	/**
	 * PRE:
	 * 	obstacle(x, y) require 0 <= x < level().height() ^ 
	 * 		               0 <= y < level().width()
	 */
	boolean obstacle(int x, int y);
	boolean gameOver();
	
	// CONSTRUCTORS
	/**
	 * PRE:
	 * 	init(sc, ss) require sc > 0 ^ ss > 0 	
	 */
	void init(ILevel lvl, int sc, int ss);
	
	// OPERATORS
	/**
	 * PRE:
	 *  addLemming(l) require spawned() < sizeColony()
	 * POST:
	 * 	spawned() == spawned()@pre + 1
	 * 	colony.size() == colony()@pre.size() + 1
	 * 	getLemm(l.getNumber()) == l
	 */
	void addLemming(ILemming l);
	
	/**
	 * PRE:
	 * 	killLemming(ln) require 0 <= ln < sizeColony()
	 * POST:
	 * 	colony().size() == colony()@pre.size() - 1
	 * 	for (int i=0; i < colony()@pre.size(); i++)
	 * 		if (getLemm(i) != null && i != ln)
	 * 			getLemm(i)@pre != null
	 */
	void killLemming(int ln);

	/**
	 * PRE:
	 * 	saveLemming(ln) require 0 <= ln < sizeColony()
	 * POST:
	 * 	nbSauves() == nbSauves()@pre + 1
	 * 	colony().size() == colony()@pre.size() - 1
	 * 	for (int i=0; i < colony()@pre.size(); i++)
	 * 		if (getLemm(i) != null && i != ln)
	 * 			getLemm(i)@pre != null
	 */	
	void saveLemming(int ln);
	
	/**
	 * POST:
	 * 	level() == level()@pre
	 * 	for (Lemming l : colony()@pre)
	 * 		l.step() == getLemm(l.getNumber())
	 */
	void step();
	
	/**
	 * PRE:
	 * 	init(sc, ss) require sc > 0 ^ ss > 0
	 * POST:
	 * 	spawnSpeed() == ss
	 * 	sizeColony() == sc
	 * 	level() == lvl
	 */
	void loadLevel(ILevel lvl, int sc, int ss);
	
	
	
	int nextLemNo();
	int peekNextLemNo();
	
	/**
	*
	*   Service: GameEng
	*   Types: int, bool
	*   Observators:
	*      getLemm: [gameEng] *int -> Lemming
	*	   sizeColony: [gameEng] -> int
	*      spawned: [gameEng] -> int
	*      spawnSpeed: [gameEng] -> int
	*      level: [gameEng] -> Level
	*      tours: [gameEng] -> int
	*	   nbSauves: [gameEng] -> int
	*  Constructors:
	*      init: Level * int * int-> [gameEng]
	*  Operators:
	*      addLemming: [gameEng] * Lemming -> [gameEng]
	*      killLemming: [gameEng] * int -> [gameEng]
	*      saveLemming: [gameEng] * int -> [gameEng]
	*      step: [gameEng] -> [gameEng]
	*	   loadLevel: Level * int * int -> [gameEng]
	*    
	*/
	/**
	 * [invariants]
	 * 	gameOver() min= |colony()| == 0
	 * 	score() min= nbSauves() / tours()
	 * 	0 <= spawned() < sizeColony()
	 * 	0 <= nbSauves() < sizeColony()
	 *  obstacle(G,x,y)) minimisation = Level::nature(x,y)!=EMPTY;
     * [init]
	 * 	sizeColony(init(G,sc,ss))=sc
	 *  spawnSpeed(init(G,sc,ss))=ss
	 *  spawned(init(G,sc,ss))=0
	 *  tours(init(G,sc,ss))=0
	 *  nbSauves(init(G,sc,ss))=0
	 *  
	 * [addLeming]
	 * 	sizeColony(addLeming(G,L,numero))=sizeColony(G)
	 *  spawnSpeed(addLeming(G,L,numero)=spawnSpeed(G)
	 *  spawned(addLeming(G,L,numero))=spawned(G)
	 *  tours(addLeming(G,L,numero)=tours(G)
	 *  nbSauves(addLeming(G,L,sc,ss))=0
	 * 
	 * 
	 * [killLeming]
	 * 	sizeColony(killLeming(G,L,numero))=sizeColony(G)
	 *  spawnSpeed(killLeming(G,L,numero)=spawnSpeed(G)
	 *  spawned(killLeming(G,L,numero))=spawned(G)
	 *  tours(killLeming(G,L,numero)=tours(G)
	 *  nbSauves(killLeming(G,L,sc,ss))=nbSauves(G)
	 * 
	 * [step]
	 * 	sizeColony(step(G))=sizeColony(G)
	 *  spawnSpeed(step(G))=spawnSpeed(G)
	 *  tours(step(G))=tours(G)+1
	 * 
	 * 
	 * [loadLevel]
	 * 	sizeColony(loadLevel(G,L,sc,ss))=sc
	 *  spawnSpeed(loadLevel(G,L,sc,ss))=ss
	 *  spawned(loadLevel(G,L,sc,ss))=0
	 *  tours(loadLevel(G,L,sc,ss))=0
	 *  nbSauves(loadLevel(G,L,sc,ss))=0
	 * 
	 * 
	 */
}
