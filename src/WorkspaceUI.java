import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WorkspaceUI extends JFrame{
	
	private String difficulty, levelName;
	private JFrame frame;
	private JPanel overall;
	private JPanel left;
	private JPanel flowchartWizard;
	
	public WorkspaceUI(String difficulty, String levelName){
		
		this.difficulty = difficulty;
		this.levelName = levelName;
		initialise();
	}
	
	private void initialise(){
		frame = new JFrame("Maze Game!");
		overall = new JPanel();
		frame.setLayout(new BorderLayout());
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		overall.setBackground(Color.CYAN);
		frame.add(overall, BorderLayout.CENTER);
		overall.setLayout(new GridLayout(1,2));
		
		leftSetup();
		rightSetup();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	private void rightSetup(){
		JPanel right = new JPanel();
		right.setBackground(Color.YELLOW);
		right.setPreferredSize(new Dimension(frame.getHeight(),(int)frame.getWidth()/2));
		overall.add(right);
		
		right.setLayout(new BorderLayout());
		JPanel mazeCanvas = new MazeCanvas(new Maze(difficulty,levelName));
		mazeCanvas.setPreferredSize(mazeCanvas.getPreferredSize());
		right.add(mazeCanvas, BorderLayout.NORTH);
		JPanel mazeControls = new JPanel();
		right.add(mazeControls, BorderLayout.SOUTH);
	}
	
	private void leftSetup(){
		left = new JPanel();
		left.setBackground(Color.GREEN);
		left.setPreferredSize(new Dimension(frame.getHeight(),(int)frame.getWidth()/2));
		overall.add(left);
		
		JPanel flowchart = new JPanel();
		
		left.setLayout(new GridLayout(2,1));
		left.add(flowchart);
		
		JLabel l1 = new JLabel("flowchartArea");
		l1.setForeground(Color.white);
		l1.setFont(new Font("Serif", Font.BOLD, 40));
		flowchart.setBackground(Color.DARK_GRAY);
		flowchart.add(l1);
		

		flowchartWizardSetup();
		
	}
	
	private void flowchartWizardSetup(){
		flowchartWizard = new JPanel();
		left.add(flowchartWizard);
		JPanel buttonContainer = new JPanel();
		
		flowchartWizard.setLayout(new CardLayout());
		
		JButton moveInstruction = new JButton("Add a move");
		flowchartWizard.add(moveInstruction);
		
		moveInstruction.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
				movePanel();
				flowchartWizard.remove(moveInstruction);
			    
			  }
			});
	}
	
	private void movePanel(){
		JPanel movesPanel = new JPanel();
		movesPanel.setLayout(new CardLayout());
		JButton up = new JButton("Up");
		JButton down = new JButton("Down");
		JButton left = new JButton("Left");
		JButton right = new JButton("Right");
		
		movesPanel.add(up);
		movesPanel.add(down);
		movesPanel.add(left);
		movesPanel.add(right);
		
		
		flowchartWizard.add(movesPanel);
		
		
	}
}
	