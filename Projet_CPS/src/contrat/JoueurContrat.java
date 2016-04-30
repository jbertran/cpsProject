package contrat;

import services.IGameEng;
import services.IJoueur;
import services.ILemming;
import services.Status;
import decorateur.JoueurDecorateur;
import error.PostConditionError;

public class JoueurContrat extends JoueurDecorateur{

	public JoueurContrat(IJoueur delegates) {
		super(delegates);
	}
	
	public void checkInvariants() {
		for (Status s: Status.values())
			if (!(super.nbTokens(s) >= 0))
				throw new Error("Joueur: token number error");
	}
	
	public int nbTokens(Status s){
		// Invariants
		checkInvariants();
		return super.nbTokens(s);
	}
	
	public void init(IGameEng gE) {
		IGameEng gpre = gameEngine();
		// Invariants
		//checkInvariants();
		super.init(gE);
		// Invariants
		checkInvariants();
		// POST
		for (Status s : Status.values())
			if (nbTokens(s) != 10)
				throw new Error("Joueur: token init error");
//		if (gameEngine() != gpre)
//			throw new Error("Joueur: game engine corruption");
	}
	
	public void spendToken(int lemm, Status s) {
		int nbpre = nbTokens(s);
		// PRE
		if (!(nbTokens(s) > 0))
			throw new Error("Joueur: token not spendable");
		// Invariants
		checkInvariants();
		super.spendToken(lemm, s);
		// Invariants
		checkInvariants();
		if (!(nbTokens(s) == nbpre - 1))
			throw new Error("Joueur: token spend error");
		ILemming l = gameEngine().getLemm(lemm);
		switch (s) {
		case BOMB:
			if (!l.isBomber())
				throw new PostConditionError("Joueur: lemming status change error");
			break;
		case FLOAT:
			if (!l.isFloater())
				throw new PostConditionError("Joueur: lemming status change error");
		default:
			if (!(l.getStatus() == s))
				throw new PostConditionError("Joueur: lemming status change error");
		}
	}
	
	public void reset() {
		// Invariants
		checkInvariants();
		super.reset();
		// Invariants
		checkInvariants();
		for (Status s : Status.values())
			if (nbTokens(s) != 10)
				throw new Error("Joueur: token init error");
	}
	
	public String toString(){
		return super.toString();
	}
}
