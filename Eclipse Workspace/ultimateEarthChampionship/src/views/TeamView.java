package views;

import javax.swing.JPanel;

import weapons.Shield;
import weapons.Weapon;

public class TeamView extends JPanel {

	private static final long serialVersionUID = -8010724197066539267L;

	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Team");
		
		Weapon weapon = new Shield();
		setLayout(null);
		JPanel weaponView = new WeaponPanel(weapon);
		weaponView.setBounds(0, 0, 300, 300);
		add(weaponView);
	}

}
