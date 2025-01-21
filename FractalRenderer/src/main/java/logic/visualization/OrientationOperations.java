package logic.visualization;

import logic.general.MathOperations;
import logic.general.Matrix;
import logic.general.Vector;


public class OrientationOperations {
    
    MathOperations mo = new MathOperations();
    
    public OrientationOperations() {
        
    }
    
    /*
    * Transforms the position of a Vector/Point based on the input yaw, pitch and roll angles
    *
    * First, the Rotation matricies for yaw (mYaw), pitch (mPitch) and roll (nRoll). Next, the 
    * complete Rotation Matrix (mRot) will be calculated: mRot = mYaw*mPitch*mRoll. Lastly, mRot
    * will be used on the input Vector/Point (v) to calculate the new Vector/Point (out):
    * out = mRot*v
    */
    public Vector transformOrientation(Vector v, Vector rotCenter, float yaw, float pitch, float roll) {
        
        Matrix mYaw;
        Matrix mPitch;
        Matrix mRoll;
        Matrix mRot;
        
        Vector out;
        
        mYaw = new Matrix( new float[][]  { new float[] {mo.cos(yaw) , -mo.sin(yaw), 0} 
                                          , new float[] {mo.sin(yaw) ,  mo.cos(yaw), 0} 
                                          , new float[] {0              ,  0             , 1}
                                          });
        
        mPitch = new Matrix(new float[][] { new float[] { mo.cos(pitch), 0 , mo.sin(pitch)}
                                          , new float[] { 0               , 1 , 0               }
                                          , new float[] {-mo.sin(pitch), 0 , mo.cos(pitch)}
                                          });
        
        mRoll = new Matrix(new float[][]  { new float[] {1, 0              ,  0              }
                                          , new float[] {0, mo.cos(roll), -mo.sin(roll)}
                                          , new float[] {0, mo.sin(roll),  mo.cos(roll)}
                                          });
        
        mRot = mo.dotM(mYaw,mo.dotM(mPitch, mRoll));
        /*
        System.out.println("BEGIN");
        v.printVector();
        System.out.println();
        
        System.out.println("END");
        mo.mDotV(mRot, v).printVector();
        System.out.println();
        */
        out = mo.mDotV(mRot, v);

        return out;
    }
    
    /*
    * Sets the position of a Vector/Point object to a new value.
    * Returns a 
    */
    public Vector alterPosition(Vector v, Vector nV) {
        
        Vector shift;
        Vector out;
        
        if(v.getDim() != nV.getDim()) {
            System.out.println("ERROR! DIMENSION OF VECTOR OBJECTS IS NOT EQUAL!");
            return null;
        }
        
        shift = mo.subV(nV, v);
        
        out = mo.addV(v,shift);
        
        return out;
    }
    
    /*
    * Updates the Position of the ViewingPlane object in respect to the Camera angle
    */
    public void updateCamView(Camera c) {
        
        ViewingPlane vP = c.getPlane();
        
        float deltaYaw = c.getYaw() - vP.getYaw();
        float deltaPitch = c.getPitch() - vP.getPitch();
        float deltaRoll = c.getRoll() - vP.getRoll();
        
        for(int x = 0; x < 4; x++) {
            vP.setConstraint(x, transformOrientation(vP.getConstraint(x),c.getPos(),deltaYaw,deltaPitch,deltaRoll));
        }        
        
    }
    
}
