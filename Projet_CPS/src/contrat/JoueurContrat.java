package contrat;

import services.IJoueur;
import decorateur.JoueurDecorateur;

public class JoueurContrat extends JoueurDecorateur{

	public JoueurContrat(IJoueur delegates) {
		super(delegates);
	}

}
