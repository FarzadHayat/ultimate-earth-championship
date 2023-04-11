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
		
		JPanel weaponsPanel= new JPanel();
		weaponsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		add(weaponsPanel);
		
		JPanel weaponPanel_1 = new PurchasablePanel(new Shield(), true, false);
		weaponsPanel.add(weaponPanel_1);
		
		JPanel weaponPanel_2 = new PurchasablePanel(new Chainsaw(), true, false);
		weaponsPanel.add(weaponPanel_2);
	}

}
