package cupic.luka.image.hsb.transformer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class used for saving {@link BufferedImage}s to the
 * default persistent memory of the computer.
 *
 * @author Luka Cupic
 */
public class ImageProducer {

	/**
	 * The export format (extension) for the images.
	 */
	private String format;

	/**
	 * Creates a new ImageProducer.
	 *
	 * @param format the format for the images
	 */
	public ImageProducer(String format) {
		this.format = format;
	}

	/**
	 * Produces the given {@link BufferedImage} onto the given
	 * location.
	 *
	 * @param image the image to produce
	 * @param path  the location of the folder in which to store
	 *              the image
	 * @throws IOException if the image cannot be saved
	 */
	public void save(BufferedImage image, String path) throws IOException {
		Path realPath = Paths.get(path);
		ImageIO.write(image, format, realPath.toFile());
	}
}
