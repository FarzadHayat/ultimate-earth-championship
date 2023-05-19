package views;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import display.GraphicalDisplay;
import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.Weapon;

/**
 * A panel that display the shop. The shop contains a subpanel for the available
 * champions and a subpanel for the available weapons.
 */
public class ShopView extends JPanel {

	private static final long serialVersionUID = 2101264902458190410L;

	/**
	 * The game manager instance
	 */
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * The main panel
	 */
	private JPanel mainPanel;

	/**
	 * The image icon for the background image
	 */
	private ImageIcon icon = new ImageIcon(
			getClass().getResource(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "shop.jpg"));

	/**
	 * Create the panel.
	 */
	public ShopView() {
		setName("Market");
		setLayout(new GridBagLayout());

		mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);

		addChampionsHeader();
		addAvailableChampionsPanel();
		addWeaponsHeader();
		addAvailableWeaponsPanel();
	}

	/**
	 * Add the header for the champions subpanel.
	 */
	private void addChampionsHeader() {
		mainPanel.add(new HeaderPanel("Shop champions", true));
		mainPanel.add(new HeaderPanel("You can only buy one champion per week", false));
	}

	/**
	 * Add the header for weapons subpanel.
	 */
	private void addWeaponsHeader() {
		mainPanel.add(new HeaderPanel("Shop weapons", true));
		mainPanel.add(new HeaderPanel("You can buy as many weapons as you want", false));
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
			championsPanel.add(card);
			if (gameManager.getPlayerTeam().getChampions().contains(champion)) {
				card.addOverlay("SOLD!");
			} else {
				if (gameManager.getPlayerTeam().isWeeklyChampionPurchased()) {
					card.addOverlay("");
				} else {
					card.addBuyButton();
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

	/**
	 * Paint the component then draw the background image onto the component.<br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @param g the graphics object to draw onto
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int yPos = (GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2;
		g.drawImage(icon.getImage(), 0, yPos, null);
	}

}
