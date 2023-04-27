package views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.Purchasable;
import model.Weapon;
import views.PurchasableCard.CardType;

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
			Champion champion;
			if (champions.size() > i) {
				champion = champions.get(i);
			} else {
				champion = null;
			}
			championsPanel.add(new PurchasableCard(champion, CardType.CAN_SELL));
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
			Weapon weapon;
			if (weapons.size() > i) {
				weapon = weapons.get(i);
			} else {
				weapon = null;
			}
			weaponsPanel.add(new PurchasableCard(weapon, CardType.CAN_SELL));
		}
		add(weaponsPanel);
	}

}
