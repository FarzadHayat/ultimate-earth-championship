package views;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
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
	private Configuration config = Configuration.getInstance();
	
	private JPanel mainPanel;

	private ImageIcon icon;
	
	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Team");
		setLayout(new GridBagLayout());
		
		icon = new ImageIcon(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "team.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(GraphicalDisplay.WIDTH,
								GraphicalDisplay.WIDTH, Image.SCALE_SMOOTH));
		
		mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		
		mainPanel.add(new HeaderPanel("Champions"));
		addChampionsPanel();
		mainPanel.add(new HeaderPanel("Weapons"));
		addAllWeaponsPanel();
	}

	/**
	 * Add a purchasable panel to display the team's champions.
	 */
	private void addChampionsPanel() {
		JPanel championsPanel = new JPanel(new FlowLayout());
		championsPanel.setOpaque(false);
		ArrayList<Champion> champions = gameManager.getPlayerTeam().getChampions();
		for (int i = 0; i < config.NUM_CHAMPIONS; i++) {
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
	
	/**
	 * Add a purchasable panel to display all the team's weapons.
	 */
	private void addAllWeaponsPanel() {
		JPanel weaponsPanel = new JPanel(new FlowLayout());
		weaponsPanel.setOpaque(false);
		ArrayList<Weapon> weapons = gameManager.getPlayerTeam().getWeapons();
		for (int i = 0; i < config.NUM_WEAPONS; i++) {
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
	    int yPos = (int) ((GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2);
        g.drawImage(icon.getImage(), 0, yPos, null);
	}

}
