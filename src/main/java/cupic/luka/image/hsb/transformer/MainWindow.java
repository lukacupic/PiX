package cupic.luka.image.hsb.transformer;

import cupic.luka.image.hsb.transformer.filters.AbstractFilter;
import cupic.luka.image.hsb.transformer.filters.BrightnessFilter;
import cupic.luka.image.hsb.transformer.filters.HueFilter;
import cupic.luka.image.hsb.transformer.filters.SaturationFilter;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * The main window of the application.
 *
 * @author Luka Cupic
 */
public class MainWindow extends JFrame {

	/**
	 * The panel holding the image.
	 */
	private ImagePanel imagePanel;

	/**
	 * Hue filter.
	 */
	private HueFilter hueFilter = new HueFilter();

	/**
	 * Saturation filter.
	 */
	private SaturationFilter saturationFilter = new SaturationFilter();

	/**
	 * Brightness filter.
	 */
	private BrightnessFilter brightnessFilter = new BrightnessFilter();

	/**
	 * The application's default constructor.
	 * Creates and initializes the main window and it's GUI.
	 */
	public MainWindow() {
		setSize(600, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		initGUI();
	}

	/**
	 * Initializes the GUI for this application.
	 */
	private void initGUI() {
		setLayout(new BorderLayout());
		setJMenuBar(creteMenuBar());
	}

	/**
	 * Creates the menu bar of this application.
	 *
	 * @return the menu bar
	 */
	private JMenuBar creteMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("File");
		menu.add(createOpenItem());
		menu.add(createSaveItem());
		menu.add(createExitItem());
		menuBar.add(menu);

		JMenuItem tools = new JMenu("Tools");
		tools.add(createHSBItem());
		menuBar.add(tools);

		return menuBar;
	}

	/**
	 * Creates the menu item for handling opening images.
	 *
	 * @return the menu item that handles opening images
	 */
	private JMenuItem createOpenItem() {
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(e -> {
			JFileChooser fc = new JFileChooser();

			FileFilter imageFilter = new FileNameExtensionFilter("Image files",
					ImageIO.getReaderFileSuffixes());
			fc.addChoosableFileFilter(imageFilter);

			if (fc.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				try {
					imagePanel = new ImagePanel(file);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(
							MainWindow.this,
							"Could not open file.",
							"Error!",
							JOptionPane.ERROR_MESSAGE
					);
				}
				add(imagePanel, BorderLayout.CENTER);
				imagePanel.revalidate();
				imagePanel.repaint();
			}
		});
		return open;
	}

	/**
	 * Creates the menu item for handling saving images.
	 *
	 * @return the menu item that handles saving images
	 */
	private JMenuItem createSaveItem() {
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(e -> {
			String fileExt = "png";
			JFileChooser fc = new JFileChooser(fileExt);
			fc.setSelectedFile(new File("image." + fileExt));

			if (fc.showSaveDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				ImageProducer producer = new ImageProducer(fileExt);
				try {
					producer.save(imagePanel.getImage(), file.getPath());
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(
							MainWindow.this,
							"Could not save file.",
							"Error!",
							JOptionPane.ERROR_MESSAGE
					);
				}
			}
		});
		return save;
	}

	/**
	 * Creates the menu item for handling application exiting.
	 *
	 * @return the menu item that closes the application
	 */
	private JMenuItem createExitItem() {
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(e -> System.exit(0));
		return exit;
	}

	/**
	 * Creates the menu item for opening the Hue, Saturation
	 * and Brightness editing dialog.
	 *
	 * @return the menu item which opens the HSB editing dialog
	 */
	private JMenuItem createHSBItem() {
		JMenuItem hsb = new JMenuItem("Change HSB");
		hsb.addActionListener(e -> JOptionPane.showMessageDialog(
				null,
				createSettingsPanel(),
				"Change HSB",
				JOptionPane.PLAIN_MESSAGE
		));
		return hsb;
	}

	/**
	 * Creates the panel that holds Hue, Saturation and Brightness
	 * sliders.
	 *
	 * @return the settings panel with HSB sliders
	 */
	private JPanel createSettingsPanel() {
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

		JSlider hueSlider = createSlider(hueFilter);
		settingsPanel.add(new JLabel("Hue:"));
		settingsPanel.add(hueSlider);

		JSlider saturationSlider = createSlider(saturationFilter);
		settingsPanel.add(new JLabel("Saturation:"));
		settingsPanel.add(saturationSlider);

		JSlider brightnessSlider = createSlider(brightnessFilter);
		settingsPanel.add(new JLabel("Brightness:"));
		settingsPanel.add(brightnessSlider);

		return settingsPanel;
	}

	/**
	 * Creates the slider for controlling each of the HSB values.
	 *
	 * @param filter the filter which is to be applied upon changing
	 *               the slider value
	 * @return the slider representing either the Hue, Saturation or
	 * Brightness control slider, depending on the type of the provided
	 * filter
	 */
	private JSlider createSlider(AbstractFilter filter) {
		JSlider slider = new JSlider(0, 50, 25);
		slider.addChangeListener(e -> {
			JSlider source = (JSlider) e.getSource();
			float offset = 1.0f / source.getMaximum() * source.getValue();
			filter.setOffset(2 * offset - 1);
			filter.apply(imagePanel.getImage(), imagePanel.getOriginal());
			imagePanel.repaint();
		});
		return slider;
	}

	/**
	 * The main method.
	 *
	 * @param args command line arguments; not used here
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(MainWindow::new);
	}
}