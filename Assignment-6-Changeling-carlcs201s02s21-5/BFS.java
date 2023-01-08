
import java.util.*;
import java.io.*;

/**
 * BFS.java
 * This class conducts a Breadth First Search on a Changeling. 
 * Solution to the Assignment 6: Changelings (Part 1).
 * CS 201: Data Structures - Spring 2021
 *
 * @author Carlos Flores
 * @author Alice Cutter
 */
public class BFS {		
	/*Returns an ArrayList that is the path using the changeling input from start to target 
	*@Param input: the changeling map that the BFS is going to be done on
	*@param start: the first word of changeling 
	*@Param target: the word that you are trying to reach through the target
	*/
 	 public static ArrayList<String> BFS(Changeling input, String start, String target) {
        HashMap <String, String> potentialPaths = new HashMap<String, String>();  
        ArrayList<String> finalPath = new ArrayList<String>();
     
        Queue<String> myQueue = new LinkedList<String>();
        myQueue.add(start); // adding the start word to the queue
        potentialPaths.put(start, target);
        // varible for figuing out the shortest path
        if (input.getNeighbors(start) == null){
            return null;
        }
        while (myQueue.isEmpty() != true){ // while myQueue is not empty 
			String currWord = myQueue.remove();
            for (String currNeighbor : input.getNeighbors(currWord)){
				if (potentialPaths.containsKey(currNeighbor) != true){ // if it has not been visited yet 
								 

					// store currNeighbor AND because we are at curr neighbor of cur word 
					potentialPaths.put(currNeighbor, currWord);
                    myQueue.add(currNeighbor);
                    
                }
            }
        }
        String currKey = target;
			while (potentialPaths.get(currKey).equals(target) == false){ // while you haven't found the target yet
            	finalPath.add(currKey);
          		currKey = potentialPaths.get(currKey);
			}
                finalPath.add(currKey);

				Collections.reverse(finalPath);  
    return finalPath;
  }
  }

