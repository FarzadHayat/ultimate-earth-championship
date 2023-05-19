package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import manager.GameManager;
import model.Champion;
import model.Configuration;

/**
 * A card to represent a champion that is currently on the grid in a live match.
 */
public class ChampionMatchCard extends JPanel {

	private static final long serialVersionUID = 8796554745910845194L;

	/**
	 * The game manager instance
	 */
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Champion shown in this match card
	 */
	private Champion champion;

	/**
	 * The main panel
	 */
	private JPanel mainPanel;

	/**
	 * The name label
	 */
	private JLabel nameLabel;
	/**
	 * The stamina bar
	 */
	private JProgressBar staminaBar;
	/**
	 * The center panel
	 */
	private JPanel panel;
	/**
	 * The image label
	 */
	private JLabel imageLabel;

	/**
	 * The center panel
	 */
	private JPanel centerPanel;

	/**
	 * Get the champion attached to the card.
	 *
	 * @return the champion object
	 */
	public Champion getChampion() {
		return champion;
	}

	/**
	 * Constructor
	 *
	 * @param centerGrid The grid to be added to
	 * @param row        The lane for this card to sit in
	 * @param column     The position along the aforementioned lane
	 */
	public ChampionMatchCard(JPanel centerGrid, int row, int column) {

		if (centerGrid == null) {
			// For testing purposes
			return;
		}
		mainPanel = new JPanel();
		mainPanel.setOpaque(false);
		centerGrid.add(mainPanel);
		mainPanel.setLayout(new CardLayout(10, 10));

		centerPanel = new JPanel();
		centerPanel.setBorder(new LineBorder(new Color(61, 56, 70), 2));
		centerPanel.setOpaque(false);
		mainPanel.add(centerPanel, "name_205877267141400");
		centerPanel.setLayout(new BorderLayout(0, 0));

		nameLabel = new JLabel("Champion Name");
		nameLabel.setForeground(new Color(255, 255, 255));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(Configuration.TEXT_FONT);
		centerPanel.add(nameLabel, BorderLayout.NORTH);

		panel = new JPanel();
		panel.setOpaque(false);
		centerPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon());
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setFont(Configuration.TEXT_FONT);
		panel.add(imageLabel);

		staminaBar = new JProgressBar();
		staminaBar.setStringPainted(true);
		staminaBar.setFont(Configuration.TEXT_FONT);
		staminaBar.setBackground(Configuration.RED);
		staminaBar.setForeground(Configuration.GREEN);
		staminaBar.setVisible(false);
		centerPanel.add(staminaBar, BorderLayout.SOUTH);
	}

	/**
	 * Assigns a new champion to this card
	 *
	 * @param assignedChampion the new champion to set for the card
	 */
	public void setChampion(Champion assignedChampion) {
		champion = assignedChampion;

		updateCard();
	}

	/**
	 * Updates the card to reflect the info of the champion assigned
	 */
	public void updateCard() {

		if (nameLabel == null) {
			// For testing purposes
			return;
		}

		if (champion == null || champion.getStamina() <= 0) {
			// No champion assigned to this...
			nameLabel.setText("");

			imageLabel.setIcon(null);
			staminaBar.setVisible(false);

			centerPanel.setBorder(new LineBorder(new Color(61, 56, 70), 2));

		} else {
			// Champion is assigned, show data
			String champName = champion.getName();
			if (champion.isFlagCarrier()) {
				champName = "[Flag] " + champName;
			}

			nameLabel.setText(champName);

			// Image championSprite = new Image();

			ImageIcon originalImage = champion.getImage();
			ImageIcon rescaledImage = new ImageIcon(
					originalImage.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

			imageLabel.setIcon(rescaledImage);

			staminaBar.setVisible(true);
			staminaBar.setMinimum(0);
			int maxStamina = (int) Math.ceil(champion.getMaxStamina());
			staminaBar.setMaximum(maxStamina);
			int currentStamina = (int) Math.ceil(champion.getStamina());
			staminaBar.setValue(currentStamina);
			staminaBar.setString(currentStamina + " / " + maxStamina);

			if (gameManager.getPlayerTeam() != null && gameManager.getEnemyTeam() != null) {
				if (gameManager.getPlayerTeam().getChosenChampions().contains(champion)) {
					centerPanel.setBorder(new LineBorder(Color.blue, 2));
				}
				if (gameManager.getEnemyTeam().getChosenChampions().contains(champion)) {
					centerPanel.setBorder(new LineBorder(Color.red, 2));
				}
			}

		}
	}

	/**
	 * Removes the champion from this card
	 */
	public void removeChampion() {
		champion = null;

		updateCard();
	}
}
