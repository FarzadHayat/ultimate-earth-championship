package views;

import javax.swing.JPanel;
import javax.swing.ScrollPaneLayout;

import display.GraphicalDisplay;
import manager.GameManager;
import manager.GraphicalGameManager;
import model.Team;
import model.Champion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

public class MatchSelectionView extends JPanel {

	private static final long serialVersionUID = 6012771276554788813L;
	
	private GameManager gameManager = GameManager.getInstance();

	/**
	 * Create the panel.
	 */
	public MatchSelectionView() {
		setName("Matches");
		setLayout(new FlowLayout(FlowLayout.CENTER, 200, 200));
		
		for (Team team : gameManager.getTeams()) {
			addTeamPanel(team);
		}
	}
	
	/**
	 * Add the team as a panel to the match selection view
	 */
	public void addTeamPanel(Team team) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel nameLabel = new JLabel(team.getName());
		panel.add(nameLabel, BorderLayout.NORTH);
		
		DefaultListModel<String> model = new DefaultListModel<>();
		for (int i = 0; i < team.getChosenChampions().size(); i++) {
			Champion champion = team.getChosenChampions().get(i);
			model.add(i, String.format("%s (%s)", champion.getName(), champion.getWeapon().getName()));
		}
		JList<String> championList = new JList<String>(model);
		panel.add(championList, BorderLayout.CENTER);
		
		JButton fightButton = new JButton("Fight");
		fightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.visitMatchSetup(team);
			}
		});
		panel.add(fightButton, BorderLayout.SOUTH);
		
		add(panel);
	}

}
