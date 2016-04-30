package contrat;

import services.Direction;
import services.IGameEng;
import services.ILemming;
import services.Nature;
import services.Status;
import decorateur.LemmingDecorateur;
import error.InvariantError;
import error.PostConditionError;

public class LemmingContrat extends LemmingDecorateur{

	public LemmingContrat(ILemming delegates) {
		super(delegates);
	}

	public void checkInvariants() {
		if (!(getX() >= 0 && getY() >= 0))
			throw new InvariantError("Lemming: erreur de position");
		if (!(getNumber() >= 0 && getNumber() < gameEngine().sizeColony()))
			throw new InvariantError("Lemming: erreur de numéro");
		if (!gameEngine().obstacle(getX(), getY()) || !gameEngine().obstacle(getX(), getY()-1))
			throw new InvariantError("Lemming: pas de place en y & y-1");
	}

	public int getX() {
		// Invariants
		checkInvariants();
		return super.getX();
	}

	public int getY() {
		// Invariants
		checkInvariants();
		return super.getY();
	}

	public int getNumber() {
		// Invariants
		checkInvariants();
		return super.getNumber();
	}

	public Direction getDir() {
		// Invariants
		checkInvariants();
		return super.getDir();
	}
	public boolean isBomber(){
		// Invariants
		checkInvariants();
		return super.isBomber();
	}
	public Status getStatus() {
		// Invariants
		checkInvariants();
		return super.getStatus();
	}

	public int timeFalling() {
		// Invariants
		checkInvariants();
		return super.timeFalling();
	}

	public IGameEng gameEngine() {
		// Invariants
		checkInvariants();
		return super.gameEngine();
	}
	 

	public void init(IGameEng gE) {
		int nextlpre = gE.peekNextLemNo();
		// Invariants
		checkInvariants();
		super.init(gE);
		// Invariants
		checkInvariants();
		// POST
		if (!(getX() == gameEngine().level().entree_x() && getY() == gameEngine().level().entree_y()
				&& getDir() == Direction.DROITE && getStatus() == Status.FALL && timeFalling() == 0
				&& getNumber() == gameEngine().peekNextLemNo() 
				&& nextlpre + 1 == gameEngine().peekNextLemNo()))
			throw new Error("Lemming: Initialization error");
	}

	public void changeDir() {
		Direction dirpre = getDir();
		checkInvariants();
		super.changeDir();
		if (dirpre == Direction.DROITE)
			if (getDir() != Direction.GAUCHE)
				throw new Error("Lemming: erreur changeDir");
		if (dirpre == Direction.GAUCHE)
			if (getDir() != Direction.DROITE)
				throw new Error("Lemming: erreur changeDir");
		// Invariants
		checkInvariants();
	}

	public void setStatus(Status s) {
		checkInvariants();
		super.setStatus(s);
		if (getStatus() != s)
			throw new Error("Lemming: erreur setStatus");
		// Invariants
		checkInvariants();
	}

	public void step() {
		// Capture
		ILemming [] colonypre = gameEngine().colony().clone();
		Direction dirpre = getDir();
		int xpre = getX(), ypre = getY();
		int fallpre = timeFalling();
		int tilespre = tilesBuilt();
		int waitingpre = timeWaiting();
		int timeEx_pre = -1;
		boolean floatpre = isFloater();
		Status Stat_pre=this.getStatus();
		if(isBomber()){
			timeEx_pre=timeExploding();
		}
		// Invariants
		checkInvariants();
		super.step();
		// POST
		switch(Stat_pre) {
		case WALK:
			boolean cldUp = false;
			if (isClimber()) {
				if (getDir() == Direction.DROITE) {
					if (gameEngine().obstacle(xpre + 1, ypre) &&
							gameEngine().obstacle(xpre + 1, ypre - 1) &&
							!gameEngine().obstacle(xpre, ypre - 2)){
						if (! (getY() == ypre - 1))
							throw new PostConditionError("Lemming: climber up err");
						cldUp = true;
					}	
				}
				else if (getDir() == Direction.GAUCHE){
					if (gameEngine().obstacle(xpre - 1, ypre) &&
							gameEngine().obstacle(xpre - 1, ypre - 1) &&
							!gameEngine().obstacle(xpre, ypre - 2)){
						if (! (getY() == ypre - 1))
							throw new PostConditionError("Lemming: climber up err");
						cldUp = true;
					}
				}
			}
			if (!gameEngine().obstacle(xpre, ypre + 1) && !cldUp) { 
				if (!(getStatus() == Status.FALL && getX() == xpre &&
						getY() == ypre))
					throw new Error("Lemming: step error");
				else if (dirpre == Direction.DROITE) {
					boolean shouldR = (!gameEngine().obstacle(xpre + 1, ypre) 
							&& !gameEngine().obstacle(xpre + 1, ypre - 1));
					boolean shouldUpR = (!gameEngine().obstacle(xpre + 1, ypre - 1) 
							&& !gameEngine().obstacle(xpre + 1, ypre - 2));
					if (shouldR && !cldUp){
						if (!(getX() == xpre + 1 && getY() == ypre))
							throw new Error("Lemming: step going right");
					}
					else if (shouldUpR && !cldUp) {
						if (!(getX() == xpre + 1 && getY() == ypre - 1))
							throw new Error("Lemming: step going right & up");
					}
					else if (!isClimber()){
						if (!(getDir() == Direction.GAUCHE && getX() == xpre && getY() == ypre))
							throw new Error("Lemming: step changing");
					}
				}
				else {
					boolean shouldL = (!gameEngine().obstacle(xpre - 1, ypre) 
							&& !gameEngine().obstacle(xpre - 1, ypre - 1));
					boolean shouldUpL = (!gameEngine().obstacle(xpre - 1, ypre - 1) 
							&& !gameEngine().obstacle(xpre - 1, ypre - 2));
					if (shouldL){
						if (!(getX() == xpre - 1 && getY() == ypre))
							throw new Error("Lemming: step going left");
					}
					else if (shouldUpL && !cldUp) {
						if (!(getX() == xpre - 1 && getY() == ypre - 1))
							throw new Error("Lemming: step going left & up");
					}
					else if (!isClimber()) {
						if (!(getDir() == Direction.DROITE && getX() == xpre && getY() == ypre))
							throw new Error("Lemming: step changing");
					}
				}
			}
			break;
		case FALL:
			if (floatpre) {
				if (gameEngine().obstacle(xpre, ypre+1)) {
					if (!(getStatus() == Status.WALK &&
							!isFloater() && getX() == xpre && getY() == ypre))
						throw new PostConditionError("Lemming: Floater reset error");
				}
				else if (fallpre%2 == 0)
					if (getY() != ypre + 1 || timeFalling() != fallpre + 1)
						throw new PostConditionError("Lemming: floater falling error");
			}
			else {
				if (gameEngine().obstacle(xpre, ypre + 1)) {
					if (timeFalling() < 8) {
						if (!(getStatus() == Status.WALK && getX() == xpre && getY() == ypre))
							throw new Error("Lemming: step error - status || position fall recovery");
					}
					else
						if (!(colonypre.length == gameEngine().colony().length - 1))
							throw new Error("Lemming: step error - not killed after landing");
				}
				else
					if (!(getX() == xpre && getY() == ypre + 1 && timeFalling() == fallpre + 1))
						throw new Error("Lemming: step error - position after falling");
			}
			break;
		case BUILD:
			if (tilespre >= 12) {
				if (!(tilesBuilt() == 0 &&
						timeWaiting() == -1 &&
						getStatus() == Status.WALK))
					throw new PostConditionError("Step BUILDER: reset to WALK");
			}
			else {
				if (dirpre == Direction.DROITE) {
					if (waitingpre == 0) {
						if (!(timeWaiting() == -1 &&
								gameEngine().level().nature(xpre + 1, ypre) == Nature.DIRT &&
								gameEngine().level().nature(xpre + 2, ypre) == Nature.DIRT &&
								gameEngine().level().nature(xpre + 2, ypre - 1) == Nature.DIRT &&
								getX() == xpre + 2 && getY() == ypre - 1 &&
								tilesBuilt() == tilespre + 3))
							throw new PostConditionError("Step BUILDER: erreur au build");
						else if (waitingpre > 0) {
							if (timeWaiting() != waitingpre - 1)
								throw new PostConditionError("Step BUILDER: decr waiting time");
						}
						else {
							if (!gameEngine().obstacle(xpre + 1, ypre) &&
									!gameEngine().obstacle(xpre + 2, ypre) &&
									!gameEngine().obstacle(xpre + 2, ypre - 1)) {
								if (! (waitingpre == 3))
									throw new PostConditionError("Step BUILDER: new build");
							}
							else
								if (!(tilesBuilt() == 0 && getStatus() == Status.WALK))
									throw new PostConditionError("Step BUILDER: reset to walker");
						}
							
					}
				}
				if (dirpre == Direction.GAUCHE) {
					if (waitingpre == 0) {
						if (!(timeWaiting() == -1) &&
								(gameEngine().level().nature(xpre - 1, ypre) == Nature.DIRT) &&
								(gameEngine().level().nature(xpre - 2, ypre) == Nature.DIRT) &&
								(gameEngine().level().nature(xpre - 2, ypre - 1) == Nature.DIRT) &&
								(getX() == xpre + 2) && (getY() == ypre - 1) &&
								(tilesBuilt() == tilespre + 3))
							throw new PostConditionError("Step BUILDER: erreur au build");
						else if (waitingpre > 0) {
							if (timeWaiting() != waitingpre - 1)
								throw new PostConditionError("Step BUILDER: decr waiting time");
						}
						else {
							if (!gameEngine().obstacle(xpre - 1, ypre) &&
									!gameEngine().obstacle(xpre - 2, ypre) &&
									!gameEngine().obstacle(xpre - 2, ypre - 1)) {
								if (! (waitingpre == 3))
									throw new PostConditionError("Step BUILDER: new build");
							}
							else
								if (!(tilesBuilt() == 0 && getStatus() == Status.WALK))
									throw new PostConditionError("Step BUILDER: reset to walker");
						}
							
					}
				}
			}
			break;
		case FLOAT:
			break;
		case STOP:
			if(gameEngine().level().nature(this.getX(), this.getY())!=Nature.DIRT 
			&& gameEngine().level().nature(this.getX(), this.getY()-1)!=Nature.DIRT)
				throw new PostConditionError("Error, STOPPER  didn't generate DIRT");	
			if(gameEngine().getLemm(getNumber())!=null)
				throw new PostConditionError("Error, STOPPER didn't die");
			break;
		case BASH:
			if(this.getDir()==Direction.DROITE){
				if(this.gameEngine().level().nature(xpre+1, ypre)==Nature.METAL ||
						this.gameEngine().level().nature(xpre+1, ypre-1)==Nature.METAL
						|| this.gameEngine().level().nature(xpre+1, ypre-2)==Nature.METAL){
					if(this.getStatus()!=Status.WALK)
						throw new PostConditionError("Error, Basher didn't stop with Metal wall in front");
				}
				if(gameEngine().level().nature(xpre+1, ypre)!=Nature.EMPTY ||
						gameEngine().level().nature(xpre+1, ypre-1)!=Nature.EMPTY ||
						gameEngine().level().nature(xpre+1, ypre-2)!=Nature.EMPTY)
					throw new PostConditionError("Error, Basher didn't destroy wall in front");

				if(xpre!=this.getX()-1)
					throw new PostConditionError("Error, Basher didn't move");
			}else{
				if(this.gameEngine().level().nature(xpre-1, ypre)==Nature.METAL ||
						this.gameEngine().level().nature(xpre-1, ypre-1)==Nature.METAL
						|| this.gameEngine().level().nature(xpre-1, ypre-2)==Nature.METAL){
					if(this.getStatus()!=Status.WALK)
						throw new PostConditionError("Error, Basher didn't stop with Metal wall in front");
				}
				if(gameEngine().level().nature(xpre-1, ypre)!=Nature.EMPTY ||
						gameEngine().level().nature(xpre-1, ypre-1)!=Nature.EMPTY ||
						gameEngine().level().nature(xpre-1, ypre-2)!=Nature.EMPTY)
					throw new PostConditionError("Error, Basher didn't destroy wall in front");

				if(xpre!=this.getX()+1)
					throw new PostConditionError("Error, Basher didn't move");
			}
			break;
		case MINER:
			if(!isMiningDown())
				if(gameEngine().level().nature(xpre, ypre+1)!=Nature.METAL){
					if(gameEngine().level().nature(xpre, ypre+1)!=Nature.EMPTY)
						throw new PostConditionError("Error, MINER didn't Mine down");
					if(ypre+2<=15)
						if(gameEngine().level().nature(xpre, ypre+2)!=Nature.METAL){
							if(gameEngine().level().nature(xpre, ypre+2)!=Nature.EMPTY)
								throw new PostConditionError("Error, MINER didn't Mine down");
						}
				}else{
					if(this.getDir()==Direction.DROITE){
						if(gameEngine().level().nature(xpre+1, ypre)!=Nature.METAL){
							if(gameEngine().level().nature(xpre+1, ypre)!=Nature.EMPTY)
								throw new PostConditionError("Error, MINER didn't Mine right");
							if(gameEngine().level().nature(xpre+1, ypre-1)!=Nature.METAL){
								if(gameEngine().level().nature(xpre+1, ypre-1)!=Nature.EMPTY)
									throw new PostConditionError("Error, MINER didn't Mine right");
							}
						}else{
							if(gameEngine().level().nature(xpre-1, ypre)!=Nature.METAL){
								if(gameEngine().level().nature(xpre-1, ypre)!=Nature.EMPTY)
									throw new PostConditionError("Error, MINER didn't Mine left");
								if(gameEngine().level().nature(xpre-1, ypre-1)!=Nature.METAL){
									if(gameEngine().level().nature(xpre-1, ypre-1)!=Nature.EMPTY)
										throw new PostConditionError("Error, MINER didn't Mine left");
								}
							}
						}
					}
				}
			break;
		default:
			break;
		}
		if(isBomber()){
			if(timeExploding()<=timeEx_pre)
				throw new PostConditionError("Counter of explosion of the bomber didn't go up");
			if(timeExploding()>4){
				for(int i=this.getX()-2;i<(this.getX()-2)+5;i++){
					for(int j=this.getY()-1;j<(this.getY()-1)+3;j++){
						if(i>=0 && i<=15 && j>=0 && j<=15)
							if(gameEngine().level().nature(i, j)!=Nature.EMPTY 
							&& gameEngine().level().nature(i, j)!=Nature.METAL)
								throw new PostConditionError("Error, Bomber exploding didn't explode DIRT");
					}
				}
				if(gameEngine().getLemm(getNumber())!=null)
					throw new PostConditionError("Error, Bomber didn't die after explosion");
			}
		}
		// Invariants
		checkInvariants();
	}

	public String toString(){
		return super.toString();
	}
}
