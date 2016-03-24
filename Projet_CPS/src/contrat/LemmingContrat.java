package contrat;

import services.ILemming;
import decorateur.LemmingDecorateur;

public class LemmingContrat extends LemmingDecorateur{

	public LemmingContrat(ILemming delegates) {
		super(delegates);
	}

}
