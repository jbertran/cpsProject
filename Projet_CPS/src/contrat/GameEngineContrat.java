package contrat;

import error.InvariantError;
import error.PostConditionError;
import error.PreConditionError;
import services.IGameEng;
import services.ILemming;
import services.ILevel;
import decorateur.GameEngineDecorateur;

public class GameEngineContrat extends GameEngineDecorateur{

	public GameEngineContrat(IGameEng delegates) {
		super(delegates);
	}

	private void checkInvariants(){
		if(super.gameOver()!=(super.colony().length==0))
			throw new InvariantError("GameOver Error");
		if(super.score()!=(super.nbSauves()/super.tours()))
			throw new InvariantError("Score Error");
		if(super.spawned()<0 || (super.spawned() >= super.sizeColony()))
			throw new InvariantError("spawned() Error");
		if(super.nbSauves()<0 || (super.spawned() >= super.sizeColony()))
			throw new InvariantError("nbSauves() Error");
	}


	public int score(){
		//PreCondition
		if(!super.gameOver())
			throw new PreConditionError("Score error");
		return super.score();
	}


	public boolean obstacle(int x, int y){
		//Precondition
		if(x<0 || y<0)
			throw new PreConditionError("obstacle error < 0");
		if(x>super.level().height() || y>super.level().width())
			throw new PreConditionError("obstacle error > heigth or width");
		return super.obstacle(x, y);
	}

	public boolean gameOver(){
		return super.gameOver();
	}

	// CONSTRUCTORS

	public void init(ILevel lvl, int sc, int ss){
		//Precondition
		if(sc < 0 || ss < 0)
			throw new PreConditionError("error init ss or sc < 0");
		super.init(lvl, sc, ss);
	}

	// OPERATORS

	public void addLemming(ILemming l){

		checkInvariants();
		//Precondition
		if(super.spawned()>=super.sizeColony())
			throw new PreConditionError("addLeming error");

		int spawned_pre=super.spawned();
		int colony_pre=super.sizeColony();


		super.addLemming(l);
		//PostCondition
		if((super.spawned()-1)!=spawned_pre)
			throw new PostConditionError("Add Lemming error, spawned post");

		if((super.sizeColony()-1)!=colony_pre)
			throw new PostConditionError("Add Lemming error, sizeColony post");

		if(super.getLemm(l.getNumber())!=l)
			throw new PostConditionError("Add Lemming error, lemming created -> wrong number");

		checkInvariants();
	}

	public void killLemming(int ln){
		checkInvariants();
		//Precondition
		if(ln <= 0 || ln >= super.sizeColony())
			throw new PreConditionError("Kill Lemming error");

		int colony_pre=super.sizeColony();


		ILemming colonytab_pre[]=new ILemming[super.colony().length];
		for(int i=0;i<super.colony().length;i++)
			colonytab_pre[i]=super.colony()[i];


		super.killLemming(ln);
		//PostCondition
		if((super.sizeColony()+1)!=colony_pre)
			throw new PostConditionError("Kill Lemming error, sizeColony post");

		for(int i=0;i<colonytab_pre.length;i++){
			if(super.colony()[i]!=null && i!=ln){
				if(colonytab_pre[i]==null)
					throw new PostConditionError("Kill Lemming error, Not correctly removed of the colony post");
			}
		}

		checkInvariants();


	}


	public void saveLemming(int ln){

		checkInvariants();
		//PreCondition
		if(ln <= 0 || ln >= super.sizeColony())
			throw new PreConditionError("Save Lemming error");

		int nbSauves_pre=super.nbSauves();


		ILemming colonytab_pre[]=new ILemming[super.colony().length];
		for(int i=0;i<super.colony().length;i++)
			colonytab_pre[i]=super.colony()[i];


		super.saveLemming(ln);
		//PostCondition
		if(super.nbSauves()!=nbSauves_pre+1)
			throw new PostConditionError("Save Lemming error, nbSauves error  post");

		for(int i=0;i<colonytab_pre.length;i++){
			if(super.colony()[i]!=null && i!=ln){
				if(colonytab_pre[i]==null)
					throw new PostConditionError("Save Lemming error, Not correctly saved of the colony post");
			}
		}

		checkInvariants();

	}


	public void step(){
		checkInvariants();
		ILevel x=super.level();
		super.step();
		//PostCondition
		if(x!=super.level())
			throw new PostConditionError("Error step, level has changed !");

		checkInvariants();
	}


	public void loadLevel(ILevel lvl, int sc, int ss){

		checkInvariants();
		//PreCondition
		ILevel x=super.level();
		if(sc <=0 || ss <= 0)
			throw new PreConditionError("loadLevel error, sc or ss <= 0");
		super.loadLevel(lvl, sc, ss);
		//PostCondition
		if(super.spawnSpeed()!=ss)
			throw new PostConditionError("loadLevel error, ss is not the same");
		if(super.sizeColony()!=sc)
			throw new PostConditionError("loadLevel error, sc is not the same");
		if(x!=super.level())
			throw new PostConditionError("Error loadLevel, level has changed !");

		checkInvariants();
	}



	public int nextLemNo(){
		checkInvariants();
		return super.nextLemNo();
	}


}
