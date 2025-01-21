package logic.visualization;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import logic.general.MathOperations;
import logic.general.Vector;
import logic.objects.RenderableObject;
import logic.shading.calculations.ShadingOperations;
import logic.shading.objects.PointLamp;

public class RenderOperations {
    
    private MathOperations mo = new MathOperations();
    
    Scene testScene = new Scene();
    
    private ShadingOperations so = new ShadingOperations();
    
    public RenderOperations() {
        
    }
    
    /*
    * Saves a BufferedImage ton the by "imgPath" defined systempath. The image is created using RayMarching in the Viewfield of the 
    * Camera object c. 
    */
    public void RenderImage(Camera c, String saveDir, String imgName, RenderableObject ro,double power,PointLamp pl) {
        
        
        ViewingPlane vP = c.getPlane();
        Vector[] pos = vP.getConstraints();
        
        testScene.addRenderableObj(ro);
        testScene.setPrimaryLightsource(pl);
        
        int w = vP.getWidth();
        int h = vP.getHeight();
        
        String outPath = saveDir + imgName + ".png";
        
        int[][] distanceVals = getDistanceMap(w,h,pos,c.getPos(),200000,power,pl);
        
        BufferedImage bi = colorImage(w,h,distanceVals);
        
        try {
            File outputfile = new File(outPath);
            ImageIO.write(bi, "png", outputfile);
        }
        catch (IOException e) {
				
        }
        
        
    }
    
    /*
    * Returns a Distance Map in the form of a float[][] Array. The values of said Array define the Distance the 
    * Ray sent out from the Cameras position (cPos Vector object) had to travel until it hit an object. The integer 
    * values w and h define the width and height of the resulting image and are needed to correctly calibrate the
    * travel direction of the rays. The rays are sent in an raster Form from the Camera object through the ViewingPlane
    * Object, starting on the upper-left Point, covering the width, then going one height increment down and repeating said 
    * process until the lower-right Point of the ViewingPlane object is reached. 
    *
    *                   FIRST RAY                            LAST RAY
    *                    ------>                    
    *              \_________________                   _________________
    *              |X|_|_|_|_|_|_|_|_|                 |_|_|_|_|_|_|_|_|_|
    *              |_|\|_|_|_|_|_|_|_|     ---->       |_|_|_|_|_|_|_|_|_|
    *              |_|_\_|_|_|_|_|_|_|     ---->       |_|_|_|_|_|_|_|_|_|/
    *              |_|_|\|_|_|_|_|_|_|                 |_|_|_|_|_|_|_|_|X|
    *                    \                                           __/
    *                     \                                       __/
    *                      \                                     /
    *                       C                                   C
    *
    * The Integer variable maxIter serves as a constraints on the iterations the RayMarching algorithm is allowed to perform 
    */
    private int[][] getDistanceMap(int w, int h, Vector[] paneConstraints, Vector cPos, int maxIter,double power,PointLamp light) {
        
        Vector hIncrement = mo.scalV(mo.subV(paneConstraints[2], paneConstraints[0]),1/(float)h);
        Vector wIncrement = mo.scalV(mo.subV(paneConstraints[1], paneConstraints[0]),1/(float)w);
        
        int[][] distanceVals = new int[w][h];
        
        
        for(int x = 0; x < w; x++) {
            for(int y = 0; y < h; y++) {
                
                distanceVals[x][y] = RayMarching(mo.normV(mo.subV(mo.addV(mo.addV(paneConstraints[0], mo.scalV(wIncrement, x)),mo.scalV(hIncrement, y)),cPos)),cPos,maxIter,0.001f,power);
                
            }
            if(x%5 == 0)
                System.out.println(x + " OF " + w);
        }
        
        return distanceVals;
    }
    
    
    /*
    * Calculates the output BufferedImage RGB-Pixelvalues based on the values of the 2D-float Array distanceVals
    * which represents the distance the rays from the Camera object had to travel before "colliding" with an 
    * object. The integer values h and w serve as the BufferedImages width and height.
    * The output is a BufferedImage, colored based on the distanceMap.
    */
    private BufferedImage colorImage(int w, int h,int[][] distanceVals) {
        
        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Color out;
        
        for(int x = 0; x < w; x++) {
            for(int y = 0; y < h; y++) {  
                    
                    out = new Color(((int)(distanceVals[x][y]) & 0xff0000) >> 16,
                                    ((int)(distanceVals[x][y]) & 0xff00) >> 8,
                                    ((int)(distanceVals[x][y]) & 0xff));
                
                output.setRGB(x, y, out.getRGB());
            }
        }
        
        return output;
    }
    
    /*
    * Calculated the distance after which a ray, sent from the location "startPos" in the direction "direction" 
    * has to travel until hitting an object. If no object is hit after "maxIter" iterations, the value 0 is returned, which 
    * acts as a default value for no ray collision. If the distance to the object is smaller than "hitEps", the algorithm
    * registers a collision, this is necessary, as the goal is to get very close to the objects surface, but not overshooting 
    * it and sending the ray as a result into the object. The algorithm thus sets the step size to half the distance to the object.
    * Thus, without an epsilon (hitEps), no collision would ever be detected.
    */
    private int RayMarching(Vector direction, Vector startPos, int maxIter, float hitEps,double power) {
        
        Vector dir = direction.copy();
        Vector sp = startPos.copy();
        Vector pos = dir.copy();
        float travelDis = 0;
        
        float distanceObj = 999999;
        RenderableObject chosenObj = testScene.getSceneObjAtID(0);
        
        for(int i = 0; i < maxIter; i++) {
            
            for(RenderableObject obj : testScene.getAllSceneObj()) {
            
            float specificDis = obj.calcDistance(mo, sp,power);
            
            if(travelDis > 10)
                return 0;
            
            if(specificDis < distanceObj) {
                distanceObj = specificDis;
                chosenObj = obj;
            }
            
            }
            
            if(!Float.isFinite(distanceObj))
                return 0;
            
            if(distanceObj > hitEps) {
                sp = mo.addV(sp,mo.scalV(dir, distanceObj/2));
                travelDis += distanceObj/2;
            }
            else {
                if(!lightrayHitByObj(sp,testScene.getPrimaryLightsource(),testScene,hitEps/2,maxIter,power))
                    return calcShaderCoeff(sp,testScene.getPrimaryLightsource(),hitEps,startPos,chosenObj,false,power);
                else
                    return calcShaderCoeff(sp,testScene.getPrimaryLightsource(),hitEps,startPos,chosenObj,true,power);
            
            }
        }
        
        return 0;
    }
    
    /*
    * By taking into account the PointLamp object l, the position of the lightray pos the Camera objects position cpos as well
    * as a epsilon value eps which is needed to calculate the numerical gradient a color rgb value in the form of a Integer
    * is returned.
    */
    private int calcShaderCoeff(Vector pos,PointLamp l, float eps, Vector cPos,RenderableObject hitObj,boolean hit,double power) {
        
        float shaderEps = 0.001f;
        
        Vector[] shiftedPos = prepareGradientVectors(pos.copy(),shaderEps);
        
        Vector input1 = new Vector(new float[] {hitObj.calcDistance(mo,shiftedPos[0],power),hitObj.calcDistance(mo,shiftedPos[1],power),hitObj.calcDistance(mo,shiftedPos[2],power) });       
        Vector input2 = new Vector(new float[] {hitObj.calcDistance(mo,shiftedPos[3],power),hitObj.calcDistance(mo,shiftedPos[4],power),hitObj.calcDistance(mo,shiftedPos[5],power) });
        
        Vector surfaceNorm = mo.normV(mo.numericGrad(input1, input2, 2*shaderEps));
        
        //return so.applyPhongShading(pos, surfaceNorm, cPos, l.getPos(), l.getIntensity(),0.6f, 0.0f, 0.4f, 30);
        return so.applyPhongShading(hitObj, pos, surfaceNorm, cPos, l.getPos(), l.getIntensity(),0.6f, 0.0f, 0.4f, 20,hit);
    }
    
    
    private boolean lightrayHitByObj(Vector pos, PointLamp l, Scene s, float hitEps,int maxIter, double power) {
        
    
        Vector dir = mo.subV(l.getPos(), pos);
        Vector sp = pos.copy();
        
        float incr = mo.lenV(dir)/maxIter;
        
        float distanceObj = 999999;
        RenderableObject chosenObj = testScene.getSceneObjAtID(0);
        
        for(int i = 0; i < maxIter; i++) {
            
            for(RenderableObject obj : testScene.getAllSceneObj()) {
            
            float specificDis = obj.calcDistance(mo, sp,power);
            
            if(specificDis < distanceObj) {
                distanceObj = specificDis;
                chosenObj = obj;
            }
            }
            
            if(!Float.isFinite(distanceObj))
                return false;
            
            if(distanceObj > hitEps) {
                sp = mo.addV(sp,mo.scalV(dir, incr));
            }
            else {
                return true;
            
            }
    }
        return false;
    }
    
    /*
    * Returns the base Vector objects needed to construct the numerical gradient in the Point pos. Hereby, the coordinates of the pos 
    * Vector get adjusted by a small float value eps, each Vector object in the output Vectro Array out is adjusted in one coordinate, resulting 
    * in all parts needed for a numerical gradient.
    */
    private Vector[] prepareGradientVectors(Vector pos, float eps) {
        
        Vector[] out = new Vector[6];
        
        out[0] = new Vector(new float[] {pos.getComponent(0) + eps, pos.getComponent(1), pos.getComponent(2)});
        out[1] = new Vector(new float[] {pos.getComponent(0), pos.getComponent(1) + eps, pos.getComponent(2)});
        out[2] = new Vector(new float[] {pos.getComponent(0), pos.getComponent(1), pos.getComponent(2) + eps});
        out[3] = new Vector(new float[] {pos.getComponent(0) - eps, pos.getComponent(1), pos.getComponent(2)});
        out[4] = new Vector(new float[] {pos.getComponent(0), pos.getComponent(1) - eps, pos.getComponent(2)});
        out[5] = new Vector(new float[] {pos.getComponent(0), pos.getComponent(1), pos.getComponent(2) - eps});
        
        return out;
    }
   
    
}
