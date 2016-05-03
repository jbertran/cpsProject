package implem_bad;

import contrat.LemmingContrat;
import implem.GameEngine;
import implem.Lemming;
import services.ILemming;
import services.ILevel;

public class GameEngineLemmingBroken extends GameEngine{
	
	@Override
	public void init(ILevel lvl, int sc, int ss) {
		colony = new LemmingContrat [sc];
		sizeColony = sc;
		spawnSpeed = ss;
		spawned = 0;
		tours = 1;
		nbSauves = 0;
		this.lvl = lvl;
		lvlInit = lvl;
		nbLemm = 0;
		nbVivants = 0;
	}
	
	@Override
	public void step() {
		if (spawned < sizeColony) {
			if (tours % spawnSpeed == 0) {
				ILemming l = new LemmingContrat(new LemmingBrokenInit());
				l.init(this);
				addLemming(l);
			}
		}
		for (ILemming l : colony)
			if(l!=null)
				l.step();
		tours++;
	}

}
