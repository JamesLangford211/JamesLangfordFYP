import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class WorkspaceUI extends JFrame{
	
	private String difficulty, levelName;
	private JFrame frame;
	private JPanel overall;
	private JPanel left;
	private JPanel flowchartWizard;
	private JPanel buttonContainer;
	
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
		overall.setLayout(new GridLayout());
		frame.setVisible(true);
		
		leftSetup();
		rightSetup();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
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
		
		left.setLayout(new BorderLayout());
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
		flowchartWizard.setLayout(new FlowLayout());
		left.add(flowchartWizard, BorderLayout.SOUTH);
		
		JTabbedPane tabs = new JTabbedPane();
		//tabs.setPreferredSize(new Dimension(left.getWidth(),300));
		JPanel moves = new JPanel();
		moves.setLayout(new FlowLayout());
		flowchartWizard.add(tabs);
		
		JButton addNode = new JButton("Add");
		flowchartWizard.add(addNode);
		addNode.setBackground(Color.GREEN);
		
		tabs.addTab("Add a Move",moves);
		JButton up = new JButton("up");
		moves.add(up);
		
		JButton down = new JButton("down");
		moves.add(down);
		
		JButton left = new JButton("left");
		moves.add(left);
		
		JButton right = new JButton("right");
		moves.add(right);
		
		JPanel decision = new JPanel();
		tabs.addTab("Make a decision", decision);
		decision.setLayout(new FlowLayout());
		JLabel ifLabel = new JLabel("If:");
		decision.add(ifLabel);
		
		JLabel notLabel = new JLabel("Not");
		decision.add(notLabel);
		JCheckBox not = new JCheckBox();
		decision.add(not);
		
		JComboBox<String> condition = new JComboBox<String>();
		condition.addItem("Wall is present:");
		condition.addItem("Just visited square:");
		decision.add(condition);
		
		JComboBox<String> potentialMoves = new JComboBox<String>();
		potentialMoves.addItem("Up");
		potentialMoves.addItem("Down");
		potentialMoves.addItem("Left");
		potentialMoves.addItem("Right");
		decision.add(potentialMoves);
		
		JPanel loop = new JPanel();
		tabs.addTab("Add a loop",loop);
		
		addNode.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	//this is where we get the information about the node we want to add
		    		//first get the tab that the user is accessing e.g. make a move, make a decision
		    	
		    		//if its on the move tab create a node representing a move and pass in that information
		    	
		    		//if its on the decision tab create a decision node
		    	
		    		//if its on the loop tab create an edge between two specified nodes
		    		
		    }
		});
		
	}
}
	