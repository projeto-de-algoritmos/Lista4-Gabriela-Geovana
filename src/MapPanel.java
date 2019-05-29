import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapPanel extends JPanel{
	
	public MapPanel() {
		setBackground(Color.GRAY);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		   
		Graphics2D g2 = (Graphics2D) g; // cast to Graphics2D
	    
	    int x;
	    int y;
	    
	    Random rdm = new Random();
	    Map map = Map.INSTANCE;
	    map.reset();
	    
	    // Car drawing 
	    for(int n=0; n<Map.SIZE; n++) {
	    	x = rdm.nextInt(1100);
	    	y = rdm.nextInt(650);
	    	map.addElement(x, y, Element.CAR);
	    		
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
	    
	    // Person drawing
	    for(int n=0; n<Map.SIZE; n++) {
	    	x = rdm.nextInt(1100);
	    	y = rdm.nextInt(650);
	    	map.addElement(x, y, Element.PERSON);
	    	
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
	
	}

}
