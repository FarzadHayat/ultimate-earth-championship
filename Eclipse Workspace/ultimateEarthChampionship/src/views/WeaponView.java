package views;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import weapons.Weapon;

public class WeaponView extends JPanel {

	private static final long serialVersionUID = 5339330332731208144L;

	/**
	 * Create the panel.
	 */
	public WeaponView(Weapon weapon) {
		setLayout(null);
		
		JLabel weaponNameLabel = new JLabel(weapon.getName());
		weaponNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weaponNameLabel.setBounds(0, 0, 300, 64);
		add(weaponNameLabel);
		
		JLabel weaponImageLabel = new JLabel(weapon.getImage());
		weaponImageLabel.setBounds(0, 76, 200, 200);
		add(weaponImageLabel);
		
	}
}
