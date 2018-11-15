package p5_package;
/**
 * 
 * @author j_hil
 *
 */
public class SimpleStackClass
{
    /**
     * Stores current capacity of stack class
     */
    private int capacity;
    /**
     * Provides constant for default capacity
     */
    private final int DEFAULT_CAPACITY = 10;
    /**
     * Provides constant -999999 for access failure messaging
     */
    static final int FAILED_ACCESS = -999999;
    /**
     * Stores current size of stack class
     */
    private int size;
    /**
     * Integer array stores stack data
     */
    private int[] stackData; 
    /**
     * Stores stack top index
     */
    private int stackTopIndex;
    /**
     * Default constructor
     */
    public SimpleStackClass()
    {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.stackData = new int[ capacity ];
        this.stackTopIndex = -1;
    }
    /**
     * Initialization constructor
     * <p>
     * @param capacitySetting - initial capacity of stackData class
     */
    public SimpleStackClass( int capacitySetting )
    {
        this.capacity = capacitySetting;
        this.size = 0;
        this.stackData = new int[ capacity ];
        this.stackTopIndex = -1;
    }
    /**
     * Copy constructor
     * <p>
     * @param copied - SimpleStackClass object to be copied
     */
    public SimpleStackClass( SimpleStackClass copied )
    {
        int index = 0;
        this.capacity = copied.capacity;
        this.size = copied.size;
        this.stackData = new int[ capacity ];
        for( index = 0; index < this.capacity; index ++ )
        {
            this.stackData[ index ] = copied.stackData[ index ];
        }
        this.stackTopIndex = copied.stackTopIndex;
    }
    /**
     * Checks for resize and resizes to twice the current capacity if needed
     * <p>
     * Note: Returns true if resize is necessary and is conducted;
     * <p>
     * returns false if no action is taken
     * <p>
     * @return - success of operation
     */
    private boolean checkForReSize()
    {
        int index;
        int[] tempIntArray;
        if( size == capacity )
        {
            capacity *= 2;
            tempIntArray = new int[ capacity ];
            for( index = 0; index < size; index++ )
            {
                tempIntArray[ index ] = stackData[ index ];
            }
            stackData = tempIntArray;
            return true;
        }
        return false;
    }
    /**
     * Clears stack by setting size to zero and top index to negative one
     */
    public void clear()
    {
        size = 0;
        stackTopIndex = 0;
    }
    /**
     * Reports stack empty status
     * <p>
     * Note: Does not use if/else
     * <p>
     * @return - Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        return size == 0;
    }
    /**
     * provides peek at top of stack
     * <p>
     * @return - value if successful, FAILED_ACCESS if not
     */
    public int peekTop()
    {
        if( stackTopIndex == -1 )
        {
            return FAILED_ACCESS;
        }
        return stackData[ stackTopIndex ];
    }
    /**
     * Removes and returns data from top of stack
     * <p>
     * @return - value if successful, FAILED_ACCESS if not
     */
    public int pop()
    {
        int valueHolder;
        if( stackTopIndex == -1)
        {
            return FAILED_ACCESS;
        }
        valueHolder = stackData[ stackTopIndex ];
        stackTopIndex -= 1;
        size -= 1;
        return valueHolder;
    }
    /**
     * Checks for resize, then pushes value onto stack
     * <p>
     * Note: end of array is always the top of the stack;
     * <p>
     * index is incremented and then value is appended to array
     * <p>
     * @param newValue - Value to be pushed onto stack
     */
    public void push( int newValue )
    {
        checkForReSize();
        stackTopIndex += 1;
        size += 1;
        stackData[ stackTopIndex ] = newValue;
    }
}
