package cupic.luka.image.hsb.transformer;

import cupic.luka.image.hsb.transformer.filters.BrightnessFilter;
import cupic.luka.image.hsb.transformer.filters.HueFilter;
import cupic.luka.image.hsb.transformer.filters.SaturationFilter;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.io.IOException;

public class MainWindow extends JFrame {

	private HueFilter hueFilter = new HueFilter();
	private SaturationFilter saturationFilter = new SaturationFilter();
	private BrightnessFilter brightnessFilter = new BrightnessFilter();

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

		JSlider hueSlider = new JSlider(0, 50, 0);
		hueSlider.addChangeListener(e -> {
			JSlider source = (JSlider) e.getSource();
			float offset = 1.0f / source.getMaximum() * source.getValue();
			hueFilter.setOffset(offset);

			hueFilter.apply(imagePanel.getImage(), imagePanel.getOriginal());
			imagePanel.repaint();
		});

		JSlider saturationSlider = new JSlider(0, 50, 0);
		saturationSlider.addChangeListener(e -> {
			JSlider source = (JSlider) e.getSource();
			float offset = 1.0f / source.getMaximum() * source.getValue();
			saturationFilter.setOffset(offset);

			saturationFilter.apply(imagePanel.getImage(), imagePanel.getOriginal());
			imagePanel.repaint();
		});

		JSlider brightnessSlider = new JSlider(0, 50, 0);
		brightnessSlider.addChangeListener(e -> {
			JSlider source = (JSlider) e.getSource();
			float offset = 1.0f / source.getMaximum() * source.getValue();
			brightnessFilter.setOffset(offset);

			brightnessFilter.apply(imagePanel.getImage(), imagePanel.getOriginal());
			imagePanel.repaint();
		});

		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
		settingsPanel.add(hueSlider);
		settingsPanel.add(saturationSlider);
		settingsPanel.add(brightnessSlider);

		add(imagePanel, BorderLayout.CENTER);
		add(settingsPanel, BorderLayout.EAST);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(MainWindow::new);
	}
}