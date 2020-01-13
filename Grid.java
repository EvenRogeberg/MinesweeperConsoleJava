import java.util.*;

public class Grid {

	boolean gameover = false;
	int rows;
	int columns;
	int totalCells;
	int bombs;
	Cell[][] grid;
	ArrayList<Cell> allBombs = new ArrayList<Cell>();

	public Grid(int rows, int columns, int bombs) {
		this.rows = rows;
		this.columns = columns;
		this.totalCells = rows * columns;
		this.bombs = bombs;
		fillGrid();
		System.out.println(
				"\nNew grid made \nrows = " + rows + "\ncolumns = " + columns + "\ntotal cells = " + totalCells);

	}

	public void printGrid() {

		/*
		 * for (int i = 0; i < rows; i++) { for (int j = 0; j < columns; j++) {
		 * System.out.print(String.format("%-4s%-4d]", "[", grid[i][j].number)); }
		 * System.out.println(); }
		 */

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(String.format("%-4s%-4s]", "[", grid[i][j].show()));
			}
			System.out.println();
		}

	}

	public void fillGrid() {

		grid = new Cell[rows][columns];

		int counter = 1;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = new Cell(0);
				System.out.println("Cell number " + counter + " inserted at [" + i + "][" + j + "]");
				counter++;
			}
		}
		placeBombs();

		// add neighbours
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				// North Cell
				if (i > 0) {
					grid[i][j].addNB(grid[i - 1][j], 0);
				}
				// North-east Cell
				if (i > 0 && j < columns - 1) {
					grid[i][j].addNB(grid[i - 1][j + 1], 1);
				}
				// East Cell
				if (j < columns - 1) {
					grid[i][j].addNB(grid[i][j + 1], 2);
				}
				// South-east Cell
				if (i < rows - 1 && j < columns - 1) {
					grid[i][j].addNB(grid[i + 1][j + 1], 3);
				}
				// South Cell
				if (i < rows - 1) {
					grid[i][j].addNB(grid[i + 1][j], 4);
				}
				// South-west Cell
				if (i < rows - 1 && j > 0) {
					grid[i][j].addNB(grid[i + 1][j - 1], 5);
				}
				// West Cell
				if (j > 0) {
					grid[i][j].addNB(grid[i][j - 1], 6);
				}
				// North-west Cell
				if (j > 0 && i > 0) {
					grid[i][j].addNB(grid[i - 1][j - 1], 7);
				}
			}
		}
	}

	public void placeBombs() {
		Random random = new Random();
		while (allBombs.size() < bombs) {
			int randomRow = random.nextInt(rows);
			int randomCol = random.nextInt(columns);

			if (!grid[randomRow][randomCol].isBomb()) {
				grid[randomRow][randomCol].setBomb();
				allBombs.add(grid[randomRow][randomCol]);
				System.out.printf("BOMB PLACED AT [%d][%d]\n", randomRow+1, randomCol+1);
			}
		}
	}

	public void play() {
		Scanner input = new Scanner(System.in);
		System.out.println("select the [row][column] you want to open");
		int cellsOpened = 0;

		while (!gameover || (cellsOpened + bombs == totalCells)) {
			
			//part 1 select a valid row and column
			int selectedRow = -1; // <0 is unvalid range
			int selectedColumn = -1; // <0 is unvalid range
			boolean validRange = false;
			while (!validRange) {
				System.out.printf("select a row [%d-%d] = ", 1, rows);
				selectedRow = input.nextInt();
				if ((selectedRow > 0) && (selectedRow <= rows)) {
					validRange = true;
				} else {
					System.out.println("please enter a number in the given range");
				}
			}
			selectedRow--; // since first row is row 0
			validRange = false;

			while (!validRange) {
				System.out.printf("select a column [%d-%d] = ", 1, columns);
				selectedColumn = input.nextInt();
				if ((selectedColumn > 0) && (selectedColumn <= columns)) {
					validRange = true;
				} else {
					System.out.println("please enter a number in the given range");
				}
			}
			selectedColumn--; // since first column is column 0
			
			
			//part 2 open the selected cell
			if (grid[selectedRow][selectedColumn].opened) {
				System.out.println("The selected cell is allready opened, please select another one");
			} else {
				if (grid[selectedRow][selectedColumn].isBomb()) {
					cellsOpened += grid[selectedRow][selectedColumn].open(0);
					grid[selectedRow][selectedColumn].show();
					System.out.println("\tBOOM\nGAME OVER");
					showAll();
					gameover = true;
					break;
				} else {
					cellsOpened += grid[selectedRow][selectedColumn].open(0);
					grid[selectedRow][selectedColumn].show();
				}

				if ((cellsOpened + bombs) == totalCells) {
					for (int i = 0; i < allBombs.size(); i++) {
						allBombs.get(i).open(0);
					}
					System.out.println("nice job, you found all the bombs!");
					printGrid();
					break;

				}
				printGrid();
			}
		}
	}

	public void showAll() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j].opened = true;
				
			}
		}
		printGrid();
	}
}
