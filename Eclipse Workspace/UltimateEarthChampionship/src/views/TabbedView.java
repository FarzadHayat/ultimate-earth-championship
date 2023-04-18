package views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedView extends JPanel {

	private static final long serialVersionUID = 8081074315449639244L;
	
	private JTabbedPane tabbedPane;

	private TeamView teamView;

	private ShopView shopView;

	/**
	 * Create the panel.
	 */
	public TabbedView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		InfoPanel infoPanel = new InfoPanel();
		add(infoPanel);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		teamView = new TeamView();
		tabbedPane.addTab(teamView.getName(), null, teamView, "View and modify your champions and weapons");

		shopView = new ShopView();
		tabbedPane.addTab(shopView.getName(), null, shopView, "Buy new champion and weapons");
	}
	
	public void showTeamView() {
		tabbedPane.setSelectedComponent(teamView);
	}
	
	public void showShopView() {
		tabbedPane.setSelectedComponent(shopView);
	}

}
