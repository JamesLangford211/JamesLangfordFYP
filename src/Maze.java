import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.UUID;
/**
 * Class to model the object that will store a maze. Contains methods and constructors to support the 
 * creation of a new maze from scratch, or a maze that has been created and stored in a CSV file.
 * 
 * @author James Langford
 * @version 1.0
 * @since 12/02/2017
 */
public class Maze {
	
	//2D arrayList to store the ongoing generation and final generated maze
	ArrayList<ArrayList<MazeCell>> maze = new ArrayList<ArrayList<MazeCell>>();
	
	//A list of all the walls in the maze
	ArrayList<MazeCell> walls = new ArrayList<MazeCell>();
	
	//primitive ints to store the maze dimensions
	int width, height;
	
	/**
	 * Constructor to create a Maze object from scratch, completely randomly.
	 * If the constructor spots any issues with the initialisation of the maze it will be defaulted 
	 * to having both a height and width of 3.
	 * @param width how wide the maze will be, must be equal to height.
	 * @param height how tall the maze will be, must be equal to width
	 */
	public Maze(int width, int height){
		
		this.width = width;
		this.height = height;
		checkParams(width,height);
		generateMaze();
	}
	
	/**
	 * Constructor to create a maze from a given CSV file. Expects parameters describing the difficulty and name of the level.
	 * This allows the system to navigate throughout the hierarchy of 
	 * @param difficulty
	 * @param name
	 */
	public Maze(String difficulty, String name){
		String url = "src/assets/levels/"+difficulty+"Levels/"+name+".csv";
		//read in csv file from designated URL
		ArrayList<ArrayList<String>> stringMaze = readInFile(url);
		width = stringMaze.get(0).size();
		height = stringMaze.size();
		
		maze = generatePremadeMaze(stringMaze);
	}
	
	/**
	 * A method to check whether two integers are the same, if not they will be corrected to 3.
	 * (This could be solved by putting both the width and height variables into one, to always ensure the grid will be square, however,
	 * in future versions rectangular versions may be supported, then this method could just be removed).
	 * @param width width of the grid.
	 * @param height height of the grid.
	 */
	private void checkParams(int width,int height){
		if(width!=height){
			this.width = this.height = 3;
		}
	}
	
	/**
	 * Method that expects a 2D array of type String and returns an acceptable 2D array of MazeCells that can be stored in this Mazes Maze field.
	 * @param stringMaze 2D Arraylist of data interpreted from a CSV file (passed from the readInFile() method).
	 * @return 2D ArrayList to store in the Maze field.
	 */
	private ArrayList<ArrayList<MazeCell>> generatePremadeMaze(ArrayList<ArrayList<String>> stringMaze){
		
		ArrayList<ArrayList<MazeCell>> generatedMaze = new ArrayList<ArrayList<MazeCell>>();
		
		for(int y = 0; y<stringMaze.size();y++){
			
			ArrayList<MazeCell> row = new ArrayList<MazeCell>();
			
			for(int x = 0; x<stringMaze.get(y).size();x++){
				
				String consideredVar = stringMaze.get(y).get(x);
				MazeCell newMC = new MazeCell(x,y,false);
				
				if(consideredVar.equals("s")){
					newMC.setStart(true);
				}
				else if(consideredVar.equals("e")){
					newMC.setEnd(true);
				}
				else if(consideredVar.equals("w")){
					newMC.changeState(true);
				}
				row.add(newMC);
			}
			generatedMaze.add(row);
		}
		return generatedMaze;
	}
	
	/**
	 * Expects a url in the form of a String and converts it into a 2D ArrayList that will be passed into generatePremadeMaze().
	 * @param url String object telling the program where to find the CSV file.
	 * @return 2D ArrayList of Strings to be passed to the generatePremadeMaze() method.
	 */
	private ArrayList<ArrayList<String>> readInFile(String url){
		ArrayList<ArrayList<String>> stringMaze = new ArrayList<ArrayList<String>>();
		
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(url));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                ArrayList<String> row = new ArrayList<String>();
                for(int i = 0; i<country.length;i++){
                	row.add(country[i]);
                }
                stringMaze.add(row);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		
		return stringMaze;
		
	}
	
	/**
	 * Iterates throughout the whole maze and sets each MazeCells neighbours
	 */
	private void setNeighbours(){
		for(int i = 0; i<maze.size(); i++){
			for(int k = 0; k<maze.get(i).size(); k++){
				if(k-1 >= 0){
					maze.get(i).get(k).LEFT = maze.get(i).get(k-1);
				}
				if(k+1 < width){
					maze.get(i).get(k).RIGHT = maze.get(i).get(k+1);
				}
				if(i+1 < height){
					maze.get(i).get(k).DOWN = maze.get(i+1).get(k);
				}
				if(i-1 >= 0){
					maze.get(i).get(k).UP = maze.get(i-1).get(k);
				}
			}
		}
	}
	
	/**
	 * Probably not needed, will more than likely be deleted.
	 */
	private void randomize(){
		for(int i = 0; i<maze.size(); i++){
			for(int k = 0; k<maze.get(i).size(); k++){
				Random r = new Random();
				if(r.nextBoolean()){
					maze.get(i).get(k).changeState(true);
				}else{
					maze.get(i).get(k).changeState(false);
				}
			}

		}
	}
	
	/**
	 * A method for testing how a maze will look without having to draw it in a JFrame, paths are represented as '0', and walls are represented as '#'.
	 */
	private void logMazeInConsole(){
		for(int i = 0; i<maze.size(); i++){
			for(int k = 0; k<maze.get(i).size(); k++){
				if(maze.get(i).get(k).isWall()){
					System.out.print("#");
				}else{
					System.out.print("0");
				}
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * A method that retrieves the 2D ArrayList of MazeCells that represents a maze.
	 * @return 2D arrayList of MazeCells that the maze is stored in.
	 */
	public ArrayList<ArrayList<MazeCell>> getMaze(){
		return maze;
	}
	
	/**
	 * Follows a maze generation algorithm that will randomly generate a new maze.
	 */
	public void generateMaze(){		
		//creates a maze of empty cells, sets every even "voxel" to be a wall, stores the walls in a list and each unique set of cells in a set
		//sets 0,0 as the start and width,height as the end, removes walls if they are in the way
		mazeGenerationInit();
		setNeighbours(); // this method assesses each maze cells neighbour and stores it in each mazecell object
		
		while(walls.size() > 0){
			//randomly choose a wall from the index list
			Random r = new Random();
			int chosenWall = r.nextInt(walls.size());
			//System.out.println(chosenWall);
			
			//Will removing this wall combine two empty spaces of  different label?
			willRemoveCombineSet(walls.get(chosenWall));
		
		//remove this wall from the list (never check a wall twice)
		walls.remove(chosenWall);
		}
		
		
	}
	
	/**
	 * Method that will be called for each neighbour a MazeCell has. This solves a key part of the generation algorithm asking
	 * "if this wall is removed, will the two conjoining paths be of different sets?"
	 * @param mc MazeCell that is being checked.
	 */
	private void willRemoveCombineSet(MazeCell mc){		
		//check all possibilities, where it combines different sets
		//set all voxels to have same label (combine lists)
		//set this wall to be a cell
		
		ArrayList<MazeCell> neighbours = getNoneNullNeighbours(mc);
		
		
		if(neighbours.size()>1){
			String comparableUID = neighbours.get(0).getUID();
			for(int i = 0; i<neighbours.size(); i++){
				if(!neighbours.get(i).getUID().equals(comparableUID)){
					neighbours.get(i).setUID(comparableUID);
					mc.changeState(false);
				}
			}
		}
		//elseifNO:
		//skip this
		
	}
	
	/**
	 * Will return an arraylist of MazeCells that arent null
	 * @param mc the MazeCell to be checked.
	 * @return ArrayList of MazeCells that are not null.
	 */
	private ArrayList<MazeCell> getNoneNullNeighbours(MazeCell mc){
		MazeCell up = (mc.UP != null) ? mc.UP : null;
		MazeCell down = (mc.DOWN != null) ? mc.DOWN : null;
		MazeCell left = (mc.LEFT != null) ? mc.LEFT : null;
		MazeCell right = (mc.RIGHT != null) ? mc.RIGHT : null;
		
		ArrayList<MazeCell> mazecells = new ArrayList<MazeCell>();
		mazecells.add(up);
		mazecells.add(down);
		mazecells.add(left);
		mazecells.add(right);
		
		for(int i = 0; i<mazecells.size(); i++){
			if(mazecells.get(i) == null){
				mazecells.remove(i);
			}
		}
		
		return mazecells;
	}
	
	/**
	 * The initialisation stage of the maze generation algorithm.
	 */
	private void mazeGenerationInit(){
		for(int y = 0; y<height; y++){
			ArrayList<MazeCell> row = new ArrayList<MazeCell>(); // make a new row
			
			for(int x = 0; x<width; x++){
				
				boolean initialise; // the variable that stores whether a cell will be a wall or not
				MazeCell mc; // the variable which stores the MazeCell object being created for this vertices 
				
				if(isEven(x) && isEven(y)){
					initialise = false; //it should not be a wall
					mc = new MazeCell(x,y,initialise);
					
					HashSet<MazeCell> hs = new HashSet();			
					//hs.add(mc); // each non-wall should be stored in a uniquely named set 
					//cellSets.add(hs); //unique name comes from index of ArrayList
				}
				else{
					initialise = true; // it is a wall
					mc = new MazeCell(x,y,initialise);
					walls.add(mc); //add it to the list of walls
				}
				row.add(mc);
			}
			maze.add(row);
			
		}
		maze.get(0).get(0).changeState(false);
		maze.get(0).get(0).setStart(true);
		
		maze.get(width-1).get(height-1).changeState(false);
		maze.get(width-1).get(height-1).setEnd(true);
	}
	
	/**
	 * Returns true if the method passed in is even, false if it is not.
	 * @param number the number to be checked.
	 * @return whether or not the checked number is even.
	 */
	private boolean isEven(int number){
		if(number%2==0){
			return true;
		}else{
			return false;
		}
	}

}
	
	