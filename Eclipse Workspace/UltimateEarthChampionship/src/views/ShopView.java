package views;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import manager.GameManager;
import model.Champion;
import model.Weapon;

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
			PurchasableCard card = new ChampionCard(champion);
			card.addStatsPanel();
			card.addBuyButton();
			championsPanel.add(card);
			if (gameManager.getPlayerTeam().getChampions().contains(champion)) {
				card.addOverlay("SOLD!");
			} else {
				if (gameManager.getPlayerTeam().isWeeklyChampionPurchased()) {
					card.addOverlay("");
				}
			}
		}
		add(championsPanel);
	}

	/**
	 * Add a purchasable panel to display the shop's available weapons.
	 */
	private void addAvailableWeaponsPanel() {
		JPanel weaponsPanel = new JPanel(new FlowLayout());
		for (Weapon weapon : gameManager.getShop().getAvailableWeapons()) {
			PurchasableCard card = new WeaponCard(weapon);
			card.addStatsPanel();
			card.addBuyButton();
			weaponsPanel.add(card);
		}
		add(weaponsPanel);
	}

}
