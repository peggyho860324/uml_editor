package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;

import line.Line;
import obj.Obj;
import obj.Port;

public abstract class LineCreationMode extends Mode{
	private Obj startObj, endObj;
	private Point startP, endP;
	private Port startPort, endPort;
	protected String shapeType;
	private ShapeFactoryInterface factory = new ShapeFactory();
	
	public void mousePressed(MouseEvent e) 
	{	
		startP = e.getPoint();
		startObj = canvas.findClickedObj(startP);
		if(startObj != null)
		{
			startPort = startObj.findClickedPort(startP);
		}
	}
	
	public void mouseDragged(MouseEvent e) {}
	
	public void mouseReleased(MouseEvent e) 
	{
		if(startObj!=null && startPort!=null)
		{
			endP = e.getPoint();
			endObj = canvas.findClickedObj(endP);
			if(endObj != null)
			{
				endPort = endObj.findClickedPort(endP);
				if(endPort!=null && startObj!=endObj)
				{
					Line line = factory.createLine(shapeType, startObj, endObj, startPort, endPort);
					startPort.addLine(line);
					endPort.addLine(line);
					canvas.addLine(line);
					
					canvas.repaint();
				}
			}
		}
	}
}
