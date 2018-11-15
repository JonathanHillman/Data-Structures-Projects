package p6_package;
/**
 * 
 * @author j_hil
 *
 */
public class LinkListIteratorClass {
    /**
     * reference to current/cursor node
     */
    private NodeClass cursorRef;
    /**
     * Provides constant -999999 for access failure messaging
     */
    public static int FAILED_ACCESS = -999999;
    /**
     * reference to head node
     */
    private NodeClass headRef;
    /**
     * Default constructor
     */
    public LinkListIteratorClass()
    {
        cursorRef = null;
        headRef = null;
    }
    /**
     * Copy Constructor
     * <p>
     * @param copied - LinkListIteratorClass to be copied
     */
    public LinkListIteratorClass( LinkListIteratorClass copied)
    {
        NodeClass tempCopyCursorRef;
        NodeClass tempCursorRef;
        NodeClass tempNodeClass;
        if( copied.headRef == null )
        {
            this.headRef = null;
            this.cursorRef = null;
        }
        else
        {
            this.headRef = new NodeClass( copied.headRef.nodeData );
            tempCursorRef= this.headRef;
            tempCopyCursorRef = copied.headRef;
            while( tempCopyCursorRef.nextRef != null )
            {
                tempCopyCursorRef = tempCopyCursorRef.nextRef;
                tempNodeClass = new NodeClass( tempCopyCursorRef.nodeData );
                if( tempCopyCursorRef == copied.cursorRef )
                {
                    cursorRef = tempNodeClass;
                }
                tempCursorRef.nextRef = tempNodeClass;
                tempCursorRef = tempCursorRef.nextRef;
                
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
     * Clears list
     */
    public void clear()
    {
        headRef = null;
        cursorRef = null;
    }
    /**
     * Displays linked list for diagnostic purposes
     */
    public void displayList()
    {
        String returnString = " ";
        NodeClass tempCursorRef = headRef;
        if( !isEmpty() )
        {
                returnString += headRef.nodeData;
        
            while( tempCursorRef.nextRef != null )
            {
                tempCursorRef = tempCursorRef.nextRef;
                if( tempCursorRef == this.cursorRef )
                {
                    returnString += ", [" + tempCursorRef.nodeData + "]";
                }
                else
                {
                    returnString += ", " + tempCursorRef.nodeData;
                }
            }
        }
           System.out.println(returnString);
    }
    /**
     * Acquires data at cursor
     * <p>
     * @return - integer value at cursor location if available,
     * <p>
     * FAILED_ACCESS otherwise
     */
    public int getDataAtCursor()
    {
        if( cursorRef == null )
        {
            return FAILED_ACCESS;
        }
        return cursorRef.nodeData;
    }
    /**
     * Recursive method finds a reference to the node just prior to the cursor;
     * <p>
     * initially called with head reference
     * <p>
     * @param workingRef - current NodeClass reference in the list
     * <p>
     * @return - NodeClass reference to the item just prior
     * <p>
     * to the cursor location
     */
    private NodeClass getRefBeforeCursor( NodeClass workingRef )
    {
        if( isEmpty() || headRef == cursorRef )
        {
            return null;
        }
        else if( workingRef.nextRef == cursorRef )
        {
            return workingRef;
        }
         return getRefBeforeCursor( workingRef.nextRef );
        
    }
    /**
     * Inserts value after cursor
     * <p>
     * @param newValue - Value to be inserted in list
     */
    public void insertAfterCursor( int newValue )
    {
        NodeClass tempNode;
        if( isEmpty() )
        {
            headRef = new NodeClass( newValue );
            cursorRef = headRef;
        }
        if( isAtEndOfList() )
        {
            cursorRef.nextRef = new NodeClass( newValue );
        }
        else
        {
            tempNode = cursorRef.nextRef;
            cursorRef.nextRef = new NodeClass( newValue );
            cursorRef.nextRef.nextRef = tempNode;
        }
    }
    /**
     * Inserts value prior to cursor
     * <p>
     * @param newValue - Value to be inserted in list
     */
    public void insertBeforeCursor( int newValue )
    {
        NodeClass tempNode;
        NodeClass insertionNode;
        if( cursorRef == null )
        {
            headRef = new NodeClass( newValue );
            cursorRef = headRef;
        }
        else if( cursorRef == headRef )
        {
            headRef = new NodeClass( newValue );
            headRef.nextRef = cursorRef;
        }
        else if( isAtEndOfList() )
        {
            insertionNode = new NodeClass( newValue );
            insertionNode = getRefBeforeCursor( headRef ).nextRef;
            insertionNode.nextRef = cursorRef;
        }
        else
        {
            insertionNode = new NodeClass( newValue );
            tempNode = cursorRef;
            moveNext();
            cursorRef.nextRef = cursorRef;
            tempNode.nextRef = insertionNode;
        }
    }
    /**
     * Checks for at last item in list
     * <p>
     * @return - Boolean result of test
     */
    public boolean isAtEndOfList()
    {
        return cursorRef.nextRef == null;
    }
    /**
     * Reports list empty status
     * <p>
     * @return - Boolean evidence of empty list
     */
    public boolean isEmpty()
    {
        return headRef == null;
    }
    /**
     * Moves cursor to next node if it is not at end
     */
    public void moveNext()
    {
        if( !isAtEndOfList() )
        {
            cursorRef = cursorRef.nextRef;
        }
    }
    /**
     * Moves cursor to previous node if it is not already at head
     */
    public void movePrevious()
    {
        if( cursorRef != headRef )
        {
            cursorRef = getRefBeforeCursor( headRef );
        }
    }
    /**
     * Removes item at current location/cursor if available
     * <p>
     * Sets cursor to previous node unless cursor is at head
     * <p>
     * @return - integer value removed if available, FAILED_ACCESS otherwise
     */
    public int removeDataAtCursor()
    {
        int valueholder;
        if( cursorRef == headRef )
        {
            valueholder = getDataAtCursor();
            headRef = headRef.nextRef;
            cursorRef = headRef;
            return valueholder;
        }
        else if( cursorRef == null )
        {
            return FAILED_ACCESS;
        }
        else if( isAtEndOfList() )
        {
            valueholder = getDataAtCursor();
            movePrevious();
            cursorRef.nextRef = null;
            return valueholder;
        }
        else
        {
            valueholder = cursorRef.nodeData;
            movePrevious();
            cursorRef.nextRef = cursorRef.nextRef.nextRef;
            return valueholder;
        }
    }
    /**
     * Sets cursor to first item in list
     */
    public void setToFirstItem()
    {
        cursorRef = headRef;
    }
    /**
     * Sets cursor to last item in list
     */
    public void setToLastItem() // can I do this recursively?
    {
        cursorRef = headRef;
        while( !isAtEndOfList() )
        {
            moveNext();
        }
    }
}
