package logic.shading.objects;

import logic.general.Vector;


public class PointLamp extends LightSource{
    
    private Vector pos;
    private float intensity;
    
    /*
    * Object initialization methods + overloads
    */
    
    public PointLamp(Vector initPos,float initIntensity) throws Exception {
        
        if(initPos.getDim() != 3) {
            throw new Exception("ERROR! LAMP OBJECT REQUIRES A POSITION VECTOR OF DIM=3!");
        }
        pos = initPos;
        intensity = initIntensity;
        
    }
    
    public PointLamp(float[] initPos, float initIntensity) throws Exception {
       
        if(initPos.length != 3) {
            throw new Exception("ERROR! LAMP OBJECT REQUIRES A POSITION VECTOR OF DIM=3!");
        }
        pos = new Vector(initPos);
        intensity = initIntensity;
        
    }
    
    public PointLamp(int x, int y, int z, float initIntensity) {
        
        pos = new Vector(x,y,z);
        intensity = initIntensity;
    }
    
    /*
    * Getter-Setter methods
    */
    
    /*
    * Returns the position Vector object pos of the PointLight object
    */
    public Vector getPos() {
        return pos;
    }
    
    /*
    * Sets the position Vector object pos of the PointLight object to a new Vector object nPos
    */
    public void setPos(Vector nPos) {
        if(nPos.getDim() != 3) 
            System.out.println("ERROR! A VECTOR OF DIM=3 IS REQIRED! \"setPos\" FAILED!");
        else
            pos = nPos;
    }
    
    /*
    * Returns the intesnity value of the PointLight object
    */
    public float getIntensity() {
        return intensity;
    }
    
    /*
    * Sets the intensity value of the Camera object to a new Value nIntensity
    */
    public void setIntensity(float nIntensity) {
        if(nIntensity < 0) {
            System.out.println("WARNING! INTENSITY CANNOT BE < 0! INETNSITY SET TO 0 INSTEAD OF " + nIntensity + "!");
            intensity = 0;
        }
        else if(nIntensity > 1) {
            System.out.println("WARNING! INTENSITY CANNOT BE > 1! INETNSITY SET TO 1 INSTEAD OF " + nIntensity + "!");
            intensity = 1;
        }
        else
            intensity = nIntensity;
    }
    
}
