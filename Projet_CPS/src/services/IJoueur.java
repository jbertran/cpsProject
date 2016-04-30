package services;

public interface IJoueur {
	
	// OBSERVATORS
	int nbTokens(Status s);
	IGameEng gameEngine();
	
	// CONSTRUCTORS
	void init(IGameEng gE);
	
	// OPERATORS
	/** 
	 * PRE : nbTokens(J,s)>0
	 * POST:
	 *  getStatus(getLemm(gameEngine(spendToken(J,L,s)))) = s
	 *  
	 */
	void spendToken(int lemm, Status s);
	void reset();
	
	
	
	/**
	*
	*   Service: Joueur
	*   Types: int, bool, enum Status{WALK, FALL, BUILD, FLOAT, BOMB, STOP, BASH}
	*   Observators:
	*      nbTokens: [Joueur] * Status-> Joueur
	*  Constructors:
	*      init: -> [Joueur]
	*  Operators:
	*      spendToken: [Joueur] *int * Status -> [Joueur]
	*         pre SpendToken(lemm,s),s) require nbTokens(s)>0
	*     reset: [Level] -> [Level]
	*/
	
	/**
	 * [invariants]
	 * 
	 * [init]
	 *  
	 * [spendToken]
	 *  nbTokens(SpendToken(lemm,s),s)=nbTokens(s)-1
	 *  s = Floater =>
	 *  	Lemming::isFloater(gameEngine(spendToken(J,L,s)),L) = true
	 *  s =  Bomber =>
	 *  	Lemming::isFloater(gameEngine(spendToken(J,L,s)),L) = true
	 *  (s != Floater ^ s != Bomber) => 
	 *  	getStatus(getLemm(gameEngine(spendToken(J,L,s)),L)) = s
	 * 
	 * [reset]
	 * 
	 */
}
