import java.util.ArrayList;

public class Map {
	private ArrayList<Element> elements;
	
	Map(){
		elements = new ArrayList<>();
	}
	
	public void addElement(int x, int y, int type) {
		Element element = new Element(x, y, type);
		elements.add(element);
	}
	
	
	//public ArrayList<Element> closestPair(ArrayList<Element> elements){
		// Elements must be sorted according to x-coordinate.
		
		// Compute separation line L such that half the points 
		// are on one side and half on the other side.
		
		//c1 = Closest-Pair(left half)
		//c2 = Closest-Pair(right half)
		//c3 = min( c1 , c2 )
		
		// Delete all points further than from separation line L
		
		// Sort remaining points by y-coordinate.
		
		//Scan points in y-order and compare distance between
		//each point and next 6 neighbors. If any of these
		//distances is less than c3 , update c3.
		
		//return c3;
	//}
}
