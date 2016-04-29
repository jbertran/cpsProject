package services;

public interface ILemming {
	
    // OBSERVATORS
    int getX();
    int getY();
    int getNumber();
    Direction getDir();
    Status getStatus();
    int timeFalling();
    int fallingRate();
    int timeBashing();
    int timeExploding();
    IGameEng gameEngine();
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
     *  fallingRate() = i 
     */
    void setFallingRate(int i);
    
    /**
     * POST:
     *  getStatus() == s
     */
    void setStatus(Status s);
    /**
     * POST:
     *   if (Status == Marcheur)
     *      if (!gameEngine().obstacle(getX()@Pre, getY()@Pre + 1)) 
     *          getStatus() == Tombeur; getX() == getX()@pre; gety() == getY()@pre
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
     *   else if (Status == Tombeur)
     *      if (gameEngine().obstacle(getX()@pre, getY()@pre + 1)) 
     *          if (timeFalling() < 8) 
     *             Status = Marcheur; getX() == getX()@pre; gety() == getY()@pre;
     *          else
     *             gameEngine().colony()@pre.size() == gameEngine().colony().size() - 1
     *      else
     *          getX() == getX()@pre; getY() == getY()@pre + 1;
     *   else if (Status == BUILDER)
     *   	
     *   else if (Status == FLOATER)
     *   else if (Status == STOP)
     *   	gameEngine().level().nature(getX(), getY()) == DIRT
     *   		&& gameEngine().level().nature(getX(), getY()-1) == Dirt
     *   			&& gameEngine().getLemm(getNumber())==null
     *   
     *   else if (Status == Miner)
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
     *   else if (Status == BASH)
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
     */
    void step();
    
    
    /**
     *  POST :
     * 	isBomber()==true;
     */
	void setBomber();

    
   


    
    
    /**
	*
	*   Service: Lemming
	*   Types: int, bool, enum Status{WALK, FALL, BUILD, FLOAT, BOMB, STOP, BASH},enum Direction{DROITIER,GAUCHER}
	*   Observators:
	*      getX: [Lemming] -> int
	*      getY: [Lemming] -> int
	*	   getNumber: [Lemming] ->int
	*      getDir(): [Lemming] -> Direction
    *      getStatus(): [Lemming] -> Status
    *      timeFalling(): [Lemming] -> int
    *      gameEngine(): [Lemming] -> [gameEngine];
	*  Constructors:
	*      init: [gameEngine] -> [Lemming]
	*  Operators:
	*      changeDir: [Lemming] -> [Lemming]
	*      setStatus: [Lemming] * Status -> [Lemming]
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
	 *  timeFalling(init(Le,G))=0;
	 *  fallingRate(init(Le,G)) = 1
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
	 * [step]
	 * 
	 * 
	 */
}
