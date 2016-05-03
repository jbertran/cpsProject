package test;

import contrat.GameEngineContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;
import implem.GameEngine;
import implem.Joueur;
import implem_bad.LevelBrokenInit;

public class Lemmings2Test extends AbstractLemmingsTest{
	
	//Testing of a bad Init implementation of the Service Level

	@Override
	public void beforeTests(){
		setGameEngine(new GameEngineContrat(new GameEngine()));
		setJoueur(new JoueurContrat(new Joueur()));
		setLevel(new LevelContrat(new LevelBrokenInit()));
	}
}