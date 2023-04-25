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
import model.SetupManager.GUISetupStage;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SetupView extends JPanel{


	private static final long serialVersionUID = 4605160332340664881L;
	private JTextField textField;
	
	private model.Configuration config = model.Configuration.getInstance();

	private String teamName;
	private int gameWeeks;
	private ArrayList<Champion> champions;
	private float difficulty;
	
	private SetupManager.GUISetupStage stage;
	private GraphicalGameManager manager;
	
	public SetupView(GraphicalGameManager manager)
	{
		this.manager = manager;
		stage = stage.None;
		
		// Data requested:
		teamName = null;
		gameWeeks = 0;
		champions = new ArrayList<Champion>();
		difficulty = 0f;
	}
	
	public void showStage(GUISetupStage newStage)
	{
		this.stage = newStage;
		
		switch(stage)
		{
		case Name:
			getTeamName();
			break;
		case Weeks:
			getGameWeeks();
			break;
		}
	}
		
	
	public void getTeamName()
	{
		setLayout(null);
		
		JLabel headerLabel = new JLabel("Welcome to Ultimate Earth Championship");
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setBounds(10, 11, 250, 30);
		add(headerLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 102, 180, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel nameTeamLabel = new JLabel("Name your team:");
		nameTeamLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameTeamLabel.setBounds(10, 72, 220, 30);
		add(nameTeamLabel);
		
		JLabel nameInfoLabel1 = new JLabel(" - Must be between 3 and 15 characters");
		nameInfoLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		nameInfoLabel1.setBounds(10, 133, 332, 30);
		add(nameInfoLabel1);
		
		JLabel nameInfoLabel2 = new JLabel("- Cannot contain special characters");
		nameInfoLabel2.setHorizontalAlignment(SwingConstants.LEFT);
		nameInfoLabel2.setBounds(10, 163, 332, 30);
		add(nameInfoLabel2);
		
		JButton submitButton = new JButton("Submit");
		
		submitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					teamName = SetupManager.PromptForTeamName(textField.getText());
					
					manager.getNextStage(stage); // Ask for next stage
				} 
				catch (InputException e1) 
				{
					JOptionPane.showMessageDialog(submitButton, e1.getMessage());
				}
			}
		});
		submitButton.setBounds(10, 204, 89, 23);
		add(submitButton);
	}

	public void getGameWeeks()
	{
		setLayout(null);
		
		JLabel headerLabel = new JLabel("Welcome to Ultimate Earth Championship");
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setBounds(10, 11, 250, 30);
		add(headerLabel);
	}
}
