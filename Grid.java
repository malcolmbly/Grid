package a01;

import java.util.ArrayList;
import java.util.Random;

/**
 * Immutable class that represents a square grid filled with numbers.
 * 
 * @author CSIS-2410 + Malcolm Bailey
 *
 */
public final class Grid {
	private final int[][] grid;
	private int size;
	
	/**
	 * Creates a grid of the specified size and fills it with random n-digit numbers.
	 * @param size : the number of columms and rows for the specified grid
	 * @param n : the maximum number of digits that the random integers can have.
	 */
	public Grid (int size, int n) throws IllegalArgumentException {
		
		this.size = size;
		if (size <= 0) {
			throw new IllegalArgumentException("Array size must be a positive integer");
		}
		
		this.grid = new int[size][size];
		Random r = new Random();
		int maxInt = (int) (Math.pow(10, n) - 1);
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.grid[i][j] = r.nextInt(maxInt);
			}
		}
		
	}
	
	/**
	 * Creates a grid of the specified size and fills it with random 2-digit numbers.
	 * @param size : the number of columms and rows for the specified grid
	 */
	public Grid (int size) throws IllegalArgumentException {
				
		this(size, 2);
	}
	
	/**
	 * Creates a grid based on <code>array2d</code> provided as an argument.
	 * @param array2d two-dimensional array of numbers
	 */
	public Grid (int[][] array2d) {
		
		int row_count;
		int col_count;
		
		if (array2d.length == 0 || array2d[0].length == 0) {
			throw new IllegalArgumentException("Array passed in must not be size 0 in either dimension");
		} else {
			row_count = array2d.length;
			col_count = array2d[0].length;
		}

		
		if (row_count != col_count) {
			throw new IllegalArgumentException("All grids must be square, meaning they have the "
					+ "same number of rows and columns");
		}
		
		
		this.size = array2d.length;
		this.grid = new int[row_count][col_count];
		
		for (int i = 0; i < row_count; i++) {
			for (int j = 0; j < col_count; j++) {
				this.grid[i][j] = array2d[i][j];
			}
		} 
	}
	
	/**
	 * <p>Determines whether the number in row i and column j is the largest among its neighbors.
	 * A number is considered the largest if none of the neighboring numbers is larger.<p />
	 * 
	 * <p>A number can have a maximum of 8 neighbors (above, below, left, right, and in the diagonals). 
	 * However, if the number is situated at the boundary of the grid, it will have fewer neighbors.</p>
	 *
	 * Examples based on the following 3 x 3 grid:  
	 * <p style="font-family:monospace">      
	 *  1  2  3<br />
	 *  4  5  6<br />
	 *  7  8  9</p>
	 *  
	 * <p>Assume i = 1 and j = 1.<br />
	 * This references the number 5 and the neighbors would be 1, 2, 3, 4, 6, 7, 8, and 9.<br />
	 * The method largestNeighbor returns false.<br />
	 * <br />
	 * Assume i = 2 and j = 2. <br />
	 * This would references the number 9 and the neighbors would be 5, 6, and 8.<br />
	 * The method largestNeighbor returns true.</p>
	 * 
	 * Example based on the following 3 x 3 grid: 
	 * <p style="font-family:monospace">        
	 *  8  2  2<br />
	 *  3  5  4<br />
	 *  6  6  4</p>
	 *  
	 * <p>Assume i = 2 and j = 1. <br />
	 * This references the number 6 and the neighbors would be 3, 5, 4, 6, and 4.<br />
	 * The method largestNeighbor returns true.</p>
	 * 
	 * @param i index of the row
	 * @param j index of the column
	 * @return true if no neighboring number is larger than the number in the specified position<br />
	 *         false otherwise
	 */
	public boolean largestNeighbor(int i, int j) {

		int selectedNumber = grid[i][j];
		int row;
		int col;
		
		for (int x = -1; x <=1; x++) {
			for (int y = -1; y <= 1; y++) { 
				row = i + x;
				col = j + y;
				
				if (isInGridBounds(row, col) && grid[i+x][j+y] > selectedNumber) {
					return false;
				}

			}
		}
		
		return true;
	}
	/**
	 * Checks whether the specified row and column are within the bounds of the grid.
	 * 
	 * @param row : row index
	 * @param col : column index
	 * @return : specifies whether the coordinate is in bounds or not
	 */
	private boolean isInGridBounds(int row, int col) {
		if (row >= this.grid.length || row < 0) {
			return false;
		} else if (col >= this.grid[0].length || col < 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * <p>Determines whether the number in row i and column j is the smallest among its neighbors.
	 * A number is considered the smallest if none of the neighboring numbers is smaller.<p />
	 * 
	 * @param i index of the row
	 * @param j index of the column
	 * @return true if no neighboring number is smaller than the one in the specified cell <br />
	 *         false otherwise
	 */
	public boolean smallestNeighbor(int i, int j) {
		
		int selectedNumber = grid[i][j];
		int row;
		int col;
		
		for (int x = -1; x <=1; x++) {
			for (int y = -1; y <= 1; y++) { 
				row = i + x;
				col = j + y;
				
				if (isInGridBounds(row, col) && grid[i+x][j+y] < selectedNumber) {
					return false;
				}

			}
		}
		
		return true;
	}

	/**
	 * <p>Returns all the numbers in the grid in the following order:<br /> 
	 * It starts with the number on position i = 0, j = 0, returns all numbers from row 0 
	 * left to right, then the numbers from the next row right to left, then the numbers 
	 * from the next row left to right again, etc.</p>
	 *
	 * Example based on the following 5 x 5 grid:  
	 * <p style="font-family:monospace">      
	 *  10  11  12  13  14<br />
	 *  15  16  17  18  19<br />
	 *  20  21  22  23  24<br />
	 *  25  26  27  28  29<br />
	 *  30  31  32  33  34</p>
	 *  
	 *  <p>The numbers are returned in the following sequence:<br />
	 *  10, 11, 12, 13, 14, 19, 18, 17, 16, 15, 20, 21, 22, 23, 24, 
	 *  29, 28, 27, 26, 25, 30, 31, 32, 33, 34</p>
	 *  
	 *  <p>A 1 x 1 grid returns an Iterable that includes the numbers.<br />
	 *  A 0 x 0 grid returns an empty Iterable.</p>
	 *  
	 * @return the grid elements in the specified way
	 */
	public Iterable<Integer> snake1() {
		
		ArrayList<Integer> snake1Array = new ArrayList<Integer>();
		int rowCount = 0;
		for (int i = 0; i < this.grid.length; i ++) {
			
			if (rowCount % 2 == 0) {
				
				for (int j = 0; j < this.size; j++) {
					snake1Array.add(grid[i][j]);
				}
				
			} else {
				for (int j = this.size - 1; j >= 0; j--) {
					snake1Array.add(grid[i][j]);
				}
			}
			rowCount++;
		}
		
		return snake1Array;
	}

	/**
	 * <p>Returns all the numbers in the grid in the following order:<br /> 
	 * It starts with the number on position i = 0, j = 0, returns all numbers from column 0 
	 * top to bottom, then the numbers from the next column bottom to top, then the numbers 
	 * from the next column top to bottom again, etc.</p>
	 *
	 * Example based on the following 5 x 5 grid:  
	 * <p style="font-family:monospace">      
	 *  10  11  12  13  14<br />
	 *  15  16  17  18  19<br />
	 *  20  21  22  23  24<br />
	 *  25  26  27  28  29<br />
	 *  30  31  32  33  34</p>
	 *  
	 *  <p>The numbers are returned in the following sequence:<br />
	 *  10, 15, 20, 25, 30, 31, 26, 21, 16, 11, 12, 17, 22, 27, 32, 
	 *  33, 28, 23, 18, 13, 14, 19, 24, 29, 34</p>
	 *  
	 *  <p>A 1 x 1 grid returns an Iterable that includes the numbers.<br />
	 *  A 0 x 0 grid returns an empty Iterable.</p>
	 *  
	 * @return the grid elements in the specified way
	 */
	public Iterable<Integer> snake2() {
		
		ArrayList<Integer> snake2Array = new ArrayList<Integer>();
		int colCount = 0;
		
		for (int j = 0; j < size; j ++) {
			
			if (colCount % 2 == 0) {
				
				for (int i = 0; i < size; i++) {
					snake2Array.add(grid[i][j]);
				}
				
			} else {
				for (int i = this.size - 1; i >= 0; i--) {
					snake2Array.add(grid[i][j]);
				}
			}
			colCount ++;
		}
		
		return snake2Array;
	}
	
	/**
	 * <p>Returns the numbers in the grid as if they were accessed along a spiral path 
	 * starting with the first row.<br /> 
	 * Each element in the grid is returned exactly once.
	 * The Iterable starts with the number on position i = 0, j = 0, 
	 * returns all numbers from row 0 left to right, 
	 * then the numbers from the last column top to bottom, 
	 * then the numbers from the last row right to left, etc.</p>
	 *
	 * Example based on the following 5 x 5 grid:  
	 * <p style="font-family:monospace">      
	 *  10  11  12  13  14<br />
	 *  15  16  17  18  19<br />
	 *  20  21  22  23  24<br />
	 *  25  26  27  28  29<br />
	 *  30  31  32  33  34</p>
	 *  
	 *  <p>The numbers are returned in the following sequence:<br />
	 *  10, 11, 12, 13, 14, 19, 24, 29, 34, 33, 32, 31, 30, 25, 20, 
	 *  15, 16, 17, 18, 23, 28, 27, 26, 21, 22</p>
	 *  
	 *  <p>A 1 x 1 grid returns an Iterable that includes the numbers.<br />
	 *  A 0 x 0 grid returns an empty Iterable.</p>
	 *  
	 * @return the grid elements in the specified way
	 */
	public Iterable<Integer> spiral1() {
		
		ArrayList<Integer> spiral1Array = new ArrayList<Integer>();

		String direction = "right";
		return spiral1(this.grid, spiral1Array, direction);
	}
	
	/**
	 * The recursive version of the spiral1 method. This will continuously store elements in the list as they are traversed
	 * and then it will call the spiral method again on the remaining grid.
	 * 
	 * @param subGrid : the remaining grid to navigate with the clockwise spiral method
	 * @param spiral1Array : the stored iterable of all previous elements traversed.
	 * @param direction : the current direction of grid traversal.
	 * @return Spiral Array the stored iterable of all elements from the grid, traversed in the clockwise spiral order.
	 */
	private Iterable<Integer> spiral1(int[][] subGrid, ArrayList<Integer> spiral1Array, String direction){

		int width = subGrid[0].length;
		int height = subGrid.length;
		int[][] remainingGrid;
		
		if (width == 1 && height == 1) {
			spiral1Array.add(subGrid[0][0]);
			return spiral1Array;
		}
		if (direction.equals("right")){
			
			for (int i = 0; i< width; i ++) {
				spiral1Array.add(subGrid[0][i]);
				
			}
			
			remainingGrid = new int[height - 1][width];
			
			for (int i = 1; i < height; i++) {
				remainingGrid[i - 1] = subGrid[i];
			}
			
			direction = "down";
			
			
		} else if (direction.equals("left")) {
			
			for (int i = width - 1; i >= 0; i --) {
				spiral1Array.add(subGrid[height - 1][i]);
			}
			
			remainingGrid = new int[height - 1][width];
			
			for (int i = 0; i < height - 1; i++) {
				remainingGrid[i] = subGrid[i];
			}
			direction = "up";
			
		} else if (direction.equals("up")) {
			
			for (int i = height - 1; i >= 0 ; i --) {
				spiral1Array.add(subGrid[i][0]);
			}
			
			remainingGrid = new int[height][width - 1];
			
			for (int i = 0; i < height; i++) {
				for (int j = 1; j < width; j ++) {
					remainingGrid[i][j - 1] = subGrid[i][j];
				}
				
			}
			direction = "right";
			
		} else {
			//direction is down
			for (int i = 0; i < height ; i ++) {
				spiral1Array.add(subGrid[i][width - 1]);
			}
			
			remainingGrid = new int[height][width - 1];
			
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width - 1; j ++) {
					remainingGrid[i][j] = subGrid[i][j];
				}
				
			}
			direction = "left";
		}
		return spiral1(remainingGrid, spiral1Array, direction);
	}

	/**
	 * <p>Returns the numbers in the grid as if they were accessed along a spiral path 
	 * starting with the first column.<br /> 
	 * Each element in the grid is returned exactly once.
	 * The Iterable starts with the number on position i = 0, j = 0, 
	 * returns all numbers from column 0 top to bottom, 
	 * the numbers from the last row left to right, 
	 * then the numbers from the last column bottom to top, etc.</p>
	 *
	 * Example based on the following 5 x 5 grid:  
	 * <p style="font-family:monospace">      
	 *  10  11  12  13  14<br />
	 *  15  16  17  18  19<br />
	 *  20  21  22  23  24<br />
	 *  25  26  27  28  29<br />
	 *  30  31  32  33  34</p>
	 *  
	 *  <p>The numbers are returned in the following sequence:<br />
	 *  10, 15, 20, 25, 30, 31, 32, 33, 34, 29, 24, 19, 14, 13, 12, 
	 *  11, 16, 21, 26, 27, 28, 23, 18, 17, 22</p>
	 *  
	 *  <p>A 1 x 1 grid returns an Iterable that includes the numbers.<br />
	 *  A 0 x 0 grid returns an empty Iterable.</p>
	 *  
	 * @return the grid elements in the specified way
	 */
	public Iterable<Integer> spiral2() {
		
		ArrayList<Integer> spiral2Array = new ArrayList<Integer>();

		String direction = "down";
		return spiral2(this.grid, spiral2Array, direction);
	}
	
	/**
	 * The recursive version of the spiral1 method. This will continuously store elements in the list as they are traversed
	 * and then it will call the spiral method again on the remaining grid.
	 * 
	 * @param subGrid : the remaining grid to navigate with the counter-clockwise spiral method
	 * @param spiral2Array : the stored iterable of all previous elements traversed.
	 * @param direction : the current direction of grid traversal.
	 * @return Spiral Array the stored iterable of all elements from the grid, traversed in the counter-clockwise spiral order.
	 */
	private Iterable<Integer> spiral2(int[][] subGrid, ArrayList<Integer> spiral2Array, String direction){

		int width = subGrid[0].length;
		int height = subGrid.length;
		int[][] remainingGrid;
		
		if (width == 1 && height == 1) {
			spiral2Array.add(subGrid[0][0]);
			return spiral2Array;
		}
		if (direction.equals("left")){
			
			for (int i = width - 1; i >= 0; i --) {
				spiral2Array.add(subGrid[0][i]);
				
			}
			
			remainingGrid = new int[height - 1][width];
			
			for (int i = 1; i < height; i++) {
				remainingGrid[i - 1] = subGrid[i];
			}
			direction = "down";
			
			
		} else if (direction.equals("right")) {
			
			for (int i = 0; i < width; i ++) {
				spiral2Array.add(subGrid[height - 1][i]);
			}
			
			remainingGrid = new int[height - 1][width];
			
			for (int i = 0; i < height - 1; i++) {
				remainingGrid[i] = subGrid[i];
			}
			direction = "up";
			
		} else if (direction.equals("down")) {
			
			for (int i = 0; i < height ; i ++) {
				spiral2Array.add(subGrid[i][0]);
			}
			
			remainingGrid = new int[height][width - 1];
			
			for (int i = 0; i < height; i++) {
				for (int j = 1; j < width; j ++) {
					remainingGrid[i][j - 1] = subGrid[i][j];
				}
				
			}
			direction = "right";
			
		} else {
			//direction is up
			for (int i = height - 1; i >= 0 ; i --) {
				spiral2Array.add(subGrid[i][width - 1]);
			}
			
			remainingGrid = new int[height][width - 1];
			
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width - 1; j ++) {
					remainingGrid[i][j] = subGrid[i][j];
				}
				
			}
			direction = "left";
		}
		return spiral2(remainingGrid, spiral2Array, direction);
	}

	
	/**
	 * <p>
	 * Returns a string that displays the grid elements right-aligned in straight columns.<br />
	 * The number of columns matches the number of columns in the grid. The same is true 
	 * for the order of the elements.<br />
	 * The width of the columns depends on the largest number in the grid. It is one larger 
	 * than its number of digits, ensuring a space between the columns. If all grid elements have
	 * the same number of digits, all numbers will be preceded by exactly one space.
	 * </p>
	 * 	 
	 * Example: based on the following 4 x 4 grid:  
	 * <p style="font-family:monospace">      
	 *   10  20  30  40<br />
	 *    2  33 444  55<br />
	 *  123  23 345   5<br />
	 *    0   1   2   3</p>
	 * 
	 * <p>The largest numbers in the grid is a 3-digit numbers, which results in a column-width 4.
	 * There is no space after the last element of a row.<br />
	 * 
	 *  <p>A 1 x 1 grid returns a string that includes the number preceded by one space.<br />
	 *  A 0 x 0 grid returns an empty string.</p>
	 *  
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		StringBuilder gridString = new StringBuilder();
		
		for (int i = 0; i< this.grid.length; i++) {
			gridString.append("|");
			for (int j = 0; j < this.grid[0].length; j++) {
				
				String rowString = String.format("%5d", grid[i][j]);
				gridString.append(rowString);
			}
			
			gridString.append("   |\n");
		}
		
		return gridString.toString();
	}
	
	// = = = test client for your own testing = = = 
	
	/**
	 * Test client 
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Grid testGrid = new Grid(4, 2);
		System.out.println("Example Grid:");
		System.out.print(testGrid);
		Iterable<Integer> snake1 = testGrid.snake1();
		Iterable<Integer> snake2 = testGrid.snake2();
		Iterable<Integer> spiral1 = testGrid.spiral1();
		Iterable<Integer> spiral2 = testGrid.spiral2();

		System.out.println("Grid Traversals:");
		System.out.println("Snake1");
		for (Integer number: snake1) {
			System.out.print(number + " ");
		}
		System.out.println();
		System.out.println("Snake2");
		for (Integer number: snake2) {
			System.out.print( number + " ");
		}
		System.out.println();
		System.out.println("Spiral1");
		for (Integer number: spiral1) {
			System.out.print(number + " ");
		}
		System.out.println();
		System.out.println("Spiral2");
		for (Integer number: spiral2) {
			System.out.print(number + " ");
		}
		
	}
}
