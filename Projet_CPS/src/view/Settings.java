package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import implem.GameEngine;
import implem.Joueur;
import implem.Level;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import contrat.GameEngineContrat;
import contrat.JoueurContrat;
import contrat.LevelContrat;
import services.IGameEng;
import services.IJoueur;
import services.ILevel;
import services.Status;

public class Settings extends JPanel{
	boolean dim=false;
	Settings x=this;
	IGameEng ge;
	ILevel level;
	IJoueur joueur;
	JTextField WIDTH=new JTextField();
	JTextField HEIGHT=new JTextField();
	JTextField SPAWNSPEED=new JTextField();
	JTextField SIZECOLONY=new JTextField();

	JTextField ENTREE_X=new JTextField();
	JTextField ENTREE_Y=new JTextField();

	JTextField SORTIE_X=new JTextField();
	JTextField SORTIE_Y=new JTextField();

	JTextField SPEED=new JTextField();
	JButton button;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	Terrain terrain;
	JPanel root;
	JFrame window;
    Token token;
    JLabel score;


	public Settings(IGameEng gameEng,ILevel level,Token token, IJoueur joueur,JPanel root,Terrain terrain,JFrame window){
		this.root=root;
		this.token=token;
		this.terrain=terrain;
		this.window=window;
		this.ge=gameEng;
		this.level=level;
		this.joueur=joueur;
		WIDTH.setText("30");
		HEIGHT.setText("13");
		SPAWNSPEED.setText("3");
		SIZECOLONY.setText("4");
		ENTREE_X.setText("2");
		ENTREE_Y.setText("2");
		SORTIE_X.setText("25");
		SORTIE_Y.setText("2");
		SPEED.setText("1");
		this.setLayout(new GridLayout(30,1));
		score=new JLabel("Spawned : -/ - | Saved : - | Tour : -");
		this.add(score);
		this.add(new JLabel("WIDTH"));
		this.add(WIDTH);
		this.add(new JLabel("HEIGHT"));
		this.add(HEIGHT);
		this.add(new JLabel("SPAWNSPEED"));
		this.add(SPAWNSPEED);
		this.add(new JLabel("SIZECOLONY"));
		this.add(SIZECOLONY);
		this.add(new JLabel("ENTREE_X"));
		this.add(ENTREE_X);
		this.add(new JLabel("ENTREE_Y"));
		this.add(ENTREE_Y);
		this.add(new JLabel("SORTIE_X"));
		this.add(SORTIE_X);
		this.add(new JLabel("SORTIE_Y"));
		this.add(SORTIE_Y);
		init();
		this.add(button);
		this.add(button2);
		this.add(new JLabel("Modify Spawn Speed"));
		this.add(SPEED);
		this.add(button3);
		this.add(button4);
		this.add(button5);
		this.add(new JLabel("1-Cliquer en premier sur : Init"));
		this.add(new JLabel("2-Cliquer sur les cases"));
		this.add(new JLabel("Clic Gauche : DIRT "));
		this.add(new JLabel("Clic Droit : METAL "));
		this.add(new JLabel("Clic MILIEU : EMPTY "));
		this.add(new JLabel("3-Cliquer sur Play !"));

		joueur.init(ge);


	}


	public void init(){
		button=new JButton("Init Terrain");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				score.setText("Spawned : -/ - | Saved : - | Tour : -");
				button.setVisible(false);
				button2.setVisible(true);
				level.init(Integer.parseInt(HEIGHT.getText()), Integer.parseInt(WIDTH.getText()));
				ge.init(level, Integer.parseInt(SIZECOLONY.getText()), Integer.parseInt(SPAWNSPEED.getText()));
				terrain.init();
				root.repaint();
				terrain.repaint();
				window.repaint();
				dim=true;
				window.setSize(root.getSize().width,root.getSize().height);
			}
		});
		button2=new JButton("Lancer Jeu");
		button2.setVisible(false);
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				button.setVisible(false);
				button3.setVisible(true);
				button4.setVisible(true);
				button5.setVisible(true);
				SPAWNSPEED.setVisible(true);
				if(!dim){
					level.init(Integer.parseInt(HEIGHT.getText()), Integer.parseInt(WIDTH.getText()));
					ge.init(level, Integer.parseInt(SIZECOLONY.getText()), Integer.parseInt(SPAWNSPEED.getText()));
					terrain.init();
				}
				level.goPlay(Integer.parseInt(ENTREE_X.getText()), Integer.parseInt(ENTREE_Y.getText()), 
						Integer.parseInt(SORTIE_X.getText()), Integer.parseInt(SORTIE_Y.getText()));
				root.repaint();
				terrain.repaint();
				window.repaint();
				new Thread(new Boucle(x)).start();
			}
		});
		button3=new JButton("Modify Speed");
		button3.setVisible(false);
		button3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ge.setSpawnSpeed(Integer.parseInt(SPEED.getText()));
			}
		});

		button4=new JButton("Annihilation");
		button4.setVisible(false);
		button4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ge.annihilate();
			}
		});

		button5=new JButton("Reset");
		button5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(ge.gameOver()){
					button.setVisible(true);
					button2.setVisible(false);
					button3.setVisible(false);
					button4.setVisible(false);
					SPAWNSPEED.setVisible(false);
					joueur= new JoueurContrat(new Joueur());
					level = new LevelContrat(new Level());
					ge=new GameEngineContrat(new GameEngine());
					root.remove(token);
					root.remove(terrain);
					root.repaint();
					token=new Token(joueur);
					joueur.init(ge);
					terrain=new Terrain(ge);
					root.add(token,BorderLayout.SOUTH);
					root.add(terrain,BorderLayout.CENTER);
				}
			}
		});

	}


}
