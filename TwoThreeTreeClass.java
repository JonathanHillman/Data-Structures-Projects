package p8_package;
/**
 * 2-3 Tree class that stores integers
 * <p>
 * @author j_hil
 *
 */
public class TwoThreeTreeClass 
{
    
    /**
     * constant used in constructor to indicate which child to create - Left
     */
    private final int LEFT_CHILD_SELECT = 101;
    /**
     * constant used for identifying one data item stored
     */
    private final int ONE_DATA_ITEM = 1;
    /**
     * constant used in constructor to indicate which child to create - Right
     */
    private final int RIGHT_CHILD_SELECT = 102;
    /**
     * constant used for identifying three data items stored
     */
    private final int THREE_DATA_ITEMS = 3;
    /**
     * constant used for identifying two data items stored
     */
    private final int TWO_DATA_ITEMS = 2;
    /**
     * Used for acquiring ordered tree visitations in String form
     */
    private String outputString;
    /**
     * root of tree
     */
    private TwoThreeNodeClass root;
    /**
     * 2-3 node class using NodeDataClass data and three references
     * 
     * @author MichaelL
     */
    private class TwoThreeNodeClass
       {
        /**
         * internal 2-3 node data
         */
        private int leftData, centerData, rightData, numItems;
        
        /**
         * references from 2-3 node
         */
        private TwoThreeNodeClass leftChildRef, centerChildRef, rightChildRef;
        
        /**
         * references for managing 2-3 node adjustments
         */
        private TwoThreeNodeClass auxLeftRef, auxRightRef;
        
        /**
         * parent reference for 2-3 node
         */
        private TwoThreeNodeClass parentRef;
        
        /**
         * Default 2-3 node class constructor
         */
        private TwoThreeNodeClass()
           {
            leftData = centerData = rightData = numItems = 0;
            
            leftChildRef = centerChildRef = rightChildRef = null;
            
            auxLeftRef = auxRightRef = parentRef = null;
           }
        
        /**
         * Initialization 2-3 node class constructor
         * 
         * @param centerIn integer data sets first node initialization
         */
        private TwoThreeNodeClass( int centerIn )
           {
            centerData = centerIn;

            leftData = rightData = 0;
            
            numItems = 1;
            
            leftChildRef = centerChildRef = rightChildRef = null;
            
            auxLeftRef = auxRightRef = parentRef = null;
           }

        /**
         * Private constructor used to create new left or right child node
         * of given parent with the children linked from
         * a current three-node object
         *
         * @param childSelect integer selection value that informs 
         * the constructor to create a left or a right child
         * along with making all the sub-child links; 
         * uses class constants LEFT_CHILD_SELECT and RIGHT_CHILD_SELECT
         * 
         * @param localRef TwoThreeNodeClass reference
         * with three-node data and associated references
         * 
         * @param parRef TwoThreeNodeclass parent reference
         * for linking with new left or right child node that is created
         */
        private TwoThreeNodeClass( int childSelect, 
                       TwoThreeNodeClass localRef, TwoThreeNodeClass parRef )
           {
            if( childSelect == LEFT_CHILD_SELECT )
               {
                this.centerData = localRef.leftData;
                this.leftChildRef = localRef.leftChildRef;
                this.rightChildRef = localRef.auxLeftRef;
                
                if( leftChildRef != null )
                   {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                   }
               }
            
            else  // assume right child select
               {
                this.centerData = localRef.rightData;
                this.leftChildRef = localRef.auxRightRef;
                this.rightChildRef = localRef.rightChildRef;

                if( rightChildRef != null )
                   {
                    this.leftChildRef.parentRef = this;
                    this.rightChildRef.parentRef = this;
                   }
               }

            this.leftData = this.rightData = 0;
            this.numItems = 1;
            this.centerChildRef = null;
            this.auxLeftRef = this.auxRightRef = null;
            this.parentRef = parRef;
           }
        
        /**
         * Copy 2-3 node class constructor
         * <p>
         * Note: Only copies data; does not copy links 
         * as these would be incorrect for the new tree 
         * (i.e., they would be related to the copied tree)
         * 
         * @param copied TwoThreeNodeClass object to be copied
         */
        private TwoThreeNodeClass( TwoThreeNodeClass copied )
           {
            leftData = copied.leftData;
            centerData = copied.centerData;
            rightData = copied.rightData;
            
            numItems = copied.numItems;

            leftChildRef = centerChildRef = rightChildRef = null;
            
            auxLeftRef = auxRightRef = parentRef = null;
           }
       }
    
    public TwoThreeTreeClass()
    {
        outputString = "";
        root = null;
    }
    /**
     * Copy 2-3 tree constructor
     * <p>
     * @param copied - TwoThreeTreeClass object to be duplicated;
     * <p>
     * data is copied, but new nodes and references must be created
     */
    public TwoThreeTreeClass( TwoThreeTreeClass copied )
    {
        this.root = new TwoThreeNodeClass();
        
        this.root.leftChildRef = 
        copyConstructorHelper( copied.root.leftChildRef );
        
        this.root.leftData = copied.root.leftData;
        
        this.root.auxLeftRef =
        copyConstructorHelper( copied.root.auxLeftRef );
        
        this.root.centerData = copied.root.centerData;
        
        this.root.auxRightRef =
        copyConstructorHelper( copied.root.auxRightRef );
        
        this.root.rightData = copied.root.rightData;
        
        this.root.rightChildRef =
        copyConstructorHelper( copied.root.rightChildRef );
        
        this.root.numItems = copied.root.numItems;
        
    }
    /**
     * Implements tree duplication effort with recursive method;
     * <p>
     * copies data into newly created nodes and creates all
     * <p>
     * new references to child nodes
     * <p>
     * @param workingCopied - TwoThreeNodeClass reference that is updated to
     * <p>
     * lower level children with each recursive call
     * <p>
     * @return - TwoThreeNodeClass reference to next higher level node;
     * <p>
     * last return is to root node of THIS object
     */
    private TwoThreeNodeClass copyConstructorHelper( TwoThreeNodeClass workingCopied)
    {
        TwoThreeNodeClass tempParentNode = new TwoThreeNodeClass();
        TwoThreeNodeClass tempChildNode = new TwoThreeNodeClass();
        
        if( workingCopied != null)
        {
            tempChildNode = 
            copyConstructorHelper( workingCopied.leftChildRef );
            
            tempChildNode.parentRef = tempParentNode;
            tempParentNode.leftChildRef = tempChildNode;

            
            
            tempParentNode.leftData = workingCopied.leftData;

            
            
            tempChildNode = 
            copyConstructorHelper( workingCopied.auxLeftRef );
            
            tempChildNode.parentRef = tempParentNode;
            tempParentNode.auxLeftRef = tempChildNode;

            
            
            tempParentNode.centerData = workingCopied.centerData;

            
            
            tempChildNode = 
            copyConstructorHelper( workingCopied.auxRightRef );
            
            tempParentNode.auxRightRef = tempChildNode;
            tempChildNode.parentRef = tempParentNode;

            
            
            tempParentNode.rightData = workingCopied.rightData;

            
            
            tempChildNode = 
            copyConstructorHelper( workingCopied.rightChildRef );
            
            tempParentNode.rightChildRef = tempChildNode;
            tempChildNode.parentRef = tempParentNode;

            
            
            tempParentNode.numItems = workingCopied.numItems;

        }
        return tempParentNode;
    }
    /**
     * Method is called when addItemHelper arrives at the bottom of the
     * <p>
     * 2-3 search tree (i.e., all node's children are null);
     * <p>
     * @param localRef - TwoThreeNodeClass reference has original node data 
     * <p>
     * and contains added value when method completes; 
     * <p>
     * method does not manage any node links
     * <p>
     * @param itemVal - integer value to be added to 2-3 node
     */
    private void addAndOrganizeData( TwoThreeNodeClass localRef, int itemVal )
    {
        //assume one item
        if( localRef.numItems == ONE_DATA_ITEM )
        {
            //assume insert is larger value
            if( itemVal > localRef.centerData )
            {
                localRef.leftData = localRef.centerData;
                localRef.rightData = itemVal;
            }
            //assume inserting smaller value
            else
            {
                localRef.rightData = localRef.centerData;
                localRef.leftData = itemVal;
            }
            localRef.numItems++;
        }
        // assume 2 items
        else
        {
            // assume itemVal is the largest value
            if( itemVal > localRef.rightData )
            {
                localRef.centerData = localRef.rightData;
                localRef.rightData = itemVal;
            }
            // assume itemVal is the smallest value
            else if( itemVal < localRef.leftData )
            {
                localRef.centerData = localRef.leftData;
                localRef.leftData = itemVal;
            }
            // assume itemVal is a duplicate value or is in 
            //between smallest and largest value
            else
            {
                localRef.centerData = itemVal;
            }
            localRef.numItems++;
        }
        fixUpInsert( localRef );
    }
    /**
     * Adds item to 2-3 tree using addItemHelper as needed
     * <p>
     * @param itemVal - integer value to be added to the tree
     */
    public void addItem( int itemVal )
    {
        if( root == null )
        {
            root = new TwoThreeNodeClass ( itemVal );
        }
        else
        {
            addItemHelper( root, itemVal );
        }
    }
    /**
     * Helper method searches from top of tree to bottom using divide and
     * <p>
     * conquer strategy to find correct location (node) for new added value;
     * <p>
     * once location is found, item is added to node using addAndOrganizeData
     * <p>
     * and then fixUpInsert is called in case the updated node has
     * <p>
     * become a three-value node
     * <p>
     * @param parRef - TwoThreeNodeClass reference to the parent of the 
     * <p>
     * current reference at a given point in the recursion process
     * <p>
     * @param localRef - TwoThreeNodeClass reference to the current item
     * <p>
     * at the same given point in the recursion process
     * <p>
     * @param itemVal - integer value to be added to the tree
     */
    private void addItemHelper( TwoThreeNodeClass localRef,
                                int itemVal )
    {
        // we only need to check for localRef.leftChildRef to see
        // if it has any children at all
        if ( localRef.leftChildRef == null )
        {
            addAndOrganizeData( localRef, itemVal );
        }
        else if( localRef.numItems == ONE_DATA_ITEM )
        {
            if( itemVal > localRef.centerData )
            {
                addItemHelper( localRef.rightChildRef, itemVal );
            }
            else
            {
                addItemHelper( localRef.leftChildRef, itemVal );
            }
        }
        //assume two node and has children
        else
        {
            if( itemVal < localRef.leftData )
            {
                addItemHelper( localRef.leftChildRef, itemVal );
            }
            else if( itemVal > localRef.rightData )
            {
                addItemHelper( localRef.rightChildRef, itemVal );
            }
            else
            {
                addItemHelper( localRef.centerChildRef, itemVal );
            }
        }
    }
    /**
     * Method clears tree so that new items can be added again
     */
    public void clear()
    {
        root = null;
    }
    /**
     * Method used to fix tree any time a three-value node has been added 
     * <p>
     * to the bottom of the tree; it is always called but decides to act
     * <p>
     * only if it finds a three-value node Resolves current three-value node
     * <p>
     * which may add a value to the node above; if the node above becomes
     * <p>
     * a three-value node, then this is resolved with the next recursive call
     * <p>
     * Recursively climbs from bottom to top of tree resolving
     * <p>
     * any three-value nodes found
     * <p>
     * @param localRef - TwoThreeNodeClas reference initially given the
     * <p>
     * currently updated node, then is given parent node recursively each
     * <p>
     * time a three-value node was resolved
     */
    private void fixUpInsert( TwoThreeNodeClass localRef )
    {
        if( localRef.numItems == THREE_DATA_ITEMS)
        {
            if( localRef.parentRef == null )
            {
                localRef.parentRef =
                new TwoThreeNodeClass( localRef.centerData );
                
                
                localRef.parentRef.rightChildRef =
                new TwoThreeNodeClass( RIGHT_CHILD_SELECT,
                                       localRef,
                                       localRef.parentRef );
                localRef.parentRef.leftChildRef =
                new TwoThreeNodeClass( LEFT_CHILD_SELECT,
                                       localRef,
                                       localRef.parentRef );
                root = localRef.parentRef;
            }
            else if( localRef.parentRef.numItems == ONE_DATA_ITEM )
            {
                //assume right child
                if( localRef.parentRef.rightChildRef == localRef )
                {
                    localRef.parentRef.leftData = 
                    localRef.parentRef.centerData;
                    
                    
                    localRef.parentRef.rightData = localRef.centerData;


                    localRef.parentRef.rightChildRef = 
                    new TwoThreeNodeClass ( RIGHT_CHILD_SELECT,
                                            localRef,
                                            localRef.parentRef );


                    localRef.parentRef.centerChildRef = 
                    new TwoThreeNodeClass ( LEFT_CHILD_SELECT,
                                            localRef,
                                            localRef.parentRef );

                    localRef.parentRef.numItems++;
                }
                //assume left child
                else
                {
                    localRef.parentRef.rightData = 
                    localRef.parentRef.centerData;
                    
                    
                    localRef.parentRef.leftData = localRef.centerData;
                    
                    
                    localRef.parentRef.centerChildRef = 
                    new TwoThreeNodeClass ( RIGHT_CHILD_SELECT,
                                            localRef,
                                            localRef.parentRef );
                    
                    
                    localRef.parentRef.leftChildRef =
                    new TwoThreeNodeClass ( LEFT_CHILD_SELECT,
                                            localRef,
                                            localRef.parentRef );
                    
                    
                    localRef.parentRef.numItems++;
                }
            }
            else if( localRef.parentRef.numItems == TWO_DATA_ITEMS )
            {
                //assume right child
                if( localRef.parentRef.rightChildRef == localRef )
                {
                    localRef.parentRef.centerData = 
                    localRef.parentRef.rightData;
                    
                    
                    localRef.parentRef.rightData = localRef.centerData;
                    
                    
                    localRef.parentRef.auxLeftRef =
                    localRef.parentRef.centerChildRef;
                    
                    
                    localRef.parentRef.rightChildRef =
                    new TwoThreeNodeClass ( RIGHT_CHILD_SELECT,
                                            localRef,
                                            localRef.parentRef );
                    
                    
                    localRef.parentRef.auxRightRef =
                    new TwoThreeNodeClass ( LEFT_CHILD_SELECT,
                                            localRef,
                                            localRef.parentRef );
                }
                //assume left child
                else if( localRef.parentRef.leftChildRef == localRef )
                {
                    localRef.parentRef.centerData = 
                    localRef.parentRef.leftData;
                    
                    
                    localRef.parentRef.leftData = localRef.centerData;
                    
                    
                    localRef.parentRef.auxRightRef =
                    localRef.parentRef.centerChildRef;
                    
                    
                    localRef.parentRef.auxLeftRef =
                    new TwoThreeNodeClass( RIGHT_CHILD_SELECT,
                                           localRef,
                                           localRef.parentRef );
                    
                    
                    localRef.parentRef.leftChildRef =
                    new TwoThreeNodeClass( LEFT_CHILD_SELECT,
                                           localRef,
                                           localRef.parentRef );
                }
                //assume center child
                else
                {
                    localRef.parentRef.centerData = localRef.centerData;
                    
                    
                    localRef.parentRef.auxLeftRef =
                    new TwoThreeNodeClass( LEFT_CHILD_SELECT,
                                           localRef,
                                           localRef.parentRef );
                    
                    localRef.parentRef.auxRightRef =
                    new TwoThreeNodeClass( RIGHT_CHILD_SELECT,
                                           localRef,
                                           localRef.parentRef );
                }
                
                localRef.parentRef.numItems++;
                fixUpInsert( localRef.parentRef );
            }
        }
    }
    /**
     * Tests center value if single node, tests left and right values
     * <p>
     * if two-value node; returns true if specified data is found
     * <p>
     * in any given node Note: Method does not use any 
     * <p>
     * branching operations such as if/else/etc.
     * <p>
     * @param localRef - TwoThreeNodeClass reference to node
     * <p>
     * with one or two data items in it
     * <p>
     * @param searchVal - integer value to be found in given node
     * <p>
     * @return - boolean result of test
     */
    private boolean foundInNode( TwoThreeNodeClass localRef, int searchVal )
    {
        
        return (localRef.numItems == ONE_DATA_ITEM && 
                localRef.centerData == searchVal)
                
                                ||
               
               (localRef.numItems == TWO_DATA_ITEMS &&
                localRef.leftData == searchVal)
               
                                ||
               
               (localRef.numItems == TWO_DATA_ITEMS &&
                localRef.rightData == searchVal);
    }
    /**
     * Search method used by programmer to find specified item in 2-3 tree
     * <p>
     * @param searchVal - integer value to be found
     * <p>
     * @return - boolean result of search effort
     */
    public boolean search( int searchVal )
    {
        if( root == null )
        {
            return false;
        }
        else
        {
            return searchHelper( root, searchVal );
        }
    }
    /**
     * Search helper method that traverses through tree in a recursive divide
     * <p>
     * and conquer search fashion to find given integer in 2-3 tree
     * <p>
     * @param localRef - TwoThreeNodeClass reference to given node at any
     * <p>
     * point during the recursive process
     * <p>
     * @param searchVal - integer value to be found in tree
     * <p>
     * @return - boolean result of search effort
     */
    private boolean searchHelper( TwoThreeNodeClass localRef, int searchVal )
    {
        if( localRef == null )
        {
            return false;
        }
        else if( foundInNode( localRef, searchVal ) )
        {
            return true;
        }
        // now we have different cases depending on the node size
        
        // if it is a one node
        else if( localRef.numItems == ONE_DATA_ITEM )
        {
            if( searchVal < localRef.centerData )
            {
                return searchHelper( localRef.leftChildRef, searchVal );
            }
            else
            {
                return searchHelper( localRef.rightChildRef, searchVal );
            }
        }
        
        //if it is a two node
        else
        {
            if( searchVal < localRef.leftData )
            {
                return searchHelper( localRef.leftChildRef, searchVal );
            }
            else if( searchVal > localRef.rightData )
            {
                return searchHelper( localRef.rightChildRef, searchVal );
            }
            else
            {
                return searchHelper( localRef.centerChildRef, searchVal );
            }
        }
    }
    /**
     * Public method called by user to display data in order
     * <p>
     * @return - String result to be displayed and/or analyzed
     */
    public String inOrderTraversal()
    {
        outputString = "";
        if( root == null )
        {
            outputString += "The 2-3 Binary Search Tree is currently empty";
        }
        else
        {
            inOrderTraversalHelper ( root );
        }
        return outputString;
    }
    /**
     * Helper method conducts in order traversal with 2-3 tree
     * <p>
     * Shows location of each value in a node: "C" at center of single
     * <p>
     * node "L" or "R" at left or right of two-value node
     * <p>
     * @param localRef - TwoThreeNodeClass reference to current location at
     * <p>
     *any given point in the recursion process
     */
    public void inOrderTraversalHelper( TwoThreeNodeClass localRef )
    {
        if( localRef != null )
        {
            if( localRef.numItems == ONE_DATA_ITEM )
            {
                inOrderTraversalHelper( localRef.leftChildRef );
                outputString += "C" + localRef.centerData + " ";
                inOrderTraversalHelper( localRef.rightChildRef );
            }
            //assume two node
            else
            {
                inOrderTraversalHelper( localRef.leftChildRef );
                outputString += "L" + localRef.leftData + " ";
                inOrderTraversalHelper( localRef.centerChildRef );
                outputString += "R" + localRef.rightData + " ";
                inOrderTraversalHelper( localRef.rightChildRef );
            }
        }
    }
}
