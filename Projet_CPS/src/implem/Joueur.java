package implem;

import java.util.HashMap;

import services.IGameEng;
import services.IJoueur;
import services.Status;

public class Joueur implements IJoueur{

	HashMap<Status, Integer> tokens;
	IGameEng gameEngine;
	
	
	public int nbTokens(Status s) {
		return tokens.get(s);
	}

	
	public void init(IGameEng gE) {
		gameEngine = gE;
		tokens = new HashMap<Status, Integer>();
		for (Status s : Status.values())
			tokens.put(s, 10);
	}

	
	public void spendToken(int lemm, Status s) {
		if(s==Status.BOMB)
			gameEngine.getLemm(lemm).setBomber();
		else
		gameEngine.getLemm(lemm).setStatus(s);
		tokens.put(s, tokens.get(s) - 1);
	}

	
	public void reset() {
		gameEngine.loadLevel(gameEngine.levelInit(), 
				gameEngine.sizeColony(), gameEngine.spawnSpeed());
	}

	
	public IGameEng gameEngine() {
		return gameEngine;
	}

}
