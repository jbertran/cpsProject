package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import services.IGameEng;

@SuppressWarnings("serial")
public class Terrain extends JPanel{

	private IGameEng ge;
	private CaseView cv[][];
	boolean ok=false;
	public Terrain(IGameEng gameEng){
		this.ge=gameEng;
	}
	public void init(){
		ok=true;
		cv=new CaseView[ge.level().width()][ge.level().height()];
		this.setLayout(new GridLayout(ge.level().height(),ge.level().width()));
		for(int i=0;i<ge.level().width();i++)
			for(int j=0;j<ge.level().height();j++)
				cv[i][j]=new CaseView(ge,i,j);
		for(int j=0;j<ge.level().height();j++)
			for(int i=0;i<ge.level().width();i++)

				this.add(cv[i][j]);
		this.setSize(cv[0][0].getSize().width*ge.level().width(),cv[0][0].getSize().height*ge.level().height());
		repaint();
	}
	public void repaint(){
		super.repaint();
		if(ok)
		if(ge!=null)
			for(int i=0;i<ge.level().width();i++)
				for(int j=0;j<ge.level().height();j++){
							cv[i][j].repaint();
				}
	}
}
