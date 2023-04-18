package views;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import main.GameManager;

public class ShopView extends JPanel {

	private static final long serialVersionUID = 2101264902458190410L;
	
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Create the panel.
	 */
	public ShopView() {
		setName("Shop");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		ChampionsPanel championsPanel = new ChampionsPanel(gameManager.getShop().getAvailableChampions(), CardType.CAN_BUY);
		add(championsPanel);
		
		WeaponsPanel WeaponsPanel = new WeaponsPanel(gameManager.getShop().getAvailableWeapons(), CardType.CAN_BUY);
		add(WeaponsPanel);
	}

}
