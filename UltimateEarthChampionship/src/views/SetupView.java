package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import display.GraphicalDisplay;
import exception.InputException;
import manager.GameManager;
import manager.GraphicalGameManager;
import model.Champion;
import model.Configuration;
import model.SetupManager;

/**
 * View which shows the setup view to the player
 */
public class SetupView extends JPanel {

	private static final long serialVersionUID = 4605160332340664881L;

	/**
	 * Config instance
	 */
	private Configuration config = Configuration.getInstance();

	/**
	 * Game manager instance
	 */
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();

	/**
	 * List of champions that can be chosen from in setup
	 */
	private ArrayList<Champion> championsToChooseFrom;

	// Data Requested
	
	/**
	 * The team name
	 */
	private String teamName;
	/**
	 * The number of game weeks
	 */
	private int gameWeeks;
	/**
	 * The champions selected for the player team
	 */
	private ArrayList<Champion> chosenChampions = new ArrayList<>();
	/**
	 * The difficulty
	 */
	private float difficulty;

	// Swing components
	
	/**
	 * Field for the team name
	 */
	private JTextField teamNameField;
	
	/**
	 * The Submit button
	 */
	private JButton submitButton;
	
	/**
	 * The week selection spinner
	 */
	private JSpinner weekSelectionSpinner;
	
	/**
	 * The difficulty slider
	 */
	private JSlider difficultySlider;
	
	/**
	 * The difficulty text
	 */
	private JLabel difficultyText;
	
	/**
	 * The form panel
	 */
	private JPanel formPanel;
	
	/**
	 * The champions panel
	 */
	private JPanel championsPanel;

	/**
	 *  Background image
	 */
	private ImageIcon icon = new ImageIcon(
			getClass().getResource(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + "setup.jpg"));

	/**
	 * Constructor, sets up this class
	 */
	public SetupView() {
		setLayout(new BorderLayout());

		addFormPanel();
		addStartingChampionsPanel();
		addSubmitPanel();
	}

	/**
	 * Creates the form panel which name, difficulty and weeks all sit within
	 */
	private void addFormPanel() {
		JPanel outerPanel = new JPanel(new GridBagLayout());
		outerPanel.setOpaque(false);
		formPanel = new JPanel();
		formPanel.setOpaque(false);
		formPanel.setLayout(new GridLayout(0, 1, 20, 20));
		outerPanel.add(formPanel);
		add(outerPanel, BorderLayout.NORTH);

		addHeaderPanel();
		addNamePanel();
		addWeeksPanel();
		addDifficultyPanel();
		addStartingChampionsHeader();
	}

	/**
	 * Adds a header
	 */
	private void addHeaderPanel() {
		formPanel.add(new HeaderPanel("Welcome to Ultimate Earth Championship!", true));
	}

	/**
	 * Adds a name panel where the player can choose what to name their team
	 */
	private void addNamePanel() {
		JPanel namePanel = new JPanel();
		namePanel.setDoubleBuffered(false);
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.setOpaque(false);
		formPanel.add(namePanel);

		// Label
		JLabel teamNameLabel = new JLabel("Team name:");
		teamNameLabel.setToolTipText("Must be between 3 and 15 characters, cannot contain special characters");
		teamNameLabel.setFont(Configuration.HEADER_FONT);
		teamNameLabel.setForeground(Color.white);
		namePanel.add(teamNameLabel);

		// Field
		teamNameField = new JTextField();
		teamNameField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		teamNameField.setFont(Configuration.HEADER_FONT);
		teamNameField.setMaximumSize(new Dimension(200, 40));
		namePanel.add(teamNameField);
	}

	/**
	 * Adds a week panel, where the player can choose how many weeks they need
	 */
	private void addWeeksPanel() {
		JPanel weeksPanel = new JPanel();
		weeksPanel.setLayout(new BoxLayout(weeksPanel, BoxLayout.X_AXIS));
		weeksPanel.setOpaque(false);
		formPanel.add(weeksPanel);

		// Label
		JLabel chooseWeeksLabel = new JLabel("Number of weeks in tournament:");
		chooseWeeksLabel.setFont(Configuration.HEADER_FONT);
		chooseWeeksLabel.setForeground(Color.white);
		weeksPanel.add(chooseWeeksLabel);

		// Spinner
		weekSelectionSpinner = new JSpinner();
		weekSelectionSpinner
				.setModel(new SpinnerNumberModel(5, config.MIN_NUM_GAME_WEEKS, config.MAX_NUM_GAME_WEEKS, 1));
		weekSelectionSpinner.setFont(Configuration.HEADER_FONT);
		weekSelectionSpinner.setMaximumSize(new Dimension(70, 40));
		weeksPanel.add(weekSelectionSpinner);
	}

	/**
	 * Adds a difficulty panel, where the player can choose the difficulty on a
	 * slider
	 */
	private void addDifficultyPanel() {
		JPanel difficultyPanel = new JPanel();
		difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
		difficultyPanel.setOpaque(false);
		formPanel.add(difficultyPanel);

		JPanel topPanel = new JPanel();
		topPanel.setOpaque(false);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		difficultyPanel.add(topPanel);

		// Label
		JLabel chooseDifficultyLabel = new JLabel("Choose the difficulty:");
		chooseDifficultyLabel.setFont(Configuration.HEADER_FONT);
		chooseDifficultyLabel.setForeground(Color.white);

		// Text
		difficultyText = new JLabel("");
		difficultyText.setFont(Configuration.HEADER_FONT);
		difficultyText.setForeground(Color.white);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		difficultyPanel.add(bottomPanel);

		// Slider
		difficultySlider = new JSlider();
		difficultySlider.setOpaque(false);
		difficultySlider.setPaintTicks(true);
		difficultySlider.setFont(Configuration.HEADER_FONT);
		difficultySlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				updateDifficultyText();
			}
		});
		difficultySlider.setMinorTickSpacing(25);
		difficultySlider.setValue(100);
		difficultySlider.setMinimum(50);
		difficultySlider.setMaximum(200);

		topPanel.add(chooseDifficultyLabel);
		topPanel.add(difficultySlider);
		bottomPanel.add(difficultyText);
	}

	/**
	 * Adds a header above the champion selection form
	 */
	private void addStartingChampionsHeader() {
		formPanel.add(new HeaderPanel("Choose four starting champions:", false));
	}

	/**
	 * Adds a grid which shows the 8 champions available to choose from
	 */
	private void addStartingChampionsPanel() {
		JPanel outerPanel = new JPanel();
		outerPanel.setOpaque(false);
		add(outerPanel, BorderLayout.CENTER);
		championsPanel = new JPanel(new GridLayout(2, 4, 50, 50));
		championsPanel.setOpaque(false);
		outerPanel.add(championsPanel);
		championsToChooseFrom = gameManager.getShop().getSetupChampions();
		for (Champion champion : championsToChooseFrom) {
			addChampionToPanel(champion);
		}
	}

	/**
	 * Adds a champion to a panel
	 *
	 * @param champion The champion to add to the panel
	 */
	private void addChampionToPanel(Champion champion) {
		PurchasableCard card = new ChampionCard(champion);
		card.addStatsPanel();
		card.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (chosenChampions.contains(champion)) {
					chosenChampions.remove(champion);
				} else {
					if (chosenChampions.size() < Configuration.NUM_STARTING_CHAMPIONS) {
						chosenChampions.add(champion);
					}
				}
				card.repaint();
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
		championsPanel.add(card);
	}

	/**
	 * Adds the submit panel, which contains the button for the player to submit
	 * their entered information
	 */
	private void addSubmitPanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setOpaque(false);
		add(panel, BorderLayout.SOUTH);

		JButton submitButton = new JButton("Submit");
		submitButton.setFont(Configuration.HEADER_FONT);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submitData();
			}
		});
		panel.add(submitButton);
	}

	/**
	 * Retrieves all the data from the components, Formats it, and then gives it to
	 * the setupManager to be checked. Setup Manager handles the logic checking,
	 * throwing InputExceptions at incorrect inputs
	 */
	private void submitData() {
		try {
			teamName = SetupManager.PromptForTeamName(teamNameField.getText());
			gameWeeks = SetupManager.PromptForNumWeeks(Integer.toString((int) weekSelectionSpinner.getValue()));
			difficulty = SetupManager.PromptForDifficulty(Float.toString((difficultySlider.getValue()) / 100f));
			chosenChampions = SetupManager.PromptForTeamChampions(chosenChampions);

			// If everything passed with no exceptions, prompt the player to confirm
			int reply = JOptionPane.showConfirmDialog(submitButton, "Are you sure you want to start the game?",
					"Confirm Setup", JOptionPane.YES_NO_OPTION);

			if (reply == 0) {
				gameManager.setupPlayerTeam(teamName, gameWeeks, chosenChampions, difficulty);
				gameManager.finishedSetup();
			}

		} catch (InputException e1) {
			JOptionPane.showMessageDialog(submitButton, e1.getMessage());
		}

	}

	/**
	 * Updates the text on the difficulty text label to reflect the chosen
	 * difficulty value. These are simply meant to be 'rules of thumb' to better
	 * contextualise the difficulty setting chosen to hte player/
	 */
	private void updateDifficultyText() {
		float difficulty = ((difficultySlider.getValue()) / 100f);
		String diffText = "";

		if (difficulty >= 0.5f && difficulty <= 0.75f) {
			diffText = "Easiest";
		} else if (difficulty > 0.75f && difficulty <= 0.9f) {
			diffText = "Easy";
		} else if (difficulty > 0.9f && difficulty <= 1.1f) {
			diffText = "Normal";
		} else if (difficulty > 1.1f && difficulty <= 1.5f) {
			diffText = "Hard";
		} else if (difficulty > 1.5f && difficulty <= 1.9f) {
			diffText = "Hard";
		} else if (difficulty > 1.5f && difficulty <= 1.9f) {
			diffText = "Hardest";
		} else if (difficulty > 1.9f && difficulty <= 2f) {
			diffText = "Extreme";
		}

		difficultyText.setText(diffText + " (" + difficulty + ")");

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
