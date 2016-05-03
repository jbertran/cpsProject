package implem_bad;

import services.Nature;
import implem.Level;

public class LevelBrokenInit extends Level{
	
	@Override
	public void init(int h, int w) {
		height = h-1; // should be h
		width = w-1; // should be w
		editing=true;
		cases = new Nature [w][h];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				cases[i][j] = Nature.EMPTY;
	}
}
