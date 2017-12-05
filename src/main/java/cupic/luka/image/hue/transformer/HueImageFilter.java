package cupic.luka.image.hue.transformer;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class HueImageFilter extends RGBImageFilter {

	private float hue;

	public HueImageFilter(float hue) {
		this.hue = hue;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		Color c = new Color(rgb);
		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		return Color.HSBtoRGB(hue, hsb[1], hsb[2]);
	}
}
