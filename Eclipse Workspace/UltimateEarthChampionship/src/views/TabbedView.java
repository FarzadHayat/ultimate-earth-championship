package views;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;

public class TabbedView extends JPanel {

	private static final long serialVersionUID = 8081074315449639244L;

	/**
	 * Create the panel.
	 */
	public TabbedView() {
		setLayout(new GridLayout(1, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		TeamView teamView = new TeamView();
		tabbedPane.addTab(teamView.getName(), null, teamView, null);

		ShopView shopView = new ShopView();
		tabbedPane.addTab(shopView.getName(), null, shopView, null);
	}

}
