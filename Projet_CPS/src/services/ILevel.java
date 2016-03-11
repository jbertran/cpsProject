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
	 * 	width(init(h, w)) = w; height(init(h, w)) = h
	 * 	editing(init(h, w)) = true;
	 */
	void init(int h, int w);
	
	// OPERATORS
	/**
	 * PRE: 
	 * 	setNature(L, x, y, n) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ editing(L)
	 * POST:
	 * 	nature(setNature(L, x, y, n), x, y) = n;
	 * 	nature(setNature(L, x, y, n), i, j) = nature(L, i, j) forall (i, j) != (x, y)
	 */
	void setNature(int x, int y, Nature n);
	
	/**
	 * PRE: 
	 * 	goPlay(L, xe, ye, xs, ys) require
	 * 		editing(L) ^
	 * 		0 <= xe <= width(L) ^ 0 <= ye <= height(L) ^
	 * 		0 <= xs <= width(L) ^ 0 <= ys <= height(L) ^
	 * 		for i in (0..height(L))
	 * 			nature(L, i, 0) == METAL ^ nature(L, i, width(L)-1) == METAL
	 * 		for j in (0..width(L))
	 * 			nature(L, 0, j) == METAL ^ nature(L, height(L)-1, j) == METAL
	 * POST:
	 * 	editing(goPlay(L)) = false;
	 * 	for i in (0..height(L))
	 * 		nature(goPlay(L, xe, ye, xs, ys), i, 0) == METAL ^ nature(goPlay(L, xe, ye, xs, ys), i, width(L)-1) == METAL
	 * 	for j in (0..width(L))
	 * 		nature(goPlay(L, xe, ye, xs, ys), 0, j) == METAL ^ nature(goPlay(L, xe, ye, xs, ys), height(L)-1, j) == METAL
	 * 	entree_x(goPlay(L, , xe, ye, xs, ys)) == xe;
	 * 	entree_y(goPlay(L, , xe, ye, xs, ys)) == ye;
	 * 	sortie_x(goPlay(L, , xe, ye, xs, ys)) == xs;
	 * 	sortie_y(goPlay(L, , xe, ye, xs, ys)) == ys;
	 */
	void goPlay(int xe, int ye, int xs, int ys);
	
	/**
	 * PRE: 
	 *  remove(L, x, y) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ nature(L, x, y) = DIRT ^ not(editing(L))
	 * POST:
	 * 	nature(remove(L, x, y), x, y) == EMPTY
	 * 	nature(remove(L, x, y), i, j) == nature(L, i, j) forall (i, j) != (x, y)
	 */
	void remove(int x, int y);
	
	/**
	 * PRE: 
	 * 	build(L, x, y) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ nature(L, x, y) = EMPTY ^ not(editing(L))
	 * POST:
	 * 	nature(build(L, x, y), x, y) == DIRT
	 * 	nature(build(L, x, y), i, j) == nature(L, i, j) forall (i, j) != (x, y)
	 */
	void build(int x, int y);
	
	// OBSERVATIONS 
	/**
	 * [invariants]
	 *  height(L) > 0; width(L) > 0
	 */
}
