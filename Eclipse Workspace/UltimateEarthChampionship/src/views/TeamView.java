package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.Weapon;

public class TeamView extends JPanel {

	private static final long serialVersionUID = -8010724197066539267L;
	
	private GameManager gameManager = GameManager.getInstance();
	private Configuration config = Configuration.getInstance();
	
	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Team");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(new HeaderPanel("Champions"));
		addChampionsPanel();
		add(new HeaderPanel("Weapons"));
		addAllWeaponsPanel();
	}

	/**
	 * Add a purchasable panel to display the team's champions.
	 */
	private void addChampionsPanel() {
		JPanel championsPanel = new JPanel(new FlowLayout());
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
		add(championsPanel);
	}
	
	/**
	 * Add a purchasable panel to display all the team's weapons.
	 */
	private void addAllWeaponsPanel() {
		JPanel weaponsPanel = new JPanel(new FlowLayout());
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
		add(weaponsPanel);
	}

}
