package decorateur;

import services.IGameEng;
import services.IJoueur;
import services.Status;

public class JoueurDecorateur implements IJoueur{
	IJoueur delegate;
	
	public JoueurDecorateur(IJoueur delegates){
		this.delegate=delegates;
	}

	@Override
	public int nbTokens(Status s) {
		return delegate.nbTokens(s);
	}

	@Override
	public void init(IGameEng gE) {
		delegate.init(gE);
	}

	@Override
	public void spendToken(int lemm, Status s) {
		delegate.spendToken(lemm, s);
	}

	@Override
	public void reset() {
		delegate.reset();	
	}

	@Override
	public IGameEng gameEngine() {
		return delegate.gameEngine();
	}

}
