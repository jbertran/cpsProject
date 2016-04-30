package decorateur;

import services.IGameEng;
import services.ILemming;
import services.ILevel;

public class GameEngineDecorateur implements IGameEng{
	IGameEng delegate;
	
	public GameEngineDecorateur(IGameEng delegates){
		this.delegate=delegates;
	}

	public ILemming[] colony() {
		return delegate.colony();
	}

	public ILemming getLemm(int ln) {
		return delegate.getLemm(ln);
	}

	public int sizeColony() {
		return delegate.sizeColony();
	}


	public int spawned() {
		return delegate.spawned();
	}

	
	public int spawnSpeed() {
		return delegate.spawnSpeed();
	}

	
	public ILevel level() {
		return delegate.level();
	}

	
	public ILevel levelInit() {
		return delegate.levelInit();
	}

	
	public int tours() {
		return delegate.tours();
	}

	
	public int nbSauves() {
		return delegate.nbSauves();
	}

	
	public int score() {
		return delegate.score();
	}

	
	public boolean obstacle(int x, int y) {
		return obstacle(x, y);
	}

	
	public boolean gameOver() {
		return delegate.gameOver();
	}

	
	public void init(ILevel lvl, int sc, int ss) {
		delegate.init(lvl, sc, ss);
	}

	
	public void addLemming(ILemming l) {
		delegate.addLemming(l);
		
	}

	
	public void killLemming(int ln) {
		delegate.killLemming(ln);
		
	}
	
	public void saveLemming(int ln) {
		delegate.saveLemming(ln);
		
	}
	
	public void step() {
		delegate.step();
		
	}
	
	public void loadLevel(ILevel lvl, int sc, int ss) {
		delegate.loadLevel(lvl, sc, ss);
		
	}

	public int nextLemNo() {
		return delegate.nextLemNo();
	}

	
	public int peekNextLemNo() {
		return delegate.peekNextLemNo();
	}
	
	public String toString(){
		return delegate.toString();
	}

	@Override
	public int nbVivants() {
		return delegate.nbVivants();
	}

}
