package obj;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class GroupObj extends Obj{ //不用Port,不用連線

	private ArrayList<Obj> childObjs;
	
	public GroupObj()
	{
		childObjs = new ArrayList<Obj>();
		
		depth = canvas.getDepth();
		canvas.setDepth(--depth);
		
		objType = "GroupObj";
	}
	
	public ArrayList<Obj> getChildObjs()
	{
		return childObjs;
	}
	
	public void addChildObj(Obj obj) 
	{
		childObjs.add(obj);
	}
	
	@Override
	public void setPosition(int offsetX, int offsetY) 
	{
		super.setPosition(offsetX, offsetY); // Set position of groupObj itself.
		
		for(Obj obj : childObjs) // Set position of childObjs.
		{
			obj.setPosition(offsetX, offsetY);
		}
	}
	
	@Override
	public void draw(Graphics g) 
	{
		for(Obj obj : childObjs)
		{
			obj.draw(g);
		}
		if(isSelected)
		{
			for(Port port : ports)
			{
				port.draw(g);
			}
		}
	}
	
	public void setAttributes(int x1, int y1, int x2, int y2)
	{
		leftUpper = new Point(x1,y1);
		width = x2-x1;
		height = y2-y1;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		
		ports = new Port[]{
	            new Port(this, new Point(x1+width/2,y1)), new Port(this, new Point(x1+width,y1+height/2)),
	            new Port(this, new Point(x1+width/2,y1+height)), new Port(this, new Point(x1, y1+height/2))
	        }; //N,E,S,W
	}

}
