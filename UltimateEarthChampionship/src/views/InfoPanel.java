package views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import display.GraphicalDisplay;
import manager.GameManager;
import model.Configuration;

/**
 * Panel for the info panel which runs along the top of the screen and 
 * displays the team name, money, score, week and difficulty
 *
 */
public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -8835384563469205994L;
	
	/**
	 * The game manager
	 */
	private GameManager gameManager = GameManager.getInstance();
	/**
	 * The configuration class
	 */
	private Configuration config = Configuration.getInstance();

	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		setMaximumSize(new Dimension(GraphicalDisplay.WIDTH, 100));

		String nameString = "Team name: " + String.valueOf(gameManager.getPlayerTeam().getName());
		StatLabel nameLabel = new StatLabel(nameString);
		nameLabel.getValueLabel().setFont(Configuration.HEADER_FONT);
		add(nameLabel);

		String moneyString = "Money: "
				+ String.valueOf(Math.round(gameManager.getPlayerTeam().getMoney() * 100.0) / 100.0);
		StatLabel moneyLabel = new StatLabel("money", moneyString);
		moneyLabel.getValueLabel().setFont(Configuration.HEADER_FONT);
		add(moneyLabel);

		String scoreString = "Score: "
				+ String.valueOf(Math.round(gameManager.getPlayerTeam().getScore() * 100.0) / 100.0);
		StatLabel scoreLabel = new StatLabel("score", scoreString);
		scoreLabel.getValueLabel().setFont(Configuration.HEADER_FONT);
		add(scoreLabel);

		String weekString = "Week: " + String.valueOf(gameManager.getGameEnvironment().getCurrentWeek()) + " out of "
				+ String.valueOf(gameManager.getGameEnvironment().getMaxWeeks());
		StatLabel weekLabel = new StatLabel(weekString);
		weekLabel.getValueLabel().setFont(Configuration.HEADER_FONT);
		add(weekLabel);

		String difficultyString = "Difficulty: " + String.valueOf(config.getDifficulty());
		StatLabel difficultyLabel = new StatLabel(difficultyString);
		difficultyLabel.getValueLabel().setFont(Configuration.HEADER_FONT);
		add(difficultyLabel);

	}

}
