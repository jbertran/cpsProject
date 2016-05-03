package test;

import contrat.GameEngineContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;
import implem.Joueur;
import implem.Level;
import implem_bad.GameEngineInitBroken;
import implem_bad.GameEngineLemmingBroken;

public class Lemmings4Test extends AbstractLemmingsTest{
	
	//Testing of a bad Init implementation of the Service Lemming

	@Override
	public void beforeTests(){
		setGameEngine(new GameEngineContrat(new GameEngineLemmingBroken()));
		setJoueur(new JoueurContrat(new Joueur()));
		setLevel(new LevelContrat(new Level()));
	}
}