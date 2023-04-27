package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import main.GraphicalGameManager;
import story.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CutsceneView extends JPanel {
	
	private static final long serialVersionUID = -3705567359135004719L;

	private String slideText;
	private String slideImagePath;
	
	private GraphicalGameManager manager;
	private Cutscene cutscene;
	
	private JLabel mainText;
	private JButton continueButton;
	
	public CutsceneView(Cutscene cutscene, GraphicalGameManager manager) {
		this.cutscene = cutscene;
		this.manager = manager;
		
		
		initialize();
		reDrawPanel();
	}

	private void initialize()
	{
		setLayout(new BorderLayout(0, 0));
		
		// Text Area:
		mainText = new JLabel(slideText);
		mainText.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(mainText);
		
		// Panel to push button to bottom center of screen
		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		
		// Button...
		continueButton = new JButton("Continue...");
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
		slideImagePath = slide.getImage();
		
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
	public void reDrawPanel()
	{
		getNextSlide();
		
		mainText.setText(slideText);
		
		// TODO: Setup image path here:
	}

	private void buttonPressed()
	{
		// check if next slide is null
		if (cutscene.checkNextSlide() == null)
		{
			// Cutscene finished, tell manager
			manager.cutsceneFinished();
			cutscene.nextSlide(); // Make the cutscene iterate again (to reset it back to 0)
		}
		else 
		{
			// Tell manager to redraw me
			manager.drawCutscene();
		}
		
	}
	
}
