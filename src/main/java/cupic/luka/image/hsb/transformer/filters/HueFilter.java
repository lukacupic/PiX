package cupic.luka.image.hsb.transformer.filters;

import java.awt.Color;

public class HueFilter extends AbstractFilter {

	public HueFilter() {
	}

	public HueFilter(float offset) {
		this.offset = offset;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		Color c = new Color(rgb);
		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		return Color.HSBtoRGB(hsb[0] + offset, hsb[1], hsb[2]);
	}
}
