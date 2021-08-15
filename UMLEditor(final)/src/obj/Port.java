package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import line.Line;

public class Port {
	private Obj parentObj;
	private Point p; // ��port���y��
	private ArrayList<Line> lines; //�����q��Port�s�X�h���u�γs�즹Port���u
    static final private int width = 20;
    static final private int height = 20;
	
	public Port(Obj parentObj, Point p)
	{
		this.parentObj = parentObj;
		this.p = p;
		lines = new ArrayList<Line>();
	}
	
	public void addLine(Line line) 
	{
		lines.add(line);
	}
	
	public void draw(Graphics g) 
	{
        g.setColor(Color.GRAY);
        g.fillRect(p.x - 10, p.y - 10, width, height);
    }
	
	public void setPosition(int offsetX, int offsetY)
	{
		p.x += offsetX;
		p.y += offsetY;
	}
	
	public Point getPosition() 
	{
		return p;
	}
}
