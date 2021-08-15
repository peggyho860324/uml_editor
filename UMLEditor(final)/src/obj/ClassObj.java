package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class ClassObj extends Obj{
	
	public ClassObj(Point leftUpper) 
	{	
		this.leftUpper = leftUpper;
		width = 125;
		height = 150;
		x1 = leftUpper.x;
		y1 = leftUpper.y;
		x2 = x1 + width;
		y2 = y1 + height;
		
		depth = canvas.getDepth();
		canvas.setDepth(--depth);
		
		ports = new Port[]{
	            new Port(this, new Point(x1+width/2,y1)), new Port(this, new Point(x1+width,y1+height/2)),
	            new Port(this, new Point(x1+width/2,y1+height)), new Port(this, new Point(x1, y1+height/2))
	        }; //N,E,S,W
		
		objType = "ClassObj";
	}
	
	public void draw(Graphics g) 
	{
		int portion = height / 3;
		
		g.setColor(Color.WHITE);
		g.fillRect(x1, y1, width, height/3);
		g.fillRect(x1, y1 + portion, width, height/3);
		g.fillRect(x1, y1 + portion * 2, width, height/3);
		
		g.setColor(Color.BLACK);
		g.drawRect(x1, y1, width, height);
		g.drawLine(x1, y1 + portion, x2, y1 + portion);
		g.drawLine(x1, y1 + portion * 2, x2, y1 + portion * 2);
		
		if(name!=null && name!="")
		{
			int stringWidth = g.getFontMetrics(monospaced).stringWidth(name);
			double empty = (Math.abs(x1-x2) - stringWidth)/2;
			g.setFont(monospaced);
			g.setColor(Color.BLACK);
			g.drawString(name, x1 + (int)empty, y1 + 25);
		}
		
		if(isSelected)
		{
			for(Port port : ports)
			{
				port.draw(g);
			}
		}
	}
}
