package cupic.luka.pix.filters;

import java.awt.Color;

/**
 * The filter for modifying the saturation of an image.
 *
 * @author Luka Cupic
 */
public class SaturationFilter extends AbstractFilter {

	/**
	 * The default constructor.
	 */
	public SaturationFilter() {
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		Color c = new Color(rgb);
		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);

		float saturation = hsb[1] + offset;
		if (saturation < 0) saturation = 0;
		else if (saturation > 1) saturation = 1;

		return Color.HSBtoRGB(hsb[0], saturation, hsb[2]);
	}
}
