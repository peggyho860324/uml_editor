package mode;

import java.awt.Point;

import line.AssociationLine;
import line.CompositionLine;
import line.GeneralizationLine;
import line.Line;
import obj.ClassObj;
import obj.Obj;
import obj.Port;
import obj.UseCaseObj;

public class ShapeFactory implements ShapeFactoryInterface{
	
	public Obj createObj(String objType, Point p) {
		if(objType.equals("ClassObj")){
			return new ClassObj(p);
		}
		else if(objType.equals("UseCaseObj")){
			return new UseCaseObj(p);
		}
		
		return null;
	}

	public Line createLine(String lineType, Obj startObj, Obj endObj, Port startPort, Port endPort) {
		if(lineType.equals("AssociationLine")){
			return new AssociationLine(startObj, endObj, startPort, endPort);
		}
		else if(lineType.equals("GeneralizationLine")){
			return new GeneralizationLine(startObj, endObj, startPort, endPort);
		}
		else if(lineType.equals("CompositionLine")) {
			return new CompositionLine(startObj, endObj, startPort, endPort);
		}
		
		return null;
	}
}
