package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import exception.FullTeamException;
import exception.IllegalPurchaseException;
import exception.InsufficientFundsException;
import manager.GameManager;
import manager.GraphicalGameManager;
import model.Champion;
import model.Configuration;
import model.Purchasable;

/**
 * An abstract class for displaying a purchasable in the form of a panel. The
 * constructor creates a basic card with just the name and the image of the
 * purchasable. The other methods allow you to customize the card by adding
 * other features.
 */
public abstract class PurchasableCard extends JPanel {

	private static final long serialVersionUID = 5339330332731208144L;

	protected static final int IMAGE_WIDTH = 100;
	protected static final int IMAGE_HEIGHT = IMAGE_WIDTH;

	protected static final int WIDTH = IMAGE_WIDTH + 70;
	protected static final int HEIGHT = IMAGE_HEIGHT + 50;

	/**
	 * The purchasable shown by this card
	 */
	private Purchasable purchasable;

	/**
	 * The game manager
	 */
	protected GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();

	/**
	 * The main panel
	 */
	protected JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
	/**
	 * The sold overlay to be displayed after purchase
	 */
	protected JPanel soldOverlay;

	protected PurchasableCard() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.lightGray);
		unselected();
	}

	/**
	 * Create the panel.
	 *
	 * @param purchasable the purchasable to display
	 */
	protected PurchasableCard(Purchasable purchasable) {
		this();
		this.purchasable = purchasable;
		mainPanel.setOpaque(false);
		setLayout(new OverlayLayout(this));
		add(mainPanel);

		addName();
		addImageIcon();
	}

	/**
	 * Set the state of the card to become selected.
	 */
	public void selected() {
		setBorder(new LineBorder(Configuration.GREEN, 3));
	}

	/**
	 * Set the state of the card to become hovered.
	 */
	public void hovered() {
		setBorder(new LineBorder(Configuration.ORANGE, 3));
	}

	/**
	 * Set the state of the card to become unselected.
	 */
	public void unselected() {
		setBorder(new LineBorder(Color.darkGray, 3));
	}

	/**
	 * Adds a label for the name to the north of the card.
	 */
	private void addName() {
		JLabel nameLabel = new JLabel(purchasable.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(Configuration.TEXT_FONT);
		mainPanel.add(nameLabel, BorderLayout.NORTH);
	}

	/**
	 * Adds the image icon to the center panel.
	 */
	private void addImageIcon() {
		ImageIcon icon = purchasable.getImage();
		ImageIcon resizedIcon;
		if (icon != null) {
			resizedIcon = new ImageIcon(
					icon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		} else {
			resizedIcon = new ImageIcon(new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB));
		}
		JLabel imageLabel = new JLabel(resizedIcon);
		mainPanel.add(imageLabel, BorderLayout.WEST);
	}

	/**
	 * Add an overlay on top of the card containing the given text in a red color.
	 *
	 * @param text the text to display on the overlay
	 */
	public void addOverlay(String text) {
		soldOverlay = new JPanel(new GridBagLayout());
		soldOverlay.setBackground(new Color(0.7f, 0.7f, 0.7f, 0.7f));
		JLabel soldLabel = new JLabel(text);
		soldLabel.setFont(Configuration.HEADER_FONT);
		soldLabel.setForeground(Configuration.RED);
		soldOverlay.add(soldLabel);
		add(soldOverlay, 0);
	}

	/**
	 * Add a buy button to the south of the card.
	 */
	public void addBuyButton() {
		JButton buyButton = new JButton("Buy for $" + (int) purchasable.getPrice());
		buyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = String.format("Are you sure you want to buy %s for $%s?", purchasable.getName(),
							(int) purchasable.getPrice());
					int answer = JOptionPane.showConfirmDialog(getParent(), message, "Buy: " + purchasable.getName(),
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						purchasable.buy(gameManager.getPlayerTeam());
						gameManager.repaintShop();
					}
				} catch (InsufficientFundsException | FullTeamException | IllegalPurchaseException e) {
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
				if (purchasable.getClass().getSuperclass() == Champion.class
						&& gameManager.getPlayerTeam().getChampions().size() <= Configuration.NUM_CHOSEN_CHAMPIONS) {
					JOptionPane.showMessageDialog(getParent(),
							"You will not have enough champions to start a match after this!",
							"INCOMPLETE TEAM WARNING", JOptionPane.WARNING_MESSAGE);
				}
				String message = String.format("Are you sure you want to sell %s for $%s?", purchasable.getName(),
						purchasable.getPrice());
				int answer = JOptionPane.showConfirmDialog(getParent(), message, "Sell: " + purchasable.getName(),
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					purchasable.sell(gameManager.getPlayerTeam());
					gameManager.repaintTeam();
				}
			}
		});
		mainPanel.add(sellButton, BorderLayout.SOUTH);
	}

	/**
	 * Add a panel to display the purchasable stats. Implemented by the individual
	 * subclasses.
	 */
	public abstract void addStatsPanel();

}
