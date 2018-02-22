package cupic.luka.image.hsb.transformer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

	private BufferedImage image;

	private BufferedImage original;

	public ImagePanel(String filename) throws IOException {
		try {
			image = ImageIO.read(new File(filename));
			original = ImageUtil.copyImage(image);
		} catch (IOException ex) {
			throw new IOException();
		}
	}

	public BufferedImage getImage() {
		return image;
	}

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