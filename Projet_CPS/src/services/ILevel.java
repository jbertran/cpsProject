package services;

public interface ILevel {
	// OBSERVATORS
	int height();
	int width();
	boolean editing();
	int entree_x();
	int entree_y();
	int sortie_x();
	int sortie_y();
	Nature nature(int x, int y);
	
	// CONSTRUCTORS
	/**
	 * PRE:
	 * 	init(h, w) require h>0 ^ w>0
	 * POST:
	 * 	width() = w; height() = h
	 * 	editing() = true;
	 */
	void init(int h, int w);
	
	// OPERATORS
	/**
	 * PRE: 
	 * 	setNature(x, y, n) require 0 <= x <= width() ^ 0 <= y <= height() ^ editing()
	 * POST:
	 * 	nature(x, y) = n;
	 * 	nature(i, j) = nature(i, j)@pre forall (i, j) != (x, y)
	 */
	void setNature(int x, int y, Nature n);
	
	/**
	 * PRE: 
	 * 	goPlay(xe, ye, xs, ys) require
	 * 		editing(L) ^
	 * 		0 <= xe <= width(L) ^ 0 <= ye <= height(L) ^
	 * 		0 <= xs <= width(L) ^ 0 <= ys <= height(L) ^
	 * 		for i in (0..height())
	 * 			nature(i, 0) == METAL ^ nature(i, width()-1) == METAL
	 * 		for j in (0..width())
	 * 			nature(0, j) == METAL ^ nature(height()-1, j) == METAL
	 * POST:
	 * 	editing() = false;
	 * 	for i in (0..height())
	 * 		nature(i, 0) == METAL ^ nature(i, width()-1) == METAL
	 * 	for j in (0..width(L))
	 * 		nature(0, j) == METAL ^ nature(height()-1, j) == METAL
	 * 	entree_x() == xe;
	 * 	entree_y() == ye;
	 * 	sortie_x() == xs;
	 * 	sortie_y() == ys;
	 */
	void goPlay(int xe, int ye, int xs, int ys);
	
	/**
	 * PRE: 
	 *  remove(x, y) require 0 <= x <= width() ^ 0 <= y <= height() ^ nature(x, y) = DIRT ^ not(editing())
	 * POST:
	 * 	nature(x, y) == EMPTY
	 * 	nature(i, j) == nature(i, j)@pre forall (i, j) != (x, y)
	 */
	void remove(int x, int y);
	
	/**
	 * PRE: 
	 * 	build(x, y) require 0 <= x <= width() ^ 0 <= y <= height() ^ nature(x, y) = EMPTY ^ not(editing())
	 * POST:
	 * 	nature(x, y) == DIRT
	 * 	nature(i, j) == nature(i, j)@pre forall (i, j) != (x, y)
	 */
	void build(int x, int y);
	
	
	
	
	
	
	
	
	/**
	*
	*   Service: Level
	*   Types: int, bool, enum Nature{DIRT, METAL, EMPTY}
	*   Observators:
	*      height: [Level] -> int
	*      width: [Level] -> int
	*      editing: [Level] -> bool
	*      nature: [Level] * int * int -> Nature
	*	   entree_x: [Level] -> int
	*      entree_y: [Level] -> int
	*      sortie_x: [Level] -> int
	*      sortie_y: [Level] -> int
	*  Constructors:
	*      init: -> [Level]
	*  Operators:
	*      setNature: [Level] *int * int * Nature -> [Level]
	*         pre setNature(L, x, y, n) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ editing(L)
	*     goPlay: [Level] -> [Level]
	*     remove: [Level] * int * int -> [Level]
	*         pre remove(L, x, y) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ nature(L, x, y) = DIRT ^ not(editing(L))
	*     build: [Level] * int * int -> [Level]
	*         pre build(L, x, y) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ nature(L, x, y) = EMPTY ^ not(editing(L))
	*/
	
	// OBSERVATIONS 
	/**
	 * [invariants]
	 *  height(L) > 0; width(L) > 0
	 *  
	 *  [init]
	 * 		width(init(h, w)) = w;
	 * 		height(init(h, w)) = h;
	 * 		editing(init(h, w)) = true;
	 * 		entree_x(init(h,w))= null;
	 * 		entree_y(init(h,w))= null;
	 * 		sortie_x(init(h,w))= null;
	 * 		sortie_y(init(h,w))= null;
	 * 		nature(L,x,y)= TERRE;
	 *  
	 *  [setNature]
	 *  	width(setNature(L,x,y))=width(L)
	 *  	height(setNature(L,x,y))=height(L)
	 *  	editing(setNature(L,x,y))=true;
	 *   	entree_x(setNature(L,x,y))= null;
	 * 		entree_y(setNature(L,x,y))= null;
	 * 		sortie_x(setNature(L,x,y))= null;
	 * 		sortie_y(setNature(L,x,y))= null;
	 * 		nature(setNature(L, x, y, n), x, y) = n;
	 * 		nature(setNature(L, x, y, n), i, j) = nature(L, i, j) forall (i, j) != (x, y)
	 *  
	 *  [goPlay]
	 *  	width(goPlay(L))=width(L)
	 *  	height(goPlay(L))=height(L)
	 * 		editing(goPlay(L)) = false;
	 *  	entree_x(goPlay(L, , xe, ye, xs, ys)) == xe;
	 * 		entree_y(goPlay(L, , xe, ye, xs, ys)) == ye;
	 * 		sortie_x(goPlay(L, , xe, ye, xs, ys)) == xs;
	 * 		sortie_y(goPlay(L, , xe, ye, xs, ys)) == ys;
	 * 		for i in (0..height(L))
	 * 			nature(goPlay(L, xe, ye, xs, ys), i, 0) == METAL ^ nature(goPlay(L, xe, ye, xs, ys), i, width(L)-1) == METAL
	 * 		for j in (0..width(L))
	 * 			nature(goPlay(L, xe, ye, xs, ys), 0, j) == METAL ^ nature(goPlay(L, xe, ye, xs, ys), height(L)-1, j) == METAL

	 *  
	 *  
	 *  [remove]
	 *   	width(remove(L,x,y))=width(L)
	 *  	height(remove(L,x,y))=height(L)
	 * 		editing(remove(L,x,y)) = false;
	 *  	entree_x(remove(L,x,y)) = entree_x(L)
	 * 		entree_y(remove(L,x,y)) = entree_y(L)
	 * 		sortie_x(remove(L,x,y)) = sortie_x(L)
	 * 		sortie_y(remove(L,x,y)) = sortie_y(L)
	 * 		nature(remove(L, x, y), x, y) == EMPTY
	 * 		nature(remove(L, x, y), i, j) == nature(L, i, j) forall (i, j) != (x, y)
	 * 
	 *  [build]
	 *  	width(build(L,x,y))=width(L)
	 *  	height(build(L,x,y))=height(L)
	 * 		editing(build(L,x,y)) = false;
	 * 		entree_x(build(L,x,y)) = entree_x(L)
	 * 		entree_y(build(L,x,y)) = entree_y(L)
	 * 		sortie_x(build(L,x,y)) = sortie_x(L)
	 * 		sortie_y(build(L,x,y)) = sortie_y(L)
	 * 		nature(build(L, x, y), x, y) == DIRT
	 * 		nature(build(L, x, y), i, j) == nature(L, i, j) forall (i, j) != (x, y)
	 */
}
