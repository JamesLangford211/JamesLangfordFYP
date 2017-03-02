import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class MainMenuUI extends JFrame{
	
	private String easyUrlString = "src/assets/easyPuzzles.png";
	private String mediumUrlString = "src/assets/mediumPuzzles.png";
	private String hardUrlString = "src/assets/hardPuzzles.png";
			
	
	public MainMenuUI(){
			try {
				initialise();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
	
	private void initialise() throws IOException{
		
		JMenuBar menuBar;
		JMenu file, options, about;
		
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		file = new JMenu("File");
		options = new JMenu("Options");
		about = new JMenu("About");
		
		menuBar.add(file);
		menuBar.add(options);
		menuBar.add(about);
		
		//initialise the frame containing the 
		JFrame frame = new JFrame("Maze Game!");
		frame.setJMenuBar(menuBar);
		frame.setLayout(new GridLayout(2,3));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JPanel easy = new JPanel();
		JPanel medium = new JPanel();
		JPanel hard = new JPanel();
		JPanel load = new JPanel();
		JPanel levelCreator = new JPanel();
		JPanel help = new JPanel();
		
		frame.add(easy);
		frame.add(medium);
		frame.add(hard);
		frame.add(load);
		frame.add(levelCreator);
		frame.add(help);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		double prefWidth = frame.getWidth()/3;
		double prefHeight = frame.getHeight()/2;
		Dimension prefDim = new Dimension();
		prefDim.setSize(prefHeight, prefWidth);
		
		//easy panel
		easy.setPreferredSize(prefDim);
		Image easyImg = ImageIO.read(new File(easyUrlString)); 
		Image scaledEasy = easyImg.getScaledInstance((int)easy.getWidth(),(int)easy.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon easyIcon = new ImageIcon(scaledEasy);
		easy.add(new JLabel(easyIcon));
		
		//medium panel
		medium.setPreferredSize(prefDim);
		Image mediumImg = ImageIO.read(new File(mediumUrlString)); 
		Image scaledMed = mediumImg.getScaledInstance((int)medium.getWidth(),(int)medium.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon mediumIcon = new ImageIcon(scaledMed);
		medium.add(new JLabel(mediumIcon));
		
		
		//hard panel
		hard.setPreferredSize(prefDim);
		Image hardImg = ImageIO.read(new File(hardUrlString)); 
		Image scaledHard = hardImg.getScaledInstance((int)hard.getWidth(),(int)hard.getHeight(),Image.SCALE_SMOOTH);
		ImageIcon hardIcon = new ImageIcon(scaledHard);
		hard.add(new JLabel(hardIcon));
		
		//load panel
		load.add(new JLabel("load"));
		load.setBackground(Color.CYAN);
		
		//leaderboards panel
		levelCreator.add(new JLabel("Level Creator"));
		levelCreator.setBackground(Color.CYAN);
		
		//help panel
		help.add(new JLabel("help"));
		help.setBackground(Color.CYAN);
		
		
		
		easy.addMouseListener(new MouseAdapter() {
			@Override
	    	public void mouseClicked(MouseEvent e) {
	        	System.out.println("Easy Clicked");
	        	WorkspaceUI ui = new WorkspaceUI("easy","level1");
	    	}
		});
		medium.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        System.out.println("Medium Clicked");
		    }
		});
		
		hard.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        System.out.println("Hard Clicked");
		    }
		});
		
		levelCreator.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        LevelCreatorUI lcUI = new LevelCreatorUI();
		    }
		});
		
		load.addMouseListener(new MouseAdapter() {
			@Override
	    	public void mouseClicked(MouseEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("src/assets/levels/userCreatedLevels/"));
			int result = fileChooser.showOpenDialog(frame);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    WorkspaceUI ui = new WorkspaceUI("userCreated",selectedFile.getName());
			    
			}
	    	}
		});
		
		frame.setVisible(true);
		
	}

}
