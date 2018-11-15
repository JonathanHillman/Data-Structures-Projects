package p6_package;
/**
 * 
 * @author j_hil
 *
 */
public class LinkListQueueClass
{
    /**
     * Provides constant -999999 for access failure messaging
     */
    public static int FAILED_ACCESS = -999999;
    /**
     * Stores queue head reference
     */
    private NodeClass headRef;
    /**
     * Stores queue tail reference
     */
    private NodeClass tailRef;
    /**
     * Default constructor
     */
    public LinkListQueueClass()
    {
        headRef = null;
        tailRef = null;
    }
    /**
     * Copy constructor
     * <p>
     * @param copied - LinkListQueueClass to be copied
     */
    public LinkListQueueClass( LinkListQueueClass copied )
    {
        NodeClass tempCopiedCursorRef;
        if( copied.headRef == null )
        {
            this.headRef = null;
            this.tailRef = null;
        }
        else
        {
            this.headRef = new NodeClass( copied.headRef.nodeData );
            this.tailRef = headRef;
            tempCopiedCursorRef = copied.headRef;
            while( tempCopiedCursorRef.nextRef != null )
            {
                tempCopiedCursorRef = tempCopiedCursorRef.nextRef;
                tailRef.nextRef = 
                        new NodeClass ( tempCopiedCursorRef.nodeData );
                tailRef = tailRef.nextRef;
                
            }
        }
        
        
    }
    private class NodeClass
    {
        int nodeData;
        
        NodeClass nextRef;
        
        private NodeClass()
        {
            nodeData = 0;
            
            nextRef = null;
        }
        private NodeClass( int newData )
        {
            nodeData = newData;
            
            nextRef = null;
        }
    }
    /**
     * Appends value to end of queue
     * <p>
     * @param newValue - Value to be enqueued
     */
    public void enqueue( int newValue )
    {
        if( headRef == null )
        {
            headRef = new NodeClass( newValue );
            tailRef = headRef;
        }
        else
        {
        tailRef.nextRef = new NodeClass ( newValue );
        tailRef = tailRef.nextRef;
        }
    }
    /**
     * Removes and returns value from front of queue
     * <p>
     * @return - Value if successful, FAILED_ACCESS if not
     */
    public int dequeue()
    {
        int tempValueHolder;
        if( headRef == null )
        {
            return FAILED_ACCESS;
        }
        else if( headRef == tailRef )
        {
            tempValueHolder = headRef.nodeData;
            headRef = null;
            tailRef = null;
            return tempValueHolder;
        }
        else
        {
            tempValueHolder = headRef.nodeData;
            headRef = headRef.nextRef;
            return tempValueHolder;
        }
    }
    /**
     * Provides peek at front of queue
     * <p>
     * @return - Value if successful, FAILED_ACCESS if not
     */
    public int peekFront()
    {
        if( headRef == null )
        {
            return FAILED_ACCESS;
        }
        return headRef.nodeData;
    }
    /**
     * Reports queue empty state
     * <p>
     * @return - Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        return headRef == null;
    }
    /**
     * Clears the queue by setting the head and tail references to null
     */
    public void clear()
    {
        headRef = null;
        tailRef = null;
    }
}
