Service: level

Types: 

	* int
	* bool
	* enum Nature = {DIRT, METAL, EMPTY}
	
Observators:

	* height: [Level] -> int
	* width: [Level] -> int
	* editing: [Level] -> bool
	* nature: [Level] * int * int -> Nature
		*  pre nature(L, x, y, n) require 0 <= x <= width(L) ^ 0 <= y <= height(L)
	* entree_x: [Level] -> int
	* entree_y: [Level] -> int
	* sortie_x: [Level] -> int
	* sortie_y: [Level] -> int

Constructors:

	* init: int * int -> [Level]
		* pre init(w, h) require h > 0 ^ w > 0

Operators:

	* setNature: [Level] *int * int * Nature -> [Level]
		* pre setNature(L, x, y, n) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ editing(L)
	* goPlay: [Level] -> [Level]
	* remove: [Level] * int * int -> [Level]
		* pre remove(L, x, y) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ nature(L, x, y) = DIRT ^ not(editing(L))
	* build: [Level] * int * int -> [Level]
		* pre build(L, x, y) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ nature(L, x, y) = EMPTY ^ not(editing(L))
	
Observations:

	 * [invariants]
		 * height(L) > 0; width(L) > 0
	 * [init]
		 * width(init(w, h)) = w;
		 * height(init(w, h)) = h;
	 	 * editing(init(w, h)) = true;
	 	 * entree_x(init(w, h))= null;
	 	 * entree_y(init(w, h))= null;
	 	 * sortie_x(init(w, h))= null;
	 	 * sortie_y(init(w, h))= null;
	 	 * \forall 0<=x<w, 0<=y<h: nature(init(w,h), x, y)= DIRT;
	 * [setNature]
		 * width(setNature(L, x, y))=width(L)
		 * height(setNature(L, x, y))=height(L)
		 * editing(setNature(L, x, y))=true;
		 * entree_x(setNature(L, x, y))= null;
		 * entree_y(setNature(L, x, y))= null;
		 * sortie_x(setNature(L, x, y))= null;
		 * sortie_y(setNature(L, x, y))= null;
		 * nature(setNature(L, x, y, n), x, y) = n;
		 * nature(setNature(L, x, y, n), i, j) = nature(L, i, j) forall (i, j) != (x, y)
	 *  [goPlay]
		 * width(goPlay(L))=width(L)
		 * height(goPlay(L))=height(L)
		 * editing(goPlay(L)) = false;
		 * entree_x(goPlay(L, , xe, ye, xs, ys)) == xe;
		 * entree_y(goPlay(L, , xe, ye, xs, ys)) == ye;
		 * sortie_x(goPlay(L, , xe, ye, xs, ys)) == xs;
		 * sortie_y(goPlay(L, , xe, ye, xs, ys)) == ys;
		 * \forall 0 <= x < width(L): 
			 * nature(goPlay(L, xe, ye, xs, ys), i, 0) == METAL ^ nature(goPlay(L, xe, ye, xs, ys), i, width(L)-1) == METAL
		 * \forall 0 <= y < height(L):
			 * nature(goPlay(L, xe, ye, xs, ys), 0, j) == METAL ^ nature(goPlay(L, xe, ye, xs, ys), height(L)-1, j) == METAL
	 *  [remove]
		 * width(remove(L, x, y))=width(L)
		 * height(remove(L, x, y))=height(L)
		 * editing(remove(L, x, y)) = false;
		 * entree_x(remove(L, x, y)) = entree_x(L)
		 * entree_y(remove(L, x, y)) = entree_y(L)
		 * sortie_x(remove(L, x, y)) = sortie_x(L)
		 * sortie_y(remove(L, x, y)) = sortie_y(L)
		 * nature(remove(L, x, y), x, y) == EMPTY
		 * nature(remove(L, x, y), i, j) == nature(L, i, j) forall (i, j) != (x, y)
	 *  [build]
		 * width(build(L, x, y))=width(L)
		 * height(build(L, x, y))=height(L)
		 * editing(build(L, x, y)) = false;
		 * entree_x(build(L, x, y)) = entree_x(L)
		 * entree_y(build(L, x, y)) = entree_y(L)
		 * sortie_x(build(L, x, y)) = sortie_x(L)
		 * sortie_y(build(L, x, y)) = sortie_y(L)
		 * nature(build(L, x, y), x, y) == DIRT
		 * nature(build(L, x, y), i, j) == nature(L, i, j) forall (i, j) != (x, y)
