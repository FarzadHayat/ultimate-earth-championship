package views;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.Purchasable;
import weapons.Weapon;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;

public class PurchasablePanel extends JPanel {

	private static final long serialVersionUID = 5339330332731208144L;
	
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = IMAGE_WIDTH;
	
	private static final int WIDTH = IMAGE_WIDTH;
	private static final int HEIGHT = WIDTH + 50;

	/**
	 * Create the panel.
	 * * @param weapon the weapon to display
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
	 * Create the panel with sell button to be used for weapons that are owned by the player.
	 * @param weapon the weapon to display
	 * @param isOwned true if the weapon is owned by the player
	 */
	public PurchasablePanel(Purchasable purchasable, boolean isOwned) {
		this(purchasable);
		
		if (isOwned) {
			JButton sellWeaponButton = new JButton("Sell for $" + purchasable.getPrice());
			add(sellWeaponButton, BorderLayout.SOUTH);
		}
	}
	
	/**
	 * Create the panel with buy button to be used for weapons that are in the shop.
	 * @param weapon the weapon to display
	 * @param isOwned true if the weapon is owned by the player
	 * @param isInShop true if the weapon is available in the shop to be purchased
	 */
	public PurchasablePanel(Purchasable purchasable, boolean isOwned, boolean isInShop) {
		this(purchasable, isOwned);
		
		if (isInShop) {
			JButton buyWeaponButton = new JButton("Buy for $" + purchasable.getPrice());
			add(buyWeaponButton, BorderLayout.SOUTH);
		}
	}
}
