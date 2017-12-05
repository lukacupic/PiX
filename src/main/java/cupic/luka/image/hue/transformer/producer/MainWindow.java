package cupic.luka.image.hue.transformer.producer;

import cupic.luka.image.hue.transformer.HueImageFilter;
import cupic.luka.image.hue.transformer.ImagePanel;

import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
import java.io.IOException;

public class MainWindow {

	private static ImageProducer imageProducer = new ImageProducer("images", "png");

	private static String imagePath = "src/main/resources/jedi.jpg";


	public static void changeHue(BufferedImage image, float hue) {
		int width = image.getWidth();
		int height = image.getHeight();

		RGBImageFilter filter = new HueImageFilter(hue);

		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				int rgb = filter.filterRGB(xx, yy, image.getRGB(xx, yy));
				image.setRGB(xx, yy, rgb);
			}
		}
	}

	public static void main(String[] args) {
		int noOfImages = Integer.parseInt(args[0]);

		ImagePanel imagePanel;
		try {
			imagePanel = new ImagePanel(imagePath);
		} catch (IOException e) {
			throw new RuntimeException();
		}

		for (int i = 0; i < noOfImages; i++) {
			float hue = 1.0f / noOfImages * i;

			changeHue(imagePanel.getImage(), hue);
			imagePanel.repaint();

			try {
				System.out.println("Producing image: " + imageProducer.getCount());
				imageProducer.produce(imagePanel.getImage());
			} catch (IOException ignorable) {
			}
		}
	}
}