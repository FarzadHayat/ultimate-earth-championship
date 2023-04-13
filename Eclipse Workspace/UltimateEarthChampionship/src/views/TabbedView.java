package views;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import main.Shop;

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
		tabbedPane.addTab(teamView.getName(), null, teamView, "View and change your current champions and weapons");

		Shop shop = new Shop();
		shop.generateCatalogue();
		ShopView shopView = new ShopView(shop);
		tabbedPane.addTab(shopView.getName(), null, shopView, "Buy new champion and weapons");
	}

}
