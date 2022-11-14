/**
 * 
 */
package formula;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.data.DriverLapDistance;
import formula.packets.LapData;
import formula.packets.PacketLapData;
import formula.packets.PacketSession;
import formula.trackpos.F1TrackPositionPanel;

/**
 * @author reinh
 *
 */
public class F1Frame {

	private static final int FRAME_WIDTH = 1300;
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

	private static JPanel infoPanel = new JPanel();
	private static F1TrackPositionPanel trackPosPanel = new F1TrackPositionPanel();

	public F1Frame() {

		changeSelf.setEnabled(false);

		frame.setUndecorated(true);
		frame.setBackground(Color.BLACK);

		frame.setAlwaysOnTop(true);
		frame.setLayout(new GridLayout(1, 1));
		infoPanel.setLayout(new GridBagLayout());
		GridBagConstraints grid = new GridBagConstraints();

		infoPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 50));
		infoPanel.setSize(new Dimension(FRAME_WIDTH, 50));

		grid.fill = GridBagConstraints.CENTER;
		grid.weightx = 1.0;
		grid.gridy = 0;
		frame.setSize(FRAME_WIDTH, 50);
		addCarFrontComponents(grid);
		addCarSelfComponents(grid);
		addCarBehindComponents(grid);

		infoPanel.add(close);
		frame.add(infoPanel);
		grid.gridy = 1;

		trackPosPanel.setPreferredSize(new Dimension(FRAME_WIDTH, 50));
		trackPosPanel.setSize(new Dimension(FRAME_WIDTH, 50));
//		frame.add(trackPosPanel);

		// Put frame in center top of the screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		frame.setLocation(screenSize.width / 2 - frame.getSize().width / 2, 0);

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
		infoPanel.add(vehicleFrontLabel, grid);
		infoPanel.add(warningsFrontLabel, grid);
		infoPanel.add(vehicleWarningFront, grid);
		infoPanel.add(tyreFrontLabel, grid);
		infoPanel.add(vehicleTyreFront, grid);
		infoPanel.add(ersFrontLabel, grid);
		infoPanel.add(vehicleERSFront, grid);
		infoPanel.add(spacer1Label, grid);

		vehicleTyreFront.setOpaque(true);
		vehicleWarningFront.setOpaque(true);
		vehicleERSFront.setOpaque(true);
	}

	private void addCarSelfComponents(GridBagConstraints grid) {
		infoPanel.add(vehicleSelfLabel, grid);
		infoPanel.add(warningsSelfLabel, grid);
		infoPanel.add(vehicleWarningSelf, grid);
		infoPanel.add(tyreSelfLabel, grid);
		infoPanel.add(vehicleTyreSelf, grid);
		infoPanel.add(ersSelfLabel, grid);
		infoPanel.add(vehicleERSSelf, grid);
		infoPanel.add(changeSelf, grid);
		infoPanel.add(spacer2Label, grid);

		vehicleTyreSelf.setOpaque(true);
		vehicleWarningSelf.setOpaque(true);
		vehicleERSSelf.setOpaque(true);
	}

	private void addCarBehindComponents(GridBagConstraints grid) {
		infoPanel.add(vehicleBehindLabel, grid);
		infoPanel.add(warningsBehindLabel, grid);
		infoPanel.add(vehicleWarningBehind, grid);
		infoPanel.add(tyreBehindLabel, grid);
		infoPanel.add(vehicleTyreBehind, grid);
		infoPanel.add(ersBehindLabel, grid);
		infoPanel.add(vehicleERSBehind, grid);

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

	public static void drawTrackPosition(PacketLapData argPacketLapData) {

		if (F1DataHelper.getParticipants() != null) {
			List<DriverLapDistance> dldList = new ArrayList<>();

			short i = 0;
			for (LapData ld : argPacketLapData.getLapDataList()) {
				if (ld.getResultStatus() != 0 && ld.getResultStatus() != 7) {
					DriverLapDistance dld = new DriverLapDistance();
					float lapprogress = ld.getLapDistance() / F1DataHelper.getTrackLength();

					dld.setVehicleIdx(i);

					dld.setRaceNumber(F1DataHelper.getParticipants().getParticipantDataList().get(i).getRaceNumber());
					dld.setTeamColor(F1DataHelper.getParticipants().getParticipantDataList().get(i).getTeamId());
					dld.setLapDistance(lapprogress * FRAME_WIDTH + FRAME_WIDTH / 4f);
					dldList.add(dld);
				}
				i++;
			}

			trackPosPanel.setDriverLapDistance(dldList);
			trackPosPanel.repaint();
		}
	}

	public static void drawSafetyCarStatus(PacketSession argPacketSession) {

		trackPosPanel.setSafetyCarType(argPacketSession.getSafetyCarStatus());
	}

}
