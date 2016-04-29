package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import contrat.GameEngineContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;
import implem.GameEngine;
import implem.Joueur;
import implem.Level;
import services.IGameEng;
import services.IJoueur;
import services.ILevel;
import services.Nature;
import services.Status;

public class testMain {
	public static Status StringToStatus(String n){
		switch(n){
		case "WALK": return Status.WALK;
		case "FALL": return Status.FALL;
		case "BUILD": return Status.BUILD;
		case "FLOAT": return Status.FLOAT;
		case "BOMB": return Status.BOMB;
		case "STOP": return Status.STOP;
		case "BASH": return Status.BASH;
		case "MINER": return Status.WALK;
		default:
			return null;
		}
		
	}

	public static void main (String [] args) throws IOException{
		int SPEED = 1000;
		int HEIGHT = 10;
		int WIDTH = 30;

		int SPAWNSPEED = 4;
		int SIZECOLONY = 8;

		int ENTREE_X = 1;
		int ENTREE_Y = 1;

		int SORTIE_X=25;
		int SORTIE_Y=3;

		int cpt=0;

		int skip=SPAWNSPEED;

		IJoueur joueur=new Joueur();
		ILevel level=new Level();
		IGameEng gameEng=new GameEngine();
		joueur=new JoueurContrat(joueur);
		level=new LevelContrat(level);
		gameEng=new GameEngineContrat(gameEng);
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
			System.out.println("------------------------------------");
			System.out.println(gameEng);
			System.out.println("------------------------------------");
			System.out.println("Spawned : "+gameEng.spawned()+"/"+gameEng.sizeColony()+
					" | Saved : "+gameEng.nbSauves()+" | Tour : "+gameEng.tours());
			System.out.println("------------------------------------");
			System.out.println("************************************");
			if(skip<1){
//				System.out.println("Enter Command : [Lemming-Number] [Status] or [Number-of-step-to-skip] SKIP");
//				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
//				System.out.println("before");
//				String inputLine = bufferReader.readLine();
//				System.out.println("after");
//				String tab[]=inputLine.split(" ");
//				int n = Integer.parseInt(tab[0]);
//				Status x = StringToStatus(tab[1]);
//				if(x!=null)
//				joueur.spendToken(n, x);
//				else
//					skip=n;
			}
			if(cpt==9)
				joueur.spendToken(0, Status.BOMB);
			gameEng.step();
			cpt++;
//			skip--;
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
