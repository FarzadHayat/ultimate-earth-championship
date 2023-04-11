package views;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import main.Purchasable;
import main.Shop;

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
		PurchasableListPanel championListPanel = new PurchasableListPanel(availableChampions, true, false);
		add(championListPanel);
		
		@SuppressWarnings("unchecked")
		ArrayList<Purchasable> availableWeapons = (ArrayList<Purchasable>) (ArrayList<?>) shop.getAvailableWeapons(); 
		PurchasableListPanel weaponListPanel = new PurchasableListPanel(availableWeapons, true, false);
		add(weaponListPanel);
	}

}
