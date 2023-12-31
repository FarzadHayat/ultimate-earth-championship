package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import display.GraphicalDisplay;
import manager.GameManager;
import model.Configuration;
import model.Team;

/**
 * The ending view, which shows the screen which shows the ranks of the four teams in the tournament
 * and allows the player to start a new game or quit
 */
public class EndingView extends JPanel {

	private static final long serialVersionUID = 6638392272492099486L;

	/**
	 *  Teams to be displayed
	 */
	private ArrayList<Team> teams;

	/**
	 *  Background image
	 */
	private ImageIcon icon = new ImageIcon(
			getClass().getResource(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "story7.jpg"));

	/**
	 * Constructor, draws the screen. This will handle ordering the teams by score.
	 * @param teams The list of four teams to be ordered and then shown
	 */
	public EndingView(ArrayList<Team> teams) {
		this.teams = orderTeams(teams);

		setLayout(new BorderLayout(0, 0));

		JLabel headerLabel = new JLabel("Game Over");
		headerLabel.setForeground(Color.white);
		headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(headerLabel, BorderLayout.NORTH);

		JPanel centerPanel = new JPanel();
		centerPanel.setForeground(Color.white);
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		JLabel subheaderLabel = new JLabel("Tournament Results:");
		subheaderLabel.setForeground(Color.white);
		subheaderLabel.setVerticalAlignment(SwingConstants.TOP);
		subheaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(subheaderLabel, BorderLayout.NORTH);

		JPanel teamGrid = new JPanel();
		teamGrid.setOpaque(false);
		centerPanel.add(teamGrid);

		drawResultsGrid(teamGrid);
		teamGrid.setLayout(new BoxLayout(teamGrid, BoxLayout.Y_AXIS));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		FlowLayout flowLayout = (FlowLayout) bottomPanel.getLayout();
		flowLayout.setHgap(0);
		add(bottomPanel, BorderLayout.SOUTH);

		JPanel bottomGrid = new JPanel();
		bottomGrid.setOpaque(false);
		bottomPanel.add(bottomGrid);
		GridBagLayout gbl_bottomGrid = new GridBagLayout();
		gbl_bottomGrid.columnWidths = new int[] { 250, 250, 250 };
		gbl_bottomGrid.rowHeights = new int[] { 60, 60 };
		gbl_bottomGrid.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_bottomGrid.rowWeights = new double[] { 0.0, 0.0 };
		bottomGrid.setLayout(gbl_bottomGrid);

		JLabel lblThanksForPlaying = new JLabel("Thank you for playing Ultimate Earth Championship");
		lblThanksForPlaying.setFont(Configuration.TEXT_FONT);
		lblThanksForPlaying.setForeground(Color.white);
		GridBagConstraints gbc_lblThankYouFor = new GridBagConstraints();
		gbc_lblThankYouFor.fill = GridBagConstraints.BOTH;
		gbc_lblThankYouFor.insets = new Insets(0, 0, 5, 5);
		gbc_lblThankYouFor.gridx = 1;
		gbc_lblThankYouFor.gridy = 0;
		bottomGrid.add(lblThanksForPlaying, gbc_lblThankYouFor);

		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().quitApplication();
				GameManager.getInstance().forceCreateNewInstance();
				GameManager.start();
			}
		});
		GridBagConstraints gbc_newGameButton = new GridBagConstraints();
		gbc_newGameButton.insets = new Insets(0, 0, 0, 5);
		gbc_newGameButton.gridx = 0;
		gbc_newGameButton.gridy = 1;
		bottomGrid.add(newGameButton, gbc_newGameButton);

		JLabel creditsLabel = new JLabel("Made by Farzad and Oliver");
		creditsLabel.setForeground(Color.white);
		creditsLabel.setFont(Configuration.TEXT_FONT);
		GridBagConstraints gbc_creditsLabel = new GridBagConstraints();
		gbc_creditsLabel.insets = new Insets(0, 0, 0, 5);
		gbc_creditsLabel.gridx = 1;
		gbc_creditsLabel.gridy = 1;
		bottomGrid.add(creditsLabel, gbc_creditsLabel);

		JButton quitGameButton = new JButton("Quit Game");
		quitGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().quitApplication();
			}
		});
		GridBagConstraints gbc_quitGameButton = new GridBagConstraints();
		gbc_quitGameButton.gridx = 2;
		gbc_quitGameButton.gridy = 1;
		bottomGrid.add(quitGameButton, gbc_quitGameButton);

	}

	/**
	 * Fills a grid to hold the four teams, ordered from highest to lowest scoring
	 * @param teamGrid The teamgrid Jpanel to populate with teams
	 */
	public void drawResultsGrid(JPanel teamGrid) {
		int num = 1;
		for (Team team : teams) {
			JPanel teamPanel = new JPanel();
			teamPanel.setOpaque(false);
			teamGrid.add(teamPanel);

			JLabel teamName = new JLabel("#" + num + " " + team.getName() + " with " + team.getScore() + " score");

			teamName.setFont(Configuration.HEADER_FONT);

			teamName.setForeground(Color.white);

			teamPanel.add(teamName);

			num++;
		}

	}

	/**
	 * Orders a given list of teams to be sorted from highest scoring to lowest
	 * scoring.
	 *
	 * @param toSort The team list to sort
	 * @return A list of teams, sorted by score, highest first.
	 */
	private ArrayList<Team> orderTeams(ArrayList<Team> toSort) {
		ArrayList<Team> sortedList = new ArrayList<Team>();

		// Duplicate toSort list just to ensure no unexpected editing
		ArrayList<Team> toSortClone = new ArrayList<Team>(toSort);

		while (toSortClone.size() > 0) {
			int highestScore = -1;
			Team highestScoringTeam = null;

			// Find the highest scoring team
			for (Team team : toSortClone) {
				if (team.getScore() > highestScore) {
					highestScore = team.getScore();
					highestScoringTeam = team;
				}
			}

			// Add it to the list
			toSortClone.remove(highestScoringTeam);
			sortedList.add(highestScoringTeam);

			// repeat
		}

		return sortedList;
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
