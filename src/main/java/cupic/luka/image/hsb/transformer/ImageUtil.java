package cupic.luka.image.hsb.transformer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageUtil {

	public static BufferedImage copyImage(BufferedImage source) {
		BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		Graphics g = b.getGraphics();
		g.drawImage(source, 0, 0, null);
		g.dispose();
		return b;
	}
}
