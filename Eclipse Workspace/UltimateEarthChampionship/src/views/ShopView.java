package views;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import champion.Champion;
import main.Shop;
import weapons.Weapon;

public class ShopView extends JPanel {

	private static final long serialVersionUID = 2101264902458190410L;

	/**
	 * Create the panel.
	 */
	public ShopView(Shop shop) {
		setName("Shop");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel championsPanel = new JPanel();
		championsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(championsPanel);
		
		for (Champion champion : shop.getAvailableChampions()) {
			JPanel championPanel = new PurchasablePanel(champion, false, true);
			championsPanel.add(championPanel);
		}
		
		JPanel weaponsPanel = new JPanel();
		weaponsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(weaponsPanel);
		
		for (Weapon weapon : shop.getAvailableWeapons()) {
			JPanel weaponPanel = new PurchasablePanel(weapon, false, true);
			weaponsPanel.add(weaponPanel);
		}
	}

}
