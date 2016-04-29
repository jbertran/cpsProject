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

	
	public int getX() {
		return delegate.getX();
	}

	
	public int getY() {
		return delegate.getY();
	}

	public int getNumber() {
		return delegate.getNumber();
	}

	
	public Direction getDir() {
		return delegate.getDir();
	}

	
	public Status getStatus() {
		return delegate.getStatus();
	}

	
	public int timeFalling() {
		return delegate.timeFalling();
	}
	public int timeBashing(){
		return delegate.timeBashing();
	}
	public boolean isMiningDown(){
		return delegate.isMiningDown();
	}
	public int timeExploding(){
		return delegate.timeExploding();
	}
	
	public IGameEng gameEngine() {
		return delegate.gameEngine();
	}

	
	public void init(IGameEng gE) {
		delegate.init(gE);
		
	}

	
	public void changeDir() {
		delegate.changeDir();
		
	}

	
	public void setStatus(Status s) {
		delegate.setStatus(s);
		
	}

	
	public void step() {
		delegate.step();
		
	}

	public boolean isBomber(){
		return delegate.isBomber();
	}
	public void setBomber() {
		delegate.setBomber();
	}
	public String toString(){
		return delegate.toString();
	}
}
