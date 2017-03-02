/**
 * A class to model the edges between two nodes of a flowchart.
 * @author James
 * @version 1.0
 * @since 15/08/2016
 *
 */
public class Edge {
	private Node from;
	private Node to;
	private String annotation;
	
	/**
	 * Constructor to define an edge object.
	 * @param from the node this edge will start from.
	 * @param to the node this edge is leading to.
	 * @param annotation String to store a user friendly description of this edges purpose.
	 */
	public Edge(Node from, Node to, String annotation){
		this.from = from;
		this.to = to;
		this.annotation = annotation;
	}
	
	/**
	 * Set a new node that this edge will come from.
	 * @param newFrom the new node this edge will come from.
	 */
	public void setFrom(Node newFrom){
		from = newFrom;
	}
	
	/**
	 * Retrieve the node this edge is beginning at.
	 * @return Node object where the edge begins.
	 */
	public Node getFrom (){
		return from;
	}
	
	/**
	 * Set a new node tha this edge will go to
	 * @param newTo the new node this edge will go to.
	 */
	public void setTo(Node newTo){
		to = newTo;
	}
	
	/**
	 * Retrieve the node this edge is going to 
	 * @return Node object where the edge is ending.
	 */
	public Node getTo(){
		return to;
	}

}
