package mode;


import java.awt.event.MouseEvent;
import obj.Obj;

public abstract class ObjCreationMode extends Mode{
	protected String shapeType;
	private ShapeFactoryInterface factory = new ShapeFactory();

	@Override
	public void mousePressed(MouseEvent e) 
	{
		Obj obj = factory.createObj(shapeType, e.getPoint());
		canvas.addObj(obj.getDepth(), obj);
		
		canvas.repaint();
	}
}
