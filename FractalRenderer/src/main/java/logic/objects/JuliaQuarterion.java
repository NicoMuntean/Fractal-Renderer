package logic.objects;

import java.awt.Color;
import logic.general.MathOperations;
import logic.general.Vector;
import logic.visualization.OrientationOperations;

public class JuliaQuarterion extends RenderableObject{
    
    // NOT IMPLEMENTED YET!
    
    private Vector center;
    private float radius;
    private Color eigenColor;
    private float shinyness;
    private float stretchCoeff;
    
    private MathOperations mo = new MathOperations();
    private OrientationOperations oo = new OrientationOperations();
    private float yaw;
    private float pitch;
    private float roll;
    
    public JuliaQuarterion(float[] centerP,float r,Color color,float shin) {
        
        center = new Vector(centerP);
        radius = r;
        eigenColor = color;
        shinyness = shin;
        stretchCoeff = 1.0f;
        pitch = 0;
        yaw = 0;
        roll = 0;
        
    }
    
    public JuliaQuarterion(float[] centerP, float r,Color color,float shin,float sC,float y, float p, float ro) {
        
        center = new Vector(centerP);
        radius = r;
        eigenColor = color;
        shinyness = shin;
        stretchCoeff = sC;
        pitch = p;
        yaw = y;
        roll = ro;
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

    @Override
    public float calcDistance(MathOperations mo, Vector rayPosition, double power) {
        
        
        return 0.0f;
    }
    
}
