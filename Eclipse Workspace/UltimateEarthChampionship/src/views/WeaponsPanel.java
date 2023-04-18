package views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import weapon.Weapon;

public class WeaponsPanel extends JPanel {

	private static final long serialVersionUID = -943950787490768336L;

	/**
	 * Create the panel.
	 */
	public WeaponsPanel(ArrayList<Weapon> weapons, CardType type) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		for (Weapon weapon : weapons) {
			JPanel weaponCard = new WeaponCard(weapon, type);
			add(weaponCard);
		}
	}

}
