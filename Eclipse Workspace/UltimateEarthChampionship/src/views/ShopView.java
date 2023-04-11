package views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import champion.Champion;
import main.Purchasable;
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
		
		@SuppressWarnings("unchecked")
		ArrayList<Purchasable> availableChampions = (ArrayList<Purchasable>) (ArrayList<?>) shop.getAvailableChampions(); 
		PurchasableListPanel championListPanel = new PurchasableListPanel(availableChampions, false, true);
		add(championListPanel);
		
		@SuppressWarnings("unchecked")
		ArrayList<Purchasable> availableWeapons = (ArrayList<Purchasable>) (ArrayList<?>) shop.getAvailableWeapons(); 
		PurchasableListPanel weaponListPanel = new PurchasableListPanel(availableWeapons, false, true);
		add(weaponListPanel);
	}

}
