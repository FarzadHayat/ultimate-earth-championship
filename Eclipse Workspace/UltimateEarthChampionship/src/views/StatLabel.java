package views;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Configuration;

public class StatLabel extends JPanel {

	private static final long serialVersionUID = 7715315720355045403L;

	private final int IMAGE_WIDTH = 30;
	private final int IMAGE_HEIGHT = IMAGE_WIDTH;

	private JLabel valueLabel;
	
	/**
	 * Create the panel.
	 */
	public StatLabel(String value) {
		setLayout(new GridBagLayout());
		setOpaque(false);
		
		addValueLabel(value);
	}
	
	/**
	 * Create the panel.
	 */
	public StatLabel(String imageFileName, String value) {
		setLayout(new GridBagLayout());
		setOpaque(false);
		
		addImageLabel(imageFileName);
		addValueLabel(value);
	}
	
	public JLabel getValueLabel() {
		return valueLabel;
	}

	public void setValueLabel(JLabel valueLabel) {
		this.valueLabel = valueLabel;
	}

	/**
	 * Add a value label to the stat label.
	 * @param value the value of the value label
	 */
	private void addValueLabel(String value) {
		setValueLabel(new JLabel(value));
		getValueLabel().setFont(Configuration.TEXT_FONT);
		add(getValueLabel());
	}

	/**
	 * Add an image label to the stat label if the image file is found or an empty image otherwise.
	 * @param imageFileName the name of the image file including the file extension located in Configuration.ICON_IMAGE_FOLDER_PATH
	 */
	private void addImageLabel(String imageFileName) {
		ImageIcon imageIcon = null;
		try {
			String path = Configuration.ICON_IMAGE_FOLDER_PATH + imageFileName + ".png";
            imageIcon = new ImageIcon(ImageIO.read(new File(path)));

        } catch (IOException e) {
        	if (Configuration.DEBUG) {
        		System.out.println("Could not find image file for " + imageFileName + " label!");
        	}
        }
    	ImageIcon resizedIcon;
		if (imageIcon != null) {
			resizedIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		}
		else {
			resizedIcon = new ImageIcon(new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB));
		}
    	add(new JLabel(resizedIcon));
	}

}
