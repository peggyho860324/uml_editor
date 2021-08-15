package uml_editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import obj.Obj;

public class MenuBar extends JMenuBar{
	private Canvas canvas;
	private ArrayList<Obj> objs = new ArrayList<>(); //取得目前畫布上選取到的物件
	
	public MenuBar() 
	{
		canvas = Canvas.getInstance(); 

		JMenu fileMenu = new JMenu("File");
		add(fileMenu);

		JMenu editMenu = new JMenu("Edit");
		add(editMenu);
		
		JMenuItem menuItem = new JMenuItem("Change object name");
		editMenu.add(menuItem);
		menuItem.addActionListener(new ChangeNameListener());
		
		menuItem = new JMenuItem("Group");
		editMenu.add(menuItem);
		menuItem.addActionListener(new GroupListener());
		
		menuItem = new JMenuItem("Ungroup");
		editMenu.add(menuItem);
		menuItem.addActionListener(new UngroupListener());
	}
	
	class ChangeNameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			objs = canvas.getSeletedObjs(); 
			
			if(objs.size() == 1) {
                String response = JOptionPane.showInputDialog("Enter the new name");
                if(response!=null)
                	objs.get(0).changeName(response);
            }
            canvas.repaint();
            canvas.unselectAll();
		}
	}
	
	class GroupListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.groupObjs();
			canvas.repaint();
		}
	}
	
	class UngroupListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			canvas.ungroupObjs();
			canvas.repaint();
		}
	}
	
}
