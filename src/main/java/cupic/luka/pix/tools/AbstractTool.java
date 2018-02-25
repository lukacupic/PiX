package cupic.luka.pix.tools;

import cupic.luka.pix.image.ImagePanel;

import javax.swing.JPanel;

/**
 * An abstract image processing tool from which all other tools
 * inherited.
 *
 * @author Luka Cupic
 */
public class AbstractTool extends JPanel {

	/**
	 * The panel holding the image.
	 */
	protected ImagePanel imagePanel;

	/**
	 * Creates a new image processing tool.
	 *
	 * @param imagePanel the panel holding the image
	 */
	public AbstractTool(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}
}
