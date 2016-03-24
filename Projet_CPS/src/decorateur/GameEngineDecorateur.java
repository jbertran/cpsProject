package decorateur;

import services.IGameEng;
import services.ILemming;
import services.ILevel;

public class GameEngineDecorateur implements IGameEng{
	IGameEng delegate;
	
	public GameEngineDecorateur(IGameEng delegates){
		this.delegate=delegates;
	}

	@Override
	public ILemming[] colony() {
		return delegate.colony();
	}

	@Override
	public ILemming getLemm(int ln) {
		return delegate.getLemm(ln);
	}

	@Override
	public int sizeColony() {
		return delegate.sizeColony();
	}

	@Override
	public int spawned() {
		return delegate.spawned();
	}

	@Override
	public int spawnSpeed() {
		return delegate.spawnSpeed();
	}

	@Override
	public ILevel level() {
		return delegate.level();
	}

	@Override
	public ILevel levelInit() {
		return delegate.levelInit();
	}

	@Override
	public int tours() {
		return delegate.tours();
	}

	@Override
	public int nbSauves() {
		return delegate.nbSauves();
	}

	@Override
	public int score() {
		return delegate.score();
	}

	@Override
	public boolean obstacle(int x, int y) {
		return obstacle(x, y);
	}

	@Override
	public boolean gameOver() {
		return delegate.gameOver();
	}

	@Override
	public void init(ILevel lvl, int sc, int ss) {
		delegate.init(lvl, sc, ss);
		
	}

	@Override
	public void addLemming(ILemming l) {
		delegate.addLemming(l);
		
	}

	@Override
	public void killLemming(int ln) {
		delegate.killLemming(ln);
		
	}

	@Override
	public void saveLemming(int ln) {
		delegate.saveLemming(ln);
		
	}

	@Override
	public void step() {
		delegate.step();
		
	}

	@Override
	public void loadLevel(ILevel lvl, int sc, int ss) {
		delegate.loadLevel(lvl, sc, ss);
		
	}

	@Override
	public int nextLemNo() {
		return delegate.nextLemNo();
	}

}
