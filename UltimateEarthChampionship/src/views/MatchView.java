package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import display.GraphicalDisplay;
import manager.GameManager;
import match.LiveMatch;
import model.Champion;
import model.Configuration;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * A panel to display the currently playing live match. This view interacts
 * directly with the live match class to deliver interactive results using the
 * player's input.
 */
public class MatchView extends JPanel {

	private static final long serialVersionUID = -9204549187590810546L;
	
	/**
	 * The game manager instance
	 */
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Reference to the live match instance
	 */
	private LiveMatch match;

	/**
	 * The champion who is next to play their turn.
	 */
	private Champion selectedChampion;
	/**
	 * Panels for the selected champion
	 */
	private JPanel selectedChampionPanel;
	
	// Action button for the currently selected champion
	
	/**
	 * Attack button
	 */
	private JButton attackButton;
	/**
	 * Wait button
	 */
	private JButton waitButton;
	/**
	 * Retreat button
	 */
	private JButton retreatButton;
	/**
	 * Attack up button
	 */
	private JButton attackUpButton;
	/**
	 * Attack down button
	 */
	private JButton attackDownButton;

	/**
	 * Background image for the match
	 */
	private ImageIcon icon = new ImageIcon(
			getClass().getResource(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "match.jpg"));

	/**
	 * Create the panel and start the match.
	 *
	 * @param match the live match to display
	 */
	public MatchView(LiveMatch match) {
		this.match = match;
		match.setMatchView(this);

		setLayout(new BorderLayout(0, 0));

		setupCenter();

		setupBottomPanel();

		setupSides();

		setupHeader();

		// Everything set up and drawn, tell match to start the first turn
		match.nextTurn();
	}

	/**
	 * Sets up the header. The header contains important information about the
	 * match.
	 */
	public void setupHeader() {
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		add(topPanel, BorderLayout.NORTH);
		
		// Top grid panel
		JPanel topGridPanel = new JPanel();
		topGridPanel.setOpaque(false);
		topPanel.add(topGridPanel);
		GridBagLayout gbl_topGridPanel = new GridBagLayout();
		gbl_topGridPanel.columnWidths = new int[] {250, 600, 250};
		gbl_topGridPanel.rowHeights = new int[] {0};
		gbl_topGridPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_topGridPanel.rowWeights = new double[]{1.0};
		topGridPanel.setLayout(gbl_topGridPanel);
		
		JLabel emptyLabel = new JLabel("");
		GridBagConstraints gbc_emptyLabel = new GridBagConstraints();
		gbc_emptyLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emptyLabel.gridx = 0;
		gbc_emptyLabel.gridy = 0;
		topGridPanel.add(emptyLabel, gbc_emptyLabel);

		JPanel topTextGridPanel = new JPanel();
		GridBagConstraints gbc_topTextGridPanel = new GridBagConstraints();
		gbc_topTextGridPanel.insets = new Insets(0, 0, 5, 5);
		gbc_topTextGridPanel.gridx = 1;
		gbc_topTextGridPanel.gridy = 0;
		topGridPanel.add(topTextGridPanel, gbc_topTextGridPanel);
		topTextGridPanel.setOpaque(false);
		topTextGridPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel headerText = new JLabel(match.getTeam1().getName() + " vs " + match.getTeam2().getName());
		headerText.setHorizontalAlignment(SwingConstants.CENTER);
		headerText.setFont(Configuration.HEADER_FONT);
		headerText.setForeground(Color.white);
		topTextGridPanel.add(headerText);

		JLabel subheader1 = new JLabel("Win by moving your flag bearer to the enemy side");
		subheader1.setHorizontalAlignment(SwingConstants.CENTER);
		subheader1.setFont(Configuration.HEADER_FONT);
		subheader1.setForeground(Color.white);
		topTextGridPanel.add(subheader1);

		JLabel subheader2 = new JLabel("Match Worth: " + match.getScore() + " score");
		subheader2.setHorizontalAlignment(SwingConstants.CENTER);
		subheader2.setFont(Configuration.HEADER_FONT);
		subheader2.setForeground(Color.white);
		topTextGridPanel.add(subheader2);
		
		JButton surrenderButton = new JButton("Surrender");
		surrenderButton.setOpaque(false);
		surrenderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				match.playerSurrenderConfirm();
			}
		});
		GridBagConstraints gbc_surrenderButton = new GridBagConstraints();
		gbc_surrenderButton.insets = new Insets(0, 0, 5, 0);
		gbc_surrenderButton.gridx = 2;
		gbc_surrenderButton.gridy = 0;
		topGridPanel.add(surrenderButton, gbc_surrenderButton);
	}

	/**
	 * Sets up the center panel. The center panel contains the main grid that the
	 * champions can move and attack on.
	 */
	public void setupCenter() {
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		JPanel centerGrid = new JPanel();
		centerGrid.setOpaque(false);
		centerPanel.add(centerGrid);
		centerGrid.setLayout(new GridLayout(Configuration.NUM_CHOSEN_CHAMPIONS, Configuration.NUM_MATCH_COLUMNS, 0, 0));

		ArrayList<ArrayList<ChampionMatchCard>> cards = new ArrayList<>();

		int row = 0;
		while (row < Configuration.NUM_CHOSEN_CHAMPIONS) {
			ArrayList<ChampionMatchCard> cardsToAdd = new ArrayList<>();

			int column = 0;

			while (column < Configuration.NUM_MATCH_COLUMNS) {
				ChampionMatchCard matchCard = new ChampionMatchCard(centerGrid, row, column);

				matchCard.updateCard();

				cardsToAdd.add(matchCard);

				column++;
			}

			cards.add(cardsToAdd);
			row++;
		}

		match.setCards(cards);
		match.assignChampions();
	}

	/**
	 * Sets up the side panel. The side panels provide padding to align the center
	 * panel.
	 */
	public void setupSides() {
		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
	}

	/**
	 * Sets up the bottom panel. The bottom panel contains a panel to display the
	 * currently selected champion along with the action buttons.
	 */
	public void setupBottomPanel() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

		// Selected champion title
		JPanel selectedHeaderPanel = new JPanel(new FlowLayout());
		selectedHeaderPanel.setOpaque(false);
		bottomPanel.add(selectedHeaderPanel);

		JLabel selectedChampionLabel = new JLabel("Currently selected champion:");
		selectedChampionLabel.setFont(Configuration.HEADER_FONT);
		selectedChampionLabel.setForeground(Color.white);
		selectedHeaderPanel.add(selectedChampionLabel);

		// Selected champion panel
		selectedChampionPanel = new JPanel(new FlowLayout());
		selectedChampionPanel.setOpaque(false);
		bottomPanel.add(selectedChampionPanel);

		// Selected champion action buttons
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		bottomPanel.add(buttons);
		buttons.setLayout(new FlowLayout());

		attackButton = new JButton("Move");
		attackButton.setFont(Configuration.HEADER_FONT);
		attackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				match.championAttack();
			}
		});
		buttons.add(attackButton);

		waitButton = new JButton("Wait");
		waitButton.setFont(Configuration.HEADER_FONT);
		waitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				match.championWait();
			}
		});
		buttons.add(waitButton);

		retreatButton = new JButton("Retreat");
		retreatButton.setFont(Configuration.HEADER_FONT);
		retreatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				match.championRetreat();
			}
		});
		buttons.add(retreatButton);

		attackUpButton = new JButton("AttackUp");
		attackUpButton.setFont(Configuration.HEADER_FONT);
		attackUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				match.championAttackUp();
			}
		});
		buttons.add(attackUpButton);

		attackDownButton = new JButton("AttackDown");
		attackDownButton.setFont(Configuration.HEADER_FONT);
		attackDownButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				match.championAttackDown();
			}
		});
		buttons.add(attackDownButton);
	}

	/**
	 * Selects a champion as the champion who is next to play their turn.
	 *
	 * @param champion Champion to be selected
	 */
	public void selectChampion(Champion champion) {
		selectedChampion = champion;
		updateButtons();
	}

	/**
	 * Updates the selected champion panel to display the given champion.
	 *
	 * @param champion the champion to update the panel to
	 */
	public void updateSelectedChampionPanel(Champion champion) {
		selectedChampionPanel.removeAll();
		ChampionCard championCard = new ChampionCard(champion);
		championCard.addStatsPanel();
		selectedChampionPanel.add(championCard);
		selectedChampionPanel.revalidate();
		selectedChampionPanel.repaint();

	}

	/**
	 * Enables and disables the action buttons depending on whether the champion is
	 * allowed to perform a certain action.
	 */
	private void updateButtons() {
		// Enable the move button
		waitButton.setEnabled(true);

		// Disable the move button if at the very right of the screen
		if (selectedChampion.getPosition() == 6) {
			attackButton.setEnabled(false);
		} else {
			attackButton.setEnabled(true);
		}

		// Change the text of attack button depending on if there is an enemy infront of
		// them
		if (selectedChampion.getPosition() != 6 && match
				.getCard(selectedChampion.getLane(), selectedChampion.getPosition() + 1).getChampion() != null) {
			attackButton.setText("Attack");
		} else {
			attackButton.setText("Move");
		}

		// Disable the retreat button if the player cannot retreat any further
		if (selectedChampion.getPosition() == 0) {
			retreatButton.setEnabled(false);
		} else {
			retreatButton.setEnabled(true);
		}

		// Enable the attack up button if there is a champion above them who is on the
		// enemy team
		if (selectedChampion.getLane() == 0) {
			attackUpButton.setEnabled(false);
		} else {
			// Get the champion above
			Champion championAbove = match.getCard(selectedChampion.getLane() - 1, selectedChampion.getPosition())
					.getChampion();

			// check they exist
			if (championAbove == null) {
				attackUpButton.setEnabled(false);
			} else if (!match.championIsOnPlayerTeam(championAbove)) {
				attackUpButton.setEnabled(true);
			} else {
				attackUpButton.setEnabled(false);
			}
		}

		// Enable the attack up button if there is a champion below them who is on the
		// enemy team
		if (selectedChampion.getLane() == 3) {
			attackDownButton.setEnabled(false);
		} else {
			// Get champion below
			Champion championBelow = match.getCard(selectedChampion.getLane() + 1, selectedChampion.getPosition())
					.getChampion();

			// Check they exist
			if (championBelow == null) {
				attackDownButton.setEnabled(false);
			} else if (!match.championIsOnPlayerTeam(championBelow)) {
				attackDownButton.setEnabled(true);
			} else {
				attackDownButton.setEnabled(false);
			}
		}
	}

	/**
	 * Disables all action buttons, useful for when the AI is taking a turn
	 */
	public void disableAllButtons() {
		attackButton.setEnabled(false);
		waitButton.setEnabled(false);
		retreatButton.setEnabled(false);
		attackUpButton.setEnabled(false);
		attackDownButton.setEnabled(false);
	}

	/**
	 * Shows a simple message dialogue
	 *
	 * @param message The string message to be displayed
	 */
	public void showDialogue(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	/**
	 * Paint the component then draw the background image onto the component.<br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @param g the graphics object to draw onto
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int yPos = (GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2;
		g.drawImage(icon.getImage(), 0, yPos, null);
	}

}
