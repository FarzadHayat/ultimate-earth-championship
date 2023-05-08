package views;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manager.GameManager;

public class InfoPanel extends JPanel {

	private static final long serialVersionUID = -8835384563469205994L;
	
	private GameManager gameManager = GameManager.getInstance();
	
	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		setLayout(new GridLayout(1, 3, 0, 0));
		
		String nameString = "Team name: " + String.valueOf(gameManager.getPlayerTeam().getName());
		add(new StatLabel(nameString));
		
		String moneyString = "Money: " + String.valueOf(gameManager.getPlayerTeam().getMoney());
		add(new StatLabel("money", moneyString));
		
		String scoreString = "Score: " + String.valueOf(gameManager.getPlayerTeam().getScore());
		add(new StatLabel("score", scoreString));
		
		String weekString = "Week: " + String.valueOf(gameManager.getGameEnvironment().getCurrentWeek())
		+ " out of " + String.valueOf(gameManager.getGameEnvironment().getMaxWeeks()); 
		add(new StatLabel(weekString));
		
		String difficultyString = "Difficulty: " + String.valueOf(gameManager.getGameEnvironment().getDifficulty());
		add(new StatLabel(difficultyString));
		
	}

}
