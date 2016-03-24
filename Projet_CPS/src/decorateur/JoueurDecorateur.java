package decorateur;

import services.IGameEng;
import services.IJoueur;
import services.Status;

public class JoueurDecorateur implements IJoueur{
	IJoueur delegates;
	
	public JoueurDecorateur(IJoueur delegates){
		this.delegates=delegates;
	}

	@Override
	public int nbTokens(Status s) {
		return delegates.nbTokens(s);
	}

	@Override
	public void init(IGameEng gE) {
		delegates.init(gE);
	}

	@Override
	public void spendToken(int lemm, Status s) {
		delegates.spendToken(lemm, s);
	}

	@Override
	public void reset() {
		delegates.reset();	
	}

}
