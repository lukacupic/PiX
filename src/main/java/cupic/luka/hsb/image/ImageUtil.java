package cupic.luka.hsb.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

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

	public static Color getAverageColor(BufferedImage image) {
		long redBucket = 0;
		long greenBucket = 0;
		long blueBucket = 0;
		long pixelCount = 0;

		int[] pixel = new int[3];
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				pixel = image.getRaster().getPixel(x, y, pixel);

				redBucket += pixel[0];
				greenBucket += pixel[1];
				blueBucket += pixel[2];
				pixelCount++;
			}
		}

		return new Color(
				(int) (redBucket / pixelCount),
				(int) (greenBucket / pixelCount),
				(int) (blueBucket / pixelCount)
		);

	}

	public static BufferedImage createImageFromColor(Color c, int width, int height) {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		g2d.setColor(c);
		g2d.fillRect(0, 0, width, height);
		return image;
	}
}
