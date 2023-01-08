# Assignment 6: Changeling

## Overview
In some crossword puzzle books, there is a kind of word puzzle called a Changeling. A changeling in this context starts with a pair of words with the same number of letters. To solve the changeling, you show a sequence of words, each differing from the previous word by a single letter, that transforms the first word of the changeling into the second.

For example, one solution to the changeling "CAT to DOG" is "CAT, COT, DOT, DOG." The intermediate words must, of course, be words. Note that in this example, we have the smallest possible number of words, since CAT and DOG differ in three letter positions, and there are three letter transitions in this solution. In a contest of changeling solutions, short solutions win.

### Your task
For this assignment, you will write a program that uses a word list to compute solutions to changelings in three letter words. I've included a list of three-letter words in the files for this assignment. 

Your main class for this assignment should be called Changeling, and will be used as follows:
```
java Changeling wordlistfile firstword secondword
```
This should compute a solution to the changeling starting at `firstword` and going to `secondword` using the indicated `wordlistfile`. If the user runs the program with some number of command-line arguments other than three, the program should print an appropriate usage message and exit.

So, how are you going to compute changeling solutions? There are many ways to solve this problem, but here is the way you must do it for this assignment:


* Build an undirected, unweighted graph out of the word list file where each word is a vertex of the graph and two words/vertices are connected if they are the same length and differ by only one letter. 
* Use breadth-first search starting at the first word until you find the second word. Then print out the path, if any, that you discovered in this way.

Think about this problem before running to the computer to start coding, and remember to keep your code organized and your methods short. The program should be lots of fun to play with when you're done.

## Implementation specifics


When you implement your graph, I want you to do so using a _HashMap version_ of the adjacency-list-style implementation. HashMaps are Java's implementation of the Map ADT (review the Maps and Sets ADT video from a couple of weeks ago if you need a reminder of those operations). Similarly, HashSets are Java's implementation of the Set ADT. You will need to use both classes in this assignment.

Specifically, here is what I want you to do:

* Use a HashMap to keep track of all edges for each word. A key for this HashMap is a word from the word list; the value associated with it is a HashSet of all words it connects to. Remember that a word connects to another word if it is the same length, and if it differs by precisely one letter.

I recommend referring to the JavaDocs for the operations associated with HashMap and HashSet. Recall that the `add()` method associated with a HashSet only adds a new item if it is not already present in the HashSet. Since there can only be at most one edge between a pair of vertices, maintaining a set of unique neighbors associated with a vertex is useful in this situation.

Another important thing you should recall about HashSets is that they are unordered collections (just like HashMaps). This means that to iterate over a HashSet, you'll need to use an enhanced for loop. An example is shown below:
```
for (String word : neighborSet) {
// commands go here
} 
```

Since HashSets are unordered, your enhanced for loop might process the words in `neighborSet` in a different order every time it is run. Luckily, the order we process vertices in a given `neighborSet` does not matter for implementing the breadth-first search algorithm.

Here is some sample code to show you how to get started building a HashMap representing the graph of changeling words:

```
Map<String, Set<String>> graph = new HashMap<>();
String word = "cat";
Set<String> connections = new HashSet<>();
connections.add("car");
connections.add("cab");
connections.add("mat");
// ....
graph.put(word, connections);
```

## Grading

Here are some things we'll be looking for in your solutions:

* Properly constructing an adjacency-list-style representation of the word graph using HashMap and HashSets
* Correctly coding up the condition under which two words share an edge
* Correctly implementing the breadth-first search algorithm
* Keeping track of the shortest path as provided by the BFS algorithm, and printing it out
* Organizing code clearly and using proper Java style

You don't have to create a separate class that handles your graph, it's fine if you both build the graph and run the algorithm in a single Changeling class. However, I've given you a lot of leeway in how you can structure your program, and there's no starter code provided. Ensure that you encapsulate processes in different methods, and use good naming and documentation practices throughout.


