package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import services.Direction;
import services.IGameEng;
import services.ILemming;

@SuppressWarnings("serial")
public class CaseView extends JPanel{
	IGameEng ge;
	int x,y;
	JLabel no;

	public CaseView(IGameEng gameEng ,int x,int y){
		this.setSize(20, 20);
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,1));
		this.ge = gameEng;
		this.x=x;
		this.y=y;
		this.no = new JLabel("");
		this.add(no);
	}

	public void paintComponent( Graphics g ) {
		super.paintComponent(g);
		switch(ge.level().nature(x, y)){
		case DIRT:
			g.setColor(new Color(165,42,42));
			this.setBackground(g.getColor());
			break;
		case METAL:
			g.setColor(Color.GRAY);
			this.setBackground(g.getColor());
			break;
		default:
			this.setBackground(null);
			break;
		}
		String lemms = "";
		for(ILemming le : ge.colony()){
			if(le!=null){
				if(le.getX()==x && le.getY()==y) {
					this.setBackground(Color.GREEN);
					lemms += ((le.getDir()==Direction.DROITE)? "D" : "G")
							+le.getNumber()+"\n";
				}
			}
		}
		this.no.setText(lemms);
		if(ge.level().entree_x()==this.x && ge.level().entree_y()==this.y)
			this.setBackground(Color.PINK);
		if(ge.level().sortie_x()==this.x && ge.level().sortie_y()==this.y)
			this.setBackground(Color.BLUE);
	}
}
