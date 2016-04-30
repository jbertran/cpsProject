package test;

import implem.GameEngine;
import implem.Joueur;
import implem.Level;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import services.IGameEng;
import services.IJoueur;
import services.ILevel;
import services.Nature;
import services.Status;
import view.Settings;
import view.Terrain;
import view.Token;
import contrat.GameEngineContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;

public class testInterfaceSettings {
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
		/*IMPLEM*/
		IJoueur joueur=new Joueur();
		ILevel level=new Level();
		IGameEng gameEng=new GameEngine();
		/*CONTRAT*/
		joueur=new JoueurContrat(joueur);
		level=new LevelContrat(level);
		gameEng=new GameEngineContrat(gameEng);
		/****************** INTERFACE ******************/
		JFrame window=new JFrame();
		window.setSize(1000, 800);
		JPanel root=new JPanel();
		Terrain terrain=new Terrain(gameEng);
		terrain.setSize(800,800);
		window.setLayout(new BorderLayout());
		//window.setResizable(false);
		window.add(root);
		root.setLayout(new BorderLayout());
		root.add(terrain, BorderLayout.CENTER);
		root.add(new Token(joueur),BorderLayout.SOUTH);
		Settings settings=new Settings(gameEng,level,joueur, root, terrain, window);
		root.add(settings,BorderLayout.EAST);
		window.setVisible(true);
		root.setVisible(true);
		terrain.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/************************************************************/

	}

}