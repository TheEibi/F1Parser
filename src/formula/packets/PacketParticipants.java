/**
 * 
 */
package formula.packets;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author reinh
 *
 */
public class PacketParticipants implements Serializable, IF1Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3553355621990177829L;
	private PacketHeader packetHeader;
	private short numActiveCars;
	private List<ParticipantData> participantDataList;

	public void initV22(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;

		numActiveCars = argBb.get();

		participantDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			ParticipantData pc = new ParticipantData();
			pc.initV22(argBb);
			participantDataList.add(pc);
		}
	}
	
	public void initV23(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;

		numActiveCars = argBb.get();

		participantDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			ParticipantData pc = new ParticipantData();
			pc.initV23(argBb);
			participantDataList.add(pc);
		}
	}

	public PacketHeader getPacketHeader() {
		return packetHeader;
	}

	public short getNumActiveCars() {
		return numActiveCars;
	}

	public List<ParticipantData> getParticipantDataList() {
		return participantDataList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getPacketHeader());
		sb.append("::");
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(Byte.toUnsignedInt((byte) getNumActiveCars()));

		sb.append("::");
		for (ParticipantData participantData : participantDataList) {
			sb.append(participantData);
		}

		return sb.toString();

	}

}
