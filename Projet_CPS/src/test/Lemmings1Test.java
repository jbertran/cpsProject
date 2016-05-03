package test;

import contrat.GameEngineContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;
import implem.GameEngine;
import implem.Joueur;
import implem.Level;

public class Lemmings1Test extends AbstractLemmingsTest{

	@Override
	public void beforeTests(){
		setGameEngine(new GameEngineContrat(new GameEngine()));
		setJoueur(new JoueurContrat(new Joueur()));
		setLevel(new LevelContrat(new Level()));
	}
}
