package uml_editor;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class UMLEditor extends JFrame{
	
	private ToolBar toolbar;
	private Canvas canvas;
	private MenuBar menubar;
	private int bl_hgap = 6; // horizontal gap of border layout
	private int bl_vgap = 6; // vertical gap of border layout
	
	public UMLEditor() {
		canvas = Canvas.getInstance();
		toolbar = new ToolBar();
		menubar = new MenuBar();
		
		BorderLayout bl = new BorderLayout();
		getContentPane().setLayout(bl);
		getContentPane().add(menubar, BorderLayout.NORTH);
		getContentPane().add(toolbar, BorderLayout.WEST);
		getContentPane().add(canvas, BorderLayout.CENTER);
		bl.setHgap(bl_hgap); 
		bl.setVgap(bl_vgap);
		getContentPane().setBackground(Color.WHITE);
    }
	
	public static void main(String[] args) {
		JFrame mainWindow = new UMLEditor();
        mainWindow.setTitle("UML Editor");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(1000, 800);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
	}

}
