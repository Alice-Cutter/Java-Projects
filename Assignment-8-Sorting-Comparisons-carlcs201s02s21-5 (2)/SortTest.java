import java.util.*;
import java.lang.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

/**
 * Class for your sort investigations, exploring how changing array size and
 * type affects runtime.
 * You'll modify main, and you're welcome to add any additional
 * methods you'd like.  
 *
 * @author Anna Rafferty
 * @author Layla Oesper
 * @author Eric Alexander
 * @author Titus Klinge
 * @author Sneha Narayan
 * @author Carlos Santos, Alice Cutter 
 */
public class SortTest {

    /**
     * Helper function you may decide to use to print out a given array to the console.
     *
     * @param arr the array to print
     */
    private static void printArrss(int[] arr) {
        StringJoiner sj = new StringJoiner (", ", "[", "]");
        for (int i = 0; i < arr.length; i++) {
            sj.add(Integer.toString(arr[i]));
        }
        System.out.println(sj.toString());
    }

    /**
     * Generates a pseudo-random permutation of the integers from 0 to a.length - 1.
     * See p. 139 of "Seminumerical Algorithms, 2nd edition," by Donald Knuth.
     */
    public static void fillAndShuffle(int[] a) {
        // Fill the array with the integers from 0 to a.length - 1.
        int k;
        for (k = 0; k < a.length; k++) {
            a[k] = k;
        }

        // Shuffle.
        for (k = a.length - 1; k > 0; k--) {
            int swapIndex = (int)Math.floor(Math.random() * (k + 1));
            int temp = a[k];
            a[k] = a[swapIndex];
            a[swapIndex] = temp;
        }
    }
	
/* THIS CODE WAS TAKEN FROM W3 SCHOOLS.
@param String input: this can be whatever I thought it would be helpful have the type of test recorded in the file with the idea that we run the same test a bunch of times. 
*/
	
  public static void recordData(String fileName, String input, long result) {
    try {
      FileWriter myWriter = new FileWriter(fileName, true);
	  myWriter.write ("\n"); 
	  myWriter.write(input + "  " + result );
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  
}

	/*This method runs the main test for the different types of sorting algorithms
	*/
	public static void test (){
			
		int [] originalArray = new int [1000]; 
		fillAndShuffle(originalArray);
		int [] pancakeArray = originalArray.clone();
		// Preform the sort test for orginalArray 
		long startTimeOg = System.currentTimeMillis(); // start the timer
    // printArrss(pancakeArray);
       pSort(pancakeArray, pancakeArray.length);
		// Arrays.sort(almostPerfect());
        long endTimeOg = System.currentTimeMillis(); // end the timer 
		long timeDifferenceOg = endTimeOg - startTimeOg; 
	    // System.out.println(originalArray);
		recordData ("javaSortData.txt" , "" , timeDifferenceOg); 

		
 
	}
   
	/*pSort is the main pancake sort method 
	* @param int[] arr: unsorted int array that will be sorted 
	* @param int stop: The stop variable can be used to only sort a portion of the array
	*/
		public static  void pSort(int [] arr , int stop){
		
		for (int currentSize = stop; currentSize>1; currentSize--){// This sets up the method to run through until the end of the array  
		int flipIndex = 0;
		flipIndex = findMax (arr, currentSize); // finds the place where the array should be flipped based off of the max value of that given subarray 

		if (flipIndex != stop-1){
			flip (arr, flipIndex); 
			flip(arr, currentSize-1); 
		}
		}
    System.out.println("SORTED (PANCAKE): ");
    printArrss(arr);
	

	}

	
	/* works as the flip method for pSort. Reverses an array
	* @param int [] arr : the array getting flipped 
	* @ param int place : the place holder value for what is getting flipped 
	*@ return int []:  the flipped array 
	*/

	public static int [] flip (int [] arr, int place){ // flips the array given an index 
		int temp = 0; 
		int start = 0; 
		while (start < place){
			temp = arr[start];
			arr[start] = arr[place]; 
			arr[place] = temp; 
			 
			start++; 
			place --; 
		}


		return arr; 

	}
	
	/* This method generates an array that is sorted in descending order with a random 1% of the array elements being randomly assigned. 
	* @return: returns the almost perfect array
	*/
	public static int [] almostPerfect (){
		int [] almostPer = new int [1000];
		for (int i = almostPer.length-1; i > -1; i--){ 
			almostPer [i] = i; // this loads the ordred array in 
		}
		for (int j = 0; j < 10 ; j++){
      Random rand = new Random(); //instance of random class
      int upperbound = almostPer.length - 1;
        //generate random values from 0-length
        int newNumIndex = rand.nextInt(upperbound); 
        int newNumValue = rand.nextInt(1000);
        almostPer[newNumIndex] = newNumValue;
		}
        return almostPer;
	}
	/* Method returns a new filled reversed Array 
	* @return int []: the newly created array in descending order
	*/
	public static int [] reversedArray (){
		 int [] reversedArr = new int [1000];
		for (int i = reversedArr.length-1; i >-1; i--){  
			reversedArr [i] = i;
		
        reversedArr[i] = i;
        }
        return reversedArr;
	}
	/* Returns the maximum value from an integer array which starts 0 to bound 
	* @param int [] arr: the array that is being searched for the maximum value
	*@param int bound: the stopping point for the array the index of the end of the subarray being search 
	*/
	public static int findMax( int [] arr, int bound){ 
		int max = Integer.MIN_VALUE ; // 
		  
		 int maxIndex = -1; 
		for (int i = 0; i < bound; i++){
			if (arr[i] > max){
				max = arr[i]; 
				maxIndex = i; 
			}
		}
		
		return maxIndex; 
	}


 

 public static void main(String[] args) {
    // System.err.println("Investigation not yet designed and carried out!");
	for ( int i = 0; i < 500; i++){
            test();

    	}
    }
}