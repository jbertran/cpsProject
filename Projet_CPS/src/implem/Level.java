package implem;

import services.ILevel;
import services.Nature;

public class Level implements ILevel {

	protected int height;
	protected int width;
	protected boolean editing;
	protected int e_x, e_y, s_x, s_y;
	protected Nature [][] cases;
	
	public Level() {}
	
	
	public int height() {
		return height;
	}

	
	public int width() {
		return width;
	}

	
	public boolean editing() {
		return editing;
	}

	
	public int entree_x() {
		return e_x;
	}

	
	public int entree_y() {
		return e_y;
	}

	
	public int sortie_x() {
		return s_x;
	}

	
	public int sortie_y() {
		return s_y;
	}

	
	public Nature nature(int x, int y) {
		return cases[x][y];
	}

	
	public void init(int h, int w) {
		height = h;
		width = w;
		editing=true;
		cases = new Nature [w][h];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				cases[i][j] = Nature.EMPTY;
	}

	
	public void setNature(int x, int y, Nature n) {
		cases[x][y] = n;
	}

	
	public void goPlay(int xe, int ye, int xs, int ys) {
		e_x = xe;
		e_y = ye;
		s_x = xs;
		s_y = ys;
		editing = false;
		for (int i = 0; i < width; i++){
			cases[i][0] = Nature.METAL;
			cases[i][height-1] = Nature.METAL;
		}
		for (int i = 0; i < height; i++){
			cases[0][i] = Nature.METAL;
			cases[width-1][i] = Nature.METAL;
		}
	}

	
	public void remove(int x, int y) {
		cases[x][y] = Nature.EMPTY;
	}

	
	public void build(int x, int y) {
		cases[x][y] = Nature.DIRT;
	}

}
