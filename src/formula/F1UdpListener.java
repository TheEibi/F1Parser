/**
 * 
 */
package formula;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import formula.handler.CarDamageHandler;
import formula.handler.CarMotionHandler;
import formula.handler.CarStatusHandler;
import formula.handler.EventDataHandler;
import formula.handler.FinalClassificationHandler;
import formula.handler.LapDataHandler;
import formula.handler.LobbyInfoHandler;
import formula.handler.ParticipantsHandler;
import formula.handler.SessionHandler;
import formula.packets.IF1Packet;
import formula.packets.PacketCarDamage;
import formula.packets.PacketCarMotion;
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

	private static final Logger log = LogManager.getLogger(F1UdpListener.class);

	private DatagramSocket listenSocket;
	private MulticastSocket sendSocket;

	private boolean running = true;

	public F1UdpListener() {
		try {
			listenSocket = new DatagramSocket(Integer.parseInt(System.getProperty("listen.port", "20777")));
			listenSocket.setSoTimeout(100);
//			sendSocket = new MulticastSocket(Integer.parseInt(System.getProperty("send.port", "54321")));
//			sendSocket.setSoTimeout(100);
		} catch (IOException | NumberFormatException e) {
			System.out.println(e);
			log.error(e);
		}
	}

	@Override
	public void run() {

		while (running) {
			try {
				byte[] data = listen();
				IF1Packet packet = parseData(data);
				handlePacket(packet);
//				if (Boolean.parseBoolean(System.getProperty("send.enabled", "false"))) {
//					send(data);
//				}

			} catch (IOException e) {
				log.error(e);
			}
		}

		log.info("================ END ================");

		System.exit(0);
	}

//	private void send(byte[] argData) throws IOException {
//		sendSocket.setBroadcast(true);
//		DatagramPacket packet = new DatagramPacket(argData, argData.length, InetAddress.getByName("127.0.0.1"),
//				Integer.parseInt(System.getProperty("send.port", "54321")));
//		sendSocket.send(packet);
//		sendSocket.close();

//	}

	public void setRunning(boolean argRunning) {
		running = argRunning;
		interrupt();
	}

	public byte[] listen() throws IOException {

		byte[] buf = new byte[10000];

		DatagramPacket rawPacket = new DatagramPacket(buf, buf.length);

		try {
			listenSocket.receive(rawPacket);

		} catch (SocketTimeoutException e) {
			log.trace(e);
		}
		byte[] data = Arrays.copyOf(rawPacket.getData(), rawPacket.getLength());

		return data;
	}

	public IF1Packet parseData(byte[] data) {
		ByteBuffer bb = ByteBuffer.wrap(data);
		bb = bb.order(ByteOrder.LITTLE_ENDIAN);

		short gameVersion = getGameVersion(bb);
		bb.rewind();

		PacketHeader ph = new PacketHeader(gameVersion);

		if (gameVersion == 2022) {
			return parseV22(bb, ph);
		} else if (gameVersion == 2023) {
			return parseV23(bb, ph);
		}
		return null;
	}

	private IF1Packet parseV22(ByteBuffer bb, PacketHeader ph) {
		ph.initV22(bb);

		IF1Packet packet = null;

		switch (ph.getPacketId()) {
		case 0:
			packet = new PacketCarMotion();
			packet.initV22(ph, bb);
			// motion
			break;
		case 1:
			// session
			packet = new PacketSession();
			packet.initV22(ph, bb);
			break;
		case 2:
			// lap data
			packet = new PacketLapData();
			packet.initV22(ph, bb);
			break;
		case 3:
			// event
			packet = new PacketEventData();
			packet.initV22(ph, bb);
			break;
		case 4:
			// participants
			packet = new PacketParticipants();
			packet.initV22(ph, bb);
			break;
		case 5:
			// car setups
			break;
		case 6:
			// car telemetry
			break;
		case 7:
			// car status
			packet = new PacketCarStatus();
			packet.initV22(ph, bb);
			break;
		case 8:
			// final classification
			packet = new PacketFinalClassification();
			packet.initV22(ph, bb);
			break;
		case 9:
			// lobby info
			packet = new PacketLobbyInfo();
			packet.initV22(ph, bb);
			break;
		case 10:
			// car damage
			packet = new PacketCarDamage();
			packet.initV22(ph, bb);
			break;
		case 11:
			// session history
			break;
		default:
			break;
		}

		return packet;
	}

	private IF1Packet parseV23(ByteBuffer bb, PacketHeader ph) {
		ph.initV23(bb);

		IF1Packet packet = null;
		switch (ph.getPacketId()) {
		case 0:
			packet = new PacketCarMotion();
			packet.initV23(ph, bb);
			// motion
			break;
		case 1:
			// session
			packet = new PacketSession();
			packet.initV23(ph, bb);
			break;
		case 2:
			// lap data
			packet = new PacketLapData();
			packet.initV23(ph, bb);
			break;
		case 3:
			// event
			packet = new PacketEventData();
			packet.initV23(ph, bb);
			break;
		case 4:
			// participants
			packet = new PacketParticipants();
			packet.initV23(ph, bb);
			break;
		case 5:
			// car setups
			break;
		case 6:
			// car telemetry
			break;
		case 7:
			// car status
			packet = new PacketCarStatus();
			packet.initV23(ph, bb);
			break;
		case 8:
			// final classification
			packet = new PacketFinalClassification();
			packet.initV23(ph, bb);
			break;
		case 9:
			// lobby info
			packet = new PacketLobbyInfo();
			packet.initV23(ph, bb);
			break;
		case 10:
			// car damage
			packet = new PacketCarDamage();
			packet.initV23(ph, bb);
			break;
		case 11:
			// session history
			break;
		default:
			break;
		}

		return packet;
	}

	public static void handlePacket(IF1Packet argF1Packet) {
		if (argF1Packet != null) {

			if (argF1Packet instanceof PacketSession) {
				SessionHandler.handleSession((PacketSession) argF1Packet);
			} else if (argF1Packet instanceof PacketLapData) {
				LapDataHandler.handleLapData((PacketLapData) argF1Packet);
			} else if (argF1Packet instanceof PacketEventData) {
				EventDataHandler.handleEventData((PacketEventData) argF1Packet);
			} else if (argF1Packet instanceof PacketParticipants) {
				ParticipantsHandler.handleParticipants((PacketParticipants) argF1Packet);
			} else if (argF1Packet instanceof PacketCarStatus) {
				CarStatusHandler.handleCarStatus((PacketCarStatus) argF1Packet);
			} else if (argF1Packet instanceof PacketFinalClassification) {
				FinalClassificationHandler.handleFinalClassification((PacketFinalClassification) argF1Packet);
			} else if (argF1Packet instanceof PacketLobbyInfo) {
				LobbyInfoHandler.handleLobbyInfo((PacketLobbyInfo) argF1Packet);
			} else if (argF1Packet instanceof PacketCarDamage) {
				CarDamageHandler.handleCarDamage((PacketCarDamage) argF1Packet);
			} else if (argF1Packet instanceof PacketCarMotion) {
				CarMotionHandler.handleCarMotion((PacketCarMotion) argF1Packet);
			}

			if (F1Parser.isWriteFile()) {
				try {
					F1DataHelper.writeSingleSerFile(argF1Packet, argF1Packet.getPacketHeader().getSessionUID());
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
	}

	public void close() {
		listenSocket.close();
	}

	public void reRunFile(String argFileName) {
		if (!Boolean.parseBoolean(System.getProperty("data.reRunFile.log.enabled", "false"))) {
			LogManager.shutdown();
		}
		F1DataHelper.readSingleSerFile(argFileName);
	}

	public short getGameVersion(ByteBuffer argBb) {
		short gameVersion = argBb.getShort();
		return gameVersion;
	}
}
