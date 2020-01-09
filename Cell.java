
public class Cell {

	int value;
	Cell neighbours[] = null;
	boolean bomb = false;
	boolean opened = false;

	public Cell(int value) {
		this.value = value;
		this.neighbours = new Cell[8]; 	//0 = North, 1 = North-east, 2 = East, 3 = South-east, 4 = South;
		//5 = South-west, 6 = West, 7 = North-west
	}

	public void addNB(Cell cell, int pos) {
		//	System.out.println("cell number "+cell.number+" added at pos "+pos+", for cell "+number);
		neighbours[pos] = cell;
		if(cell.isBomb()) {
			value++;
		}
	}

	public void setBomb() {
		bomb = true;
	}

	public boolean isBomb() {
		if (bomb) {
			return true;
		} else {
			return false;
		}
	}

	public Cell getCell() {
		return this;
	}

	public String show() {
		if (opened) {
			if (isBomb()) {
				return "o*";
			} else{
				return ""+value;
			}
		} else {
			return "";
		}
	}

	//opens the cell so the cell's value will be exposed to the player
	//if value is 0, opens every unopened neighbour recursively  
	public int open(int cellsOpened) {
		opened = true;
	//	System.out.println("cellsOpened recived = "+cellsOpened); //0
		int recursiveResult = cellsOpened;
		if(!isBomb() && value == 0) {
			for (int i = 0; i < 8; i++) {
				if(neighbours[i] != null && neighbours[i].opened == false) { //opens every neighbours,
			//		System.out.println(cellsOpened+" = cellsOpened in open");
					recursiveResult += neighbours[i].open(cellsOpened);		
				}
			}
		}
	//	System.out.println("returns "+(recursiveResult+1));
		return recursiveResult+1;
	}


}
