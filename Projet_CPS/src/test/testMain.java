package test;

import implem.GameEngine;
import implem.Joueur;
import implem.Level;
import services.IGameEng;
import services.IJoueur;
import services.ILevel;

public class testMain {

	
	public static void main (String [] args){
		IJoueur joueur=new Joueur();
		ILevel level=new Level();
		IGameEng gameEng=new GameEngine();
		
		joueur.init(gameEng);
		level.init(5, 30);
		gameEng.init(level, 8, 2);
		level.goPlay(3, 3, 25, 3);
		while(!gameEng.gameOver()) {
			System.out.println(gameEng);
			gameEng.step();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(gameEng.score());
	}
	
}
