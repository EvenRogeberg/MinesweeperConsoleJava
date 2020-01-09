import java.util.*;
public class Main {
	
	static int rows;
	static int columns;
	static int totalCells;
	static int bombs;
	static Grid grid;
	
	public static void main(String[] args) {
		
		setup();
		grid.printGrid();
		grid.play();

	}
	
	public static void setup() {
		Scanner in = new Scanner(System.in);
		System.out.println("How many rows and columns should it?");
		System.out.println("Enter number of rows: ");
		rows = in.nextInt();
		System.out.println("Enter number of columns: ");
		columns = in.nextInt();
		totalCells = rows*columns;
		System.out.println("number of cells = "+totalCells+", how many of them should be bombs?");
		bombs = in.nextInt();
		System.out.println("rows = "+rows+", columns = "+columns+", total cells = "+totalCells+", total bombs = "+bombs);
		
		grid = new Grid(rows, columns, bombs);
	}
	

}
