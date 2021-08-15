package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class UseCaseObj extends Obj{

	public UseCaseObj(Point leftUpper)
	{
		this.leftUpper = leftUpper;
		this.width = 175;
		this.height = 140;
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
		
		objType = "UseCaseObj";
	}
	
	public void draw(Graphics g) 
	{
		g.setColor(Color.WHITE);
		g.fillOval(x1, y1, width, height);
		
		g.setColor(Color.BLACK);
		g.drawOval(x1, y1, width, height);
		
		if(name!=null && name!="")
		{
			g.setFont(monospaced);
			g.setColor(Color.BLACK);
			int stringWidth = g.getFontMetrics(monospaced).stringWidth(name);
			double empty = (Math.abs(x1-x2) - stringWidth)/2;
			g.drawString(name, x1 + (int)empty, y1 + 50);
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
