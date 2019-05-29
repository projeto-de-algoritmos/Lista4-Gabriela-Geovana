import java.awt.EventQueue;

import javax.swing.JOptionPane;
public class Main {

	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			@Override
	        public void run() {
	            MainFrame frame = new MainFrame();
	            frame.setVisible(true);
	        }
		});
		

	}

}
