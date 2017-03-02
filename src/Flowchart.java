import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Flowchart {
	HashMap<String, Node> nodes = new HashMap<String,Node>();
	
	public Flowchart(){
	}
	
	public void addNode(String instruction, ArrayList<Edge> comingFrom, ArrayList<Edge> goingTo){
		String uniqueID = UUID.randomUUID().toString();
		Node n = new Node(uniqueID, instruction, comingFrom, goingTo);
		nodes.put(n.getUID(), n);
	}
	
	public void removeNode(String UUID){
		nodes.remove(nodes.get(UUID));
	}

}
