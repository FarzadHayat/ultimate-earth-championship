package views;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedView extends JPanel {

	private static final long serialVersionUID = 8081074315449639244L;

	/**
	 * Create the panel.
	 */
	public TabbedView() {
		setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 450, 300);
		add(tabbedPane);
		
		TeamView teamView = new TeamView();
		tabbedPane.addTab(teamView.getName(), null, teamView, null);

	}

}
