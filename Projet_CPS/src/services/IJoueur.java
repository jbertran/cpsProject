package services;

public interface IJoueur {
	
	// OBSERVATORS
	int nbTokens(Status s);
	
	// CONSTRUCTORS
	void init();
	
	// OPERATORS
	/* /pre : nbTokens(J,s)>0 */
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
	 *  
	 * [reset]
	 * 
	 */
}
