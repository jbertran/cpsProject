package contrat;

import services.IGameEng;
import decorateur.GameEngineDecorateur;

public class GameEngineContrat extends GameEngineDecorateur{

	public GameEngineContrat(IGameEng delegates) {
		super(delegates);
	}

}
