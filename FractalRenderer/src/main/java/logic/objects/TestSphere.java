package logic.objects;

import java.awt.Color;
import logic.general.MathOperations;
import logic.general.Vector;

public class TestSphere extends RenderableObject{
    
    private Vector center;
    private float radius;
    private Color eigenColor;
    private float shinyness;
    
    public TestSphere(float[] centerP,float r,Color color,float shin) {
        
        center = new Vector(centerP);
        radius = r;
        eigenColor = color;
        shinyness = shin;
        
    }
    
    @Override
    public float calcDistance(MathOperations mo, Vector rayPosition,double power) {
        
        Vector c = center;
        float r = radius;
        
        return mo.lenV(mo.subV(rayPosition, c)) - r;
        
    }
    
    public Vector getCenter() {
        return center;
    }
    
    public float getRadius() {
        return radius;
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
}
