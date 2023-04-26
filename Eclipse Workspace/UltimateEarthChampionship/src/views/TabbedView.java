package views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedView extends JPanel {

	private static final long serialVersionUID = 8081074315449639244L;
	
	private JTabbedPane tabbedPane;

	private TeamView teamView;

	private ShopView shopView;
	
	private StadiumView stadiumView;

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
		
		stadiumView = new StadiumView();
		tabbedPane.addTab(stadiumView.getName(), null, stadiumView, "Select a match and fight");
	}
	
	public void showTeamView() {
		tabbedPane.setSelectedComponent(teamView);
	}
	
	public void showShopView() {
		tabbedPane.setSelectedComponent(shopView);
	}
	
	public void showStadiumView() {
		tabbedPane.setSelectedComponent(stadiumView);
	}

}
