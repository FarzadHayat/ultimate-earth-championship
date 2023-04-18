package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import display.GraphicalDisplay;
import exception.InsufficientFundsException;
import exception.TeamFullException;
import main.GameManager;
import main.GraphicalGameManager;
import weapon.Weapon;

public class WeaponCard extends JPanel {

	private static final long serialVersionUID = 5339330332731208144L;
	
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = IMAGE_WIDTH;
	
	private static final int WIDTH = IMAGE_WIDTH;
	private static final int HEIGHT = WIDTH + 50;
	
	private Weapon weapon;
	
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();
		
	/**
	 * Create the panel.
	 * @param weapon the weapon to display
	 * @param type the type of the card according to the CardType enum
	 */
	public WeaponCard(Weapon weapon, CardType type) {
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout(0, 0));
		
		this.weapon = weapon;
		
		JLabel weaponNameLabel = new JLabel(weapon.getName());
		weaponNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(weaponNameLabel, BorderLayout.NORTH);
		
		ImageIcon icon = weapon.getImage();
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
	 * Adds a label displaying the stats of the weapon.
	 */
	public void addStatsLabel() {
	    ;
	}

	/**
	 * Adds a button for buying the weapon.
	 */
	public void addBuyButton() {
	    JButton buyButton = new JButton("Buy for $" + weapon.getPrice());
	    buyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					gameManager.getShop().buyWeapon(weapon, gameManager.getPlayerTeam());
					GraphicalDisplay graphicalDisplay = gameManager.getGraphicalDisplay();
					graphicalDisplay.displayShop();
				} catch (InsufficientFundsException | TeamFullException e) {
					JOptionPane.showMessageDialog(buyButton, e.getMessage());
				}
			}
		});
	    add(buyButton, BorderLayout.SOUTH);
	}

	/**
	 * Adds a button for selling the weapon.
	 */
	public void addSellButton() {
	    JButton sellButton = new JButton("Sell for $" + weapon.getPrice());
	    add(sellButton, BorderLayout.SOUTH);
	}

}
