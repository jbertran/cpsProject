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
	int timeBashing;
	int timeExploding;
	boolean isBomber;
	boolean minedown;

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getNumber() {
		return number;
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

	public int timeFalling() {
		return timeFalling;
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

			case MINER:
				if(minedown){
					if(this.gameEngine().level().nature(this.x, y+1)==Nature.METAL ){
						stat= Status.WALK;
						break;
					}else{
						this.gameEngine().level().remove(this.x, this.y+1);
						y+=1;
						minedown=!minedown;
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
				this.gameEngine().level().build(this.x, this.y);
				this.gameEngine().level().build(this.x, this.y-1);
				this.gameEngine().killLemming(this.number);
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
				break;

			default:
				System.out.println("Shit.");
				break;
			}
			if(isBomber){
				timeExploding++;
				if(timeExploding>4){
					for(int i=x-2;i<(x-2)+5;i++){
						for(int j=y-1;j<(y-1)+3;j++){
							if(gameEngine.level().nature(i, j)==Nature.DIRT)
								gameEngine.level().remove(i, j);
						}
					}
					gameEngine.killLemming(number);
				}
			}
		}
	}

}
