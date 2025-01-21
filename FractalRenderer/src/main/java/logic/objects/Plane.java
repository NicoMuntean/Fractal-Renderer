package logic.objects;

import java.awt.Color;
import logic.general.MathOperations;
import logic.general.Vector;

public class Plane extends RenderableObject{
    
    // NOT IMPLEMENTED YET!
   
    private Color eigenColor;
    private Vector u1;
    private Vector u2;
    private float shinyness;
    
    public Plane() {
        
    }
    
    @Override 
    public float calcDistance(MathOperations mo,Vector rayPosition,double power) {
        
        return 0;
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
