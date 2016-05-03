package implem_bad;

import services.ILevel;
import implem.GameEngine;
import implem.Lemming;

public class GameEngineInitBroken extends GameEngine{

	@Override
	public void init(ILevel lvl, int sc, int ss) {
		colony = new Lemming [sc];
		sizeColony = sc; 
		spawnSpeed = ss;
		spawned = 0;
		tours = 0; //should be 1
		nbSauves = 0;
		this.lvl = lvl;
		lvlInit = lvl;
		nbLemm = 0;
		nbVivants = 0;
	}
}
