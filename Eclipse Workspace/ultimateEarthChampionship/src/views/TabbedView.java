package views;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedView extends JPanel {

	private static final long serialVersionUID = 8081074315449639244L;

	/**
	 * Create the panel.
	 */
	public TabbedView(ArrayList<JPanel> tabs) {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 450, 300);
		add(tabbedPane);
		
		for (JPanel tab : tabs) {
			tabbedPane.addTab(tab.getName(), null, tab, null);
		}

	}

}
