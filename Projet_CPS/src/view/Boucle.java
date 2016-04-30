package view;

public class Boucle implements Runnable{
	
	private Settings s;

	public Boucle (Settings s){
		this.s=s;
	}

	@Override
	public void run() {
		while(!s.ge.gameOver()) {
			/******** STEP *****/
			System.out.println("------------------------------------");
			System.out.println(s.ge);
			System.out.println("------------------------------------");
			System.out.println("Spawned : "+s.ge.spawned()+"/"+s.ge.sizeColony()+
					" | Saved : "+s.ge.nbSauves()+" | Tour : "+s.ge.tours());
			System.out.println("------------------------------------");
			System.out.println("************************************");
			s.ge.step();
			s.root.repaint();
			s.terrain.repaint();
			s.window.repaint();
			/******************/
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ee) {
				ee.printStackTrace();
			}
		}
		System.out.println(s.ge.score());
		
	}

}
