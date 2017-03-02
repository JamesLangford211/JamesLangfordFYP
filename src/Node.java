import java.util.ArrayList;

public class Node {
	private String UID = "";
	private String instruction = "";
	
	private ArrayList<Edge> comingFrom;
	private ArrayList<Edge> goingTo;
	
	public Node(String UID, String instruction, ArrayList<Edge> coming, ArrayList<Edge> going){
		this.UID = UID;
		this.instruction = instruction;
		for(int i = 0; i<coming.size(); i++){
			comingFrom.add(coming.get(i));
		}
		for(int i = 0; i<going.size(); i++){
			goingTo.add(going.get(i));
		}
	}
	
	public String getInstruction(){
		return instruction;
	}
	
	public String getUID(){
		return UID;
	}

}
