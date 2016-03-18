package implem;

import services.Direction;
import services.IGameEng;
import services.ILemming;
import services.Nature;
import services.Status;

public class Lemming implements ILemming{

	int x, y, number, timeFalling;
	IGameEng gameEngine;
	Direction dir;
	Status stat;

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public Direction getDir() {
		return dir;
	}

	@Override
	public Status getStatus() {
		return stat;
	}

	@Override
	public int timeFalling() {
		return timeFalling;
	}

	@Override
	public IGameEng gameEngine() {
		return gameEngine;
	}

	@Override
	public void init(IGameEng gE) {
		gameEngine = gE;
		stat = Status.FALL;
		dir = Direction.DROITE;
		number = gE.nextLemNo();
		x = gE.level().entree_x();
		y = gE.level().entree_y();
		timeFalling = 0;
	}

	@Override
	public void changeDir() {
		dir = ((dir == Direction.DROITE) ? Direction.GAUCHE : Direction.DROITE);
	}

	@Override
	public void setStatus(Status s) {
		stat = s;
	}

	@Override
	public void step() {
		if (getX() == gameEngine().level().sortie_x() && 
				getY() == gameEngine().level().sortie_y())
			gameEngine().saveLemming(number);
		else {
			switch (stat) {
			case WALK:
				if (gameEngine().level().nature(getX(), getY() + 1) == Nature.EMPTY)
					stat = Status.FALL;
				else if (dir == Direction.DROITE) {
					if (!(gameEngine().obstacle(getX()+1, getY())
							|| gameEngine().obstacle(getX()+1, getY()-1)))
						x += 1;
					else
						changeDir();
				}
				else if (dir == Direction.GAUCHE){
					if (!(gameEngine().obstacle(getX()-1, getY())
							|| gameEngine().obstacle(getX()-1, getY()-1)))
						x -= 1;
					else
						changeDir();
				}
			case FALL:
				if (gameEngine().obstacle(getX(), getY()+1))
					if (timeFalling > 8)
						gameEngine().killLemming(number);
					else
						stat = Status.WALK;
				else 
					y += 1;
				break;
			default:
				System.out.println("Shit.");
				break;
			}
		}
	}

}
