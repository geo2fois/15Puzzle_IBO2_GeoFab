import java.util.ArrayList;
import java.util.List;

public class Astar {
	
	public static final int LENGTH = 4;
	private List<Grid> priorityQueue;
	private List<Grid> closedQueue;
	private List<Grid> result;
	private Grid priorityGrid;
	private Grid startGrid;
	
	public Astar() {
		this.priorityQueue = new ArrayList<Grid>();
		this.closedQueue = new ArrayList<Grid>();
		this.result = new ArrayList<Grid>();
		this.priorityGrid = new Grid(LENGTH);
	}

	public Astar(Grid myGrid) {
		this.priorityQueue = new ArrayList<Grid>();
		this.closedQueue = new ArrayList<Grid>();
		this.result = new ArrayList<Grid>();
		this.priorityGrid = new Grid(LENGTH);
		this.startGrid = myGrid;

		myGrid.setNbMove(0);
		myGrid.getPriority();
		this.priorityQueue.add(myGrid);
		this.priorityGrid = getMiniPriority();
	}
	
	public void initMethod() {
		Grid myGrid = new Grid(LENGTH);
		myGrid.initGoal();
		myGrid.mix();
		
		while(!myGrid.isPossible()) {
			myGrid.mix();
		}
		
		myGrid.setNbMove(0);
		myGrid.getPriority();
		myGrid.display();
		this.startGrid = myGrid;
		this.priorityQueue.add(myGrid);
		this.priorityGrid = getMiniPriority();
	}
	
	public void begin() {

		List<Grid> Sons = new ArrayList<Grid>();
		Sons = this.createSons(this.priorityGrid);
						
		for(int i=0; i < Sons.size(); i++) {
			this.priorityQueue.add(Sons.get(i));
		}

		this.priorityGrid = getMiniPriority();

	}
	
	private List<Grid> createSons(Grid parent) {
		List<Grid> Sons = new ArrayList<Grid>();
		
		if(parent.getTileZero().getY() < LENGTH-1 ) {
			Grid son = new Grid(parent);
			son.zeroDown();
			if(!isParent(son)) {
				//if(!this.isAlreadyInPriorQueue(son))
				Sons.add(son);
			}
		}
		
		if(parent.getTileZero().getY() > 0 ) {
			Grid son = new Grid(parent);
			son.zeroUp();
			if(!isParent(son)) {
				//if(!this.isAlreadyInPriorQueue(son))
				Sons.add(son);
			}
		}
			
		if(parent.getTileZero().getX() > 0 ) {
			Grid son = new Grid(parent);
			son.zeroOnLeft();
			if(!isParent(son)) {
				//if(!this.isAlreadyInPriorQueue(son))
				Sons.add(son);
			}
		}
		
		if(parent.getTileZero().getX() < LENGTH-1 ) {
			Grid son = new Grid(parent);
			son.zeroOnRight();
			if(!isParent(son)) {
				//if(!this.isAlreadyInPriorQueue(son))
				Sons.add(son);
			}
		}
		
		return Sons;
	}
	
	private boolean isParent(Grid son) {
		Grid parent = this.findGridByID(son.getParentID());
		parent = this.findGridByID(parent.getParentID());
		for(int y=0; y < parent.getColumnLength(); y++) {
			for(int x=0; x < parent.getRowLength(); x++) {
				if(parent.getTab()[x][y].getValue() != son.getTab()[x][y].getValue()) {
					return false;
				}
			}
		}
		return true;		
	}

 	private Grid getMiniPriority() {
		Grid priorGrid = this.priorityQueue.get(0);
		for(Grid grid: this.priorityQueue) {
			if(priorGrid.getPriority() > grid.getPriority()) priorGrid = grid;
			else if(priorGrid.getPriority() == grid.getPriority()) {
				if(priorGrid.getNbMove() < grid.getNbMove()) priorGrid = grid;
			}
		}
		this.priorityQueue.remove(priorGrid);
		this.closedQueue.add(priorGrid);
		return priorGrid;
	}

 	private Grid findGridByID(int ID) {
 		Grid myGrid = new Grid();
 		for(final Grid grid: this.closedQueue) {
 			if(ID == grid.hashCode()) {
 				myGrid = grid;
 				break;
 			}
 		}
 		return myGrid;
 	}

 	public boolean isFinish() {
 		for(Tile goalTile: Grid.goal) {
 			if(goalTile.getValue() != this.priorityGrid.getTab()[goalTile.getX()][goalTile.getY()].getValue()) {
				return false;
			}
 		}
 		
 		System.out.println(" *********************** ");
 		System.out.println(" *********************** ");
 		System.out.println(" Success !!!!! ");
 		System.out.println(" Etat final atteint ");
 		this.priorityGrid.display();
 		System.out.println(" *********************** ");
 		System.out.println(" *********************** ");
 		return true;
 	}
 	
 	private void display() {
 		System.out.println();
		System.out.println();
		System.out.println("My priority Grid");
		this.priorityGrid.display();
		/*System.out.println();
		System.out.println("My priority Queue");
 		System.out.println();
		for(Grid myGrid: this.priorityQueue) {
			myGrid.getPriority();
			myGrid.display();
		}
		System.out.println();
		System.out.println();*/
		System.out.println();
		System.out.println();
 	}

 	private boolean isAlreadyInPriorQueue(Grid myGrid) {
		Grid gridToRemoved = new Grid(LENGTH);
 		for(Grid grid: this.priorityQueue) {
 			if(areEquals(myGrid,grid)) {
 				if(myGrid.getNbMove() < grid .getNbMove()) {
					gridToRemoved = grid;
 				}
 				else return true;
 			}
 		}
 		this.priorityQueue.remove(gridToRemoved);
 		return false;
 	}

 	private boolean areEquals(Grid grid1, Grid grid2) {
 		if(grid1.getColumnLength() != grid2.getColumnLength()) return false;
 		if(grid1.getRowLength() != grid2.getRowLength()) return false;
 		
 		for(int column=0; column < grid1.getColumnLength(); column++) {
 			for(int row=0; row < grid1.getRowLength(); row++) {
 				if(grid1.getTab()[row][column].getValue() != grid2.getTab()[row][column].getValue()) return false;
 			}
 		}
 		return true;
 	}

 	public List<Grid> getResult() {
 		this.result.add(this.priorityGrid);
 		Grid child = this.result.get(this.result.size()-1);
 		while(!areEquals(child,this.startGrid)) {

			for(Grid grid: this.closedQueue) {
				if(child.getParentID() == grid.hashCode()){
					this.result.add(grid);
					break;
				}
			}
			child = this.result.get(this.result.size()-1);
		}

 		return this.result;
	}
}
