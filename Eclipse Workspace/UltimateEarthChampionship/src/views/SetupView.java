package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import exception.InputException;
import manager.GraphicalGameManager;
import model.Champion;
import model.SetupManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class SetupView extends JPanel{


	private static final long serialVersionUID = 4605160332340664881L;
	
	private model.Configuration config = model.Configuration.getInstance();

	private GraphicalGameManager manager;
	
	private ArrayList<Champion> championsToChooseFrom;
	
	// Data Requested:
	private String teamName;
	private int gameWeeks;
	private ArrayList<Champion> chosenChampions;
	private float difficulty;
	
	// Swing components:
	private JTextField teamNameField;
	private JButton submitButton;
	private JSpinner weekSelectionSpinner;
	private JSlider difficultySlider;
	
	private JList availChampionsList;
	private JList chosenChampionsList;
	
	private JLabel difficultyText;
	
	public SetupView(GraphicalGameManager manager)
	{
		this.manager = manager;
		
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
	
	private void initialize()
	{
		setLayout(null);
		
		JLabel headerLabel = new JLabel("Welcome to Ultimate Earth Championship");
		headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		headerLabel.setBounds(10, 11, 430, 30);
		add(headerLabel);
		
		teamNameField = new JTextField();
		teamNameField.setBounds(145, 51, 200, 20);
		add(teamNameField);
		
		JLabel nameTeamLabel = new JLabel("Team name:");
		nameTeamLabel.setToolTipText("Must be between 3 and 15 characters, cannot contain special characters");
		nameTeamLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameTeamLabel.setBounds(10, 46, 278, 30);
		add(nameTeamLabel);
		
		JButton submitButton = new JButton("Submit");
		
		submitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				submitData();
			}
		});
		submitButton.setBounds(10, 266, 89, 23);
		add(submitButton);
		
		JLabel chooseWeeksLabel = new JLabel("Number of weeks in tournament:");
		chooseWeeksLabel.setToolTipText("");
		chooseWeeksLabel.setHorizontalAlignment(SwingConstants.LEFT);
		chooseWeeksLabel.setBounds(10, 87, 230, 30);
		add(chooseWeeksLabel);
		
		weekSelectionSpinner = new JSpinner();
		weekSelectionSpinner.setModel(new SpinnerNumberModel(5, config.MIN_NUM_GAME_WEEKS, config.MAX_NUM_GAME_WEEKS, 1));
		weekSelectionSpinner.setBounds(294, 92, 51, 20);
		add(weekSelectionSpinner);
		
		JLabel chooseDifficultyLabel = new JLabel("Choose the difficulty:");
		chooseDifficultyLabel.setToolTipText("");
		chooseDifficultyLabel.setHorizontalAlignment(SwingConstants.LEFT);
		chooseDifficultyLabel.setBounds(10, 128, 143, 30);
		add(chooseDifficultyLabel);
		
		difficultyText = new JLabel("");
		difficultyText.setToolTipText("");
		difficultyText.setHorizontalAlignment(SwingConstants.CENTER);
		difficultyText.setBounds(177, 156, 143, 30);
		add(difficultyText);
		
		difficultySlider = new JSlider();
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
		difficultySlider.setBounds(145, 132, 200, 26);
		add(difficultySlider);
		
		
		
		// Some more labels:
		JLabel championsChooseHeaderLabel = new JLabel("Champions to choose from:");
		championsChooseHeaderLabel.setBounds(397, 69, 193, 14);
		add(championsChooseHeaderLabel);
		
		JLabel chosenChampionsLabel = new JLabel("Chosen champions:     (Choose 4)");
		chosenChampionsLabel.setBounds(397, 270, 193, 14);
		add(chosenChampionsLabel);
		
		// Available champions list
		availChampionsList = new JList<Object>();
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
		availChampionsList.setBounds(397, 94, 601, 150);
		add(availChampionsList);
		
		// Chosen champions lists
		chosenChampionsList = new JList<Object>();
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
		chosenChampionsList.setBounds(397, 295, 601, 90);
		add(chosenChampionsList);
		
		updateChampionLists();
	}
	
	private void submitData()
	{
		try 
		{
			teamName = SetupManager.PromptForTeamName(teamNameField.getText());
			gameWeeks = SetupManager.PromptForNumWeeks(Integer.toString((int) weekSelectionSpinner.getValue()));
			difficulty = SetupManager.PromptForDifficulty( Float.toString(((float) difficultySlider.getValue()) / 100f));
			
			// In case of too few champions
			if (chosenChampions.size() < 4)
			{
				throw new InputException("Not enough champions in your team!");
			}
		} 
		catch (InputException e1) 
		{
			JOptionPane.showMessageDialog(submitButton, e1.getMessage());
		}
		
		// If everything passed the test:
		manager.setupPlayerTeam(teamName, gameWeeks, chosenChampions, difficulty); // :) setup done
		manager.finishedSetup(); // Let gameManager know we are done
	}
	
	private void updateChampionLists()
	{
		// Chosen champions model
		DefaultListModel<String> chosenChampModel = new DefaultListModel<String>();
		for (Champion champ : chosenChampions)
		{
			chosenChampModel.addElement(
					champ.getName() + " | Max Health: " + champ.getMaxHealth() + " | Max Stamina: " +
					champ.getMaxStamina() + " | Offense: " + champ.getOffense() + " | Defense: " + champ.getDefense());
		}
		
		chosenChampionsList.setModel(chosenChampModel);
		
		// Available champions model
		DefaultListModel<String> availChampModel = new DefaultListModel<String>();
		for (Champion champ : championsToChooseFrom)
		{
			availChampModel.addElement(
					champ.getName() + " | Max Health: " + champ.getMaxHealth() + " | Max Stamina: " +
					champ.getMaxStamina() + " | Offense: " + champ.getOffense() + " | Defense: " + champ.getDefense());
		}
		
		availChampionsList.setModel(availChampModel);
	}
	
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
