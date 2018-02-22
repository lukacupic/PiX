package cupic.luka.image.hsb.transformer.filters;

import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;

public abstract class AbstractFilter extends RGBImageFilter {

	protected float offset;

	public void apply(BufferedImage image, BufferedImage original) {
		int width = image.getWidth();
		int height = image.getHeight();

		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				int rgb = filterRGB(xx, yy, original.getRGB(xx, yy));
				image.setRGB(xx, yy, rgb);
			}
		}
	}

	public float getOffset() {
		return offset;
	}

	public void setOffset(float offset) {
		this.offset = offset;
	}
}
