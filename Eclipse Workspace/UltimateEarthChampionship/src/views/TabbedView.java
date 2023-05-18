package views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import model.Configuration;

/**
 * A panel that contains a tabbed pane for navigating between the game's team,
 * shop, and stadium views.
 */
public class TabbedView extends JPanel {

	private static final long serialVersionUID = 8081074315449639244L;

	/**
	 * The tabbed pane
	 */
	private JTabbedPane tabbedPane;

	/**
	 * The team view
	 */
	private TeamView teamView;

	/**
	 * The show view
	 */
	private ShopView shopView;

	/**
	 * The stadium view
	 */
	private StadiumView stadiumView;

	/**
	 * Create the panel.
	 */
	public TabbedView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		InfoPanel infoPanel = new InfoPanel();
		add(infoPanel);

		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setFont(Configuration.HEADER_FONT);
		add(tabbedPane);

		teamView = new TeamView();
		tabbedPane.addTab(teamView.getName(), null, teamView, "View and modify your champions and weapons");

		shopView = new ShopView();
		tabbedPane.addTab(shopView.getName(), null, shopView, "Buy new champion and weapons");

		stadiumView = new StadiumView();
		tabbedPane.addTab(stadiumView.getName(), null, stadiumView, "Select a match and fight");
	}

	/**
	 * Shows the team view
	 */
	public void showTeamView() {
		tabbedPane.setSelectedComponent(teamView);
	}

	/**
	 * Shows the shop view
	 */
	public void showShopView() {
		tabbedPane.setSelectedComponent(shopView);
	}

	/**
	 * Shows the stadium view
	 */
	public void showStadiumView() {
		tabbedPane.setSelectedComponent(stadiumView);
	}

}
