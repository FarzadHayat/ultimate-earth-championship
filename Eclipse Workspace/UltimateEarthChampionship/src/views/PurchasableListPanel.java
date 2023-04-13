package views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import main.Purchasable;

public class PurchasableListPanel extends JPanel {

	private static final long serialVersionUID = -943950787490768336L;

	/**
	 * Create the panel.
	 */
	public PurchasableListPanel(ArrayList<Purchasable> purchasableList, CardType type) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		for (Purchasable purchasable : purchasableList) {
			JPanel purchasableCard = new PurchasableCard(purchasable, type);
			add(purchasableCard);
		}
	}

}
