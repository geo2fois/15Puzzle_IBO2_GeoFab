import sun.security.util.Length;

import java.util.ArrayList;
import java.util.List;

//import com.sun.org.apache.xpath.internal.functions.FuncFalse;

// La class Grid g�n�re le plateau du Puzzle de dimension (x,y)
public class Grid {
	
	public static List<box> goal = new ArrayList<box>();

	private int nbMove;
	private int priority;
	private int parentID;
	
	private int rowLength;
	private int columnLength;
	private int boxNumber;
	private box[][] tab;
	private box boxZero;
	
	public Grid() {
		// G�re la taille du plateau
		this.rowLength = 4;
		this.columnLength = 4;
		this.boxNumber = this.rowLength * this.columnLength;
		
		// Instanciation du tableau du plateau de jeu
		this.tab = new box[this.rowLength][this.columnLength];
		//Grid.goal =  new box[this.rowLength][this.columnLength];
		
		
		this.priority = 0;
		this.nbMove = 0;
		this.parentID = 0;
		
		int value = 1;
		
		for(int y = 0; y < this.columnLength; y++){
			for(int x = 0; x < this.rowLength; x++){
				this.tab[x][y] = new box(x, y, value);
				if(value == (this.boxNumber)) {
					this.tab[x][y].setValue(0);
				}
				value ++;
			}
		}
		boxZero = findBoxByValue(0);
	}
	
	public Grid(int length) {
		
		// G�re la taille du plateau
		this.rowLength = length;
		this.columnLength = length;
		this.boxNumber = this.rowLength * this.columnLength;
		
		// Instanciation du tableau du plateau de jeu
		this.tab = new box[this.rowLength][this.columnLength];
		//goal = new box[this.rowLength][this.columnLength];
		
		this.priority = 0;
		this.nbMove = 0;
		this.parentID = 0;
		
		int value = 1;
		
		for(int y = 0; y < this.columnLength; y++){
			for(int x = 0; x < this.rowLength; x++){
				this.tab[x][y] = new box(x, y, value);
				if(value == (this.boxNumber)) {
					this.tab[x][y].setValue(0);
				}
				value ++;
			}
		}
		boxZero = findBoxByValue(0);
	}

	public Grid(Grid copie) {
		// G�re la taille du plateau
		this.rowLength = copie.getRowLength();
		this.columnLength = copie.getColumnLength();
		this.boxNumber = this.rowLength * this.columnLength;
		
		// Instanciation du tableau du plateau de jeu
		this.tab = new box[this.rowLength][this.columnLength];
		//goal = new box[this.rowLength][this.columnLength];
		
		this.priority = 0;
		this.nbMove = copie.getNbMove();
		this.parentID = copie.hashCode();
		
		for(int y = 0; y < this.columnLength; y++){
			for(int x = 0; x < this.rowLength; x++){
				this.tab[x][y] = new box(x, y, copie.getTab()[x][y].getValue());
			}
		}
		boxZero = findBoxByValue(0);
	}
	
	public void mix() {
		int lower = 1;
		int higher = 5;
		int random = 0;
		int i = 0;
		while(i<40) {

			random = (int)(Math.random() * (higher-lower)) + lower;

			switch (random)
			{
				case 1:
					if(this.findBoxByValue(0).getY() > 0) {
						this.zeroUp();
						i++;
					}
					break;
				case 2:
					if(this.findBoxByValue(0).getY() < 3) {//Astar.LENGTH-1) {
						this.zeroDown();
						i++;
					}
					break;
				case 3:
					if(this.findBoxByValue(0).getX() > 0) {
						this.zeroOnLeft();
						i++;
					}
					break;
				case 4:
					if(this.findBoxByValue(0).getX() < 3) {//Astar.LENGTH-1) {
						this.zeroOnRight();
						i++;
					}
					break;
				default:
					System.out.println("Erreur Math.Random");
			}
		}
		boxZero = findBoxByValue(0);
		this.nbMove = 0;
	}

	public boolean isPossible() {
		boolean originParity = false;
		boolean caseParity = false;
		
		//Parit� de l'origine
		//Nombre de d�placement de la case vide entre case de d�part et case d'arriv�e
		int distance = 0;
		for (int collumn = 0; collumn < this.columnLength; collumn++) {
			for (int row = 0; row < this.rowLength; row++) {
				if(tab[row][collumn].getValue() == 0) {
					distance = (3 - row) + (3 - collumn);
				}
			}
		}
		//Parit� du nombre de d�placement
		if((distance%2) == 0) originParity = true;
		
		System.out.println(" Parit� du 0 : " + originParity);
		
		//Parit� des permutations
		int permutations = 0;
		
		Grid gridTemp = new Grid(this.rowLength);
		gridTemp = this;
		
		box goalBox;
		box myBox;
		int valTemp;
		int x = 0;
		int y = 0;
		//Nombre de permutation des cases jusqu'� �tat final
		for(int boxValue = 1; boxValue < this.boxNumber; boxValue++) {
			
			x = findGoalByValue(boxValue).getX();
			y = findGoalByValue(boxValue).getY();
			
			goalBox = new box(x,y,boxValue);
			
			myBox = gridTemp.findBoxByValue(boxValue);
			
			if(goalBox.getValue() != myBox.getValue()) {
				valTemp = gridTemp.getTab()[goalBox.getX()][goalBox.getY()].getValue();
				gridTemp.getTab()[goalBox.getX()][goalBox.getY()].setValue(myBox.getValue());
				gridTemp.getTab()[myBox.getX()][myBox.getY()].setValue(valTemp);
				permutations ++;
			}
		}
		
		//Parit� du nombre de permutation
		if((permutations%2) == 0) caseParity = true;
		
		System.out.println(" Parit� des permutations : " + caseParity);
		
		if(originParity == caseParity) {
			System.out.println(" Taquin Solvable ");
			return true;
		}
		
		System.out.println(" Taquin non Solvable ");
		return false;
	}
	
	public void display() {
		System.out.println(" ***********  Display  ************");
		
		for (int y = 0; y < this.columnLength; y++) {
			for (int x = 0; x < this.rowLength; x++) {
				System.out.print(" || " + String.format("%4d",this.tab[x][y].getValue()));
				
				if (x == (this.rowLength - 1)) {
					System.out.print(" || ");
				}
			}
			System.out.println("");
		}
		System.out.println("Number of moves : " + this.nbMove);
		System.out.println("Priority : " + this.priority);
		//System.out.println("HashCode : " + this.hashCode());
		//System.out.println("Parent ID : " + this.parentID);
		System.out.println("");
	}
	
	public box findBoxByValue(int value) {
		for (int column = 0; column < this.rowLength; column++) {
			for (int row = 0; row < this.columnLength; row++) {
				if(this.tab[row][column].getValue() == value) {
					return this.tab[row][column];
				}
			}
		}
		return null;
	}
	
	public box findGoalByValue(int value) {
		for(box myBox: goal){
			if(myBox.getValue() == value) return myBox;
		}
		return null;
	}

 	public void initGoal() {
 		//goal = new box[this.rowLength][this.columnLength];
		int value = 1;
		for(int column = 0; column < this.columnLength; column++){
			for(int row = 0; row < this.rowLength; row++){
				if(value == (this.boxNumber)) {
					goal.add(new box(row, column, 0));
				}
				else goal.add(new box(row, column, value));
				value ++;
			}
		}
	}

	public boolean isMoveable(box myBox) {
		System.out.println("diff sur x" + Math.abs(myBox.getX()-this.boxZero.getX()));
		System.out.println("diff sur y" + Math.abs(myBox.getY()-this.boxZero.getY()));
		if( (Math.abs(myBox.getX()-this.boxZero.getX()) ==  1) && (myBox.getY()-this.boxZero.getY() == 0)) {
			return true;
		}
		else if((myBox.getX()-this.boxZero.getX() ==  0) && (Math.abs(myBox.getY()-this.boxZero.getY()) == 1)) {
			return true;
		}
		return false;
	}

	/******************
	* Moves Functions *
	*******************/
	public void zeroUp() {
		box zeroBox = this.findBoxByValue(0);
		box myBox = this.tab[zeroBox.getX()][zeroBox.getY()-1];
		zeroBox.setValue(myBox.getValue());
		myBox.setValue(0);
		this.nbMove++;
		this.boxZero = this.findBoxByValue(0);
	}
	
	public void zeroDown() {
		box zeroBox = this.findBoxByValue(0);
		box myBox = this.tab[zeroBox.getX()][zeroBox.getY()+1];
		zeroBox.setValue(myBox.getValue());
		myBox.setValue(0);
		this.nbMove++;
		this.boxZero = this.findBoxByValue(0);
	}
	
	public void zeroOnRight() {
		box zeroBox = this.findBoxByValue(0);
		box myBox = this.tab[zeroBox.getX()+1][zeroBox.getY()];
		zeroBox.setValue(myBox.getValue());
		myBox.setValue(0);
		this.nbMove++;
		this.boxZero = this.findBoxByValue(0);
	}
	
	public void zeroOnLeft() {
		box zeroBox = this.findBoxByValue(0);
		box myBox = this.tab[zeroBox.getX()-1][zeroBox.getY()];
		zeroBox.setValue(myBox.getValue());
		myBox.setValue(0);
		this.nbMove++;
		this.boxZero = this.findBoxByValue(0);
	}

	public void Swap(box myBox) {
		this.boxZero.setValue(myBox.getValue());
		myBox.setValue(0);
		this.nbMove++;
		this.boxZero = this.findBoxByValue(0);
	}

	/***********************
	* Getteur and Setteur *
	***********************/
	public int getRowLength() {
    	return this.rowLength;
    }
    
    public void setRowLength(int rowLength) {
    	this.rowLength = rowLength;
    }

    public int getColumnLength() {
    	return this.columnLength;
    }

    public int getPriority() {
		this.priority = 0;
		
		box myBox = new box();
		box myGoalBox = new box();
		for (int value=0; value < boxNumber; value++) {
			myBox = findBoxByValue(value);
			myGoalBox = findGoalByValue(value);
			if(	myBox.getX() != myGoalBox.getX() || myBox.getY() != myGoalBox.getY()) {
				
				priority += Math.abs(myBox.getX() - myGoalBox.getX());
				priority += Math.abs(myBox.getY() - myGoalBox.getY());
			}
		}
		this.priority += this.nbMove;
		return this.priority;
	}
    
    public void setNbMove(int nbMove) {
		this.nbMove = nbMove;
	}
	
	public int getNbMove() {
		return this.nbMove;
	}

	public box[][] getTab() {
		return this.tab;
	}

	public int getParentID() {
		return this.parentID;
	}
	
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public int getBoxNumber() {
		return this.boxNumber;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
}