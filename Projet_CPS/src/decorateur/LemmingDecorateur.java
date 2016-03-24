package decorateur;

import services.Direction;
import services.IGameEng;
import services.ILemming;
import services.Status;

public class LemmingDecorateur implements ILemming{
	
	ILemming delegate;
	
	public LemmingDecorateur(ILemming delegates){
		this.delegate=delegates;
	}

	@Override
	public int getX() {
		return delegate.getX();
	}

	@Override
	public int getY() {
		return delegate.getY();
	}

	@Override
	public int getNumber() {
		return delegate.getNumber();
	}

	@Override
	public Direction getDir() {
		return delegate.getDir();
	}

	@Override
	public Status getStatus() {
		return delegate.getStatus();
	}

	@Override
	public int timeFalling() {
		return delegate.timeFalling();
	}

	@Override
	public IGameEng gameEngine() {
		return delegate.gameEngine();
	}

	@Override
	public void init(IGameEng gE) {
		delegate.init(gE);
		
	}

	@Override
	public void changeDir() {
		delegate.changeDir();
		
	}

	@Override
	public void setStatus(Status s) {
		delegate.setStatus(s);
		
	}

	@Override
	public void step() {
		delegate.step();
		
	}

}
