import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private JPanel contentPane;
	private JButton btnRandom;
	private JButton btnSolution;

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

		// Generate random matrix 
		btnRandom = new JButton("Matriz aleatória");
		btnRandom.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnRandom);

		// Separating buttons
		contentPane.add(Box.createRigidArea(new Dimension(10, 10)));
		
		// Generate solution
		btnSolution = new JButton("Solução");
		btnSolution.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnSolution);
	}

	
}
