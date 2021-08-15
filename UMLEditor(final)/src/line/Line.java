package line;

import java.awt.Graphics;

import obj.Obj;
import obj.Port;

public abstract class Line {
	protected Port fromPort, toPort;
	protected Obj fromObj, toObj;
	protected int x1,y1,x2,y2; // 用於繪製箭頭的座標
	
	abstract public void draw(Graphics g); 
	
	public Line(Obj fromObj, Obj toObj, Port fromPort, Port toPort) 
	{
		this.fromObj = fromObj;
		this.toObj = toObj;
		this.fromPort = fromPort;
		this.toPort = toPort;
	}
	
}
