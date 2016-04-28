package test;

import implem.GameEngine;
import implem.Joueur;
import implem.Level;
import services.IGameEng;
import services.IJoueur;
import services.ILevel;
import services.Nature;
import services.Status;

public class testMain {

	
	public static void main (String [] args){
		int SPEED = 1300;
		int HEIGHT = 10;
		int WIDTH = 30;
		
		int SPAWNSPEED = 4;
		int SIZECOLONY = 8;
		
		int ENTREE_X = 1;
		int ENTREE_Y = 1;
		
		int SORTIE_X=25;
		int SORTIE_Y=3;
		
		int cpt=0;
		IJoueur joueur=new Joueur();
		ILevel level=new Level();
		IGameEng gameEng=new GameEngine();
		
		joueur.init(gameEng);
		level.init(HEIGHT, WIDTH);
		for(int i=0;i<HEIGHT;i++){
			for(int j=0;j<WIDTH;j++){
				if(i>3 || ((j < 22) && (j > 10)))
				level.setNature(j, i, Nature.DIRT);
			}
		}
		gameEng.init(level, SIZECOLONY, SPAWNSPEED);
		level.goPlay(ENTREE_X, ENTREE_Y, SORTIE_X, SORTIE_Y);
		while(!gameEng.gameOver()) {
			/******** STEP *****/
			System.out.println(gameEng);
			gameEng.step();
			cpt++;
			if(cpt==3)
				joueur.spendToken(0, Status.BOMB);
			if(cpt==6)
				joueur.spendToken(1, Status.BOMB);
			/******************/
			try {
				Thread.sleep(SPEED);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(gameEng.score());
	}
	
}
