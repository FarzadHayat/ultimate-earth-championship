package views;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import manager.GameManager;
import model.Team;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class EndingView extends JPanel {

	private static final long serialVersionUID = 6638392272492099486L;
	
	private ArrayList<Team> teams;
	
	public EndingView(ArrayList<Team> teams) {
		this.teams = orderTeams(teams);
		
		setLayout(new BorderLayout(0, 0));
		
		JLabel headerLabel = new JLabel("Game Over");
		headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(headerLabel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel subheaderLabel = new JLabel("Tournament Results:");
		subheaderLabel.setVerticalAlignment(SwingConstants.TOP);
		subheaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(subheaderLabel, BorderLayout.NORTH);
		
		JPanel teamGrid = new JPanel();
		centerPanel.add(teamGrid);
		
		drawResultsGrid(teamGrid);
		teamGrid.setLayout(new BoxLayout(teamGrid, BoxLayout.Y_AXIS));
		
		JPanel bottomPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) bottomPanel.getLayout();
		flowLayout.setHgap(0);
		add(bottomPanel, BorderLayout.SOUTH);
		
		JPanel bottomGrid = new JPanel();
		bottomPanel.add(bottomGrid);
		GridBagLayout gbl_bottomGrid = new GridBagLayout();
		gbl_bottomGrid.columnWidths = new int[] {250, 250, 250};
		gbl_bottomGrid.rowHeights = new int[] {60, 60};
		gbl_bottomGrid.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_bottomGrid.rowWeights = new double[]{0.0, 0.0};
		bottomGrid.setLayout(gbl_bottomGrid);
		
		JLabel lblThankYouFor = new JLabel("Thank you for playing Ultimate Earth Championship");
		GridBagConstraints gbc_lblThankYouFor = new GridBagConstraints();
		gbc_lblThankYouFor.fill = GridBagConstraints.BOTH;
		gbc_lblThankYouFor.insets = new Insets(0, 0, 5, 5);
		gbc_lblThankYouFor.gridx = 1;
		gbc_lblThankYouFor.gridy = 0;
		bottomGrid.add(lblThankYouFor, gbc_lblThankYouFor);
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(new ActionListener() {
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
		
		JLabel creditsLabel = new JLabel("Made by Fazard and Oliver");
		GridBagConstraints gbc_creditsLabel = new GridBagConstraints();
		gbc_creditsLabel.insets = new Insets(0, 0, 0, 5);
		gbc_creditsLabel.gridx = 1;
		gbc_creditsLabel.gridy = 1;
		bottomGrid.add(creditsLabel, gbc_creditsLabel);
		
		JButton quitGameButton = new JButton("Quit Game");
		quitGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameManager.getInstance().quitApplication();
			}
		});
		GridBagConstraints gbc_quitGameButton = new GridBagConstraints();
		gbc_quitGameButton.gridx = 2;
		gbc_quitGameButton.gridy = 1;
		bottomGrid.add(quitGameButton, gbc_quitGameButton);
		
		
	}
	
	public void drawResultsGrid(JPanel teamGrid)
	{
		int num = 1;
		for (Team team : teams)
		{
			JPanel teamPanel = new JPanel();
			teamGrid.add(teamPanel);
			
			JLabel teamName = new JLabel(
					"#" + num + " " + team.getName() + " with " + team.getScore() + " score");
			teamPanel.add(teamName);
		
			num++;
		}
		
		
	}
	
	
	/**
	 * Orders a given list of teams to be sorted from highest scoring to lowest scoring.
	 * @param toSort The team list to sort
	 * @return A list of teams, sorted by score, highest first. 
	 */
	private ArrayList<Team> orderTeams(ArrayList<Team> toSort)
	{
		ArrayList<Team> sortedList = new ArrayList<Team>();
		
		// Duplicate toSort list just to ensure no unexpected editing
		ArrayList<Team> toSortClone = new ArrayList<Team>(toSort);
		
		while (toSortClone.size() > 0)
		{
			int highestScore = -1;
			Team highestScoringTeam = null;
			
			// Find the highest scoring team
			for (Team team : toSortClone)
			{
				if (team.getScore() > highestScore)
				{
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
}
