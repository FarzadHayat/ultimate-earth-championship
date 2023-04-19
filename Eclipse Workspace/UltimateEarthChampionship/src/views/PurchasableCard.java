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

import champion.Champion;
import display.GraphicalDisplay;
import exception.InsufficientFundsException;
import exception.FullTeamException;
import exception.IncompleteTeamException;
import main.GameManager;
import main.GraphicalGameManager;
import main.Purchasable;

public class PurchasableCard extends JPanel {

	private static final long serialVersionUID = 5339330332731208144L;
	
	private static final int IMAGE_WIDTH = 200;
	private static final int IMAGE_HEIGHT = IMAGE_WIDTH;
	
	private static final int WIDTH = IMAGE_WIDTH;
	private static final int HEIGHT = WIDTH + 50;
	
	private Purchasable purchasable;
	
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();
		
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
		
		JLabel nameLabel = new JLabel(purchasable.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(nameLabel, BorderLayout.NORTH);
		
		ImageIcon icon = purchasable.getImage();
		ImageIcon resizedIcon;
		if (icon != null) {
			resizedIcon = new ImageIcon(icon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		}
		else {
			resizedIcon = new ImageIcon(new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB));
		}
		JLabel imageLabel = new JLabel(resizedIcon);
		add(imageLabel, BorderLayout.CENTER);
		
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
	 * Add a buy button to the purchasable.
	 */
	public void addBuyButton() {
	    JButton buyButton = new JButton("Buy for $" + purchasable.getPrice());
	    buyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					gameManager.getPlayerTeam().buy(purchasable);
					GraphicalDisplay graphicalDisplay = gameManager.getGraphicalDisplay();
					graphicalDisplay.displayShop();
				} catch (InsufficientFundsException | FullTeamException e) {
					JOptionPane.showMessageDialog(buyButton, e.getMessage());
				}
			}
		});
	    add(buyButton, BorderLayout.SOUTH);
	}

	/**
	 * Add a sell button the purchasable.
	 */
	public void addSellButton() {
	    JButton sellButton = new JButton("Sell for $" + purchasable.getPrice());
	    sellButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					gameManager.getPlayerTeam().sell(purchasable);
					GraphicalDisplay graphicalDisplay = gameManager.getGraphicalDisplay();
					graphicalDisplay.displayTeam();
				} catch (IncompleteTeamException e) {
					JOptionPane.showMessageDialog(sellButton, e.getMessage());
				}
			}
		});
	    add(sellButton, BorderLayout.SOUTH);
	}

}
