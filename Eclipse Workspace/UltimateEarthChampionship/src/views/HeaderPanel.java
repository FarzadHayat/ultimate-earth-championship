package views;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Configuration;

public class HeaderPanel extends JPanel {

	private static final long serialVersionUID = -1503579800072447729L;

	/**
	 * Create the panel.
	 */
	public HeaderPanel(String header) {
		setLayout(new GridBagLayout());
		JLabel label = new JLabel(header);
		label.setFont(Configuration.HEADER_FONT);
		setOpaque(false);
		label.setForeground(Color.white);
		label.setBackground(Color.darkGray);
		label.setOpaque(true);
		add(label);
	}

}
