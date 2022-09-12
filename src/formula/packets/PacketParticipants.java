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
public class PacketParticipants implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3553355621990177829L;
	private PacketHeader header;
	private short numActiveCars;
	private List<ParticipantData> participantDataList;

	public PacketParticipants(PacketHeader argHeader, ByteBuffer argBb) {
		header = argHeader;

		numActiveCars = argBb.get();

		participantDataList = new ArrayList<>();

		for (int i = 0; i < 22; i++) {
			participantDataList.add(new ParticipantData(argBb));
		}

	}

	public PacketHeader getHeader() {
		return header;
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
		sb.append(getHeader());
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
