package views;

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
	 * Add a purchasable panel to display the team's chosen champions.
	 */
	private void addChampionsPanel() {
		ArrayList<Purchasable> champions = new ArrayList<Purchasable>();
		for (Champion champion : gameManager.getPlayerTeam().getChampions()) {
			champions.add(champion);
		}
		PurchasablesPanel championsPanel = new PurchasablesPanel(champions, CardType.CAN_SELL);
		add(championsPanel);
	}
	
	/**
	 * Add a purchasable panel to display all the team's weapons.
	 */
	private void addAllWeaponsPanel() {
		ArrayList<Purchasable> weapons = new ArrayList<Purchasable>();
		for (Weapon weapon : gameManager.getPlayerTeam().getWeapons()) {
			weapons.add(weapon);
		}
		PurchasablesPanel weaponsPanel = new PurchasablesPanel(weapons, CardType.CAN_SELL);
		add(weaponsPanel);
	}

}
