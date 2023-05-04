package views;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Image.*;

import javax.swing.SwingConstants;

import model.Champion;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;

public class ChampionMatchCard extends JPanel{

	private Champion champion;
	
	private JPanel mainPanel;
	
	private JLabel championNameText;
	private JLabel championHealthText;
	private JPanel panel;
	private JLabel championImage;
	
	public Champion getChampion()
	{
		return champion;
	}
	
	/**
	 * Constructor
	 * @param centerGrid The grid to be added to
	 * @param row The lane for this card to sit in
	 * @param column The position along the aforementioned lane
	 */
	public ChampionMatchCard(JPanel centerGrid, int row, int column) {
		
		mainPanel = new JPanel();
		centerGrid.add(mainPanel);
		mainPanel.setLayout(new CardLayout(10, 10));
		
		JPanel centerPanel = new JPanel();
		mainPanel.add(centerPanel, "name_205877267141400");
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		championNameText = new JLabel("Champion Name");
		championNameText.setHorizontalAlignment(SwingConstants.CENTER);
		championNameText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		centerPanel.add(championNameText, BorderLayout.NORTH);
		
		panel = new JPanel();
		centerPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		championImage = new JLabel("");
		championImage.setIcon(new ImageIcon());
		championImage.setHorizontalAlignment(SwingConstants.CENTER);
		championImage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.add(championImage);
		
		championHealthText = new JLabel("0/100 Health");
		championHealthText.setHorizontalAlignment(SwingConstants.CENTER);
		championHealthText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		centerPanel.add(championHealthText, BorderLayout.SOUTH);
	}
	
	/**
	 * Assigns a new champion to this card
	 * @param assignedChampion
	 */
	public void setChampion(Champion assignedChampion)
	{
		champion = assignedChampion;
		
		updateCard();
	}
	
	/**
	 * Updates the card to reflect the info of the champion assigned
	 */
	public void updateCard()
	{

		if (champion == null)
		{
			// No champion assigned to this...
			championNameText.setText("");
			
			championImage.setIcon(null);
			championImage.setText("");
			
			championHealthText.setText("");
			
		}
		else if (champion.getHealth() <= 0)
		{
			championNameText.setText("");
			
			championImage.setIcon(null);
			championImage.setText("");
			
			championHealthText.setText("");
		}
		else
		{
			// Champion is assigned, show data
			String champName = champion.getName();
			if (champion.isFlagCarrier())
			{
				champName = "[Flag] " + champName;
			}
			
			championNameText.setText(champName);
			
			//Image championSprite = new Image();
			
			ImageIcon originalImage = champion.getImage();
			ImageIcon rescaledImage = new ImageIcon(originalImage.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
			
			championImage.setIcon(rescaledImage);
			//champion
			championImage.setText("");
			
			championHealthText.setText(champion.getHealth() + "/" + champion.getMaxHealth());
		}
	}
	
	/**
	 * Removes the champion from this card
	 */
	public void removeChampion()
	{
		champion = null;
		
		updateCard();
	}
}
