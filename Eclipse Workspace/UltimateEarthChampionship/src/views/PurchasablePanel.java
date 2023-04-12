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

public class PurchasablePanel extends JPanel {

	private static final long serialVersionUID = 5339330332731208144L;
	
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = IMAGE_WIDTH;
	
	private static final int WIDTH = IMAGE_WIDTH;
	private static final int HEIGHT = WIDTH + 50;

	/**
	 * Create the panel.
	 * * @param purchasable the purchasable to display
	 * @wbp.parser.constructor
	 */
	public PurchasablePanel(Purchasable purchasable) {
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout(0, 0));
		
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
		}
	
	/**
	 * Create the panel with optional buy and sell buttons.
	 * @param purchasable the purchasable to display
	 * @param canBuy true if the player should be able to buy the purchasable
	 * @param canSell true if the player should be able to sell the purchasable
	 */
	public PurchasablePanel(Purchasable purchasable, boolean canBuy, boolean canSell) {
		this(purchasable);
		
		if (canBuy) {
			JButton buyWeaponButton = new JButton("Buy for $" + purchasable.getPrice());
			add(buyWeaponButton, BorderLayout.SOUTH);
		}
		if (canSell) {
			JButton sellWeaponButton = new JButton("Sell for $" + purchasable.getPrice());
			add(sellWeaponButton, BorderLayout.SOUTH);
		}
	}

}
