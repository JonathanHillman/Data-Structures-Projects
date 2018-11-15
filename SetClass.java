package p1_package;
/**
 * Class for managing sets of integers,
 * has capacity to generate various sets
 * <p>
 * @author Jonathan Hillman
 *
 */
public class SetClass 
{
    
    /**
     * Capacity of array. This represents the most the array can hold.
     */
    private int arrayCapacity;
    /**
     * Size of the array. This represents how many integers are in the array.
     */
    private int arraySize;
    /**
     * integer array for data
     */
    private int[] setArray;
    /**
     * constant with default array capacity
     */
    public final static int DEFAULT_ARRAY_CAPACITY = 10;
    /**
     * constant used for base two exponential calculations
     */
    private static final int BASE_TWO = 2;
    /**
     * 103 is the constant used to represent the value of "EVEN"
     */
    public static final int EVEN = 103;
    /**
     * 101 is the constant used to represent the value of "INCREMENTED"
     */
    public static final int INCREMENTED = 101;
    /**
     * 102 is the constant used to represent the value of "ODD"
     */
    public static final int ODD = 102;
    /**
     * 104 is the constant used to represent the value of "PRIME"
     */
    public static final int PRIME = 104;
    
    /**
     * Default constructor
     * <p>
     * Initializes set array but sets power set array to null
     */
    SetClass()
    {
        int index;
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        setArray = new int[ arrayCapacity ];
        arraySize = 0;
        for( index = 0; index < arrayCapacity; index++ )
        {
            setArray[ index ] = 0;
        }
    }
    
    /**
     * Initialization constructor
     * <p>
     * Allows specification of set array capacity
     * <p>
     * Initializes set array but sets power set array to null
     * <p>
     * @param initialarrayCapacity - integer that specifies array capacity
     */
    SetClass( int initialCapacity )
    {
        int index;
        arrayCapacity = initialCapacity;
        setArray = new int[ arrayCapacity ];
        arraySize = 0;
        for( index = 0; index < arrayCapacity; index++ )
        {
            setArray[ index ] = 0;
        }
    }
    /**Copy constructor
     * <p>
     * Duplicates copied set class
     * <p>
     * Also responsible for correct initialization/update of set class array
     * @param copied
     */
    SetClass( SetClass copied )
    {
        int index;
        copied.arrayCapacity = this.arrayCapacity;
        copied.setArray = new int[ this.arrayCapacity ];
        arraySize = 0;
        for( index = 0; index < this.arrayCapacity; index++ )
        {
            copied.setArray[ index ] = this.setArray[ index ];
        }
    }
    
    /**Adds integer to set
     * <p>
     * Increases capacity using checkForResize if array is full
     * <p>
     * Does not allow duplicate values in set
     * <p>
     * @param item - integer value to be added to set
     */
    void addItem( int item )
    {
        if( this.hasElement( item ) == false )
        {    
            this.checkForResize();
            setArray[ arraySize ] = item;
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
            int[] tempIntArray = new int[ arrayCapacity ];
            for( index = 0; index < arrayCapacity; index++ )
            {
                tempIntArray[ index ] = 0;
            }
            for( index = 0; index < arrayCapacity / 2; index++ )
            {
                tempIntArray[ index ] = setArray[ index ];
            }
            setArray = tempIntArray;
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
     * Tests to indicate if given integer value is prime
     * <p>
     * @param testval - integer value given
     * <p>
     * @return - boolean result of test
     */
    private boolean isPrime( int testval )
    {
        int divisor;
        for( divisor = 2; divisor < testval; divisor++ )
        {
            if( testval%divisor == 0 )
            {
                return false;
            }
        }
        return true;
    }
    /**
     * Seeks and finds prime starting at given value
     * <p>
     * @param value - integer value to start search for prime
     * <p>
     * @return - next prime number
     */
    private int getNextPrime( int value )
    {
        if( isPrime( value ) )
        {
            return value;
        }
        return getNextPrime( value +1 );
    }
    /**
     * Tests for even, reports
     * <p>
     * @param value - integer value to be tested
     * <p>
     * @return - boolean result as specified
     */
    private boolean isEven( int value )
    {
        return value % 2 == 0;
    }
    /**
     * Tests to indicate if integer value is one of the set elements
     * <p>
     * @param testElement - integer element to be found in set
     * <p>
     * @return - boolean result of test
     */
    public boolean hasElement( int testElement )
    {
        int index;
        for( index = 0; index < this.arraySize; index++ )
        {
            if( setArray[ index ] == testElement )
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Provides list of set array elements as comma-delimited string
     * <p>
     * @return - string of integers separated by a comma
     * <p>
     * @override - Overrides toString in class java.lang.Object
     */
    public String toString()
    {
        String myString = "";
        int index;
        for( index = 0; index < ( arraySize - 1 ); index++ )
        {
            myString += setArray[ index ] + ", ";
        }
        myString += setArray[ arraySize - 1 ];
        return myString;
    }
    
    /**
     * Returns the intersection of THIS set and the given other set
     * <p>
     * @param other - SetClass data with which intersection is found
     * <p>
     * @return - SetClass object with intersection of two sets
     */
    SetClass findIntersection( SetClass other )
    {
        int index;
        SetClass intersectionSetClass = new SetClass(); 
        for( index = 0; index < this.arraySize; index++ )
        {
            if( other.hasElement( this.setArray[ index ] ) )
            {
                intersectionSetClass.addItem( this.setArray[ index ] );
            }
        }
        return intersectionSetClass;
    }
    
    /**
     * Returns the union of THIS set and the give other set
     * <p>
     * @param other - SetClass data with which union is found
     * <p>
     * @return - SetClass object with union of two sets
     */
    public SetClass findUnion( SetClass other )
    {
        int index;
        SetClass unionSetClass = new SetClass();
        for( index = 0; index < this.arraySize; index++ )
        {
            unionSetClass.addItem( this.setArray[ index ] );
        }
        return unionSetClass;
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
    public boolean isSubSetOf( SetClass other )
    {
        int index;
        for( index = 0; index < this.arraySize; index++ )
        {
            if( other.hasElement( setArray[ index ] ) == false)
            {
                return false;
            }
        }
        return true;
    }
    /**
     * Loads a number of specified integers to set
     * <p>
     * Characteristics may be odd, even, incremented, or prime
     * <p>
     * Parameter four is only used with INCREMENTED
     * <p>
     * @param startValue - integer value indicates starting value
     * <p>
     * @param numItems - integer number of items to load
     * <p>
     * @param valueCharacteristic - integer characteristic code:
     * <p>
     * (ODD, EVEN, INCREMENTED, PRIME )
     * <p>
     * @param incrementBy - integer value used to specify 
     * <p>
     * increment if INCREMENTED characteristic is set
     */
    public void loadItems( int startValue, int numItems, 
               int valueCharacteristic, int incrementBy )
    {
        int numItemsAdded = 0;
        switch( valueCharacteristic )
        {
            case ODD:
                if( isEven ( startValue ) )
                {
                    startValue += 1;
                }
                while( numItemsAdded < numItems )
                {
                    this.addItem( startValue + ( numItemsAdded * 2 ) );
                    numItemsAdded += 1;
                }
            case EVEN:
                if( !( isEven ( startValue ) ) )
                {
                    startValue += 1;
                }
                while( numItemsAdded < numItems )
                {
                    this.addItem( startValue + ( numItemsAdded * 2 ) );
                    numItemsAdded += 1;
                }
            case PRIME:
                int currentPrime = getNextPrime( startValue );
                while( numItemsAdded < numItems )
                {
                    this.addItem( currentPrime );
                    numItemsAdded += 1;
                    currentPrime = getNextPrime( currentPrime + 1 );
                }
            case INCREMENTED:
                while( numItemsAdded < numItems )
                {
                    this.addItem( startValue + 
                    ( numItemsAdded * incrementBy ) );
                    numItemsAdded += 1;
                }
        }
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
    public boolean removeValue( int valToRemove )
    {
        int index;
        boolean valueWasRemoved = false;
        for( index = 0; index < arraySize; index++ )
        {
            if( setArray[ index ] == valToRemove )
            {
                this.removeAtIndex( index );
                valueWasRemoved = true;
            }
        }
        return valueWasRemoved;
    }
    
    /**
     * Removes value at given index; moves all succeeding data down in array
     * <p>
     * @param indexToRemove - integer index of element value to remove
     */
    private void removeAtIndex( int indexToRemove )
    {
        int index;
        setArray[ indexToRemove ] = 0;
        for( index = indexToRemove; index < arraySize; index++ )
        {
            setArray[ index ] = setArray[ index + 1 ];
        }
        arraySize -= 1;
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
    public SetClass findRelativeComplementOfThisSetIn( SetClass other )
    {
        int index;
        SetClass relativeComplement = new SetClass();
        for( index = 0; index < other.arraySize; index++ )
        {
            if( !( this.hasElement ( other.setArray [ index ] ) ) )
            {
                relativeComplement.addItem ( other.setArray [ index ] );
            }
        }
        return relativeComplement;
    }
}
