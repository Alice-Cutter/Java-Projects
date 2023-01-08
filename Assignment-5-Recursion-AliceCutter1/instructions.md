# Assignment 5: Recursion

## Goals
The goal of this assignment is to get more practice with recursion. You will also get to cement your knowledge of linked lists and interfaces.

This is an **individual assignment** – you must turn in your own code for the assignment. You may discuss ideas for the problems at a high level with classmates but cannot write code together. Please submit your work by clicking Submit at the top right of this repl; the **due date is Mon May 10 at 10pm CT.**

## The Comparable Interface
The Comparable interface is an interface in Java that allows for there to be a total ordering of the objects in each class that implements it. This means that any two objects of a class that implements this interface will be _comparable_ with one another, and we will be able to tell whether one of them is greater than, less than, or equal to the other. 

In particular, any class that implements the Comparable interface must implement a `compareTo` method. This method compares the object calling the method to the object passed as a parameter, and returns a negative integer, zero, or a positive integer if this object is less than, equal to, or greater than the given object respectively. 

A few examples of classes we have seen that implement the comparable interface are: String, Integer, and Double. Consider the following snippet of code:
```
Integer one = new Integer(1);
Integer two = new Integer(2);
int comp = one.compareTo(two);
System.out.println(comp);
```
This would print a negative number (-1) since 1 is less than 2. 

Similarly, consider the following snippet of code:

```
String one = "one";
String two = "two"
int comp = two.compareTo(one);
System.out.println(comp);
```

This will print a positive number (5) since “two” comes later alphabetically (hence is greater) than “one”.

## Your Task
I’ve provided you with a file called `RecursiveLinkedList.java` that implements the List ADT using a LinkedList structure. This code only works with items that implement that Comparable interface. You should spend some time just looking through this code. In addition to public methods, there are a number of private helper methods included. Take note of how these helper methods can make some of the code for other methods (e.g. `public E remove(int index)`) much cleaner and easier to understand. Much of this code is taken straight from your textbook, but it’s nice to see it all in one place.

Your task with this assignment will be to write the code to add the following three methods to this class. To receive full credit, you must implement each of these methods using recursion and your implementation of each must run in O(n) time, where n is the number of elements in the list. The method signatures for these methods are already in the file containing the starter code.

```
/**
 * Return the maximum element in the list using
 * compareTo() method of Comparable.
 *
 * @return maximum element of the list
 **/
public E max()
{
    // YOUR CODE WILL GO HERE
    // You will likely want to use a helper method
    return null;
}
/**
 * Remove all elements that match element using the 
 * equals() operator to determine a match. 
 * (Don't use ==).
 *
 * @param element The element that should be removed
 **/
public void removeAll(E element)
{
    // YOUR CODE WILL GO HERE
    // You will likely want to use a helper method
}
/**
 * Duplicate each element of the list
 *
 * For example, the list [ 0 1 2 ] duplicated becomes 
 * [ 0 0 1 1 2 2 ]
 **/
public void duplicate()
{
    // YOUR CODE WILL GO HERE
    // You will likely want to use a helper method!
}
```

When you're done writing these methods, test them thoroughly in the main method of the class. **Showing that you've tested your methods in `main()` will count towards your grade on this assignment.**

## Hints
* I suggest you take my suggestion about using a helper method seriously. The key thing here will be to think about how to define the signature of that method.
* It’s totally fine if all the recursion is done in your helper methods, rather than the specific methods I am asking you to implement.
* To get full credit on this assignment, all methods need to be implemented recursively (no loops!) and need to run in O(n). 