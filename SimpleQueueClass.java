package p5_package;
/**
 * 
 * @author j_hil
 *
 */
public class SimpleQueueClass
{
    /**
     * Stores current capacity of queue class
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
     * Stores queue head index
     */
    private int headIndex;
    /**
     * Integer array stores queue data
     */
    private int[] queueData;
    /**
     * Stores current size of queue class
     */
    private int size;
    /**
     * Stores queue tail index
     */
    private int tailIndex;
    /**
     * Default constructor, initializes everything
     */
    public SimpleQueueClass()
    {
        capacity = DEFAULT_CAPACITY;
        headIndex = 0;
        size = 0;
        tailIndex = -1;
        queueData = new int[ capacity ];
    }
    /**
     * Initialization constructor,
     * <p>
     * @param capacitySetting - initial capacity of queueData class
     */
    public SimpleQueueClass( int capacitySetting )
    {
        capacity = capacitySetting;
        headIndex = 0;
        size = 0;
        tailIndex = -1;
        queueData = new int[ capacity ];
    }
    /**
     * Copy constructor
     * <p>
     * Note: queue is copied so that head index is at zero and tail 
     * <p>
     * index is at size - 1; i.e., this resets the array to start at zero
     * <p>
     * @param copied - SimpleQueueClass object to be copied
     */
    public SimpleQueueClass( SimpleQueueClass copied )
    {
        int index = 0;
        this.capacity = copied.capacity;
        this.size = copied.size;
        queueData = new int[ capacity ];
        headIndex = 0;
        tailIndex = -1;
        for( index = 0; index < size; index++ )
        {
            updateTailIndex();
            this.queueData[ this.tailIndex ] =
            copied.queueData[ ( copied.tailIndex + index ) % copied.capacity ];
        }
    }
    /**
     * Checks for resize, then enqueues value
     * <p>
     * Note: Updates tail index, then appends value to array at tail index
     * <p>
     * @param newValue - Value to be enqueued
     */
    public void enqueue( int newValue )//TODO
    {
        checkForReSize();
        updateTailIndex();
        queueData[ tailIndex ] = newValue;
        size += 1;
    }
    /**
     * Removes and returns value from front of queue
     * <p>
     * Note: Acquires data from head of queue, then updates head index
     * <p>
     * @return - Value if successful, FAILED_ACCESS if not
     */
    public int dequeue()
    {
        int valueHolder = queueData[ headIndex ];
        updateHeadIndex();
        size -= 1;
        return valueHolder;
    }
    /**
     * Provides peek at front of queue
     * <p>
     * @return - Value if successful, FAILED_ACCESS if not
     */
    public int peekFront()
    {
        if( isEmpty() )
        {
            return FAILED_ACCESS;
        }
        return queueData[ headIndex ];
    }
    /**
     * Reports queue empty state
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
     * Updates queue head index to wrap around array as needed
     * <p>
     * Note: Does not use if/else
     */
    private void updateHeadIndex()
    {
        headIndex = ( headIndex + 1 ) % capacity;
    }
    /**
     * Updates queue tail index to wrap around array as needed
     * <p>
     * Note: Does not use if/else
     */
    private void updateTailIndex()
    {
        tailIndex = ( tailIndex + 1 ) % capacity;
    }
    /**
     * Clears the queue by setting the size to zero, 
     * <p>
     * the tail index to -1 and the head index to zero
     */
    public void clear()
    {
        size = 0;
        headIndex = 0;
        tailIndex = -1;
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
                tempIntArray[ index ] =
                queueData[ ( headIndex + index ) % size ];
            }
            queueData = tempIntArray;
            headIndex = 0;
            tailIndex = size - 1;
            return true;
        }
        return false;
    }
}


