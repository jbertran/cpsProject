package implem;

import services.Direction;
import services.IGameEng;
import services.ILemming;
import services.Nature;
import services.Status;

public class Lemming implements ILemming{

	protected int x, y, number, timeFalling;
	protected IGameEng gameEngine;
	protected Direction dir;
	protected Status stat;
	protected int timeWaiting;
	protected int tilesBuilt;
	protected int timeBashing;
	protected int timeExploding;
	protected boolean isClimber;
	protected boolean isBomber;
	protected boolean isFloater;
	protected boolean minedown;

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getNumber() {
		return number;
	}


	public int getTimeWaiting() {
		return timeWaiting;
	}


	public void setTimeWaiting(int timeWaiting) {
		this.timeWaiting = timeWaiting;
	}


	public int timeWaiting() {
		return timeWaiting;
	}


	public void setWaiting(int timeWaiting) {
		this.timeWaiting = timeWaiting;
	}


	public int tilesBuilt() {
		return tilesBuilt;
	}


	public void setTilesBuilt(int tilesBuilt) {
		this.tilesBuilt = tilesBuilt;
	}


	public Direction getDir() {
		return dir;
	}


	public Status getStatus() {
		return stat;
	}

	public boolean isBomber(){
		return isBomber;
	}

	public void setBomber(){
		isBomber=true;
	}

	@Override
	public boolean isFloater() {
		return isFloater;
	}

	public void setFloater(boolean b) {
		isFloater = b;
	}

	public int timeFalling() {
		return timeFalling;
	}

	public void setTimeFalling(int i) {
		timeFalling = i;
	}

	public int timeBashing() {
		return timeBashing;
	}

	public boolean isMiningDown(){
		return minedown;
	}

	public int timeExploding() {
		return timeExploding;
	}
	public IGameEng gameEngine() {
		return gameEngine;
	}

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


	public void changeDir() {
		dir = ((dir == Direction.DROITE) ? Direction.GAUCHE : Direction.DROITE);
	}


	public void setStatus(Status s) {
		stat = s;
	}


	public void step() {
		if (getX() == gameEngine().level().sortie_x() && 
				getY() == gameEngine().level().sortie_y())
			gameEngine().saveLemming(number);
		else {
			switch (getStatus()) {
			case WALK:
				boolean cldUp = false;
				if (isClimber()) {
					if (getDir() == Direction.DROITE) {
						if (gameEngine().obstacle(getX() + 1, getY()) &&
								gameEngine().obstacle(getX() + 1, getY() - 1) &&
								!gameEngine().obstacle(getX(), getY() - 2)){
							y--;
							cldUp = true;
						}
						else if (gameEngine().obstacle(getX() + 1, getY()) &&
								!gameEngine().obstacle(getX() + 1, getY() - 1) &&
								!gameEngine().obstacle(getX() + 1, getY() - 2)) {
							y--; x++;
							cldUp = true;
						}
					}
					else if (getDir() == Direction.GAUCHE) {
						if (gameEngine().obstacle(getX() - 1, getY()) &&
								gameEngine().obstacle(getX() - 1, getY() - 1) &&
								!gameEngine().obstacle(getX(), getY() - 2)){
							y--;
							cldUp = true;
						}
						else if (gameEngine().obstacle(getX() - 1, getY()) &&
									!gameEngine().obstacle(getX() - 1, getY() - 1) &&
									!gameEngine().obstacle(getX() - 1, getY() - 2)) {
							y--; x--;
							cldUp = true;
						}
					}
				}
				if (!gameEngine().obstacle(getX(), getY() + 1) && !cldUp) {
					setStatus(Status.FALL);
				}
				else if (dir == Direction.DROITE) {
					boolean canR = !(gameEngine().obstacle(getX()+1, getY())
							|| gameEngine().obstacle(getX()+1, getY()-1));
					boolean canUpR = !(getY() - 2 < 0 
							|| gameEngine().obstacle(getX()+1, getY()-1)
							|| gameEngine().obstacle(getX()+1, getY()-2));
					if (canR && !cldUp) {
						x++;
					}
					else if (canUpR && !cldUp) {
						x++; y--;
					}
					else if (!isClimber())
						changeDir();
				}
				else if (dir == Direction.GAUCHE){
					boolean canL = !(gameEngine().obstacle(getX()-1, getY())
							|| gameEngine().obstacle(getX()-1, getY()-1));
					boolean canUpL = !(getY() - 2 < 0 
							|| gameEngine().obstacle(getX()-1, getY()-1)
							|| gameEngine().obstacle(getX()-1, getY()-2));
					if (canL && !cldUp) {
						x--;
					}
					else if (canUpL && cldUp) {
						x--; y--;
					}
					else if (!isClimber())
						changeDir();
				}
				break;
			case FALL:
				boolean obs = gameEngine().obstacle(getX(), getY()+1);
				if (isFloater()) {
					if (obs) {
						setStatus(Status.WALK);
						setFloater(false);
						setTimeFalling(0);
					}
					else if (timeFalling() % 2 == 0) {
						y++;
						timeFalling++;
					}
				}
				else {
					if (obs)
						if (timeFalling() < 8) {
							setStatus(Status.WALK);
							setTimeFalling(0);
						}
						else
							gameEngine().killLemming(getNumber());
					else {
						y++;
						timeFalling++;
					}
				}
				break;
			case BUILD:
				if (tilesBuilt() >= 12) {
					tilesBuilt = 0;
					setWaiting(-1);
					setStatus(Status.WALK);
				}
				else {
					if (getDir() == Direction.DROITE) {
						if (timeWaiting() == 0) {
							setWaiting(-1);
							gameEngine().level().build(x + 1, y);
							gameEngine().level().build(x + 2, y);
							gameEngine().level().build(x + 2, y - 1);
							if (!gameEngine().obstacle(x+2, y-2) &&
									!gameEngine().obstacle(x+2, y-3)) {
								x += 2; y -=2;
								tilesBuilt += 3;
							}
							else {
								tilesBuilt = 0;
								setWaiting(-1);
								setStatus(Status.WALK);
							}
						}
						else if (timeWaiting() > 0)
							timeWaiting--;
						else {
							if (!gameEngine().obstacle(x + 1, y) &&
									!gameEngine().obstacle(x + 2, y) &&
									!gameEngine().obstacle(x + 2, y - 1))
								timeWaiting = 3;
							else {
								tilesBuilt = 0;
								setWaiting(-1);
								setStatus(Status.WALK);
							}
						}
					}
					if (getDir() == Direction.GAUCHE) {
						if (timeWaiting() == 0) {
							setWaiting(-1);
							gameEngine().level().build(x - 1, y);
							gameEngine().level().build(x - 2, y);
							gameEngine().level().build(x - 2, y - 1);
							if (!gameEngine().obstacle(x-2, y-2) &&
									!gameEngine().obstacle(x-2, y-3)) {
								x -= 2; y -=2;
								tilesBuilt += 3;
							}
							else {
								tilesBuilt = 0;
								setWaiting(-1);
								setStatus(Status.WALK);
							}
						}
						else if (timeWaiting() > 0)
							timeWaiting--;
						else {
							if (!gameEngine().obstacle(x - 1, y) &&
									!gameEngine().obstacle(x - 2, y) &&
									!gameEngine().obstacle(x - 2, y - 1))
								timeWaiting = 3;
							else {
								tilesBuilt = 0;
								setWaiting(-1);
								setStatus(Status.WALK);
							}
						}
					}
				}
				break;
			case MINER:
				if(this.gameEngine().level().nature(this.x, y+1)==Nature.EMPTY){
					stat=Status.FALL;
					break;
				}
				if(minedown){
					if(this.gameEngine().level().nature(this.x, y+1)==Nature.METAL ){
						stat= Status.WALK;
						break;
					}else{
						this.gameEngine().level().remove(this.x, this.y+1);
						y+=1;
						minedown=!minedown;
					}
					if(this.gameEngine().level().nature(this.x, y+1)==Nature.EMPTY){
						stat=Status.FALL;
						break;
					}
					if( this.gameEngine().level().nature(this.x, y+1)==Nature.METAL){
						stat= Status.WALK;
						break;
					}else{
						this.gameEngine().level().remove(this.x, this.y+1);
						y+=1;
					}
				}else{
					if(dir==Direction.DROITE){
						if(this.gameEngine().level().nature(this.x+1, y)==Nature.METAL 
								|| this.gameEngine().level().nature(this.x+1, y-1)==Nature.METAL){
							stat= Status.WALK;
							break;
						}else{
							minedown=!minedown;
							this.gameEngine().level().remove(this.x+1, this.y);
							this.gameEngine().level().remove(this.x+1, this.y-1);
							x+=1;
						}
					}else{
						if(this.gameEngine().level().nature(this.x-1, y)==Nature.METAL 
								|| this.gameEngine().level().nature(this.x-1, y-1)==Nature.METAL){
							stat= Status.WALK;
							break;
						}else{
							minedown=!minedown;
							this.gameEngine().level().remove(this.x-1, this.y);
							this.gameEngine().level().remove(this.x-1, this.y-1);
							x-=1;
						}
					}
				}
				break;
			case STOP:
				break;

			case BASH:
				if(this.timeBashing>19){
					timeBashing=0;
					stat = Status.WALK;
					break;	
				}
				if(this.gameEngine().level().nature(this.x, this.y+1)==Nature.EMPTY){
					stat = Status.FALL;
					break;
				}
				if(this.dir==Direction.DROITE){
					if(this.gameEngine().level().nature(this.x+1, y)==Nature.METAL ||
							this.gameEngine().level().nature(this.x+1, y-1)==Nature.METAL
							|| this.gameEngine().level().nature(this.x+1, y-2)==Nature.METAL){
						stat=Status.WALK;
						break;
					}
					this.gameEngine().level().remove(this.x+1, y);
					this.gameEngine().level().remove(this.x+1, y-1);
					this.gameEngine().level().remove(this.x+1, y-2);
					x += 1;
				}else{
					if(this.gameEngine().level().nature(this.x-1, y)==Nature.METAL ||
							this.gameEngine().level().nature(this.x-1, y-1)==Nature.METAL
							|| this.gameEngine().level().nature(this.x-1, y-2)==Nature.METAL){
						stat=Status.WALK;
						break;
					}
					this.gameEngine().level().remove(this.x-1, y);
					this.gameEngine().level().remove(this.x-1, y-1);
					this.gameEngine().level().remove(this.x-1, y-2);
					x -= 1;
				}
				timeBashing++;
				break;

			default:
				System.out.println("Weird.");
				break;
			}
			if(isBomber){
				timeExploding++;
				if(timeExploding>4){
					for(int i=x-2;i<(x-2)+5;i++){
						for(int j=y-1;j<(y-1)+3;j++){
							if(i>0 && j>0 && i<gameEngine().level().width() && j<gameEngine().level().height())
							if(gameEngine.level().nature(i, j)==Nature.DIRT)
								gameEngine.level().remove(i, j);
						}
					}
					gameEngine.killLemming(number);
				}
			}
		}
	}

	public boolean isClimber() {
		return isClimber;
	}

	public void setClimber() {
		isClimber = true;
	}
}
