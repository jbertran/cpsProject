package contrat;

import services.ILevel;
import decorateur.LevelDecorateur;

public class LevelContrat extends LevelDecorateur{

	public LevelContrat(ILevel delegate) {
		super(delegate);
	}

}
