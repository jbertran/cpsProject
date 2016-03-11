package services;

public interface ILemming {
	
	// OBSERVATORS
	int getX();
	int getY();
	int getNumber();
	Direction getDir();
	Status getStatus();
	int timeFalling();
	IGameEng gameEngine();
	
	// CONSTRUCTORS
	void init(IGameEng gE);
	
	// OPERATORS
	void changeDir();
	void setStatus(Status s);
	void step();
}
