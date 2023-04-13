package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.Purchasable;

public class PurchasableCard extends JPanel {

	private static final long serialVersionUID = 5339330332731208144L;
	
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = IMAGE_WIDTH;
	
	private static final int WIDTH = IMAGE_WIDTH;
	private static final int HEIGHT = WIDTH + 50;

	
	private Purchasable purchasable;
	
	/**
	 * Create the panel.
	 * @param purchasable the purchasable to display
	 * @param type the type of the card according to the CardType enum
	 */
	public PurchasableCard(Purchasable purchasable, CardType type) {
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout(0, 0));
		
		this.purchasable = purchasable;
		
		JLabel weaponNameLabel = new JLabel(purchasable.getName());
		weaponNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(weaponNameLabel, BorderLayout.NORTH);
		
		ImageIcon icon = purchasable.getImage();
		ImageIcon resizedIcon;
		if (icon != null) {
			resizedIcon = new ImageIcon(icon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		}
		else {
			resizedIcon = new ImageIcon(new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB));
		}
		JLabel weaponImageLabel = new JLabel(resizedIcon);
		add(weaponImageLabel, BorderLayout.CENTER);
		
		switch (type) {
		case STANDARD: {
			addStatsLabel();
			break;
		}
		case CAN_BUY: {
			addStatsLabel();
			addBuyButton();
			break;
		}
		case CAN_SELL: {
			addStatsLabel();
			addSellButton();
			break;
		}
		}
	}
	
	/**
	 * Adds a label displaying the stats of the purchasable.
	 */
	public void addStatsLabel() {
	    ;
	}

	/**
	 * Adds a button for buying the purchasable.
	 */
	public void addBuyButton() {
	    JButton buyWeaponButton = new JButton("Buy for $" + purchasable.getPrice());
	    add(buyWeaponButton, BorderLayout.SOUTH);
	}

	/**
	 * Adds a button for selling the purchasable.
	 */
	public void addSellButton() {
	    JButton sellWeaponButton = new JButton("Sell for $" + purchasable.getPrice());
	    add(sellWeaponButton, BorderLayout.SOUTH);
	}

}
