package implem;

import services.ILevel;
import services.Nature;

public class Level implements ILevel {

	int height, width;
	boolean editing;
	int e_x, e_y, s_x, s_y;
	Nature [][] cases;
	
	public Level() {}
	
	@Override
	public int height() {
		return height;
	}

	@Override
	public int width() {
		return width;
	}

	@Override
	public boolean editing() {
		return editing;
	}

	@Override
	public int entree_x() {
		return e_x;
	}

	@Override
	public int entree_y() {
		return e_y;
	}

	@Override
	public int sortie_x() {
		return s_x;
	}

	@Override
	public int sortie_y() {
		return s_y;
	}

	@Override
	public Nature nature(int x, int y) {
		return cases[x][y];
	}

	@Override
	public void init(int h, int w) {
		height = h;
		width = w;
		cases = new Nature [w][h];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				cases[i][j] = Nature.EMPTY;
	}

	@Override
	public void setNature(int x, int y, Nature n) {
		cases[x][y] = n;
	}

	@Override
	public void goPlay(int xe, int ye, int xs, int ys) {
		e_x = xe;
		e_y = ye;
		s_x = xs;
		s_y = ys;
		editing = true;
		for (int i = 0; i < width; i++){
			cases[i][0] = Nature.METAL;
			cases[i][height-1] = Nature.METAL;
		}
		for (int i = 0; i < height; i++){
			cases[0][i] = Nature.METAL;
			cases[width-1][i] = Nature.METAL;
		}
	}

	@Override
	public void remove(int x, int y) {
		cases[x][y] = Nature.EMPTY;
	}

	@Override
	public void build(int x, int y) {
		cases[x][y] = Nature.DIRT;
	}

}
