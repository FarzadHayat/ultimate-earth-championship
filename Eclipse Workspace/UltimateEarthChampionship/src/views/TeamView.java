package views;

import javax.swing.JPanel;

import weapons.*;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class TeamView extends JPanel {

	private static final long serialVersionUID = -8010724197066539267L;

	/**
	 * Create the panel.
	 */
	public TeamView() {
		setName("Team");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel weaponPanel= new JPanel();
		weaponPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(weaponPanel);
		
		JPanel weapon_1 = new WeaponPanel(new Shield(), true);
		weaponPanel.add(weapon_1);
		
		JPanel weapon_2 = new WeaponPanel(new Chainsaw());
		weaponPanel.add(weapon_2);
	}

}
