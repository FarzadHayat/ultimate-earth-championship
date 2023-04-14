package views;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import main.GameManager;
import main.Shop;

public class TabbedView extends JPanel {

	private static final long serialVersionUID = 8081074315449639244L;

	/**
	 * Create the panel.
	 */
	public TabbedView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		InfoPanel infoPanel = new InfoPanel();
		add(infoPanel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		TeamView teamView = new TeamView();
		tabbedPane.addTab(teamView.getName(), null, teamView, "View and change your current champions and weapons");

		ShopView shopView = new ShopView();
		tabbedPane.addTab(shopView.getName(), null, shopView, "Buy new champion and weapons");
	}

}
