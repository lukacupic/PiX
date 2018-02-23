package cupic.luka.pix.image;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A {@link JPanel} for holding a single {@link BufferedImage}
 * in it's center.
 *
 * @author Luka Cupic
 */
public class ImagePanel extends JPanel {

	/**
	 * The image to hold.
	 */
	private BufferedImage image;

	/**
	 * The original version of the image.
	 * Held for better performance purposes.
	 */
	private BufferedImage original;

	/**
	 * Creates a new ImagePanel object.
	 *
	 * @param file the file from which to read the image
	 * @throws IOException if the image cannot be read
	 */
	public ImagePanel(File file) throws IOException {
		try {
			image = ImageIO.read(file);
			original = ImageUtil.copyImage(image);
		} catch (IOException ex) {
			throw new IOException();
		}
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Gets the original image.
	 *
	 * @return the original image
	 */
	public BufferedImage getOriginal() {
		return original;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x = (this.getWidth() - image.getWidth()) / 2;
		int y = (this.getHeight() - image.getHeight()) / 2;
		g.drawImage(image, x, y, null);
	}
}