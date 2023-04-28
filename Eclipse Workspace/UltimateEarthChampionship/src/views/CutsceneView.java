package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import manager.GameManager;
import manager.GraphicalGameManager;
import story.Cutscene;

public class CutsceneView extends JPanel {
	
	private static final long serialVersionUID = -3705567359135004719L;
	
	private GraphicalGameManager manager = (GraphicalGameManager) GameManager.getInstance();
	private Cutscene cutscene;
	
	public CutsceneView(Cutscene cutscene) {
		this.cutscene = cutscene;
		setLayout(new BorderLayout(0, 0));
		addText();

		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		if (cutscene.checkNextSlide() == null)
		{
			JButton finishButton = new JButton("Go to setup");
			finishButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					cutscene.nextSlide();
					manager.finishedCutscene();
				}
			});
			bottomPanel.add(finishButton);
		}
		else {
			JButton continueButton = new JButton("Continue...");
			continueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					cutscene.nextSlide();
					manager.repaintCutscene();
				}
			});
			bottomPanel.add(continueButton);
		}
	}

	private void addText() {
		JLabel mainText = new JLabel(cutscene.getCurrentSlide().getText());
		mainText.setHorizontalAlignment(SwingConstants.CENTER);
		add(mainText);
	}
	
}
