package mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Map;

import obj.Obj;

public class SelectionMode extends Mode{
	
	private Obj draggedObj;
	private Point startP, endP;
	
	public SelectionMode() 
	{
		filePathString = "select";
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		canvas.unselectAll();
		
		Obj obj = canvas.findClickedObj(e.getPoint());

		if(obj != null)
		{
			obj.select();
		}
		
		canvas.repaint();
	}
	
	public void mousePressed(MouseEvent e) 
	{	
		canvas.unselectAll();
		
		draggedObj = canvas.findClickedObj(e.getPoint());
		
		if(draggedObj != null)
		{
			startP = e.getPoint();
		}
		else //嘗試select多個物件
		{
			startP = e.getPoint();
		}
		
	}
	
	public void mouseDragged(MouseEvent e)
	{
		if(draggedObj != null)
		{
			endP = e.getPoint();
			int offsetX = endP.x - startP.x;
			int offsetY = endP.y - startP.y;
			draggedObj.setPosition(offsetX, offsetY); //改物件,Port,線條的位置
			
			canvas.repaint();
			startP = e.getPoint();
		}
	}
	
	public void mouseReleased(MouseEvent e)
	{
		if(draggedObj != null)
		{
			endP = e.getPoint();
			int offsetX = endP.x - startP.x;
			int offsetY = endP.y - startP.y;
			draggedObj.setPosition(offsetX, offsetY);
			
			canvas.repaint();
		}
		else
		{
			endP = e.getPoint();
			
			//考慮四種情況 : 左上到右下(原本預設的情形)/右上到左下/左下到右上/右下到左上
			int x1 = startP.x;
			int y1 = startP.y;
			int x2 = endP.x;
			int y2 = endP.y;
			
			if(x1>x2 && y1<y2) //右上到左下
			{
				startP = new Point(x2,y1);
				endP = new Point(x1,y2);
			}
			else if(x1<x2 && y1>y2) //左下到右上
			{
				startP = new Point(x1,y2);
				endP = new Point(x2,y1);
			}
			else if(x1>x2 && y1>y2) //右下到左上
			{
				startP = new Point(x2,y2);
				endP = new Point(x1,y1);
			}
			
			Map<Integer, Obj> objs = canvas.getObjs();
			for(int depth=minDepth; depth<=maxDepth; depth++)
	        {
	        	if(objs.containsKey(depth))
	        	{
	        		Obj obj = objs.get(depth);
	        		int obj_x1 = obj.getX1();
	        		int obj_y1 = obj.getY1();
	        		int obj_x2 = obj.getX2();
	        		int obj_y2 = obj.getY2();
	        		
	        		if(obj_x1>=startP.x && obj_x2<=endP.x && obj_y1>=startP.y && obj_y2<=endP.y)
	        		{
	        			canvas.addPreGroupedObj(obj);
	        			obj.select();
	        		}
	        	}
	        }
			
			if(canvas.getPreGroupedObjs().size()==0)
			{
				canvas.unselectAll();
			}
			
			canvas.repaint();
		}
	}
	
}
