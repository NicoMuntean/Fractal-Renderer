package logic.visualization;

import java.util.ArrayList;
import logic.objects.RenderableObject;
import logic.shading.objects.LightSource;
import logic.shading.objects.PointLamp;


public class Scene {
    
private ArrayList<RenderableObject> sceneObjects = new ArrayList<RenderableObject>();
private PointLamp primaryLamp;


/*
* Sets up a renderable Scene with the possibility to already insert a primary lightsource as well as renderable objects
*/
public Scene(ArrayList<RenderableObject> inputObjs, PointLamp ls) {
        
        primaryLamp = ls;
        sceneObjects = inputObjs;
        
}


/*
* Creates an empty Scene, this Scene is NOT renderable until a primary lightsource as well as an ensemble of renderable Objects are inserted
*/
public Scene() {
    
}

/*
* Getter & Setter methods
*/
public void setPrimaryLightsource(PointLamp ls) {
    primaryLamp = ls;
}

public void addRenderableObj(RenderableObject nObj) {
    sceneObjects.add(nObj);
}

public void setAllSceneObjects(ArrayList<RenderableObject> nSceneObj) {
    sceneObjects = nSceneObj;
}

public PointLamp getPrimaryLightsource() {
    return primaryLamp;
}

public ArrayList<RenderableObject> getAllSceneObj() {
    return sceneObjects;
}

public int getNumberOfObjects() {
    return sceneObjects.size();
}

/*
* Returns the Renderable Object at the List position iD, if iD > size of the list, the last object is returned instead
*/
public RenderableObject getSceneObjAtID(int iD) {
    
    if(iD < sceneObjects.size()) {
        return sceneObjects.get(iD);
    }
    else {
        System.out.println("ERROR! THE INDEX IS LARGER THAN THE NUMBER OF OBJECTS IN THE SCENE! RETURNED LAST OBJECTS OF THE LIST INSTEAD!");
        return sceneObjects.get(sceneObjects.size() - 1);
    }      
}

}
