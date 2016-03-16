package services;

import java.util.List;

public interface IGameEng {
	
	// OBSERVATORS
	
	List<Lemming> colony();
	Lemming getLemm(int ln);
	int sizeColony();
	int spawned();
	int spawnSpeed();
	Level level();
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
	void init(Level lvl, int sc, int ss);
	
	// OPERATORS
	/**
	 * PRE:
	 *  addLemming(l) require spawned() < sizeColony()
	 * POST:
	 * 	spawned() == spawned()@pre + 1
	 * 	colony.size() == colony()@pre.size() + 1
	 * 	getLemm(l.getNumber()) == l
	 */
	void addLemming(Lemming l);
	
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
	void loadLevel(Level lvl, int sc, int ss);
	
	/**
	 * [invariants]
	 * 	gameOver() min= |colony()| == 0
	 * 	score() min= nbSauves() / tours()
	 * 	0 <= spawned() < sizeColony()
	 * 	0 <= nbSauves() < sizeColony()
	 */
}
