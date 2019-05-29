import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private JPanel contentPane;
	private JButton btnRandom;
	private MapPanel mapPanel;
	private Map map;

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
		contentPane.add(btnRandom);
		
		// Separating button from button
		contentPane.add(Box.createRigidArea(new Dimension(10, 10)));

		// Generate solution
		btnRandom = new JButton("Mostrar pares de carona");
		btnRandom.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnRandom);
		
		// Separating button from pane
		contentPane.add(Box.createRigidArea(new Dimension(10, 10)));
		
		//Map of drivers
		map = new Map();
		mapPanel = new MapPanel(map);
		contentPane.add(mapPanel);
	
	}

	
}
