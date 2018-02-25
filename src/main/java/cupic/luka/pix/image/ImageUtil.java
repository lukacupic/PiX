package cupic.luka.pix.image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A utility class which holds some commonly used methods
 * while working with images
 *
 * @author Luka Cupic
 */
public class ImageUtil {

	/**
	 * Creates a copy of the given image.
	 *
	 * @param source the image to copy
	 * @return the copy of the given image
	 */
	public static BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}

	/**
	 * Determines and returns the average color of the given image.
	 *
	 * @param image the image
	 * @return the average value of all reds, greens and blues of
	 * the given image
	 */
	public static Color getAverageColor(BufferedImage image) {
		long reds = 0;
		long greens = 0;
		long blues = 0;
		long count = 0;

		int[] pixel = new int[3];
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				pixel = image.getRaster().getPixel(x, y, pixel);

				reds += pixel[0];
				greens += pixel[1];
				blues += pixel[2];
				count++;
			}
		}
		return new Color((int) (reds / count), (int) (greens / count), (int) (blues / count));
	}

	/**
	 * Creates a new {@link BufferedImage} filled with the specidfied color
	 * and of the given dimensions.
	 *
	 * @param c      the color of the image
	 * @param width  the width of the image
	 * @param height the height of the image
	 * @return a colorized image of the given dimensions
	 */
	public static BufferedImage createImageFromColor(Color c, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setColor(c);
		g2d.fillRect(0, 0, width, height);
		return image;
	}

	/**
	 * Converts the given color, represented by the RBG components
	 * to an integer representation.
	 *
	 * @param r the red component
	 * @param g the green component
	 * @param b the blue component
	 * @return an integer representation of the given color
	 */
	public static int convertRGBToInt(int r, int g, int b) {
		return 0xFFFF * r + 0xFF * g + b;
	}
}
