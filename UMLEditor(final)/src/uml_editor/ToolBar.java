package uml_editor;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import mode.AssociationLineMode;
import mode.ClassMode;
import mode.CompositionLineMode;
import mode.GeneralizationLineMode;
import mode.Mode;
import mode.SelectionMode;
import mode.UseCaseMode;

public class ToolBar extends JPanel{
	private int rows = 6; //工具欄上的按鍵數量(列數)
	private int cols = 1; //工具欄行數
	private int hgap = 5; //horizontal gap
	private int vgap = 5; //vertical gap
	private ToolBtn pressedBtn = null;
	private Color grey = new Color(152,155,145);
	private Canvas canvas;
	
	
	public ToolBar() {
		canvas = Canvas.getInstance();
		setLayout(new GridLayout(rows, cols, hgap, vgap));
		this.setBackground(Color.WHITE);
		
		ToolBtn selectBtn = new ToolBtn("select", new ImageIcon("res/select.png"), new SelectionMode());
		add(selectBtn);
		
		ToolBtn associateBtn = new ToolBtn("association line", new ImageIcon("res/association_line.png"), new AssociationLineMode());
		add(associateBtn);
		
		ToolBtn generalBtn = new ToolBtn("generalization line", new ImageIcon("res/generalization_line.png"), new GeneralizationLineMode());
		add(generalBtn);
		
		ToolBtn compositeBtn = new ToolBtn("composition line", new ImageIcon("res/composition_line.png"), new CompositionLineMode());
		add(compositeBtn);
		
		ToolBtn classBtn = new ToolBtn("class", new ImageIcon("res/class.png"), new ClassMode());
		add(classBtn);
		
		ToolBtn usecaseBtn = new ToolBtn("use case", new ImageIcon("res/use_case.png"), new UseCaseMode());
		add(usecaseBtn);

	}
	
	private class ToolBtn extends JButton{
		private Mode toolMode;
		
		public ToolBtn(String toolText, ImageIcon icon, Mode mode) {
			toolMode = mode;
			
			setToolTipText(toolText);
			setIcon(icon);
			setFocusable(false);
			setBackground(Color.WHITE);
			setRolloverEnabled(true);
			addActionListener(new toolBtnListener());
		}
		
		class toolBtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(pressedBtn != null) //表示需要先取消前面的toolBtn
				{
					pressedBtn.setBackground(Color.WHITE);
					pressedBtn.setIcon(new ImageIcon("res/"+pressedBtn.toolMode.getFilePathString()+".png"));
				}
				pressedBtn = (ToolBtn) e.getSource();
				setIcon(new ImageIcon("res/"+toolMode.getFilePathString()+"_black.png"));
				setBackground(grey);
				canvas.setCurrentMode(toolMode);
				canvas.repaint();
			}
		}
	}

	
}
