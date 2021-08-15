package mode;

import java.awt.Point;

import line.Line;
import obj.Obj;
import obj.Port;

public interface ShapeFactoryInterface { 
	
	public Obj createObj(String objType, Point p);
	public Line createLine(String lineType, Obj startObj, Obj endObj, Port startPort, Port endPort);

}

//�Ѯv��ĳ : �Ҧ����󪺫إ߳��n���@�ӿW�ߪ�interface�A�o�˴N���U��class���Y�N���|��if/else statement
