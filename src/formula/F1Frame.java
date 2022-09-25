/**
 * 
 */
package formula;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author reinh
 *
 */
public class F1Frame {

	private static final String TYRES = "Tyres ";
	private static final String WARN = "Warn ";
	private static final String ERS = "ERS ";
	private static final String TYRE_LABEL_INIT_VALUE = "100,0 100,0 100,0 100,0";
	private static final String ERS_LABEL_INIT_VALUE = "100,0";

	private static final Logger log = LogManager.getLogger(F1Frame.class);

	private JFrame frame = new JFrame("F1 warnings");
	private static JLabel warningsFrontLabel = new JLabel(WARN);
	private static JLabel warningsSelfLabel = new JLabel(WARN);
	private static JLabel warningsBehindLabel = new JLabel(WARN);

	private static JLabel ersFrontLabel = new JLabel(ERS);
	private static JLabel ersSelfLabel = new JLabel(ERS);
	private static JLabel ersBehindLabel = new JLabel(ERS);

	private static JLabel vehicleFrontLabel = new JLabel("Front: ");
	private static JLabel tyreFrontLabel = new JLabel(TYRES);
	private static JLabel spacer1Label = new JLabel("           |           ");

	private static JLabel vehicleSelfLabel = new JLabel("Self: ");
	private static JLabel tyreSelfLabel = new JLabel(TYRES);
	private static JLabel spacer2Label = new JLabel("           |           ");

	private static JLabel vehicleBehindLabel = new JLabel("Behind: ");
	private static JLabel tyreBehindLabel = new JLabel(TYRES);

	private static JLabel vehicleTyreFront = new JLabel(TYRE_LABEL_INIT_VALUE);
	private static JLabel vehicleTyreSelf = new JLabel(TYRE_LABEL_INIT_VALUE);
	private static JLabel vehicleTyreBehind = new JLabel(TYRE_LABEL_INIT_VALUE);

	private static JLabel vehicleERSFront = new JLabel(ERS_LABEL_INIT_VALUE);
	private static JLabel vehicleERSSelf = new JLabel(ERS_LABEL_INIT_VALUE);
	private static JLabel vehicleERSBehind = new JLabel(ERS_LABEL_INIT_VALUE);

	private static JLabel vehicleWarningFront = new JLabel("warnfront");
	private static JLabel vehicleWarningSelf = new JLabel("warnself");
	private static JLabel vehicleWarningBehind = new JLabel("warnbehind");

	private static JButton changeSelf = new JButton("..");
	private static JButton close = new JButton("x");

	public F1Frame() {
		changeSelf.setEnabled(false);

		frame.setUndecorated(true);
		frame.setBackground(Color.BLACK);

		frame.setAlwaysOnTop(true);
		frame.setLayout(new GridBagLayout());

		GridBagConstraints grid = new GridBagConstraints();

		grid.fill = GridBagConstraints.CENTER;
		grid.weightx = 1.0;
		frame.setSize(1300, 50);
		addCarFrontComponents(grid);
		addCarSelfComponents(grid);
		addCarBehindComponents(grid);

		frame.add(close);

		frame.setVisible(true);

		changeSelf.addActionListener(e -> {

			String[] choices = new String[F1DataHelper.getParticipants().getParticipantDataList().size()];
			for (int i = 0; i < choices.length; i++) {
				choices[i] = F1DataHelper.getParticipants().getParticipantDataList().get(i).getNameAsString();
			}

			String input = (String) JOptionPane.showInputDialog(null, "Select driver:", "Driver select",
					JOptionPane.QUESTION_MESSAGE, null, choices,
					choices[F1DataHelper.getMyVehicleIdx() < 0 ? 0 : F1DataHelper.getMyVehicleIdx()]);

			log.debug(input);

			int i = 0;
			for (; i < choices.length; i++) {
				if (input.equals(choices[i])) {
					break;
				}
			}

			F1DataHelper.setMyVehicleIdx((short) i);
			log.debug(F1DataHelper.getMyVehicleIdx());

			F1DataHelper.setUseSelf(false);

		});

		close.addActionListener(e -> {
			WindowEvent windowClosing = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
			frame.dispatchEvent(windowClosing);
		});

		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent argE) {
				// egal
			}

			@Override
			public void windowIconified(WindowEvent argE) {
				// egal
			}

			@Override
			public void windowDeiconified(WindowEvent argE) {
				// egal
			}

			@Override
			public void windowDeactivated(WindowEvent argE) {
				// egal
			}

			@Override
			public void windowClosing(WindowEvent argE) {
				try {
					if (F1DataHelper.getOos() != null) {
						F1DataHelper.getOos().flush();
						F1DataHelper.getOos().close();
					}
					if (F1DataHelper.getFos() != null) {
						F1DataHelper.getFos().flush();
						F1DataHelper.getFos().close();

					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				F1Parser.getF1UdpListener().setRunning(false);
			}

			@Override
			public void windowClosed(WindowEvent argE) {
				// egal
			}

			@Override
			public void windowActivated(WindowEvent argE) {
				// egal
			}
		});
	}

	private void addCarFrontComponents(GridBagConstraints grid) {
		frame.add(vehicleFrontLabel, grid);
		frame.add(warningsFrontLabel, grid);
		frame.add(vehicleWarningFront, grid);
		frame.add(tyreFrontLabel, grid);
		frame.add(vehicleTyreFront, grid);
		frame.add(ersFrontLabel, grid);
		frame.add(vehicleERSFront, grid);
		frame.add(spacer1Label, grid);

		vehicleTyreFront.setOpaque(true);
		vehicleWarningFront.setOpaque(true);
		vehicleERSFront.setOpaque(true);
	}

	private void addCarSelfComponents(GridBagConstraints grid) {
		frame.add(vehicleSelfLabel, grid);
		frame.add(warningsSelfLabel, grid);
		frame.add(vehicleWarningSelf, grid);
		frame.add(tyreSelfLabel, grid);
		frame.add(vehicleTyreSelf, grid);
		frame.add(ersSelfLabel, grid);
		frame.add(vehicleERSSelf, grid);
		frame.add(changeSelf, grid);
		frame.add(spacer2Label, grid);

		vehicleTyreSelf.setOpaque(true);
		vehicleWarningSelf.setOpaque(true);
		vehicleERSSelf.setOpaque(true);
	}

	private void addCarBehindComponents(GridBagConstraints grid) {
		frame.add(vehicleBehindLabel, grid);
		frame.add(warningsBehindLabel, grid);
		frame.add(vehicleWarningBehind, grid);
		frame.add(tyreBehindLabel, grid);
		frame.add(vehicleTyreBehind, grid);
		frame.add(ersBehindLabel, grid);
		frame.add(vehicleERSBehind, grid);

		vehicleTyreBehind.setOpaque(true);
		vehicleWarningBehind.setOpaque(true);
		vehicleERSBehind.setOpaque(true);
	}

	public static void setChangeSelfEnabled(boolean argEnabled) {
		changeSelf.setEnabled(argEnabled);
	}

	public static void setTyreFrontText(String argText) {
		vehicleTyreFront.setText(argText);
	}

	public static void setTyreSelfText(String argText) {
		vehicleTyreSelf.setText(argText);
	}

	public static void setTyreBehindText(String argText) {
		vehicleTyreBehind.setText(argText);
	}

	public static void setTyreFrontColor(Color argColor) {
		vehicleTyreFront.setBackground(argColor);
	}

	public static void setTyreSelfColor(Color argColor) {
		vehicleTyreSelf.setBackground(argColor);
	}

	public static void setTyreBehindColor(Color argColor) {
		vehicleTyreBehind.setBackground(argColor);
	}

	public static void setWarningFrontText(String argText) {
		vehicleWarningFront.setText(argText);
	}

	public static void setWarningFrontColor(Color argColor) {
		vehicleWarningFront.setBackground(argColor);
	}

	public static void setWarningSelfText(String argText) {
		vehicleWarningSelf.setText(argText);
	}

	public static void setWarningSelfColor(Color argColor) {
		vehicleWarningSelf.setBackground(argColor);
	}

	public static void setWarningBehindText(String argText) {
		vehicleWarningBehind.setText(argText);
	}

	public static void setWarningBehindColor(Color argColor) {
		vehicleWarningBehind.setBackground(argColor);
	}

	public static void setERSFront(String argText, Color argColor) {
		vehicleERSFront.setText(argText);
		vehicleERSFront.setBackground(argColor);
	}

	public static void setERSSelf(String argText, Color argColor) {
		vehicleERSSelf.setText(argText);
		vehicleERSSelf.setBackground(argColor);
	}

	public static void setERSBehind(String argText, Color argColor) {
		vehicleERSBehind.setText(argText);
		vehicleERSBehind.setBackground(argColor);
	}

}
