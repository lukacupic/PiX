package cupic.luka.image.hsb.transformer;

import java.awt.Graphics;
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
}
