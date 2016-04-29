package decorateur;

import services.ILevel;
import services.Nature;

public class LevelDecorateur implements ILevel{

		ILevel delegate;
		
		public LevelDecorateur(ILevel delegate) {
			this.delegate = delegate;
		}

		
		public int height() {
			return delegate.height();
		}

		
		public int width() {
			return delegate.width();
		}

		
		public boolean editing() {
			return delegate.editing();
		}

		
		public int entree_x() {
			return delegate.entree_x();
		}

		
		public int entree_y() {
			return delegate.entree_y();
		}

		
		public int sortie_x() {
			return delegate.sortie_x();
		}

		
		public int sortie_y() {
			return delegate.sortie_y();
		}

		
		public Nature nature(int x, int y) {
			return delegate.nature(x,y);
		}

		
		public void init(int h, int w) {
			delegate.init(h,w);
			
		}

		
		public void setNature(int x, int y, Nature n) {
			delegate.setNature(x,y,n);
			
		}

		
		public void goPlay(int xe, int ye, int xs, int ys) {
			delegate.goPlay(xe, ye, xs, ys);
			
		}

		
		public void remove(int x, int y) {
			delegate.remove(x, y);
			
		}

		
		public void build(int x, int y) {
			delegate.build(x, y);
			
		}
		
		public String toString(){
			return delegate.toString();
		}
}
