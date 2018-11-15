package p9_package;
/**
 * Array-based generic min heap class used as a
 * <p>
 * priority queue for generic data
 * <p>
 * @author j_hil
 *
 */
public class GenericHeapClass<GenericData extends Comparable<GenericData>>
{
    /**
     * Management data for array
     */
    private int arrayCapacity;
    /**
     * Management data for array
     */
    private int arraySize;
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;
    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;
    /**
     * Array for heap
     */
    private Object[] heapArray;
    /**
     * Default constructor sets up array management conditions 
     * <p>
     * and default display flag setting
     */
    public GenericHeapClass()
    {
        this.arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        this.arraySize = 0;
        displayFlag = false;
        heapArray = new Object[ this.arrayCapacity ];
    }
    /**
     * Copy constructor copies array and array management conditions 
     * <p>
     * and default display flag setting
     * <p>
     * @param copied - GenericHeapClass object to be copied
     */
    public GenericHeapClass( GenericHeapClass<GenericData> copied )
    {
        int index;
        this.arrayCapacity = copied.arrayCapacity;
        this.arraySize = copied.arraySize;
        this.displayFlag = copied.displayFlag;
        this.heapArray = new Object[ this.arrayCapacity ];
        for( index = 0; index < this.arraySize; index++ )
        {
            this.heapArray[ index ] = copied.heapArray[ index ];
        }
    }
    /**
     * Note: uses bubbleUpArrayHeap to resolve
     * <p>
     * unbalanced heap after data addition
     * <p>
     * @param newItem
     */
    public void addItem( GenericData newItem )
    {
        checkForResize();
        heapArray[ arraySize ] = newItem;
        bubbleUpArrayHeap( arraySize );
        arraySize++;
    }
    /**
     * Recursive operation to reset data in the correct 
     * <p>
     * order for the min heap after new data addition
     * <p>
     * @param currentIndex
     */
    @SuppressWarnings("unchecked")
    private void bubbleUpArrayHeap( int currentIndex )
    {
        if( displayFlag )
        {
            System.out.println( "Adding new process: " + 
                                    heapArray[ currentIndex ].toString() );
            GenericData GD1 = (GenericData)heapArray[ currentIndex ];
            GenericData GD2 = (GenericData)heapArray[ (currentIndex - 1) / 2 ];
            int parentIndex = ( currentIndex - 1 ) / 2;
            if( GD1.compareTo( GD2 ) < 0 )
            {
                System.out.println( "    - Bubble Up: ");
                System.out.println( "      - Swapping Parent: " +
                                       heapArray[ parentIndex ].toString() +
                                       "; with child: " +
                                       heapArray[ currentIndex ].toString() );
                System.out.println();
                heapArray[ currentIndex ] = GD2;
                heapArray[ parentIndex ] = GD1;
                bubbleUpArrayHeap( parentIndex );
            }
            System.out.println();
        }
        // this runs if displayFlag is false, its exactly the same as before
        else
        {
            GenericData GD1 = (GenericData)heapArray[ currentIndex ];
            GenericData GD2 = (GenericData)heapArray[ (currentIndex - 1) / 2 ];
            int parentIndex = ( currentIndex - 1 ) / 2;
            if( GD1.compareTo( GD2 ) < 0 )
            {
                heapArray[ currentIndex ] = GD2;
                heapArray[ parentIndex ] = GD1;
                bubbleUpArrayHeap( parentIndex );
            }
        }
    }
    /**
     * Automatic resize operation used prior to any
     * <p>
     * new data addition in the heap
     * <p>
     * Tests for full heap array, and resizes to twice
     * <p>
     * the current capacity as required
     */
    private void checkForResize()
    {
        Object[] tempArray;
        int index;
        if( arraySize == arrayCapacity )
        {
            arrayCapacity = arrayCapacity * 2;
            tempArray = new Object[ arrayCapacity ];
            for( index = 0; index < arraySize; index++ )
            {
                tempArray[ index ] = heapArray[ index ];
            }
            heapArray = tempArray;
        }
    }
    /**
     * Tests for empty heap
     * @return
     */
    public boolean isEmpty()
    {
        return arraySize == 0;
    }
    /**
     * Removes GenericData item from top of min heap,
     * <p>
     * thus being the operation with the lowest priority value
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve
     * <p>
     * unbalanced heap after data removal
     * <p>
     * @return - GenericData item removed
     */
    @SuppressWarnings("unchecked")
    public GenericData removeItem()
    {
        GenericData tempGD = (GenericData) heapArray[ 0 ];
        heapArray[ 0 ] = heapArray[ arraySize - 1 ];
        arraySize--;
        trickleDownArrayHeap( 0 );
        return tempGD;
    }
    /**
     * Utility method to set the display flag for displaying internal
     * <p>
     * operations of the heap bubble and trickle operations
     * <p>
     * @param setState - flag used to set the state to display, or not
     */
    public void setDisplayFlag( boolean setState )
    {
        displayFlag = setState;
    }
    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
    {
        int index;
        for( index = 0; index < arraySize; index ++ )
        {
            System.out.print( heapArray[ index ].toString() + " - ");
        }
        System.out.println();
        System.out.println();
    }
    /**
     * Recursive operation to reset data in the correct
     * <p>
     * order for the min heap after data removal
     * <p>
     * @param currentIndex - index of current item being assessed,
     * <p>
     * and moved down as required
     */
    @SuppressWarnings("unchecked")
    private void trickleDownArrayHeap( int currentIndex )
    {
        int leftChildIndex = ( currentIndex ) * 2 + 1;
        int rightChildIndex = ( currentIndex ) * 2 + 2;
        GenericData GD1, GD2, GD3, tempGD;
        if( displayFlag )
        {
            System.out.println( "Removing process: " + 
                                     heapArray[ currentIndex ].toString() );
          //we can assume left child exists as well
            if( rightChildIndex < arraySize )
            {
                GD1 = (GenericData) heapArray[ leftChildIndex ];
                GD2 = (GenericData) heapArray[ rightChildIndex ];
                GD3 = (GenericData) heapArray[ currentIndex ];
                //assume that the heap is not in order
                if( GD1.compareTo( GD3 ) < 0||
                        GD2.compareTo( GD3) < 0 )
                {
                    System.out.println("    - Trickle down: ");
                    //assume left child is smallest 
                    if( GD1.compareTo( GD2 ) < 0 )
                    {
                        System.out.println("      -Swapping Parent: " +
                                                GD3.toString() + 
                                                " with left child: " + 
                                                GD1.toString() );
                        tempGD = GD3;
                        heapArray[ currentIndex ] = heapArray[leftChildIndex];
                        heapArray[ leftChildIndex ] = tempGD;
                        trickleDownArrayHeap( leftChildIndex );
                    }
                    //assume right child is smallest
                    else
                    {
                        System.out.println("      -Swapping Parent: " +
                                                   GD3.toString() + 
                                                   " with right child: " + 
                                                   GD2.toString() );
                        tempGD = GD3; 
                        heapArray[ currentIndex ] = heapArray[rightChildIndex];
                        heapArray[ rightChildIndex ] = tempGD;
                        trickleDownArrayHeap( rightChildIndex );
                    }
                }
                System.out.println();
            }
            //assume there is no right child
            else if( leftChildIndex < arraySize )
            {
                //keeping GD# names consistent
                GD1 = (GenericData) heapArray[ leftChildIndex ];
                GD3 = (GenericData) heapArray[ currentIndex ];
                if( GD1.compareTo( GD3 ) < 0)
                {
                    System.out.println("      -Swapping Parent: " +
                            GD3.toString() + 
                            " with left child: " + 
                            GD1.toString() );
                    tempGD = GD3;
                    heapArray[ currentIndex ] = heapArray[ leftChildIndex ];
                    heapArray[ leftChildIndex ] = tempGD;
                    trickleDownArrayHeap( leftChildIndex );
                }
                System.out.println();
            }
            //assume there we are at the bottom of the heap otherwise
        }
        //assume display flag is disabled
        else
        {
        //we can assume left child exists as well
            if( rightChildIndex < arraySize )
            {
                GD1 = (GenericData) heapArray[ leftChildIndex ];
                GD2 = (GenericData) heapArray[ rightChildIndex ];
                GD3 = (GenericData) heapArray[ currentIndex ];
                //assume that the heap is not in order
                if( GD1.compareTo( GD3 ) < 0||
                        GD2.compareTo( GD3) < 0 )
                {
                    //assume left child is smallest 
                    if( GD1.compareTo( GD2 ) < 0 )
                    {
                        tempGD = GD3;
                        heapArray[currentIndex] = heapArray[ leftChildIndex ];
                        heapArray[ leftChildIndex ] = tempGD;
                        trickleDownArrayHeap( leftChildIndex );
                    }
                    //assume right child is smallest
                    else
                    {
                        tempGD = GD3; 
                        heapArray[currentIndex] = heapArray[ rightChildIndex ];
                        heapArray[ rightChildIndex ] = tempGD;
                        trickleDownArrayHeap( rightChildIndex );
                    }
                }
            }
            //assume there is no right child
            else if( leftChildIndex < arraySize )
            {
                //keeping GD# names consistent
                GD1 = (GenericData) heapArray[ leftChildIndex ];
                GD3 = (GenericData) heapArray[ currentIndex ];
                if( GD1.compareTo( GD3 ) < 0)
                {
                    tempGD = GD3;
                    heapArray[ currentIndex ] = heapArray[ leftChildIndex ];
                    heapArray[ leftChildIndex ] = tempGD;
                    trickleDownArrayHeap( leftChildIndex );
                }
            }
            //assume there we are at the bottom of the heap otherwise
        }
    }
    
}
