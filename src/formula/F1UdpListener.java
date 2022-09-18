/**
 * 
 */
package formula;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import formula.packets.IF1Packet;
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
 */
public class F1UdpListener extends Thread {

	private DatagramSocket socket;

	private static boolean running = true;

	public F1UdpListener() {
		try {
			socket = new DatagramSocket(20777);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (running) {
			try {
				listen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void setRunning(boolean argRunning) {
		running = argRunning;
	}

	public void listen() throws IOException {

		byte[] buf = new byte[10000];

		DatagramPacket rawPacket = new DatagramPacket(buf, buf.length);
		socket.receive(rawPacket);

		byte[] data = Arrays.copyOf(rawPacket.getData(), rawPacket.getLength());
		parseData(data);
	}

	private void parseData(byte[] data) throws IOException {
		ByteBuffer bb = ByteBuffer.wrap(data);
		bb = bb.order(ByteOrder.LITTLE_ENDIAN);

		PacketHeader ph = new PacketHeader(bb);

		IF1Packet packet = null;

		switch (ph.getPacketId()) {
		case 0:
			// motion
			break;
		case 1:
			// session
			packet = new PacketSession(ph, bb);
			break;
		case 2:
			// lap data
			packet = new PacketLapData(ph, bb);
			break;
		case 3:
			// event

			packet = new PacketEventData(ph, bb);
			break;
		case 4:
			// participants

			packet = new PacketParticipants(ph, bb);
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
			break;
		case 8:
			// final classification

			packet = new PacketFinalClassification(ph, bb);
			break;
		case 9:
			// lobby info

			packet = new PacketLobbyInfo(ph, bb);
			break;
		case 10:
			// car damage
			packet = new PacketCarDamage(ph, bb);
			break;
		case 11:
			// session history

			break;
		default:
			break;
		}

		handlePacket(packet);

	}

	public static void handlePacket(IF1Packet argF1Packet) {
		if (argF1Packet != null) {

			if (argF1Packet instanceof PacketSession) {
				handleSession((PacketSession) argF1Packet);
			}

			if (argF1Packet instanceof PacketLapData) {
				handleLapData((PacketLapData) argF1Packet);
			}

			if (argF1Packet instanceof PacketEventData) {
				handleEventData((PacketEventData) argF1Packet);
			}

			if (argF1Packet instanceof PacketParticipants) {
				handleParticipants((PacketParticipants) argF1Packet);
			}
			if (argF1Packet instanceof PacketCarStatus) {
				handleCarStatus((PacketCarStatus) argF1Packet);
			}
			if (argF1Packet instanceof PacketFinalClassification) {
				handleFinalClassification((PacketFinalClassification) argF1Packet);
			}
			if (argF1Packet instanceof PacketLobbyInfo) {
				handleLobbyInfo((PacketLobbyInfo) argF1Packet);
			}
			if (argF1Packet instanceof PacketCarDamage) {
				handleCarDamage((PacketCarDamage) argF1Packet);
			}

			if (F1Parser.isWriteFile()) {
				try {
					F1DataHelper.writeSingleSerFile(argF1Packet, argF1Packet.getPacketHeader().getSessionUID());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static void handleSession(PacketSession argPacketSession) {
	}

	private static void handleLapData(PacketLapData argPacketLapData) {

		short pos = 1;
		if (F1DataHelper.getMyVehicleIdx() > -1) {
			pos = argPacketLapData.getLapDataList().get(F1DataHelper.getMyVehicleIdx()).getCarPosition();
		}
		F1DataHelper.setFrontVehicleIdx(F1DataHelper.getVehicleIdxDriverInFront(pos, argPacketLapData));
		F1DataHelper.setBehindVehicleIdx(F1DataHelper.getVehicleIdxDriverBehind(pos, argPacketLapData));

		F1DataHelper.updateWarnings(F1DataHelper.getFrontVehicleIdx(), 0);
		F1DataHelper.updateWarnings(F1DataHelper.getMyVehicleIdx(), 1);
		F1DataHelper.updateWarnings(F1DataHelper.getBehindVehicleIdx(), 2);
	}

	private static void handleEventData(PacketEventData argPacketEventData) {
		if ("SSTA".equals(argPacketEventData.getEventStringCodeAsString())) {
			F1DataHelper.setVehicleTrackWarnings(
					new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
			F1DataHelper.updateWarnings((short) 0, 0);
			F1DataHelper.updateWarnings((short) 0, 1);
			F1DataHelper.updateWarnings((short) 0, 2);
		}
		if ("SEND".equals(argPacketEventData.getEventStringCodeAsString())) {
			F1DataHelper.setUseSelf(true);
			F1Frame.setChangeSelfEnabled(false);
		}
		if ("PENA".equals(argPacketEventData.getEventStringCodeAsString())) {
			String name = F1DataHelper.getNameForIdx(argPacketEventData.getEventDataDetails().getVehicleIdx());
			System.out.println(name + "  " + argPacketEventData);

			if (5 == argPacketEventData.getEventDataDetails().getPenaltyType()
					&& (27 == argPacketEventData.getEventDataDetails().getInfringementType()
							|| 7 == argPacketEventData.getEventDataDetails().getInfringementType())) {

				short idx = argPacketEventData.getEventDataDetails().getVehicleIdx();
				F1DataHelper.increaseWarningByVehicleIdx(idx);

				if (F1DataHelper.getVehicleTrackWarnings()[idx] == 3) {
					F1DataHelper.resetWarningByVehicleIdx(idx);
				}

				F1DataHelper.updateWarnings(F1DataHelper.getFrontVehicleIdx(), 0);
				F1DataHelper.updateWarnings(F1DataHelper.getMyVehicleIdx(), 1);
				F1DataHelper.updateWarnings(F1DataHelper.getBehindVehicleIdx(), 2);
			}
		}
	}

	private static void handleParticipants(PacketParticipants argPacketParticipants) {
		if (F1DataHelper.isUseSelf()) {
			F1DataHelper.setMyVehicleIdx(argPacketParticipants.getPacketHeader().getPlayerCarIndex());
		}
		F1DataHelper.setParticipants(argPacketParticipants);
		F1Frame.setChangeSelfEnabled(true);
	}

	private static void handleCarStatus(PacketCarStatus argCarStatus) {
		if (F1Parser.isWriteOnce()) {
			System.out.println(argCarStatus);
			F1Parser.setWriteOnce(false);
		}
	}

	private static void handleFinalClassification(PacketFinalClassification argPacketFinalClassification) {
	}

	private static void handleLobbyInfo(PacketLobbyInfo argPacketLobbyInfo) {
	}

	private static void handleCarDamage(PacketCarDamage argCarDamage) {

		F1DataHelper.updateTyres(F1DataHelper.getFrontVehicleIdx(), argCarDamage, 0);
		F1DataHelper.updateTyres(F1DataHelper.getMyVehicleIdx(), argCarDamage, 1);
		F1DataHelper.updateTyres(F1DataHelper.getBehindVehicleIdx(), argCarDamage, 2);

		F1DataHelper.updateWarnings(F1DataHelper.getFrontVehicleIdx(), 0);
		F1DataHelper.updateWarnings(F1DataHelper.getMyVehicleIdx(), 1);
		F1DataHelper.updateWarnings(F1DataHelper.getBehindVehicleIdx(), 2);
	}

	public void close() {
		socket.close();
	}
}
