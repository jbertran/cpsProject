package decorateur;

import services.ILevel;
import services.Nature;

public class LevelDecorateur implements ILevel{

		ILevel delegate;
		
		public LevelDecorateur(ILevel delegate) {
			this.delegate = delegate;
		}

		@Override
		public int height() {
			return delegate.height();
		}

		@Override
		public int width() {
			return delegate.width();
		}

		@Override
		public boolean editing() {
			return delegate.editing();
		}

		@Override
		public int entree_x() {
			return delegate.entree_x();
		}

		@Override
		public int entree_y() {
			return delegate.entree_y();
		}

		@Override
		public int sortie_x() {
			return delegate.sortie_x();
		}

		@Override
		public int sortie_y() {
			return delegate.sortie_y();
		}

		@Override
		public Nature nature(int x, int y) {
			return delegate.nature(x,y);
		}

		@Override
		public void init(int h, int w) {
			delegate.init(h,w);
			
		}

		@Override
		public void setNature(int x, int y, Nature n) {
			setNature(x,y,n);
			
		}

		@Override
		public void goPlay(int xe, int ye, int xs, int ys) {
			delegate.goPlay(xe, ye, xs, ys);
			
		}

		@Override
		public void remove(int x, int y) {
			delegate.remove(x, y);
			
		}

		@Override
		public void build(int x, int y) {
			delegate.build(x, y);
			
		}
}
