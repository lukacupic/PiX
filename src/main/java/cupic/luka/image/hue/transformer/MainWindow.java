package cupic.luka.image.hue.transformer;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.image.RGBImageFilter;
import java.io.IOException;

public class MainWindow extends JFrame {

	private String imagePath = "src/main/resources/bird.jpg";

	public MainWindow() {
		setSize(600, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);

		initGUI();
	}

	private void initGUI() {
		setLayout(new BorderLayout());

		ImagePanel imagePanel;
		try {
			imagePanel = new ImagePanel(imagePath);
		} catch (IOException e) {
			throw new RuntimeException();
		}

		JSlider slider = new JSlider(0, 50, 0);
		slider.addChangeListener(e -> {
			JSlider source = (JSlider) e.getSource();
			float hue = 1.0f / source.getMaximum() * source.getValue();

			changeHue(imagePanel.getImage(), hue);
			imagePanel.repaint();
		});

		add(imagePanel, BorderLayout.CENTER);
		add(slider, BorderLayout.SOUTH);
	}

	public void changeHue(BufferedImage image, float hue) {
		int width = image.getWidth();
		int height = image.getHeight();

		RGBImageFilter filter = new HueImageFilter(hue);

		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				int rgb = filter.filterRGB(xx, yy, image.getRGB(xx, yy));
				image.setRGB(xx, yy, rgb);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(MainWindow::new);
	}
}