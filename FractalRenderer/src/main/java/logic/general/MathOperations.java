package logic.general;

import logic.objects.RenderableObject;

public class MathOperations {
    
    public MathOperations() {
        
        
    }
    
    //###########################################//
    //############## TRIGONOMETRY ###############//
    //###########################################//
    
    public float sin(float arg) {
        
        return (float)(Math.sin(Math.toRadians(arg)));
    }
    
    public float cos(float arg) {
        
        return (float)(Math.cos(Math.toRadians(arg)));
    }
    
    //###########################################//
    //############## RAY MARHCING ###############//
    //###########################################//
    
    
    /*
    * Calculates the greatest value in the input 2D float Array disArr
    *
    * TODO: RELOCATE THIS METHOD IN A MATH CLASS!
    *
    */
    public float maxDis(float[][] disArr) {
        
        float max = 0;
        
        for (float[] disArr1 : disArr) {
            for (int y = 0; y < disArr[0].length; y++) {
                if (max < disArr1[y]) {
                    max = disArr1[y];
                }
            }
        }
        System.out.println(max);
        return max;
    }
    
    /*
    * Calculates the smallest value in the input 2D float Array disArr 
    *
    * TODO: RELOCATE THIS METHOD IN A MATH CLASS!
    *
    */
    public float minDis(float[][] disArr) {
        
        float min = 999;
        
        for (float[] disArr1 : disArr) {
            for (int y = 0; y < disArr[0].length; y++) {
                if (min > disArr1[y] && disArr1[y] != 0) {
                    min = disArr1[y];
                }
            }
        }
        System.out.println(min);
        return min;
        
    }

    
    //###########################################//
    //############## VECTOR LOGIC ###############//
    //###########################################//
    
    
    //################ ADDITION #################//
    
    /*
    * Vector Addition of two Vector Objects a and b. IMPORTANT: a and b must possess the same dimension!
    * returns a Vector object: out = sum1 + sum2
    */
    public Vector addV(Vector sum1, Vector sum2) {
        
        int d_a = sum1.getDim();
        int d_b = sum2.getDim();
        
        if(d_a == d_b) {
            
            float[] out = new float[d_a];
            
            for(int x = 0; x < d_a; x++) {
                out[x] = sum1.getComponent(x) + sum2.getComponent(x);
            }
            return new Vector(out);
        }
        else {
            System.out.println("OPERATION \"addV \" FAILED! THE PROVIDED VECTOR OBJECTS MUST POSSESS THE SAME DIMENSION!");
            return null;
        } 
    }
    
    /*
    * Vector Addition of n Vector Objects. IMPORTANT: All Vectors must possess the same dimension!
    * returns a Vector object: out = summand[0] + summand[1] + ... + summand[n]
    */
    public Vector addV(Vector[] summands) {
        
        int len = summands.length;
        
        for(int i = 0; i < len - 1; i++) {
            if(summands[i].getDim() != summands[i+1].getDim()) {
                System.out.println("OPERATION \"addMultiV \" FAILED! THE PROVIDED VECTOR OBJECTS MUST POSSESS THE SAME DIMENSION!");
                return null;
            }
        } 
        
        int d = summands[0].getDim();
        
        float[] out = summands[0].getAllComponents();
        
        for(int x = 1; x < d; x++) {
            for(int i = 0; i < len; i++) {
                out[x] += summands[i].getComponent(x);
            }
        }
        
        return new Vector(out);
    }
    
    //############### SUBTRACTION ###############//
    
    /*
    * Subtracts a vector "subtrahend" from a vector "minuend", both vectors must possess the same dimension!
    * returns a vector object: out = minuend - subtrahend
    */
    public Vector subV(Vector minuend, Vector subtrahend) {
        
        int d_a = minuend.getDim();
        int d_b = subtrahend.getDim();
        
        if(d_a == d_b) {
            
            float[] out = new float[d_a];
            
            for(int x = 0; x < d_a; x++) {
                out[x] = minuend.getComponent(x) - subtrahend.getComponent(x);
            }
            return new Vector(out);
        }
        else {
            System.out.println("OPERATION \"subV \" FAILED! THE PROVIDED VECTOR OBJECTS MUST POSSESS THE SAME DIMENSION!");
            return null;
        }
    }
    
    /*
    * Subtracts n vectors "subtrahends" from a vector "minuend", all vectors must possess the same dimension!
    * returns a vector object: out = minuend - subtrahend[0] - subtrahend[1] - ... - subtrahend[n]
    */
    public Vector subV(Vector minuend, Vector[] subtrahends) {
        
        int len = subtrahends.length;
        
        for(int i = 0; i < len - 1; i++) {
            if(subtrahends[i].getDim() != subtrahends[i+1].getDim() || minuend.getDim() != subtrahends[i].getDim()) {
                System.out.println("OPERATION \"subV \" FAILED! THE PROVIDED VECTOR OBJECTS MUST POSSESS THE SAME DIMENSION!");
                return null;
            }
        } 
        
        int d = minuend.getDim();
        float[] out = minuend.getAllComponents();
        
        for(int x = 0; x < d; x++) {
            for(int i = 0; i < len; i++) {
                
                out[x] -= subtrahends[i].getComponent(x);
                
            }
        }
        
        return new Vector(out);
    }
    
    //############# MULTIPLICATION ##############//
    
    /*
    * Scalar Product of a Vector object a and a scalar s.
    * Return a Vector object: out = s * a
    */
    public Vector scalV(Vector a,float s) {
        
        int d = a.getDim();
        float[] out = new float[d];
        
        for(int x = 0; x < d; x++) {
            
            out[x] = s*a.getComponent(x);
        }
        
        return new Vector(out);
    }
    
    /*
    * Dot product of two Vector objects a and b. Both vector Objects must possess the same dimension!
    * Return a float value: out = a[0] * b[0] + a[1] * b[1] + ... + a[n] * b[n]
    */
    public float dotV(Vector a, Vector b) {
        
        int d_a = a.getDim();
        int d_b = b.getDim();
        
        if(d_a == d_b) {
            
            float out = 0;
            
            for(int x = 0; x < d_a; x++) {
                out += a.getComponent(x)*b.getComponent(x);
            }
            return out;
        }
        else {
            System.out.println("OPERATION \"dotP \" FAILED! THE PROVIDED VECTOR OBJECTS MUST POSSESS THE SAME DIMENSION!");
            return 0;
        }
        
    }
    
    /*
    * Vector product of two Vector objects a and b. As of now, both Vector objects must possess a dimension of dim= 3!
    * return a vector perpendicular to a and b: out = a x b
    */
    public Vector vecP(Vector a, Vector b) {
        
        int d_a = a.getDim();
        int d_b = b.getDim();
        
        if(d_a == d_b && d_a == 3) {
            
            float[] out = new float[3];
            
            out[0] = a.getComponent(1)*b.getComponent(2) - a.getComponent(2)*b.getComponent(1);
            out[1] = a.getComponent(2)*b.getComponent(0) - a.getComponent(0)*b.getComponent(2);
            out[2] = a.getComponent(0)*b.getComponent(1) - a.getComponent(1)*b.getComponent(0);
            
            return new Vector(out);
        }
        else {
            System.out.println("ERROR: THE OPERATION \"vecProduct\" IS, AS OF NOW, ONLY DEFINED FOR DIM=3 VECTORS! BOTH VECTORS MUST BE OF DIM=3!");
            return null;
        }
    }
    
    //################## OTHER ##################//
    
    /*
    * Returns the normed version of the input Vector a.
    * Returns a Vector object.
    */
    public Vector normV(Vector a) {
        
        int d = a.getDim();
        
        double divid = 0;
        
        for(int x = 0; x < d; x++) {
            divid += a.getComponent(x)*a.getComponent(x);
        }
        
        divid = Math.sqrt(divid);
        float[] out = new float[d];
        
        for(int x = 0; x < d; x++) {
            out[x] = (float)(a.getComponent(x)/divid);
        }
        
        return new Vector(out);
    }
    
    /*
    * Tests if two Vector objects a and b are orthogonal. They are taken to be orthogonal if their dot product is smaller than the input eps.
    * Returns a boolean value:
    *
    * dotV(a,b) < eps -> true
    * dotV(a,b) > eps -> false
    */
    public boolean testOrthog(Vector a, Vector b,float eps) {
        
        return dotV(a,b) < eps;
    }
    
    /*
    * Returns the length of a vector object a.
    Returns a float value
    */
    public float lenV(Vector a) {
        
        int d = a.getDim();
        
        float out = 0;
        
        for(int x = 0; x < d; x++) {
            out += a.getComponent(x)*a.getComponent(x);
        }
        
        return (float)(Math.sqrt(out));
    }
    
    /*
    * Returns the angle tow Vector objects a and b enclose.
    * returns a float value for the angle in degrees.
    */
    public float angleV(Vector a, Vector b) {
        
        int d_a = a.getDim();
        int d_b = b.getDim();
        
        if(d_a == d_b) {
            
            Vector n_a = normV(a);
            Vector n_b = normV(b);
            
            double sinTheta = dotV(n_a,n_b);

            return (float)(Math.toDegrees(Math.acos(sinTheta)));
        }
        else {
            System.out.println("OPERATION \"angleV \" FAILED! THE PROVIDED VECTOR OBJECTS MUST POSSESS THE SAME DIMENSION!");
            return -9999;
        } 
    }
    
    //############################################//
    //############## FRACTAL LOGIC ###############//
    //############################################//
    
    /*
    * Calculates the numerical gradient of an arbitrary function based on two points taken at f(x1,x2,x3,...,xn) and f(x1+h,x2+h,x3+h,...,xn+h).
    * IMPORTANT: the pos1 Vector objects is treated as the point taken at f(x1,x2,...,xn) while the pos2 Vector object is treated as the point
    * taken at f(x1+h, x2 +h, ... , xn + h)!
    * Returns a Vector object
    */
    public Vector numericGrad(Vector pos1, Vector pos2, float h) {
        
        int d1 = pos1.getDim();
        int d2 = pos2.getDim();
        
        if(d1 == d2) {
            
            float[] out = new float[d1];
            
            for(int x = 0; x < d1; x++) {
                
                out[x] = (pos2.getComponent(x) - pos1.getComponent(x))/(h);
                
            }
            
            return new Vector(out);
        }
        else {
            System.out.println("OPERATION \"numericGrad \" FAILED! THE PROVIDED VECTOR OBJECTS MUST POSSESS THE SAME DIMENSION!");
            return null;
        }
        
    }
    
    //###########################################//
    //############## MATRIX LOGIC ###############//
    //###########################################//
    
    //################ ADDITION #################//
    
    /*
    * Performs a Matrix addition with two Matrix ojects sum1 and sum2.
    * Returns a Matrix object: out = sum1 + sum2
    */
    public Matrix addM(Matrix sum1, Matrix sum2) {
        
        Matrix out;
        
        int r1 = sum1.getNumRows();
        int c1 = sum1.getNumCols();
        int r2 = sum2.getNumRows();
        int c2 = sum2.getNumCols();
        
        if(r1 != r2 || c1 != c2) {
            System.out.println("ERROR: MATRIX OBJECTS ARE NOT OF THE SAME SIZE! \"addM\" FAILED!");
            return null;
        }
        else {
            
            out = new Matrix(r1,c1);
            
            for(int x = 0; x < r1; x++) {
                for(int y = 0; y < c1; y++) {
                    out.setVal(x, y, sum1.getVal(x, y) + sum2.getVal(x, y));
                }
            }
            return out;
        } 
    }
    
    /*
    * Performs a Matrix addition with n Matrix objects summands[0], summands[1], ... , summands[n]
    * Returns a Matrix object: out = summands[0] + summands[1] + ... + summands[n]
    */
    public Matrix addM(Matrix[] summands) {
        
        Matrix out;
        int len = summands.length;
        int r;
        int c;
        
        for(int i = 0; i < len - 1; i++) {
            
            if(summands[i].getNumRows() != summands[i+1].getNumRows() || 
                    summands[i].getNumCols() != summands[i+1].getNumCols()) {
                System.out.println("ERROR: MATRIX OBJECTS ARE NOT OF THE SAME SIZE! \"addM\" FAILED!");
                return null;
            }   
        }
        
        c = summands[0].getNumCols();
        r = summands[0].getNumRows();
        out = new Matrix(r,c);
        
        for(int i = 0; i < len; i++) {
            out = addM(out,summands[i]);
        }
        
        return out;
    }
    
    //############### SUBTRACTION ###############//
    
    /*
    * Subtracts a Matrix "subtrahend" from a Matrix "minuend", both Matricies must possess the same dimension!
    * returns a Matrix object: out = minuend - subtrahend
    */
    public Matrix subM(Matrix minuend, Matrix subtrahend) {
        
        Matrix out;
        
        int r1 = minuend.getNumRows();
        int c1 = minuend.getNumCols();
        int r2 = subtrahend.getNumRows();
        int c2 = subtrahend.getNumCols();
        
        if(r1 != r2 || c1 != c2) {
            System.out.println("ERROR: MATRIX OBJECTS ARE NOT OF THE SAME SIZE! \"subM\" FAILED!");
            return null;
        }
        else {
            
            out = new Matrix(r1,c1);
            
            for(int x = 0; x < r1; x++) {
                for(int y = 0; y < c1; y++) {
                    out.setVal(x, y, minuend.getVal(x, y) - subtrahend.getVal(x, y));
                }
            }
            return out;
        } 
    }
    
    /*
    * Subtracts n Matricies "subtrahends" from a Matrix "minuend", all Matricies must possess the same dimension!
    * returns a Matrix object: out = minuend - subtrahend[0] - subtrahend[1] - ... - subtrahend[n]
    */
    public Matrix subM(Matrix minuend, Matrix[] subtrahends) {
        
        Matrix out;
        int len = subtrahends.length;
        int r;
        int c;
        
        for(int i = 0; i < len - 1; i++) {
            
            if(subtrahends[i].getNumRows() != subtrahends[i+1].getNumRows() || 
                    subtrahends[i].getNumCols() != subtrahends[i+1].getNumCols()) {
                System.out.println("ERROR: MATRIX OBJECTS ARE NOT OF THE SAME SIZE! \"subM\" FAILED!");
                return null;
            }   
        }
        
        c = subtrahends[0].getNumCols();
        r = subtrahends[0].getNumRows();
        out = minuend;
        
        for(int i = 0; i < len; i++) {
            out = subM(out,subtrahends[i]);
        }
        
        return out;
    }
    
    //############# MULTIPLICATION ##############//
    
    /*
    * Multiplicates a Matrix object a with a scalar s.
    * Returns a Matrix object: out = s*a
    */
    public Matrix scalM(Matrix a, int s) {
        
        int r = a.getNumRows();
        int c = a.getNumCols();
        Matrix out = new Matrix(r,c);
        
        for(int x = 0; x < r; x++) {
            for(int y = 0; y < c; y++) {
                out.setVal(x, y, s*a.getVal(x, y));
            }
        }
        return out;
    }
    /*
    * performs a Matrix Multiplication with the matricies a and b.
    * Returns a Matrix object: out = a*b
    */
    public Matrix dotM(Matrix a, Matrix b) {
        
        Matrix out;
        int c1 = a.getNumCols();
        int r1 = a.getNumRows();
        int c2 = b.getNumCols();
        int r2 = b.getNumRows();
        
        if(c1 != r2) {
            System.out.println("ERROR: NUMBER OF COLUMNS OF MATRIX A MUST MATCH NUMBER OF ROWS OF MATRIX B! \"dotM\" FAILED!");
            return null;
        }
        
        out = new Matrix(r1,c2);
        
        for(int x = 0; x < r1; x++) {
            for(int y = 0; y < c2; y++) {
                for(int k = 0; k < c1; k++) {
                    
                    out.setVal(x, y, out.getVal(x, y) + a.getVal(x, k) * b.getVal(k, y));
                    
                }
            }
        }
        
        return out;
    }
    
    /*
    * Performs a Matrix-Vector multiplication with a Matrix object m and a Vector object v.
    * Returns a Vector object: out = m*v
    */
    public Vector mDotV(Matrix m, Vector v) {
        
        Vector out;
        
        int n = v.getDim();
        int r = m.getNumRows();
        int c = m.getNumCols();
        
        if(c != n) {
            System.out.println("ERROR: NUMBER OF MATRIX COLUMNS MUST MATCH VECTOR DIMENSION! \"mDotV\" FAILED!");
            return null;
        }
        
        out = new Vector(r);
        
        for(int x = 0; x < r; x++) {
            for(int k = 0; k < n; k++) {
                
                out.setComp(out.getComponent(x) + m.getVal(x,k)*v.getComponent(k), x);
                
            }
        }
        
        return out;
    }
    
    /*
    * Performs a Vector-Matrix multiplication with a Vector object v and a Matrix object m.
    * Returns a Matrix onbject: out = v*m
    */
    public Matrix vDotM(Vector v, Matrix m) {
        
        Matrix out;
        
        int n = v.getDim();
        int c = m.getNumCols();
        int r = m.getNumRows();
        
        if(n != r) {
            System.out.println("ERROR: NUMBER OF MATRIX ROWS MUST MATCH VECTOR DIMENSION! \"vDotM\" FAILED.");
            return null;
        }
        
        out = new Matrix(1,c);
        
        for(int x = 0; x < c; x++) {
            for(int k = 0; k < n; k++) {
                out.setVal(0, x, out.getVal(0, x) + v.getComponent(k)*m.getVal(k, x));
            }
        }
        
        return out;
    }
    
    
    //################## OTHER ##################//
    
    /*
    * Transposes the input Matrix m.
    * Returns a Matrix object: out = m^T
    */
    public Matrix transpM(Matrix m) {
        
        int c = m.getNumCols();
        int r = m.getNumRows();
        Matrix out = new Matrix(c,r);
        
        for(int x = 0; x < c; x++) {
            for(int y = 0; y < r; y++) {
                
                out.setVal(x, y, m.getVal(y, x));
                
            }
        }
        
        return out;
    }
    
    /*
    * Calculates the fast inverse square root for double precision floating point numbers
    */
    
    public double invSqrt(double x) {
        double xhalf = 0.5d * x;
        long i = Double.doubleToLongBits(x);
        i = 0x5fe6ec85e7de30daL - (i >> 1);
        x *= (1.5d - xhalf * x * x);
        return x;
    }
    
    
}
