package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import display.GraphicalDisplay;
import manager.GameManager;
import manager.GraphicalGameManager;
import model.Configuration;
import story.Cutscene;
import story.CutsceneSlide;

public class CutsceneView extends JPanel {
	
	private static final long serialVersionUID = -3705567359135004719L;

	private String slideText;
	private String slideImagePath;
	
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();
	private Cutscene cutscene;
	
	private JTextPane mainText;
	private JButton continueButton;

	private ImageIcon icon;
	
	public CutsceneView(Cutscene cutscene) {
		this.cutscene = cutscene;
		
		initialize();
		redrawPanel();
	}

	private void initialize()
	{
		setLayout(new BorderLayout(0, 0));
		
		// Text Area:
		JPanel textPanel = new JPanel(new GridBagLayout());
		mainText = new JTextPane();
		mainText.setText(slideText);
		mainText.setFont(Configuration.HEADER_FONT);
		mainText.setForeground(Color.white);
		mainText.setOpaque(true);
		mainText.setBackground(Color.darkGray);
		mainText.setPreferredSize(new Dimension((int) (GraphicalDisplay.WIDTH / 2), 100));
		textPanel.add(mainText);
		textPanel.setOpaque(false);
		add(textPanel, BorderLayout.CENTER);
		
		// Panel to push button to bottom center of screen
		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		add(bottomPanel, BorderLayout.SOUTH);
		
		// Button...
		continueButton = new JButton("Continue...");
		continueButton.setFont(Configuration.HEADER_FONT);
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				buttonPressed();
			}
		});
		bottomPanel.add(continueButton);
	}
	
	public void getNextSlide()
	{
		CutsceneSlide slide = cutscene.nextSlide();
		slideText = slide.getText();
		slideImagePath = slide.getImagePath();
		icon = new ImageIcon(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + slideImagePath);
		
		if (cutscene.checkNextSlide() == null)
		{
			continueButton.setText("Go to setup");
		}
		else {
			continueButton.setText("Continue...");
		}
	}
	
	/**
	 * Updates the text and images on the panel
	 */
	public void redrawPanel()
	{
		getNextSlide();
		
		mainText.setText(slideText);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    int yPos = (int) ((GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2);
        g.drawImage(icon.getImage(), 0, yPos, null);
	}

	private void buttonPressed()
	{
		// check if next slide is null
		if (cutscene.checkNextSlide() == null)
		{
			// Cutscene finished, tell manager
			gameManager.finishedCutscene();
			cutscene.nextSlide(); // Make the cutscene iterate again (to reset it back to 0)
		}
		else 
		{
			// Tell manager to redraw me
			gameManager.repaintCutscene();
		}
		
	}
	
}
