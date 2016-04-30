package implem;

import services.IGameEng;
import services.ILemming;
import services.ILevel;
import services.Nature;

public class GameEngine implements IGameEng {

	static int nbLemm = 0;
	ILemming [] colony;
	ILevel lvl, lvlInit;
	int spawned, tours, nbSauves;
	int sizeColony, spawnSpeed, nbVivants;
	boolean gameOver;

	@Override
	public ILemming [] colony() {
		return colony;
	}

	@Override
	public ILemming getLemm(int ln) {
		return colony[ln];
	}

	@Override
	public int sizeColony() {
		return sizeColony;
	}

	@Override
	public int spawned() {
		return spawned;
	}

	@Override
	public int spawnSpeed() {
		return spawnSpeed;
	}

	@Override
	public ILevel level() {
		return lvl;
	}

	@Override
	public int tours() {
		return tours;
	}

	@Override
	public int nbSauves() {
		return nbSauves;
	}

	@Override
	public int score() {
		return nbSauves/tours;
	}

	public int nextLemNo() {
		return nbLemm++;
	}
	
	public int nbVivants() {
		return nbVivants;
	}

	@Override
	public boolean obstacle(int x, int y) {
		return lvl.nature(x, y) != Nature.EMPTY;
	}

	@Override
	public boolean gameOver() {
		return nbVivants == 0 && spawned != 0;
	}

	@Override
	public void init(ILevel lvl, int sc, int ss) {
		colony = new Lemming [sc];
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
	public void addLemming(ILemming l) {
		colony[l.getNumber()] = l;
		nbVivants++;
		spawned++;
	}

	@Override
	public void killLemming(int ln) {
		colony[ln] = null;
		nbVivants--;
	}

	@Override
	public void saveLemming(int ln) {
		colony[ln] = null;
		nbSauves++;
		nbVivants--;
	}

	@Override
	public void step() {
		if (spawned < sizeColony) {
			if (tours % spawnSpeed == 0) {
				ILemming l = new Lemming();
				l.init(this);
				addLemming(l);
			}
		}
		for (ILemming l : colony)
			if(l!=null)
				l.step();
		tours++;
	}

	@Override
	public void loadLevel(ILevel lvl, int sc, int ss) {
		colony = new Lemming [sc];
		sizeColony = sc;
		spawnSpeed = ss;
		spawned = 0;
		tours = 0;
		nbSauves = 0;
		this.lvl = lvl;
		lvlInit = lvl;
		nbLemm = 0;
		nbVivants = 0;
	}

	@Override
	public ILevel levelInit() {
		return lvlInit;
	}

	public String toString() {
		char [][] res = new char [level().width()][level().height()];
		for (int i = 0; i < level().width(); i++)
			for (int j = 0; j < level().height(); j++) {
				switch (level().nature(i, j)) {
				case EMPTY:
					res[i][j] = ' ';
					break;
				case METAL:
					res[i][j] = 'M';
					break;
				case DIRT:
					res[i][j] = 'X';
				}
			}
		for (ILemming l : colony)
			if (l != null)
				switch (l.getDir()) {
				case DROITE:
					res[l.getX()][l.getY()] = 'D';
					break;
				case GAUCHE:
					res[l.getX()][l.getY()] = 'G';
				}
		res[level().entree_x()][level().entree_y()] = 'E';
		res[level().sortie_x()][level().sortie_y()] = 'S';
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < level().height(); i++) {
			for (int j = 0; j < level().width(); j++) {
				b.append(res[j][i]);
			}
			b.append("\n");
		}
		return b.toString();
	}

	@Override
	public int peekNextLemNo() {
		return nextLemNo();
	}

	@Override
	public void setSpawnSpeed(int i) {
		spawnSpeed = i;
	}

}
