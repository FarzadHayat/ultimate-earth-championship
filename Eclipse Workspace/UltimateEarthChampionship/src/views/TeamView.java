package views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import manager.GameManager;
import model.Champion;
import model.Purchasable;
import model.Weapon;

public class TeamView extends JPanel {

	private static final long serialVersionUID = -8010724197066539267L;
	
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Team");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		addChampionsPanel();
		addAllWeaponsPanel();
	}

	/**
	 * Add a purchasable panel to display the team's champions.
	 */
	private void addChampionsPanel() {
		JPanel championsPanel = new JPanel(new FlowLayout());
		for (Champion champion : gameManager.getPlayerTeam().getAllChampions()) {
			championsPanel.add(new PurchasableCard(champion, CardType.CAN_SELL));
		}
		add(championsPanel);
	}
	
	/**
	 * Add a purchasable panel to display all the team's weapons.
	 */
	private void addAllWeaponsPanel() {
		JPanel weaponsPanel = new JPanel(new FlowLayout());
		for (Weapon weapon : gameManager.getPlayerTeam().getAllWeapons()) {
			weaponsPanel.add(new PurchasableCard(weapon, CardType.CAN_SELL));
		}
		add(weaponsPanel);
	}

}
