import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.StrokeBorder;

public class MazeCanvas extends JPanel{
	
	public Maze m;
	private int size;
	
	public MazeCanvas(Maze m){
		this.m = m;
		size = 200;
		
		setPreferredSize(new Dimension(m.height*size,m.width*size));
		setLayout(new BorderLayout());
	}
	
	
	public void doDrawing(Graphics g){
		Graphics2D graphics  = (Graphics2D) g;
		
		int x = 0;
		int y = 0;
		
		for(int i = 0; i < m.getMaze().size(); i++){
			for(int k = 0; k < m.getMaze().get(i).size(); k++){
				graphics.drawRect(x, y, size, size);
				graphics.setPaint(m.getMaze().get(i).get(k).getColour());
					
				graphics.fillRect(x, y, size, size);
				x += size;
			}
			x = 0;
			y += size;
		}
	}
	
	@Override
    public void paintComponent(Graphics g) {    
        super.paintComponent(g);
        doDrawing(g);
    }
}
	

