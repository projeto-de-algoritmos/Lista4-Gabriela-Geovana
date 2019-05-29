import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public enum Map {
	INSTANCE;
	public static final int SIZE = 15;
	private ArrayList<Element> elements =  new ArrayList<>();
	private HashMap<Element, Element> pairs = new HashMap<Element, Element>();
	
	public void reset(){
		elements = new ArrayList<>();
		pairs = new HashMap<Element, Element>();
	}
	
	public void addElement(int x, int y, int type) {
		Element element = new Element(x, y, type);
		elements.add(element);
	}
	
	public void sortByX() {
		Collections.sort(elements, new Comparator<Element>() {
		    public int compare(Element e1, Element e2) {
		        return Integer.compare(e1.getX(), e2.getY());
		    }
		});
	}
	
	public boolean arePairsComplete() {
		
		if(pairs.size() == SIZE)
			return true;
		
		return false;
	}
	
	//TODO TRANSFORMAR PSEUDOCODIGO EM CODIGO
	//public ArrayList<Element> closestPair(ArrayList<Element> elements){
		// Elements must be sorted according to x-coordinate.
		
		// Compute separation line L such that half the points 
		// are on one side and half on the other side.
		
		//c1 = Closest-Pair(left half)
		//c2 = Closest-Pair(right half)
		//c3 = min( c1 , c2 )
		
		// Delete all points further than from separation line L
		
		// Sort remaining points by y-coordinate.
		
		// Scan points in y-order and compare distance between
		// each point (IF they have different types)
		// and next 6 neighbors. If any of these
		// distances is less than c3 , update c3.
		
		//return c3;
	//}
}
