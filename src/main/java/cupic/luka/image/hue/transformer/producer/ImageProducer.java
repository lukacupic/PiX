package cupic.luka.image.hue.transformer.producer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageProducer {

	private int count = 0;

	private String path;

	private String format;

	public ImageProducer(String path, String format) {
		this.path = path;
		this.format = format;
	}

	public void produce(BufferedImage image) throws IOException {
		Path realPath = Paths.get(path, count++ + "." + format);
		ImageIO.write(image, "png", realPath.toFile());
	}

	public int getCount() {
		return count;
	}
}
