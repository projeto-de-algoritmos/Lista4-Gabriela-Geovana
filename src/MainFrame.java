import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JButton btnRandom;
	private JButton btnSolution;
	private MapPanel mapPanel;
	private HashMap<Element, Element> pairs = new HashMap<Element, Element>();

	public MainFrame() {

		setTitle("Divide and conquer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		// Generate random drivers and stickmen
		btnRandom = new JButton("Randomizar dados");
		btnRandom.setAlignmentX(CENTER_ALIGNMENT);
		btnRandom.addActionListener(this);
		contentPane.add(btnRandom);
		
		// Separating button from button
		contentPane.add(Box.createRigidArea(new Dimension(10, 10)));

		// Generate solution
		btnSolution = new JButton("Mostrar pares de carona");
		btnSolution.setAlignmentX(CENTER_ALIGNMENT);
		btnSolution.addActionListener(this);
		contentPane.add(btnSolution);
		
		// Separating button from pane
		contentPane.add(Box.createRigidArea(new Dimension(10, 10)));
		
		//Map of drivers
		mapPanel = new MapPanel();
		contentPane.add(mapPanel);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Map map = Map.INSTANCE;
		if(e.getSource() == btnRandom) {
			pairs = new HashMap<Element, Element>();
			mapPanel.setPairs(null);
			mapPanel.repaint();
			mapPanel.revalidate();
		} else {
			map.sortByX();
			while(!arePairsComplete()) {
				map.minorDistanceHashMap = (float)10000;
				map.minorDistance = (float)10000;		
				map.printDistances();
				
				System.out.println("Menor distancia: " + map.closestPair(map.getElements()));
				
				pairs.put(map.getPairs().get("One"), map.getPairs().get("Two"));
				map.removePair(map.getPairs().get("One"), map.getPairs().get("Two"));
			}
			mapPanel.setPairs(pairs);
			mapPanel.repaint();
		}
	}
	
	public boolean arePairsComplete() {		
		if(pairs.size() == Map.SIZE)
			return true;
		
		return false;
	}
	
	
}
