package main;

import java.awt.Color;
import logic.general.MathOperations;
import logic.general.Vector;
import logic.objects.TestMandelbulb;
import logic.shading.objects.PointLamp;
import logic.visualization.Camera;
import logic.visualization.RenderOperations;

public class FractalRenderer {
    
    static MathOperations mo = new MathOperations();
    static RenderOperations ro = new RenderOperations();

    public static void main(String[] args) {
        
        /*
        *   Initializing the camera object and rendering a Mandelbulb object illuminated by a PointLamp, Parameters used:
        *
        *   --- Camera Parameters: ---
        *
        *   - cameraPosition:       Vector object, defines the relative positon of the camera object in the 3D render construct
        *   - cameraYaw:            Defines initial camera yaw
        *   - cameraPitch:          Defines initial cameraPitch
        *   - cameraRoll:           Defines initial cameraRoll
        *   - viewplaneDistance:    Defines the absolute distance of the viewplane from the camera object
        *   - widthPixels:          Defines the dispersion relation of viewplane width to the pixel width of the resulting PNG
        *   - heightPixes:          Defines the dispersion relation of the viewplane to the pixel height of the resulting PNG
        *   - saveDir:              Path leading to the directory in which the resulting png is to be saved
        *   - imgName:              String containing the filename of the resulting PNG, important: .png is not necessary to be added!
        *
        *   --- Mandelbulb Parameters: ---
        *
        *   - mandelbulbPosition:   Vector object, defines the relative position of the Mandelbulb render object
        *   - bulbRadius:           Defines the Mandelbulb objects radius in construct coordinates
        *   - bulbPower:            Defines the power value used in the Mandelbulb set calculations
        *   - bulbColor:            Defines the natural Color of the Mandelbulb assuming full illumination
        *   - shinynes:             Defines the shinyness coefficient used in the Phong-Illumination Model
        *   - stretchCoeff:         Defines if and how much the Mandelbulbs interior shall be "stretched"
        *   - bulbYaw:              Defines inital yaw of the Mandelbulb
        *   - bulbPitch:            Defines initial pitch of the Mandelbulb
        *   - bulbRoll:             Defines initial roll of the Mandelbulb
        *
        *   --- Point Lamp Parameters: ---
        *
        *   - lampPosition:         Vector object, defines the relative position of the Point Lamp object
        *   - lampIntensity:        Defines the light intensity of the lamp object (0 = no illumination, 1 = maximum illumination)
        *
        */
        
        Vector cameraPosition = new Vector(0,0,-6);
        float cameraYaw = 0;
        float cameraPitch = 0;
        float cameraRoll = 0;
        int viewplaneDistance = 2;
        int widthPixels = 7680/2;
        int heightPixels = 4320/2;
        String saveDir = "/Users/dummyuser/dummydir/";
        String imgName = "dummyname";
        
        Vector mandelbulbPosition = new Vector(0,0,0);
        float bulbRadius = 1f;
        double bulbPower = 3.0;
        Color bulbColor = new Color(255,255,255);
        float shinyness = 1;
        float stretchCoeff = 1f;
        float bulbYaw = 0;
        float bulbPitch = 0;
        float bulbRoll = 0;
        
        Vector lampPosition = new Vector(1,6,-4);
        float lampIntensity = 1;
        
        
        
        Camera c;
        try {
            TestMandelbulb bulbObj = new TestMandelbulb(mandelbulbPosition,bulbRadius,bulbPower,bulbColor,shinyness,stretchCoeff,bulbYaw,bulbPitch,bulbRoll);
            c = new Camera(cameraPosition,cameraYaw,cameraPitch,cameraRoll,viewplaneDistance,widthPixels,heightPixels);
            PointLamp pLamp = new PointLamp(lampPosition,lampIntensity);
            ro.RenderImage(c,saveDir,imgName,bulbObj,bulbObj.getPower(),pLamp);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
}
