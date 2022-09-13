/**
 * 
 */
package formula;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import formula.packets.PacketCarDamage;
import formula.packets.PacketCarStatus;
import formula.packets.PacketEventData;
import formula.packets.PacketFinalClassification;
import formula.packets.PacketHeader;
import formula.packets.PacketLapData;
import formula.packets.PacketLobbyInfo;
import formula.packets.PacketParticipants;
import formula.packets.PacketSession;

/**
 * @author reinh
 *
 *         uint8 = short get() uint16 = short getShort() uint32 = int getInt()
 *         uint64 = long getLong() float = float getFloat()
 * 
 */
public class F1Parser extends Thread {
	private static final String DECIMAL_FORMAT = "%.02f";
	private static final String TYRES = "Tyres ";
	private static final String WARN = "Warn ";
	private static final String TYRE_LABEL_INIT_VALUE = "100,00 100,00 100,00 100,00";
	private DatagramSocket socket;

	private byte[] buf = new byte[1000000];

	private JFrame frame = new JFrame("F1 warnings");
	private JLabel warningsFrontLabel = new JLabel(WARN);
	private JLabel warningsSelfLabel = new JLabel(WARN);
	private JLabel warningsBehindLabel = new JLabel(WARN);

	private JLabel vehicleFrontLabel = new JLabel("Front:");
	private JLabel tyreFrontLabel = new JLabel(TYRES);
	private JLabel spacer1Label = new JLabel("           |           ");

	private JLabel vehicleSelfLabel = new JLabel("Self:");
	private JLabel tyreSelfLabel = new JLabel(TYRES);
	private JLabel spacer2Label = new JLabel("           |           ");

	private JLabel vehicleBehindLabel = new JLabel("Behind:");
	private JLabel tyreBehindLabel = new JLabel(TYRES);

	private JLabel vehicleTyreFront = new JLabel(TYRE_LABEL_INIT_VALUE);
	private JLabel vehicleTyreSelf = new JLabel(TYRE_LABEL_INIT_VALUE);
	private JLabel vehicleTyreBehind = new JLabel(TYRE_LABEL_INIT_VALUE);

	private JLabel vehicleWarningFront = new JLabel("warnfront");
	private JLabel vehicleWarningSelf = new JLabel("warnself");
	private JLabel vehicleWarningBehind = new JLabel("warnbehind");

	private short frontVehicleIdx = 0;
	private short myVehicleIdx = 0;
	private short behindVehicleIdx = 0;

	boolean writeOnce = true;

	private long fileNum = 1;

	private boolean writeFile = false;

	private int[] vehicleTrackWarnings;

	private PacketParticipants participants;

	private boolean useSelf = true;
	private boolean running = true;

	FileOutputStream fos = null;
	ObjectOutputStream oos = null;

	private JButton changeSelf = new JButton("..");

	private JButton close = new JButton("x");
	private long curSessionUUID;

	public void setWriteFile(boolean argWriteFile) {
		writeFile = argWriteFile;
	}

	public short getVehicleIdxDriverInFront(short argMyPos, PacketLapData argPacketLapData) {

		if (argMyPos > 1) {
			for (short i = 0; i < 22; i++) {
				if (argPacketLapData.getLapDataList().get(i).getCarPosition() == argMyPos - 1) {
					return i;
				}
			}
		}
		return 0;
	}

	public short getVehicleIdxDriverBehind(short argMyPos, PacketLapData argPacketLapData) {
		if (argMyPos < 20) {
			for (short i = 0; i < 22; i++) {
				if (argPacketLapData.getLapDataList().get(i).getCarPosition() == argMyPos + 1) {
					return i;
				}
			}
		}
		return 20;
	}

	public F1Parser() throws SocketException, UnknownHostException {
		socket = new DatagramSocket(20777);

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

		frame.add(close);

		frame.setVisible(true);

		vehicleTrackWarnings = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		updateWarnings((short) 0, 0);
		updateWarnings((short) 0, 1);
		updateWarnings((short) 0, 2);

		changeSelf.addActionListener(e -> {

			String[] choices = new String[participants.getParticipantDataList().size()];
			for (int i = 0; i < choices.length; i++) {
				choices[i] = participants.getParticipantDataList().get(i).getNameAsString();
			}

			String input = "";
			if (myVehicleIdx < 0) {
				input = (String) JOptionPane.showInputDialog(null, "Select driver:", "Driver select",
						JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			} else {
				input = (String) JOptionPane.showInputDialog(null, "Select driver:", "Driver select",
						JOptionPane.QUESTION_MESSAGE, null, choices, choices[myVehicleIdx]);
			}

			System.out.println(input);

			int i = 0;
			for (; i < choices.length; i++) {
				if (input.equals(choices[i])) {
					break;
				}
			}

			myVehicleIdx = (short) i;
			System.out.println(myVehicleIdx);

			useSelf = false;

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
					if (oos != null) {
						oos.flush();
						oos.close();
					}
					if (fos != null) {
						fos.flush();

						fos.close();

					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				running = false;

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

	public void output() throws IOException {
		DatagramPacket rawPacket = new DatagramPacket(buf, buf.length);
		socket.receive(rawPacket);

		byte[] data = Arrays.copyOf(rawPacket.getData(), rawPacket.getLength());
		parseData(data);
	}

	public void readFile() throws IOException, ClassNotFoundException {

		File file = new File("c:/tmp/f1_" + fileNum + ".bin");
		Path p = Path.of("c:/tmp/");
		long filecount = Files.list(p).count();
		while (file.exists()) {
			FileInputStream f = new FileInputStream(file);
			ObjectInputStream o = new ObjectInputStream(f);

			Object obj;
			obj = o.readUnshared();

			if (obj instanceof PacketFinalClassification) {
				System.out.println(obj.toString());
			}
			if (fileNum % 10000l == 0 || fileNum == filecount)
				System.out.println("read: " + fileNum + "/" + filecount + " files");

			fileNum++;
			file = new File("c:/tmp/f1_" + fileNum + ".bin");

			writeSingleSerFile(obj, 0);
			o.close();
			f.close();
		}
		oos.flush();
		fos.flush();
		oos.close();
		fos.close();
		oos = null;
		fos = null;
	}

	public void writeSingleSerFile(Object argObj, long argSessionUUID) throws IOException {
		if (curSessionUUID != argSessionUUID && fos != null) {
			oos.flush();
			fos.flush();
			oos.close();
			fos.close();
			oos = null;
			fos = null;
			curSessionUUID = argSessionUUID;
		}
		if (fos == null)
			fos = new FileOutputStream("c:/tmp/" + argSessionUUID + ".ser");
		if (oos == null)
			oos = new ObjectOutputStream(fos);

		oos.writeUnshared(argObj);
		oos.flush();
	}

	public void readSingleSerFile() throws IOException, ClassNotFoundException {
		File f = new File("c:/tmp/-7963470744936800331.ser");
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);

		Object obj;

		boolean read = true;

		try (ois) {
			while (read) {
				obj = ois.readUnshared();
				if (obj != null)
					System.out.println(obj.toString());

			}
		} catch (EOFException e) {
			read = false;
			System.out.println("jetzt is aus");
		}
	}

	private void parseData(byte[] data) throws IOException {
		ByteBuffer bb = ByteBuffer.wrap(data);
		bb = bb.order(ByteOrder.LITTLE_ENDIAN);

		PacketHeader ph = new PacketHeader(bb);

		Object packet = null;
		short playerindex = 0;

		switch (ph.getPacketId()) {
		case 0:
			// motion
			break;
		case 1:
			// session
			packet = new PacketSession(ph, bb);

//			System.out.println(packet);
			break;
		case 2:
			// lap data
			packet = new PacketLapData(ph, bb);
			short pos = 1;
			if (myVehicleIdx > -1) {
				pos = ((PacketLapData) packet).getLapDataList().get(myVehicleIdx).getCarPosition();
			}
			frontVehicleIdx = getVehicleIdxDriverInFront(pos, (PacketLapData) packet);
			behindVehicleIdx = getVehicleIdxDriverBehind(pos, (PacketLapData) packet);

			updateWarnings(frontVehicleIdx, 0);
			updateWarnings(myVehicleIdx, 1);
			updateWarnings(behindVehicleIdx, 2);

//			System.out.println(packet);
			break;
		case 3:
			// event

			packet = new PacketEventData(ph, bb);
			if ("SSTA".equals(((PacketEventData) packet).getEventStringCodeAsString())) {
				vehicleTrackWarnings = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				updateWarnings((short) 0, 0);
				updateWarnings((short) 0, 1);
				updateWarnings((short) 0, 2);
			}
			if ("SEND".equals(((PacketEventData) packet).getEventStringCodeAsString())) {
				useSelf = true;
				changeSelf.setEnabled(false);
			}
			if ("PENA".equals(((PacketEventData) packet).getEventStringCodeAsString())) {
				String name = getNameForIdx(((PacketEventData) packet).getEventDataDetails().getVehicleIdx());
				System.out.println(name + "  " + packet);
//				if (myVehicleIdx == ((PacketEventData) packet).getEventDataDetails().getVehicleIdx()

//						&& 
				if (5 == ((PacketEventData) packet).getEventDataDetails().getPenaltyType()
						&& (27 == ((PacketEventData) packet).getEventDataDetails().getInfringementType()
								|| 7 == ((PacketEventData) packet).getEventDataDetails().getInfringementType())) {
					++vehicleTrackWarnings[((PacketEventData) packet).getEventDataDetails().getVehicleIdx()];
//					warningsCnt.setText(String.valueOf(++warningsSelf));
					if (vehicleTrackWarnings[((PacketEventData) packet).getEventDataDetails().getVehicleIdx()] == 3) {
						vehicleTrackWarnings[((PacketEventData) packet).getEventDataDetails().getVehicleIdx()] = 0;
					}

					updateWarnings(frontVehicleIdx, 0);
					updateWarnings(myVehicleIdx, 1);
					updateWarnings(behindVehicleIdx, 2);
				}
			}
//			System.out.println(packet);
			break;
		case 4:
			// participants

			packet = new PacketParticipants(ph, bb);
			playerindex = ph.getPlayerCarIndex();

			if (useSelf) {
				myVehicleIdx = playerindex;
			}

			participants = (PacketParticipants) packet;

			changeSelf.setEnabled(true);

//			System.out.println(packet);
			break;
		case 5:
			// car setups

			break;
		case 6:
			// car telemetry
			break;
		case 7:
			// car status

			packet = new PacketCarStatus(ph, bb);
			if (writeOnce) {
				System.out.println(packet);
				writeOnce = false;
			}
			break;
		case 8:
			// final classification

			packet = new PacketFinalClassification(ph, bb);
//			System.out.println(packet);
			break;
		case 9:
			// lobby info

			packet = new PacketLobbyInfo(ph, bb);
//			System.out.println(packet);
			break;
		case 10:
			// car damage
			packet = new PacketCarDamage(ph, bb);

			updateTyres(frontVehicleIdx, (PacketCarDamage) packet, 0);
			updateTyres(myVehicleIdx, (PacketCarDamage) packet, 1);
			updateTyres(behindVehicleIdx, (PacketCarDamage) packet, 2);

			updateWarnings(frontVehicleIdx, 0);
			updateWarnings(myVehicleIdx, 1);
			updateWarnings(behindVehicleIdx, 2);

//			System.out.println(packet);

			break;
		case 11:
			// session history

			break;
		default:
			break;
		}

		if (writeFile) {

//			FileOutputStream f = new FileOutputStream(new File("c:/tmp/f1_" + fileNum + ".bin"));
//			ObjectOutputStream o = new ObjectOutputStream(f);
//
//			o.writeUnshared(packet);
//
//			o.close();
//			f.close();
//			fileNum++;

			writeSingleSerFile(packet, ph.getSessionUID());
		}
	}

	private String getNameForIdx(short argVehicleIdx) {
		return participants.getParticipantDataList().get(argVehicleIdx).getNameAsString();

	}

	private void updateWarnings(short argVehicleIdx, int argType) {
		if (argVehicleIdx < 0) {
			return;
		}
		String text = "  " + vehicleTrackWarnings[argVehicleIdx] + "  ";

		Color bgColor = Color.GREEN;
		if (vehicleTrackWarnings[argVehicleIdx] == 1) {
			bgColor = Color.YELLOW;
		}
		if (vehicleTrackWarnings[argVehicleIdx] == 2) {
			bgColor = Color.RED;
		}

		if (argType == 0) {
			vehicleWarningFront.setText(text);
			vehicleWarningFront.setBackground(bgColor);
			vehicleWarningFront.setOpaque(true);
		}
		if (argType == 1) {
			vehicleWarningSelf.setText(text);
			vehicleWarningSelf.setBackground(bgColor);
			vehicleWarningSelf.setOpaque(true);
		}
		if (argType == 2) {
			vehicleWarningBehind.setText(text);
			vehicleWarningBehind.setBackground(bgColor);
			vehicleWarningBehind.setOpaque(true);
		}

	}

	private void updateTyres(short argVehicleIdx, PacketCarDamage argPacket, int argType) {
		if (argVehicleIdx < 0) {
			return;
		}
		float[] tyreWear = argPacket.getCarDamageDataList().get(argVehicleIdx).getTyresWear();
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[2])));
		sb.append(" ");
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[3])));
		sb.append(" ");
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[0])));
		sb.append(" ");
		sb.append(String.format(DECIMAL_FORMAT, (tyreWear[1])));

		if (argType == 0) {

			vehicleTyreFront.setText(sb.toString());
		}
		if (argType == 1) {
			vehicleTyreSelf.setText(sb.toString());
		}
		if (argType == 2) {
			vehicleTyreBehind.setText(sb.toString());
		}

	}

	public void close() {
		socket.close();
	}

	@Override
	public void run() {

		while (running) {
			try {
				output();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		F1Parser f1 = new F1Parser();
//		f1.readFile();
		f1.readSingleSerFile();

//		f1.setWriteFile(true);

//		f1.start();
	}
}
