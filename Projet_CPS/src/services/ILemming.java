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
}
