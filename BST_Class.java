package p7_package;
/**
 * 
 * @author j_hil
 *
 */
public class BST_Class 
{
    /**
     * Root of BST
     */
    private StudentClassNode BST_Root;
    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private String outputString;
    /**
     * Default class constructor, initializes BST
     */
    public BST_Class()
    {
        BST_Root = null;
        outputString = "";
    }
    /**
     * Copy constructor
     * <p>
     * Note: Uses copyConstHelper
     * <p>
     * @param copied - BST_Class object to be copied
     */
    public BST_Class( BST_Class copied )
    {
        this.BST_Root = copyConstHelper( copied.BST_Root );
    }
    /**
     * Copy constructor helper, recursively adds nodes to duplicate tree
     * <p>
     * @param copiedRef - StudentClassNode reference
     * <p>
     * for accessing copied object data
     * <p>
     * @return - StudentClassNode reference to node added 
     * <p>
     * at current level of recursion
     */
    private StudentClassNode copyConstHelper( StudentClassNode copiedRef )
    {
        StudentClassNode newNode = null;
        if( copiedRef != null )
        {
            newNode = new StudentClassNode( copiedRef );
            newNode.rightChildRef = copyConstHelper( copiedRef.rightChildRef );
            newNode.leftChildRef = copyConstHelper( copiedRef.leftChildRef );
        }
        return newNode;
    }
    /**
     * Searches for data in BST given StudentClassNode with necessary key
     * <p>
     * @param searchData - StudentClassNode item containing key
     * <p>
     * @return - StudentClassNode reference to found data
     */
    public StudentClassNode search( StudentClassNode searchData )
    {
        return searchHelper( BST_Root, searchData );
    }
    /**
     * Helper method for BST search action
     * <p>
     * @param localRoot - StudentClassNode tree
     * <p>
     * root reference at the current recursion level
     * <p>
     * @param searchData - StudentClassNode item containing key
     * <p>
     * @return - StudentClassNode item found
     */
    private StudentClassNode searchHelper( StudentClassNode localRoot, 
                                            StudentClassNode searchData )
    {
        if ( localRoot == null )
        {
            return null;
        }
        else if( searchData.studentID > localRoot.studentID )
        {
            return searchHelper( localRoot.rightChildRef, searchData );
        }
        else if( searchData.studentID < localRoot.studentID )
        {
            return searchHelper( localRoot.leftChildRef, searchData );
        }
        return localRoot;
    }
    /**
     * Clears tree
     */
    public void clearTree()
    {
        BST_Root = null;
    }
    /**
     * Test for empty tree
     * <p>
     * @return - Boolean result of test
     */
    public boolean isEmpty()
    {
        return BST_Root == null;
    }
    /**
     * Insert method for BST
     * <p>
     * Note: uses insert helper method
     * <p>
     * @param inData - StudentClassNode data to be added to BST
     */
    public void insert( StudentClassNode inData )
    {
        if( isEmpty() )
        {
            BST_Root = new StudentClassNode ( inData );
        }
        insertHelper( BST_Root, inData );
    }
    /**
     * Insert Helper for BST insert action
     * <p>
     * @param localRoot - StudentClassNode tree root 
     * <p>
     * reference at the current recursion level
     * <p>
     * @param inData - StudentClassNode item to be added to BST
     */
    public void insertHelper( StudentClassNode localRoot,
                              StudentClassNode inData )
    {
        if( inData.studentID > localRoot.studentID && 
                localRoot.rightChildRef == null )
        {
            localRoot.rightChildRef =  new StudentClassNode( inData );
        }
        
        else if( inData.studentID < localRoot.studentID && 
                     localRoot.leftChildRef == null )
        {
            localRoot.leftChildRef = new StudentClassNode( inData );
        }
        
        else if( inData.studentID > localRoot.studentID )
        {
            insertHelper( localRoot.rightChildRef, inData );
        }
        
        else if( inData.studentID < localRoot.studentID )
        {
            insertHelper( localRoot.leftChildRef, inData );
        }
    }
    /**
     * Provides preOrder traversal for user as a string
     * <p>
     * @return - String containing pre order output
     */
    public String outputPreOrder()
    {
        outputString = "";
        if( BST_Root == null )
        {
            outputString = "The Binary Search Tree Is Currently Empty";
        }
        else
        {
            outputPreOrderHelper( BST_Root );
        }
        return outputString;
    }
    /**
     * Provides preOrder traversal action helper
     * <p>
     * @param localRoot - StudentClassNode tree root
     * <p>
     * reference at the current recursion level
     */
    private void outputPreOrderHelper( StudentClassNode localRoot )
    {
        if( localRoot != null )
        {
            outputString += localRoot.toString() + '\n';
            outputPreOrderHelper( localRoot.leftChildRef );
            outputPreOrderHelper( localRoot.rightChildRef );
        }
    }
    /**
     * Provides postOrder traversal for use as a string
     * <p>
     * @return - String containing post order output
     */
    public String outputPostOrder()
    {
        outputString = "";
        if( BST_Root == null )
        {
            outputString += "The Binary Search Tree Is Currently Empty";
        }
        else
        {
            outputPostOrderHelper( BST_Root );
        }
        return outputString;
    }
    /**
     * Provides postOrder action helper
     * <p>
     * @param localRoot - StudentClassNode tree root
     * <p>
     * reference at the current recursion level
     */
    public void outputPostOrderHelper( StudentClassNode localRoot )
    {
        if( localRoot != null )
        {
            outputPostOrderHelper( localRoot.leftChildRef );
            outputPostOrderHelper( localRoot.rightChildRef );
            outputString += localRoot.toString() + '\n';
        }
    }
    /**
     * Provides inOrder traversal for user as a string
     * <p>
     * @return - String containing in order output
     */
    public String outputInOrder()
    {
        outputString = "";
        if( BST_Root == null )
        {
            outputString += "The Binary Search Tree Is Currently Empty";
        }
        else
        {
            outputInOrderHelper( BST_Root );
        }
        return outputString;
    }
    /**
     * Provides inOrder traversal action helper
     * <p>
     * @param localRoot - StudentClassNode tree root
     * <p>
     * reference at the current recursion level
     */
    private void outputInOrderHelper( StudentClassNode localRoot )
    {
        if( localRoot != null )
        {
            outputInOrderHelper( localRoot.leftChildRef );
            outputString += localRoot.toString() + '\n';
            outputInOrderHelper( localRoot.rightChildRef );
        }
    }
    /**
     * Removes data node from tree using given key
     * <p>
     * Note: uses remove helper method
     * <p>
     * @param inData - StudentClassNode that includes the necessary key
     * <p>
     * @return - StudentClassNode result of remove action
     */
    public StudentClassNode removeNode( StudentClassNode inData )
    {
        StudentClassNode nodeToRemove = search( inData );
        return removeNodeHelper( nodeToRemove, inData );
    }
    /**
     * Remove helper for BST remove action
     * <p>
     * @param localRoot - StudentClassNode tree root 
     * <p>
     * reference at the current recursion level
     * <p>
     * @param outData - StudentClassNode item that includes the necessary key
     * <p>
     * @return - StudentClassNode reference result of remove helper action
     */
    private StudentClassNode removeNodeHelper( StudentClassNode localRoot,
                                                   StudentClassNode outData )
    {
        StudentClassNode tempNode;
        StudentClassNode removedNode;
        if( localRoot == null )
        {
            return null;
        }
        else if( localRoot.leftChildRef == null &&
                     localRoot.rightChildRef == null )
        {
            localRoot = null;
            return null;
        }
        else if( localRoot.leftChildRef == null &&
                     localRoot.rightChildRef != null )
        {
            tempNode = new StudentClassNode( localRoot );
            localRoot.setStudentClassData( localRoot.rightChildRef );
            return tempNode;
        }
        else if( localRoot.leftChildRef != null &&
                     localRoot.rightChildRef == null )
        {
            tempNode = new StudentClassNode( localRoot );
            localRoot.setStudentClassData ( localRoot.leftChildRef );
            return tempNode;
        }
        else if ( localRoot.leftChildRef != null &&
                      localRoot.rightChildRef != null )
        {
            removedNode = removeFromMin( localRoot, localRoot.rightChildRef );
            removedNode.rightChildRef = localRoot.rightChildRef;
            removedNode.leftChildRef = localRoot.leftChildRef;
            tempNode = new StudentClassNode( localRoot );
            localRoot.setStudentClassData (removedNode);
            return localRoot;
        }
        //to make compiler happy
        return null;
    }
    /**
     * Searches tree from given node to minimum value node below it,
     * <p>
     * stores data value found, and then unlinks the node
     * <p>
     * @param minParent - StudentClassNode reference to current node
     * <p>
     * @param minChild - StudentClassNode reference to child node to be tested
     * <p>
     * @return - StudentClassNode reference containing removed node
     */
    private StudentClassNode removeFromMin( StudentClassNode minParent,
                                                StudentClassNode minChild )
    {
        if( minChild.leftChildRef != null )
        {
            return removeFromMin( minChild, minChild.leftChildRef );
        }
        minParent.leftChildRef = minChild.rightChildRef;
        return minChild;
    }
}
