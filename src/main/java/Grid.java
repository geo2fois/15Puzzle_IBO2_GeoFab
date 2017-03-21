import sun.security.util.Length;

import java.util.ArrayList;
import java.util.List;

//import com.sun.org.apache.xpath.internal.functions.FuncFalse;

// La class Grid g�n�re le plateau du Puzzle de dimension (x,y)
public class Grid {
	
	public static List<Tile> goal = new ArrayList<Tile>();

	private int nbMove;
	private int priority;
	private int parentID;
	
	private int rowLength;
	private int columnLength;
	private int tileNumber;
	private Tile[][] tab;
	private Tile tileZero;
	
	public Grid() {
		// G�re la taille du plateau
		this.rowLength = 4;
		this.columnLength = 4;
		this.tileNumber = this.rowLength * this.columnLength;
		
		// Instanciation du tableau du plateau de jeu
		this.tab = new Tile[this.rowLength][this.columnLength];
		
		this.priority = 0;
		this.nbMove = 0;
		this.parentID = 0;
		
		int value = 1;
		
		for(int y = 0; y < this.columnLength; y++){
			for(int x = 0; x < this.rowLength; x++){
				this.tab[x][y] = new Tile(x, y, value);
				if(value == (this.tileNumber)) {
					this.tab[x][y].setValue(0);
				}
				value ++;
			}
		}
		this.tileZero = findTileByValue(0);
	}
	
	public Grid(int length) {
		
		// G�re la taille du plateau
		this.rowLength = length;
		this.columnLength = length;
		this.tileNumber = this.rowLength * this.columnLength;
		
		// Instanciation du tableau du plateau de jeu
		this.tab = new Tile[this.rowLength][this.columnLength];
		
		this.priority = 0;
		this.nbMove = 0;
		this.parentID = 0;
		
		int value = 1;
		
		for(int y = 0; y < this.columnLength; y++){
			for(int x = 0; x < this.rowLength; x++){
				this.tab[x][y] = new Tile(x, y, value);
				if(value == (this.tileNumber)) {
					this.tab[x][y].setValue(0);
				}
				value ++;
			}
		}
		this.tileZero = findTileByValue(0);
	}

	public Grid(Grid copie) {
		// G�re la taille du plateau
		this.rowLength = copie.getRowLength();
		this.columnLength = copie.getColumnLength();
		this.tileNumber = this.rowLength * this.columnLength;
		
		// Instanciation du tableau du plateau de jeu
		this.tab = new Tile[this.rowLength][this.columnLength];
		
		this.priority = 0;
		this.nbMove = copie.getNbMove();
		this.parentID = copie.hashCode();
		
		for(int y = 0; y < this.columnLength; y++){
			for(int x = 0; x < this.rowLength; x++){
				this.tab[x][y] = new Tile(x, y, copie.getTab()[x][y].getValue());
			}
		}
		this.tileZero = findTileByValue(0);
	}
	
	public void mix() {
		int lower = 1;
		int higher = 5;
		int random = 0;
		int i = 0;
		while(i<60) {

			random = (int)(Math.random() * (higher-lower)) + lower;

			switch (random)
			{
				case 1:
					if(this.findTileByValue(0).getY() > 0) {
						this.zeroUp();
						i++;
					}
					break;
				case 2:
					if(this.findTileByValue(0).getY() < 3) {//Astar.LENGTH-1) {
						this.zeroDown();
						i++;
					}
					break;
				case 3:
					if(this.findTileByValue(0).getX() > 0) {
						this.zeroOnLeft();
						i++;
					}
					break;
				case 4:
					if(this.findTileByValue(0).getX() < 3) {//Astar.LENGTH-1) {
						this.zeroOnRight();
						i++;
					}
					break;
				default:
					System.out.println("Erreur Math.Random");
			}
		}
		this.tileZero = findTileByValue(0);
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

		Tile goalTile;
		Tile myTile;
		int valTemp;
		int x = 0;
		int y = 0;
		//Nombre de permutation des cases jusqu'� �tat final
		for(int tileValue = 1; tileValue < this.tileNumber; tileValue++) {
			
			x = findGoalByValue(tileValue).getX();
			y = findGoalByValue(tileValue).getY();

			goalTile = new Tile(x,y,tileValue);

			myTile = gridTemp.findTileByValue(tileValue);
			
			if(goalTile.getValue() != myTile.getValue()) {
				valTemp = gridTemp.getTab()[goalTile.getX()][goalTile.getY()].getValue();
				gridTemp.getTab()[goalTile.getX()][goalTile.getY()].setValue(myTile.getValue());
				gridTemp.getTab()[myTile.getX()][myTile.getY()].setValue(valTemp);
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
	
	public Tile findTileByValue(int value) {
		for (int column = 0; column < this.rowLength; column++) {
			for (int row = 0; row < this.columnLength; row++) {
				if(this.tab[row][column].getValue() == value) {
					return this.tab[row][column];
				}
			}
		}
		return null;
	}
	
	public Tile findGoalByValue(int value) {
		for(Tile myTile: goal){
			if(myTile.getValue() == value) return myTile;
		}
		return null;
	}

 	public void initGoal() {
		int value = 1;
		for(int column = 0; column < this.columnLength; column++){
			for(int row = 0; row < this.rowLength; row++){
				if(value == (this.tileNumber)) {
					goal.add(new Tile(row, column, 0));
				}
				else goal.add(new Tile(row, column, value));
				value ++;
			}
		}
	}

	public boolean isMoveable(Tile myTile) {
		System.out.println("diff sur x" + Math.abs(myTile.getX()-this.tileZero.getX()));
		System.out.println("diff sur y" + Math.abs(myTile.getY()-this.tileZero.getY()));
		if( (Math.abs(myTile.getX()-this.tileZero.getX()) ==  1) && (myTile.getY()-this.tileZero.getY() == 0)) {
			return true;
		}
		else if((myTile.getX()-this.tileZero.getX() ==  0) && (Math.abs(myTile.getY()-this.tileZero.getY()) == 1)) {
			return true;
		}
		return false;
	}

	/******************
	* Moves Functions *
	*******************/
	public void zeroUp() {
		Tile myTile = this.tab[tileZero.getX()][tileZero.getY()-1];
		tileZero.setValue(myTile.getValue());
		myTile.setValue(0);
		this.nbMove++;
		this.tileZero = this.findTileByValue(0);
	}
	
	public void zeroDown() {
		Tile myTile = this.tab[tileZero.getX()][tileZero.getY()+1];
		tileZero.setValue(myTile.getValue());
		myTile.setValue(0);
		this.nbMove++;
		this.tileZero = this.findTileByValue(0);
	}
	
	public void zeroOnRight() {
		Tile myTile = this.tab[tileZero.getX()+1][tileZero.getY()];
		tileZero.setValue(myTile.getValue());
		myTile.setValue(0);
		this.nbMove++;
		this.tileZero = this.findTileByValue(0);
	}
	
	public void zeroOnLeft() {
		Tile myTile = this.tab[tileZero.getX()-1][tileZero.getY()];
		tileZero.setValue(myTile.getValue());
		myTile.setValue(0);
		this.nbMove++;
		this.tileZero = this.findTileByValue(0);
	}

	public void Swap(Tile myTile) {
		this.tileZero.setValue(myTile.getValue());
		myTile.setValue(0);
		this.nbMove++;
		this.tileZero = this.findTileByValue(0);
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
		
		Tile myTile = new Tile();
		Tile myGoalTile = new Tile();
		for (int value=0; value < tileNumber; value++) {
			myTile = findTileByValue(value);
			myGoalTile = findGoalByValue(value);
			if(	myTile.getX() != myGoalTile.getX() || myTile.getY() != myGoalTile.getY()) {
				
				priority += Math.abs(myTile.getX() - myGoalTile.getX());
				priority += Math.abs(myTile.getY() - myGoalTile.getY());
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

	public Tile[][] getTab() {
		return this.tab;
	}

	public int getParentID() {
		return this.parentID;
	}
	
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public int getTileNumber() {
		return this.tileNumber;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Tile getTileZero() {
		return this.tileZero;
	}
}