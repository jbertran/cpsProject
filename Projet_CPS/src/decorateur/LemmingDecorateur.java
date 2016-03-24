package decorateur;

import services.Direction;
import services.IGameEng;
import services.ILemming;
import services.Status;

public class LemmingDecorateur implements ILemming{
	
	ILemming delegates;
	
	public LemmingDecorateur(ILemming delegates){
		this.delegates=delegates;
	}

	@Override
	public int getX() {
		return delegates.getX();
	}

	@Override
	public int getY() {
		return delegates.getY();
	}

	@Override
	public int getNumber() {
		return delegates.getNumber();
	}

	@Override
	public Direction getDir() {
		return delegates.getDir();
	}

	@Override
	public Status getStatus() {
		return delegates.getStatus();
	}

	@Override
	public int timeFalling() {
		return delegates.timeFalling();
	}

	@Override
	public IGameEng gameEngine() {
		return delegates.gameEngine();
	}

	@Override
	public void init(IGameEng gE) {
		delegates.init(gE);
		
	}

	@Override
	public void changeDir() {
		delegates.changeDir();
		
	}

	@Override
	public void setStatus(Status s) {
		delegates.setStatus(s);
		
	}

	@Override
	public void step() {
		delegates.step();
		
	}

}
