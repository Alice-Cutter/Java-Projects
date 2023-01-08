import java.io.*;
import java.util.*;

/**
 * Maze.java
 * Solution to the Assignment 2: Mazes (Part 1).
 * CS 201: Data Structures - Spring 2021
 *
 * @author Eric Alexander
 * @author Sneha Narayan (modified Jan 31, 2019)
 */
public class Maze {
    private ArrayList<ArrayList<MazeSquare>> rowList;
    private int w, h;
    private int startRow, startCol, endRow, endCol;

    // MazeSquare is implemented as an inner class
    // to simplify the file structure a little bit.
    private class MazeSquare {
        private int r, c;// ASSUMING THIS IS LOCATION COORDINATES
        private boolean top, bottom, left, right,
                start, end, visited;

        private MazeSquare(int r, int c,
                           boolean top, boolean bottom, boolean left, boolean right,
                           boolean start, boolean end) {
            this.r = r;
            this.c = c;
            this.top = top;
            this.bottom = bottom;
            this.left = left;
            this.right = right;
            this.start = start;
            this.end = end;
            this.visited = false;
        }

        boolean hasTopWall() {
            return top;
        }
        boolean hasBottomWall() {
            return bottom;
        }
        boolean hasLeftWall() {
            return left;
        }
        boolean hasRightWall() {
            return right;
        }
        boolean isStart() {
            return start;
        }
        boolean isEnd() {
            return end;
        }
        int getRow() {
            return r;
        }
        int getCol() {
            return c;
        }
				void visit(){
						visited = true; 
				}
        boolean isVisited(){
            return visited;
				}
        
        
    }

    /**
     * Construct a new Maze
     */
    public Maze() {
        rowList = new ArrayList<ArrayList<MazeSquare>>();
    }

    /*
     * Returns the specified neighbor square
     *@param current MazeSquare
     *@param direction of the desired neighbor square
     *@return neighbor MazeSquare
     */
    private MazeSquare getNeighbor(MazeSquare s, String direction) {
        if (direction == "top" ) {
				    MazeSquare tempSquare = rowList.get((s.r)-1).get(s.c); 
						return tempSquare;
        } 
				else if (direction == "bottom") {
            MazeSquare tempSquare = rowList.get((s.r)+1).get(s.c); 
            return tempSquare;
        } 
				else if (direction == "left") {
					  MazeSquare tempSquare = rowList.get(s.r).get((s.c)-1); 
            return tempSquare;
        } 
				else if (direction == "right") {
            MazeSquare tempSquare = rowList.get(s.r).get((s.c)+1); 
            return tempSquare;
        }
        return null;
    }

    /*
     * Chooses a valid neighbor MazeSquare
     *@param current MazeSquare
     *@return choice of neighbor MazeSquare
     */
		private MazeSquare findNextSquare(MazeSquare m) {
      if ((m.hasTopWall() == false) && (getNeighbor(m, "top").visited == false)) {
          return getNeighbor(m, "top");
      }	 
      if ((m.hasBottomWall() == false) && (getNeighbor(m, "bottom").visited == false)){
				  return getNeighbor(m, "bottom");
			}
      if ((m.hasLeftWall() == false) && (getNeighbor(m, "left").visited == false)){
					return getNeighbor(m, "left"); 
			}
      if ((m.hasRightWall() == false) && (getNeighbor(m, "right").visited == false)){
          return getNeighbor(m, "right");
			}
			return null; 
    }
		
		/* 
    *Returns a valid path from start to end square. 
		*Returns and empty stack if there is no solution. 
    *@return LLStack with solution
    */
    private LLStack getSolution() { 
			  //Creates a new stack and pushes visited start square
        LLStack<MazeSquare> myStack = new LLStack<MazeSquare>();
        MazeSquare startSquare = rowList.get(startRow).get(startCol); 
        myStack.push(startSquare); 
        startSquare.visit(); 
				boolean running = true; // this boolean is used as a way to break out of the stack algorithm.  
				while (running){
						if (myStack.isEmpty()) {// If the stack is empty then the maze is done and it is unsolvable 
                return myStack; 
            } else {// peeking at the next mazeSquare
                MazeSquare t = myStack.peek(); 
                if ((t.c == endCol) && (t.r == endRow)){// If the square is the end square the stack is done. 
                    return myStack;
                } 
                else {
                    MazeSquare nextSquare = findNextSquare(t);
                    if (nextSquare == null) { // not visited
                        myStack.pop();// if it's a dead end and the process is repeated with the MazeSquare under it. 
                    } else {
                        myStack.push(nextSquare);// put the next valid square onto the stack. 
                        nextSquare.visit(); 
                    }
                }
            }
				}
        return null;
    }

    /**
     * Load Maze in from given file
     *
     * @param fileName the name of the file containing the Maze structure
     */
    public void load(String fileName) {

        // Create a scanner for the given file
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        // First line of file is "w h"
        String[] lineParams = scanner.nextLine().split(" ");
        w = Integer.parseInt(lineParams[0]);
        h = Integer.parseInt(lineParams[1]);

        // Second line of file is "startCol startRow"
        lineParams = scanner.nextLine().split(" ");
        startCol = Integer.parseInt(lineParams[0]);
        startRow = Integer.parseInt(lineParams[1]);

        // Third line of file is "endCol endRow"
        lineParams = scanner.nextLine().split(" ");
        endCol = Integer.parseInt(lineParams[0]);
        endRow = Integer.parseInt(lineParams[1]);

        // Read the rest of the lines (L or | or _ or -)
        String line;
        int rowNum = 0;
        boolean top, bottom, left, right;
        boolean start, end;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            rowList.add(new ArrayList<MazeSquare>());

            // Loop through each cell, creating MazeSquares
            for (int i = 0; i < line.length(); i++) {
                // For top, check row above, if there is one
                if (rowNum > 0) {
                    top = rowList.get(rowNum-1).get(i).hasBottomWall();
                } else {
                    top = true;
                }

                // For right, check cell to the right, if there is one
                if (i < line.length() - 1 ) {
                    char nextCell = line.charAt(i+1);
                    if (nextCell == 'L' || nextCell == '|') {
                        right = true;
                    } else {
                        right = false;
                    }
                } else {
                    right = true;
                }

                // For left and bottom, switch on the current character
                switch (line.charAt(i)) {
                    case 'L':
                        left = true;
                        bottom = true;
                        break;
                    case '_':
                        left = false;
                        bottom = true;
                        break;
                    case '|':
                        left = true;
                        bottom = false;
                        break;
                    case '-':
                        left = false;
                        bottom = false;
                        break;
                    default:
                        left = false;
                        bottom = false;
                }

                // Check to see if this is the start or end spot
                start = startCol == i && startRow == rowNum;
                end = endCol == i && endRow == rowNum;

                // Add a new MazeSquare
                rowList.get(rowNum).add(new MazeSquare(rowNum, i, top, bottom, left, right, start, end));
            }

            rowNum++;
        }
    }

    /**
     * Print the Maze to the Console
     */
    public void print() {

	      // Get the a stack of the solution to the Maze.
        LLStack<MazeSquare> mySolutionStack = getSolution(); 
				
        ArrayList<MazeSquare> currRow;
        MazeSquare currSquare;

        // Print each row of text based on top and left
        for (int r = 0; r < rowList.size(); r++) {
            currRow = rowList.get(r);

            // First line of text: top wall
            for (int c = 0; c < currRow.size(); c++) {
                System.out.print("+");
                if (currRow.get(c).hasTopWall()) {
                    System.out.print("-----");
                } else {
                    System.out.print("     ");
                }
            }
            System.out.println("+");

            // Second line of text: left wall then space
            for (int c = 0; c < currRow.size(); c++) {
                if (currRow.get(c).hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                System.out.print("     ");
            }
            System.out.println("|");

            // Third line of text: left wall, then space, then start/end/sol, then space
            for (int c = 0; c < currRow.size(); c++) {
                currSquare = currRow.get(c);

                if (currSquare.hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }

                System.out.print("  ");

                if (currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("SE ");
                } else if (currSquare.isStart() && !currSquare.isEnd()) {
                    System.out.print("S  ");
                } else if (!currSquare.isStart() && currSquare.isEnd()) {
                    System.out.print("E  ");
                }	else if ((!mySolutionStack.isEmpty()) && (mySolutionStack.contains(currSquare)) && ((!currSquare.isEnd())|| (!currSquare.isStart()))) {
                    System.out.print("*  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("|");

            // Fourth line of text: same as second
            for (int c = 0; c < currRow.size(); c++) {
                if (currRow.get(c).hasLeftWall()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
                System.out.print("     ");
            }
            System.out.println("|");
        }

        // Print last row of text as straight wall
        for (int c = 0; c < rowList.get(0).size(); c++) {
            System.out.print("+-----");
        }
        System.out.println("+");
    }

    // This main program acts as a simple unit test for the
    // load-from-file and print-to-System.out Maze capabilities.
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Maze mazeFile");
            System.exit(1);
        }

        Maze maze = new Maze();
        maze.load(args[0]);
        maze.print();
    }
}
