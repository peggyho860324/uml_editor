package obj;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import uml_editor.Canvas;

public abstract class Obj {
	protected String name = null;
	protected Port[] ports;
	protected int width, height;
	protected int x1,x2,y1,y2; //(x1,y1)為左上角座標, (x2,y2)為右下角座標
	protected int depth; //物件於畫布上的深度值
	protected Canvas canvas = Canvas.getInstance();
	protected Point leftUpper;
	protected boolean isSelected = false;
	protected String objType;
	protected Font monospaced = new Font(Font.MONOSPACED, Font.BOLD, 15);
	private int portOffset = 30;
	
	public void changeName(String name) 
	{
		this.name = name;
	}
	
	public String getObjType()
	{
		return objType;
	}
	
	public int getDepth() 
	{
		return depth;
	}
	
	public abstract void draw(Graphics g);
	
	public boolean getIsSelected()
	{
		return isSelected;
	}

	public void unselect() 
	{
		isSelected = false;
	}
	
	public void select() 
	{
		isSelected = true;
	}
	
	public int getX1()
	{
		return x1;
	}
	
	public int getX2()
	{
		return x2;
	}
	
	public int getY1()
	{
		return y1;
	}
	
	public int getY2()
	{
		return y2;
	}
	
	public void setPosition(int offsetX, int offsetY) 
	{
		x1+=offsetX;
		y1+=offsetY;
		x2+=offsetX;
		y2+=offsetY;
		leftUpper = new Point(x1,y1);
		
		for(Port port : ports)
		{
			port.setPosition(offsetX, offsetY);
		}
	}
	
	public boolean isInside(Point p)
	{
		int x1 = getX1();
		int y1 = getY1();
		int x2 = getX2();
		int y2 = getY2();
		
		if(p.x>=x1 && p.x<=x2 && p.y>=y1 && p.y<=y2)
		{
			return true;
		}
		else
		{
			return false;
		}	
	}
	
	// 判斷點p落在哪個port的周圍
	public Port findClickedPort(Point p)
	{
		for(Port port : ports)
		{
			Point portLocation = port.getPosition();
			int port_x1 = portLocation.x-portOffset;
			int port_y1 = portLocation.y-portOffset;
			int port_x2 = portLocation.x+portOffset;
			int port_y2 = portLocation.y+portOffset;
			
			if(p.x>=port_x1 && p.x<=port_x2 && p.y>=port_y1 && p.y<=port_y2)
			{
				return port;
			}
		}
		
		return null;
	}
	
}
