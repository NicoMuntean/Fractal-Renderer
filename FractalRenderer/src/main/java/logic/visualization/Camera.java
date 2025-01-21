package logic.visualization;

import logic.general.Vector;

public final class Camera {
    
    private Vector pos;
    private Vector orientation = new Vector(new float[] {1,0,0}); 
    private ViewingPlane pic;
    
    private float yaw;
    private float pitch;
    private float roll;
    
    /*
    * Object initialization methods + overloads
    */
        
    public Camera(Vector p,float initYaw, float initPitch, float initRoll,int planeDist,int wPixels, int hPixels) throws Exception {
        
        if(p.getDim() != 3)
            throw new Exception("ERROR! CAMERA OBJECT REQUIRES A POSITION VECTOR OF DIM=3!");
        pos = p;
        yaw = initYaw;
        pitch = initPitch;
        roll = initRoll;
        initializePlane(planeDist,(int)(wPixels),(int)(hPixels));
        
    }
    
    public Camera(float[] coord, float initYaw, float initPitch, float initRoll,int planeDist,int wPixels, int hPixels) throws Exception {
    
        if(coord.length != 3)
            throw new Exception("ERROR! CAMERA OBJECT REQUIRES A POSITION VECTOR OF DIM=3!");
        pos = new Vector(coord);
        yaw = initYaw;
        pitch = initPitch;
        roll = initRoll;
        initializePlane(planeDist,(int)(wPixels),(int)(hPixels));
    }
    
    public Camera(float x, float y, float z, float initYaw, float initPitch, float initRoll,int planeDist,int wPixels, int hPixels) {
        
        pos = new Vector(new float[] {x,y,z});
        yaw = initYaw;
        pitch = initPitch;
        roll = initRoll;
        initializePlane(planeDist,(int)(wPixels),(int)(hPixels));
    }
    
    /*
    * Getter-Setter methods
    */
    
    /*
    * Return the position Vector of the Camera object
    * returns a Vector object.
    */
    public Vector getPos() {
        return pos;
    }
    
    /*
    * Returns the float array comprising the position Vector of the Camera Object
    */
    public float[] getPosArr() {
        return pos.getAllComponents();
    }
    
    /*
    * Sets the position Vector of the Camera object to a new value
    */
    public void setPos(Vector nPos) {
        
        if(nPos.getDim() != 3)
            System.out.println("ERROR! CAMERA OBJECT REQUIRES A POSITIONAL VECTOR OF DIM=3!");
        else
            pos = nPos;
    }
    
    /*
    * Sets a specific coordinate of the position Vector of the Camera object to a new value.
    */
    public void setCoord(float nC, int i) {
        if(i < 0 || i > pos.getDim())
            System.out.println("ERROR! REQUESTED POSITIONAL COMPONENT IS EITHER <0 OR > POS.DIM!");
        else
            pos.setComp(nC, i);
    }
    
    /*
    * Returns a specific coordinate of the position Vector of the Camera object.
    */
    public float getCoord(int i) {
        
        if(i < 0 || i > pos.getDim()) {
            System.out.println("ERROR! REQUESTED POSITIONAL COMPONENT IS EITHER <0 OR > POS.DIM!");
            return -9999;
        }
        else
            return pos.getComponent(i);
    }
    
    /*
    * Returns the Yaw angle orientation of the Camera object
    */
    public float getYaw() {
        return yaw;
    }
    
    /*
    * Sets the Yaw angle orientation of the Camera object to a new value
    */
    public void setYaw(int nYaw) {
        yaw = nYaw;
    }
    
    /*
    * Returns the Pitch angle orientation of the Camera object
    */
    public float getPitch() {
        return pitch;
    }
    
    /*
    * Sets the Pitch angle orientation of the Camera object to a new value
    */
    public void setPitch(float nPitch) {
        pitch = nPitch;
    }
    
    /*
    * Returns the Roll angle orientation of the Camera object
    */
    public float getRoll() {
        return roll;
    }
    
    /*
    * Sets the Roll angle orientation of the Camera object to a new value
    */
    public void setRoll(float nRoll) {
        roll = nRoll;
    }
    
    /*
    * Returns the assigned ViewingPlane object
    */
    public ViewingPlane getPlane() {
        return pic;
    }
    
    /*
    * Assigns a new VieingPlane object
    */
    public void setPlane(ViewingPlane nP) {
        pic = nP;
    }
    
    /*
    * Returns the orientation Vector of the Camera object
    */
    public Vector getOrientation() {
        return orientation;
    }
    
    /*
    * Sets the orientation vector object of the Camera object to a new Vector object nO
    */
    public void setOrientation(Vector nO) {
        if(nO.getDim() == orientation.getDim())
            orientation = nO;
        else
            System.out.println("ERROR! THE CAMERA OBJECT ORIENTATION VECTORS DIMENSION DOES NOT MATCH THE INPUT VECTORS DIMENSION!");
    }
     
    //######## VIEWING PLANE HANDELING ########//
    
    private void initializePlane(float dist,int w, int h) {
        
        try {
            pic = new ViewingPlane(dist,yaw,pitch,roll,w,h,2f,1.125f,pos);
        } catch (Exception ex) {
            System.out.println("ERROR: VIEWING PLANE INITIALIZATION FAILED! ERROR CODE: " + ex);
        }
    }
    
}
