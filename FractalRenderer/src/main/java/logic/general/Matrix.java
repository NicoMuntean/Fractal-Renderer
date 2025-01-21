package logic.general;

public class Matrix {
    
    private final int NUM_ROWS;
    private final int NUM_COLS;
    
    float[][] vals;
    
    /*
    * Object intialization methods 
    */
    
    /*
    * Initializes Matrix object based on a float input Array, the Array should always be of rectangular shape, if it is not, a failsafe is 
    * implemented to isnert zeros to get it to aforementioned shape.
    */
    public Matrix(float[][] inputVals) {
        
        vals = inputVals;
        
        for(int i = 0; i < vals.length - 1; i++) {
            
            if(vals[i + 1].length != vals[i].length) {
                
                System.out.println("ERROR: ALL MATRIX ROWS MUST HOLD THE SAME AMOUNT OF ELEMENTS! MISSING ELEMENTS WILL BE INSERTED AS \"0\" TO "
                        + "GET THE MATRIX OBJECT INTO A RECTANGULAR SHAPE!");
                vals = rectangularizeMatrix(vals);

            }
            
        }
        
        NUM_ROWS = vals.length;
        NUM_COLS = vals[0].length;
        
    }
    
    /*
    * Initializes a Matrix object filled with zeros with r rows and c columns.
    */
    public Matrix(int r, int c) {
        
        vals = new float[r][c];
        NUM_ROWS = r;
        NUM_COLS = c;
        
        for(int x = 0; x < r; x++) {
            for(int y = 0; y < c; y++) {
                vals[x][y] = 0;
            }
        }
    }
    
    /*
    * Getter-Setter Methods
    */
    
    public int getNumRows() {
        return NUM_ROWS;
    }
    
    public int getNumCols() {
        return NUM_COLS;
    }
    
    /*
    * Returns the Matrix element on the position (x,y), fails if x > NUM_ROWS or x < 0 or y > NUM_COLS or y < 0
    */
    public float getVal(int x, int y) {
        if(x < NUM_ROWS && y < NUM_COLS && x >= 0 && y >= 0) {
            
            return vals[x][y];
        }
        else {
            System.out.println("ERROR: INDEX TOO LARGE/SMALL! NO MATRIX ELEMENT FOUND! PROGRAM SHUTDOWN.");
            System.exit(0);
            return 0;
        } 
    }
    
    /*
    * Sets the Matrix element in the position (x,y) to a new value nVal.
    */
    public void setVal(int x,int y, float nVal) {
        if(x < NUM_ROWS && y < NUM_COLS && x >= 0 && y >= 0) {
            vals[x][y] = nVal;
        }
        else 
            System.out.println("ERROR: INDEX TOO LARGE/SMALL! NEW VALUE COULD NOT BE ASSIGNED TO MATRIX ELEMENT!");
    }
    
    /*
    * Returns the x-th row of the Matrix object as a float Array
    */
    public float[] getRow(int x) {
        if(x < NUM_COLS && x >= 0)
            return vals[x];
        else {
            System.out.println("ERROR: INDEX TOO LARGE/SMALL! NO MATRIX ROW FOUND! PROGRAM SHUTDOWN.");
            System.exit(0);
            return null;
        }
    }
    
    /*
    * Returns the i-th row of the Matrix object as a float Array
    */
    public float[] getCol(int i) {
        if(i < NUM_ROWS && i >= 0) {
            
            float[] outCol = new float[NUM_ROWS];
            
            for(int x = 0; x < NUM_ROWS; x++) {
                outCol[x] = vals[x][i];
            }
            return outCol;
        }
        else {
            System.out.println("ERROR: INDEX TOO LARGE/SMALL! NO MATRIX COLUMN FOUND! PROGRAM SHUTDOWN.");
            System.exit(0);
            return null;
        }
    }
    
    /*
    * Returns the Matrix object as a 2D float Array
    */
    public float[][] toFloatArray() {
        return vals;
    }
    
    /*
    * Object methods
    */
    
    /*
    * Takes a 2D-float Array with differing row lengths and rectangualizes it by appending zeros to bring all rows to the same length.
    * This method should only be used in the initialization prcocess of the matrix, as all provided math methods only work fpr revtangular 
    * matrices.
    */
    private float[][] rectangularizeMatrix(float[][] unrectMatrix) {
        
        int maxLength = 0;
        int cols;
        int rows;
        float[][] output;
        
        for (float[] row : unrectMatrix) {
            if (maxLength < row.length) { 
                maxLength = row.length;
            }
        }
        
        cols = unrectMatrix.length;
        rows = maxLength;
        output = new float[cols][rows];
        
        for(int x = 0; x < cols; x++) {
            for(int y = 0; y < rows; y++) {
                
                if(y < unrectMatrix[x].length)
                    output[x][y] = unrectMatrix[x][y];
                else
                    output[x][y] = 0;
          
            }
        }
        
        return output;
    }
    
    /*
    * Prints a Easy readable visualization of the Matrix objects elements into the console
    */
    public void printMatrix() {
        System.out.println();
        for (float[] val : vals) {
            System.out.print("|");
            for (int y = 0; y < vals[0].length; y++) {
                System.out.print(val[y] + " ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println();
    }

    
}
