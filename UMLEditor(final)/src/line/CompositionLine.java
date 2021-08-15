package line;

import java.awt.Color;
import java.awt.Graphics;

import obj.Obj;
import obj.Port;

public class CompositionLine extends Line{
	private int diamond_w, diamond_h;

	public CompositionLine(Obj fromObj, Obj toObj, Port fromPort, Port toPort) 
	{
		super(fromObj, toObj, fromPort, toPort);
		diamond_w = 10;
		diamond_h = 10;
		
	}
	
	@Override
	public void draw(Graphics g) {
		x1 = fromPort.getPosition().x;
		y1 = fromPort.getPosition().y;
		x2 = toPort.getPosition().x;
		y2 = toPort.getPosition().y;
		
		int dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - diamond_w, xn = xm, ym = diamond_h, yn = -diamond_h, x;
		double sin = dy/D, cos = dx/D;
		
		x = xm*cos - ym*sin + x1;
        ym = xm*sin + ym*cos + y1;
        xm = x;

        x = xn*cos - yn*sin + x1;
        yn = xn*sin + yn*cos + y1;
        xn = x;
        
        double xq = (diamond_h*2/D)*x1 + ((D-diamond_h*2)/D)*x2;
        double yq = (diamond_h*2/D)*y1 + ((D-diamond_h*2)/D)*y2;
   
        int[] xpoints = {x2, (int) xm, (int) xq, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yq, (int) yn};

        g.setColor(Color.BLACK);
        g.drawLine(x1, y1, (int) xq, (int) yq);
        g.drawPolygon(xpoints, ypoints, 4);
		
	}

}
