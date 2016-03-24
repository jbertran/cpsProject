package implem;

import java.util.HashMap;

import services.IGameEng;
import services.IJoueur;
import services.Status;

public class Joueur implements IJoueur{

	HashMap<Status, Integer> tokens;
	IGameEng gameEngine;
	
	@Override
	public int nbTokens(Status s) {
		return tokens.get(s);
	}

	@Override
	public void init(IGameEng gE) {
		gameEngine = gE;
		tokens = new HashMap<Status, Integer>();
		for (Status s : Status.values())
			tokens.put(s, 10);
	}

	@Override
	public void spendToken(int lemm, Status s) {
		gameEngine.getLemm(lemm).setStatus(s);
		tokens.put(s, tokens.get(s) - 1);
	}

	@Override
	public void reset() {
		gameEngine.loadLevel(gameEngine.levelInit(), 
				gameEngine.sizeColony(), gameEngine.spawnSpeed());
	}

	@Override
	public IGameEng gameEngine() {
		return gameEngine;
	}

}
