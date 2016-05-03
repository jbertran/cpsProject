package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.IGameEng;
import services.IJoueur;
import services.ILevel;
import services.Nature;

public abstract class AbstractLemmingsTest {
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
				getLevel().init(-1, 10);
				getGameEngine().init(getLevel(), 8, 4);
			}catch(Error e){
				b=true;
			}
			assertTrue(b);
		}
		
		@Test
		public void initAutoTest(){
			getLevel().init(15, 10);
			getGameEngine().init(getLevel(), 8, 4);
			assertTrue(getLevel().height()==15);
			assertTrue(getLevel().width()==10);
			assertTrue(getGameEngine().spawnSpeed()==4);
			assertTrue(getGameEngine().sizeColony()==8);		
		}
		
		@Test
		public void setNaturePrePosTest(){
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
			getLevel().init(10, 10);
			getGameEngine().init(getLevel(), 8, 4);
			getLevel().setNature(5, 5, Nature.DIRT);
			assertTrue(getLevel().nature(5, 5)==Nature.DIRT);
		}
		
		@Test
		public void goPlayPrePosTest(){
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
			getLevel().init(10, 10);
			getGameEngine().init(getLevel(), 8, 4);
			getLevel().goPlay(2, 2, 4, 8);
			assertTrue(getLevel().editing()==false);
			assertTrue(getLevel().entree_x()==2);
			assertTrue(getLevel().entree_y()==2);
			assertTrue(getLevel().sortie_x()==4);
			assertTrue(getLevel().sortie_y()==8);
		}
		
		@After
		public final void afterTests(){
			ge=null;
			level=null;
			player=null;
		}
	}

