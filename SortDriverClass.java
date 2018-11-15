package p3_package;
/**
 * Class used to test merge, quick, and Shell sorts
 * <p>
 * @author Jonathan Hillman
 *
 */

public class SortDriverClass 
{
    public SortDriverClass()
    {
        
    }
    /**
     * Main function driver for sort operations
     * <p>
     * @param args - String array not used
     */
    public static void main(String[] args)
    {
     int[] testArray;
     int numValues = 25, lowLimit = 10, highLimit = 99;
     String resultString;
   
     testArray = loadRands( numValues, lowLimit, highLimit );
     resultString = arrayToString( testArray, numValues );
     System.out.println( "\nArray before Merge Sort: " + resultString );
     runMergeSort( testArray, numValues );
     resultString = arrayToString( testArray, numValues );
     System.out.println( "Array after Merge Sort: " + resultString );
   
     testArray = loadRands( numValues, lowLimit, highLimit );
     resultString = arrayToString( testArray, numValues );
     System.out.println( "\nArray before Quick Sort: " + resultString );
     runQuickSort( testArray, numValues );
     resultString = arrayToString( testArray, numValues );
     System.out.println( "Array after Quick Sort: " + resultString );
   
     testArray = loadRands( numValues, lowLimit, highLimit );
     resultString = arrayToString( testArray, numValues );
     System.out.println( "\nArray before Shell's Sort: " + resultString );
     runShellSort( testArray, numValues );
     resultString = arrayToString( testArray, numValues );
     System.out.println( "Array after Shell's Sort: " + resultString );
    }
    /**
     * Generate random value between the lowest and highest
     * specified limits inclusive
     * <p>
     * @param lowLimit specified integer low limit of random value range
     * <p>
     * @param highLimit specified integer high limit of random value range
     * <p>
     * @return random integer value generated between the inclusive limits
     */
    public static int generateRandBetween( int lowLimit, int highLimit )
       {
        int randVal, range;
       
        if( highLimit > lowLimit )
           {
            // create range of numbers
            range = highLimit - lowLimit + 1;
           
            // find random integer value from value between 0 and 1
            randVal = (int)( Math.random() * 1000000 );
           
            return randVal % range + lowLimit;
           }
       
        return 0;
       }
    /**
     * Converts array of integers into string for output or display
     * <p>
     * @param localArray integer array of values
     * <p>
     * @param size number of values in the array
     * <p>
     * @return String holding a list of all values, comma-delimited
     */
    public static String arrayToString( int[] localArray, int size )
       {
        int index;
        String outString ="";
        
        for( index = 0; index < size; index++ )
           {
            if( index > 0 )
               {
                outString += ", ";
               }
            
            outString += localArray[ index ];
           }
        
        return outString;
       }
    /**
     * Method to load a given integer array with random values
     * <p>
     * @param numRands integer identifies number of values to generate
     * <p>
     * @param lowLimit integer identifies low limit of generated randoms
     * <p>
     * @param highLimit integer identifies high limit of generated randoms
     * <p>
     * @return integer array with data loaded
     */
    public static int[] loadRands( int numRands, int lowLimit, int highLimit )
       {
        int index;
        int[] localArray = new int[ numRands ];
       
        for( index = 0; index < numRands; index++ )
           {
            localArray[ index ] = generateRandBetween( lowLimit, highLimit );
           }

        return localArray;
       }
    /**
     * Swaps values within given array
     * <p>
     * @param localArray array of Objects used for swapping
     * <p>
     * @param indexOne integer index for one of the two items to be swapped
     * <p>
     * @param indexOther integer index for the other of the two items
     * <p> 
     * to be swapped
     */
    public static void swapValues( int[] localArray,
                                   int indexOne,
                                   int indexOther )
       {
        int temp = localArray[ indexOne ];
        
        localArray[ indexOne ] = localArray[ indexOther ];
        
        localArray[ indexOther ] = temp;        
       }
    /**
     * Uses Shell's sorting algorithm to sort an array of integers
     * <p>
     * Shell's sorting algorithm is an optimized insertion algorithm
     * <p>
     * @param localArray integer array holding unsorted values
     * <p>
     * @param size integer number of items in array
     */
    public static void runShellSort( int[] localArray, int size )
       {
        int gap, gapPassIndex, insertionIndex, temp;
    
        for( gap = size / 2; gap > 0; gap /= 2 )
           {
            for( gapPassIndex = gap; gapPassIndex < size; gapPassIndex++ )
               {
                temp = localArray[ gapPassIndex ];

               for( insertionIndex = gapPassIndex; insertionIndex >= gap
                && localArray[ insertionIndex - gap] > 
                   temp; insertionIndex -= gap )
                  {
                   localArray[ insertionIndex ] = 
                   localArray[ insertionIndex - gap ];
                  }

               localArray[ insertionIndex ] = temp;                 
              }
           }    
       }
    /**
     * Data sorted using quick sort algorithm
     * <p>
     * Note: Call runQuickSortHelper with lower and upper
     * indices of array to be sorted
     * <p>
     * @param localArray integer array holding unsorted values
     * <p>
     * @param size integer value holding the number of values in the array
     */
    public static void runQuickSort( int[]
                                     localArray,
                                     int size )
    {
        int partitionIndex = runPartition( localArray, 0, size-1 );
        runQuickSortHelper( localArray, 0, partitionIndex - 1 );
        runQuickSortHelper( localArray, partitionIndex + 1, size - 1 );
    }
    /**
     * Partitions array using the first value as the partition;
     * when this method is complete the partition value is
     * in the correct location in the array
     * <p>
     * @param localArray integer array holding unsorted values
     * <p>
     * @param lowIndex low index of array segment to be partitioned
     * <p>
     * @param highIndex high index of array segment to be partitioned
     * <p>
     * @return integer index of partition pivot
     */
    private static int runPartition( int[] localArray,
                                     int lowIndex,
                                     int highIndex )
    {
        int rightIndex;
        int leftIndex = lowIndex;
        int partitionIndex = lowIndex;
        
        for( rightIndex = lowIndex; rightIndex <= highIndex; rightIndex++ )
        {
            if( localArray[ rightIndex ] < localArray[ partitionIndex ] )
            {
                leftIndex += 1;
                swapValues( localArray, leftIndex, rightIndex );
            }
        }
        swapValues( localArray, leftIndex, partitionIndex );
        partitionIndex = leftIndex;
        return partitionIndex;
    }
    /**
     * helper method run with parameters that support
     * recursive access
     * <p>
     * @param localArray integer array holding unsorted values
     * <p>
     * @param lowIndex low index of the segment of the array 
     * to be processed
     * <p>
     * @param highIndex high index of the segment of the array 
     * to be processed
     */
    private static void runQuickSortHelper( int[] localArray, 
                                            int lowIndex, 
                                            int highIndex )
    {
        if( lowIndex < highIndex )
        {
            int partitionIndex = runPartition( localArray,
                                               lowIndex,
                                               highIndex );
            runQuickSortHelper( localArray, lowIndex, partitionIndex -1 );
            runQuickSortHelper( localArray, partitionIndex + 1, highIndex );
        }
    }
    /**
     * Data sorted using merge sort algorithm
     * <p>
     * Note: Call runMergeSortHelper with lower and upper 
     * <p>
     * indices of array to be sorted
     * <p>
     * @param localArray - integer array holding unsorted values
     * <p>
     * @param size - integer value holding number of values in array
     */
    private static void runMergeSort( int[] localArray, int size )
    {
        runMergeSortHelper( localArray, 0, size - 1 );
    }
    /**
     * Merge sort helper, places low and high indices of array segment to
     * <p>
     * be processed into recursive method, 
     * <p>
     * then sorts data using merge sort algorithm
     * <p>
     * @param localArray - integer array holding unsorted values
     * <p>
     * @param lowIndex - lowest index of array segment to 
     * <p>
     * be managed; this varies as the segments are broken down recursively
     * <p>
     * @param highIndex - highest index of array segment to be managed; 
     * <p>
     * this varies as the segments are broken down recursively
     */
    private static void runMergeSortHelper( int[] localArray,
                                            int lowIndex,
                                            int highIndex )
    {
        int middleIndex;
        if( lowIndex < highIndex )
        {
            middleIndex = ( highIndex + lowIndex )  / 2 ;
            runMergeSortHelper( localArray, lowIndex, middleIndex );
            runMergeSortHelper( localArray, middleIndex + 1, highIndex );
            runMerge( localArray, lowIndex, middleIndex, highIndex );
        }
    }
    /**
     * Merges values brought in between a low and 
     * <p>
     * high index segment of an array
     * <p>
     * Note: uses locally sized single array for temporary storage
     * <p>
     * @param localArray - integer array holding unsorted values
     * <p>
     * @param lowIndex - lowest index of array segment to be managed
     * <p>
     * @param middleIndex - middle index of array segment to be managed
     * <p>
     * @param highIndex - high index of array segment to be managed
     */
    private static void runMerge( int[] localArray,
                                  int lowIndex,
                                  int middleIndex,
                                  int highIndex )
    {
        int index;
        int[] tempMergeArray = new int[ ( highIndex - lowIndex ) + 1 ];
        int tempArrayIndex = 0;
        int leftIndex = lowIndex;
        int rightIndex = middleIndex + 1;
        while( leftIndex <= middleIndex && rightIndex <= highIndex )
        {
            if( localArray[ leftIndex ] < localArray[ rightIndex ] )
            {
                tempMergeArray[ tempArrayIndex ] = localArray[ leftIndex ];
                leftIndex++;
            }
            else
            {
                tempMergeArray[ tempArrayIndex ] = localArray[ rightIndex ];
                rightIndex++;
            }
            tempArrayIndex++;
        }
        while( leftIndex <= middleIndex )
        {
            tempMergeArray[ tempArrayIndex ] = localArray[ leftIndex ];
            leftIndex += 1;
            tempArrayIndex += 1;
        }
        while( rightIndex <= highIndex )
        {
            tempMergeArray[ tempArrayIndex ] = localArray[ rightIndex ];
            rightIndex += 1;
            tempArrayIndex += 1;
        }
        tempArrayIndex = 0;
        for( index = lowIndex; index <= highIndex; index++)
        {
            localArray[ index ] = tempMergeArray[ tempArrayIndex ];
            tempArrayIndex++;
        }
    }
}