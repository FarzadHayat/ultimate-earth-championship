package views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Purchasable;

public class PurchasablesPanel extends JPanel {

	private static final long serialVersionUID = -943950787490768336L;

	/**
	 * Create the panel.
	 */
	public PurchasablesPanel(ArrayList<Purchasable> purchasables, CardType type) {
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		for (Purchasable purchasable : purchasables) {
			PurchasableCard purchasableCard = new PurchasableCard(purchasable, type);
			add(purchasableCard);
		}
	}

}
