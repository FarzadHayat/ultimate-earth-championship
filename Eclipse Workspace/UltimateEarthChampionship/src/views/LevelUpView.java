package views;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import manager.GameManager;
import model.Champion;
import model.LevelUpStat;

public class LevelUpView extends JPanel {
	
	public static void showLevelUpDialogue(Champion champion)
	{
		String header = champion.getName() + " has leveled up!";
		
		Object[] options = {
				"Stamina",
				"Regen",
				"Offense",
				"Defense"
		};
		

		int result = JOptionPane.showOptionDialog(null, "Choose a stat to improve:", header, 
												  JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
												  null, options, options[0]);
		
		
		LevelUpStat statChosen = null;
		
		if (result == 0)
		{
			statChosen = LevelUpStat.STAMINA;
		}
		else if (result == 1)
		{
			statChosen = LevelUpStat.REGEN;
		}
		else if (result == 2)
		{
			statChosen = LevelUpStat.OFFENSE;
		}
		else if (result == 3)
		{
			statChosen = LevelUpStat.DEFENSE;
		}
		else {
			statChosen = LevelUpStat.STAMINA;
		}
		
		System.out.println("chosen: " + statChosen);
		
		GameManager.getInstance().applyLevelUp(champion, statChosen);
	}
}
