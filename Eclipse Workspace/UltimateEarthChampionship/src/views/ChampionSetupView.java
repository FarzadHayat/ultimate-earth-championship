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

/**
 * View which allows the user to choose four champions to take part in the match
 */
public class ChampionSetupView extends JPanel {

	private static final long serialVersionUID = -1352915737619575543L;

	/**
	 *  The gamemanager
	 */
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();

	/**
	 *  The background image
	 */
	private ImageIcon icon;

	/**
	 * Create the panel.
	 */
	public ChampionSetupView() {
		setLayout(new BorderLayout());
		icon = new ImageIcon(getClass().getResource(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "championsetup.jpg"));

		addBackButton();
		addChampionsPanel();
		addLanesPanel();
		addChosenChampionsPanel();
		addNextButton();
	}

	/**
	 * Adds a back button onto the panel
	 */
	private void addBackButton() {
		JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonPanel.setOpaque(false);
		JButton backButton = new JButton("Go back to the Stadium");
		backButton.setFont(Configuration.HEADER_FONT);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gameManager.backFromChampionSetup();
			}
		});
		backButtonPanel.add(backButton);
		add(backButtonPanel, BorderLayout.NORTH);
	}

	/**
	 * Draws the champion panel, which shows all the champions in the team and
	 * allows the player to choose them
	 */
	private void addChampionsPanel() {
		JPanel outerPanel = new JPanel(new GridBagLayout());
		outerPanel.setOpaque(false);
		JPanel championsPanel = new JPanel(new GridLayout(3, 3, 20, 20));
		championsPanel.setOpaque(false);
		ArrayList<Champion> champions = gameManager.getPlayerTeam().getChampions();
		outerPanel.add(championsPanel);
		add(outerPanel, BorderLayout.WEST);
		for (int i = 0; i < Configuration.NUM_CHAMPIONS; i++) {
			if (champions.size() > i) {
				addChampionToPanel(champions.get(i), championsPanel);
			} else {
				championsPanel.add(new ChampionCard());
			}
		}
	}

	/**
	 * Adds a champion onto a panel
	 *
	 * @param champion The champion to be added
	 * @param panel    The panel to add the champion to
	 */
	private void addChampionToPanel(Champion champion, JPanel panel) {
		PurchasableCard card = new ChampionCard(champion);
		ArrayList<Champion> chosenChampions = gameManager.getPlayerTeam().getChosenChampions();
		card.addStatsPanel();
		card.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (chosenChampions.contains(champion)) {
					gameManager.getPlayerTeam().removeChosenChampion(champion);
				} else {
					try {
						gameManager.getPlayerTeam().addChosenChampion(champion);
					} catch (FullTeamException e1) {
						JOptionPane.showMessageDialog(getParent(), e1.getMessage());
					}
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
			public void mouseClicked(MouseEvent e) {
			}
		});
		if (chosenChampions.contains(champion)) {
			card.selected();
		}
		panel.add(card);
	}

	/**
	 * Adds a lane panel, which shows the four lanes
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
	 * Adds a panel which displays the four champions chosen for the four different
	 * panels
	 */
	private void addChosenChampionsPanel() {
		JPanel chosenChampionsPanel = new JPanel(new GridLayout(0, 1, 20, 20));
		chosenChampionsPanel.setOpaque(false);
		ArrayList<Champion> chosenChampions = gameManager.getPlayerTeam().getChosenChampions();
		add(chosenChampionsPanel, BorderLayout.EAST);
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
	 * Adds a 'go to weapon setup' button which allows the player to advance to
	 * weapon setup
	 */
	private void addNextButton() {
		JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		nextButtonPanel.setOpaque(false);
		JButton startButton = new JButton("Go to Weapon Setup");
		startButton.setFont(Configuration.HEADER_FONT);
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameManager.getPlayerTeam().getChosenChampions().size() < Configuration.NUM_CHOSEN_CHAMPIONS) {
					JOptionPane.showMessageDialog(getParent(),
							"Team roster is not complete!\nSelect more champions to continue.");
				} else {
					gameManager.finishedChampionSetup();
				}

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
