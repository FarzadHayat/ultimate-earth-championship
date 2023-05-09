package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import display.GraphicalDisplay;
import exception.FullTeamException;
import manager.GameManager;
import manager.GraphicalGameManager;
import model.Champion;
import model.Configuration;

public class ChampionSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;
	
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();
	private Configuration config = Configuration.getInstance();
	
	/**
	 * Create the panel.
	 */
	public ChampionSetupView() {
		setLayout(new BorderLayout());
		
		addBackButton();
		addChampionsPanel();
		addLanesPanel();
		addChosenChampionsPanel();
		addNextButton();
	}

	private void addBackButton() {
		JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonPanel.setOpaque(false);
		JButton backButton = new JButton("Go back to the Stadium");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.backFromChampionSetup();
			}
		});
		backButtonPanel.add(backButton);
		add(backButtonPanel, BorderLayout.NORTH);
	}

	private void addChampionsPanel() {
		JPanel championsPanel = new JPanel(new GridLayout(3, 3, 20, 20));
		championsPanel.setOpaque(false);
		ArrayList<Champion> champions = gameManager.getPlayerTeam().getChampions(); 
		add(championsPanel, BorderLayout.WEST);
		for (int i = 0; i < config.NUM_CHAMPIONS; i++) {
			if (champions.size() > i) {
				addChampionToPanel(champions.get(i), championsPanel);
			} else {
				championsPanel.add(new ChampionCard());
			}
		}
	}
	
	private void addChampionToPanel(Champion champion, JPanel panel) {
		PurchasableCard card = new ChampionCard(champion);
		ArrayList<Champion> chosenChampions = gameManager.getPlayerTeam().getChosenChampions();
		card.addStatsPanel();
		card.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (chosenChampions.contains(champion)) {
					gameManager.getPlayerTeam().removeChosenChampion(champion);
				} else {
					try {
						gameManager.getPlayerTeam().addChosenChampion(champion);
					} catch (FullTeamException e1) {
						JOptionPane.showMessageDialog(getParent(), e1.getMessage());
					};
				}
				gameManager.repaintChampionSetup();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (chosenChampions.contains(champion)) {
					card.selected();
				} else {
					card.unselected();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				card.hovered();
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		if (chosenChampions.contains(champion)) {
			card.selected();
		}
		panel.add(card);
	}

	private void addLanesPanel() {
		JPanel lanesPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		lanesPanel.setOpaque(false);
		add(lanesPanel, BorderLayout.CENTER);
		for (int i = 0; i < config.NUM_CHOSEN_CHAMPIONS; i++) {
			JLabel label = new JLabel("Lane " + String.valueOf(i+1), JLabel.RIGHT);
			label.setFont(Configuration.HEADER_FONT);
			label.setForeground(Color.white);
			lanesPanel.add(label);
		}
	}
	
	private void addChosenChampionsPanel() {
		JPanel chosenChampionsPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		chosenChampionsPanel.setOpaque(false);
		ArrayList<Champion> chosenChampions = gameManager.getPlayerTeam().getChosenChampions(); 
		add(chosenChampionsPanel, BorderLayout.EAST);
		for (int i = 0; i < config.NUM_CHOSEN_CHAMPIONS; i++) {
			PurchasableCard card;
			if (chosenChampions.size() > i) {
				Champion champion = chosenChampions.get(i);
				card = new ChampionCard(champion);
				card.addStatsPanel();
				card.selected();
			} else {
				card = new ChampionCard();
			}
			chosenChampionsPanel.add(card);
		}
	}
	
	private void addNextButton() {
		JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		nextButtonPanel.setOpaque(false);
		JButton startButton = new JButton("Go to Weapon Setup");
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameManager.getPlayerTeam().getChosenChampions().size() < config.NUM_CHOSEN_CHAMPIONS) {
					JOptionPane.showMessageDialog(getParent(), "Team roster is not complete!\nSelect more champions to continue.");
				} else {
					gameManager.finishedChampionSetup();
				}
				
			}
		});
		nextButtonPanel.add(startButton);
		add(nextButtonPanel, BorderLayout.SOUTH);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon icon = new ImageIcon(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "championsetup.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(GraphicalDisplay.WIDTH,
								GraphicalDisplay.WIDTH, Image.SCALE_SMOOTH));
	    int yPos = (int) ((GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2);
        g.drawImage(icon.getImage(), 0, yPos, null);
	}

}
