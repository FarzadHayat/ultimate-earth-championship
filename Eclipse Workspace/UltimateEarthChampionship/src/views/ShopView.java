package views;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import display.GraphicalDisplay;
import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.Weapon;

public class ShopView extends JPanel {

	private static final long serialVersionUID = 2101264902458190410L;
	
	private GameManager gameManager = GameManager.getInstance();

	private JPanel mainPanel;
	
	/**
	 * Create the panel.
	 */
	public ShopView() {
		setName("Shop");
		setLayout(new GridBagLayout());
		mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		
		mainPanel.add(new HeaderPanel("Champions"));
		addAvailableChampionsPanel();
		mainPanel.add(new HeaderPanel("Weapons"));
		addAvailableWeaponsPanel();
	}

	/**
	 * Add a purchasable panel to display the shop's available champions.
	 */
	private void addAvailableChampionsPanel() {
		JPanel championsPanel = new JPanel(new FlowLayout());
		championsPanel.setOpaque(false);
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
		mainPanel.add(championsPanel);
	}

	/**
	 * Add a purchasable panel to display the shop's available weapons.
	 */
	private void addAvailableWeaponsPanel() {
		JPanel weaponsPanel = new JPanel(new FlowLayout());
		weaponsPanel.setOpaque(false);
		for (Weapon weapon : gameManager.getShop().getAvailableWeapons()) {
			PurchasableCard card = new WeaponCard(weapon);
			card.addStatsPanel();
			card.addBuyButton();
			weaponsPanel.add(card);
		}
		mainPanel.add(weaponsPanel);
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon icon = new ImageIcon(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "shop.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(GraphicalDisplay.WIDTH,
								GraphicalDisplay.WIDTH, Image.SCALE_SMOOTH));
	    int yPos = (int) ((GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2);
        g.drawImage(icon.getImage(), 0, yPos, null);
	}
	
}
