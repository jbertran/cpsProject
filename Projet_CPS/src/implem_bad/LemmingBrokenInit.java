package implem_bad;

import services.Direction;
import services.IGameEng;
import services.Status;
import implem.Lemming;

public class LemmingBrokenInit extends Lemming{
	
	@Override
	public void init(IGameEng gE) {
		gameEngine = gE;
		stat = Status.FALL;
		dir = Direction.DROITE;
		number = gE.nextLemNo();
		x = gE.level().entree_x();
		y = gE.level().entree_y();
		timeWaiting = -1;
		tilesBuilt = 0;
		timeFalling = 0;
		timeBashing = 0;
		minedown=true;
		timeExploding=0;
		isBomber=false;
	}

}
