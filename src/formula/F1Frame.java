/**
 * 
 */
package formula;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author reinh
 *
 */
public class F1Frame {

	private static final String TYRES = "Tyres ";
	private static final String WARN = "Warn ";
	private static final String TYRE_LABEL_INIT_VALUE = "100,00 100,00 100,00 100,00";

	private JFrame frame = new JFrame("F1 warnings");
	private static JLabel warningsFrontLabel = new JLabel(WARN);
	private static JLabel warningsSelfLabel = new JLabel(WARN);
	private static JLabel warningsBehindLabel = new JLabel(WARN);

	private static JLabel vehicleFrontLabel = new JLabel("Front:");
	private static JLabel tyreFrontLabel = new JLabel(TYRES);
	private static JLabel spacer1Label = new JLabel("           |           ");

	private static JLabel vehicleSelfLabel = new JLabel("Self:");
	private static JLabel tyreSelfLabel = new JLabel(TYRES);
	private static JLabel spacer2Label = new JLabel("           |           ");

	private static JLabel vehicleBehindLabel = new JLabel("Behind:");
	private static JLabel tyreBehindLabel = new JLabel(TYRES);

	private static JLabel vehicleTyreFront = new JLabel(TYRE_LABEL_INIT_VALUE);
	private static JLabel vehicleTyreSelf = new JLabel(TYRE_LABEL_INIT_VALUE);
	private static JLabel vehicleTyreBehind = new JLabel(TYRE_LABEL_INIT_VALUE);

	private static JLabel vehicleWarningFront = new JLabel("warnfront");
	private static JLabel vehicleWarningSelf = new JLabel("warnself");
	private static JLabel vehicleWarningBehind = new JLabel("warnbehind");

	private static JButton changeSelf = new JButton("..");
	private static JButton close = new JButton("x");

	public F1Frame() {
		changeSelf.setEnabled(false);

		frame.setUndecorated(true);

		frame.setAlwaysOnTop(true);
		frame.setLayout(new FlowLayout());
		frame.setSize(1300, 75);
		frame.add(vehicleFrontLabel);
		frame.add(warningsFrontLabel);
		frame.add(vehicleWarningFront);
		frame.add(tyreFrontLabel);
		frame.add(vehicleTyreFront);
		frame.add(spacer1Label);
		frame.add(vehicleSelfLabel);
		frame.add(warningsSelfLabel);
		frame.add(vehicleWarningSelf);
		frame.add(tyreSelfLabel);
		frame.add(vehicleTyreSelf);
		frame.add(changeSelf);
		frame.add(spacer2Label);
		frame.add(vehicleBehindLabel);
		frame.add(warningsBehindLabel);
		frame.add(vehicleWarningBehind);
		frame.add(tyreBehindLabel);
		frame.add(vehicleTyreBehind);

		vehicleWarningFront.setOpaque(true);
		vehicleWarningSelf.setOpaque(true);
		vehicleWarningBehind.setOpaque(true);

		frame.add(close);

		frame.setVisible(true);

		changeSelf.addActionListener(e -> {

			String[] choices = new String[F1DataHelper.getParticipants().getParticipantDataList().size()];
			for (int i = 0; i < choices.length; i++) {
				choices[i] = F1DataHelper.getParticipants().getParticipantDataList().get(i).getNameAsString();
			}

			String input = "";
			if (F1DataHelper.getMyVehicleIdx() < 0) {
				input = (String) JOptionPane.showInputDialog(null, "Select driver:", "Driver select",
						JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			} else {
				input = (String) JOptionPane.showInputDialog(null, "Select driver:", "Driver select",
						JOptionPane.QUESTION_MESSAGE, null, choices, choices[F1DataHelper.getMyVehicleIdx()]);
			}

			System.out.println(input);

			int i = 0;
			for (; i < choices.length; i++) {
				if (input.equals(choices[i])) {
					break;
				}
			}

			F1DataHelper.setMyVehicleIdx((short) i);
			System.out.println(F1DataHelper.getMyVehicleIdx());

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

				F1UdpListener.setRunning(false);

				System.exit(0);
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

}
