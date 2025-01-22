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
$$\frac{\log\left(\left|\vc{r}_{p}\right|\right)}{2} $$
Optical depth is achieved by employing the [Phong reflection model](https://en.wikipedia.org/wiki/Phong_reflection_model) in combination with a crude [Ray Tracing](https://en.wikipedia.org/wiki/Ray_tracing_(graphics)) implementation to simulate physical shadows.


## Planned Features


