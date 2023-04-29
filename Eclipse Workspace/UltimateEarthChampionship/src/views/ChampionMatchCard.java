package views;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;

import model.Champion;

public class ChampionMatchCard extends JPanel{

	private Champion champion;
	
	private JPanel mainPanel;
	
	private JLabel championNameText;
	private JLabel championImage;
	private JLabel championHealthText;
	
//	public JLabel getNameText()
//	{
//		return championNameText;
//	}
//	
//	public JLabel getImage()
//	{
//		return championImage;
//	}
//	
//	public JLabel getHealthText()
//	{
//		return championHealthText;
//	}
	
	public Champion getChampion()
	{
		return champion;
	}
	
	public ChampionMatchCard(JPanel centerGrid, int row, int column) {
		
		mainPanel = new JPanel();
		centerGrid.add(mainPanel);
		mainPanel.setLayout(new CardLayout(35, 35));
		
		JPanel centerPanel = new JPanel();
		mainPanel.add(centerPanel, "name_213159913163300");
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		championNameText = new JLabel("Champion Name");
		championNameText.setHorizontalAlignment(SwingConstants.CENTER);
		championNameText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		centerPanel.add(championNameText, BorderLayout.NORTH);
		
		championImage = new JLabel("image");
		championImage.setHorizontalAlignment(SwingConstants.CENTER);
		championImage.setFont(new Font("Tahoma", Font.PLAIN, 11));
		centerPanel.add(championImage, BorderLayout.CENTER);
		
		championHealthText = new JLabel("0/100 Health");
		championHealthText.setHorizontalAlignment(SwingConstants.CENTER);
		championHealthText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		centerPanel.add(championHealthText, BorderLayout.SOUTH);
	}
	
	public void setChampion(Champion assignedChampion)
	{
		champion = assignedChampion;
		
		updateCard();
	}
	
	public void updateCard()
	{
		if (champion == null)
		{
			// No champion assigned to this...
			championNameText.setText("");
			
			championImage.setIcon(null);
			
			championHealthText.setText("");
			
		}
		else 
		{
			// Champion is assigned, show data
			championNameText.setText(champion.getName());
			
			championImage.setIcon(null);
			
			championHealthText.setText(champion.getHealth() + "/" + champion.getMaxHealth());
		}
	}
	
	public void removeChampion()
	{
		champion = null;
		
		updateCard();
	}
}
