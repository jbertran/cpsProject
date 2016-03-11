package services;

public interface IJoueur {
	
	// OBSERVATORS
	int nbTokens(Status s);
	
	// CONSTRUCTORS
	void init();
	
	// OPERATORS
	void spendToken(int lemm, Status s);
	void reset();
}
