package cupic.luka.hsb.filters;

import java.awt.*;

/**
 * The filter for modifying the brightness of an image.
 *
 * @author Luka Cupic
 */
public class BrightnessFilter extends AbstractFilter {

	/**
	 * The default constructor.
	 */
	public BrightnessFilter() {
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		Color c = new Color(rgb);
		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);

		float brightness = hsb[2] + offset;
		if (brightness < 0) brightness = 0;
		else if (brightness > 1) brightness = 1;

		return Color.HSBtoRGB(hsb[0], hsb[1], brightness);
	}
}
