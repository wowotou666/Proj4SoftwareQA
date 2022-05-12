import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ResetButton extends JButton {

	private MainPanel mainPanel;

	/**
	 * Constructor - Adds a listener to the button.
	 * 
	 * @param m the main animation panel where all the action happens
	 */
	public ResetButton(MainPanel m) {
		super("Reset");
		mainPanel = m;
		addActionListener(new ResetButtonListener());
	}

	class ResetButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			mainPanel.reset();
		}
	}

}
