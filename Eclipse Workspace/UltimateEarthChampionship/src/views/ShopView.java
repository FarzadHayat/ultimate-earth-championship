package views;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import champion.Champion;
import main.GameManager;
import main.Purchasable;
import main.Shop;
import weapon.Weapon;

public class ShopView extends JPanel {

	private static final long serialVersionUID = 2101264902458190410L;
	
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Create the panel.
	 */
	public ShopView() {
		setName("Shop");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ArrayList<Purchasable> availableChampions = new ArrayList<Purchasable>();
		for (Champion champion : gameManager.getShop().getAvailableChampions()) {
			availableChampions.add(champion);
		}
		PurchasableListPanel championListPanel = new PurchasableListPanel(availableChampions, CardType.CAN_BUY);
		add(championListPanel);
		
		ArrayList<Purchasable>availableWeapons = new ArrayList<Purchasable>();
		for (Weapon weapon : gameManager.getShop().getAvailableWeapons()) {
			availableWeapons.add(weapon);
		}
		PurchasableListPanel weaponListPanel = new PurchasableListPanel(availableWeapons, CardType.CAN_BUY);
		add(weaponListPanel);
	}

}
