package views;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import champion.Champion;
import manager.GameManager;
import model.Purchasable;
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
		
		addAvailableChampionsPanel();
		addAvailableWeaponsPanel();
	}

	/**
	 * Add a purchasable panel to display the shop's available champions.
	 */
	private void addAvailableChampionsPanel() {
		ArrayList<Purchasable> availableChampions = new ArrayList<Purchasable>();
		for (Champion champion : gameManager.getShop().getAvailableChampions()) {
			availableChampions.add(champion);
		}
		PurchasablesPanel championsPanel = new PurchasablesPanel(availableChampions, CardType.CAN_BUY);
		add(championsPanel);
	}

	/**
	 * Add a purchasable panel to display the shop's available weapons.
	 */
	private void addAvailableWeaponsPanel() {
		ArrayList<Purchasable> availableWeapons = new ArrayList<Purchasable>();
		for (Weapon weapon : gameManager.getShop().getAvailableWeapons()) {
			availableWeapons.add(weapon);
		}
		PurchasablesPanel WeaponsPanel = new PurchasablesPanel(availableWeapons, CardType.CAN_BUY);
		add(WeaponsPanel);
	}

}
