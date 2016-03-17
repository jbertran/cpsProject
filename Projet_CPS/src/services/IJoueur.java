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
