/**
 * This file is the starting point for homework assignment02.
 */

package homework02;

/**
 * Objects of this class represent a matrix (from mathematics).  Matrix
 * elements are kept as long integer values.
 * 
 * @author Cody Cortello - u0781604.
 * @version 1/17/14.
 */
public class Matrix
{
    // Fields - use these instance variables in your solution
    private int numRows;
    private int numCols;
    private long elements[][];

    /**
     *  Creates an empty matrix of the specified size.  All entries are
     *  set to 0.
     *  
     *  @param   rows   the number of rows to be in this matrix
     *  @param   cols   the number of columns to be in this matrix
     *  @throws  IllegalMatrixSizeException if rows or cols are non-positive
     */ 
    public Matrix (int rows, int cols)
    {
    	numRows=rows;
    	numCols=cols;
        elements=new long[rows][cols];
    }
    
    /**
     *  Creates this matrix from an existing 2D array of integer data.
     *  The contents of the array are copied into this matrix (so that
     *  the caller can change the input array without changing this matrix).
     *  
     *  The size of this matrix will match the size of the input data, so the
     *  input data array dimensions must be non-zero.
     *  
     *  @param   d   a 2D array of integer data to place in this matrix
     *  @throws  IllegalMatrixSizeException if the input array is empty or non-rectangular
     */  
    public Matrix (int data[][])
    {
        // Extract the size from the array, make sure it is rectangular.
        
        numRows = data.length; // d.length is the number of 1D arrays in the 2D array, the length of the first dimension
        if (numRows == 0)
            throw new IllegalMatrixSizeException();
        
        for (int row = 0; row < numRows; row++)  // Check to see all rows are the same width
            if (data[row].length != data[0].length)
                throw new IllegalMatrixSizeException();

        numCols = data[0].length; // d[0] is the first 1D array, the first row
        
        // initialize created Matrix
        elements=new long[numRows][numCols];
        
        // Copy the values from the input array
        for (int row=0; row<numRows; row++)
        	for (int column=0; column<numCols; column++)
        		this.setElement(row, column, data[row][column]);;
    }
    
    /**
     * Returns the number of columns in this matrix.
     * 
     * @return  the number of columns in this matrix
     */
    public int getWidth ()
    {
    	return numCols;
    }
    
    /**
     * Returns the number of rows in this matrix.
     * 
     * @return  the number of rows in this matrix
     */
    public int getHeight ()
    {
        return numRows;
    }
    
    /**
     * Changes one location in this matrix to the specified value.
     * 
     * @param row the row index (0 based)
     * @param col the column index (0 based)
     * @param value  the new value for this element in the matrix
     */
    public void setElement (int row, int col, long value)
    {
    	elements[row][col] = value;
    }
    
    /**
     * Returns the value from one location in this matrix.
     * 
     * @param row the row index (0 based)
     * @param col the column index (0 based)
     * @return the value of the specified element
     */
    public long getElement (int row, int col)
    {    	
        return elements[row][col];
    }
    
    /**
     * Returns a multi-line string representing the contents of this matrix.
     * If printed, the returned string will resemble the mathematical diagram
     * of a matrix.  There will be single spaces between values and newline 
     * characters at the end of every row.  The returned string will only 
     * contain numbers, single spaces, and newline characters ('\n').
     * 
     * If the matrix contains {{1, 2, 3},{4, 5, 6},{7, 8, 9}}, this method would
     * return:
     *   "1 2 3\n4 5 6\n7 8 9\n"
     *   
     * @return a printable string representing this matrix
     */
    @Override
    public String toString ()
    {
        String result="";
        
        // Loop through elements in matrix
        for (int row=0; row<numRows; row++)
        {
        	for (int column=0; column<numCols; column++)
        	{
        		if (column==this.getWidth()-1)
        			result += this.getElement(row,column) + "\n";
        		else
        			result += this.getElement(row,column) + " ";	// Add elements to result string with spaces between
        	}
        }
        return result;
    }
    
    /**
     * Returns the matrix product between this matrix and matrix m,
     * or null if the matrix dimensions are incompatible.  Both 'this'
     * matrix and matrix m are unchanged, a new Matrix is created and
     * returned to contain the product.
     * 
     * For the multiplication, 'this' matrix is the left matrix and
     * matrix m is the right matrix.
     * 
     * @param  m  a matrix for the righthand side of matrix multiplication 
     * @return the product (this * m)
     */
    public Matrix multiply (Matrix m)
    {
    	// Check for compatible dimensions
    	if(this.getWidth() != m.getHeight())
    		return null;
    	
    	// Multiply matrixes
    	Matrix outputMatrix=new Matrix(this.getHeight(),m.getWidth());
    	
    	// iterate through rows of 'this' and columns of 'm'
    	for (int thisRow=0; thisRow<this.getHeight(); thisRow++)
    		for (int mColumn=0; mColumn<m.getWidth(); mColumn++)
    		{
    			// initialize sum, which will be each value of the outputMatrix in turn
    			long sum=0;
    			
    			// do the actual matrix multiplication
    			for (int multiplierIndex=0; multiplierIndex<this.getWidth(); multiplierIndex++)
    				sum += this.getElement(thisRow, multiplierIndex) * m.getElement(multiplierIndex, mColumn);
    			
    			// set the correct element in the outputMatrix as the sum
    			outputMatrix.setElement(thisRow, mColumn, sum);
    		}
    	
    	// return the finalized matrix
        return outputMatrix;
    }
    
    /**
     * Returns the matrix sum between this matrix and matrix m,
     * or null if the matrix dimensions are incompatible.  Both 'this'
     * matrix and matrix m are unchanged, a new Matrix is created and
     * returned to contain the sum.
     * 
     * For the addition, 'this' matrix is the left matrix and
     * matrix m is the right matrix.
     * 
     * @param m  a matrix for the righthand side of matrix addition 
     * @return  the sum (this + m)
     */
    public Matrix add (Matrix m)
    {
    	// check for compatible dimensions, if incompatible return null
        if (this.getHeight() != m.getHeight() || this.getWidth() != m.getWidth())
        	return null;
        
        // initialize output matrix
        Matrix outputMatrix = new Matrix(this.getHeight(), this.getWidth());        
        
        // iterate through the input matrixes and store the added elements in the output matrix
        for (int row=0; row<this.getHeight(); row++)
        	for (int column=0; column<this.getWidth(); column++)
        		outputMatrix.setElement(row, column, this.getElement(row,column) + m.getElement(row,column));
        
        return outputMatrix;
    }
    
    /**
     * Same as add, but iterates through columns and rows instead of rows and columns
     * 
     * @param m  a matrix for the righthand side of matrix addition 
     * @return  the sum (this + m)
     */
    public Matrix add2 (Matrix m)
    {
    	// check for compatible dimensions, if incompatible return null
        if (this.getHeight() != m.getHeight() || this.getWidth() != m.getWidth())
        	return null;
        
        // initialize output matrix
        Matrix outputMatrix = new Matrix(this.getHeight(), this.getWidth());        
        
        // iterate through the input matrixes and store the added elements in the output matrix
        for (int column=0; column<this.getWidth(); column++)
        	for (int row=0; row<this.getHeight(); row++)
        		outputMatrix.setElement(column, row, this.getElement(column,row) + m.getElement(column,row));
        
        return outputMatrix;
    }
    
    /**
     * Returns true if Object o is a matrix and o is equal to this
     * matrix, and false otherwise.  Two matrices are equal if they
     * have the same dimensions and the the same values.
     * 
     * @return true if this matrix is equivalent to matrix o
     */    
    @Override
    public boolean equals (Object o)
    {
        if (!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
            return false;
        Matrix m = (Matrix) o; // if the above was not true, we know it's safe to treat 'o' as a Matrix

        // check if the matrix dimensions are equal, return false if not
        if (this.getHeight() != m.getHeight() || this.getWidth() != m.getWidth())
        	return false;
        
        // iterate through matrix elements, return false if they are inequal
        for (int thisRow=0; thisRow<this.getHeight(); thisRow++)
        	for (int thisColumn=0; thisColumn<this.getWidth(); thisColumn++)
        		if (this.getElement(thisRow, thisColumn) != m.getElement(thisRow, thisColumn))
        			return false;

        return true; // if none of the tests returned false then the matrixes must be equal
    }
}

/**
 * This exception type is used when a matrix is created with illegal dimensions.
 * 
 * @author Peter Jensen
 * @Version January 11, 2014
 */
class IllegalMatrixSizeException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
}
