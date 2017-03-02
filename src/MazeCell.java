import java.awt.Color;
import java.util.UUID;
/**
 * Model to represent each cell of a maze.
 * @author James Langford
 * @version 1.0
 * @since 15/08/2016
 */
public class MazeCell {
	private boolean isWall;
	private boolean isStart = false;
	private boolean isEnd = false;
	
	public int x,y;
	
	public MazeCell LEFT, RIGHT, UP, DOWN;
	
	public String UID = "";
	
	/**
	 * Constructor for creating a MazeCell
	 * @param x the x coordinate of this MazeCell
	 * @param y the y coordinate of this MazeCell
	 * @param isWall whether or not this will be instatiated as a wall.
	 */
	public MazeCell(int x, int y, boolean isWall){
		this.x = x;
		this.y = y;
		this.isWall = isWall;
		isStart = false;
		isEnd = false;
		
		if(!isWall){
			UID = UUID.randomUUID().toString();
		}
	}

	/**
	 * Returns whether or not this MazeCell is considered to be a wall.
	 * @return boolean representing if this cell is a wall
	 */
	public boolean isWall(){
		return isWall;
	}
	
	/**
	 * Method to set whether this cell is a wall or not.
	 * @param state true means this is a wall, false sets it as an accessible cell.
	 */
	public void changeState(Boolean state){
		isWall = state;
	}
	
	/**
	 * Method used to check whether this cell has been defined as a start cell or not.
	 * @return whether or not this cell is the start.
	 */
	public boolean isStart(){
		return isStart();
	}
	
	/**
	 * Method to return whether or not this is the end of the maze
	 * @return returns true if it is the end, false if it isnt.
	 */
	public boolean isEnd(){
		return isEnd();
	}
	
	/**
	 * Method to set this MazeCells isStart boolean.
	 * @param changeState set this to true if it is the start of the maze.
	 */
	public void setStart(boolean changeState){
		isStart = changeState;
	}
	
	/**
	 * Method to set this MazeCells isEnd boolean.
	 * @param changeState set this to true if it is the end of the maze.
	 */
	public void setEnd(boolean changeState){
		isEnd = changeState;
	}
	
	/**
	 * Method to return the colour that this MazeCell will have to be.
	 * @return
	 */
	public Color getColour(){
		if(isStart){
			return Color.RED;
		}else if(isEnd){
			return Color.GREEN;
		}else if(isWall){
			return Color.BLACK;
		}else{
			return Color.WHITE;
		}
	}
	
	/**
	 * Get the Unique identifier assigned to this MazeCell
	 * @return the UID String.
	 */
	public String getUID(){
		return UID;
	}
	
	/**
	 * Set the Unique Identifier assigned to this MazeCell
	 * @param UID
	 */
	public void setUID(String UID){
		this.UID = UID;
	}

}
