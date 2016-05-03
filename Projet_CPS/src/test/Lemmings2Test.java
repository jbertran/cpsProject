package test;

import contrat.GameEngineContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;
import implem.GameEngine;
import implem.Joueur;
import implem.Level;

public class Lemmings2Test extends AbstractLemmingsTest{

	@Override
	public void beforeTests(){
		setGameEngine(new GameEngineContrat(new GameEngineBroken()));
		setJoueur(new JoueurContrat(new JoueurBroken()));
		setLevel(new LevelContrat(new LevelBroken()));
	}
}