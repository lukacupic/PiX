package cupic.luka.image.hsb.transformer.filters;

import cupic.luka.image.hsb.transformer.filters.AbstractFilter;

import java.awt.Color;

public class BrightnessFilter extends AbstractFilter {

	public BrightnessFilter() {
	}

	public BrightnessFilter(float offset) {
		this.offset = offset;
	}

	@Override
	public int filterRGB(int x, int y, int rgb) {
		Color c = new Color(rgb);
		float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
		return Color.HSBtoRGB(hsb[0], hsb[1], hsb[2] + offset);
	}
}
