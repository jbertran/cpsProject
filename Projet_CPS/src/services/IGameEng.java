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
	 * 	score(G) require gameOver(G)
	 */
	int score();
	
	/**
	 * PRE:
	 * 	obstacle(G, x, y) require 0 <= x < Level::height(level(G)) ^
	 * 								0 <= y < Level::width(level(G))
	 */
	boolean obstacle(int x, int y);
	boolean gameOver();
	
	// CONSTRUCTORS
	/**
	 * PRE:
	 * 	init(L, sc, ss) require sc > 0 ^ ss > 0 	
	 */
	void init(Level lvl, int sc, int ss);
	
	// OPERATORS
	/**
	 * PRE:
	 *  addLemming(G, l) require spawned() < sizeColony(G)
	 * POST:
	 * 	spawned() == spawned()@pre + 1
	 * 	colony.size() == colony()@pre.size() + 1
	 * 	getLemm(l.getNumber()) == l
	 */
	void addLemming(Lemming l);
	
	/**
	 * PRE:
	 * 	killLemming(G, ln) require 0 <= ln < sizeColony(G)
	 * POST:
	 * 	colony().size() == colony()@pre.size() - 1
	 * 	for (int i=0; i < colony()@pre.size(); i++)
	 * 		if (getLemm(i) != null && i != ln)
	 * 			getLemm(i)@pre != null
	 */
	void killLemming(int ln);

	/**
	 * PRE:
	 * 	saveLemming(G, ln) require 0 <= ln < sizeColony(G)
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
	 * 	init(L, sc, ss) require sc > 0 ^ ss > 0
	 * POST:
	 * 	spawnSpeed() == ss
	 * 	sizeColony() == sc
	 * 	level() == lvl
	 */
	void loadLevel(Level lvl, int sc, int ss);
	
	/**
	 * [invariants]
	 * 	gameOver(G) min= |colony(G)| == 0
	 * 	score(G) min= nbSauves(G) / tours(G)
	 * 	0 <= spawned(G) < sizeColony(G)
	 * 	0 <= nbSauves(G) < sizeColony(G)
	 */
}
