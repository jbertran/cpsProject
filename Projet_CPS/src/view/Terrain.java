package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import services.IGameEng;

@SuppressWarnings("serial")
public class Terrain extends JPanel{

	private IGameEng ge;
	private CaseView cv[][];

	public Terrain(IGameEng gameEng){
		this.ge=gameEng;
		cv=new CaseView[gameEng.level().width()][gameEng.level().height()];
		this.setLayout(new GridLayout(gameEng.level().height(),gameEng.level().width()));
		for(int i=0;i<gameEng.level().width();i++)
			for(int j=0;j<gameEng.level().height();j++)
				cv[i][j]=new CaseView(gameEng,i,j);
		for(int j=0;j<gameEng.level().height();j++)
		for(int i=0;i<gameEng.level().width();i++)
			
				this.add(cv[i][j]);
	}
	
	public void repaint(){
		super.repaint();
		if(ge!=null)
			for(int i=0;i<ge.level().width();i++)
				for(int j=0;j<ge.level().height();j++)
					cv[i][j].repaint();
	}
}
