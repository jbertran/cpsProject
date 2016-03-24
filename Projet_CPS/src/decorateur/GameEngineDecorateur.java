package decorateur;

import services.IGameEng;
import services.ILemming;
import services.ILevel;

public class GameEngineDecorateur implements IGameEng{
	IGameEng delegates;
	
	public GameEngineDecorateur(IGameEng delegates){
		this.delegates=delegates;
	}

	@Override
	public ILemming[] colony() {
		return delegates.colony();
	}

	@Override
	public ILemming getLemm(int ln) {
		return delegates.getLemm(ln);
	}

	@Override
	public int sizeColony() {
		return delegates.sizeColony();
	}

	@Override
	public int spawned() {
		return delegates.spawned();
	}

	@Override
	public int spawnSpeed() {
		return delegates.spawnSpeed();
	}

	@Override
	public ILevel level() {
		return delegates.level();
	}

	@Override
	public ILevel levelInit() {
		return delegates.levelInit();
	}

	@Override
	public int tours() {
		return delegates.tours();
	}

	@Override
	public int nbSauves() {
		return delegates.nbSauves();
	}

	@Override
	public int score() {
		return delegates.score();
	}

	@Override
	public boolean obstacle(int x, int y) {
		return obstacle(x, y);
	}

	@Override
	public boolean gameOver() {
		return delegates.gameOver();
	}

	@Override
	public void init(ILevel lvl, int sc, int ss) {
		delegates.init(lvl, sc, ss);
		
	}

	@Override
	public void addLemming(ILemming l) {
		delegates.addLemming(l);
		
	}

	@Override
	public void killLemming(int ln) {
		delegates.killLemming(ln);
		
	}

	@Override
	public void saveLemming(int ln) {
		delegates.saveLemming(ln);
		
	}

	@Override
	public void step() {
		delegates.step();
		
	}

	@Override
	public void loadLevel(ILevel lvl, int sc, int ss) {
		delegates.loadLevel(lvl, sc, ss);
		
	}

	@Override
	public int nextLemNo() {
		return delegates.nextLemNo();
	}

}
