package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IGameEng;
import services.IJoueur;
import services.ILevel;
import services.Nature;
import services.Status;

public abstract class AbstractLemmingsTest{
	private IGameEng ge;
	private IJoueur player;
	private ILevel level;

	protected AbstractLemmingsTest(){
		ge=null;
		player=null;
		level=null;
	}

	protected final IGameEng getGameEngine(){
		return ge;
	}

	protected final IJoueur getJoueur(){
		return player;
	}

	protected final ILevel getLevel(){
		return level;
	}

	protected final void setGameEngine(IGameEng ge){
		this.ge=ge;
	}

	protected final void setJoueur(IJoueur player){
		this.player=player;
	}

	protected final void setLevel(ILevel level){
		this.level=level;
	}
	@Before
	public void beforeTests() {}

	@Test
	public void initPrePosTest(){
		boolean b=true;
		try{
			getJoueur().init(getGameEngine());
			getLevel().init(15, 10);
			getGameEngine().init(getLevel(), 8, 4);
		}catch(Error e){
			b=false;
		}
		assertTrue(b);
	}

	@Test
	public void initPreNegTest(){
		boolean b=false;
		try{
			getJoueur().init(getGameEngine());
			getLevel().init(-1, 10);
			getGameEngine().init(getLevel(), 8, 4);
		}catch(Error e){
			b=true;
		}
		assertTrue(b);
	}

	@Test
	public void initAutoTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(15, 10);
		getGameEngine().init(getLevel(), 8, 4);
		assertTrue(getLevel().height()==15);
		assertTrue(getLevel().width()==10);
		assertTrue(getGameEngine().spawnSpeed()==4);
		assertTrue(getGameEngine().sizeColony()==8);		
	}

	@Test
	public void setNaturePrePosTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 10);
		getGameEngine().init(getLevel(), 8, 4);
		boolean b=true;
		try{
			getLevel().setNature(3, 3, Nature.DIRT);
		}catch(Error e){
			b=!b;
		}
		assertTrue(b);	
	}

	@Test
	public void setNaturePreNegTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 10);
		getGameEngine().init(getLevel(), 8, 4);
		boolean b=true;
		try{
			getLevel().setNature(12, 13, Nature.DIRT);
		}catch(Error e){
			b=!b;
		}
		assertFalse(b);	
	}

	@Test
	public void setNatureAutoTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 10);
		getGameEngine().init(getLevel(), 8, 4);
		getLevel().setNature(5, 5, Nature.DIRT);
		assertTrue(getLevel().nature(5, 5)==Nature.DIRT);
	}

	@Test
	public void goPlayPrePosTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 10);
		getGameEngine().init(getLevel(), 8, 4);
		boolean b=true;
		try{
			getLevel().goPlay(2, 2, 4, 8);
		}catch(Error e){
			b=false;
		}
		assertTrue(b);
	}

	@Test
	public void goPlayPreNegTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 10);
		getGameEngine().init(getLevel(), 8, 4);
		boolean b=true;
		try{
			getLevel().goPlay(2, 2, 4, 9);
		}catch(Error e){
			b=false;
		}
		assertFalse(b);
	}


	@Test
	public void goPlayAutoTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 10);
		getGameEngine().init(getLevel(), 8, 4);
		getLevel().goPlay(2, 2, 4, 8);
		assertTrue(getLevel().editing()==false);
		assertTrue(getLevel().entree_x()==2);
		assertTrue(getLevel().entree_y()==2);
		assertTrue(getLevel().sortie_x()==4);
		assertTrue(getLevel().sortie_y()==8);
	}


	@Test
	public void stepPrePosTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 10);
		getGameEngine().init(getLevel(), 8, 4);
		getLevel().goPlay(2, 2, 4, 8);
		boolean b=true;
		try{
			ge.step();
		}catch(Error e){
			b=false;
		}
		assertTrue(b);
	}

	@Test
	public void stepMultiPrePosTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 10);
		getGameEngine().init(getLevel(), 8, 4);
		getLevel().goPlay(2, 2, 4, 8);
		boolean b=true;
		try{
			for(int i=0;i<10;i++)
				if(!ge.gameOver())
					ge.step();
		}catch(Error e){
			b=false;
		}
		assertTrue(b);
	}
	
	@Test
	public void spendTokenPrePosTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 20);
		getGameEngine().init(getLevel(), 8, 1);
		for(int i=0;i<getLevel().width();i++)
			getLevel().setNature(i, 4, Nature.METAL);
		getLevel().goPlay(2, 2, 18, 3);
		ge.step();
		ge.step();
		ge.step();
		boolean b=true;
		try{	
			player.spendToken(0, Status.STOP);
		}catch(Error e){
			b=false;
		}
		assertTrue(b);
	}
	
	@Test
	public void spendTokenPreNegTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 20);
		getGameEngine().init(getLevel(), 8, 1);
		for(int i=0;i<getLevel().width();i++)
			getLevel().setNature(i, 4, Nature.METAL);
		getLevel().goPlay(2, 2, 18, 3);
		ge.step();
		ge.step();
		ge.step();
		boolean b=false;
		try{	
			player.spendToken(5, Status.STOP);
		}catch(Exception e){
			b=false;
		}catch(Error e){
			b=false;
		}
		assertFalse(b);
	}
	
	@Test
	public void spendTokenAutoTest(){
		getJoueur().init(getGameEngine());
		getLevel().init(10, 20);
		getGameEngine().init(getLevel(), 8, 1);
		for(int i=0;i<getLevel().width();i++)
			getLevel().setNature(i, 4, Nature.METAL);
		getLevel().goPlay(2, 2, 18, 3);
		ge.step();
		ge.step();
		ge.step();
		getJoueur().spendToken(0, Status.STOP);
		assertTrue(ge.getLemm(0).getStatus()==Status.STOP);
		int xpre=ge.getLemm(0).getX();
		int ypre=ge.getLemm(0).getY();
		ge.step();
		assertTrue(ge.getLemm(0).getX()==xpre);
		assertTrue(ge.getLemm(0).getY()==ypre);
		
	}
	@After
	public final void afterTests(){
		ge=null;
		level=null;
		player=null;
	}
}

