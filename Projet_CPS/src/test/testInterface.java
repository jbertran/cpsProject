package test;

import implem.GameEngine;
import implem.Joueur;
import implem.Level;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import services.IGameEng;
import services.IJoueur;
import services.ILevel;
import services.Nature;
import services.Status;
import view.Terrain;
import view.Token;
import contrat.GameEngineContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;

public class testInterface {
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
		/****************** INTERFACE ******************/
		JFrame window=new JFrame();
		window.setSize(800, 600);
		JPanel root=new JPanel();
		Terrain terrain=new Terrain(gameEng);
		window.setLayout(new GridLayout(1,1));
		window.add(root);
		root.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 400;  
		c.ipadx = 400;
		c.weightx = 0.0;
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 1;
		root.add(terrain, c);
		root.add(new Token(joueur));
		window.setVisible(true);
		root.setVisible(true);
		terrain.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/************************************************************/
		while(!gameEng.gameOver()) {
			/******** STEP *****/
			System.out.println("------------------------------------");
			System.out.println(gameEng);
			System.out.println("------------------------------------");
			System.out.println("Spawned : "+gameEng.spawned()+"/"+gameEng.sizeColony()+
					" | Saved : "+gameEng.nbSauves()+" | Tour : "+gameEng.tours());
			System.out.println("------------------------------------");
			System.out.println("************************************");
			gameEng.step();
			root.repaint();
			terrain.repaint();
			window.repaint();
			cpt++;
			skip--;
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
