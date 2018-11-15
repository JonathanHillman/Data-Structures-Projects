package p4_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 * @author Jonathan Hillman and Michael Leverington
 *
 */
public class MatrixAnalysisClass
   {
    /**
     * constant definition for maximum input string
     */
    private static final int MAX_INPUT_CHARS = 80;
    
    /**
     * constant definition of EOF marker
     */
    private static final int EOF_MARKER = -1;

    /**
     * constant specification of number of spaces 
     * <p>
     * for each recursive level indent
     */
    private final int REC_LEVEL_INDENT = 3;
    
    /**
     * constant definition for COLON    
     */
    private static final char COLON = ':';
    
    /**
     * constant definition for SPACE    
     */
    private static final char SPACE = ' ';
    
    /**
     * constant definition for minus sign
     */
    private static final char MINUS_SIGN = '-';
    
    /**
     * constant control code for display method
     */
    private static final int VALID_ITEM = 101;
    
    /**
     * constant control code for display method
     */
    private static final int INVALID_ITEM = 102;
    
    /**
     * constant control code for display method
     */
    private static final int OVER_SUM = 103;
    
    /**
     * FileReader object used for data upload
     */
    private FileReader fileIn;
   
    /**
     * flag selects show operations
     */
    private boolean verboseFlag;
    
    /**
     * specification of member row height
     */
    private int arrayRowHeight;
    
    /**
     * specification of member row width
     */
    private int arrayRowWidth;
    
    /**
     * two dimensional array for data storage
     */
    private int[][] dataArray;

    /**
     * SimpleGenericSetClass holds locations found during search
     */
    private SimpleGenericSetClass<CellDataClass> locationSet;
    
    /**
     * Default constructor
     */
    public MatrixAnalysisClass()
       {
        verboseFlag = false;
        dataArray = null;
        arrayRowWidth = 0;
        arrayRowHeight = 0;
        fileIn = null;
        locationSet = new SimpleGenericSetClass<CellDataClass>();
       }
    
    /**
     * Initialization constructor
     * <p>
     * @param verboseSetting boolean flag for verbose setting;
     * <p>
     * indicates whether or not to show search process
     */
    public MatrixAnalysisClass( boolean verboseSetting )
       {
        verboseFlag = verboseSetting;
        dataArray = null;
        arrayRowWidth = 0;
        arrayRowHeight = 0;
        fileIn = null;
        locationSet = new SimpleGenericSetClass<CellDataClass>();
       }
    
    /**
     * Method calls helper to find contiguous values
     * <p>
     * adding to a specified number in an array
     * <p>
     * Note: Displays success or failed results
     * <p>
     * Rules of the searching process:
     * <p> 
     * The search starts in the upper left corner, 
     * <p>
     * reporting each valid location found
     * <p>
     * The method must search to a current location's right,
     * <p>
     * then below it, and then to its left, exactly in that order
     * <p>
     * The method must report what it finds, 
     * <p>
     * either as a successful candidate value
     * <p>
     * or a failed candidate value (and why it failed)
     * <p>
     * The method must use recursive backtracking,
     * <p>
     * it must backtrack the recursion upon any failure,
     * <p>
     * but it must continue forward recursion upon any success
     * <p>
     * until the solution is found
     * <p>
     * The method must be able to handle the condition
     * <p>
     * that the value in the upper left corner does not support the solution
     * <p>
     * @param sumRequest integer value indicating desired sum
     * <p>
     * @return boolean indication of success
     */
    public boolean findSum( int sumRequest )
       {
        int column;
        CellDataClass cellData = new CellDataClass();
        String searchStart = "Search Start";
        String searchEnd = "Search End";
        String searchFailed = "Search Failed";
        displayStatus( 0, searchStart, cellData, 0 );
        for( column = 0; column < arrayRowWidth; column++)
        {
            if( findSumHelper( sumRequest, 0, 0, column, 0 ) )
            {
                displayStatus( 0, searchEnd, cellData, 0);
                return true;
            }
        }
        displayStatus( 0, searchFailed, cellData, 0 );
        return false;
       }
    
    /**
     * Helper method to find requested sum in an array
     * <p>
     * Note: Uses displayStatus method for all output
     * <p>
     * (no other printing from this method);
     * <p>
     * displayStatus provides appropriately indented current test location
     * <p>
     * @param sumRequest integer value indicating desired sum
     * <p>
     * @param runningTotal integer current sum of search process
     * <p>
     * @param xIndex integer current x position of search process
     * <p>
     * @param yIndex integer current y position of search process
     * <p>
     * @param recLevel integer current level of recursion, 
     * <p>
     * used to set display indent
     * <p>
     * @return boolean indication of success
     */
    private boolean findSumHelper( int sumRequest, int runningTotal, 
                                       int xIndex, int yIndex, int recLevel )
    {
        String locationFailed = "Location Failed";
        String validLocationFound = "Valid Location Found";
        String foundAt = "Found At";
        CellDataClass cellData = new CellDataClass( 0, xIndex, yIndex );
        if( sumRequest == runningTotal && yIndex == ( arrayRowHeight - 1 ) )
        {
           return true;
        }
        if ( verboseFlag )
        {
            if( isInBounds( xIndex, yIndex ) )
            {
                cellData =
                new CellDataClass( dataArray[ xIndex ][ yIndex ],
                                   xIndex, 
                                   yIndex );
                displayStatus( recLevel, validLocationFound, cellData, 0 );
            }
            else
            {
                displayStatus( recLevel, locationFailed, cellData, 0 );
                return false;
            }
            if( locationSet.hasItem( cellData ) )
            {
                displayStatus( recLevel, locationFailed, cellData, 0 );
                return false;
            }
            else if( runningTotal + dataArray[ xIndex ][ yIndex ] 
                                     > sumRequest )
                
            {
                displayStatus( recLevel, locationFailed, cellData, OVER_SUM );
                return false;
            }
            
            locationSet.addItem( cellData );
            
            if( findSumHelper( sumRequest,
                runningTotal + dataArray[ xIndex ][ yIndex ],
                               xIndex,
                               yIndex + 1, 
                               recLevel + 1 ) )
            {
                displayStatus( recLevel, foundAt, cellData, VALID_ITEM );
                return true;
            }
            else if( findSumHelper( sumRequest,
                     runningTotal + dataArray[ xIndex ][ yIndex ],
                                    xIndex + 1,
                                    yIndex,
                                    recLevel + 1 ) )
            {
                displayStatus( recLevel, foundAt, cellData, VALID_ITEM );
                return true;
            }
            else if( findSumHelper( sumRequest,
                     runningTotal +dataArray[ xIndex ][ yIndex ],
                                    xIndex,
                                    yIndex - 1,
                                    recLevel +1 ) )
            {
                displayStatus( recLevel, foundAt, cellData, VALID_ITEM );
                return true;
            }
            locationSet.removeLastItem();
            displayStatus( recLevel, locationFailed, cellData, INVALID_ITEM );
            return false;
            
        }
        
        //if verboseFlag is set to false
        else
        {
            if( isInBounds( xIndex, yIndex ) )
            {
                cellData = new CellDataClass( dataArray[ xIndex ][ yIndex ],
                                              xIndex, 
                                              yIndex );
            }
            else
            {
                return false;
            }
            if( locationSet.hasItem( cellData ) )
            {
                return false;
            }
            else if( runningTotal + dataArray[ xIndex ][ yIndex ] 
                                      > sumRequest )
                
            {
                return false;
            }
            
            locationSet.addItem( cellData );
            
            if( findSumHelper( sumRequest,
                runningTotal + dataArray[ xIndex ][ yIndex ],
                xIndex,
                yIndex + 1, 
                recLevel + 1 ) )
            {
                return true;
            }
            else if( findSumHelper( sumRequest,
                     runningTotal + dataArray[ xIndex ][ yIndex ],
                     xIndex + 1,
                     yIndex,
                     recLevel + 1 ) )
            {
                return true;
            }
            else if( findSumHelper( sumRequest,
                     runningTotal + dataArray[ xIndex ][ yIndex ],
                     xIndex,
                     yIndex - 1,
                     recLevel +1 ) )
            {
                return true;
            }
            locationSet.removeLastItem();
            return false;
            
        }
    }

    /**
     * checks that requested x, y indices of array
     * are not out of bounds
     * <p>
     * @param xLocTest integer x index to be tested
     * <p>
     * @param yLocTest integer y index to be tested
     * <p>
     * @return boolean result of specified test
     */
    private boolean isInBounds( int xLocTest, int yLocTest )
       {
        return xLocTest < arrayRowWidth && 
               xLocTest >= 0 &&
               yLocTest < arrayRowHeight &&
               yLocTest >= 0;
       }
    
    /**
     * displays status at indented level
     * <p>
     * Displays for 
     * <p>1) location not found, 
     * <p>2) item already in set,
     * <p>3) item causes over sum condition (opSuccess code OVER_SUM), 
     * <p>4) item has exhausted all child tests and 
     * <p>
     * does not support the solution (opSuccess code INVALID_ITEM),
     * <p>
     * <p>5) item accepted (opSuccess code VALID_ITEM), shows current location
     * <p>
     * @param recLevel integer specification of current level
     * <p>
     * @param status String indication of success or failure
     * <p>
     * @param opSuccess integer code indicating success or different failures
     * <p>
     * @param current CellDataClass data at current level
     */
    private void displayStatus( int recLevel, 
                     String status, CellDataClass current, int opSuccess )
    {
        String displayString = "";
        int numOfSpaces = REC_LEVEL_INDENT * recLevel;
        int iterator;
        for( iterator = 0; iterator <= numOfSpaces; iterator++ )
        {
            displayString += SPACE;
        }
        displayString += status + COLON + SPACE;
        if( opSuccess == OVER_SUM )
        {
            displayString += "Over Requested Sum";
        }
        else if( opSuccess == INVALID_ITEM )
        {
            displayString += "Doesn't Support Solution";
        }
        else if( opSuccess == VALID_ITEM )
        {
            displayString += current.toString();
        }
        else if( !isInBounds( current.xPos, current.yPos ) )
        {
            displayString += "Location Not Found";
        }
        else if( status == "Valid Location Found")
        {
            displayString += current.toString();
        }
        else if( status == "Search Start")
        {
            displayString += SPACE;
        }
        else if( status == "Search End" )
        {
            displayString += locationSet.toString();
        }
        else if( status == "Search Failed" )
        {
            displayString += "There Is No Solution";
        }
        else
        {
            displayString += "Already In Set";
        }
        System.out.println( displayString );
    }

    /**
     * uploads data from requested file
     * <p>
     * @param fileName name of file to access
     * <p>
     * @return Boolean result of data upload
     */
    public boolean uploadData( String fileName )
       {
        int rowIndex, colIndex;
        
        try
           {
            // Open FileReader 
            fileIn = new FileReader( fileName );
           
            // get leader line ahead of array height
            getALine( MAX_INPUT_CHARS, COLON );
           
            // get row height
            arrayRowHeight = getAnInt( MAX_INPUT_CHARS );
            
            // get leader line ahead of array width
            getALine( MAX_INPUT_CHARS, COLON );
           
            // get row width
            arrayRowWidth = getAnInt( MAX_INPUT_CHARS );
            
            dataArray = new int[ arrayRowHeight ][ arrayRowWidth ];
            
            for( rowIndex = 0; rowIndex < arrayRowHeight; rowIndex++ )
               {                
                for( colIndex = 0; colIndex < arrayRowWidth; colIndex++ )
                   {
                    dataArray[ rowIndex ][ colIndex ] 
                                               = getAnInt( MAX_INPUT_CHARS );
                   }
               }
           }
      
        catch( FileNotFoundException fnfe )
           {
            System.out.println( "DATA ACCESS ERROR:" );
            System.out.print( "Failure to open input file" );
          
            return false;
           }

        return true;
       }
   
   /**
    * gets a string up to a maximum length or to specified delimiter
    * <p>
    * @param maxLength maximum length of input line
    * <p>
    * @param delimiterChar delimiter character to stop input
    * <p>
    * @return String captured from file
    */
   private String getALine( int maxLength, char delimiterChar )
      {
       int inCharInt;
       int index = 0;
       String strBuffer = "";

       try
          {
           inCharInt = fileIn.read();

           // consume leading spaces
           while( index < maxLength && inCharInt <= (int)( SPACE )  )
              {
               if( inCharInt == EOF_MARKER )
                  {
                   return "EndOfFile";
                  }
               
               index++; 
               
               inCharInt = fileIn.read();
              }
           
           while( index < maxLength && inCharInt != (int)( delimiterChar ) )
              {   
               if( inCharInt >= (int)( SPACE ) )
                  {
                   strBuffer += (char)( inCharInt );

                   index++;
                 }
               
               inCharInt = fileIn.read();             
              }
           
           //inCharInt = fileIn.read();
          }
       
       catch( IOException ioe )
          {
           System.out.println( "INPUT ERROR: Failure to capture character" );
          
           strBuffer = "";
          }
          
       return strBuffer;
      }

    /**
     * gets an integer from the input string
     * <p>
     * @param maxLength maximum length of characters
     * <p>
     * input to capture the integer
     * <p>
     * @return integer captured from file
     */
    private int getAnInt( int maxLength )
       {
       int inCharInt;
       int index = 0;
       String strBuffer = "";
       int intValue;
       boolean negativeFlag = false;

       try
          {
           inCharInt = fileIn.read();

           // clear space up to number
           while( index < maxLength && 
                  !charInString( (char)inCharInt, "0123456789+-" ) )
              {
               inCharInt = fileIn.read();
               
               index++;
              }
           
           if( inCharInt == MINUS_SIGN )
              {
               negativeFlag = true;
               
               inCharInt = fileIn.read();
              }

           while( charInString( (char)inCharInt, "0123456789" ) )
              {   
               strBuffer += (char)( inCharInt );

               index++;
               
               inCharInt = fileIn.read();
              }            
          }
       
       catch( IOException ioe )
          {
           System.out.println( "INPUT ERROR: Failure to capture character" );
          
           strBuffer = "";
          }
          
       intValue = Integer.parseInt( strBuffer );
       
       if( negativeFlag )
          {
           intValue *= -1;
          }
       
       return intValue;
       }

    public void dumpArray()
       {
        int rowIndex, colIndex;
        
        System.out.println( "\nMatrixAnalysisClass Diagnostic Array Dump:" );
        
        for( rowIndex = 0; rowIndex < arrayRowHeight; rowIndex++ )
           {
            for( colIndex = 0; colIndex < arrayRowWidth; colIndex++ )
               {
                if( colIndex > 0 )
                   {
                    System.out.print( ", " );
                   }
                
                System.out.print( dataArray[ rowIndex ][ colIndex ] );
               }
            
            System.out.println();
           }
        
        System.out.println();
       }
    
    /**
     * tests and reports if a character is found in a given string
     * 
     * @param testChar character to be tested against the string
     * 
     * @param testString string within which the character is tested
     * 
     * @return Boolean result of test
     */
    private boolean charInString( char testChar, String testString )
       {
        int index;
       
        for( index = 0; index < testString.length(); index++ )
           {
            if( testChar == testString.charAt( index ) )
               {
                return true;
               }
           }
       
        return false;
       }
   

   }