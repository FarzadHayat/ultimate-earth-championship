package views;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import manager.GameManager;
import model.Champion;
import model.Weapon;
import views.PurchasableCard.CardType;

public class ShopView extends JPanel {

	private static final long serialVersionUID = 2101264902458190410L;
	
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Create the panel.
	 */
	public ShopView() {
		setName("Shop");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(new HeaderPanel("Champions"));
		addAvailableChampionsPanel();
		add(new HeaderPanel("Weapons"));
		addAvailableWeaponsPanel();
	}

	/**
	 * Add a purchasable panel to display the shop's available champions.
	 */
	private void addAvailableChampionsPanel() {
		JPanel championsPanel = new JPanel(new FlowLayout());
		for (Champion champion : gameManager.getShop().getAvailableChampions()) {
			championsPanel.add(new PurchasableCard(champion, CardType.CAN_BUY));
		}
		add(championsPanel);
	}

	/**
	 * Add a purchasable panel to display the shop's available weapons.
	 */
	private void addAvailableWeaponsPanel() {
		JPanel weaponsPanel = new JPanel(new FlowLayout());
		for (Weapon weapon : gameManager.getShop().getAvailableWeapons()) {
			weaponsPanel.add(new PurchasableCard(weapon, CardType.CAN_BUY));
		}
		add(weaponsPanel);
	}

}
