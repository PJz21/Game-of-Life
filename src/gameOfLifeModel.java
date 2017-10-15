
/**
 *  Holds all of the logic for the program
 */
public class gameOfLifeModel {

    /**
     * Iterates through the grid and randomly assigns -1 or 1 to each cell
     * @param grid  //input grid to iterate through
     * @return      //return grid with changes
     */
    private int[][] generateGrid(int[][] grid) {

        //Iterate through the grid and assign either a 0 or 1 to each cell randomly
    	for (int i = 0; i < grid.length; ++i) {
    		for (int j = 0; j < grid.length; ++j) {
    		    //generate numbers between -1 and 1
    		    int rand = (int) (Math.random() * 3) - 1; //built in function
    		    if (rand != 0) {
                    grid[i][j] = rand;
                } else {
    		        j--; //move back one cell and regenerate a random number
                }
    		}
    	}

        return grid;
    }

    /**
     * For a cell i, j the surrounding cells are checked to see whether they are alive or dead
     * If they are alive the aliveCount is increased by one
     * @param grid  //input grid to iterate through
     * @param i     //row number
     * @param j     //column number
     * @return      //return aliveCount
     */
    private int aliveCount(int[][] grid, int i, int j) {

        int aliveCount = 0;

        //Iterate through a 3x3 grid where the center cell is the cell changing state and sums the number of surrounding alive cells
        for (int y = -1; y < 2; ++y) {
            for (int x = -1; x < 2; ++x) {
                //Ignore x = 0 and y = 0 because that is the cell changing state and does not need to be included
                if (!((x == 0) && (y == 0))) {
                    if (grid[wrapGrid(i, x, grid)][wrapGrid(j, y, grid)] > 0) {
                        aliveCount++; //increase alive count
                    }
                }
            }
        }

        return aliveCount;
    }

    /**
     * For a cell row or column number and the horizontal or vertical modifier return the row or column number to be checked
     * If the row or column number to be checked is outside of the array then the opposite side will be returned
     * @param i     //row number or column number the cell
     * @param x     //horizontal or vertical modifier for iterating across 3 cells
     * @param grid  //input grid for the length
     * @return      //return the cell row number to be checked
     */
    private int wrapGrid(int i, int x, int[][] grid) {

        //If the neighbouring cell is outside of the bounds set it to the opposite vertical side of the grid
        if ((i + x) < 0) {
            i = grid.length - 1; //bottom row or left column
        } else if ((i + x) >= grid.length) {
            i = 0;  //top row or right column
        } else {
            i += x;
        }
        return i;
    }

    /**
     * Iterates through the grid and enter a cell into the aliveCount method to find the alive count
     * If the alive count = 3 and the cell is dead then the cell state is set to alive
     * If the alive count is < 2 or > 3 and the cell is alive then the cell state is set to dead
     * Increases the count on the cell to reflect how many iterations it has been alive
     * The changes are made to a duplicate grid so that the changes do not affect the alive count
     * @param grid  //grid to iterate through
     * @return      //return the duplicate grid with the changes
     */
    private int[][] updateGrid(int[][] grid) {

        //Create a new array equal to the original so that when a cell's state changes it doesn't affect the count
    	int[][] gridCopy = new int[grid.length][grid.length];

    	//Iterate through the grid and count the number of surrounding cells that are alive
        for (int i = 0; i < (grid.length); ++i) {
            for (int j = 0; j < (grid.length); ++j) {
                //If the alive count is equal to 3 and the cell is alive then set the cell state to alive
                if ((grid[i][j] < 0 && aliveCount(grid, i, j) == 3)) {
                    gridCopy[i][j] = 1; //> 0 is alive
                    //If the alive count is < 2 or > 3 and the cell is dead then set the cell state to dead
                } else if (grid[i][j] > 0 && (aliveCount(grid, i, j) < 2 || aliveCount(grid, i, j) > 3)){
                    gridCopy[i][j] = -1; //< 0 is dead
                } else {
                    gridCopy[i][j] = grid[i][j]; //stay the same
                    if (gridCopy[i][j] > 0) {
                        gridCopy[i][j]++; //increase number of iterations alive by 1
                    } else if (gridCopy[i][j] < 0){
                        gridCopy[i][j]--; //increase number of iterations dead by -1
                    }
                }
            }
        }
        return gridCopy;
    }

    /**
     * Iterates through the grid to check whether all cells are alive or dead
     * @param grid  //grid to iterate through
     * @return      //returns true if all cells are dead, else false
     */
    private boolean dead(int[][] grid) {

        int aliveCellCount = 0;

        //Iterate through the grid and increase the count if a cell is alive
        for (int i = 0; i < grid.length; ++i){
            for (int j = 0; j < grid.length; ++j) {
                if (grid[i][j] >= 1) {
                    aliveCellCount++;
                }
            }
        }

        //If all cells are dead then return true, else false
        if (aliveCellCount == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Getter for the generateGrid method
     * @param grid  //input grid for random generation
     * @return      //return the grid after being generated
     */
    public int[][] getGenerateGrid(int[][] grid) {

        return generateGrid(grid);
    }

    /**
     * Getter for the updateGrid method
     * @param grid  //input grid to be updated
     * @return      //return the grid after being updated
     */
    public int[][] getUpdateGrid(int[][] grid) {

        return updateGrid(grid);
    }

    /**
     * Getter for the dead method
     * @param grid //input grid to be checked whether all cells are dead
     * @return      //return true if all the cells are dead, else false
     */
    public boolean getDead(int[][] grid) {

        return dead(grid);
    }
}