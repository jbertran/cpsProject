package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import implem.GameEngine;
import implem.Joueur;
import implem.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	JButton button;
	JButton button2;
	Terrain terrain;
	JPanel root;
	JFrame window;


	public Settings(IGameEng gameEng,ILevel level,IJoueur joueur,JPanel root,Terrain terrain,JFrame window){
		this.root=root;
		this.terrain=terrain;
		this.window=window;
		this.ge=gameEng;
		this.level=level;
		this.joueur=joueur;
		WIDTH.setText("30");
		HEIGHT.setText("13");
		SPAWNSPEED.setText("4");
		SIZECOLONY.setText("8");
		ENTREE_X.setText("1");
		ENTREE_Y.setText("1");
		SORTIE_X.setText("25");
		SORTIE_Y.setText("3");
		this.setLayout(new GridLayout(30,1));
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
		this.add(new JLabel("HELP : "));
		this.add(new JLabel("1-Cliquer en premier sur : Init"));
		this.add(new JLabel("2-Cliquer sur les cases"));
		this.add(new JLabel("après avoir Init "));
		this.add(new JLabel("pour mettre de la DIRT"));
		this.add(new JLabel("3-Cliquer sur Play !"));
		joueur.init(ge);
		

	}


	public void init(){
		button=new JButton("Init Terrain");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
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
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
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
	}


}
