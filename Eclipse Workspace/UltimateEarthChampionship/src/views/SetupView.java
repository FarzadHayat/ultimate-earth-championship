package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exception.InputException;
import manager.GameManager;
import manager.GraphicalGameManager;
import model.Champion;
import model.SetupManager;

public class SetupView extends JPanel{
	
	private static final long serialVersionUID = 4605160332340664881L;
	
	private model.Configuration config = model.Configuration.getInstance();

	private GraphicalGameManager manager = (GraphicalGameManager) GameManager.getInstance();
		
	/**
	 * List of champions that can be chosen from in setup
	 */
	private ArrayList<Champion> championsToChooseFrom;
	
	// Data Requested:
	private String teamName;
	private int gameWeeks;
	private ArrayList<Champion> chosenChampions;
	private float difficulty;
	
	// Swing components:
	// These aren't going to be commented as their names should be very self explanatory
	private JTextField teamNameField;
	private JButton submitButton;
	private JSpinner weekSelectionSpinner;
	private JSlider difficultySlider;
	
	private JList availChampionsList;
	private JList chosenChampionsList;
	private JLabel difficultyText;
	
	/**
	 * Constructor, sets up this class
	 * @param manager The game manager
	 */
	public SetupView()
	{	
		// Data requested:
		teamName = null;
		gameWeeks = 0;
		chosenChampions = new ArrayList<Champion>();
		difficulty = 0f;
		
		// Get champions:
		championsToChooseFrom = manager.getShop().getStartingChampions();
		
		// Draw the panel
		initialize();
	}
	
	/**
	 * Creates all the Swing components so they can be displayed
	 */
	private void initialize()
	{
		setLayout(null);
		
		JLabel headerLabel = new JLabel("Welcome to Ultimate Earth Championship");
		headerLabel.setBounds(10, 11, 430, 30);
		headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(headerLabel);
		
		teamNameField = new JTextField();
		teamNameField.setBounds(145, 51, 200, 20);
		add(teamNameField);
		
		JLabel nameTeamLabel = new JLabel("Team name:");
		nameTeamLabel.setBounds(10, 46, 278, 30);
		nameTeamLabel.setToolTipText("Must be between 3 and 15 characters, cannot contain special characters");
		nameTeamLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(nameTeamLabel);
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(10, 628, 105, 30);
		
		submitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				submitData();
			}
		});
		add(submitButton);
		
		JLabel chooseWeeksLabel = new JLabel("Number of weeks in tournament:");
		chooseWeeksLabel.setBounds(10, 87, 230, 30);
		chooseWeeksLabel.setToolTipText("");
		chooseWeeksLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(chooseWeeksLabel);
		
		weekSelectionSpinner = new JSpinner();
		weekSelectionSpinner.setBounds(294, 92, 51, 20);
		weekSelectionSpinner.setModel(new SpinnerNumberModel(5, config.MIN_NUM_GAME_WEEKS, config.MAX_NUM_GAME_WEEKS, 1));
		add(weekSelectionSpinner);
		
		JLabel chooseDifficultyLabel = new JLabel("Choose the difficulty:");
		chooseDifficultyLabel.setBounds(10, 128, 143, 30);
		chooseDifficultyLabel.setToolTipText("");
		chooseDifficultyLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(chooseDifficultyLabel);
		
		difficultyText = new JLabel("");
		difficultyText.setBounds(177, 156, 143, 30);
		difficultyText.setToolTipText("");
		difficultyText.setHorizontalAlignment(SwingConstants.CENTER);
		add(difficultyText);
		
		difficultySlider = new JSlider();
		difficultySlider.setBounds(145, 132, 200, 26);
		difficultySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				updateDifficultyText();
			}
		});
		difficultySlider.setPaintTicks(true);
		difficultySlider.setMinorTickSpacing(25);
		difficultySlider.setValue(100);
		difficultySlider.setMinimum(50);
		difficultySlider.setMaximum(200);
		add(difficultySlider);
		
		
		
		// Some more labels:
		JLabel championsChooseHeaderLabel = new JLabel("Champions to choose from:");
		championsChooseHeaderLabel.setBounds(396, 52, 193, 14);
		add(championsChooseHeaderLabel);
		
		JLabel chosenChampionsLabel = new JLabel("Chosen champions:     (Choose 4)");
		chosenChampionsLabel.setBounds(396, 253, 193, 14);
		add(chosenChampionsLabel);
		
		// Available champions list
		availChampionsList = new JList<Object>();
		availChampionsList.setBounds(396, 77, 601, 150);
		availChampionsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Move champion from available champion to chosen champion...
				int availChampIndex = availChampionsList.getSelectedIndex();
				
				if (availChampIndex == -1)
				{
					// This returns -1 sometimes, I think its due to mouse input
					// if this happens we can ignore this
					return;
				}
				
				if (chosenChampions.size() >= 4)
				{
					// Prevent more than four being selected!
					return;
				}
				
				chosenChampions.add(championsToChooseFrom.get(availChampIndex));
				championsToChooseFrom.remove(availChampIndex);
				
				updateChampionLists();
			}
		});
		availChampionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(availChampionsList);
		
		// Chosen champions lists
		chosenChampionsList = new JList<Object>();
		chosenChampionsList.setBounds(396, 278, 601, 90);
		chosenChampionsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				// Move champion from chosen champions to available champion...
				
				int chosenChampIndex = chosenChampionsList.getSelectedIndex();
				
				if (chosenChampIndex == -1)
				{
					// This returns -1 sometimes, I think its due to mouse input
					// if this happens we can ignore this
					return;
				}
				
				championsToChooseFrom.add(chosenChampions.get(chosenChampIndex));
				chosenChampions.remove(chosenChampIndex);
				
				updateChampionLists();
			}
		});
		chosenChampionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(chosenChampionsList);
		
		updateChampionLists();
	}
	
	/**
	 * Retrieves all the data from the components,
	 * Formats it, and then gives it to the setupManager to be checked.
	 * Setup Manager handles the logic checking, throwing InputExceptions at incorrect inputs
	 */
	private void submitData()
	{
		try 
		{
			teamName = SetupManager.PromptForTeamName(teamNameField.getText());
			gameWeeks = SetupManager.PromptForNumWeeks(Integer.toString((int) weekSelectionSpinner.getValue()));
			difficulty = SetupManager.PromptForDifficulty( Float.toString(((float) difficultySlider.getValue()) / 100f));
			chosenChampions = SetupManager.PromptForTeamChampions(chosenChampions);
			
			// If everything passed with no exceptions, prompt the player to confirm
			int reply = JOptionPane.showConfirmDialog(submitButton, 
					"Are you sure you want to start the game?", 
					"Confirm Setup", 
					JOptionPane.YES_NO_OPTION); 
			
			if (reply == 0)
			{
				manager.setupPlayerTeam(teamName, gameWeeks, chosenChampions, difficulty);
				manager.finishedSetup();
			}
			
		} 
		catch (InputException e1) 
		{
			JOptionPane.showMessageDialog(submitButton, e1.getMessage());
		}		
		
	}
	
	/**
	 * Updates the champion JLists based on the chosenChampions and championsToChooseFrom ArrayLists
	 */
	private void updateChampionLists()
	{
		// Chosen champions model
		DefaultListModel<String> chosenChampModel = new DefaultListModel<String>();
		for (Champion champ : chosenChampions)
		{
			chosenChampModel.addElement(
					champ.getName() + " | Max Stamina: " + champ.getMaxStamina() + " | Regen: " +
					champ.getRegen() + " | Offense: " + champ.getOffense() + " | Defense: " + champ.getDefense());
		}
		
		chosenChampionsList.setModel(chosenChampModel);
		
		// Available champions model
		DefaultListModel<String> availChampModel = new DefaultListModel<String>();
		for (Champion champ : championsToChooseFrom)
		{
			availChampModel.addElement(
					champ.getName() + " | Max Stamina: " + champ.getMaxStamina() + " | Regen: " +
					champ.getRegen() + " | Offense: " + champ.getOffense() + " | Defense: " + champ.getDefense());
		}
		
		availChampionsList.setModel(availChampModel);
	}
	
	/**
	 * Updates the text on the difficulty text label to reflect the chosen difficulty value
	 */
	private void updateDifficultyText()
	{
		float difficulty = (((float) difficultySlider.getValue()) / 100f);
		String diffText = "";
		
		if (difficulty >= 0.5f && difficulty <= 0.75f)
		{
			diffText = "Easiest";
		}
		else if (difficulty > 0.75f && difficulty <= 0.9f)
		{
			diffText = "Easy";
		}
		else if (difficulty > 0.9f && difficulty <= 1.1f)
		{
			diffText = "Normal";
		}
		else if (difficulty > 1.1f && difficulty <= 1.5f)
		{
			diffText = "Hard";
		}
		else if (difficulty > 1.5f && difficulty <= 1.9f)
		{
			diffText = "Hard";
		}
		else if (difficulty > 1.5f && difficulty <= 1.9f)
		{
			diffText = "Hardest";
		}
		else if (difficulty > 1.9f && difficulty <= 2f)
		{
			diffText = "Extreme";
		}
		
		difficultyText.setText(diffText + " (" + difficulty + ")");
		
	}


}
