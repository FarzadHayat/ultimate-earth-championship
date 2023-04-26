package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import exception.FullTeamException;
import exception.IncompleteTeamException;
import exception.InsufficientFundsException;
import manager.GameManager;
import model.Champion;
import model.Purchasable;
import model.Weapon;

public class PurchasableCard extends JPanel {

	private static final long serialVersionUID = 5339330332731208144L;
	
	private static final int IMAGE_WIDTH = 150;
	private static final int IMAGE_HEIGHT = IMAGE_WIDTH;
	
	private static final int WIDTH = IMAGE_WIDTH + 50;
	private static final int HEIGHT = IMAGE_HEIGHT + 110;
	
	private Purchasable purchasable;
	
	private GameManager gameManager = GameManager.getInstance();
	
	private JPanel centerPanel = new JPanel(new FlowLayout());
		
	/**
	 * Create the panel.
	 * @param purchasable the purchasable to display
	 * @param cardType the type of the card according to the CardType enum
	 */
	public PurchasableCard(Purchasable purchasable, CardType cardType) {
		setBackground(Color.orange);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new BorderLayout(0, 0));
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);
		this.purchasable = purchasable;
		
		addName();
		addImageIcon();
		
		switch (cardType) {
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
		case MINIMAL:
			break;
		default:
			break;
		}
		
	}

	/**
	 * Adds a label for the name to the north of the card.
	 */
	private void addName() {
		JLabel nameLabel = new JLabel(purchasable.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(nameLabel, BorderLayout.NORTH);
	}

	/**
	 * Adds the image icon to the center panel.
	 */
	private void addImageIcon() {
		ImageIcon icon = purchasable.getImage();
		ImageIcon resizedIcon;
		if (icon != null) {
			resizedIcon = new ImageIcon(icon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		}
		else {
			resizedIcon = new ImageIcon(new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB));
		}
		JLabel imageLabel = new JLabel(resizedIcon);
		centerPanel.add(imageLabel);
	}
	
	/**
	 * Adds a label displaying the purchasable stats to the center panel.
	 */
	public void addStatsLabel() {
	    if (purchasable.getClass().getSuperclass() == Champion.class) {
	    	Champion champion = (Champion) purchasable; 
	    	centerPanel.add(new JLabel("Health: " + String.valueOf(champion.getHealth())));
	    	centerPanel.add(new JLabel("Stamina: " + String.valueOf(champion.getStamina())));
	    	centerPanel.add(new JLabel("Offense: " + String.valueOf(champion.getOffense())));
	    	centerPanel.add(new JLabel("Damage: " + String.valueOf(champion.getDamage())));
	    	centerPanel.add(new JLabel("Defense: " + String.valueOf(champion.getDefense())));
	    }
	    if (purchasable.getClass().getSuperclass() == Weapon.class) {
	    	Weapon weapon = (Weapon) purchasable;
	    	centerPanel.add(new JLabel("Offense boost: " + String.valueOf(weapon.getOffenseBoost())));
	    	centerPanel.add(new JLabel("Damage boost: " + String.valueOf(weapon.getDamageMultiplier())));
	    	centerPanel.add(new JLabel("Defense boost: " + String.valueOf(weapon.getDefenseBoost())));
	    }
	}

	/**
	 * Add a buy button to the south of the card.
	 */
	public void addBuyButton() {
	    JButton buyButton = new JButton("Buy for $" + purchasable.getPrice());
	    buyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = String.format("Are you sure you want to buy %s for $%s?",
							purchasable.getName(), purchasable.getPrice());
					int answer = JOptionPane.showConfirmDialog(getParent(), message,
							"Buy: " + purchasable.getName(), JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						gameManager.getPlayerTeam().buy(purchasable);
						gameManager.visitShop();
					}
				} catch (InsufficientFundsException | FullTeamException e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage());
				}
			}
		});
	    add(buyButton, BorderLayout.SOUTH);
	}

	/**
	 * Add a sell button the purchasable to the south of the card.
	 */
	public void addSellButton() {
	    JButton sellButton = new JButton("Sell for $" + purchasable.getPrice());
	    sellButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = String.format("Are you sure you want to sell %s for $%s?",
							purchasable.getName(), purchasable.getPrice());
					int answer = JOptionPane.showConfirmDialog(getParent(), message,
							"Sell: " + purchasable.getName(), JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						gameManager.getPlayerTeam().sell(purchasable);
						gameManager.visitTeam();
					}
				} catch (IncompleteTeamException e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage());
				}
			}
		});
	    add(sellButton, BorderLayout.SOUTH);
	}

}
