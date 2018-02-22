package cupic.luka.image.hsb.transformer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageProducer {

	private String format;

	/**
	 * Creates a new {@link ImageProducer}.
	 *
	 * @param format
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
	 * @throws IOException if an I/O error occurs while producing
	 *                     the image
	 */
	public void save(BufferedImage image, String path) throws IOException {
		Path realPath = Paths.get(path);
		ImageIO.write(image, format, realPath.toFile());
	}
}
