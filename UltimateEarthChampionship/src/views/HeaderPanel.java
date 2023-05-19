package views;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Configuration;

/**
 * A panel containing a white label in header font and an optional gray
 * background for readability.
 */
public class HeaderPanel extends JPanel {

	private static final long serialVersionUID = -1503579800072447729L;

	/**
	 * Create the panel.
	 *
	 * @param header        the text to display on the header
	 * @param hasBackground true if you want the gray background to show
	 */
	public HeaderPanel(String header, boolean hasBackground) {
		setLayout(new GridBagLayout());
		setOpaque(false);
		JLabel label = new JLabel(header);
		label.setFont(Configuration.HEADER_FONT);
		label.setForeground(Color.white);
		if (hasBackground) {
			label.setBackground(Color.darkGray);
			label.setOpaque(true);
		}
		add(label);
	}

}
