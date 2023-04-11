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
		
		JPanel teamPanel= new JPanel();
		teamPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(teamPanel);
		
		JPanel weaponPanel_1 = new WeaponPanel(new Shield(), true);
		teamPanel.add(weaponPanel_1);
		
		JPanel weaponPanel_2 = new WeaponPanel(new Chainsaw());
		teamPanel.add(weaponPanel_2);
	}

}
