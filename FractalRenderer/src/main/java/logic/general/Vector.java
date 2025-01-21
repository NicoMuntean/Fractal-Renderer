package logic.general;

public class Vector {
    
    private int dim;
    private float[] vals;
    
    /*
    * Creates an Vector object with the dimension of the length of the input array inputVals, the vector object will hold
    * the data provided in inputVals.
    */
    public Vector(float[] inputVals) {
        
        vals = inputVals;
        dim = vals.length;
        
    }
    
    /*
    * Creates a 3D-Vector-object, x,y and z components have to be provided
    */
    public Vector(float x, float y, float z) {
        
        vals = new float[] {x,y,z};
        dim = 3;
    }
    
    /*
    * Creates a zero-vector of dimension vecDim
    */
    public Vector(int vecDim) {
        
        dim = vecDim;
        vals = new float[dim];
        
        for(int x = 0; x < dim; x++) {
            vals[x] = 0;
        }
    }

    
    /*
    * Returns the dimension of the Vector.
    * The output is an Integer.
    */
    public int getDim() {
        return dim;
    }
    
    /*
    * Returns the Vector Component with the index i.
    * The output is a float.
    */
    public float getComponent(int i) {
        if(i < dim || i >= 0)
            return vals[i];
        else{
            System.out.println("ERROR: REQUESTED COMPONENT DIMENSION IS LARGER THAN THE VECTOR OBJECTS DIMENSION!");
            return -9999;
        }    
    }
    
    /*
    * Returns all Vector Components as a float array possessing the length equal to the vector dimension.
    * The output is a float array.
    */
    public float[] getAllComponents() {
        return vals;
    }
    
    /*
    * Sets a specific component of the vector object to a new value
    */
    public void setComp(float newComp, int i) {
        if(i < dim || i >= 0)
            vals[i] = newComp;
        else{
            System.out.println("ERROR: REQUESTED COMPONENT DIMENSION IS LARGER THAN THE VECTOR OBJECTS DIMENSION!");
        }   
    }
    
    /*
    * Sets the all Vector object Components to new values nComp, can also alter the Vector objects dimension
    */
    public void setAllComp(float[] nComp) {
        vals = nComp;
        dim = vals.length;
    }
    
    /*
    * Prints the Vector object into the console
    */
    public void printVector() {
        
        for(int x = 0; x < dim; x++) {
            System.out.print("|" + vals[x] + "|");
            System.out.println();
        }
    }
    
    /*
    * Returns a copy of the Vector object
    */
    public Vector copy() {
        return new Vector(vals.clone());
    }
}
