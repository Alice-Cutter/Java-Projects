# Assignment 8: Sorting Comparisons

## Goals
The goal of this assignment is to study the performance characteristics of different sorting algorithms and to get practice on how to analyze algorithms. You will also get practice articulating what an algorithm is doing in prose.

This is a _partner_ assignment (unless you were not assigned a partner), and you will be working with the same partner as for Assignments 6 and 7. You may certainly discuss the assignment with other classmates, but make sure to cite any sources used or classmates consulted.

## Overview
You've now seen a number of examples of how the runtime of algorithms can be determined analytically, by making estimates of how many steps an algorithm takes in the worst case.

In this assignment, you'll be taking a different approach to finding the runtime of sorting algorithms, by running a series of _timing experiments_. Timing experiments help you determine the runtime of a procedure experimentally, by measuring how long it takes to run for different input sizes. 

This assignment entails a mixture of coding, designing timing experiments, and analyzing and writing about your results. This will require thinking through what timing data makes sense to collect, and writing your reasoning in a short report. Your report should be typed, and **must be a PDF**. The easiest way to produce a PDF is to write your text in a text editor or a word processing program, then export or print it to PDF. On a Mac (such as the Macs in the lab), you can choose "Print" and then in the lower left corner there will be a "PDF" button. Click on that button and choose "Save as PDF". No format other than PDF is acceptable. If you are familiar with LaTeX, the PDF created from that is also a good option.

## Find an algorithm
You have now seen a few sorting algorithms in class, but there are many (maaaany!) more out there that we have not discussed. 

For this assignment, you must research, implement, and analyze a sorting algorithm that you have not seen before in either CS201 or CS111 (if you've taken it here). The sorting algorithm that you choose should have a worst-case complexity of O(n^2) or better. Possible sources of information include your textbook, the web, or other books in the library. **Note:** the sorting algorithms that are most commonly taught in CS111 and CS201, _which you should not choose_, are insertion sort, selection sort, mergesort, heapsort, and quicksort.

After picking your algorithm, you should delve into it enough to understand how it works so that you will be able to both implement it and explain it as part of your report for this assignment.

## Compare to Java
As a first step, you will implement your algorithm in Java. Specifically, you should write a method to perform your algorithm on an array of integers, and should have a method signature that looks like:

```
  public static void yourSort(int[] arr) { ... }

```
You may use whatever helper methods you need as part of this implementation.

I have provided sample code that demonstrates how to use Java's `System.currentTimeMillis()` method to get precise timing information on the execution of a piece of code. Using this code as a template, you should investigate how the performance of your sorting algorithm varies with the size of the array you are sorting, as compared to the performance of Java's built-in `Arrays.sort()` method. _You should gather enough data to be able to clearly see the long-run behavior that you anticipate from the two algorithms._ Precisely how much data depends on how you decide to investigate, but (for instance), two or three points are probably not enough to clearly see an algorithm's long term performance!

You should at least observe the behavior of the two algorithms on _random data_ and _reverse-sorted data_, though you may wish to investigate other special cases, as well. The sample code includes a method to create a randomly filled array of integers. You should be able to create your own such method for reverse-sorted data.

## The Report
Your report should be no longer than 4 pages (including images/tables) and should contain two main sections:

### Section 1: The Algorithm 
In some combination of pseudocode, natural language, and/or diagrams, describe the procedure for the sorting algorithm that you have chosen to analyze. Provide a thorough analysis of its asymptotic runtime (using Big-O analysis), including how it performs in best and worst case scenarios, along with any special cases. If there are other notable use cases or types of data on which your algorithm is particularly useful (or not!), be sure to note this as well. Although you can and should use external sources to research this algorithm, the explanation of the procedure as well as the analytic description of its runtime must be written in your own words. Any sources should be citied in a bibliography.

### Section 2: The comparison
Describe the results of your timing experiments. As part of this description, you should include:
* Justification for the different inputs you used
* A visual representation of your results across the two algorithms (this can be done in any graphing tool available to you)
* A recommendation for which algorithm to use (in which cases) based on your results, and support for this recommendation

As with any piece of writing you submit for a Carleton class, you should be sure that your presentation is professional and tuned to the proper audience. In this case, _you should anticipate that your audience is your fellow students in CS 201_. If you encounter terms and jargon in researching your chosen algorithm that you have not encountered before in class, you should define what the term means before using it in your report. 

## Other tips and suggestions
* Make sure your timing code times only the thing you actually want to time - i.e., the sorting. It shouldn't include file loading or printing to the console, as both of these can take long and variable amounts of time that may swamp true differences in execution time.
* You should use many different array sizes to explore the questions in this assignment. Make sure the arrays you're choosing are big enough to show real differences. If all of your experiments take only 1 or 2 milliseconds, it will be very hard to understand how your variations are related to differences in timing. Instead, use arrays that are big enough to take at least hundreds of milliseconds to sort.
* The time a procedure takes to run can vary even for arrays of the same length containing random data! Ensure that the data you're choosing to collect accounts for this kind of variation. 
* I recommend coming up with a plan for the kind of data you'd like to collect, and then write code that automates the data collection on a number of different runs of the procedure, with timing data written to a file after each run. 
* If you're working with a partner, you should both contribute to all parts of the assignment - experimenting and writing things up. Your sorting implementation and timing experiment code should be done while you're working in multiplayer mode, and you should collaboratively decide what timing data to collect. Both of you should be able to explain the results that you got from your timing experiments. Although you are allowed to draft parts of the writeup separately, you should still read and edit each other's drafted sections, and both of you should be aware of everything that's included in the writeup. _If you aren't able to explain why a particular section of the report includes the content it does and what the content means, then you have violated the collaboration guidelines for this assignment._
* You may find it helpful when working on this assignment to consult with the Writing Center, located at the west end of the fourth floor of the library. The consultants there are trained to help with writing in multiple disciplines, including STEM fields like CS!


## Submission and Grading

Submission for this assignment looks a little different!

Submit the _code_ for your assignment in the usual manner, by hitting the Submit button at the top right corner of the repl. All your code should be in `SortTest.java`, with the main() method running your experiments.  and Ensure your code compiles and is well documented. 

Submit your _report_ in PDF format to the Assignment 8 page on Moodle. Your report should be at most 4 pages including any tables and figures. If you're working with a partner, only one person in the partnership needs to submit the report on behalf of the team. Ensure your name (and your partner's name, if applicable) are included in the file name for the report. 

Here are some of the things the graders are going to be looking for in your submission:
* Is the explanation of the algorithm clear and easy to follow?
* Is the implementation of the sorting algorithm correctly done?
* Does the timing code just time the sorting procedure, or does it include unnecessary overhead?
* Do you make good choices for the data you collect, and justify them in your report?
* Are any graphs or tables clearly labeled and easy to read?
* Is your report well written and structured?


The instructor, the lab assistants, and the prefect are all here to help you succeed---don't hesitate to ask for help!