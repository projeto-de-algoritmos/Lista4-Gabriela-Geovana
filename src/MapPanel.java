import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapPanel extends JPanel{
	private HashMap<Element, Element> pairs = new HashMap<Element, Element>();
	
	public MapPanel() {
		setBackground(Color.GRAY);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		   
		Graphics2D g2 = (Graphics2D) g; // cast to Graphics2D
	    
	    int x;
	    int y;
	    
	    if(pairs == null) {
	    	
	    	Random rdm = new Random();
	 	    Map map = Map.INSTANCE;
	 	    map.reset();
	 	    
	    	for(int n=0; n<Map.SIZE; n++) {
	    		x = rdm.nextInt(1100);
	    		y = rdm.nextInt(650);
	    		drawCar(g2, x, y);
	    		map.addElement(x, y, Element.CAR);
	    		x = rdm.nextInt(1100);
	    		y = rdm.nextInt(650);
	    		drawPerson(g2, x, y);
	    		map.addElement(x, y, Element.PERSON);
	    	}
	    	
	    } else {
	    	for (Entry<Element, Element> pair : pairs.entrySet()) {
		        
	    		Element el1 = pair.getKey();
		        Element el2 = pair.getValue();
		        
		        if(el1.getType() == Element.CAR) {
			        drawCar(g2, el1.getX(), el1.getY());
			        drawPerson(g2, el2.getX(), el2.getY());
		        } else {
		        	drawCar(g2, el2.getX(), el2.getY());
				    drawPerson(g2, el1.getX(), el1.getY());
		        }
		        
		        g2.drawLine(el1.getX(), el1.getY(), el2.getX(), el2.getY());
		    }
	    } 
	  
	}
	
	private void drawCar(Graphics2D g2, int x, int y) {
    	GeneralPath gp = new GeneralPath();
    	gp.moveTo(x,y); //start here
	    gp.lineTo(x+10,y);
	    gp.quadTo(x+15,y+10,x+20,y);
	    gp.lineTo(x+50,y);
	    gp.quadTo(x+55,y+10,x+60,y);
	    gp.lineTo(x+70,y);
	    gp.curveTo(x+68,y-10,x+70,y-20,x+50,y-20);
	    gp.lineTo(x+25,y-20);
	    gp.lineTo(x+15,y-10);
	    gp.lineTo(x,y-10);
	    gp.lineTo(x,y); //going left (back to start)

	    g2.setPaint(Color.red);
	    g2.fill(gp);
	    g2.setPaint(Color.black);
	    g2.draw(gp);//or g2d.fill(gp)
	}
	
	private void drawPerson(Graphics2D g2, int x, int y) {
		// the head
        g2.drawOval(x,y,10,10);
        
        // the body
        g2.drawLine(x+5,y+10,x+5,y+30);
        
        // the hands
        g2.drawLine(x+5,y+20,x-5,y+20);
        g2.drawLine(x+5,y+20,x+15,y+8);
        
        // the legs
        g2.drawLine(x+5,y+30,x-3,y+40);
        g2.drawLine(x+5,y+30,x+13,y+40);
	}

	public HashMap<Element, Element> getPairs() {
		return pairs;
	}

	public void setPairs(HashMap<Element, Element> pairs) {
		this.pairs = pairs;
	}

}
