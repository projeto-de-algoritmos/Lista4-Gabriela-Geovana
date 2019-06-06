import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public enum Map {
	INSTANCE;
	public static final int SIZE = 15;
	private ArrayList<Element> elements =  new ArrayList<>();
	private HashMap<String, Element> pairs = new HashMap<String, Element>();
	public Float minorDistance = (float) 10000;
	public Float minorDistanceHashMap = (float) 10000; 

	public HashMap<String, Element> getPairs() {
		return pairs;
	}

	public void setPairs(HashMap<String, Element> pairs) {
		this.pairs = pairs;
	}

	public void reset(){
		elements = new ArrayList<>();
		pairs = new HashMap<String, Element>();
	}
	
	public void removePair(Element el1, Element el2) {
		elements.remove(el1);
		elements.remove(el2);
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
	private void addInHashMap(Element element1, Element element2) {
		float d = distanceTeste(element1, element2);
		if(d < minorDistanceHashMap) {
			pairs.put("One", element1);
			pairs.put("Two", element2);
			
			minorDistanceHashMap = d;
		}
	}
	
	public float distanceTeste(Element element1, Element element2){
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
	
	public ArrayList<Element> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Element> elements) {
		this.elements = elements;
	}

	public Float closestPair(ArrayList<Element> elements){
		if(elements.size() == 3 || elements.size() == 2) {
			minorDistance = bruteForce(elements);
			return minorDistance;
		}
		else if(elements.size() == 1) {
			return (float) 10000;
		}

		Float medianPoint = getMedianPoint(elements);
		
		ArrayList<Element> left = new ArrayList<Element>();
		ArrayList<Element> right = new ArrayList<Element>();
		
		for(int i = 0; i < elements.size(); i++) {
			if(elements.get(i).getX() <= medianPoint) {
				left.add(elements.get(i));
			}
			else{				
				right.add(elements.get(i));
			}
		}
		
		float dl = closestPair(left);
		float dr = closestPair(right);
		
		float dis = min(dl, dr);
		if (dis < minorDistance) {
			minorDistance = dis;
		}
		
		ArrayList<Element> strip = new ArrayList<Element>();		
		for(int i = 0; i < elements.size(); i++) {
			if((abs(elements.get(i).getX() - medianPoint)) <= minorDistance) {
				strip.add(elements.get(i));
			}
		}
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
//				System.out.println("Dist�ncia: (" + elements.get(i).getType() + "," + elements.get(j).getType() + ") : " + 
//						   distanceTeste(elements.get(i), elements.get(j)));
				
				
				if(distanceTeste(elements.get(i), elements.get(j)) < min && elements.get(i).getType() != elements.get(j).getType()){
					min = distanceTeste(elements.get(i), elements.get(j));
				}
			}
		}
		
		System.out.println("Menor: " + min);
	}
}
