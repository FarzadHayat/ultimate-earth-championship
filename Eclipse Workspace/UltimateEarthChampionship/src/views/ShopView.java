package views;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import champion.Champion;
import main.GameManager;
import weapon.Weapon;

public class ShopView extends JPanel {

	private static final long serialVersionUID = 2101264902458190410L;
	
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Create the panel.
	 */
	public ShopView() {
		setName("Shop");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ArrayList<Champion> availableChampions = new ArrayList<Champion>();
		for (Champion champion : gameManager.getShop().getAvailableChampions()) {
			availableChampions.add(champion);
		}
		ChampionsPanel championListPanel = new ChampionsPanel(availableChampions, CardType.CAN_BUY);
		add(championListPanel);
		
		ArrayList<Weapon>availableWeapons = new ArrayList<Weapon>();
		for (Weapon weapon : gameManager.getShop().getAvailableWeapons()) {
			availableWeapons.add(weapon);
		}
		WeaponsPanel WeaponsPanel = new WeaponsPanel(availableWeapons, CardType.CAN_BUY);
		add(WeaponsPanel);
	}

}
