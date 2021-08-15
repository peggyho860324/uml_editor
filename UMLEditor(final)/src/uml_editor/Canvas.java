package uml_editor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import line.Line;
import mode.Mode;
import obj.GroupObj;
import obj.Obj;

public class Canvas extends JPanel{

	private static Canvas canvas = null;
	
	private EventListener listener = null;
	private Mode currentMode = null;
	private Map<Integer, Obj> objs = new HashMap<>(); //pair : (depth_value, Obj)
	private ArrayList<Obj> preGroupedObjs = new ArrayList<Obj>();
	private ArrayList<Line> lines = new ArrayList<Line>();
	private int currentDepth = 99;
	private int maxDepth = 99;
	private int minDepth = 0;
	private Color rose = new Color(214, 188, 198);
	
	private Canvas() {}
	
	public static Canvas getInstance() 
	{
		if (canvas == null) 
		{
			canvas = new Canvas();
		}
		return canvas;
	}
	
	public void addObj(int depth, Obj obj) 
	{
		objs.put(depth, obj);
	}
	
	public void setCurrentMode(Mode mode) 
	{
		currentMode = mode;
		
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
		listener = currentMode;
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	public void groupObjs() 
	{
		if(preGroupedObjs.size()>1)
		{
			GroupObj groupObj = new GroupObj();
			
			int lu_x = 1382, lu_y = 765, rl_x = 0, rl_y = 0; //left_upper_x, left_upper_y, right_lower_x, right_lower_y
			for (Obj obj : preGroupedObjs) 
			{
				groupObj.addChildObj(obj);
				
				if(obj.getX1()<lu_x) 
					lu_x = obj.getX1();
				if(obj.getX2()>rl_x)
					rl_x = obj.getX2();
				if(obj.getY1()<lu_y)
					lu_y = obj.getY1();
				if(obj.getY2()>rl_y)
					rl_y = obj.getY2();
				
				obj.unselect();
				objs.remove(obj.getDepth());
				
			}
			groupObj.setAttributes(lu_x, lu_y, rl_x, rl_y);
			
			addObj(groupObj.getDepth(), groupObj);
			groupObj.select();
		}
		
		preGroupedObjs.clear();
	}
	
	public void ungroupObjs() 
	{
		ArrayList<Obj> list = new ArrayList<Obj>();
		
		for(int depth=minDepth; depth<=maxDepth; depth++)
        {
        	if(objs.containsKey(depth))
        	{
        		Obj obj = objs.get(depth);
        		
        		if(obj.getIsSelected())
        		{
        			list.add(obj);
        		}
        	}
        }
		
		if(list.size()==1)
		{
			Obj obj = list.get(0);
			if(obj.getObjType()=="GroupObj")
			{
				ArrayList<Obj> childObjs = ((GroupObj)obj).getChildObjs();
				for(Obj childObj : childObjs)
				{
					addObj(childObj.getDepth(), childObj);
				}
				objs.remove(obj.getDepth());
			}
		}
	}
	
	@Override
    public void paint(Graphics g) 
	{ 
        g.setColor(rose);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // 畫Obj及它的Port(若isSelected為true)
        for(int depth=maxDepth; depth>=minDepth; depth--)
        {
        	if(objs.containsKey(depth))
        	{
        		objs.get(depth).draw(g);
        	}
        }
        
        // 畫Line
        for(Line line : lines)
        {
        	line.draw(g);
        }
    }
	
	public void addLine(Line line)
	{
		lines.add(line);
	}
	
	public int getDepth()
	{
		return currentDepth;
	}
	
	public void setDepth(int depth)
	{
		currentDepth = depth;
	}
	
	public int getMaxDepth()
	{
		return maxDepth;
	}
	
	public int getMinDepth()
	{
		return minDepth;
	}
	
	public Map<Integer, Obj> getObjs()
	{
		return objs;
	}
	
	public void addPreGroupedObj(Obj selectedObj)
	{
		preGroupedObjs.add(selectedObj);
	}
	
	public ArrayList<Obj> getPreGroupedObjs()
	{
		return preGroupedObjs;
	}
	
	public void unselectAll()
	{
		for(int depth=minDepth; depth<=maxDepth; depth++)
        {
        	if(objs.containsKey(depth))
        	{
        		objs.get(depth).unselect();
        	}
        }
		preGroupedObjs.clear();
	}
	
	public ArrayList<Obj> getSeletedObjs()
	{
		ArrayList<Obj> selectedObjs = new ArrayList<Obj>();
		
		for(int depth=minDepth; depth<=maxDepth; depth++)
        {
        	if(objs.containsKey(depth))
        	{
        		Obj obj = objs.get(depth);
        		if(obj.getIsSelected())
        		{
        			selectedObjs.add(obj);
        		}
        	}
        }
		
		return selectedObjs;
	}
	
	//判斷滑鼠點擊事件是落在哪個物件上
	public Obj findClickedObj(Point p)
	{
		Obj checkedObj;	
					
		for(int depth=minDepth; depth<=maxDepth; depth++)
		{
			if(objs.containsKey(depth))
			{
			    checkedObj = objs.get(depth);
			        		
			    if(checkedObj.isInside(p)) 
			    {       			
			        return checkedObj;
			    }
			}
	    }
					
		return null;
	}	
	
}
