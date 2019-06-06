import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public enum Map {
	INSTANCE;
	public static final int SIZE = 15;
	public ArrayList<Element> elements =  new ArrayList<>();
	private HashMap<Element, Element> pairs = new HashMap<Element, Element>();
	public Float minorDistance = (float) 10000;
	public Float minorDistanceHashMap = (float) 10000;
	public HashMap<String , Element> pair = new HashMap<String, Element>(); 

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
		        return Integer.compare(e1.getX(), e2.getX());
		    }
		});
	}
	
	public ArrayList<Element> sortByY(ArrayList<Element> elements) {
		Collections.sort(elements, new Comparator<Element>() {
		    public int compare(Element e1, Element e2) {
		        return Integer.compare(e1.getY(), e2.getY());
		    }
		});
		
		return elements;
	}
	
	public Float min(Float num1, Float num2) {
		if(num1 < num2) {
			return num1;
		}
		else {
			return num2;
		}
	}
	
	public boolean arePairsComplete() {		
		if(pairs.size() == SIZE)
			return true;
		
		return false;
	}
	
	private float distance(Element element1, Element element2){
		float result = (float) sqrt(
   			 (element1.getX() - element2.getX())*(element1.getX() - element2.getX()) + 
             (element1.getY() - element2.getY())*(element1.getY() - element2.getY()) 
           );
				
		if (result < minorDistanceHashMap && element1.getType() != element2.getType()) {
			addInHashMap(element1, element2);
		}
			
	    return result;
	}
	private void addInHashMap(Element one, Element two) {
		float d = distanceTeste(one, two);
//		System.out.println("Entrou: " + d);
		if(d < minorDistanceHashMap) {
			//System.out.println("1:" + minorDistance + " 2:" + distanceTeste(one, two));
			pair.put("primaryPair", one);
			pair.put("secondPair", two);
			
			minorDistanceHashMap = d;
			
//			System.out.println("ADD: (" + one.getX() + "," + one.getY() + 
//					  "), (" +	two.getX() + "," + two.getY() + "): " + d);
		}
	}
	
	private float distanceTeste(Element element1, Element element2){
		float result = (float) sqrt(
   			 (element1.getX() - element2.getX())*(element1.getX() - element2.getX()) + 
             (element1.getY() - element2.getY())*(element1.getY() - element2.getY()) 
           );
			
	    return result;
	}

	private float bruteForce(ArrayList<Element> elements){ 
	    float min = 10000; 
	    for (int i = 0; i < (elements.size() - 1); ++i) {
	        for (int j = i + 1; j < elements.size(); ++j) { 
	            if (distance(elements.get(i), elements.get(j)) < min && elements.get(i).getType() != elements.get(j).getType()) {
	            	addInHashMap(elements.get(i), elements.get(j));
	                min = distance(elements.get(i), elements.get(j));
	            }
	        }
	    }
	    return min; 
	}
	
	
	private Float getMedianPoint(ArrayList<Element> elements) {
		if (elements.size() % 2 == 0 && elements.size() != 2) {
			Integer d = elements.get(elements.size()/2).getX() - elements.get(elements.size()/2 - 1).getX();
			return (float) elements.get(elements.size()/2 - 1).getX() + (d/2);
		}
		else {
			return (float) (elements.get(elements.size()/2).getX());
		}
	}
	
	private float stripClosest(ArrayList<Element> strip) {
		ArrayList<Element> stripSortedByY = sortByY(strip);
		
		for (int i = 0; i < stripSortedByY.size(); ++i) {
			for (int j = i + 1; j < stripSortedByY.size() && ((stripSortedByY.get(j).getY() - strip.get(i).getY()) < minorDistance); ++j) {
				//updateMinorDistance(minorDistance, stripSortedByY.get(i), stripSortedByY.get(j));
				if((stripSortedByY.get(j).getType() != strip.get(i).getType()))
					minorDistance = distance(stripSortedByY.get(i), stripSortedByY.get(j));
				
				//addInHashMap(stripSortedByY.get(i), stripSortedByY.get(j));
			}
		}
		
		return minorDistance;
	}
	
	public Float closestPair(ArrayList<Element> elements){
//		for(int i=0; i<elements.size(); i++) {
//			System.out.println("Elem (" + elements.get(i).getX() + ", " + elements.get(i).getY() + ")");
//		}
//		System.out.println("__________________________________________");
		
		if(elements.size() == 3 || elements.size() == 2) {
			minorDistance = bruteForce(elements);
			return minorDistance;
		}
		else if(elements.size() == 1) {
			return (float) 10000;
		}

		Float medianPoint = getMedianPoint(elements);
		
		
//		System.out.println("MedianPoint: " + medianPoint);
		
//		System.out.println("MD1: " + minorDistance);
		
		ArrayList<Element> left = new ArrayList<Element>();
		ArrayList<Element> right = new ArrayList<Element>();
		
		for(int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getX() <= medianPoint) {
				//System.out.println("Elem" + i + " - Left ");
				left.add(elements.get(i));
			}
			else{				
				right.add(elements.get(i));
				//System.out.println("Elem" + i + " - Right ");
			}
		}
//		System.out.println("MDA2: " + minorDistance);
		
		float dl = closestPair(left);
		float dr = closestPair(right);
		
		float dis = min(dl, dr);
		if (dis < minorDistance) {
			minorDistance = dis;
		}
		
//		System.out.println("MD2: " + minorDistance);
		
		ArrayList<Element> strip = new ArrayList<Element>();		
		for(int i = 0; i < elements.size(); i++) {
			if((abs(elements.get(i).getX() - medianPoint)) <= minorDistance) {
				strip.add(elements.get(i));
			}
		}
//		System.out.println("MD3: " + minorDistance);
		return min(minorDistance, stripClosest(strip));
		
	}
	
	public void printDistances() {
		float min = 10000;
		for (int i = 0; i < elements.size() -1; i++) {
			for (int j = (i+1); j < elements.size(); j++) {
//				System.out.println("Distancia: " + distance(elements.get(i), elements.get(j)));
				
//				System.out.println("Dist�ncia: (" + elements.get(i).getX() + "," + elements.get(i).getY() + 
//						  "), (" +	elements.get(j).getX() + "," + elements.get(j).getY() + ") : " + 
//						   distanceTeste(elements.get(i), elements.get(j)));
				
				System.out.println("Dist�ncia: (" + elements.get(i).getType() + "," + elements.get(j).getType() + ") : " + 
						   distanceTeste(elements.get(i), elements.get(j)));
				
				
				if(distanceTeste(elements.get(i), elements.get(j)) < min && elements.get(i).getType() != elements.get(j).getType()){
					Element element1 = elements.get(i);
					Element element2 = elements.get(j);
					
					
					min = distanceTeste(elements.get(i), elements.get(j));
				}
			}
		}
		
		System.out.println("Menor: " + min);
	}
}
