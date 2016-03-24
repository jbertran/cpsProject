package contrat;

import services.Direction;
import services.IGameEng;
import services.ILemming;
import services.Nature;
import services.Status;
import decorateur.LemmingDecorateur;

public class LemmingContrat extends LemmingDecorateur{

	public LemmingContrat(ILemming delegates) {
		super(delegates);
	}

	public void checkInvariants() {
		if (!(getX() >= 0 && getY() >= 0))
			throw new Error("Lemming: erreur de position");
		if (!(getNumber() >= 0 && getNumber() < gameEngine().sizeColony()))
			throw new Error("Lemming: erreur de numéro");
	}

	public int getX() {
		// Invariants
		checkInvariants();
		return super.getX();
	}

	public int getY() {
		// Invariants
		checkInvariants();
		return super.getY();
	}

	public int getNumber() {
		// Invariants
		checkInvariants();
		return super.getNumber();
	}

	public Direction getDir() {
		// Invariants
		checkInvariants();
		return super.getDir();
	}

	public Status getStatus() {
		// Invariants
		checkInvariants();
		return super.getStatus();
	}

	public int timeFalling() {
		// Invariants
		checkInvariants();
		return super.timeFalling();
	}

	public IGameEng gameEngine() {
		// Invariants
		checkInvariants();
		return super.gameEngine();
	}
	
	public void init(IGameEng gE) {
		int expre = gE.level().entree_x();
		int eypre = gE.level().entree_y();
		// Invariants
		checkInvariants();
		super.init(gE);
		// Invariants
		checkInvariants();
		// POST
		if (getX(init(G))=gameEngine::entree_X()
				getY(init(G))=gameEngine::entree_Y()
				getDir(init(G))=DROITIER;
		getStatus(init(G))=TOMBEUR;
		timeFalling(init(G))=0;
		GameEng::nextLemNo(gameEngine(init(G))) = GameEng::nextLemNo(G) + 1
				getNumber(init(G)) = GameEng::nextLemNo(G)
	}

	public void changeDir() {
		Direction dirpre = getDir();
		checkInvariants();
		super.changeDir();
		if (dirpre == Direction.DROITE)
			if (getDir() != Direction.GAUCHE)
				throw new Error("Lemming: erreur changeDir");
		if (dirpre == Direction.GAUCHE)
			if (getDir() != Direction.DROITE)
				throw new Error("Lemming: erreur changeDir");
		// Invariants
		checkInvariants();
	}

	public void setStatus(Status s) {
		checkInvariants();
		super.setStatus(s);
		if (getStatus() != s)
			throw new Error("Lemming: erreur setStatus");
		// Invariants
		checkInvariants();
	}

	public void step() {
		// Capture
		ILemming [] colonypre = gameEngine().colony().clone();
		Direction dirpre = getDir();
		int xpre = getX(), ypre = getY();
		// Invariants
		checkInvariants();
		super.step();
		// POST
		if (getStatus() == Status.WALK) {
			if (gameEngine().level().nature(xpre, ypre + 1) == Nature.EMPTY) { 
				if (!(getStatus() == Status.FALL && getX() == xpre &&
						getY() == ypre))
					throw new Error("Lemming: step error");
				else if (dirpre == Direction.DROITE) {
					if (gameEngine().level().nature(xpre + 1, ypre) != Nature.EMPTY &&
							gameEngine().level().nature(xpre + 1, ypre - 1) != Nature.EMPTY) {
						if (!(getDir() == Direction.GAUCHE && getX() == xpre && getY() == ypre))
							throw new Error("Lemming: step error");
					}
					else
						if (!(getX() == xpre + 1 && getY() == ypre))
							throw new Error("Lemming: step error");
				}
				else {
					if (gameEngine().level().nature(xpre - 1, ypre) != Nature.EMPTY &&
							gameEngine().level().nature(xpre - 1, ypre - 1) != Nature.EMPTY) {
						if (!(getDir() == Direction.GAUCHE && getX() == xpre && getY() == ypre))
							throw new Error("Lemming: step error");
					}	
					else
						if (!(getX() == xpre - 1 && getY() == ypre))
							throw new Error("Lemming: step error");
				}
			}
			else if (getStatus() == Status.FALL)
				if (gameEngine().level().nature(xpre, ypre + 1) != Nature.EMPTY) { 
					if (timeFalling() < 8) {
						if (!(getStatus() == Status.WALK && getX() == xpre && getY() == ypre))
							throw new Error("Lemming: step error - status || position fall recovery");
					}
					else
						if (!(colonypre.length == gameEngine().colony().length - 1))
							throw new Error("Lemming: step error - colony size");
				}
				else
					if (!(getX() == xpre && getY() == ypre + 1))
						throw new Error("Lemming: step error - position after falling");
		}
		// Invariants
		checkInvariants();
	}
}
