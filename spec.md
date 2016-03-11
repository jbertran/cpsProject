# Level

* __Service__: Level
* __Types__: int, bool, enum Nature{DIRT, METAL, EMPTY}
* __Observators__:
	* height: [Level] -> int
	* width: [Level] -> int
	* editing: [Level] -> bool
	* nature: [Level] * int * int -> Nature
* __Constructors__:
	* init: -> [Level]
* __Operators__:
	* setNature: [Level] *int * int * Nature -> [Level]
		* pre setNature(L, x, y, n) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ editing(L)
	* goPlay: [Level] -> [Level]
	* remove: [Level] * int * int -> [Level]
		* pre remove(L, x, y) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ nature(L, x, y) = DIRT ^ not(editing(L))
	* build: [Level] * int * int -> [Level]
		* pre build(L, x, y) require 0 <= x <= width(L) ^ 0 <= y <= height(L) ^ nature(L, x, y) = EMPTY ^ not(editing(L))
* __Observations__:
	* [invariants]
		* 
	* [init]
	* [goPlay]
	* [remove]
	* [build]
