package services;

public interface ILemming {
	
    // OBSERVATORS
    int getX();
    int getY();
    int getNumber();
    Direction getDir();
    Status getStatus();
    int timeFalling();
    IGameEng gameEngine();
	
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
     *  getStatus() == s
     */
    void setStatus(Status s);
    /**
     * POST:
     *   if (Status == Marcheur)
     *      if (gameEngine().level().nature(getX()@Pre, getY()@Pre + 1) == VIDE) 
     *          getStatus() == Tombeur; getX() == getX()@pre; gety() == getY()@pre
     *      else if (getDir()@pre == Droite)
     *          if (gameEngine().level().nature(getX()@pre + 1, getY()@pre) != VIDE &&
     *              gameEngine().level().nature(getX()@pre + 1, getY()@pre - 1) != VIDE)
     *             getDir() == Gauche; getX() == getX()@pre; gety() == getY()@pre;
     *          else
     *             getX() == getX()@pre + 1; gety() == getY()@pre; 
     *      else
     *          if (gameEngine().level().nature(getX()@pre - 1, getY()@pre) != VIDE &&
     *              gameEngine().level().nature(getX()@pre - 1, getY()@pre - 1) != VIDE)
     *             getDir() == Gauche; getX() == getX()@pre; gety() == getY()@pre
     *          else
     *             getX() == getX()@pre - 1; getY() == getY()@pre; 
     *   else if (Status == Tombeur)
     *      if (gameEngine().level().nature(getX()@pre, getY()@pre + 1) != VIDE) 
     *          if (timeFalling() < 8) 
     *             Status = Marcheur; getX() == getX()@pre; gety() == getY()@pre;
     *          else
     *             gameEngine().colony()@pre.size() == gameEngine().colony().size() - 1
     *      else
     *          getX() == getX()@pre; getY() == getY()@pre + 1;
     */
    void step();
    
    /**
	 * [invariants]
	 * 
	 * [init]
	 * 	getX(init(Le,G))=gameEngine::entree_X()
	 *  getY(init(Le,G))=gameEngine::entree_Y()
	 *  getDir(init(Le,G))=DROITIER;
	 *  getStatus(init(Le,G))=TOMBEUR;
	 *  timeFalling(init(Le,G))=0;
	 *  
	 * [changeDir]
	 * 	getX(changeDir(Le))=getX(Le);
	 *  getY(changeDir(Le))=getY(Le);
	 *  getDir(changeDir(Le))!= getDir(Le);
	 *  getStatus(changeDir(Le))=getStatus(Le);
	 *  timeFalling(changeDir(Le))=timeFalling(Le);
	 *  
	 * [setStatus]
	 * 	getX(setStatus(Le,s))=getX(Le);
	 *  getY(setStatus(Le,s))=getY(Le);
	 *  getDir(setStatus(Le,s))!= getDir(Le);
	 *  getStatus(setStatus(Le,s))=s;
	 *  timeFalling(setStatus(Le,s))=timeFalling(Le);
	 *  
	 * [step]
	 * 
	 * 
	 */
}
