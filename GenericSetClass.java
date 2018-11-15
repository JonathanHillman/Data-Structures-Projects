package p2_package;
/**
 * 
 * @author Jonathan Hillman
 *
 *Class for managing sets of GenericData that extend Comparable
 *
 * @param < GenericData >
 */
public class GenericSetClass< GenericData extends Comparable < GenericData > >
{
    /**
     * constant used for base two exponential calculations
     */
    private static final int BASE_TWO = 2;
    /**
     * constant with default array capacity
     */
    private static final int DEFAULT_ARRAY_CAPACITY = 10;
    /**
     * constant used for base two exponential calculations
     */
    private Object[] genericSetArray;
    /**
     * capacity of array
     */
    private int arrayCapacity;
    /**
     * number of values in array
     */
    private int arraySize;
    /**
     * Default constructor
     * <p>
     * Initializes set array, sets power set array to null
     */
    
    public GenericSetClass()
    {
        int index;
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        genericSetArray = new Object[ arrayCapacity ];
        arraySize = 0;
        for( index = 0; index < arrayCapacity; index++ )
        {
            genericSetArray[ index ] = null;
        }
        
    }
    /**
     * Copy constructor
     * <p>
     * Duplicates copied set class
     * <p>
     * Also responsible for correct initialization/update of 
     * <p>
     * set class array and power set array
     * <p>
     * @param copied - GenericSetClass object to be copied
     */
    @SuppressWarnings( "unchecked" )
    public GenericSetClass( GenericSetClass < GenericData > copied )
    {
        int index;
        this.arrayCapacity = copied.arrayCapacity;
        this.genericSetArray = new Object[ this.arrayCapacity ];
        this.arraySize = 0;
        for( index = 0; index < copied.arraySize; index++ )
        {
            this.addItem( ( GenericData ) copied.genericSetArray [ index ] );
        }
    }
    /**
     * Initialization constructor
     * <p>
     * Allows specification of set array capacity
     * <p>
     * Initializes set array, sets power set array to null
     * <p>
     * @param initialCapacity - integer that specifies array capacity
     */
    public GenericSetClass( int initialCapacity )
    {
        int index;
        arrayCapacity = initialCapacity;
        genericSetArray = new Object[ arrayCapacity ];
        arraySize = 0;
        for ( index = 0; index < arrayCapacity; index++ )
        {
            genericSetArray[ index ] = null;
        }
    }
    /**Adds GenericData to set
     * <p>
     * Increases capacity using checkForResize if array is full
     * <p>
     * Does not allow duplicate values in set
     * <p>
     * @param item - GenericData item to be added to set
     */
    public void addItem( GenericData item )
    {
        if( this.hasElement( item ) == false )
        {    
            this.checkForResize();
            genericSetArray[ arraySize ] = item;
            arraySize += 1;
        }
    }
    /**
     * Local function tests for resize of the set array
     * <p>
     * If array needs to be resized, array capacity is doubled; otherwise,
     * <p>
     * no change.
     * <p>
     * @return - boolean report that resize was conducted
     */
    private boolean checkForResize()
    {
        if( arraySize == arrayCapacity )
        {
            //copy the values into a bigger array
            arrayCapacity = arrayCapacity * 2;
            int index;
            Object[] tempGenericDataArray = new Object[ arrayCapacity ];
            for( index = 0; index < arrayCapacity; index++ )
            {
                tempGenericDataArray[ index ] = null;
            }
            for( index = 0; index < arraySize ; index++ )
            {
                tempGenericDataArray[ index ] = genericSetArray[ index ];
            }
            genericSetArray = tempGenericDataArray;
            return true;
        }
        return false;
    }
    /**
     * recursively calculates integer base to power ( exponent )
     * <p>
     * @param base - integer base value
     * <p>
     * @param exponent - int exponent value
     * <p>
     * @return integer base to the exponent result
     */
    private int toPower( int base, int exponent )
    {
        if( exponent == 0 )
        {
            return 1;
        }
        return base * toPower( base, exponent - 1 );
    }
    /**
     * Tests to indicate if integer value is one of the set elements
     * <p>
     * @param testElement - integer element to be found in set
     * <p>
     * @return - boolean result of test
     */
    public boolean hasElement( GenericData testElement )
    {
        int index;
        for( index = 0; index < this.arraySize; index++ )
        {
            @SuppressWarnings( "unchecked" )
            GenericData gd1 = ( GenericData ) genericSetArray[ index ];
            if( gd1.compareTo( testElement ) == 0 )
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns the union of THIS set and the give other set
     * <p>
     * @param other - GenericSetClass data with which union is found
     * <p>
     * @return - GenericSetClass object with union of two sets
     */
    @SuppressWarnings( "unchecked" )
    public GenericSetClass< GenericData > findUnion 
    ( GenericSetClass < GenericData > other )
    
    {
        int index;
        GenericSetClass < GenericData > unionGenericSetClass = 
                        new GenericSetClass < GenericData > ();
        
        
        for( index = 0; index < this.arraySize; index++ )
        {
            unionGenericSetClass.addItem( 
            ( GenericData ) this.genericSetArray[ index ] );
            
        }
        for( index = 0; index < other.arraySize; index++)
        {
            unionGenericSetClass.addItem( 
            ( GenericData ) other.genericSetArray [ index ] );
            
        }
        return unionGenericSetClass;
    }
    /**
     * Returns the intersection of THIS set and the given other set
     * <p>
     * @param other - GenericSetClass data with which intersection is found
     * <p>
     * @return - GenericSetClass object with intersection of two sets
     */
    @SuppressWarnings( "unchecked" )
    public GenericSetClass< GenericData > 
    findIntersection( GenericSetClass< GenericData > other )
    {
        int index;
        GenericSetClass < GenericData > intersectionSetClass = 
                new GenericSetClass < GenericData > (); 
        
        for( index = 0; index < this.arraySize; index++ )
        {
            if( other.hasElement 
              ( ( GenericData ) this.genericSetArray[ index ] ) )
                
            {
                intersectionSetClass.addItem 
                ( ( GenericData ) this.genericSetArray[ index ] );
            }
        }
        for( index = 0; index < other.arraySize; index++ )
        {
            if( this.hasElement
              ( ( GenericData )  other.genericSetArray [ index ] ) )
            {
                intersectionSetClass.addItem( 
                ( GenericData ) other.genericSetArray [ index ] );
            }
        }
        
        return intersectionSetClass;
    }
    /**
     * Removes value at given index; moves all succeeding data down in array
     * <p>
     * @param indexToRemove - GenericData index of element value to remove
     */
    private void removeAtIndex( int indexToRemove )
    {
        int index;
        genericSetArray[ indexToRemove ] = null;
        for( index = indexToRemove; index < arraySize; index++ )
        {
            genericSetArray[ index ] = genericSetArray[ index + 1 ];
        }
        arraySize -= 1;
    }
    /**
     * Removes value if it is found in set
     * <p>
     * All values equal to given value will be removed 
     * <p>
     * in case there is more than one
     * <p>
     * @param valToRemove - integer value to be removed
     * <p>
     * @return - boolean result of operation success
     */
    @SuppressWarnings( "unchecked" )
    public boolean removeItem( GenericData valToRemove )
    {
        int index;
        GenericData gd1;
        boolean valueWasRemoved = false;
        for( index = 0; index < arraySize; index++ )
        {
            gd1 = ( GenericData ) genericSetArray[ index ];
            if( gd1.compareTo( valToRemove ) == 0 )
            {
                this.removeAtIndex( index );
                valueWasRemoved = true;
            }
        }
        return valueWasRemoved;
    }
    /**
     * Finds relative complement of THIS set in given other set
     * <p>
     * Returns other set having removed any items intersecting with THIS set
     * <p>
     * @param other - SetClass object from which THIS 
     * <p>
     * SetClass items will be removed
     * <p>
     * @return - SetClass object with data as specified
     */
    @SuppressWarnings( "unchecked" )
    public GenericSetClass< GenericData > 
    findRelativeComplementOfThisSetIn ( GenericSetClass< GenericData > other )
    {
        int index;
        GenericSetClass < GenericData > relativeComplement = 
                new GenericSetClass < GenericData > ( other );
        
        for( index = 0; index < other.arraySize; index++ )
        {
            if( relativeComplement.hasElement 
              ( ( GenericData ) this.genericSetArray [ index ] ) )
                
            {
                relativeComplement.removeItem( 
                ( GenericData ) this.genericSetArray [ index ] );
            }
        }
        return relativeComplement;
    }
    /**
     * Tests to indicate if set is subclass another given set
     * <p>
     * @param other - SetClass object to be tested if THIS 
     * <p>
     * set is a subset of it
     * <p>
     * @return - boolean result of test
     */
    @SuppressWarnings( "unchecked" )
    public boolean isSubSetOf( GenericSetClass< GenericData > other )
    {
        int index;
        for( index = 0; index < this.arraySize; index++ )
        {
            if( other.hasElement( ( GenericData ) 
                genericSetArray [ index ] ) == false)
            {
                return false;
            }
        }
        return true;
    }
    /**
     * Provides list of set array elements as comma-delimited string
     * <p>
     * @overrides toString class in java.lang.Object
     * <p>
     * @return - uses toStringHelper to create return String
     */
    public String toString()
    {
        return toStringHelper( genericSetArray );
    }
    /**
     * Provides list of set array elements as comma-delimited string
     * <p>
     * @param localArray - Object array holding Generic Data
     * <p>
     * @return - String holding objects from array
     */
    public  String toStringHelper( Object[] localArray )
    {
        String genericSetString = "";
        int index = 0;
        for( index = 0; index < arraySize -1; index++ )
        {
            genericSetString += localArray[ index ] + ", ";
        }
        genericSetString += localArray[ arraySize -1 ];
        return genericSetString;
    }
    /**
     * Swaps values within given array
     * <p>
     * @param localArray - Array of objects used for swapping
     * <p>
     * @param indexOne - integer index for one of the two items to be swapped
     * <p>
     * @param indexOther - integer index for the 
     * <p>
     * other of the two items to be swapped
     */
    @SuppressWarnings( "unchecked" )
    public void swapValues( Object[] localArray, 
                            int indexOne, int indexOther )
    {
        GenericData temp = ( GenericData ) localArray[ indexOne ];
        localArray[ indexOne ] = localArray[ indexOther ];
        localArray[ indexOther ] = temp;
    }
    /**
     * Description: Sorts elements using the bubble sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of the given
     * <p>
     * data set; the compareTo method decides the key and the order resulting
     * <p>
     * @return - String data holding list of sorted objects
     */
    @SuppressWarnings( "unchecked" )
    public String runBubbleSort()
    {
        GenericSetClass < GenericData > tempBubbleSortSet = 
                new GenericSetClass< GenericData > ( this );
        
        int index1, index2;
        GenericData gd1, gd2;
        for( index1 = 0; index1 < arraySize; index1++ )
        {
            for( index2 = 0; index2 < arraySize - 1; index2++ )
            {
                gd1 = ( GenericData ) 
                tempBubbleSortSet.genericSetArray [ index2 ];
                
                gd2 = ( GenericData ) 
                tempBubbleSortSet.genericSetArray [ index2 +1 ];
                
                if( gd1.compareTo(gd2) > 0 )
                {
                    swapValues( tempBubbleSortSet.genericSetArray, 
                               index2, index2 + 1 );
                }
            }
        }
        return toStringHelper( tempBubbleSortSet.genericSetArray );
    }
    /**
     * Description: Sorts elements using the selection sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of the given 
     * <p>
     * data set; the compareTo method decides the key and the order resulting
     * <p>
     * @return - String data holding list of sorted objects
     */
    @SuppressWarnings( "unchecked" )
    public String runSelectionSort()
    {
        int index1, index2;
        int minIndex;
        GenericData gd1, gd2;
        GenericSetClass < GenericData > tempSelectionSortSet =
                    new GenericSetClass < GenericData > ( this );
        for( index1 = 0; index1 < arraySize; index1++ )
        {
            minIndex = index1;
            for( index2 = index1; index2 < arraySize; index2++ )
            {
                gd1 = ( GenericData ) 
                tempSelectionSortSet.genericSetArray[ index2 ];
                
                gd2 = ( GenericData ) 
                tempSelectionSortSet.genericSetArray[ minIndex ];
                
                if( gd1.compareTo(gd2) < 0 )
                {
                    minIndex = index2;
                }
            }
            swapValues( tempSelectionSortSet.genericSetArray, 
                       minIndex, index1 );
        }
        return tempSelectionSortSet.toString();
    }
    /**
     * Description: Sorts elements using the insertion sort algorithm
     * <p>
     * Note: The data is sorted using the compareTo method of the given 
     * <p>
     * data set; the compareTo method decides the key and the order resulting
     * <p>
     * @return - String data holding list of sorted objects
     */
    @SuppressWarnings( "unchecked" )
    public String runInsertionSort()
    {
        int index1, index2;
        GenericData gd1, gd2;
        GenericSetClass < GenericData > tempInsertionSort =
        new GenericSetClass < GenericData > (this);
        for( index1 = 1; index1 < arraySize; index1++ )
        {
            for( index2 = index1; index2 > 0; index2-- )
            {
                gd1 = ( GenericData )
                tempInsertionSort.genericSetArray[ index2 ];
                
                gd2 = ( GenericData )
                tempInsertionSort.genericSetArray[ index2-1 ];
                if(gd1.compareTo( gd2 ) < 0)
                {
                    swapValues( tempInsertionSort.genericSetArray, index2, index2-1 );
                }
            }
        }
        return tempInsertionSort.toString();
    }
    
}