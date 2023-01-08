import java.util.*;
import java.io.*;

/* BFS.java
 * This class conducts creates an unweighted undirected graph for words that differ by one letter.  
 * Solution to the Assignment 6: Changelings (Part 1).
 * CS 201: Data Structures - Spring 2021
 * @author Carlos Flores
 *
 */
public class Changeling {
  // Instance Varibles 
  private HashMap <String, HashSet<String>> mapGraph ;// The hashmap works in the same way as the outermost list in the double list implementation of graphs.  	
//Constructor
	public Changeling(){
	mapGraph = new HashMap<String, HashSet<String>>();
	}


// methods: 
	/* returns a HashSet of neighbors for a given vertex 
	*@param: vertex whose neighbors will be retreived 
	*@return: the HashSet of vertex's neighbors
	*/
	public HashSet<String> getNeighbors(String vertex){
        HashSet<String> neighbors = new HashSet<String>();
        neighbors = mapGraph.get(vertex);
		return neighbors; // this is temporary 
	}
    /* takes in a file name and parses it into a arrayList of Strings 
	* @param fileName: takes in name of file that words will be read from and assumes that each word is on it's own line. 
	* @ return: returns an arrayList of the words from the file. 
    * Parts of code in the following method were taken from Assignment 4: Mazes 
    */
	public ArrayList<String> loadWords(String fileName){ // 
			
			 ArrayList<String> words = new ArrayList<String>(); 
       Scanner scanner = null; 
        try {
            scanner = new Scanner (new File (fileName)); 
        } catch (FileNotFoundException e){
            System.err.println(e); 
            System.exit(1); 
        
        }
        while (scanner.hasNextLine()){
            String tempWord = scanner.nextLine(); 
            words.add(tempWord); 
        }
        return words;
    }
	/* This method determines if two words are neighbors by having all the same characters except for one
	* @param target: The word that we are trying to compare to 
	* @param item: The word that is being compared with the target
	* @return boolean:  Helper for AddNeighbor. Returns true if two items are eligibile neighbors
	*/
	public boolean isNeighborHelper(String target, String item){
		if (target.length() != item.length()){ // This is here in case you want to use changeling on a full dictionary (not just 3 letter words)
			return false; 
		}
		int diffChar = 0; 
		
			for (int i = 0; i< item.length(); i++){
                
				if (item.charAt(i) != target.charAt(i)){
					diffChar++;
					}
                
             	if (diffChar == 2) {
              return false;
				}
                
				
			}
			return true; 
		}
	
		
	/* connects neighbors together by setting the hashsets to be connected 
	* @param target: the vertex that is being connected  
	* @param item: item is the other word that is being linked to target.
	*/
    public void addNeighbor(String target, String item){ // this updates the neighbors hashset list when a neighbor is found. This is not the method that loops through all fo the words. It is a helper for the mega method (mega method being the proper CS term). ASSUMING THAT THERE IS ALREADY AN ITEM IN THE MAP 
        if (isNeighborHelper(target, item) == true){
				
        HashSet<String> targetSet = mapGraph.get(target);
        HashSet<String> itemSet = mapGraph.get(item);
        targetSet.add(item); 
        itemSet.add(target);
        }
	}

	/* puts all of the words from the words arrayList into the graph
	*@param fileName: the name of the file used to create an arrayList through loadwords method; 
	*/
	public void assembleGraph(String fileName){ // just declaring this for now
		ArrayList<String> words = loadWords(fileName);
       
        for (int i=0;i<words.size();i++){
            HashSet <String> tempSet = new  HashSet<String> ();
			mapGraph.put(words.get(i), tempSet);
			
        }
		for (int i = 0; i < words.size(); i++ ){
			for (int j = i+1; j<words.size(); j++){
                addNeighbor(words.get(i), words.get(j));
            }
		}
	
		}
			

	
  /* This main program acts as a simple unit test for the
	* print-to-System.out Changeling capabilities.
	*/ 
	public static void main (String[] args){
        // Check if appropriate number of Arguments is given.
        if (args.length != 3){
            if (args.length < 3){
                System.out.println("Error: Arguments Given Are Less Than 3");
                System.exit(1);
            }else{
                System.out.println("Error: Arguments Given Are More Than 3");
                System.exit(1);
            }
        }
        Changeling example = new Changeling();
		example.assembleGraph(args[0]);
        System.out.println(BFS.BFS(example, args[1], args[2]));
		System.out.println("Changeling!"); 
       

    }
}