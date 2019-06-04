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
		        return Integer.compare(e1.getX(), e2.getX());
		    }
		});
	}
	
	public boolean arePairsComplete() {
		
		if(pairs.size() == SIZE)
			return true;
		
		return false;
	}
	
//	private Float getMedian(ArrayList<Element> elements) {
//		if (isPair(elements.size()))
//			return (float) ((elements.get((Integer)elements.size()/2).getX() + (elements.get(((Integer)elements.size()/2) + 1)).getX())/2);
//		else
//			return (float) elements.get(((Integer)elements.size()/2) + 1).getX();
//	}
/*	
	private Element medianOfMedians(ArrayList<Element> elements) {
		ArrayList<Element> subGroups = new ArrayList<Element>();		
		for(int j=0; j<elements.size(); j++) {		
			ArrayList<Element> fiveGroup = new ArrayList<Element>();
			for(int i=0; (i<5 && ((j*5 + i + 1) <= elements.size())); i++){
				fiveGroup.add(elements.get(j));
			}
			Element median = getMedian(elements);
			subGroups.add(median);
		}
		return getMedian(subGroups);
	}*/
	
	private Element kthSmallest(ArrayList<Element> itens, Integer index){
		return itens.get(index);
	}	
	
	private Element oracle(){
		Element mom;
		int j = 0;
		
		System.out.println("Elements");
		for(int i=0; i<elements.size(); i++) {
			System.out.println("Elem" + i + ": " + elements.get(i).getX());
		}		
		
		ArrayList<Element> subGroups = new ArrayList<Element>();
		
		while(j < elements.size()) {
			ArrayList<Element> fiveGroup = new ArrayList<Element>();
			for(int i = 0; i < 5 && j < elements.size(); i++){
				fiveGroup.add(elements.get(j));
				j++;
			}
			subGroups.add(kthSmallest(elements, elements.size()/2));			
		}
		
		mom = kthSmallest(subGroups, subGroups.size()/2);				
		System.out.println("Median Of Medians: " + mom.getX());		
		elements.indexOf(mom);
		
		return mom;
	}
	
	private Element exotericSelection(ArrayList<Element> items, Integer index) {
		Integer position;
		ArrayList<Element> left = new ArrayList<Element>();
		ArrayList<Element> right = new ArrayList<Element>();
		
		Element mom = oracle();
		position = items.indexOf(mom);
		
		for(int i = 0; i < items.size(); i++) {
			if(i < position) {
				left.add(items.get(i));
			}
			else if(i > position){
				right.add(items.get(i));
			}			
		}
		
		if(left.size() == index-1) {
			return mom;
		}
		else if(left.size() > index-1) {
			exotericSelection(left, index);
		}
		else {
			exotericSelection(right, index - left.size() - 1);
		}
		return null;
		
	}
	
	//TODO TRANSFORMAR PSEUDOCODIGO EM CODIGO
	public void closestPair(){
		exotericSelection(elements, 1/2);
	}
}
