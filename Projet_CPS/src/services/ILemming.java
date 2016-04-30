package services;

public interface ILemming {
	
    // OBSERVATORS
    int getX();
    int getY();
    int getNumber();
    Direction getDir();
    Status getStatus();
    int timeWaiting();
    int tilesBuilt();
    int timeFalling();
    int timeBashing();
    int timeExploding();
    IGameEng gameEngine();
    boolean isClimber();
    boolean isFloater();
    boolean isMiningDown();
    boolean isBomber();
    // CONSTRUCTORS
    void init(IGameEng gE);
	
    // OPERATORS
    
    /**
     * POST:
     *  if (getDir()@pre == Droite) getDir() == Gauche
     *  if (getDir()@pre == Gauche) getDir() == Droite
     */
    void changeDir();
    
    /**
     * POST:
     *  timeFalling() = i
     */
    void setTimeFalling(int i);
    
    /**
     * POST:
     *  timeWaiting() = i
     */
    void setWaiting(int i);
    
    /**
     * POST:
     *  tilesBuilt() = i
     */
    void setTilesBuilt(int i);
    
    /**
     * POST:
     *  getStatus() == s
     */
    void setStatus(Status s);
    /**
     * POST:
     *   if (getStatus()@pre == WALK)
     *   	if (isClimber())
     *   		if (getDir()@pre == Droite)
     *				if (gameEngine().obstacle(getX()@pre+1, getY()@pre) && 
     *					gameEngine().obstacle(getX()@pre+1, getY()@pre - 1) &&
     *					!gameEngine().obstacle(getX()@pre, getY()@pre-2))
     *					getX() = getX()@pre; getY() = getY()@pre - 1
     *			if (getDir()@pre == Gauche)
     *				if (gameEngine().obstacle(getX()@pre-1, getY()@pre) && 
     *					gameEngine().obstacle(getX()@pre-1, getY()@pre - 1) &&
     *					!gameEngine().obstacle(getX()@pre, getY()@pre-2))
     *					getX() = getX()@pre; getY() = getY()@pre - 1					 
     *      if (!gameEngine().obstacle(getX()@Pre, getY()@Pre + 1) && !isClimber()) 
     *          getgetStatus()@pre() == Tombeur; getX() == getX()@pre; gety() == getY()@pre
     *      else if (getDir()@pre == Droite)
     *          if (!gameEngine().obstacle(getX()@pre + 1, getY()@pre) &&
     *              !gameEngine().obstacle(getX()@pre + 1, getY()@pre - 1))
     *              getX() == getX()@pre + 1; gety() == getY()@pre;
     *          else if (!gameEngine().obstacle(getX()@pre + 1, getY()@pre - 1) &&
     *          		 !gameEngine().obstacle(getX()@pre + 1, getY()@pre - 2))
     *          	getX() == getX()@pre + 1; getY() == getY()@pre - 1
     *          else
     *          	getDir() == Gauche; getX() == getX()@pre; gety() == getY()@pre;
     *      else
     *          if (!gameEngine().obstacle(getX()@pre - 1, getY()@pre) &&
     *              !gameEngine().obstacle(getX()@pre - 1, getY()@pre - 1))
     *             	getX() == getX()@pre - 1; gety() == getY()@pre;
     *          else if (gameEngine().obstacle(getX()@pre - 1, getY()@pre - 1) &&
     *          		 gameEngine().obstacle(getX()@pre - 1, getY()@pre - 2))
     *             	getX() == getX()@pre - 1; getY() == getY()@pre;
     *          else
     *          	getDir() == Gauche; getX() == getX()@pre; gety() == getY()@pre 
     *   else if (getStatus()@pre == Tombeur)
     *   	if (isFloater()@pre)
     *      	if (gameEngine().obstacle(getX()@pre, getY()@pre + 1))
     *      		getStatus() = Marcheur;
     *      		timeFalling() = 0
     *      		isFloater() = false 
     *      		getX() == getX()@pre; 
     *      		gety() == getY()@pre;
     *          else if (timeFalling()@pre % 2 == 0)
     *          	getX() == getX()@pre; getY() == getY()@pre + 1;
     *          	timeFalling() = timeFalling()@pre + 1
     *      else
     *      	if (gameEngine().obstacle(getX()@pre, getY()@pre + 1))
     *      		if (timeFalling() < 8) 
     *          		getStatus() = Marcheur;
     *          		timeFalling() = 0 
     *             		getX() == getX()@pre; 
     *             		gety() == getY()@pre;
     *          	else
     *             		gameEngine().colony()@pre.size() == gameEngine().colony().size() - 1
     *      	else 
     *          	getX() == getX()@pre; getY() == getY()@pre + 1;
     *          	timeFalling() = timeFalling()@pre + 1
     *   else if (getStatus()@pre == BUILDER)
     *   	if tilesBuilt()@pre >= 12
     *   			tilesBuilt() = 0
     *   			timeWaiting() = -1
     *   			getStatus() = WALK
     *   	else
     *   		if getDir()@pre == Droite
     *   			if timeWaiting()@pre == O
     *	   				timeWaiting() = -1;
     *  	 			gameEngine().level().nature(getX()@pre + 1, getY()@pre) = DIRT;
     *  	 			gameEngine().level().nature(getX()@pre + 2, getY()@pre) = DIRT;
     *   				gameEngine().level().nature(getX()@pre + 2, getY()@pre - 1) = DIRT;
     *   				getX() = getX()@pre + 2; getY() = getY()@pre - 1
     *   				tilesBuilt() = tilesBuilt()@pre + 3;
     *   			else if timeWaiting > 0
     *   				timeWaiting() = timeWaiting()@pre - 1
     *   			else 
     *   				if !gameEngine().obstacle(getX()@pre + 1, getY()@pre) &&
     *   					!gameEngine().obstacle(getX()@pre + 2, getY()@pre) &&
     *   					!gameEngine().obstacle(getX()@pre + 2, getY()@pre - 1)
     *   					timeWaiting() = 3
     *   				else
     *   					tilesBuilt() = 0
     *   					timeWaiting() = -1
     *   					getStatus() = WALK
     *   		if getDir()@pre == Gauche
     *   			if timeWaiting()@pre == O
     *	   				timeWaiting() = -1;
     *  	 			gameEngine().level().nature(getX()@pre - 1, getY()@pre) = DIRT;
     *  	 			gameEngine().level().nature(getX()@pre - 2, getY()@pre) = DIRT;
     *   				gameEngine().level().nature(getX()@pre - 2, getY()@pre - 1) = DIRT;
     *   				getX() = getX()@pre - 2; getY() = getY()@pre - 1;
     *   				tilesBuilt() = tilesBuilt()@pre + 3;
     *   			else if timeWaiting()@pre > 0
     *   				timeWaiting() = timeWaiting()@pre - 1
     *   			else 
     *   				if !gameEngine().obstacle(getX()@pre - 1, getY()@pre) &&
     *   					!gameEngine().obstacle(getX()@pre - 2, getY()@pre) &&
     *   					!gameEngine().obstacle(getX()@pre - 2, getY()@pre - 1)
     *   					timeWaiting() = 3
     *   				else
     *   					tilesBuilt() = 0
     *   					timeWaiting() = -1
     *   					getStatus() = WALK
     *   else if (getStatus()@pre == STOP)
     *   	gameEngine().level().nature(getX(), getY()) == DIRT
     *   		&& gameEngine().level().nature(getX(), getY()-1) == Dirt
     *   			&& gameEngine().getLemm(getNumber())==null
     *   
     *   else if (getStatus()@pre == Miner)
     *   	if(!isMiningDown())
     *   			if(gameEngine().level().nature(getX()@pre, getY()@pre+1)@pre!=Nature.METAL)then
     *   				gameEngine().level().nature(getX()@pre, getY()@pre+1)==Nature.EMPTY && getY()==getY()@pre+1
     *   				if(gameEngine().level().nature(getX()@pre, getY()@pre+2)@pre!=Nature.METAL)then
     *   					gameEngine().level().nature(getX()@pre, getY()@pre+2)==Nature.EMPTY && getY()==getY()@pre+2
     *  
     *   	else
	 *			if(getDir()@pre == Droite)
	 *				if(gameEngine().level()nature(getX()@pre+1,getY()@pre)@pre!=Nature.Metal
	 *					&& gameEngine().level()nature(getX()@pre+1,getY()@pre-1)@pre!=Nature.Metal)then
	 *						gameEngine().level()nature(getX()@pre+1,getY()@pre)==Nature.Dirt &&
	 *							gameEngine().level()nature(getX()@pre+1,getY()-1@pre)==Nature.Dirt
	 *			else
	 *				if(gameEngine().level()nature(getX()@pre-1,getY()@pre)@pre!=Nature.Metal
	 *					&& gameEngine().level()nature(getX()@pre-1,getY()@pre-1)@pre!=Nature.Metal)then
	 *						gameEngine().level()nature(getX()@pre-1,getY()@pre)==Nature.Dirt &&
	 *							gameEngine().level()nature(getX()@pre-1,getY()-1@pre)==Nature.Dirt
	 *
     *   else if (getStatus()@pre == BASH)
     *   	if(timeBashing()<19)
     *   		if(getDir()@pre == Droite)
     *   			if (gameEngine().level().nature(getX()@pre+1, getY()@pre)@pre!=Nature.METAL &&
	 *						gameEngine().level().nature(getX()@pre+1, getY()@pre-1)@pre!=Nature.METAL
	 *							&& gameEngine().level().nature(getX()@pre+1, getY()@pre-2)@pre!=Nature.METAL) then
	 *								gameEngine().level().nature(getX()@pre+1, getY()@pre)==Nature.EMPTY &&
	 *									gameEngine().level().nature(getX()@pre+1, getY()@pre-1)==Nature.EMPTY
	 *										&& gameEngine().level().nature(getX()@pre+1, getY()@pre-2)==Nature.EMPTY
	 *					
     *   		else
     *   		if (gameEngine().level().nature(getX()@pre-1, getY()@pre)@pre!=Nature.METAL &&
	 *						gameEngine().level().nature(getX()@pre-1, getY()@pre-1)@pre!=Nature.METAL
	 *							&& gameEngine().level().nature(getX()@pre-1, getY()@pre-2)@pre!=Nature.METAL) then
	 *								gameEngine().level().nature(getX()@pre-1, getY()@pre)==Nature.EMPTY &&
	 *									gameEngine().level().nature(getX()@pre-1, getY()@pre-1)==Nature.EMPTY
	 *										&& gameEngine().level().nature(getX()@pre-1, getY()@pre-2)==Nature.EMPTY
     *   
     *   if(isBomber())
     *   	timeExploding()=timeExploding()@pre+1
     *   	if(timeExploding()>4)
     *   	for(i = getX()-2 to getX()-2)+5)
	 *			for(j=this.getY()-1 to j<(this.getY()-1)+3)
	 *						if(i>=0 && i<=15 && j>=0 && j<=15)
	 *							if(gameEngine().level().nature(i, j)@pre==Nature.METAL)
	 *								gameEngine().level().nature(i, j)==Nature.METAL)
	 *							else
	 *								gameEngine().level().nature(i, j)==Nature.EMPTY)	 
	 * 					
     */
    void step();
    
    
    /**
     *  POST :
     * 	isBomber() = true;
     */
	void setBomber();

   /**
    * POST:
    *  isFloater() = b; 
    */
	void setFloater(boolean b);

	/**
	 * POST:
	 * 	isClimber() = true
	 */
    void setClimber();
    
    /**
	*
	*   Service: Lemming
	*   Types: int, bool, enum Status{WALK, FALL, BUILD, FLOAT, BOMB, STOP, BASH},enum Direction{DROITIER,GAUCHER}
	*   Observators:
	*      getX: [Lemming] -> int
	*      getY: [Lemming] -> int
	*	   getNumber: [Lemming] ->int
	*      getDir: [Lemming] -> Direction
    *      getStatus: [Lemming] -> Status
    *      isBomber: [Lemming] -> bool
    *      isFloater: [Lemming] -> bool
    *      isClimber: [Lemming] -> bool
    *      tilesBuilt: [Lemming] -> int
    *      timeWaiting: [Lemming] -> int
    *      timeFalling: [Lemming] -> int
    *      gameEngine: [Lemming] -> [gameEngine];
	*  Constructors:
	*      init: [gameEngine] -> [Lemming]
	*  Operators:
	*      changeDir: [Lemming] -> [Lemming]
	*      setStatus: [Lemming] * Status -> [Lemming]
	*      setTimeFalling: [Lemming] * int -> [Lemming]
	*      setTimeWaiting: [Lemming] * int -> [Lemming]
	*      setBomber: [Lemming] -> [Lemming]
	*      setClimber: [Lemming] -> [Lemming]
	*      setFloater: [Lemming] * bool -> [Lemming]
	*      step: [Lemming] -> [Lemming]
	*    
	*/
    /**
	 * [invariants]
	 * 	
	 * [init]
	 * 	getX(init(Le,G))=gameEngine::entree_X()
	 *  getY(init(Le,G))=gameEngine::entree_Y()
	 *  getDir(init(Le,G))=DROITIER;
	 *  getStatus(init(Le,G))=TOMBEUR;
	 *  isBomber(init(Le,G))=false
	 *  isFloater(init(Le,G))=false
	 *  timeWaiting(init(Le,G))=-1
	 *  tilesBuilt(init(Le,G))=0
	 *  
	 * [changeDir]
	 * 	getX(changeDir(Le))=getX(Le);
	 *  getY(changeDir(Le))=getY(Le);
	 *   if(getDir(Le)==DROITIER)then getDir(changeDir(Le))=GAUCHER else getDir(changeDir(Le))=DROITIER;
	 *  getStatus(changeDir(Le))=getStatus(Le);
	 *  timeFalling(changeDir(Le))=timeFalling(Le);
	 *  
	 * [setStatus]
	 * 	getX(setStatus(Le,s))=getX(Le);
	 *  getY(setStatus(Le,s))=getY(Le);
	 *  getDir(setStatus(Le,s))= getDir(Le);
	 *  getStatus(setStatus(Le,s))=s;
	 *  timeFalling(setStatus(Le,s))=timeFalling(Le);
	 *  
	 * [setBomber]
	 * 	getX(setBomber(Le))=getX(Le);
	 *  getY(setBomber(Le))=getY(Le);
	 *  getDir(setBomber(Le))= getDir(Le);
	 *  getStatus(setBomber(Le))=s;
	 *  timeFalling(setBomber(Le))=timeFalling(Le);
	 * 
	 * [step]
	 *  
	 * 
	 */
}
