package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;

import display.GraphicalDisplay;
import manager.GameManager;
import model.Champion;
import model.Configuration;
import model.Team;

public class StadiumView extends JPanel {

	private static final long serialVersionUID = 6012771276554788813L;
	
	private GameManager gameManager = GameManager.getInstance();

	private JPanel teamsPanel;

	private ImageIcon icon;

	/**
	 * Create the panel.
	 */
	public StadiumView() {
		setName("Stadium");
		setLayout(new BorderLayout());
		
		icon = new ImageIcon(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "stadium.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(GraphicalDisplay.WIDTH,
								GraphicalDisplay.WIDTH, Image.SCALE_SMOOTH));
		
		add(new HeaderPanel("Select a team to start a match against", true), BorderLayout.NORTH);
		addTeamsPanel();
		addBottomPanel();
	}

	private void addTeamsPanel() {
		JPanel outerPanel = new JPanel(new GridBagLayout());
		outerPanel.setOpaque(false);
		teamsPanel = new JPanel();
		teamsPanel.setLayout(new BoxLayout(teamsPanel, BoxLayout.Y_AXIS));
		teamsPanel.setOpaque(false);
		outerPanel.add(teamsPanel);
		add(outerPanel, BorderLayout.CENTER);
		for (Team team : gameManager.getAITeams()) {
			addTeamToPanel(team);
		}
	}
	
	/**
	 * Add the team as a panel to the teams panel
	 */
	public void addTeamToPanel(Team team) {
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BorderLayout());
		
		// Team name
		HeaderPanel nameLabel = new HeaderPanel(team.getName(), true);
		panel.add(nameLabel, BorderLayout.NORTH);
		
		// Team champions
		JPanel championsPanel = new JPanel(new FlowLayout());
		championsPanel.setOpaque(false);
		ArrayList<Champion> champions = team.getChampions();
		for (int i = 0; i < Configuration.getInstance().NUM_CHAMPIONS; i++) {
			PurchasableCard card;
			if (champions.size() > i) {
				Champion champion = champions.get(i);
				card = new ChampionCard(champion);
				card.addStatsPanel();
			} else {
				card = new ChampionCard();
			}
			championsPanel.add(card);
		}
		panel.add(championsPanel, BorderLayout.CENTER);
		
		// Fight team button
		JPanel fightPanel = new JPanel();
		fightPanel.setOpaque(false);
		panel.add(fightPanel, BorderLayout.SOUTH);
		JButton fightButton = new JButton("Fight " + team.getName());
		fightButton.setFont(Configuration.HEADER_FONT);
		fightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.startMatchSetup(team);
			}
		});
		fightPanel.add(fightButton);
		
		teamsPanel.add(panel);
	}
	
	public void addBottomPanel() {
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setOpaque(false);
		add(bottomPanel, BorderLayout.SOUTH);
		JButton byeButton = new JButton("Take a bye and go to next week");
		byeButton.setFont(Configuration.HEADER_FONT);
		byeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.finishedWeek();
			}
			
		});
		bottomPanel.add(byeButton);
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    int yPos = (int) ((GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2);
        g.drawImage(icon.getImage(), 0, yPos, null);
	}

}
