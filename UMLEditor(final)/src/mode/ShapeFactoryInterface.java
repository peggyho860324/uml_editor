package mode;

import java.awt.Point;

import line.Line;
import obj.Obj;
import obj.Port;

public interface ShapeFactoryInterface { 
	
	public Obj createObj(String objType, Point p);
	public Line createLine(String lineType, Obj startObj, Obj endObj, Port startPort, Port endPort);

}

//老師建議 : 所有物件的建立都要有一個獨立的interface，這樣就底下的class裡頭就不會有if/else statement
