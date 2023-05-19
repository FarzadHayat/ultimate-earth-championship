package views;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Configuration;

/**
 * A stat label is a panel that shows a certain stat in a pre-formatted way. The
 * stat label can either take just a value for the label or an image file name
 * along with the value.
 */
public class StatLabel extends JPanel {

	private static final long serialVersionUID = 7715315720355045403L;

	/**
	 * Image width
	 */
	private final int IMAGE_WIDTH = 30;
	/**
	 * Image height
	 */
	private final int IMAGE_HEIGHT = IMAGE_WIDTH;

	/**
	 * The value label
	 */
	private JLabel valueLabel;

	/**
	 * Create the panel with a value.
	 *
	 * @param value of value to display on the stat label.
	 */
	public StatLabel(String value) {
		setLayout(new GridBagLayout());
		setOpaque(false);

		addValueLabel(value);
	}

	/**
	 * Create the panel with an image and value. The image should be located inside
	 * the folder path set by Configuration.ICON_IMAGE_FOLDER_PATH
	 *
	 * @param imageFileName the name of the image file to load
	 * @param value         of value to display on the stat label.
	 */
	public StatLabel(String imageFileName, String value) {
		setLayout(new GridBagLayout());
		setOpaque(false);

		addImageLabel(imageFileName);
		addValueLabel(value);
	}

	/**
	 * Returns the label that holds the value of the stat label
	 *
	 * @return the valueLabel
	 */
	public JLabel getValueLabel() {
		return valueLabel;
	}

	/**
	 * Set the label that should hold the value of the stat label
	 *
	 * @param valueLabel the valueLabel to set
	 */
	public void setValueLabel(JLabel valueLabel) {
		this.valueLabel = valueLabel;
	}

	/**
	 * Add a value label to the stat label.
	 *
	 * @param value the value of the value label
	 */
	private void addValueLabel(String value) {
		setValueLabel(new JLabel(value));
		getValueLabel().setFont(Configuration.TEXT_FONT);
		add(getValueLabel());
	}

	/**
	 * Add an image label to the stat label if the image file is found or an empty
	 * image otherwise.
	 *
	 * @param imageFileName the name of the image file including the file extension
	 *                      located in Configuration.ICON_IMAGE_FOLDER_PATH
	 */
	private void addImageLabel(String imageFileName) {
		ImageIcon imageIcon = null;
		String path = Configuration.ICON_IMAGE_FOLDER_PATH + imageFileName + ".png";
		imageIcon = new ImageIcon(getClass().getResource(path));
		ImageIcon resizedIcon;
		if (imageIcon != null) {
			resizedIcon = new ImageIcon(
					imageIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		} else {
			resizedIcon = new ImageIcon(new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB));
		}
		add(new JLabel(resizedIcon));
	}

}
