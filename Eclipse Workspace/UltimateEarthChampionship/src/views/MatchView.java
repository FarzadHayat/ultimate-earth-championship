package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.SwingConstants;
import javax.swing.Timer;

import display.GraphicalDisplay;
import match.LiveMatch;
import model.Champion;
import model.Configuration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MatchView extends JPanel {

	private static final long serialVersionUID = -9204549187590810546L;

	private LiveMatch match;
	
	private JLabel selectedChampionLabel;
	
	private Champion selectedChampion;
	
	private JButton attackButton;
	private JButton waitButton;
	private JButton retreatButton;
	private JButton attackUpButton;
	private JButton attackDownButton;
	
	/**
	 * Create the panel.
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
	 * Sets up the center panel
	 */
	public void setupCenter()
	{
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setBackground(new Color(192, 192, 192));
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JPanel centerGrid = new JPanel();
		centerGrid.setOpaque(false);
		centerGrid.setBackground(new Color(192, 192, 192));
		centerPanel.add(centerGrid);
		centerGrid.setLayout(new GridLayout(4, 7, 0, 0));
		
		
		ArrayList<ArrayList<ChampionMatchCard>> cards = new ArrayList<ArrayList<ChampionMatchCard>>();
				
		int row = 0;
		while (row < 4)
		{
			ArrayList<ChampionMatchCard> cardsToAdd = new ArrayList<ChampionMatchCard>();
			
			int column = 0;
			
			while (column < 7)
			{
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
	 * Sets up the botom panel
	 */
	public void setupBottomPanel()
	{
		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 15));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		bottomPanel.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 6, 30, 0));
		
		selectedChampionLabel = new JLabel("<Champion Selected> :");
		selectedChampionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectedChampionLabel.setFont(Configuration.TEXT_FONT);
		buttonPanel.add(selectedChampionLabel);
		
		attackButton = new JButton("Move");
		attackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				match.championAttack();
			}
		});
		buttonPanel.add(attackButton);
		
		waitButton = new JButton("Wait");
		waitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				match.championWait();
			}
		});
		buttonPanel.add(waitButton);
		
		retreatButton = new JButton("Retreat");
		retreatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				match.championRetreat();
			}
		});
		buttonPanel.add(retreatButton);
		
		attackUpButton = new JButton("AttackUp");
		attackUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				match.championAttackUp();
			}
		});
		buttonPanel.add(attackUpButton);
		
		attackDownButton = new JButton("AttackDown");
		attackDownButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				match.championAttackDown();
			}
		});
		buttonPanel.add(attackDownButton);
	}
	
	/**
	 * Sets up the side panel
	 */
	public void setupSides()
	{
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
	 * Sets up the header
	 */
	public void setupHeader()
	{
		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		add(topPanel, BorderLayout.NORTH);
		
		JPanel topGridPanel = new JPanel();
		topGridPanel.setOpaque(false);
		topPanel.add(topGridPanel);
		topGridPanel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel headerText = new JLabel(match.getTeam1().getName() + " vs " + match.getTeam2().getName());
		headerText.setHorizontalAlignment(SwingConstants.CENTER);
		headerText.setFont(Configuration.TEXT_FONT);
		topGridPanel.add(headerText);
		
		JLabel subheader1 = new JLabel("Win by moving your flag bearer to the enemy side");
		subheader1.setHorizontalAlignment(SwingConstants.CENTER);
		subheader1.setFont(Configuration.TEXT_FONT);
		topGridPanel.add(subheader1);
		
		JLabel subheader2 = new JLabel("Match Worth: "+ match.getScore() +" score");
		subheader2.setHorizontalAlignment(SwingConstants.CENTER);
		subheader2.setFont(Configuration.TEXT_FONT);
		topGridPanel.add(subheader2);
	}
	
	/**
	 * Selects a champion as the champion whose turn is currently undergoing
	 * @param champion Champion to be selected
	 */
	public void selectChampion(Champion champion)
	{
		selectedChampionLabel.setText(champion.getName() + ": " );
		selectedChampion = champion;
		
		updateButtons();
	}

	/**
	 * Enables and disables the buttons based upon whether the champion is allowed to make a certain action
	 */
	public void updateButtons()
	{
		// Enable the move button
		waitButton.setEnabled(true);
		
		// Disable the move button if at the very right of the screen
		if (selectedChampion.getPosition() == 6)
		{
			attackButton.setEnabled(false);
		}
		else 
		{
			attackButton.setEnabled(true);
		}
		
		// Change the text of attack button depending on if there is an enemy infront of them
		if (selectedChampion.getPosition() != 6 &&
			match.getCard(selectedChampion.getLane(), selectedChampion.getPosition()+1).getChampion() != null)
		{
			attackButton.setText("Attack");
		}
		else 
		{
			attackButton.setText("Move");
		}
		
		// Disable the retreat button if the player cannot retreat any further
		if (selectedChampion.getPosition() == 0)
		{
			retreatButton.setEnabled(false);
		}
		else 
		{
			retreatButton.setEnabled(true);
		}
		
		// Enable the attack up button if there is a champion above them who is on the enemy team
		if (selectedChampion.getLane() == 0)
		{
			attackUpButton.setEnabled(false);
		}
		else 
		{
			// Get the champion above
			Champion championAbove = match.getCard(selectedChampion.getLane()-1, selectedChampion.getPosition()).getChampion();
			
			// check they exist
			if (championAbove == null)
			{
				attackUpButton.setEnabled(false);
			}
			else if (!match.championIsOnPlayerTeam(championAbove)) {
				attackUpButton.setEnabled(true);
			}
			else {
				attackUpButton.setEnabled(false);
			}
		}
		
		// Enable the attack up button if there is a champion below them who is on the enemy team
		if (selectedChampion.getLane() == 3)
		{
			attackDownButton.setEnabled(false);
		}
		else 
		{
			// Get champion below
			Champion championBelow = match.getCard(selectedChampion.getLane()+1, selectedChampion.getPosition()).getChampion();
			
			// Check they exist
			if (championBelow == null)
			{
				attackDownButton.setEnabled(false);
			}
			else if (!match.championIsOnPlayerTeam(championBelow)) {
				attackDownButton.setEnabled(true);
			}
			else {
				attackDownButton.setEnabled(false);
			}
		}
	}
	
	/**
	 * Disables all buttons, useful for when the AI is taking a turn
	 */
	public void disableAllButtons()
	{
		attackButton.setEnabled(false);
		waitButton.setEnabled(false);
		retreatButton.setEnabled(false);
		attackUpButton.setEnabled(false);
		attackDownButton.setEnabled(false);
	}


	/**
	 * Shows a simple message dialogue
	 * @param message The string message to be displayed
	 */
	public void showDialogue(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon icon = new ImageIcon(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "match.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(GraphicalDisplay.WIDTH,
								GraphicalDisplay.WIDTH, Image.SCALE_SMOOTH));
	    int yPos = (int) ((GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2);
        g.drawImage(icon.getImage(), 0, yPos, null);
	}

}
