package mode;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import uml_editor.Canvas;

public abstract class Mode implements MouseListener, MouseMotionListener{
	protected Canvas canvas = Canvas.getInstance();
	
	protected String filePathString;
	protected int maxDepth = canvas.getMaxDepth();
	protected int minDepth = canvas.getMinDepth();
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public String getFilePathString() 
	{
		return filePathString;
	}
}
