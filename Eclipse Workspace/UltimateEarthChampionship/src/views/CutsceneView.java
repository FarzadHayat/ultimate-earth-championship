package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
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

/**
 * A panel to display a cutscene. The cutscene includes a list of slides that
 * this view will iterate through. Once the cutscene is done, the game manager
 * is called to move onto the next view.
 */
public class CutsceneView extends JPanel {

	private static final long serialVersionUID = -3705567359135004719L;
	private GraphicalGameManager gameManager = (GraphicalGameManager) GameManager.getInstance();

	/**
	 * The cutscene instance
	 */
	private Cutscene cutscene;

//	The current cutscene slide text and image path
	private String slideText;
	private String slideImagePath;

//	Text pane for the current cutscene text
	private JTextPane mainText;

	/**
	 * The button to go to the next cutscene slide
	 */
	private JButton continueButton;

	/**
	 * Background image for the current cutscene slide
	 */
	private ImageIcon icon;

	/**
	 * Creates the panel.
	 *
	 * @param cutscene the cutscene object to display
	 */
	public CutsceneView(Cutscene cutscene) {
		this.cutscene = cutscene;

		initialize();
		redrawPanel();
	}

	/**
	 * Initializes the panel contents. The panel contains the story text and the
	 * continue button.
	 */
	private void initialize() {
		setLayout(new BorderLayout(0, 0));

		// Text Area:
		JPanel textPanel = new JPanel(new GridBagLayout());
		mainText = new JTextPane();
		mainText.setText(slideText);
		mainText.setFont(Configuration.HEADER_FONT);
		mainText.setForeground(Color.white);
		mainText.setOpaque(true);
		mainText.setBackground(Color.darkGray);
		mainText.setPreferredSize(new Dimension(GraphicalDisplay.WIDTH / 2, 100));
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
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonPressed();
			}
		});
		bottomPanel.add(continueButton);
	}

	/**
	 * Retrieves the next slide from the cutscene.
	 */
	public void getNextSlide() {
		CutsceneSlide slide = cutscene.nextSlide();
		slideText = slide.getText();
		slideImagePath = slide.getImagePath();
		icon = new ImageIcon(Configuration.BACKGROUND_IMAGE_FOLDER_PATH + slideImagePath);

		if (cutscene.checkNextSlide() == null) {
			continueButton.setText("Go to setup");
		} else {
			continueButton.setText("Continue...");
		}
	}

	/**
	 * Updates the text and images on the panel to the next slide.
	 */
	public void redrawPanel() {
		getNextSlide();

		mainText.setText(slideText);
	}

	/**
	 * Paint the component then draw the background image onto the component.
	 *
	 * @param g the graphics object to draw onto
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int yPos = (GraphicalDisplay.HEIGHT - icon.getIconHeight()) / 2;
		g.drawImage(icon.getImage(), 0, yPos, null);
	}

	/**
	 * If there is more slides to display, call game manager to update the view,
	 * otherwise we have reached the end of the cutscene and tell game manager that
	 * this view is finished.
	 */
	private void buttonPressed() {
		// check if next slide is null
		if (cutscene.checkNextSlide() == null) {
			// Cutscene finished, tell manager
			gameManager.finishedCutscene();
			cutscene.nextSlide(); // Make the cutscene iterate again (to reset it back to 0)
		} else {
			// Tell manager to redraw me
			gameManager.repaintCutscene();
		}

	}

}
