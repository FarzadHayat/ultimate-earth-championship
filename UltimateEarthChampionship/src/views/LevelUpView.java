package views;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import manager.GameManager;
import model.Champion;
import model.LevelUpStat;

/**
 * Static class which handles showing the level up dialogue for a champion and
 * communicating the chosen stat to level up to the game manager
 * @author Oliver Coates
 *
 */
public class LevelUpView extends JPanel {

	private static final long serialVersionUID = -6049198470080468548L;

	/**
	 * Shows a level up dialogue box to the player, This should be called by the gameManager upon
	 * a champion level up
	 * @param champion The champion being leveled up
	 */
	public static void showLevelUpDialogue(Champion champion) {
		String header = champion.getName() + " has leveled up!";

		Object[] options = { "Stamina", "Regen", "Offense", "Defense" };

		int result = JOptionPane.showOptionDialog(null, "Choose a stat to improve:", header, JOptionPane.DEFAULT_OPTION,
				JOptionPane.WARNING_MESSAGE, null, options, options[0]);

		LevelUpStat statChosen = null;

		if (result == 0) {
			statChosen = LevelUpStat.STAMINA;
		} else if (result == 1) {
			statChosen = LevelUpStat.REGEN;
		} else if (result == 2) {
			statChosen = LevelUpStat.OFFENSE;
		} else if (result == 3) {
			statChosen = LevelUpStat.DEFENSE;
		} else {
			statChosen = LevelUpStat.STAMINA;
		}

		System.out.println("chosen: " + statChosen);

		GameManager.getInstance().applyLevelUp(champion, statChosen);
	}
}
