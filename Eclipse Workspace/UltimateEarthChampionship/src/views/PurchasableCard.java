package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;

import exception.FullTeamException;
import exception.IncompleteTeamException;
import exception.InsufficientFundsException;
import manager.GameManager;
import manager.GraphicalGameManager;
import model.Champion;
import model.Purchasable;
import model.Weapon;

public class PurchasableCard extends JPanel {

	private static final long serialVersionUID = 5339330332731208144L;
	
	private static final int IMAGE_WIDTH = 100;
	private static final int IMAGE_HEIGHT = IMAGE_WIDTH;
	
	private static final int WIDTH = IMAGE_WIDTH + 100;
	private static final int HEIGHT = IMAGE_HEIGHT + 50;
	
	private Purchasable purchasable;
	
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();
	
	private JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
	private JPanel soldOverlay;
	
	/**
	 * Enum to represent the card type.
	 * MINIMAL: small form factor with just a name and image.
	 * STANDARD: a minimal card with stat labels.
	 * CAN_BUY: a standard card with a buy button.
	 * CAN_SELL: a standard card with a sell button.
	 * SELECTABLE: a standard card where clicking it adds/removes it in the roster.
	 */
	public enum CardType {
		MINIMAL,
		STANDARD,
		CAN_BUY,
		CAN_SELL,
		SELECTABLE
	}
		
	/**
	 * Create the panel.
	 * @param purchasable the purchasable to display
	 * @param cardType the type of the card according to the CardType enum
	 */
	public PurchasableCard(Purchasable purchasable, CardType cardType) {
		setBackground(Color.yellow);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		if (purchasable == null) {
			setBackground(Color.gray);
			return;
		}
		if (gameManager.getPlayerTeam().getChosenChampions().contains(purchasable) ||
				gameManager.getPlayerTeam().getChosenWeapons().contains(purchasable)) {
			setBackground(Color.green);
		}
		mainPanel.setOpaque(false);
		setLayout(new OverlayLayout(this));
		add(mainPanel);		
		this.purchasable = purchasable;
		
		addName();
		addImageIcon();
		
		switch (cardType) {
		case MINIMAL: {
			break;
		}
		case STANDARD: {
			addStatsPanel();
			break;
		}
		case CAN_BUY: {
			addStatsPanel();
			if (purchasable.getClass().getSuperclass() == Champion.class && gameManager.getPlayerTeam().getChampions().contains((Champion) purchasable) ||
					purchasable.getClass().getSuperclass() == Weapon.class && gameManager.getPlayerTeam().getWeapons().contains((Weapon) purchasable)) {
				addSoldOverlay();
			} else {
				addBuyButton();
			}
			break;
		}
		case CAN_SELL: {
			addStatsPanel();
			addSellButton();
			break;
		}
		case SELECTABLE: {
			addRosterToggle();
			break;
		}
		}
		
	}

	private void addRosterToggle() {
		addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (gameManager.getPlayerTeam().getChosenChampions().contains(purchasable) ||
						gameManager.getPlayerTeam().getChosenWeapons().contains(purchasable)) {
					setBackground(Color.green);
				} else {
					setBackground(Color.yellow);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.orange);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (purchasable.getClass().getSuperclass() == Champion.class) {
					if (gameManager.getPlayerTeam().getChosenChampions().contains(purchasable)) {
						gameManager.getPlayerTeam().removeChosenChampion((Champion) purchasable);
					} else {
						try {
							gameManager.getPlayerTeam().addChosenChampion((Champion) purchasable);
						} catch (FullTeamException e1) {
							JOptionPane.showMessageDialog(getParent(), e1.getMessage());
						};
					}
					gameManager.repaintChampionSetup();
				}
				if (purchasable.getClass().getSuperclass() == Weapon.class) {
					if (gameManager.getPlayerTeam().getChosenWeapons().contains(purchasable)) {
						gameManager.getPlayerTeam().removeChosenWeapon((Weapon) purchasable);
					} else {
						try {
							gameManager.getPlayerTeam().addChosenWeapon((Weapon) purchasable);
						} catch (FullTeamException e1) {
							JOptionPane.showMessageDialog(getParent(), e1.getMessage());
						};
					}
					gameManager.repaintWeaponSetup();
				}
			}
		});
	}

	private void addSoldOverlay() {
		soldOverlay = new JPanel(new GridBagLayout());
		soldOverlay.setBackground(new Color(0.6f, 0.6f, 0.6f, 1f));
		JLabel soldLabel = new JLabel("SOLD!");
		soldLabel.setFont(new Font("Arial", Font.BOLD, 24));
		soldLabel.setForeground(Color.red);
		soldOverlay.add(soldLabel);
		add(soldOverlay);
	}

	/**
	 * Adds a label for the name to the north of the card.
	 */
	private void addName() {
		JLabel nameLabel = new JLabel(purchasable.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(nameLabel, BorderLayout.NORTH);
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
		mainPanel.add(imageLabel, BorderLayout.WEST);
	}
	
	/**
	 * Add a panel displaying the purchasable stats to the center panel.
	 */
	public void addStatsPanel() {
		JPanel statsPanel = new JPanel(new GridLayout(0, 1));
		statsPanel.setOpaque(false);
		mainPanel.add(statsPanel, BorderLayout.EAST);
	    if (purchasable.getClass().getSuperclass() == Champion.class) {
	    	Champion champion = (Champion) purchasable; 
	    	statsPanel.add(new StatLabel("health.png", String.valueOf(champion.getHealth())));
	    	statsPanel.add(new StatLabel("stamina.png", String.valueOf(champion.getStamina())));
	    	statsPanel.add(new StatLabel("offense.png", String.valueOf(champion.getOffense())));
	    	statsPanel.add(new StatLabel("damage.png", String.valueOf(champion.getDamage())));
	    	statsPanel.add(new StatLabel("defense.png", String.valueOf(champion.getDefense())));
	    }
	    if (purchasable.getClass().getSuperclass() == Weapon.class) {
	    	Weapon weapon = (Weapon) purchasable;
	    	statsPanel.add(new StatLabel("offense.png", String.valueOf(weapon.getOffenseBoost())));
	    	statsPanel.add(new StatLabel("damage.png", String.valueOf(weapon.getDamageMultiplier())));
	    	statsPanel.add(new StatLabel("defense.png", String.valueOf(weapon.getDefenseBoost())));
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
						gameManager.repaintShop();
					}
				} catch (InsufficientFundsException | FullTeamException e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage());
				}
			}
		});
	    mainPanel.add(buyButton, BorderLayout.SOUTH);
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
						gameManager.repaintTeam();
					}
				} catch (IncompleteTeamException e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage());
				}
			}
		});
	    mainPanel.add(sellButton, BorderLayout.SOUTH);
	}

}
