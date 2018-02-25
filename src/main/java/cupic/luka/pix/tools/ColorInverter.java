package cupic.luka.pix.tools;

import cupic.luka.pix.image.ImagePanel;

import java.awt.image.*;

/**
 * A tool for inverting the loaded image.
 *
 * @author Luka Cupic
 */
public class ColorInverter extends AbstractTool {

	/**
	 * Creates a new ColorInverter object.
	 *
	 * @param imagePanel the panel holding the image
	 */
	public ColorInverter(ImagePanel imagePanel) {
		super(imagePanel);
	}

	/**
	 * Inverts the image held in {@link #imagePanel}.
	 */
	public void invert() {
		BufferedImage im = imagePanel.getImage();
		int width = im.getWidth();
		int height = im.getHeight();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				im.setRGB(i, j, im.getRGB(i, j) ^ 0x00FFFFFF);
			}
		}
		imagePanel.repaint();
	}
}
