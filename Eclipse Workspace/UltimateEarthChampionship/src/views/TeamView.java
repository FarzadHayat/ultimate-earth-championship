package views;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import champion.Champion;
import main.GameManager;
import main.Purchasable;
import weapon.Weapon;

public class TeamView extends JPanel {

	private static final long serialVersionUID = -8010724197066539267L;
	
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Team");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		addChosenChampionsPanel();
		addReserveChampionsPanel();
		addAllWeaponsPanel();
	}

	/**
	 * Add a purchasable panel to display the team's chosen champions.
	 */
	private void addChosenChampionsPanel() {
		ArrayList<Purchasable> chosenChampions = new ArrayList<Purchasable>();
		for (Champion champion : gameManager.getPlayerTeam().getChosenChampions()) {
			chosenChampions.add(champion);
		}
		PurchasablesPanel chosenChampionsPanel = new PurchasablesPanel(chosenChampions, CardType.CAN_SELL);
		add(chosenChampionsPanel);
	}
	
	/**
	 * Add a purchasable panel to display the team's reserve champions.
	 */
	private void addReserveChampionsPanel() {
		ArrayList<Purchasable> reserveChampions = new ArrayList<Purchasable>();
		for (Champion champion : gameManager.getPlayerTeam().getReserveChampions()) {
			reserveChampions.add(champion);
		}
		PurchasablesPanel reserveChampionsPanel = new PurchasablesPanel(reserveChampions, CardType.CAN_SELL);
		add(reserveChampionsPanel);
	}
	
	/**
	 * Add a purchasable panel to display all the team's weapons.
	 */
	private void addAllWeaponsPanel() {
		ArrayList<Purchasable> allWeapons = new ArrayList<Purchasable>();
		for (Weapon weapon : gameManager.getPlayerTeam().getAllWeapons()) {
			allWeapons.add(weapon);
		}
		PurchasablesPanel allWeaponsPanel = new PurchasablesPanel(allWeapons, CardType.CAN_SELL);
		add(allWeaponsPanel);
	}

}
