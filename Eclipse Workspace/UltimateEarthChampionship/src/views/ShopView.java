package views;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import weapons.Chainsaw;
import weapons.Shield;

public class ShopView extends JPanel {

	private static final long serialVersionUID = 2101264902458190410L;

	/**
	 * Create the panel.
	 */
	public ShopView() {
		setName("Shop");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel weaponPanel = new JPanel();
		weaponPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(weaponPanel);
		
		JPanel weapon_1 = new WeaponPanel(new Shield(), false, true);
		weaponPanel.add(weapon_1);
		
		JPanel weapon_2 = new WeaponPanel(new Chainsaw(), false, true);
		weaponPanel.add(weapon_2);
	}

}
