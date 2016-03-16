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
	
	// OBSERVATIONS 
	/**
	 * [invariants]
	 *  height() > 0; width() > 0
	 */
}
