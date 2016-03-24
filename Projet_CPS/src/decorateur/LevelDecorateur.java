package decorateur;

import services.ILevel;
import services.Nature;

public class LevelDecorateur implements ILevel{

		ILevel delegates;
		
		public LevelDecorateur(ILevel delegate) {
			this.delegates = delegate;
		}

		@Override
		public int height() {
			return delegates.height();
		}

		@Override
		public int width() {
			return delegates.width();
		}

		@Override
		public boolean editing() {
			return delegates.editing();
		}

		@Override
		public int entree_x() {
			return delegates.entree_x();
		}

		@Override
		public int entree_y() {
			return delegates.entree_y();
		}

		@Override
		public int sortie_x() {
			return delegates.sortie_x();
		}

		@Override
		public int sortie_y() {
			return delegates.sortie_y();
		}

		@Override
		public Nature nature(int x, int y) {
			return delegates.nature(x,y);
		}

		@Override
		public void init(int h, int w) {
			delegates.init(h,w);
			
		}

		@Override
		public void setNature(int x, int y, Nature n) {
			setNature(x,y,n);
			
		}

		@Override
		public void goPlay(int xe, int ye, int xs, int ys) {
			delegates.goPlay(xe, ye, xs, ys);
			
		}

		@Override
		public void remove(int x, int y) {
			delegates.remove(x, y);
			
		}

		@Override
		public void build(int x, int y) {
			delegates.build(x, y);
			
		}
}
