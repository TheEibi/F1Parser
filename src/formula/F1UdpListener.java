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
			if (F1DataHelper.getMyVehicleIdx() > -1) {
				pos = ((PacketLapData) packet).getLapDataList().get(F1DataHelper.getMyVehicleIdx()).getCarPosition();
			}
			F1DataHelper.setFrontVehicleIdx(F1DataHelper.getVehicleIdxDriverInFront(pos, (PacketLapData) packet));
			F1DataHelper.setBehindVehicleIdx(F1DataHelper.getVehicleIdxDriverBehind(pos, (PacketLapData) packet));

			F1DataHelper.updateWarnings(F1DataHelper.getFrontVehicleIdx(), 0);
			F1DataHelper.updateWarnings(F1DataHelper.getMyVehicleIdx(), 1);
			F1DataHelper.updateWarnings(F1DataHelper.getBehindVehicleIdx(), 2);

//			System.out.println(packet);
			break;
		case 3:
			// event

			packet = new PacketEventData(ph, bb);
			if ("SSTA".equals(((PacketEventData) packet).getEventStringCodeAsString())) {
				F1DataHelper.setVehicleTrackWarnings(
						new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
				F1DataHelper.updateWarnings((short) 0, 0);
				F1DataHelper.updateWarnings((short) 0, 1);
				F1DataHelper.updateWarnings((short) 0, 2);
			}
			if ("SEND".equals(((PacketEventData) packet).getEventStringCodeAsString())) {
				F1DataHelper.setUseSelf(true);
				F1Frame.setChangeSelfEnabled(false);
			}
			if ("PENA".equals(((PacketEventData) packet).getEventStringCodeAsString())) {
				String name = F1DataHelper
						.getNameForIdx(((PacketEventData) packet).getEventDataDetails().getVehicleIdx());
				System.out.println(name + "  " + packet);

				if (5 == ((PacketEventData) packet).getEventDataDetails().getPenaltyType()
						&& (27 == ((PacketEventData) packet).getEventDataDetails().getInfringementType()
								|| 7 == ((PacketEventData) packet).getEventDataDetails().getInfringementType())) {

					short idx = ((PacketEventData) packet).getEventDataDetails().getVehicleIdx();
					F1DataHelper.increaseWarningByVehicleIdx(idx);

					if (F1DataHelper.getVehicleTrackWarnings()[idx] == 3) {
						F1DataHelper.resetWarningByVehicleIdx(idx);
					}

					F1DataHelper.updateWarnings(F1DataHelper.getFrontVehicleIdx(), 0);
					F1DataHelper.updateWarnings(F1DataHelper.getMyVehicleIdx(), 1);
					F1DataHelper.updateWarnings(F1DataHelper.getBehindVehicleIdx(), 2);
				}
			}
//			System.out.println(packet);
			break;
		case 4:
			// participants

			packet = new PacketParticipants(ph, bb);
			playerindex = ph.getPlayerCarIndex();

			if (F1DataHelper.isUseSelf()) {
				F1DataHelper.setMyVehicleIdx(playerindex);
			}

			F1DataHelper.setParticipants((PacketParticipants) packet);

			F1Frame.setChangeSelfEnabled(true);

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
			if (F1Parser.isWriteOnce()) {
				System.out.println(packet);
				F1Parser.setWriteOnce(false);
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

			F1DataHelper.updateTyres(F1DataHelper.getFrontVehicleIdx(), (PacketCarDamage) packet, 0);
			F1DataHelper.updateTyres(F1DataHelper.getMyVehicleIdx(), (PacketCarDamage) packet, 1);
			F1DataHelper.updateTyres(F1DataHelper.getBehindVehicleIdx(), (PacketCarDamage) packet, 2);

			F1DataHelper.updateWarnings(F1DataHelper.getFrontVehicleIdx(), 0);
			F1DataHelper.updateWarnings(F1DataHelper.getMyVehicleIdx(), 1);
			F1DataHelper.updateWarnings(F1DataHelper.getBehindVehicleIdx(), 2);

//			System.out.println(packet);

			break;
		case 11:
			// session history

			break;
		default:
			break;
		}

		if (F1Parser.isWriteFile()) {
			F1DataHelper.writeSingleSerFile(packet, ph.getSessionUID());
		}
	}

	public void close() {
		socket.close();
	}
}
