package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.SwingConstants;

import display.GraphicalDisplay;
import exception.FullTeamException;
import manager.GameManager;
import manager.GraphicalGameManager;
import model.Champion;
import model.Configuration;
import model.Weapon;

/**
 * Class which handles the weapon setup view, which is where the player selects
 * which of their weapons are given to each champion.
 */
public class WeaponSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;

	/**
	 *  The game manager
	 */
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();

	/**
	 *  The panel which shows chosen weapons
	 */
	private JPanel chosenPanel;

	/** 
	 * The background image
	 */
	private ImageIcon icon = new ImageIcon(
			getClass().getResource(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "weaponsetup.jpg"));

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

	/**
	 * Adds a back button, so the player can return to the champion selection screen
	 */
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

	/**
	 * Adds a weapon panel, which displays all of the weapons in the players
	 * inventory
	 */
	private void addWeaponsPanel() {
		JPanel outerPanel = new JPanel(new GridBagLayout());
		outerPanel.setOpaque(false);
		JPanel weaponsPanel = new JPanel(new GridLayout(3, 3, 20, 20));
		weaponsPanel.setOpaque(false);
		ArrayList<Weapon> weapons = gameManager.getPlayerTeam().getWeapons();
		outerPanel.add(weaponsPanel);
		add(outerPanel, BorderLayout.WEST);
		for (int i = 0; i < Configuration.NUM_CHAMPIONS; i++) {
			if (weapons.size() > i) {
				addWeaponToPanel(weapons.get(i), weaponsPanel);
			} else {
				weaponsPanel.add(new WeaponCard());
			}
		}
	}

	/**
	 * Adds a weapon to a weaponPanel
	 *
	 * @param weapon The weapon to be added
	 * @param panel  The panel to have the weapon set to
	 */
	private void addWeaponToPanel(Weapon weapon, JPanel panel) {
		PurchasableCard card;
		ArrayList<Weapon> chosenWeapons = gameManager.getPlayerTeam().getChosenWeapons();
		card = new WeaponCard(weapon);
		card.addStatsPanel();
		card.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (chosenWeapons.contains(weapon)) {
					gameManager.getPlayerTeam().removeChosenWeapon(weapon);
				} else {
					try {
						gameManager.getPlayerTeam().addChosenWeapon(weapon);
					} catch (FullTeamException e1) {
						JOptionPane.showMessageDialog(getParent(), e1.getMessage());
					}
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
			public void mouseClicked(MouseEvent e) {
			}
		});
		if (chosenWeapons.contains(weapon)) {
			card.selected();
		}
		panel.add(card);
	}

	/**
	 * Adds a panel which displays the four lanes
	 */
	private void addLanesPanel() {
		JPanel lanesPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		lanesPanel.setOpaque(false);
		add(lanesPanel, BorderLayout.CENTER);
		for (int i = 0; i < Configuration.NUM_CHOSEN_CHAMPIONS; i++) {
			JLabel label = new JLabel("Lane " + String.valueOf(i + 1), SwingConstants.RIGHT);
			label.setFont(Configuration.HEADER_FONT);
			label.setForeground(Color.white);
			lanesPanel.add(label);
		}
	}

	/**
	 * Adds a panel which displays the four chosen champions
	 */
	private void addChosenChampionsPanel() {
		JPanel chosenChampionsPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		chosenChampionsPanel.setOpaque(false);
		ArrayList<Champion> chosenChampions = gameManager.getPlayerTeam().getChosenChampions();
		chosenPanel.add(chosenChampionsPanel);
		for (int i = 0; i < Configuration.NUM_CHOSEN_CHAMPIONS; i++) {
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

	/**
	 * Adds a panel which shows the four chosen weapons set to each of the four
	 * champions
	 */
	private void addChosenWeaponsPanel() {
		JPanel chosenWeaponsPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		chosenWeaponsPanel.setOpaque(false);
		ArrayList<Weapon> chosenWeapons = gameManager.getPlayerTeam().getChosenWeapons();
		chosenPanel.add(chosenWeaponsPanel);
		for (int i = 0; i < Configuration.NUM_CHOSEN_CHAMPIONS; i++) {
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

	/**
	 * Adds a button which allows the player to start the match.
	 */
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
