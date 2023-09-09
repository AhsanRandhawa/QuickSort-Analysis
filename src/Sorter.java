import java.util.Random;

/*  Authors: Ahsan Tahir | Javier Matos
    Date Completed: 12th April 2021
 */

public class Sorter
{
    static int comparisons;

    /**
     *
     * @param array
     * @param x
     * @param y
     */
    static void swap(int[] array, int x, int y)
    {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     *
     * @param array
     * @param lo
     * @param hi
     * @param type
     */
    static void quickSort(int[] array, int lo, int hi, char type)
    // Sorts all of a given arrays elements between lo to hi, by using different algorithms based on "type".
    // type == 'd' for deterministic Algorithm | type == 'r' for Randomized Algorithm | type == 'e' for Middle Index Experiment Algorithm | else it is Median Experiment Algorithm
    // isRandom is false for deterministic Algorithm, True for Random Algorithm
    {
        if (hi > lo)
        {
            int randomIndex = hi; //Deterministic
            if(type == 'r')  // Random Index
            {
                randomIndex = (int)(Math.random() * (hi - lo + 1)) + lo; // Generates A Random number between hi and lo Inclusive
            }
            else if(type == 'e') // Middle Index Experiment
            {
                randomIndex = (hi + lo)/2;
            }
            else if(type != 'd') //Median Experiment
            {
                //The following code basically arranges elements array[lo], array[(hi+lo)/2], array[hi] in ascending order. (simple bubble sort)
                comparisons += 3;
                if(array[lo] > array[(hi + lo)/2])
                    swap(array, lo, (hi + lo)/2);
                if(array[(hi + lo)/2] > array[hi])
                    swap(array, (hi + lo)/2, hi);
                if(array[lo] > array[(hi + lo)/2])
                    swap(array, lo, (hi + lo) / 2);

                randomIndex = (hi + lo) / 2;

            }

            int partitionIndex = partition(array, lo, hi, randomIndex);
            quickSort(array, lo, partitionIndex - 1, type);
            quickSort(array, partitionIndex + 1, hi, type);

        }
    }
    /**
     *
     * @param array
     * @param lo
     * @param hi
     * @param partitionIndex
     * @return
     */
    static int partition(int[] array, int lo, int hi, int partitionIndex)
    //The Partition function that we learned in class.
    {
        swap(array, hi, partitionIndex);
        int j = hi - 1;
        int i = lo;
        int x = array[hi];
        while(i <= j)
        {
            comparisons++;
            if(array[i] <= x)  i++;
            else
            {
                swap(array, i, j);
                j--;
            }

        }
        swap(array, hi, j + 1);
        return j + 1;
    }
    /**
     *
     * @param array
     * @param type
     * @return
     */
    static int runtimeQuicksort(int[] array, char type)
    //Given an array outputs the number of comparisons made to do QuickSort Algorithm of TYPE: type.
    {
        comparisons = 0; // initializes comparisons
        // if (type == 'd' || type =='s')
        //     shuffle(array);

        quickSort(array, 0, array.length - 1, type);
        return comparisons;
    }

    static void shuffle(int[] array)
    {
        int n = array.length;
        for(int i = n-1; i >= 1; i--)
        {
            int rand = (int) (Math.random() * (i + 1));
            swap(array, i, rand);
        }
    }
    /**
     *
     * @param n
     * @param typeQuickSort
     * @param typeArray
     * @param timesRepeated
     * @return
     */
    static int averageRuntimeQuicksort(int n, char typeQuickSort, char typeArray, int timesRepeated)
    {
        //Computes Average Runtime of timesRepeated different quicksorts for specified type of algorithm, and the specified type of input Array with n elements.
        // typeArray == 'r' for Random | typeArray == 'p' for Partially Sorted Arrays | typeArray == 'm' for Mostly Sorted Arrays

        long average = 0;
        int[] array;
        for(int i =0; i< timesRepeated; i++)
        {
            if(typeArray == 'r')  // Random Array
            {
                array = Generate.generateRandomInput(n);
            }
            else if(typeArray == 'p') // Partially Sorted Array
            {
                array = Generate.generatePartiallySortedInput(n);
            }
            else // typeArray == 'm' | Mostly Sorted Array
            {
                array = Generate.generateMostlySortedInput(n);
            }
            average += runtimeQuicksort(array, typeQuickSort);
        }
        average /= timesRepeated;
        return (int) average;
    }
    /**
     *
     * @param n
     * @param typeQuickSort
     * @param typeArray
     * @param timesRepeated
     * @return
     */
    static long varianceRuntimeQuicksort(int n, char typeQuickSort, char typeArray, int timesRepeated)
    {
        //Computes Variance, Squared Coefficient of Variance, and Average Runtime of timesRepeated different quicksorts for specified type of algorithm, and the specified type of input Array with n elements.
        // typeArray == 'r' for Random | typeArray == 'p' for Partially Sorted Arrays | typeArray == 'm' for Mostly Sorted Arrays

        int[] comparisonsArray = new int[timesRepeated];
        long sum = 0;
        long average = 0;
        int[] array;
        for(int i =0; i < timesRepeated; i++)
        {
            if(typeArray == 'r')  // Random Array
            {
                array = Generate.generateRandomInput(n);
            }
            else if(typeArray == 'p') // Partially Sorted Array
            {
                array = Generate.generatePartiallySortedInput(n);
            }
            else // typeArray == 'm' | Mostly Sorted Array
            {
                array = Generate.generateMostlySortedInput(n);
            }
            comparisonsArray[i] = runtimeQuicksort(array, typeQuickSort);
            //System.out.println(comparisonsArray[i]);  //Uncomment this code if you Also want to see each individual experiment's results
            average += comparisonsArray[i];
        }
        average /= timesRepeated;
        System.out.print("Average: " + average + "\t");
        for(int i =0; i < timesRepeated; i++)
        {
            sum += Math.pow((comparisonsArray[i] - average), 2);
        }
        double var =  sum / (timesRepeated - 1);
        System.out.print("Variance: " + var + "\t");
        double C = var / Math.pow(average, 2);
        System.out.println("Squared Coefficent Of Variance: " + C + "\t");

        return (long)var;
    }

    static void allVariances()
    { //Function that prints out all the data used in our Mini Research Study
        int timesRepeated = 10;
        int[] arr = { 10, 100, 1000, 10000, 100000, 1000000 };
        char[] c = {'r', 'p', 'm'};

        for (char d : c)
        {
            switch(d)
            {
                case 'r':
                    System.out.println("_________________________________\nRandom Array Input");
                    break;
                case 'p' :
                    System.out.println("_________________________________\nPartially Sorted Array Input");
                    break;
                case 'm' :
                    System.out.println("_________________________________\nMostly Sorted Array Input");
                    break;
            }

            for (int i : arr)
            {
                System.out.println("--------------------------------");
                System.out.println(i);

                System.out.print("Random Element Index      ||\t");
                varianceRuntimeQuicksort(i, 'r', d, timesRepeated);

                System.out.print("Middle Element Experiment ||\t");
                varianceRuntimeQuicksort(i, 'e', d, timesRepeated);

                if(i <= 10000)
                {
                    System.out.print("Deterministic             ||\t");
                    varianceRuntimeQuicksort(i, 'd', d, timesRepeated);
                }


                System.out.print("Median Experiment         ||\t");
                varianceRuntimeQuicksort(i, 's', d, timesRepeated);

                //System.out.println("--------------------------------");
            }

        }
    }

    public static void main(String[] args)
    {
        allVariances();
    }


}
