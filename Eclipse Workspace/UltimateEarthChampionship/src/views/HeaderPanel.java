package views;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Configuration;

public class HeaderPanel extends JPanel {

	private static final long serialVersionUID = -1503579800072447729L;

	/**
	 * Create the panel.
	 */
	public HeaderPanel(String header) {
		JLabel label = new JLabel(header);
		label.setFont(Configuration.HEADER_FONT);
		add(label);
	}

}
