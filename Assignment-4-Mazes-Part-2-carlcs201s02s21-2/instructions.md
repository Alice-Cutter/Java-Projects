# Assignment 4: Mazes (Part 2)

## Goals
This assignment is a continuation of Assignment 2, and you will be working on it with the same partner that you had for Assignment 3. Whereas in Part 1 we were just concerned with printing the maze, this time we will be using a stack to actually solve the maze! There are many maze-solving algorithms out there, but in particular you'll be implementing a stack-based algorithm generally known as _depth-first search_. A description of the algorithm is included later in these instructions.

You will need a working solution to Assignment 3 before starting this assignment. I have included a sample solution in this repl (`Maze.java`), which you are welcome to use as a starting point. This solution implements MazeSquare as an inner class rather than a separate class to simplify things a bit. 

In this assignment, **you are not required to implement a stack**, instead you're provided with a working implementation named LLStack. The summary of the Stack ADT it implements is in the The LLStack Class section below.

## Depth-First Search
Here's a description of the depth-first search algorithm you will be implementing in prose. Ensure you understand the procedure and have a sense of why this would work to produce a valid solution. I suggest walking through these steps with a picture of a maze to help you visualize how this algorithm works. 

* Mark every square in the maze as "unvisited."
* Create an empty stack of MazeSquare objects.
* Push the start square onto the stack, and mark the start square as "visited".
* Loop:
    - If the stack is empty, then you are done and the maze is unsolvable. 

    - Otherwise, peek at the square at the top of the stack--let's call it T.

    - If T is the finish square, then you are done and the stack contains a solution to the maze (with the start square at the bottom of the stack and the finish square at the top).

    - If all of the squares that are adjacent to T in the maze (i.e., the squares up, down, left, or right from T--no diagonals) are either blocked by a wall or have already been marked as visited, pop T off of the stack and continue on.

    - Else, select a square that is adjacent to T, unvisited, and not blocked by a wall. Mark it as visited and push it on to the stack.

## The LLStack Class
The stack implementation you'll be using for this assignment is given to you in this repl. It is called LLStack (for "linked list stack"), and allows you to create stacks with the following methods:

| Method           | Return Type | Description                                                    |
|------------------|-------------|----------------------------------------------------------------|
| push(E item)     | void        | Add a given item to top of stack                               |
| peek()           | E           | Return the item at the top of the stack (without removing it). |
| pop()            | E           | Return and remove the item at the top of the stack.            |
| size()           | int         | Return the number of items in the queue.                       |
| isEmpty()        | boolean     | Return whether or not the queue has no items.                  |
| contains(E item) | boolean     | Return whether or not the stack contains the given item.       |

**Note:** the contains() method will be particularly useful when you want to decide whether or not to print a * character for a given MazeSquare.

## Your Task

Your assignment is to modify Maze.java to support solving mazes using the above algorithm, and printing the solved version. Specifically, you need to add a method to Maze.java with the following signature:
```
/**
* Computes and returns a solution to this maze. If there are multiple 
* solutions, only one is returned, and getSolution() makes no guarantees about 
* which one. However, the returned solution will not include visits to dead 
* ends or any backtracks, even if backtracking occurs during the solution 
* process.
*
* @return a LLStack of MazeSquare objects containing the sequence of squares
*         visited to go from the start square (bottom of the stack) to the 
*         finish square (top of the stack).
*/
public LLStack<MazeSquare> getSolution()
```

This method should then be called by the `print()` method so that each square in the maze that is part of the solution is marked with *. For example, if the following is input as `maze.txt`:
```
6 5
0 0
0 4
L-_|_-
|L--|_
|-_-|_
|L|L||
L__L__
```
Then the following should be print out to the console:
```
+-----+-----+-----+-----+-----+-----+
|                 |                 |
|  S     *        |                 |
|                 |                 |
+-----+     +-----+     +-----+     +
|     |                 |           |
|     |  *     *     *  |           |
|     |                 |           |
+     +-----+     +     +     +-----+
|                       |           |
|  *     *     *     *  |           |
|                       |           |
+     +     +-----+     +     +-----+
|     |     |     |     |     |     |
|  *  |     |     |     |     |     |
|     |     |     |     |     |     |
+     +-----+     +-----+     +     +
|                 |                 |
|  F              |                 |
|                 |                 |
+-----+-----+-----+-----+-----+-----+

```
Note that your solution for the same input file might look slightly different from the one shown here, since there are multiple valid paths to the Finish square in this maze. 

## Additional Hints

Apart from the requirement that you use the algorithm specified above as well as the LLStack class, how you implement this is up to you! However, here are the steps I might consider taking:

* Add a visited instance variable to the MazeSquare class. This should be initialized to `false` by the MazeSquare constructor.
* Add associated methods `isVisited()` (which checks to see if the MazeSquare is visited) and `visit()` (which changes the visited instance variable to true) to the MazeSquare class.
* Write a helper method to the Maze class called `getNeighbor(MazeSquare s, String direction)` which takes in a given MazeSquare and a direction ("left", "right", "top", "bottom"), and returns a reference to the MazeSquare in that direction in the maze. This will be helpful to have when writing `getSolution()`.
* Implement the `getSolution()` method, following the algorithm written above.
* Call `getSolution()` in the beginning of the `print() ` method, storing the stack it returns in a variable. 
* For each MazeSquare that you print out in the print() method, check to see if it is contained in the solution (using the LLStack's `contains()` method). If it is, then write a * character in the middle.

There are other ways to go about solving this problem, but I believe that the above steps will be a good way of approaching it.

## Submission and Grading
This assignment is worth 20 points. Submit your solution when you're done by clicking the Submit button at the top right of the repl. Below is a partial list of the things that we'll look for when evaluating your work.

* Does your program print out a correct solution to a given maze?
* Are you able to handle mazes that have no solution (by drawing them without a solution marked)?
* Do you properly implement the stack algorithm to return a solution to the maze?
* How efficient is your code? Are you looping unnecessarily through the stack data structure, or are you making good use of its constant-time operations?
* Do your classes exhibit good organization and commenting? Don't forget to follow the Java Style Guidelines!
* Start early, ask lots of questions, and have fun! The instructor, the lab assistants, and the prefect are all here to help you succeed---don't hesitate to ask for help!