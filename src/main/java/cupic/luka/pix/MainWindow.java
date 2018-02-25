package cupic.luka.pix;

import cupic.luka.pix.image.ImagePanel;
import cupic.luka.pix.image.ImageProducer;
import cupic.luka.pix.tools.ColorAverager;
import cupic.luka.pix.tools.ColorInverter;
import cupic.luka.pix.tools.hsb.HSBChanger;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
	 * The image-processing tools.
	 */
	private HSBChanger hsbChanger;
	private ColorAverager colorAverager;
	private ColorInverter colorInverter;

	/**
	 * The application's default constructor.
	 * Creates and initializes the main window and it's GUI.
	 */
	private MainWindow() {
		setSize(600, 600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("PiX v0.1");

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
		tools.add(createColorAveragerItem());
		tools.add(createColorInverterItem());
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
					createTools();
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

	private void createTools() {
		hsbChanger = new HSBChanger(imagePanel);
		colorAverager = new ColorAverager(imagePanel);
		colorInverter = new ColorInverter(imagePanel);
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
		JMenuItem hsb = new JMenuItem("HSB Changer");
		hsb.addActionListener(e -> JOptionPane.showMessageDialog(
				null,
				hsbChanger,
				"HSB Changer",
				JOptionPane.PLAIN_MESSAGE
		));
		return hsb;
	}

	/**
	 * Creates the menu item for opening the Color Averager.
	 *
	 * @return the menu item which opens the Color Averager.
	 */
	private JMenuItem createColorAveragerItem() {
		JMenuItem hsb = new JMenuItem("Color Averager");
		hsb.addActionListener(e -> JOptionPane.showMessageDialog(
				null,
				colorAverager,
				"Color Averager",
				JOptionPane.PLAIN_MESSAGE
		));
		return hsb;
	}

	/**
	 * Creates the menu item for opening the Color Inverter.
	 *
	 * @return the menu item which opens the Color Inverter.
	 */
	private JMenuItem createColorInverterItem() {
		JMenuItem hsb = new JMenuItem("Color Inverter");
		hsb.addActionListener(e -> colorInverter.invert());
		return hsb;
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