package decorateur;

import services.IGameEng;
import services.IJoueur;
import services.Status;

public class JoueurDecorateur implements IJoueur{
	IJoueur delegate;
	
	public JoueurDecorateur(IJoueur delegates){
		this.delegate=delegates;
	}

	
	public int nbTokens(Status s) {
		return delegate.nbTokens(s);
	}

	
	public void init(IGameEng gE) {
		delegate.init(gE);
	}

	
	public void spendToken(int lemm, Status s) {
		delegate.spendToken(lemm, s);
	}

	
	public void reset() {
		delegate.reset();	
	}

	
	public IGameEng gameEngine() {
		return delegate.gameEngine();
	}
	public String toString(){
		return delegate.toString();
	}
}
