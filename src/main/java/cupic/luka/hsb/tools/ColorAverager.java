package cupic.luka.hsb.tools;

import cupic.luka.hsb.image.ImagePanel;
import cupic.luka.hsb.image.ImageUtil;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.image.*;

/**
 * @author Luka Cupic
 */
public class ColorAverager extends JPanel {

	/**
	 * The panel holding the image.
	 */
	private ImagePanel imagePanel;

	public ColorAverager(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;

		initGUI();
	}

	/**
	 * Initializes the panel's GUI.
	 */
	private void initGUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Color average = ImageUtil.getAverageColor(imagePanel.getImage());
		BufferedImage averageImage = ImageUtil.createImageFromColor(average, 50, 50);

		add(new JLabel("Average color:"));
		add(new JLabel(new ImageIcon(averageImage)));

		String hex = String.format("#%02X%02X%02X", average.getRed(), average.getGreen(), average.getBlue());
		JTextField field = new JTextField(hex);
		field.setEditable(false);
		add(field);
	}
}
