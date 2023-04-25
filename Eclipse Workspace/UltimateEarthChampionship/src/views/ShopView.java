package views;

import java.awt.FlowLayout;
import java.awt.Font;
import model.Configuration;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
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
		
		addHeader("Champions");
		addAvailableChampionsPanel();
		addHeader("Weapons");
		addAvailableWeaponsPanel();
	}

	private void addHeader(String header) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(header);
		label.setFont(Configuration.HEADER_FONT);
		panel.add(label);
		add(panel);
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
