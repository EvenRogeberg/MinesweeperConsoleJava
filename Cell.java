
public class Cell {

	int value;	// = number of neighbours that are bombs
	Cell neighbours[] = null;
	boolean bomb = false;
	boolean opened = false;	

	public Cell(int value) {
		this.value = value;
		this.neighbours = new Cell[8]; 	// 	[0] = North, [1] = North-east, [2] = East, [3] = South-east, []4 = South;
										//	[5] = South-west, [6] = West, [7] = North-west
	}
	
	//fills the neighbours array with cells next to this cell and adjusts value
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

	// shows a bomb, the value or nothing(if it has not opened yet
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
	

	/*
	 * opens the cell so the cell's value will be exposed to the player
	 * if value is 0, opens every unopened neighbour recursively  
	 * 
	 * returns a value of how many cells that was opened, a value used in 
	 * grid.play() to update the value cellsOpened.
	 */
	public int open(int number) {
		opened = true;
		int returnNumber = number;
		if(!isBomb() && value == 0) {
			for (int i = 0; i < 8; i++) {
				if(neighbours[i] != null && neighbours[i].opened == false) { //opens every neighbour that is !opened
					returnNumber += neighbours[i].open(number);			//adds 1 to returnNumber for each new cell that was opened
				}
			}
		}
		return returnNumber+1;	//+1 for this cell
	}
}
