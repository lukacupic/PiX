package cupic.luka.image.hsb.transformer.filters;

import java.awt.image.*;

/**
 * An abstract image-manipulation filter.
 *
 * @author Luka Cupic
 */
public abstract class AbstractFilter {

	/**
	 * Represents the "offset" of a filter. This value represents
	 * the deviation of the value of the pixel upon the filter is
	 * currently operating.
	 */
	protected float offset;

	/**
	 * Applies this filter on the provided image. The original version
	 * of the image is also provided, for better performance.
	 *
	 * @param image    the image to filter
	 * @param original the original image
	 */
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

	/**
	 * Gets the offset
	 *
	 * @return the offset
	 */
	public float getOffset() {
		return offset;
	}

	/**
	 * Sets the offset.
	 *
	 * @param offset the offset
	 */
	public void setOffset(float offset) {
		this.offset = offset;
	}

	/**
	 * Converts a single input pixel to a single output pixel.
	 * Subclasses must provide a valid method for performing this
	 * conversion.
	 *
	 * @param x   the X coordinate of the pixel
	 * @param y   the Y coordinate of the pixel
	 * @param rgb the integer pixel representation in the default RGB
	 *            color model
	 * @return a filtered pixel in the default RGB color model
	 */
	public abstract int filterRGB(int x, int y, int rgb);
}
