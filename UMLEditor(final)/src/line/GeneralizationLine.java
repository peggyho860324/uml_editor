package line;

import java.awt.Color;
import java.awt.Graphics;

import obj.Obj;
import obj.Port;

public class GeneralizationLine extends Line{
	private int d, h;
	
	public GeneralizationLine(Obj fromObj, Obj toObj, Port fromPort, Port toPort) 
	{
		super(fromObj, toObj, fromPort, toPort);
		d = 12;
		h = 10;
	}
	
	@Override
	public void draw(Graphics g) {
		x1 = fromPort.getPosition().x;
		y1 = fromPort.getPosition().y;
		x2 = toPort.getPosition().x;
		y2 = toPort.getPosition().y;
		
		int dx = x2 - x1, dy = y2 - y1;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - d, xn = xm, ym = h, yn = -h, x;
	    double sin = dy / D, cos = dx / D;

	    x = xm*cos - ym*sin + x1;
	    ym = xm*sin + ym*cos + y1;
	    xm = x;

	    x = xn*cos - yn*sin + x1;
	    yn = xn*sin + yn*cos + y1;
	    xn = x;

	    g.setColor(Color.BLACK);
	    g.drawLine(x1, y1, (int)((xm+xn)/2), (int)((ym+yn)/2));
	    g.drawLine((int) xm, (int) ym, x2, y2);
	    g.drawLine((int) xn, (int) yn, x2, y2);
	    g.drawLine((int) xm, (int) ym, (int) xn, (int) yn);
	}

}
