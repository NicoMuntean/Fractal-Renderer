# Fractal-Renderer
## Overview

The *Fractal Renderer* project is a vanilla Java developed ***first sketchout*** of a 3D [Ray-Marching](https://en.wikipedia.org/wiki/Ray_marching) based rendering engine, capable of visualizing a range of 3D fractals. As of now, the functionality is very limited, with the only fractal supported being the [Mandelbulb](https://en.wikipedia.org/wiki/Mandelbulb). The current state of the application does not posess a GUI, with the parametrs for the *fractals, camera and lighting* having to be written directly in the source code. 

## Current Features

### Supported Shapes and Fractals
Currently, the *Fractal Renderer* only supports the visualization of ***Spheres*** and ***Mandelbulb fractals*** of differing paramters. 

<p align="center">
  <img src="https://github.com/user-attachments/assets/fe45f9fa-307e-48ee-88be-f92a5486cdb6" height="290" />
</p>
<p align="center"><i>Example render of a sphere and a power 8 Mandelbulb</i></p>

Taking the *Mandelbulb* as an example, these parameters consist of:

```java
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
```
- ***Mandelbulb Center Position (center)***:
  - Defines the relative center position of the Mandelbulb
- ***Mandelbulb Radius (radius)***:
  - Defines the dispersion relation of the coordinates of the render construct to the Mandelbulb set
- ***Power (power)***:
  - Defines the power of the Mandelbulb
- ***Color (color)***:
  - Defines the natural color of the Mandelbulb assuming full illumination
- ***Shinyness (shinyness)***:
  - Defines the *shinyness* coefficient used in the [Phong reflection model](https://en.wikipedia.org/wiki/Phong_reflection_model)
- ***Stretch coefficient (stretchCoeff)***:
  - Defines the grade to which the shape of the Mandelbulb is stretched/compressed
- ***Yaw (yaw)***:
  - Defines the initial *[yaw](https://en.wikipedia.org/wiki/Degrees_of_freedom_(mechanics))* of the Mandelbulb
- ***Pitch (pitch)***:
  - Defines the intial *[pitch](https://en.wikipedia.org/wiki/Degrees_of_freedom_(mechanics))* of the Mandelbulb
- ***Roll (roll)***:
  - Defines the initial *[roll](https://en.wikipedia.org/wiki/Degrees_of_freedom_(mechanics))* of the Mandelbulb
 




### Rendering 
The core component of the *Fractal Renderer* is a CPU based [Ray Marching](https://en.wikipedia.org/wiki/Ray_marching) implementation, which allows the approximation of the fractal surface due to an *distance estimator*. In the case of the *Mandelbulb* the used distance estimator is of the form: 


$$\frac{\log\left(\left|\vec{r_p}\right|\right)}{2}\frac{\left|\vec{r_p}\right|}{d\left|\vec{r_p}\right|_n} \qquad\text{,}\qquad d\left|\vec{r_p}\right|_n = \left|\vec{r_p}\right|^{p-1} p d\left|\vec{r_p}\right|_m + 1 \quad\text{, ...}\quad m=n-1$$


Optical depth is achieved by employing the [Phong reflection model](https://en.wikipedia.org/wiki/Phong_reflection_model) in combination with a crude [Ray Tracing](https://en.wikipedia.org/wiki/Ray_tracing_(graphics)) implementation to simulate physical shadows.

The ***camera*** and ***lamp*** objects are freely placeable in all 3 spacial coordinates, alternating *pitch*,*yaw*, and *roll* of the objects is in principle possible, but results in bugs as of now, thus not recommended. Video rendering is not implemented yet, but in principle possible, by rendering multiple images and combining them to a video sequence. The resolution of the resulting image can be chosen freely.

The generation of a *power 8 Mandelbulb* fractal using the *Fractal Renderer* may look like this (The following code is set as the standard code in the projects ```java main``` function):

```java
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
        int widthPixels = 7680;
        int heightPixels = 4320;
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
```


## Planned Features

- Properly implementing angle transformation operations
- Properly implementing the stretch coefficient
- Implementing support for GPU rendering
- Video rendering
- Time dynamic fractal parameters
- Camera and lamp movement paths
- Control GUI
- Include more 3D fractals (e.g. Julia Quarterions, ...)
- Iclude support for user defined distance estimators -> own 3D fractal shapes

