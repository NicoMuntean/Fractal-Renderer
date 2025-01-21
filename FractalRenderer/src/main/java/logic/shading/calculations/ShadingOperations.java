package logic.shading.calculations;

import java.awt.Color;
import logic.general.MathOperations;
import logic.general.Vector;
import logic.objects.RenderableObject;

public class ShadingOperations {
    
    MathOperations mo = new MathOperations();
    
    public ShadingOperations() {
        
        
    }
    
    public float applyPhongShading(Vector oPos, Vector sNorm, Vector cPos, Vector lPos, float intensity, float diffConst, float ambConst, float specConst,float specIntensity,boolean hit) {
        
        Vector lampOrientation = mo.subV(oPos, lPos);
        
        float diffCoeff = intensity*diffConst*mo.dotV(sNorm, mo.normV(lampOrientation));
        
        if(diffCoeff < 0)
            diffCoeff = 0;
        
        float ambCoeff = intensity*ambConst;
        
        if(ambCoeff < 0)
            ambCoeff = 0;
        
        Vector reflectionVec = mo.subV(mo.normV(lampOrientation),mo.scalV(mo.scalV(sNorm, mo.dotV(mo.normV(lampOrientation), sNorm)),2));
        
        float specCoeff = intensity*specConst*(float)Math.pow((double)mo.dotV(mo.normV(cPos), mo.normV(reflectionVec)), (double)specIntensity);
        
        if(mo.dotV(mo.normV(lampOrientation),mo.normV(sNorm)) < 0)
            specCoeff = 0;
        
        return diffCoeff + ambCoeff + specCoeff;
    }
    
    public int applyPhongShading(RenderableObject rO, Vector oPos, Vector sNorm, Vector cPos, Vector lPos, float intensity, float diffConst, float ambConst, float specConst,float specIntensity,boolean hit) {
        
        int outColor = 0;
        Vector lampOrientation = mo.subV(oPos, lPos);
        
        float diffCoeff = intensity*diffConst*mo.dotV(sNorm, mo.normV(lampOrientation));
        
        if(diffCoeff < 0)
            diffCoeff = 0;
        
        float ambCoeff = intensity*ambConst;
        
        if(ambCoeff < 0)
            ambCoeff = 0;
        
        Vector reflectionVec = mo.subV(mo.normV(lampOrientation),mo.scalV(mo.scalV(sNorm, mo.dotV(mo.normV(lampOrientation), sNorm)),2));
        
        float specCoeff = rO.getShinyness()*intensity*specConst*(float)Math.pow((double)mo.dotV(mo.normV(cPos), mo.normV(reflectionVec)), (double)specIntensity);
        
        if(mo.dotV(mo.normV(lampOrientation),mo.normV(sNorm)) < 0)
            specCoeff = 0;
        
        float totalCoeff = diffCoeff + ambCoeff + specCoeff;
        
        //int objColor = rO.getColor().getRGB();
        
        int red;
        int blue;
        int green; 
       
        if(!hit) {
            blue = (int)(rO.getColor().getBlue() * totalCoeff );
            green = (int)(rO.getColor().getGreen() * totalCoeff);
            red = (int)(rO.getColor().getRed() * totalCoeff);
        }
        else {
            blue = (int)(rO.getColor().getBlue() * (totalCoeff-specCoeff) * 0.1);
            green = (int)(rO.getColor().getGreen() * (totalCoeff-specCoeff) * 0.1);
            red = (int)(rO.getColor().getRed() * (totalCoeff-specCoeff) * 0.1);
        }
         
        if(blue > 255) 
            blue = (int)(255);
        if(red > 255)
            red = 255;
        if(green > 255)
            green = (int)(255);
                   
        
        Color col = new Color(red,green,blue);
        outColor = col.getRGB();
        
        return outColor;
    }
    
}
