import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorkspaceUI extends JFrame{
	
	private String difficulty, levelName;
	
	public WorkspaceUI(String difficulty, String levelName){
		
		this.difficulty = difficulty;
		this.levelName = levelName;
		initialise();
	}
	
	private void initialise(){
		JFrame frame = new JFrame("Maze Game!");
		frame.setLayout(new BorderLayout());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
		JPanel overall = new JPanel();
		overall.setBackground(Color.CYAN);
		frame.add(overall, BorderLayout.CENTER);
		overall.setLayout(new GridLayout(1,2));
		
		JPanel left = new JPanel();
		left.setBackground(Color.GREEN);
		left.setPreferredSize(new Dimension(frame.getHeight(),(int)frame.getWidth()/2));
		overall.add(left);
		
		JPanel right = new JPanel();
		right.setBackground(Color.YELLOW);
		right.setPreferredSize(new Dimension(frame.getHeight(),(int)frame.getWidth()/2));
		overall.add(right);
		
		JPanel flowchart = new JPanel();
		JPanel flowchartWizard = new JPanel();
		left.setLayout(new GridLayout(2,1));
		left.add(flowchart);
		left.add(flowchartWizard);
		
		JLabel l1 = new JLabel("flowchartArea");
		l1.setForeground(Color.white);
		l1.setFont(new Font("Serif", Font.BOLD, 40));
		
		JLabel l2 = new JLabel("flowchartWizardArea");
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("Serif", Font.BOLD, 40));
		
		flowchart.setBackground(Color.DARK_GRAY);
		flowchartWizard.setBackground(Color.gray);
		
		flowchart.add(l1);
		flowchartWizard.add(l2);
		
		right.setLayout(new BorderLayout());
		JPanel mazeCanvas = new MazeCanvas(new Maze(difficulty,levelName));
		mazeCanvas.setPreferredSize(mazeCanvas.getPreferredSize());
		right.add(mazeCanvas, BorderLayout.NORTH);
		JPanel mazeControls = new JPanel();
		//mazeControls.setPreferredSize(new Dimension(right.getHeight()
		right.add(mazeControls, BorderLayout.SOUTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
	