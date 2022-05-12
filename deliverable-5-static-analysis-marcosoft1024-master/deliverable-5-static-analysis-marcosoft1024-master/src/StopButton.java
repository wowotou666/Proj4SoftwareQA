import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class StopButton extends JButton {

	private MainPanel mainPanel;

	/**
	 * Constructor - Adds a listener to the button.
	 * 
	 * @param m the main animation panel where all the action happens
	 */
	public StopButton(MainPanel m) {
		super("Stop");
		mainPanel = m;
		addActionListener(new StopButtonListener());
	}

	class StopButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			mainPanel.stop();
		}
	}

}
