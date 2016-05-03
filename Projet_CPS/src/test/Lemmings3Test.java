package test;

import contrat.GameEngineContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;
import implem.Joueur;
import implem.Level;
import implem_bad.GameEngineInitBroken;

public class Lemmings3Test extends AbstractLemmingsTest{
	
	//Testing of a bad Init implementation of the Service GameEngine

	@Override
	public void beforeTests(){
		setGameEngine(new GameEngineContrat(new GameEngineInitBroken()));
		setJoueur(new JoueurContrat(new Joueur()));
		setLevel(new LevelContrat(new Level()));
	}
}