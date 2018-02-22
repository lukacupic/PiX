package cupic.luka.hsb.filters;

import java.awt.Color;

/**
 * The filter for modifying the hue of an image.
 *
 * @author Luka Cupic
 */
public class HueFilter extends AbstractFilter {

	/**
	 * The default constructor.
	 */
	public HueFilter() {
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		Color c = new Color(rgb);
		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		return Color.HSBtoRGB(hsb[0] + offset, hsb[1], hsb[2]);
	}
}
