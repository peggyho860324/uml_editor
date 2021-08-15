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
		else //����select�h�Ӫ���
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
			draggedObj.setPosition(offsetX, offsetY); //�磌��,Port,�u������m
			
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
			
			//�Ҽ{�|�ر��p : ���W��k�U(�쥻�w�]������)/�k�W�쥪�U/���U��k�W/�k�U�쥪�W
			int x1 = startP.x;
			int y1 = startP.y;
			int x2 = endP.x;
			int y2 = endP.y;
			
			if(x1>x2 && y1<y2) //�k�W�쥪�U
			{
				startP = new Point(x2,y1);
				endP = new Point(x1,y2);
			}
			else if(x1<x2 && y1>y2) //���U��k�W
			{
				startP = new Point(x1,y2);
				endP = new Point(x2,y1);
			}
			else if(x1>x2 && y1>y2) //�k�U�쥪�W
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
