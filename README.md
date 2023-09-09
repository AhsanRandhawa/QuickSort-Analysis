# QuickSort-Analysis
Investigating the use of different pivot picking strategies on the runtime of QuickSort.

## Introduction

Instead of making 4 different functions for our four different implementations for QuickSort. We have made a 
single function(quickSort) that takes the type of implementation as a character parameter, and selects the Pivot 
Index according to that.

We have implemented the partition function that was studied in class.

The number of comparisons is stored in a static variable for the class. So to initialize and return the number
of comparisons for quicksorting we have made a function called runtimeQuicksort(), which just takes the array
and the type of algorithm to be used as parameters and returns number of comparisons.

To repeat this process and take average runtime for a specific type of input array, we have implemented
the function called averageRuntimeQuicksort() that takes n(size of array),
typeQuickSort(type of Algorithm to be implemented), typeArray(type of Array to be generated using
Generate class functions), and timesRepeated(times the experiment should be repeated) as parameters.
The average number of comparisons is returned.

The varianceRuntimeQuicksort() function has the same inputs as above, but it calculates
and prints out Average, Variance, and Squared Coefficient of Variance, and returns the
variance of the runtimes of the quicksort.

The allVariances() function has no inputs, but is used to generate all of the data that we used in our 
mini research study in a user-friendly way.


## How to Use

If you wish to just see all the data we used in our research paper in an easy to use manner, just call
allVariances() in the main function,(the code already does this, so you can run the code without making any
changes). 

Alternatively you can call the runtimeQuicksort() function providing it with a specific array you made in main,
and the Char Value for the Algorithm Type(these are specified below).

Furthermore, you can call the averageRuntimeQuicksort() or the varianceRuntimeQuicksort(). For the 
former you will need to print out the return value of the function, while the latter prints it out for you.
For both of these functions you would need to give them size of array you wish to work with, Char Value
For Algorithm Type(these are specified below), Char Value For Array Type(these are specified below), 
and the time you wish the experiments to be repeated.



### Char Values For Array Type

For Random Array Input the character value is 'r'.

For Partially Sorted Array Input the character value is 'p'.

For Mostly Sorted Array Input the default character value is 'm'.

### Char Values For Algorithm Type

For Deterministic Algorithm the character value is 'd'.

For Randomized Algorithm the character value is 'r'.

For Middle Index Experiment Algorithm the character value is 'e'.

For Median Algorithm the default character value is 's'. Or Any other character that is not 'd', 'r', 'e'.

## Acknowledgements

Professor Kristen Gardner,
Javier Matos,
Ahsan Tahir
