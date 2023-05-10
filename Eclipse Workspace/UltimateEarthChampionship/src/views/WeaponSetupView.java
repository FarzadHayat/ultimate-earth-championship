package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
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
import model.Weapon;
import weapons.Fists;

public class WeaponSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;
	
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();
	private Configuration config = Configuration.getInstance();
	
	private JPanel chosenPanel;
	
	/**
	 * Create the panel.
	 */
	public WeaponSetupView() {
		setLayout(new BorderLayout());
		chosenPanel = new JPanel(new GridLayout(0, 2));
		chosenPanel.setOpaque(false);
		add(chosenPanel, BorderLayout.EAST);
		
		addBackButton();
		addWeaponsPanel();
		addLanesPanel();
		addChosenChampionsPanel();
		addChosenWeaponsPanel();
		addNextButton();
	}

	private void addBackButton() {
		JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonPanel.setOpaque(false);
		JButton backButton = new JButton("Go back to Champion Setup");
		backButton.setFont(Configuration.HEADER_FONT);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.backFromWeaponSetup();
			}
		});
		backButtonPanel.add(backButton);
		add(backButtonPanel, BorderLayout.NORTH);
	}

	private void addWeaponsPanel() {
		JPanel outerPanel = new JPanel(new GridBagLayout());
		outerPanel.setOpaque(false);
		JPanel weaponsPanel = new JPanel(new GridLayout(3, 3, 20, 20));
		weaponsPanel.setOpaque(false);
		ArrayList<Weapon> weapons = gameManager.getPlayerTeam().getWeapons();
		outerPanel.add(weaponsPanel);
		add(outerPanel, BorderLayout.WEST);
		for (int i = 0; i < config.NUM_CHAMPIONS; i++) {
			if (weapons.size() > i) {
				addWeaponToPanel(weapons.get(i), weaponsPanel);
			} else {
				weaponsPanel.add(new WeaponCard());
			}
		}
	}
	
	private void addWeaponToPanel(Weapon weapon, JPanel panel) {
		PurchasableCard card;
		ArrayList<Weapon> chosenWeapons = gameManager.getPlayerTeam().getChosenWeapons();
		card = new WeaponCard(weapon);
		card.addStatsPanel();
		card.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (chosenWeapons.contains(weapon)) {
					gameManager.getPlayerTeam().removeChosenWeapon(weapon);
				} else {
					try {
						gameManager.getPlayerTeam().addChosenWeapon(weapon);
					} catch (FullTeamException e1) {
						JOptionPane.showMessageDialog(getParent(), e1.getMessage());
					};
				}
				gameManager.getPlayerTeam().unassignChosenWeapons();
				gameManager.getPlayerTeam().assignChosenWeapons();
				gameManager.repaintWeaponSetup();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if (chosenWeapons.contains(weapon)) {
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
		if (chosenWeapons.contains(weapon)) {
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
		chosenPanel.add(chosenChampionsPanel);
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
	
	private void addChosenWeaponsPanel() {
		JPanel chosenWeaponsPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		chosenWeaponsPanel.setOpaque(false);
		ArrayList<Weapon> chosenWeapons = gameManager.getPlayerTeam().getChosenWeapons(); 
		chosenPanel.add(chosenWeaponsPanel);
		for (int i = 0; i < config.NUM_CHOSEN_CHAMPIONS; i++) {
			PurchasableCard card;
			if (chosenWeapons.size() > i) {
				Weapon weapon = chosenWeapons.get(i);
				card = new WeaponCard(weapon);
				card.addStatsPanel();
				card.selected();
			} else {
				card = new WeaponCard();
			}
			chosenWeaponsPanel.add(card);
		}
	}
		
	private void addNextButton() {
		JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		nextButtonPanel.setOpaque(false);
		JButton startButton = new JButton("Start match!");
		startButton.setFont(Configuration.HEADER_FONT);
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.finishedWeaponSetup();
			}
		});
		nextButtonPanel.add(startButton);
		add(nextButtonPanel, BorderLayout.SOUTH);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    ImageIcon icon = new ImageIcon(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "weaponsetup.jpg");
		icon = new ImageIcon(icon.getImage().getScaledInstance(GraphicalDisplay.WIDTH,
								GraphicalDisplay.WIDTH, Image.SCALE_SMOOTH));
	    int yPos = (int) ((GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2);
        g.drawImage(icon.getImage(), 0, yPos, null);
	}

}
