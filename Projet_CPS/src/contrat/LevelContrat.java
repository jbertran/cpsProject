package contrat;

import error.InvariantError;
import error.PostConditionError;
import error.PreConditionError;
import services.ILevel;
import services.Nature;
import decorateur.LevelDecorateur;

public class LevelContrat extends LevelDecorateur{

	public LevelContrat(ILevel delegate) {
		super(delegate);
	}

	public void checkInvariants(){
		if(super.height()<=0 || super.width()<=0)
			throw new InvariantError("Size of the level is wrong");
	}

	// CONSTRUCTORS

	public void init(int h, int w){
		if(h<=0 || w<=0)
			throw new PreConditionError("init error, h and w <= 0");
		super.init(h, w);
		if(super.width()!=w || super.height()!=h)
			throw new PreConditionError("init error, h and w has changed");
		if(super.editing()==false)
			throw new PostConditionError("init error, editing is false");
	}

	// OPERATORS

	public void setNature(int x, int y, Nature n){
		checkInvariants();
		//PreCondition
		Nature[][] level_pre=new Nature[super.width()][super.height()];
		for(int i=0;i<super.width();i++)
			for(int j=0;j<super.height();j++)
				level_pre[i][j]=super.nature(i, j);
		super.setNature(x, y, n);
		//PostCondition
		if(x<0 || y <0 || super.editing()==false)
			throw new PreConditionError("setNature error, ");
		if(super.nature(x, y)!=n)
			throw new PostConditionError("setNature error, not de same nature at post");
		for(int i=0;i<super.width();i++)
			for(int j=0;j<super.height();j++)
				if(i!=x && j!=y)
					if(level_pre[i][j]!=super.nature(i, j))
						throw new PostConditionError("setNature error, nature hasn't changed");

		checkInvariants();

	}


	public void goPlay(int xe, int ye, int xs, int ys){
		checkInvariants();
		//PreCondition
		if(super.editing()==false )
			throw new PreConditionError("goPlay error, editing is false");
		if(xs<0 || xs <0 || xe >super.width() || xs >super.width())
			throw new PreConditionError("goPlay error,error coordinate entry/exit");
		if(ye<0 || ye <0 || ye >super.height() || ye>super.height())
			throw new PreConditionError("goPlay error,error coordinate entry/exit");

		super.goPlay(xe, ye, xs, ys);
		//PostCondition
		for(int i =0;i<super.height();i++)
			if(super.nature(0, i)!=Nature.METAL || super.nature(super.width()-1,i )!=Nature.METAL)
				throw new PreConditionError("goPlay error, Metal border missing");
		for(int i =0;i<super.width();i++)
			if(super.nature(i, 0)!=Nature.METAL || super.nature(i, super.height()-1)!=Nature.METAL)
				throw new PreConditionError("goPlay error, Metal border missing");
		
		if((ys+1)>=this.height() || ys-1<0 || xs-1<0 || (xs+1)>=this.height())
			throw new PreConditionError("Entrance or exit outside the map");
		if(super.nature(xe, ye-1)!=Nature.EMPTY)
			throw new PreConditionError("Entrance not clear");
		if(super.nature(xe, ye+1)!=Nature.EMPTY)
			throw new PreConditionError("Entrance not clear");
		if(super.nature(xe, ye)!=Nature.EMPTY)
			throw new PreConditionError("Entrance not clear");
		if(super.nature(xs, ys-1)!=Nature.EMPTY)
			throw new PreConditionError("Exit not clear");
		if(super.nature(xs, ys+1)!=Nature.METAL)
			throw new PreConditionError("Exit metal platform not here");
		if(super.nature(xs, ys)!=Nature.EMPTY)
			throw new PreConditionError("Exit not clear");

		if(super.entree_x()!=xe || super.entree_y()!=ye || super.sortie_x() !=xs || super.sortie_y()!=ys)
			throw new PreConditionError("goPlay error, Error coordinate entry/exit");

		checkInvariants();
	}


	public void remove(int x, int y){
		checkInvariants();
		//PreCondition
		if(x<0 || x> super.width() ||y<0 || y>super.height() || (super.nature(x, y)!=Nature.DIRT  && super.nature(x, y)!=Nature.EMPTY))
			throw new PreConditionError("remove error");


		Nature[][] level_pre=new Nature[super.width()][super.height()];
		for(int i=0;i<super.width();i++)
			for(int j=0;j<super.height();j++)
				level_pre[i][j]=super.nature(i, j);

		super.remove(x, y);
		//PostCondition
		if(super.nature(x, y)!=Nature.EMPTY)
			throw new PostConditionError("remove error");
		for(int i=0;i<super.width();i++)
			for(int j=0;j<super.height();j++)
				if(i!=x && j!=y)
					if(level_pre[i][j]!=super.nature(i, j))
						throw new PostConditionError("setNature error, nature hasn't changed");

		checkInvariants();
	}


	public void build(int x, int y){
		checkInvariants();
		//Precondition
		if(x<0 || x> super.width() ||y<0 || y>super.height() || super.nature(x, y) == Nature.METAL)
			throw new PreConditionError("remove error");


		Nature[][] level_pre=new Nature[super.width()][super.height()];
		for(int i=0;i<super.width();i++)
			for(int j=0;j<super.height();j++)
				level_pre[i][j]=super.nature(i, j);

		super.build(x, y);
		//PostCondition
		if(super.nature(x, y)!=Nature.DIRT)
			throw new PostConditionError("remove error");

		for(int i=0;i<super.width();i++)
			for(int j=0;j<super.height();j++)
				if(i!=x && j!=y)
					if(level_pre[i][j]!=super.nature(i, j))
						throw new PostConditionError("setNature error, nature hasn't changed");
		checkInvariants();
	}	
	public String toString(){
		return super.toString();
	}
}
