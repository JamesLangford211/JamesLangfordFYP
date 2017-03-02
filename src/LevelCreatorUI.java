import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelCreatorUI {
	
	private JFrame frame;
	private JPanel creatorPanel;
	private int width, height;
	private ArrayList<ArrayList<JPanel>> mazeCreator;
	
	public LevelCreatorUI(){
		width = height = 7;
		frame = new JFrame("Maze Game!");
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		creatorPanel = new JPanel();
		mazeCreator = new ArrayList<ArrayList<JPanel>>();
		
		JPanel overallPanel = new JPanel();
		JPanel north = new JPanel();
		JPanel south = new JPanel();
		JButton export = new JButton("Export");
		
		export.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    exportMaze();
		  }
		});
		
		creatorPanel.setLayout(new GridLayout(width,height));
		
		frame.add(overallPanel);
		frame.setVisible(true);
		frame.add(overallPanel);
		
		overallPanel.setLayout(new BorderLayout());
		Dimension northSize = new Dimension(overallPanel.getWidth(),(int) (overallPanel.getHeight()*0.9));
		Dimension southSize = new Dimension(overallPanel.getWidth(), (int)(overallPanel.getHeight()*0.1));
		
		overallPanel.add(north,BorderLayout.NORTH);
		overallPanel.add(south,BorderLayout.SOUTH);
		
		north.add(creatorPanel);
		south.add(export);
		
		
		initPanels();
		drawPanels();
		
		frame.setVisible(true);	
	}
	
	private void initPanels(){
		for(int y = 0; y<height; y++){
			ArrayList<JPanel> row = new ArrayList<JPanel>();
			for(int x = 0; x<width; x++){
				JPanel newPanel = new JPanel();
				
				newPanel.setBorder(BorderFactory.createLineBorder(Color.black));
				
				if(x == 0 && y == 0){
					newPanel.setBackground(Color.RED); 
					newPanel.add(new JLabel("Start"));
				}
				else if(x == width-1 && y == height-1){
					newPanel.setBackground(Color.GREEN);
					newPanel.add(new JLabel("End"));
				}
				else{
					newPanel.setBackground(Color.WHITE);
				}
				
				newPanel.addMouseListener(new MouseAdapter() {
				    @Override
				    public void mouseClicked(MouseEvent e) {
				    	if(newPanel.getBackground().equals(Color.WHITE)){
				        	newPanel.setBackground(Color.BLACK);
				        }else if(newPanel.getBackground().equals(Color.BLACK)){
				        	newPanel.setBackground(Color.WHITE);
				        }
				    }
				});
				
				row.add(newPanel);
			}
			mazeCreator.add(row);
		}
	}

	private void drawPanels(){
		for(int i = 0; i<mazeCreator.size(); i++){
			for(int k = 0; k<mazeCreator.get(i).size(); k++){
				creatorPanel.add(mazeCreator.get(i).get(k));
			}
		}
	}
	
	private void exportMaze(){
		//check to see if there are any black squares at all on the grid.
		//check to see if the maze is solvable
		
		//if it passes both tests, export the file
		File fileToSave = stringToFile("src/assets/userLevels/");
	}
	
	private File stringToFile(String directory){
		File file = null;
		try {
			file = new File("src\\assets\\userLevels\\fileName.csv");
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(mazeToString());
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return file;
	}
	private String mazeToString(){
		String output = "";
		for(int y = 0; y<mazeCreator.size(); y++){
			ArrayList<String> row = new ArrayList<String>();
			for(int x = 0; x<mazeCreator.get(y).size(); x++){
				if(x == y && y == 0){
					output+="s,";
				} else if(y == mazeCreator.size()-1 && x == mazeCreator.get(y).size()-1){
					output+="e";
				} else if(isWall(mazeCreator.get(y).get(x))){
					output+="w,";
				} else if(!isWall(mazeCreator.get(y).get(x))){
					output+="p,";
				}
			}
			output+="\n";
			
		}
		return output;
	}
	
	private boolean isWall(JPanel panel){
		boolean isWall = false;
		return isWall = (panel.getBackground() == Color.BLACK) ? true : false;
	}
}
