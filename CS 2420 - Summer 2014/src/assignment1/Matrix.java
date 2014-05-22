package assignment1;

public class Matrix {
    int numRows;
    int numColumns;
    int data[][];

    // Constructor with data for new matrix (automatically determines dimensions)
    public Matrix(int d[][]) {
        numRows = d.length; // d.length is the number of 1D arrays in the 2D array
        if (numRows == 0)
            numColumns = 0;
        else
            numColumns = d[0].length; // d[0] is the first 1D array
        data = new int[numRows][numColumns]; // create a new matrix to hold the data
        // copy the data over
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numColumns; j++)
                data[i][j] = d[i][j];
    }

    @Override
    // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
    public boolean equals(Object o) {
        if (!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
            return false;
        Matrix m = (Matrix) o; // if the above was not true, we know it's safe to treat 'o' as a Matrix

        // check if the matrix dimensions are equal, return false if not
        if (this.numRows != m.numRows || this.numColumns != m.numColumns)
            return false;

        // iterate through matrix elements, return false if they are inequal
        for (int thisRow = 0; thisRow < this.numRows; thisRow++)
            for (int thisColumn = 0; thisColumn < this.numColumns; thisColumn++)
                if (this.data[thisRow][thisColumn] != m.data[thisRow][thisColumn])
                    return false;

        return true; // if none of the tests returned false then the matrices must be equal
    }

    @Override
    // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
    public String toString() {
        String result = "";

        // Loop through elements in matrix
        for (int row = 0; row < numRows; row++)
            for (int column = 0; column < numColumns; column++) {
                if (column == this.numColumns - 1)
                    result += this.data[row][column] + " \n";
                else
                    result += this.data[row][column] + " ";    // Add elements to result string with spaces between
            }
        return result;
    }

    public Matrix times(Matrix m) {
        // Check for compatible dimensions
        if (this.numColumns != m.numRows) {
            System.out.println("Error: matrix arguments are not compatible for multiplication!");
            return null;
        }

        // Multiply matrixes
        Matrix outputMatrix = new Matrix(new int[this.numRows][m.numColumns]);

        // iterate through rows of 'this' and columns of 'm'
        for (int thisRow = 0; thisRow < this.numRows; thisRow++)
            for (int mColumn = 0; mColumn < m.numColumns; mColumn++) {
                // initialize sum, which will be each value of the outputMatrix in turn
                int sum = 0;

                // do the actual matrix multiplication
                for (int multiplierIndex = 0; multiplierIndex < this.numColumns; multiplierIndex++)
                    sum += this.data[thisRow][multiplierIndex] * m.data[multiplierIndex][mColumn];

                // set the correct element in the outputMatrix as the sum
                outputMatrix.data[thisRow][mColumn] = sum;
            }

        // return the finalized matrix
        return outputMatrix;
    }

    public Matrix plus(Matrix m) {
        // check for compatible dimensions, if incompatible return null
        if (this.numRows != m.numRows || this.numColumns != m.numColumns) {
            System.out.println("Error: matrix arguments are not compatible for addition!");
            return null;
        }

        // initialize output matrix
        Matrix outputMatrix = new Matrix(new int[this.numRows][this.numColumns]);

        // iterate through the input matrixes and store the added elements in the output matrix
        for (int row = 0; row < this.numRows; row++)
            for (int column = 0; column < this.numColumns; column++)
                outputMatrix.data[row][column] = this.data[row][column] + m.data[row][column];

        return outputMatrix;
    }
}