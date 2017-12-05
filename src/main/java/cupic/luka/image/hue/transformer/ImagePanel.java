package cupic.luka.image.hue.transformer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {

	private BufferedImage originalImage;

	public ImagePanel(String filename) throws IOException {
		try {
			originalImage = ImageIO.read(new File(filename));
		} catch (IOException ex) {
			throw new IOException();
		}
	}

	public BufferedImage getImage() {
		return originalImage;
	}

	public void setImage(BufferedImage image) {
		this.originalImage = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x = (this.getWidth() - originalImage.getWidth()) / 2;
		int y = (this.getHeight() - originalImage.getHeight()) / 2;
		g.drawImage(originalImage, x, y, null);
	}
}
