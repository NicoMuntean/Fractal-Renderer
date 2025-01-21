package logic.objects;

import java.awt.Color;
import logic.general.MathOperations;
import logic.general.Vector;
import logic.visualization.OrientationOperations;

public class TestMandelbulb extends RenderableObject{

    private Vector center;
    private float radius;
    private Color eigenColor;
    private float shinyness;
    private float stretchCoeff;
    private double power;
    
    private MathOperations mo = new MathOperations();
    private OrientationOperations oo = new OrientationOperations();
    private float yaw;
    private float pitch;
    private float roll;
    
    
    /*
    * Creates a TestMandelbulb Object with the mathematical center in R3 defined by the 1D float array centerP
    */
    public TestMandelbulb(Vector centerP,float r,double pow,Color color,float shin) {
        
        center = centerP;
        radius = r;
        power = pow;
        eigenColor = color;
        shinyness = shin;
        stretchCoeff = 1.0f;
        pitch = 0;
        yaw = 0;
        roll = 0;
        
    }
    
    public TestMandelbulb(Vector centerP, float r,double pow,Color color,float shin,float sC,float y, float p, float ro) {
        
        center = centerP;
        radius = r;
        power = pow;
        eigenColor = color;
        shinyness = shin;
        stretchCoeff = sC;
        pitch = p;
        yaw = y;
        roll = ro;
    }
    
    @Override
    public float calcDistance(MathOperations mo, Vector rayPosition,double power) {
        
        Vector z = rayPosition.copy();
        
	double dr = 1.0;
	double r = 0.0;
        double pow = power;
        double m = mo.dotV(z, z);
        
	for (int i = 0; i < 50; i++) {
            
            r = mo.lenV(z);
	
            if (r > 4.0) {
                break;
            }
            
            double theta = Math.acos(z.getComponent(2) / r);
            double phi = Math.atan2(z.getComponent(1),z.getComponent(0));
            dr = Math.pow(r, pow - 1.0) * pow * dr + 1.0;

			// scale and rotate the point
	    double zr = Math.pow(r, pow);
	    theta = theta * pow;
	    phi = phi * pow;

			// convert back to cartesian coordinates
                        
            double z0 = zr * (Math.sin(theta) * Math.cos(phi)) + rayPosition.getComponent(0);
            double z1 = zr * (Math.sin(theta) * Math.sin(phi)) + rayPosition.getComponent(1);
            double z2 = zr * Math.cos(theta) + rayPosition.getComponent(2);
            
            // z = zr*vec3(sin(theta)*cos(phi), sin(phi)*sin(theta), cos(theta));
            
            z.setComp((float)z0, 0);
            z.setComp((float)z1, 1);
            z.setComp((float)z2, 2);
            
            m = mo.dotV(z, z);
			// z+=pos;
	}
        //return (float)(0.25 * Math.log(m)*Math.sqrt(m) / dr);
	return (float)(0.5 * Math.log(r) * r / dr);
        
    }
    
    /*
    * Getter & Setter methods for local variables
    */
    
    public Vector getCenter() {
        return center;
    }
    
    public float getRadius() {
        return radius;
    }
    
    public double getPower() {
        return power;
    }
    
    public void setPower(double nP) {
        if(nP >= 0)
            power = nP;
        else
            System.out.println("INPUT FLOAT MUST BE >= 0!");
    }

    @Override
    public Color getColor() {
        return eigenColor;
    }

    @Override
    public void setColor(Color nC) {
        eigenColor = nC;
    }

    @Override
    public float getShinyness() {
        return shinyness;
    }
    
    public void setStretchCoeff(float nSC) {
        stretchCoeff = nSC;
    }
    
    public float getStretchCoeff() {
        return stretchCoeff;
    }
    
}

