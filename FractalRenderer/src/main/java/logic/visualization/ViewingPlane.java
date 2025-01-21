package logic.visualization;

import logic.general.Vector;
import logic.visualization.OrientationOperations;

public class ViewingPlane {
    
    private Vector[] constraints = new Vector[4];
    
    private int planeWidth;
    private int planeHeight;
    
    private float realWidth;
    private float realHeight;
    
    private float dist;
    private float yaw;
    private float pitch;
    private float roll;
    
    private OrientationOperations oo = new OrientationOperations();
    
    /*
    * Object initialization methods + overloads
    */
    
    @Deprecated
    public ViewingPlane(Vector[] init) throws Exception {
        
        if(init.length != 4) {
            throw new Exception("ERROR: INPUT VECTOR ARRAY HAS TO HAVE A DIMENSION OF 4!");
        }
        else {
            
            constraints = init;
        }
    }
    
    @Deprecated
    public ViewingPlane(float[][] init) throws Exception {
        
        if(init.length != 4) {
            throw new Exception("ERROR: INPUT VECTOR ARRAY HAS TO HAVE A DIMENSION OF 4!");
        }
        else {
            
            for(int x = 0; x < init.length; x++) {
                constraints[x] = new Vector(init[x]);
            } 
        }
        
    }
    
    /*
    * TODO: Fnish initialization!!!
    */
    public ViewingPlane(float d,float initYaw, float initPitch, float initRoll, int w, int h, float rW, float rH, Vector camPos) throws Exception{
        
        if(camPos.getDim() == 3) {
            
            dist = d;
            planeWidth = w;
            planeHeight = h;
            realWidth = rW;
            realHeight = rH;
            yaw = initYaw;
            pitch = initPitch;
            roll = initRoll;
            
            float cx = camPos.getComponent(0);
            float cy = camPos.getComponent(1);
            float cz = camPos.getComponent(2);
            
            constraints = new Vector[] { oo.transformOrientation(new Vector(cx-realWidth/2,cy+realHeight/2,cz+dist),camPos,yaw,pitch,roll),
                                         oo.transformOrientation(new Vector(cx+realWidth/2,cy+realHeight/2,cz+dist),camPos,yaw,pitch,roll), 
                                         oo.transformOrientation(new Vector(cx-realWidth/2,cy-realHeight/2,cz+dist),camPos,yaw,pitch,roll), 
                                         oo.transformOrientation(new Vector(cx+realWidth/2,cy-realHeight/2,cz+dist),camPos,yaw,pitch,roll) };
           
            
        } else {
            throw new Exception("ERROR: CAMERA VECTOR IS NOT OF DIMENSION 3, THIS SHOULD NEVER HAPPEN, CONGRATULATIONS!");
        }
    }
    
    /*
    * Getter-Setter methods
    */
    
    /*
    * Returns the Width of the ViewingPlane object
    */
    public int getWidth() {
        return planeWidth;
    }
    
    /*
    * Sets the Width of the ViewingPlane object to a value nW
    */
    public void setWidth(int nW) {
        planeWidth = nW;
    }
    
    /*
    * Returns the Height of the ViewingPlane object 
    */
    public int getHeight() {
        return planeHeight;
    }
    
    /*
    * Sets the Height of the ViewingPlane object to a value nH
    */
    public void setHeight(int nH) {
        planeHeight = nH;
    }
    
    /*
    * Returns the distance of the center of the ViewingPlane object to the assigned Camera object
    */
    public float getDistance() {
        return dist;
    }
    
    /*
    * Sets the distance of the center of the ViewingPlane object to the assigned Camera object to a value nD
    */
    public void setDistance(float nD) {
        dist = nD;
    }
    
    /*
    * Returns the Yaw angle orientation of the ViewingPlane object
    */
    public float getYaw() {
        return yaw;
    }
    
    /*
    * Sets the Yaw angle orientation of the ViewingPlane object to a value nYaw
    */
    public void setYaw(float nYaw) {
        yaw = nYaw;
    }
    
    /*
    * Returns the Pitch angle orientation of the ViewingPlane object 
    */
    public float getPitch() {
        return pitch;
    }
    
    /*
    * Sets the Pitch angle orientation of the ViewingPlane object to a value nPitch
    */
    public void setPitch(float nPitch) {
        pitch = nPitch;
    }
    
    /*
    * Returns the Roll angle orientation of the ViewingPlane object 
    */
    public float getRoll() {
        return roll;
    }
    
    /*
    * Sets the Roll angle orientatiom of the ViewingPlane object to a new value nRoll
    */
    public void setRoll(int nRoll) {
        roll = nRoll;
    }
    
    /*
    * Returns a specific constraint Vector object of the ViewingPlane object
    */
    public Vector getConstraint(int i) {
        if(i >= 0 && i < 3)
            return constraints[i];
        else {
            System.out.println("ERROR: REQUESTED COMPONENT DIMENSION IS LARGER THAN THE CONSTRAINTS LIST DIMENSION! ");
            return null;
        }
    }
    
    /*
    * Sets a specific constraints Vector object of the ViewingPlane object to a new Vector object
    */
    public void setConstraint(int i, Vector nConstraint) {
        if(i >= 0 && i < 3)
            constraints[i] = nConstraint;
        else
            System.out.println("ERROR: REQUESTED COMPONENT DIMENSION IS LARGER THAN THE CONSTRAINTS LIST DIMENSION!");
    }
    
    /*
    * Returns the constraints Vector objects of the ViewingPlane object 
    */
    public Vector[] getConstraints() {
        return constraints;
    }
    
    /*
    * Sets the constraints Vector objects of the ViewingPlane object to a new set of Vector objects nCosntraints
    */
    public void setConstraints(Vector[] nConstraints) {
        if(nConstraints.length != 4) {
            System.out.println("ERROR: INPUT VECTOR ARRAY MUST HAVE AN LENGTH OF 4!");
        }
        else {
            constraints = nConstraints;
        }
    }
    
    /*
    * Object methods
    */
    
    
}
