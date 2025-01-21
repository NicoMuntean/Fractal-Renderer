package logic.objects;

import java.awt.Color;
import logic.general.MathOperations;
import logic.general.Vector;

public abstract class RenderableObject {
    
    public abstract Color getColor();
    
    public abstract void setColor(Color nC);
    
    public abstract float getShinyness();
    
    public abstract float calcDistance(MathOperations mo, Vector rayPosition,double power);
    
    
}
