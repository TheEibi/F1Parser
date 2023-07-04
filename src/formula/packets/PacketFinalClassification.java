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
public class PacketFinalClassification implements Serializable, IF1Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3080461537027593448L;
	private PacketHeader packetHeader;
	private short numCars;
	private List<FinalClassificationData> finalClassificationDataList;

	public void initV22(PacketHeader argPacketHeader, ByteBuffer argBb) {
		packetHeader = argPacketHeader;
		numCars = argBb.get();
		finalClassificationDataList = new ArrayList<>();
		for (int i = 0; i < 22; i++) {
			FinalClassificationData fcd = new FinalClassificationData();
			fcd.initV22(argBb);
			finalClassificationDataList.add(fcd);
		}
	}
	
	public void initV23(PacketHeader argPacketHeader, ByteBuffer argBb) {
		initV22(argPacketHeader, argBb);
	}

	public PacketHeader getPacketHeader() {
		return packetHeader;
	}

	public short getNumCars() {
		return numCars;
	}

	public List<FinalClassificationData> getFinalClassificationDataList() {
		return finalClassificationDataList;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append(getPacketHeader());
		sb.append("::");
		sb.append(getClass().getSimpleName());
		sb.append("::");
		sb.append(getNumCars());
		for (FinalClassificationData finalClassificationData : finalClassificationDataList) {
			sb.append("::");
			sb.append(finalClassificationData);
		}
		return sb.toString();
	}

}
