package views;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import display.GraphicalDisplay;
import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.Weapon;

public class TeamView extends JPanel {

	private static final long serialVersionUID = -8010724197066539267L;

	private GameManager gameManager = GameManager.getInstance();

	private JPanel mainPanel;

	private ImageIcon icon = new ImageIcon(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "team.jpg");

	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Club");
		setLayout(new GridBagLayout());

		mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);

		addChampionsHeader();
		addChampionsPanel();
		addWeaponsHeader();
		addAllWeaponsPanel();
	}

	private void addChampionsHeader() {
		mainPanel.add(new HeaderPanel("Your champions", true));
		mainPanel.add(new HeaderPanel("You need at least four champions to start a match", false));
	}

	/**
	 * Add a purchasable panel to display the team's champions.
	 */
	private void addChampionsPanel() {
		JPanel championsPanel = new JPanel(new FlowLayout());
		championsPanel.setOpaque(false);
		ArrayList<Champion> champions = gameManager.getPlayerTeam().getChampions();
		for (int i = 0; i < Configuration.NUM_CHAMPIONS; i++) {
			PurchasableCard card;
			if (champions.size() > i) {
				Champion champion = champions.get(i);
				card = new ChampionCard(champion);
				card.addStatsPanel();
				card.addSellButton();
			} else {
				card = new ChampionCard();
			}
			championsPanel.add(card);
		}
		mainPanel.add(championsPanel);
	}

	private void addWeaponsHeader() {
		mainPanel.add(new HeaderPanel("Your weapons", true));
		mainPanel.add(new HeaderPanel("You can assign your weapons to champions during match setup", false));
	}

	/**
	 * Add a purchasable panel to display all the team's weapons.
	 */
	private void addAllWeaponsPanel() {
		JPanel weaponsPanel = new JPanel(new FlowLayout());
		weaponsPanel.setOpaque(false);
		ArrayList<Weapon> weapons = gameManager.getPlayerTeam().getWeapons();
		for (int i = 0; i < Configuration.NUM_WEAPONS; i++) {
			PurchasableCard card;
			if (weapons.size() > i) {
				Weapon weapon = weapons.get(i);
				card = new WeaponCard(weapon);
				card.addStatsPanel();
				card.addSellButton();
			} else {
				card = new WeaponCard();
			}
			weaponsPanel.add(card);
		}
		mainPanel.add(weaponsPanel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int yPos = (GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2;
		g.drawImage(icon.getImage(), 0, yPos, null);
	}

}
