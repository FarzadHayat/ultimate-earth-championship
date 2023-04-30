package views;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Configuration;

public class StatLabel extends JPanel {

	private static final long serialVersionUID = 7715315720355045403L;

	private final int IMAGE_WIDTH = 30;
	private final int IMAGE_HEIGHT = IMAGE_WIDTH;
	
	/**
	 * Create the panel.
	 */
	public StatLabel(String imageFileName, String value) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setOpaque(false);
		if (imageFileName != "") {
			ImageIcon imageIcon = null;
        	try {
        		imageIcon = new ImageIcon(Configuration.ICON_IMAGE_FOLDER_PATH + imageFileName);
        	}
        	catch (NullPointerException e) {
        		System.out.println(e.getMessage());
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
		add(new JLabel(value));
	}

}
