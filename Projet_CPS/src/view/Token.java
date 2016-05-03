package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import services.IJoueur;
import services.Status;

@SuppressWarnings("serial")
public class Token extends JPanel{
	IJoueur player;
	JTextField lem;
	JComboBox<Status> box;
	JButton button;

	public Token( IJoueur player){
		this.player=player;
		this.setLayout(new GridLayout(1,3));
		Status[] x = {Status.WALK,Status.CLIMB, Status.BUILD, Status.FLOAT, Status.BOMB, Status.STOP, Status.BASH,Status.MINER};
		this.box= new JComboBox<Status>(x);
		this.add(box);
		lem=new JTextField("0");
		this.add(lem);
		init();
		this.add(button);
	}

	public void init(){
		button=new JButton("Spend Token");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				player.spendToken(Integer.parseInt(lem.getText()), (Status)box.getSelectedItem());
			}
		});
	}
}
